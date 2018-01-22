package com.anjey;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    Connection connection;
    PreparedStatement ps;

    public UserDao() {
    }

    public void add(User p) {
        try {
            connection = DbConnection.getConnection();
            ps = connection.prepareStatement("CALL proc_insert(?,?)");
            ps.setString(1, p.getName());
            ps.setString(2, p.getPassword());
            ps.executeUpdate();
            ps.close();
            connection.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public List<User> getAll(){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createNativeQuery("CALL proc_select()");
        List<Object[]> list = query.list();
        List<User> userList = new ArrayList<User>();
        for (Object[] row : list){
            User user = new User();
            user.setName((String) row[0]);
            user.setPassword((String) row[1]);
            // set the rest of the properties
            userList.add(user);
        }
        return userList;
    }
}