package com.example.ld.webControllers;

import com.example.ld.ds.*;
import com.example.ld.ds.Course;
import com.example.ld.hibernateControllers.CourseHibernateController;
import com.example.ld.hibernateControllers.FileHibernateController;
import com.example.ld.hibernateControllers.FolderHibernateController;
import com.example.ld.hibernateControllers.UserHibernateController;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Controller
public class CourseWebController {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseSystem");
    CourseHibernateController courseHibernateController = new CourseHibernateController(entityManagerFactory);
    UserHibernateController userHibernateController = new UserHibernateController(entityManagerFactory);
    FolderHibernateController folderHibernateController = new FolderHibernateController(entityManagerFactory);
    FileHibernateController fileHibernateController = new FileHibernateController(entityManagerFactory);

    @RequestMapping(value = "/course/createCourse", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String createCourse(@RequestBody String request){
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);
        if(userHibernateController.getUserById(Integer.parseInt(properties.getProperty("creatorId"))) instanceof Company){
            return "Kursus gali kurti tik asmuo";
        }
        List<User> participants = new ArrayList<>();
        participants.add(userHibernateController.getUserById(Integer.parseInt(properties.getProperty("creatorId"))));
        List<User> admins = new ArrayList<>();
        admins.add(userHibernateController.getUserById(Integer.parseInt(properties.getProperty("creatorId"))));
        Course course = new Course(properties.getProperty("courseName"), properties.getProperty("description"),  participants, admins, (Person) userHibernateController.getUserById(Integer.parseInt(properties.getProperty("creatorId"))));
        courseHibernateController.createCourse(course);
        User user = userHibernateController.getUserById(Integer.parseInt(properties.getProperty("creatorId")));
        user.getMyCourses().add(course);
        user.getMyAdminCourses().add(course);
        userHibernateController.editUser(user);
        return "Kursas sukurtas";
    }


    @RequestMapping(value = "/course/updateCourse/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String updateCourse(@RequestBody String request, @PathVariable(name = "id") int id) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);
        Course course = courseHibernateController.getCourseById(id);
        if(properties.getProperty("courseName") != null)
            course.setCourseName(properties.getProperty("courseName"));
        if(properties.getProperty("description") != null)
                course.setDescription(properties.getProperty("description"));
        courseHibernateController.editCourse(course);
        return "Kurso informacija atnaujinta";
    }

    @RequestMapping(value = "/course/deleteCourse/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String deleteCourseById(@PathVariable(name = "id") int id) {
        //Gson gson = new Gson();
        Course course = courseHibernateController.getCourseById(id);
        course.getCourseFolders().forEach(f -> {
            fullyRemoveEditors(f.getId());
            fullyRemoveFolder(f.getId());
        });
        course = courseHibernateController.getCourseById(id);

        for(User u : course.getParticipants()){
            u.getMyAdminCourses().remove(course);
            u.getMyCourses().remove(course);
            userHibernateController.editUser(u);
        }
        course.setCreator(null);
        courseHibernateController.editCourse(course);
        courseHibernateController.removeCourse(id);
        course = courseHibernateController.getCourseById(id);
        if(course == null){
            return "Kursas istrintas";
        }else {
            return "Nepavyko istrinti";
        }
    }

