package com.example.application.services

import org.springframework.stereotype.Service

import com.example.application.model.HelloBean

@Service
class HelloService {

    HelloBean getHelloBean(String name) {
        new HelloBean(helloNumber: 42, helloField : "Hello Custom from service ${name}")
    }
}
