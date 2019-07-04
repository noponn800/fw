package com.rafal.family;

import com.rafal.family.model.ApplicationUser;
import com.rafal.family.repositories.UserRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.textfield.TextField;

@Route("")
@SpringComponent
public class LoginView extends Div {
    @Autowired
            UserRepository repository;
    VerticalLayout vl = new VerticalLayout();
    Button bt = new Button("Add");
    TextField usernameField = new TextField("Username");
    PasswordField passwordField = new PasswordField("Password");

    public LoginView(){
        vl.addComponentAsFirst(bt);
        vl.addComponentAsFirst(usernameField);
        vl.addComponentAsFirst(passwordField);
        bt.addClickListener(e -> {
//            ApplicationUser user = new ApplicationUser("rafal","cezori","raf","al");
//            repository.save(user);
            String username = usernameField.getValue();
            String password = passwordField.getValue();
            Notification.show("Username: " + username + " Password: " + password);
        });
        setTitle("Hello World!");
        add(vl);
    }
}
