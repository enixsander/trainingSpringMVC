package site.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import site.model.User;

import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    public void persist(User entity) throws SQLException  {
        getSession().persist(entity);
    }

    public void update(User entity) throws SQLException  {
        getSession().update(entity);
    }

    public void delete(User entity) throws SQLException  {
        getSession().delete(entity);
    }

    public List getAllUsers() throws SQLException {
        List users;
        users =  getSession().createCriteria(User.class).list();
        return users;
    }
}
