package com.example.ld.webControllers;


import com.example.ld.ds.Company;
import com.example.ld.ds.Person;
import com.example.ld.ds.User;
import com.example.ld.hibernateControllers.CourseHibernateController;
import com.example.ld.hibernateControllers.UserHibernateController;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;

@Controller
public class UserWebController {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseSystem");
    UserHibernateController userHibernateController = new UserHibernateController(entityManagerFactory);
    CourseHibernateController courseHibernateController = new CourseHibernateController(entityManagerFactory);

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String checkLogin(@RequestBody String request) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);
        User user = userHibernateController.getAllUsers().stream().filter(u -> u.getUsername().equals(properties.getProperty("username"))).filter(u -> u.getPassword().equals(properties.getProperty("password"))).findFirst().orElse(null);
        if(user == null){
            return "Vartotojo vardas arba slaptazodis ivesti neteisingai";
        }
        else return user.toString();
    }

    @RequestMapping(value = "/user/createPerson", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String createPerson(@RequestBody String request){
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);
        Person person = new Person(properties.getProperty("username"), properties.getProperty("password"),  properties.getProperty("firstName"), properties.getProperty("lastName"), properties.getProperty("email"), properties.getProperty("phoneNumber"));
        userHibernateController.createUser(person);
        return "Vartotojas sukurtas";
    }

    @RequestMapping(value = "/user/createCompany", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String createCompany(@RequestBody String request){
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);
        Company company = new Company(properties.getProperty("username"), properties.getProperty("password"),  properties.getProperty("companyName"), properties.getProperty("representative"));
        userHibernateController.createUser(company);
        return "Vartotojas sukurtas";
    }

    @RequestMapping(value = "/user/updateUser/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String updateUser(@RequestBody String request, @PathVariable(name = "id") int id) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);
        if(properties.getProperty("username") != null){
            return "Negalima keisti vartotojo vardo";
        }
        User user = userHibernateController.getUserById(id);
        if(properties.getProperty("password") != null)
        user.setPassword(properties.getProperty("password"));
        user.setDateModified(LocalDate.now());
        if(user instanceof Person) {
            if(properties.getProperty("firstName") != null)
            ((Person) user).setFirstName(properties.getProperty("firstName"));
            if(properties.getProperty("lastName") != null)
            ((Person) user).setLastName(properties.getProperty("lastName"));
            if(properties.getProperty("email") != null)
            ((Person) user).setEmail(properties.getProperty("email"));
            if(properties.getProperty("phoneNumber") != null)
            ((Person) user).setPhoneNumber(properties.getProperty("phoneNumber"));
            userHibernateController.editUser(user);
            return "Vartotojo duomenys atnaujinti";
        }
        else if(user instanceof Company){
            if(properties.getProperty("companyName") != null)
            ((Company) user).setCompanyName(properties.getProperty("companyName"));
            if(properties.getProperty("representative") != null)
            ((Company) user).setRepresentative(properties.getProperty("representative"));
            userHibernateController.editUser(user);
            return "Vartotojo duomenys atnaujinti";
        }
        return "Nepavyko atnaujinti duomenu";
    }

    @RequestMapping(value = "/user/deleteUser/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String deleteUserById(@PathVariable(name = "id") int id) {
        //Gson gson = new Gson();
        //User user = getUser(id);
        User user = userHibernateController.getUserById(id);
        user.setMyAdminCourses(null);
        user.setMyCourses(null);
        user.setMyFolders(null);
        userHibernateController.editUser(user);
        user = userHibernateController.getUserById(id);
        if(user instanceof Person){
            ((Person) user).getMyCreatedCourses().forEach(c -> {
                c.setCreator(null);
                courseHibernateController.editCourse(c);
            });
        }
        userHibernateController.editUser(user);
        userHibernateController.removeUser(id);
        user = userHibernateController.getUserById(id);
        if(user != null){
            return "Vartotojo istrinti nepavyko";
        }else {
            return "Vartotojas istrintas";
        }
    }

    @RequestMapping(value = "/user/getAllUsers", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getAllUsers() {
        //Gson gson = new Gson();
        return userHibernateController.getAllUsers().toString();//gson.toJson(userHibControl.getAllUsers());
    }

    @RequestMapping(value = "/user/getUser/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getUserById(@PathVariable(name = "id") int id){
        return userHibernateController.getUserById(id).toString();
    }

    /*private User getUser(int id){
        return userHibernateController.getAllUsers().stream().filter(u -> u.getId() == id).findFirst().orElse(null);
    }*/


}
