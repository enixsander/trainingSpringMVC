package site.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import site.model.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
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
        Root<User> root = criteria.from(User.class);
        // TODO
        //root.fetch("marker");//, JoinType.INNER);
        //criteria.select(root);
        // Execute query
        return getSession().createQuery(criteria).getResultList();
    }

    public User findByUserName(String username) throws SQLException {

        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        // Specify criteria root
        Root<User> root = criteria.from(User.class);

        Path<String> namePath = root.get("login");
        criteria.where(builder.equal(namePath, username));
        // Execute query
        User user = getSession().createQuery(criteria).getSingleResult();

        /*if(user!=null)
            Hibernate.initialize(user.getMarker());*/

        return user;
    }

    public User findByUserID(long id) throws SQLException {

        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        // Specify criteria root
        Root<User> root = criteria.from(User.class);

        criteria.where(builder.equal(root.get("_id"), id));

        return getSession().createQuery(criteria).getSingleResult();
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
