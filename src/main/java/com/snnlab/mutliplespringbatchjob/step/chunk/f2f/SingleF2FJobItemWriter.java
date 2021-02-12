package com.snnlab.mutliplespringbatchjob.step.chunk.f2f;

import com.snnlab.mutliplespringbatchjob.model.SnnLabInfoDTO;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.core.io.FileUrlResource;

import java.net.MalformedURLException;
import java.util.List;

public class SingleF2FJobItemWriter extends FlatFileItemWriter<SnnLabInfoDTO>  {

    private static final String ENCODING_UTF8 = "UTF-8";
    private static final String ITEM_WRITER_LINE_DELIMITER= "|";
    private static final String ITEM_WRITER_OUTPUT_FILE = "snnLabF2FJobReaderOutput.txt";
    private static final String RESOURCE_PATH = "/Users/sinan.hotamis/";

    public SingleF2FJobItemWriter(){
        this.setEncoding(ENCODING_UTF8);
        this.setLineAggregator(generateLineAgregator());
        this.setAppendAllowed(true);
    }

    @BeforeStep
    public void beforeStepActions() throws MalformedURLException {
        this.setResource(new FileUrlResource(RESOURCE_PATH + ITEM_WRITER_OUTPUT_FILE));
    }

    private LineAggregator<SnnLabInfoDTO> generateLineAgregator() {
        return new DelimitedLineAggregator<>() {
            {
                setDelimiter(ITEM_WRITER_LINE_DELIMITER);
                setFieldExtractor(new BeanWrapperFieldExtractor<>() {
                    { setNames(new String[]{"labId", "labAmount", "labCurrency"});}
                });
            }
        };
    }
}
