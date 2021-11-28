module com.example.ld {
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.java;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.persistence;
    requires java.sql;

    opens com.example.ld to javafx.fxml;
    exports com.example.ld;
    exports com.example.ld.fxControllers;
    opens com.example.ld.fxControllers to javafx.fxml;
    opens com.example.ld.ds to javafx.fxml, org.hibernate.orm.core, java.persistence;
    exports com.example.ld.ds;
    exports com.example.ld.hibernateControllers;
    opens com.example.ld.hibernateControllers to javafx.fxml;
}