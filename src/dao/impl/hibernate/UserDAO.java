package dao.impl.hibernate;

import entities.User;

import java.util.List;

public class UserDAO extends DAOHibImpl<User, Integer> {

    @Override
    public Class<User> getModelClass() {
        return User.class;
    }

    public List<User> findAll () {
        return super.findAll(this.getModelClass());
    }
}

