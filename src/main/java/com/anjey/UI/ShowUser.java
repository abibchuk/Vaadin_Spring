package com.anjey.UI;

import com.anjey.User;
import com.anjey.UserDao;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Theme("mytheme")
@SpringUI(path = "show")
public class ShowUser extends UI {

    @Autowired
    UserDao userDao;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        VerticalLayout layout = new VerticalLayout();

        Grid<User> grid = new Grid<>("Users");

//        grid.addColumn("Id");
//        grid.addColumn("Name");
//        grid.addColumn("Price");
        Button loadButton = new Button("Load data");

        loadButton.addClickListener(e -> {

//            Session session;
//            try {
//                session = sessionFactory.getCurrentSession();
//            } catch (HibernateException he) {
//                session = sessionFactory.openSession();
//            }
//                    = this.sessionFactory.getCurrentSession();


            grid.setItems(userDao.getAll());
            grid.addColumn(User::getName).setCaption("Name");
            grid.addColumn(User::getPassword).setCaption("Password");
        });

        layout.addComponents(grid, loadButton);
        layout.setMargin(true);
        layout.setSpacing(true);

        setContent(layout);

    }

}
