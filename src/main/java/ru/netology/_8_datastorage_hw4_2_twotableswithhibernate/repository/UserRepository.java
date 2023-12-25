package ru.netology._8_datastorage_hw4_2_twotableswithhibernate.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
public class UserRepository {

    private final String myScriptFileName = "myScript.sql";
    private final String myScriptContent;

    @PersistenceContext
    private EntityManager entityManager;

    public UserRepository() {
        this.myScriptContent = read(myScriptFileName);
    }

    public List<String> getProductName(String name) {
        Query personList = entityManager.createNativeQuery(myScriptContent, String.class);
        personList.setParameter("name", name);
        return personList.getResultList();
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