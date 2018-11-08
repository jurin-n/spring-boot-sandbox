package com.jurin_n.csvprocessing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class CsvContentItemProcessor implements ItemProcessor<CsvContent, CsvContent> {

    private static final Logger log = LoggerFactory.getLogger(CsvContentItemProcessor.class);
    
    @Override
    public CsvContent process(final CsvContent csvContent) throws Exception {
        log.info("Converting (" + csvContent + ")");
        return csvContent;
    }
}
