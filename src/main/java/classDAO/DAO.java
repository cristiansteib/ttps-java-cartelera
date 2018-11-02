package main.java.classDAO;

public interface DAO<Type, ID> {

    Type getById(Class <Type> typo,ID id);

    void create (Type entity);

    Type update (Type entity);

    void remove (Type entity);

}
