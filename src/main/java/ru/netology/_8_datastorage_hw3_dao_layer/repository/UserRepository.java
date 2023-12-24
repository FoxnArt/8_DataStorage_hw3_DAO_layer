package ru.netology._8_datastorage_hw3_dao_layer.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final String myScriptFileName = "myScript.sql";
    private final String myScriptContent;


    public UserRepository() {
        this.myScriptContent = read(myScriptFileName);
    }

    public List<String> getProductName(String name) {
        var parameters = new HashMap<String, Object>();
        parameters.put("name", name);
        var result = namedParameterJdbcTemplate.queryForList(myScriptContent, parameters, String.class);
        return result;
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
