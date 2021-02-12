package com.snnlab.mutliplespringbatchjob.step.chunk.d2f;

import com.snnlab.mutliplespringbatchjob.model.SnnLabInfoDTO;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;

public class JdbcCursorItemReaderTemplate extends JdbcCursorItemReader<SnnLabInfoDTO>  {

    public JdbcCursorItemReaderTemplate(DataSource dataSource){
        this.setDataSource(dataSource);
        this.setRowMapper(getRowMapper());
        this.setSql("set sql for your db access");

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
