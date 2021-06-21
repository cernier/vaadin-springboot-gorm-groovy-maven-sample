package com.example.application.services

import org.springframework.stereotype.Service

@Service
class HelloService {

    String getHello(String name) {
        "Hello Custom from service ${name}"
    }
}
