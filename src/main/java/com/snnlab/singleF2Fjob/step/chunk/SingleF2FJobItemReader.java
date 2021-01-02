package com.snnlab.singleF2Fjob.step.chunk;

import com.snnlab.singleF2Fjob.model.SnnLabInfoDTO;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.FileSystemResource;


public class SingleF2FJobItemReader implements ItemReader<SnnLabInfoDTO> {

    private FlatFileItemReader<SnnLabInfoDTO> paymentInfoDTOFlatFileItemReader;

    public SingleF2FJobItemReader(){
        DefaultLineMapper<SnnLabInfoDTO> defaultLineMapper = generateDefaultLineMapper();
        paymentInfoDTOFlatFileItemReader = new FlatFileItemReader<SnnLabInfoDTO>();
        paymentInfoDTOFlatFileItemReader.setResource(new FileSystemResource("snnLabF2FJobReaderInput.txt"));
        paymentInfoDTOFlatFileItemReader.setEncoding("UTF-8");
        paymentInfoDTOFlatFileItemReader.setLineMapper(defaultLineMapper);
        paymentInfoDTOFlatFileItemReader.open(new ExecutionContext());
    }

    @Override
    public SnnLabInfoDTO read() throws Exception {
        return paymentInfoDTOFlatFileItemReader.read();
    }

    private DefaultLineMapper<SnnLabInfoDTO> generateDefaultLineMapper() {
        DefaultLineMapper<SnnLabInfoDTO> defaultLineMapper = new DefaultLineMapper<>();
        FieldSetMapper<SnnLabInfoDTO> fieldSetMapper = generateFieldSetMapper();
        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer(";");

        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);

        return defaultLineMapper;
    }

    private FieldSetMapper<SnnLabInfoDTO> generateFieldSetMapper() {
        FieldSetMapper<SnnLabInfoDTO> fieldSetMapper = fieldSet -> {
            SnnLabInfoDTO snnLabInfoDTO = new SnnLabInfoDTO();
            snnLabInfoDTO.setLabId(fieldSet.readString(0));
            snnLabInfoDTO.setLabAmount(fieldSet.readBigDecimal(1));
            snnLabInfoDTO.setLabCurrency(fieldSet.readString(2));

            return snnLabInfoDTO;
        };

        return fieldSetMapper;
    }

}
