package classDAO;

public interface DAO<Type, ID> {

    public Type find(Class <Type> typo,ID id);

}
