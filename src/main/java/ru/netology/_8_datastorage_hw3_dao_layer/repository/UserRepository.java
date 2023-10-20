package ru.netology._8_datastorage_hw3_dao_layer.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.netology._8_datastorage_hw3_dao_layer.model.Order;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.stream.Collectors;

@Repository
public class UserRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final String myScriptFileName = "myScript.sql";
    private String myScriptContent;


    public UserRepository() {
    }

    public String getProductName(String name) {
        myScriptContent = read(myScriptFileName);
        var parameters = new HashMap<String, Object>();
        parameters.put("name", name);
        var result = namedParameterJdbcTemplate.queryForObject(myScriptContent,
                parameters,
                (ResultSet resultSet, int rowNum) -> {
                    var productName = resultSet.getString(1);

                    return new Order(productName);
                });
        return result.toString();
    }

    private static String read(String scriptFileName) {
        try (InputStream is = new ClassPathResource(scriptFileName).getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is))) {
            return bufferedReader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
