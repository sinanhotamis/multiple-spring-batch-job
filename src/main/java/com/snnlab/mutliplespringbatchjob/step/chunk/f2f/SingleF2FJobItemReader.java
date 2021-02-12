package com.snnlab.mutliplespringbatchjob.step.chunk.f2f;

import com.snnlab.mutliplespringbatchjob.model.SnnLabInfoDTO;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.FileUrlResource;

import java.net.MalformedURLException;

public class SingleF2FJobItemReader extends FlatFileItemReader<SnnLabInfoDTO>  {

    private static final String ENCODING_UTF8 = "UTF-8";
    private static final String ITEM_READER_LINE_TOKENIZER = ";";
    private static final String ITEM_READER_INPUT_FILE = "snnLabF2FJobReaderInput.txt";
    private static final String RESOURCE_PATH = "/Users/sinan.hotamis/";

    public SingleF2FJobItemReader(){
        DefaultLineMapper<SnnLabInfoDTO> defaultLineMapper = generateDefaultLineMapper();
        this.setEncoding(ENCODING_UTF8);
        this.setLineMapper(defaultLineMapper);
    }

    @BeforeStep
    public void beforeStep() throws MalformedURLException {
        //You can change resource info programmaticalyy before step execution.
        this.setResource(new FileUrlResource(RESOURCE_PATH + ITEM_READER_INPUT_FILE));
    }

    private DefaultLineMapper<SnnLabInfoDTO> generateDefaultLineMapper() {
        DefaultLineMapper<SnnLabInfoDTO> defaultLineMapper = new DefaultLineMapper<>();
        FieldSetMapper<SnnLabInfoDTO> fieldSetMapper = generateFieldSetMapper();
        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer(ITEM_READER_LINE_TOKENIZER);

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
