package main.java.classDAO.dao;

import main.java.entities.User;

public class UserDAO extends DAOHibernateImplementation<User, Integer> {

    @Override
    String getModelName() {
        return "User";
    }
}

