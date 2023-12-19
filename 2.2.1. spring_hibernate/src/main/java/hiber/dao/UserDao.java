package hiber.dao;

import hiber.model.Car;
import hiber.model.User;

import java.util.List;
import java.util.Objects;

public interface UserDao {
   void add(User user);
   List<User> listUsers();

   void getUserByCar(Car car);

}
