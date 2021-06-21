package com.example.application.services.gorm

import com.example.application.model.HelloBean

import grails.gorm.services.Service
import grails.gorm.services.Where

@org.springframework.stereotype.Service
@Service(HelloBean)
interface HelloGormService {

    @Where({ helloField ==~ '%' + string + '%' })
    List<HelloBean> getAllWithHelloFieldContaining(String string)
}
