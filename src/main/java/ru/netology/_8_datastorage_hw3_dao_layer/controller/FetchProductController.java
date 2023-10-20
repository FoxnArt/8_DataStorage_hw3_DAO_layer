package ru.netology._8_datastorage_hw3_dao_layer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.netology._8_datastorage_hw3_dao_layer.repository.UserRepository;


import java.util.List;

@RestController
public class FetchProductController {

    private final UserRepository userRepository;

    public FetchProductController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/products/fetch-product")
        public String getProductName(@RequestParam("name") String name) {
            return userRepository.getProductName(name);
        }
}
