package com.anjey.UI;

import com.anjey.User;
import com.anjey.UserDao;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("mytheme")
@SpringUI(path = "add")
public class AddUser extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        
        final TextField name = new TextField();
        name.setCaption("Name:");

        final TextField password = new TextField();
        password.setCaption("Password:");

        Button button = new Button("Submit");
        button.addClickListener(e -> {
            User user = new User();
            UserDao dao = new UserDao();
            user.setName(name.getValue());
            user.setPassword(password.getValue());
            dao.add(user);
            getPage().setLocation("/show");
//            mainWindow.showNotification("Success");
        });

        layout.addComponents(name, password, button);

        setContent(layout);

    }
//    JS error will occurred in browser if don't add /VAADIN/* url pattern in one of our servlets
//    @WebServlet(urlPatterns = {"/add/*", "/VAADIN/*"}, name = "AddServlet", asyncSupported = true)
//    @VaadinServletConfiguration(ui = AddUser.class, productionMode = false)
//    public static class AddServlet extends VaadinServlet {
//    }
}
