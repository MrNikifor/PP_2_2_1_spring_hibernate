package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        String hql = "SELECT DISTINCT user FROM User user LEFT JOIN FETCH user.car";
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hql, User.class);
        return query.getResultList();
    }


    @Override
    public Optional<User> getUserByCar(String model, int series) {
        String hql = "SELECT user FROM User user JOIN FETCH user.car WHERE user.car.model = :model AND user.car.series = :series";
        Query<User> query = sessionFactory.getCurrentSession().createQuery(hql, User.class);
        query.setParameter("model", model);
        query.setParameter("series", series);

        try {
            return Optional.of(query.setMaxResults(1).getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

}
