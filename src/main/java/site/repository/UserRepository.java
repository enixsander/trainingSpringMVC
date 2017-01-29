package site.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import site.model.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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

        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        // Specify criteria root
        criteria.from(User.class);
        // Execute query
        return getSession().createQuery(criteria).getResultList();
    }

    //JDBC
    /*public void saveUser(User entity) throws SQLException {
        String sql = "INSERT INTO spring.User (_id, name, country) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, entity.get_id(), entity.getName(), entity.getCountry());
    }

    public List<User> findAll() throws SQLException{
        String sql = "SELECT * FROM spring.User";
        return jdbcOperations.query(sql, new BeanPropertyRowMapper<User>(User.class));
    }*/
}
