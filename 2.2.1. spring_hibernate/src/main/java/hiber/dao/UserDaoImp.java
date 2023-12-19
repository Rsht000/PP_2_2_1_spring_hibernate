package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

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
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public void getUserByCar(Car car) {
      try(Session session = sessionFactory.openSession()) {
         String hql = "from User u  LEFT  OUTER JOIN FETCH u.car where u.car.model=:carmodel and u.car.series=:carseries ";
         User user = session.createQuery(hql, User.class)
                 .setParameter("carmodel", car.getModel())
                 .setParameter("carseries",car.getSeries())
                 .getSingleResult();
         System.out.println(user.toString());

      }catch (HibernateException e) {
         e.printStackTrace();
      }
   }
}
