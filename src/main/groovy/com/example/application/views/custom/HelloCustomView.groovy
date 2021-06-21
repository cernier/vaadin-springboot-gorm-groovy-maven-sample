package com.example.application.views.custom

import com.example.application.views.MainLayout
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.notification.Notification
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.router.PageTitle
import com.vaadin.flow.router.Route

@Route(value = 'hello-custom', layout = MainLayout.class)
@PageTitle('Hello Custom')
public class HelloCustomView extends HorizontalLayout {

    private TextField name
    private Button sayHello

    public HelloCustomView() {
        addClassName('hello-world-view')
        name = new TextField('Your custom name')
        sayHello = new Button('Say hello custom')
        add(name, sayHello)
        setVerticalComponentAlignment(Alignment.END, name, sayHello)
        sayHello.addClickListener({ e ->
            Notification.show("Hello Custom ${name.value}")
        })
    }
}
