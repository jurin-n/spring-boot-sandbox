package com.jurin_n.csvprocessing;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
    @Bean
    public FlatFileItemReader<CsvContent> reader(){
        return new FlatFileItemReaderBuilder<CsvContent>()
                .name("csvContentReader")
                .build();
    }
    
    @Bean
    public CsvContentItemProcessor process() {
        return new CsvContentItemProcessor();
    }
}
