package com.snnlab.singleF2Fjob.step.chunk;

import com.snnlab.singleF2Fjob.model.SnnLabInfoDTO;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.core.io.FileSystemResource;

import java.util.List;

public class SingleF2FJobItemWriter implements ItemWriter<SnnLabInfoDTO> {

    private FlatFileItemWriter<SnnLabInfoDTO> paymentInfoDTOFlatFileItemWriter;

    public SingleF2FJobItemWriter(){
        paymentInfoDTOFlatFileItemWriter = new FlatFileItemWriter<>();
        paymentInfoDTOFlatFileItemWriter.setResource(new FileSystemResource("snnLabF2FJobReaderOutput.txt"));
        paymentInfoDTOFlatFileItemWriter.setEncoding("UTF8");
        paymentInfoDTOFlatFileItemWriter.setLineAggregator(generateLineAgregator());
        paymentInfoDTOFlatFileItemWriter.setAppendAllowed(true);
        paymentInfoDTOFlatFileItemWriter.open(new ExecutionContext());
    }

    @Override
    public void write(List<? extends SnnLabInfoDTO> list) throws Exception {
        paymentInfoDTOFlatFileItemWriter.write(list);
    }

    private LineAggregator<SnnLabInfoDTO> generateLineAgregator() {
        return new DelimitedLineAggregator<>() {
            {
                setDelimiter("|");
                setFieldExtractor(new BeanWrapperFieldExtractor<>() {
                    { setNames(new String[]{"labId", "labAmount", "labCurrency"});}
                });
            }
        };
    }
}
