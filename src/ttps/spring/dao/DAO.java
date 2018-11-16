package ttps.spring.dao;


public interface DAO<Type, ID> {

    Type getById(Class <Type> typo,ID id);

    Type create (Type entity);

    Type update (Type entity);

    void remove (Type entity);

}