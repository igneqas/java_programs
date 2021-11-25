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
import java.util.Properties;

@Controller
public class FileWebController {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseSystem");
    CourseHibernateController courseHibernateController = new CourseHibernateController(entityManagerFactory);
    UserHibernateController userHibernateController = new UserHibernateController(entityManagerFactory);
    FolderHibernateController folderHibernateController = new FolderHibernateController(entityManagerFactory);
    FileHibernateController fileHibernateController = new FileHibernateController(entityManagerFactory);


    @RequestMapping(value = "/file/createFile", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String createFile(@RequestBody String request){
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);
        if(properties.getProperty("parentFolderId") != null && properties.getProperty("creatorId") != null) {
            Folder parentFolder = folderHibernateController.getFolderById(Integer.parseInt(properties.getProperty("parentFolderId")));
            File file = new File(properties.getProperty("fileName"), getFilePath(parentFolder) + "/", userHibernateController.getUserById(Integer.parseInt(properties.getProperty("creatorId"))), parentFolder);
            fileHibernateController.createFile(file);
        }
        return "Failas sukurtas";
    }


    @RequestMapping(value = "/file/updateFile/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String updateFile(@RequestBody String request, @PathVariable(name = "id") int id) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);
        File file = fileHibernateController.getFileById(id);
        if(properties.getProperty("fileName") != null)
            file.setFileName(properties.getProperty("fileName"));
        fileHibernateController.editFile(file);
        return "Failas atnaujintas";
    }

    @RequestMapping(value = "/file/deleteFile/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String deleteFileById(@PathVariable(name = "id") int id) {
        fullyRemoveFile(id);
        File file = fileHibernateController.getFileById(id);
        if(file == null){
            return "Failas istrintas";
        }else {
            return "Nepavyko istrinti";
        }
    }

    @RequestMapping(value = "/file/getAllFiles", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getAllFiles() {
        //Gson gson = new Gson();
        return fileHibernateController.getAllFiles().toString();//gson.toJson(fileHibControl.getAllFiles());
    }

    @RequestMapping(value = "/file/getFile/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getFileById(@PathVariable(name = "id") int id){
        return fileHibernateController.getFileById(id).toString();
    }

    private String getFilePath(Folder folder){
        if(folder.getParentCourse() != null){
            return folder.getFolderName();
        }else {
            return getFilePath(folder.getParentFolder()) + "/" + folder.getFolderName();
        }
    }

    private void fullyRemoveFile(int fileId){
        File file = fileHibernateController.getFileById(fileId);
        file.setParentFolder(null);
        file.setCreator(null);
        fileHibernateController.editFile(file);
        fileHibernateController.removeFile(file.getId());
    }
}
