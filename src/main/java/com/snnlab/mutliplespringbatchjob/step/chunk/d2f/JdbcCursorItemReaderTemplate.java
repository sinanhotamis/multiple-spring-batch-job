package com.snnlab.mutliplespringbatchjob.step.chunk.d2f;

import com.snnlab.mutliplespringbatchjob.model.SnnLabInfoDTO;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;

public class JdbcCursorItemReaderTemplate implements ItemReader<SnnLabInfoDTO> {

    private JdbcCursorItemReader<SnnLabInfoDTO> jdbcCursorItemReader;

    public JdbcCursorItemReaderTemplate(DataSource dataSource){
        jdbcCursorItemReader = new JdbcCursorItemReader<>();
        jdbcCursorItemReader.setDataSource(dataSource);
        jdbcCursorItemReader.setRowMapper(getRowMapper());
    }

    @BeforeStep
    public void beforeStep() {
        //You can change sql query programmaticaly before step action..
        jdbcCursorItemReader.setSql("set sql for your db access");
        jdbcCursorItemReader.open(new ExecutionContext());
    }

    @Override
    public SnnLabInfoDTO read() throws Exception{
        return jdbcCursorItemReader.read();
    }

    @AfterStep
    public void afterStep(){
        jdbcCursorItemReader.close();
    }


    private RowMapper<SnnLabInfoDTO> getRowMapper() {
        RowMapper<SnnLabInfoDTO> rowMapper = (resultSet, i) -> {
            SnnLabInfoDTO snnLabInfoDTO = new SnnLabInfoDTO();
            snnLabInfoDTO.setLabId(resultSet.getString("set your own column name"));
            snnLabInfoDTO.setLabAmount(resultSet.getBigDecimal("set your own column name"));

            return snnLabInfoDTO;
        };
        return rowMapper;
    }
}
