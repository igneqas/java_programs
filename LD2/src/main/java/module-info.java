module com.example.ld {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.persistence;
    requires java.sql;
    requires spring.context;
    requires spring.web;
    requires spring.core;
    requires com.google.gson;

    exports com.example.ld to javafx.graphics;
    opens com.example.ld.fxControllers to javafx.fxml, javafx.base;
    opens com.example.ld.ds to javafx.fxml, org.hibernate.orm.core, java.persistence;
    exports com.example.ld.ds;
    exports com.example.ld.hibernateControllers;
    opens com.example.ld.hibernateControllers to javafx.fxml;
    opens com.example.ld.webControllers to javafx.fxml, org.hibernate.orm.core, java.persistence;
    exports com.example.ld.webControllers;
    exports com.example.ld.fxControllers to javafx.graphics;
}