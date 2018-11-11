package com.jurin_n.csvprocessing;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.separator.DefaultRecordSeparatorPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader<CsvContent> reader(){
        return new FlatFileItemReaderBuilder<CsvContent>()
                .name("csvContentReader")
                .resource(new ClassPathResource("sample-data.csv"))
                .linesToSkip(1)
                .recordSeparatorPolicy(new DefaultRecordSeparatorPolicy())
                .delimited()
                .names(new String[]{"title", "description"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<CsvContent>() {{
                    setTargetType(CsvContent.class);
                }})
                .build();
    }

    @Bean
    public CsvContentItemProcessor processor() {
        return new CsvContentItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<CsvContent> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<CsvContent>()
            .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
            .sql("INSERT INTO csv_content (title, description) VALUES (:title, :description)")
            .dataSource(dataSource)
            .build();
    }

    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importUserJob")
            .incrementer(new RunIdIncrementer())
            .listener(listener)
            .flow(step1)
            .end()
            .build();
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<CsvContent> writer) {
        return stepBuilderFactory.get("step1")
            .<CsvContent, CsvContent> chunk(500)
            .reader(reader())
            .processor(processor())
            .writer(writer)
            .build();
    }
}
