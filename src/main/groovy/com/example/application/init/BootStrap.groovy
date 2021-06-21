package com.example.application.init

import org.springframework.stereotype.Component

import com.example.application.model.HelloBean

import grails.gorm.transactions.Transactional

@Component
class BootStrap {

    @Transactional
    public void init() {
        new HelloBean(helloNumber: 42, helloField: 'short message').save()
        new HelloBean(helloNumber: 13, helloField: 'loooooooooooooooooooooooong message').save()
        new HelloBean(helloNumber: -1, helloField: 'another short message').save()
        new HelloBean(helloNumber: 100, helloField: null).save()
    }
}
