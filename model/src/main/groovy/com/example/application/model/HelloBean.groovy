package com.example.application.model

import org.grails.datastore.gorm.GormEntity

import grails.gorm.annotation.Entity

@Entity
class HelloBean implements Serializable, GormEntity<HelloBean> {

    Integer helloNumber
    String helloField

    static constraints = {
        helloField nullable: true
    }
}
