package dao.impl.hibernate;

import entities.User;

import java.util.List;

public class UserDAO extends DAOHibernateImplementation<User, Integer> {

    @Override
    String getModelName() {
        return "User";
    }
}

