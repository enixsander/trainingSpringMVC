package site.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import site.model.Marker;
import site.model.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MarkerRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public MarkerRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    public void persist(Marker entity) throws SQLException {
        getSession().persist(entity);
    }

    public void update(Marker entity) throws SQLException  {
        getSession().merge(entity);
    }

    public void delete(Marker entity) throws SQLException  {
        getSession().delete(entity);
    }

    public List getAllMarkers() throws SQLException {

        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<Marker> criteria = builder.createQuery(Marker.class);
        // Specify criteria root
        criteria.from(Marker.class);

        return getSession().createQuery(criteria).getResultList();
    }

    public Marker findByMarkerID(long id) throws SQLException {

        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<Marker> criteria = builder.createQuery(Marker.class);
        // Specify criteria root
        Root<Marker> root = criteria.from(Marker.class);

        criteria.where(builder.equal(root.get("marker_id"), id));

        return getSession().createQuery(criteria).getSingleResult();
    }

    public List getMarkersByUser(User user) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<Marker> criteria = builder.createQuery(Marker.class);
        // Specify criteria root
        Root<Marker> root = criteria.from(Marker.class);

        criteria.where(builder.equal(root.get("user"), user));

        return getSession().createQuery(criteria).getResultList();
    }
}
