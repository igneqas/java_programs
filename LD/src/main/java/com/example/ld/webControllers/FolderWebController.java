package com.example.ld.webControllers;

import com.example.ld.ds.Course;
import com.example.ld.ds.File;
import com.example.ld.ds.Folder;
import com.example.ld.ds.User;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Controller
public class FolderWebController {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseSystem");
    CourseHibernateController courseHibernateController = new CourseHibernateController(entityManagerFactory);
    UserHibernateController userHibernateController = new UserHibernateController(entityManagerFactory);
    FolderHibernateController folderHibernateController = new FolderHibernateController(entityManagerFactory);
    FileHibernateController fileHibernateController = new FileHibernateController(entityManagerFactory);

    @RequestMapping(value = "/folder/createFolder", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String createFolder(@RequestBody String request){
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);
        Course parentCourse = null;
        Folder parentFolder = null;

        if(properties.getProperty("parentCourseId") != null) {
            parentCourse = courseHibernateController.getCourseById(Integer.parseInt(properties.getProperty("parentCourseId")));
            Folder folder = new Folder(properties.getProperty("folderName"), parentCourse, parentCourse.getAdmins());
            parentCourse.getAdmins().forEach(u -> {
                u.getMyFolders().add(folder);
                userHibernateController.editUser(u);
            });
            parentCourse.getCourseFolders().add(folder);
            courseHibernateController.editCourse(parentCourse);
        }
        else if(properties.getProperty("parentFolderId") != null) {
            parentFolder = folderHibernateController.getFolderById(Integer.parseInt(properties.getProperty("parentFolderId")));
            Folder folder = new Folder(properties.getProperty("folderName"), parentFolder, parentFolder.getEditors());
            parentFolder.getEditors().forEach(u -> {
                u.getMyFolders().add(folder);
                userHibernateController.editUser(u);
            });
            parentFolder.getSubFolders().add(folder);
            folderHibernateController.editFolder(parentFolder);
        }
        return "Aplankas sukurtas";
    }


    @RequestMapping(value = "/folder/updateFolder/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String updateFolder(@RequestBody String request, @PathVariable(name = "id") int id) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);
        Folder folder = folderHibernateController.getFolderById(id);
        if(properties.getProperty("folderName") != null)
            folder.setFolderName(properties.getProperty("folderName"));
        folder.setDateModified(LocalDate.now());
        folderHibernateController.editFolder(folder);
        return "Aplankas atnaujintas";
    }

    @RequestMapping(value = "/folder/deleteFolder/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String deleteFolderById(@PathVariable(name = "id") int id) {
        //Gson gson = new Gson();
        fullyRemoveEditors(id);
        fullyRemoveFolder(id);
        Folder folder = folderHibernateController.getFolderById(id);
        if(folder == null){
            return "Aplankas istrintas";
        }
        return "Nepavyko istrinti";
    }

    @RequestMapping(value = "/folder/getAllFolders", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getAllFolders() {
        //Gson gson = new Gson();
        return folderHibernateController.getAllFolders().toString();//gson.toJson(folderHibControl.getAllFolders());
    }

    @RequestMapping(value = "/folder/getFolder/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getFolderById(@PathVariable(name = "id") int id){
        return folderHibernateController.getFolderById(id).toString();
    }

    @RequestMapping(value = "/folder/getFoldersInCourse/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getFoldersInCourse(@PathVariable(name = "id") int id) {
        List<Folder> allFolders = folderHibernateController.getAllFolders();
        List<Folder> foldersInCourse = new ArrayList<>();

            allFolders.forEach(f -> {
                    if ((f.getParentCourse() != null && f.getParentCourse().getId() == id) || (foldersInCourse.stream().filter(x -> f.getParentFolder() != null && x.getId() == f.getParentFolder().getId()).findFirst().orElse(null) != null))
                        foldersInCourse.add(f);

            });
        return foldersInCourse.toString();
    }

    @RequestMapping(value = "/folder/addAdminToFolder/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String addAdmin(@RequestBody String request, @PathVariable(name = "id") int id) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);
        fullyAddAdmin(id, Integer.parseInt(properties.getProperty("userId")));
        return "Pavyko";
    }

    private void fullyAddAdmin(int folderId, int userId){
        try{
            Folder folder = folderHibernateController.getFolderById(folderId);
            folder.getSubFolders().forEach(x -> fullyAddAdmin(x.getId(), userId));
            User user = userHibernateController.getUserById(userId);
            user.getMyFolders().add(folder);
            userHibernateController.editUser(user);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/folder/removeAdminFromFolder/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String removeAdmin(@RequestBody String request, @PathVariable(name = "id") int id) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);
        fullyRemoveAdmin(id, Integer.parseInt(properties.getProperty("userId")));
        return "Pavyko";
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
