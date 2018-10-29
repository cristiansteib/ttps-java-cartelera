package classDAO;

public interface DAO<Type, ID> {

    Type find(Class <Type> typo,ID id);

    void create (Type entity);

}
