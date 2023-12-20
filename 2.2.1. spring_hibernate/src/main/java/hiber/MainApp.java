package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User s = new User("s","Shushik","Serj3@mail.ru");
      User roma = new User("Rome", "Lastname3", "user3@mail.ru");
      User roman  = new User("Roman", "Lastname4", "user4@mail.ru");

      Car car = new Car(s,"Honda",55);
      Car car1 = new Car(roma,"Mers",33);
      Car car2 = new Car(roman,"TAYOTA",70);

      s.setCar(car);
      roma.setCar(car1);
      roman.setCar(car2);

      userService.add(s);
      userService.add(roma);
      userService.add(roman);

      System.out.println(userService.getUserByCar(car));


//
//      List<User> users = userService.listUsers();
//      for (User user : users) {
//         System.out.println("Id = "+user.getId());
//         System.out.println("First Name = "+user.getFirstName());
//         System.out.println("Last Name = "+user.getLastName());
//         System.out.println("Email = "+user.getEmail());
//         System.out.println("Car_Model = "+user.getCar().getModel());
//         System.out.println("Car_Series = "+user.getCar().getSeries());
//         System.out.println();
//      }

      context.close();
   }
}
