package com.example.ld.fxControllers;

import com.example.ld.StartGui;
import com.example.ld.ds.*;
import com.example.ld.hibernateControllers.CourseHibernateController;
import com.example.ld.hibernateControllers.FileHibernateController;
import com.example.ld.hibernateControllers.FolderHibernateController;
import com.example.ld.hibernateControllers.UserHibernateController;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainCoursesWindow implements Initializable {
    @FXML
    public ListView courseList;
    @FXML
    public TreeView<String> courseFolders;
    @FXML
    public Button enrollB;
    @FXML
    public ListView folderFiles;
    @FXML
    public Button newFileB;
    @FXML
    public Button createCourseB;
    @FXML
    public Button quitCourseB;
    @FXML
    public ContextMenu folderContextMenu;
    @FXML
    public MenuItem courseUsersM;
    @FXML
    public MenuItem deleteCourseM;
    @FXML
    public MenuItem courseInfoM;
    @FXML
    public Menu adminM;

    private User user;
    private boolean showAllCourses = true;

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseSystem");
    UserHibernateController userHibernateController = new UserHibernateController(entityManagerFactory);
    CourseHibernateController courseHibernateController = new CourseHibernateController(entityManagerFactory);
    FolderHibernateController folderHibernateController = new FolderHibernateController(entityManagerFactory);
    FileHibernateController fileHibernateController = new FileHibernateController(entityManagerFactory);

    public void setCourseData(User user) {
        this.user = user;
        newFileB.setDisable(true);
        enrollB.setVisible(false);
        quitCourseB.setVisible(false);
        courseList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        courseFolders.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        fillCourseList();
        if(user.getUserRole() == Role.REGULAR_USER) {
            createCourseB.setVisible(false);
        }

        if(user.getUserRole() != Role.SYSTEM_ADMIN) {
            adminM.setVisible(false);
        }

        courseUsersM.setVisible(false);
        deleteCourseM.setVisible(false);
        courseInfoM.setVisible(false);
    }

    private void fillCourseList() {
        user = userHibernateController.getUserById(user.getId());
        courseList.getItems().clear();
        courseFolders.setRoot(null);
        folderFiles.getItems().clear();
        if (user != null) {
            List<Course> courses;
            if(showAllCourses) {
                courses = courseHibernateController.getAllCourses();
            } else {
                courses = user.getMyCourses();
            }
            courses.forEach(c -> courseList.getItems().add(c.getId() + " : " + c.getCourseName()));
        }
    }

    public void showMyCourses(Event event) {
        showAllCourses = !showAllCourses;
        fillCourseList();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void openNewCourseForm(ActionEvent actionEvent) throws IOException {
        if (user instanceof Person) {
            FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("new-course-form.fxml"));
            Parent root = fxmlLoader.load();
            NewCourseForm newCourseForm = fxmlLoader.getController();
            newCourseForm.setCourseData(user);
            Scene scene = new Scene(root);
            Stage stage = (Stage) courseList.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } else {
            LoginWindow.alertMessage("You are not an individual");
        }
    }

    public void openEditCourseWindow(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("edit-course-window.fxml"));
        Parent root = fxmlLoader.load();
        EditCourseWindow editCourseWindow = fxmlLoader.getController();
        editCourseWindow.setCourseData(courseHibernateController.getCourseById(selectCourse()), user);
        Scene scene = new Scene(root);
        Stage stage = (Stage) courseList.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void deleteCourse(ActionEvent actionEvent) {
        Course course = courseHibernateController.getCourseById(selectCourse());
        course.getCourseFolders().forEach(f -> {
            fullyRemoveEditors(f.getId());
            fullyRemoveFolder(f.getId());
        });
        course = courseHibernateController.getCourseById(selectCourse());

        for (User u : course.getParticipants()) {
            u.getMyAdminCourses().remove(course);
            u.getMyCourses().remove(course);
            userHibernateController.editUser(u);
        }
        course.setCreator(null);
        courseHibernateController.editCourse(course);
        courseHibernateController.removeCourse(selectCourse());
        user = userHibernateController.getUserById(user.getId());
        fillCourseList();
    }

    public void deleteUser(ActionEvent actionEvent) throws IOException {
        user.setMyAdminCourses(null);
        user.setMyCourses(null);
        user.setMyFolders(null);
        if (user instanceof Person) {
            ((Person) user).getMyCreatedCourses().forEach(c -> c.setCreator(null));
        }
        userHibernateController.editUser(user);
        userHibernateController.removeUser(user.getId());
        LoginWindow.alertMessage("Account was deleted");
        toLoginWindow();
    }

    public void editUser(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("edit-user-window.fxml"));
        Parent root = fxmlLoader.load();
        EditUserWindow editUserWindow = fxmlLoader.getController();
        editUserWindow.setUserData(user);
        Scene scene = new Scene(root);
        Stage stage = (Stage) courseList.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void returnToLogin(ActionEvent actionEvent) throws IOException {
        user = null;
        toLoginWindow();
    }

    private void toLoginWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("login-window.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) courseList.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void openCourseUsersWindow(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("course-users-window.fxml"));
        Parent root = fxmlLoader.load();
        CourseUsersWindow courseUsersWindow = fxmlLoader.getController();
        courseUsersWindow.setCourseParameters(courseHibernateController.getCourseById(selectCourse()));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    private int selectCourse() {
        String courseInfo = courseList.getSelectionModel().getSelectedItem().toString();
        return Integer.parseInt(courseInfo.split(":")[0].trim());
    }

    private int selectFolder() {
        try {
            TreeItem<String> selectedItem = courseFolders.getSelectionModel().getSelectedItem();
            return Integer.parseInt(selectedItem.getValue().split(":")[0].trim());
        } catch (Exception e) {
            return 0;
        }
    }

    public void addUserToCourse(ActionEvent actionEvent) {
        Course course = courseHibernateController.getCourseById(selectCourse());
        user = userHibernateController.getUserById(user.getId());
        course.getParticipants().add(user);
        user.getMyCourses().add(course);
        userHibernateController.editUser(user);
        enrollB.setVisible(false);
        quitCourseB.setVisible(true);
    }

    public void setEnrollButton(MouseEvent mouseEvent) {
        courseInfoM.setVisible(true);
        courseFolders.setRoot(null);
        folderFiles.getItems().clear();
        if(user.getUserRole() == Role.SYSTEM_ADMIN) {
            courseInfoM.setVisible(true);
            courseUsersM.setVisible(true);
            deleteCourseM.setVisible(true);
            populateFolder();
            return;
        }

        enrollB.setVisible(true);
        quitCourseB.setVisible(false);
        for (User p : courseHibernateController.getCourseById(selectCourse()).getParticipants()) {
            if (p.getId() == user.getId()) {
                enrollB.setVisible(false);
                quitCourseB.setVisible(true);
                populateFolder();
                folderFiles.getItems().clear();
                newFileB.setDisable(true);
                break;
            }
        }

        if(courseHibernateController.getCourseById(selectCourse()).getCreator().getId() == user.getId()) {
            deleteCourseM.setVisible(true);
            courseUsersM.setVisible(true);
        }

        for (User p : courseHibernateController.getCourseById(selectCourse()).getAdmins()) {
            if (p.getId() == user.getId() || user.getUserRole() == Role.SYSTEM_ADMIN) {
                courseFolders.removeEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, Event::consume);
                folderFiles.removeEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, Event::consume);
                return;
            }
        }

        courseFolders.addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, Event::consume);
        folderFiles.addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, Event::consume);
        courseUsersM.setVisible(false);
        deleteCourseM.setVisible(false);
    }

    private void populateFolder() {
        courseFolders.setRoot(new TreeItem<String>("Course folders:"));
        courseHibernateController.getCourseById(selectCourse()).getCourseFolders().forEach(folder -> addTreeItem(folder, courseFolders.getRoot()));
    }

    private void addTreeItem(Folder folder, TreeItem parentFolder) {
        TreeItem<String> treeItem = new TreeItem<>(folder.getId() + " : " + folder.getFolderName());
        //TreeItem<Folder> treeItem = new TreeItem<>(folder);
        parentFolder.getChildren().add(treeItem);
        folder.getSubFolders().forEach(sub -> addTreeItem(sub, treeItem));
    }

    public void openAboutAlert(ActionEvent actionEvent) {
        LoginWindow.alertMessage("This is a course management system");
    }

    public void openEditFolderWindow(ActionEvent actionEvent) throws IOException {
        Folder folder = folderHibernateController.getFolderById(selectFolder());
        FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("edit-folder-window.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        EditFolderWindow editFolderWindow = fxmlLoader.getController();
        editFolderWindow.setFolderData(folder);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        populateFolder();
    }

    public void createFolder(ActionEvent actionEvent) {
        TreeItem<String> selectedItem = courseFolders.getSelectionModel().getSelectedItem();
        Folder tempFolder = folderHibernateController.getFolderById(selectFolder());
        Course tempCourse = courseHibernateController.getCourseById(selectCourse());
        //Folder folder = null;
        if (!selectedItem.getValue().equals("Course folders:")) {
            Folder folder = new Folder("New folder", tempFolder, tempFolder.getEditors());
            tempFolder.getEditors().forEach(u -> {
                u.getMyFolders().add(folder);
                userHibernateController.editUser(u);
            });
            tempFolder.getSubFolders().add(folder);
            folderHibernateController.editFolder(tempFolder);
        } else {
            Folder folder = new Folder("New folder", tempCourse, tempCourse.getAdmins());
            tempCourse.getAdmins().forEach(u -> {
                u.getMyFolders().add(folder);
                userHibernateController.editUser(u);
            });
            tempCourse.getCourseFolders().add(folder);
            courseHibernateController.editCourse(tempCourse);
        }
        populateFolder();
        folderFiles.getItems().clear();
        newFileB.setDisable(true);
    }

    public void removeFolder(ActionEvent actionEvent) {
        fullyRemoveEditors(selectFolder());
        fullyRemoveFolder(selectFolder());
        populateFolder();
        folderFiles.getItems().clear();
    }

    private void fullyRemoveFolder(int folderId) {
        Folder folder = null;
        folder = folderHibernateController.getFolderById(folderId);
        try {
            folder.getFiles().forEach(f -> fullyRemoveFile(f.getId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        folder = folderHibernateController.getFolderById(folderId);
        try {
            folder.getSubFolders().forEach(x -> fullyRemoveFolder(x.getId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        folder.setSubFolders(null);
        folder.setParentFolder(null);
        folder.setParentCourse(null);
        folderHibernateController.editFolder(folder);
        folderHibernateController.removeFolder(folder.getId());
    }

    private void fullyRemoveFile(int fileId) {
        File file = fileHibernateController.getFileById(fileId);
        file.setParentFolder(null);
        file.setCreator(null);
        fileHibernateController.editFile(file);
        fileHibernateController.removeFile(file.getId());
    }

    private void fullyRemoveEditors(int folderId) {
        try {
            Folder folder = folderHibernateController.getFolderById(folderId);
            folder.getEditors().forEach(u -> {
                u.getMyFolders().remove(folder);
                userHibernateController.editUser(u);
            });
            folder.getSubFolders().forEach(x -> fullyRemoveEditors(x.getId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fillFiles() {
        if(selectFolder() != 0) {
            try {
                folderFiles.getItems().clear();
                List<File> files = folderHibernateController.getFolderById(selectFolder()).getFiles();
                files.forEach(f -> folderFiles.getItems().add(f.getId() + " : " + f.getFileName()));
                folderFiles.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            } catch (NullPointerException e) {
            }
            for (User p : courseHibernateController.getCourseById(selectCourse()).getAdmins()) {
                if (p.getId() == user.getId() || user.getUserRole() == Role.SYSTEM_ADMIN) {
                    newFileB.setDisable(false);
                    return;
                }
            }
        }
        newFileB.setDisable(true);
    }

    public void openFiles(MouseEvent mouseEvent) {
        fillFiles();
        //newFileB.setDisable(false);
    }

    private int selectFile() {
        String fileInfo = folderFiles.getSelectionModel().getSelectedItem().toString();
        return Integer.parseInt(fileInfo.split(":")[0].trim());
    }

    public void createFile(ActionEvent actionEvent) {
        Folder parentFolder = folderHibernateController.getFolderById(selectFolder());
        String path = getFilePath(parentFolder) + "/";
        File file = new File("New file", path, user, parentFolder);
        fileHibernateController.createFile(file);
        fillFiles();
    }

    private String getFilePath(Folder folder) {
        if (folder.getParentCourse() != null) {
            return "";
        } else {
            return getFilePath(folder.getParentFolder()) + "/" + folder.getFolderName();
        }
    }

    public void openEditFileWindow(ActionEvent actionEvent) throws IOException {
        File file = fileHibernateController.getFileById(selectFile());
        FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("edit-file-window.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        EditFileWindow editFileWindow = fxmlLoader.getController();
        editFileWindow.setFileData(file);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        fillFiles();
    }

    public void removeFile(ActionEvent actionEvent) {
        fullyRemoveFile(selectFile());
        fillFiles();
    }

    public static boolean illegalCharactersUsed(String text) {
        if (text.contains("/") || text.contains("\\") || text.contains(":") || text.contains("*") || text.contains("?") || text.contains("\"") || text.contains("<") || text.contains(">") || text.contains("|")) {
            return true;
        } else {
            return false;
        }
    }

    public void exitFromCourse(ActionEvent actionEvent) {
        enrollB.setVisible(true);
        quitCourseB.setVisible(false);
        courseFolders.setRoot(null);
        Course course = courseHibernateController.getCourseById(selectCourse());
        user = course.getParticipants().stream().filter(u -> u.getId() == this.user.getId()).findFirst().orElse(null);
        course.getCourseFolders().forEach(x -> fullyRemoveAdmin(x.getId(), user.getId()));
        user.getMyCourses().remove(course);
        user.getMyAdminCourses().remove(course);
        userHibernateController.editUser(user);
        courseHibernateController.editCourse(course);
        fillCourseList();
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

    public void openSystemUsersWindow(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("system-users-window.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
}