    @RequestMapping(value = "/course/getAllCourses", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getAllCourses() {
        //Gson gson = new Gson();
        return courseHibernateController.getAllCourses().toString();//gson.toJson(courseHibControl.getAllCourses());
    }

    @RequestMapping(value = "/course/getCourse/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getCourseById(@PathVariable(name = "id") int id){
        return courseHibernateController.getCourseById(id).toString();
    }

    /*private User getUser(int id){
        return userHibernateController.getAllUsers().stream().filter(u -> u.getId() == id).findFirst().orElse(null);
    }*/

    @RequestMapping(value = "/course/addAdminToCourse/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String addAdmin(@RequestBody String request, @PathVariable(name = "id") int id) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);
        Course course = courseHibernateController.getCourseById(id);
        User user = userHibernateController.getUserById(Integer.parseInt(properties.getProperty("userId")));
        user.getMyCourses().add(course);
        user.getMyAdminCourses().add(course);
        userHibernateController.editUser(user);
        courseHibernateController.editCourse(course);
        return "Administratorius pridetas";
    }

    @RequestMapping(value = "/course/removeAdminFromCourse/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String removeAdmin(@RequestBody String request, @PathVariable(name = "id") int id) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);
        Course course = courseHibernateController.getCourseById(id);
        //User user = userHibernateController.getUserById(Integer.parseInt(properties.getProperty("userId")));
        User user = course.getParticipants().stream().filter(u -> u.getId() == Integer.parseInt(properties.getProperty("userId"))).findFirst().orElse(null);
        course.getCourseFolders().forEach(x -> fullyRemoveAdmin(x.getId(), Integer.parseInt(properties.getProperty("userId"))));
        user.getMyCourses().remove(course);
        user.getMyAdminCourses().remove(course);
        //return user.getMyCourses().toString();
        userHibernateController.editUser(user);
        courseHibernateController.editCourse(course);
        return "Administratorius pasalintas";
    }

    private void fullyRemoveAdmin(int folderId, int userId){
        try{
            Folder folder = folderHibernateController.getFolderById(folderId);
            folder.getSubFolders().forEach(x -> fullyRemoveAdmin(x.getId(), userId));
            User user = userHibernateController.getUserById(userId);
            user.getMyFolders().remove(user.getMyFolders().stream().filter(f -> f.getId() == folderId).findFirst().orElse(null));
            userHibernateController.editUser(user);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/course/addParticipantToCourse/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String addParticipant(@RequestBody String request, @PathVariable(name = "id") int id) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);
        Course course = courseHibernateController.getCourseById(id);
        User user = userHibernateController.getUserById(Integer.parseInt(properties.getProperty("userId")));
        user.getMyCourses().add(course);
        userHibernateController.editUser(user);
        courseHibernateController.editCourse(course);
        return "Dalyvis pridetas";
    }

    @RequestMapping(value = "/course/removeParticipantFromCourse/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String removeParticipant(@RequestBody String request, @PathVariable(name = "id") int id) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);
        Course course = courseHibernateController.getCourseById(id);
        //User user = userHibernateController.getUserById(Integer.parseInt(properties.getProperty("userId")));
        User user = course.getParticipants().stream().filter(u -> u.getId() == Integer.parseInt(properties.getProperty("userId"))).findFirst().orElse(null);
        user.getMyCourses().remove(course);
        userHibernateController.editUser(user);
        courseHibernateController.editCourse(course);
        return "Dalyvis pasalintas";
    }

    private void fullyRemoveFolder(int folderId){
        Folder folder = null;
        folder = folderHibernateController.getFolderById(folderId);
        try{
            folder.getFiles().forEach(f -> fullyRemoveFile(f.getId()));
        }catch(Exception e){
            e.printStackTrace();
        }
        folder = folderHibernateController.getFolderById(folderId);
        try{
            folder.getSubFolders().forEach(x -> fullyRemoveFolder(x.getId()));
        }catch (Exception e){
            e.printStackTrace();
        }
        folder.setSubFolders(null);
        folder.setParentFolder(null);
        folder.setParentCourse(null);
        folderHibernateController.editFolder(folder);
        folderHibernateController.removeFolder(folder.getId());
    }

    private void fullyRemoveFile(int fileId){
        File file = fileHibernateController.getFileById(fileId);
        file.setParentFolder(null);
        file.setCreator(null);
        fileHibernateController.editFile(file);
        fileHibernateController.removeFile(file.getId());
    }

    private void fullyRemoveEditors(int folderId){
        try{
            Folder folder = folderHibernateController.getFolderById(folderId);
            folder.getEditors().forEach(u -> {
                u.getMyFolders().remove(folder);
                userHibernateController.editUser(u);
            });
            folder.getSubFolders().forEach(x -> fullyRemoveEditors(x.getId()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
