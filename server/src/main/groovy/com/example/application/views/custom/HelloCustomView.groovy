package com.example.application.views.custom

import org.springframework.beans.factory.annotation.Autowired

import com.example.application.model.HelloBean
import com.example.application.services.HelloService
import com.example.application.views.MainLayout
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.notification.Notification
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.router.PageTitle
import com.vaadin.flow.router.Route
import com.vaadin.flow.spring.annotation.SpringComponent
import com.vaadin.flow.spring.annotation.UIScope

@Route(value = 'hello-custom', layout = MainLayout.class)
@PageTitle('Hello Custom')
@SpringComponent
@UIScope
public class HelloCustomView extends HorizontalLayout {

    @Autowired
    HelloService service

    private TextField name
    private Button sayHello

    public HelloCustomView() {
        addClassName('hello-world-view')
        name = new TextField('Your custom name')
        sayHello = new Button('Say hello custom')
        add(name, sayHello)
        setVerticalComponentAlignment(Alignment.END, name, sayHello)
        sayHello.addClickListener({ e ->
            service.customSearchHelloBeans(name.getValue()).each { HelloBean helloBean ->
                Notification.show("${helloBean.helloNumber} : ${helloBean.helloField}")
            }
        })
    }
}
