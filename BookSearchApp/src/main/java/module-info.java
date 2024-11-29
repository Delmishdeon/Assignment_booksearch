module com.booksearch.booksearchapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.a.bootstrapfx.core;

    opens com.booksearch.booksearchapp to javafx.fxml;
    exports com.booksearch.booksearchapp;
    requires com.google.gson;
    exports com.booksearch.booksearchapp.controller;
    opens com.booksearch.booksearchapp.controller to javafx.fxml;

}