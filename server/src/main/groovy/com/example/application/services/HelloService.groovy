package com.example.application.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import com.example.application.model.HelloBean
import com.example.application.services.gorm.HelloGormService

@Service
class HelloService {

    @Autowired
    HelloGormService gormService

    List<HelloBean> customSearchHelloBeans(String searchValue) {
        try {
            // if searchValue is a number, use this GORM finder
            return HelloBean.findAllByHelloFieldIsNullOrHelloNumberGreaterThan(searchValue as Integer)
        } catch (NumberFormatException e) {
            // otherwise, use this GORM data service
            return gormService.getAllWithHelloFieldContaining(searchValue)
        }
    }
}
