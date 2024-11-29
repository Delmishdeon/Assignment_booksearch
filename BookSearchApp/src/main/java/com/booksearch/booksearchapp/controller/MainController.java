package com.booksearch.booksearchapp.controller;

import com.booksearch.booksearchapp.model.Book;
import com.booksearch.booksearchapp.utils.ApiClient;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.List;

public class MainController {

    @FXML
    private AnchorPane mainPane;

    @FXML
    private TextField searchField;

    @FXML
    private Button searchButton;

    @FXML
    private ListView<String> bookListView;

    private List<Book> books;

    @FXML
    private void initialize() {
        // Initialize UI components if needed
    }

    @FXML
    private void searchBooks() {
        String query = searchField.getText().trim();
        if (query.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Search Field Empty", "Please enter a book title to search.");
            return;
        }

        try {
            books = ApiClient.fetchBooks(query);
            if (books.isEmpty()) {
                showAlert(Alert.AlertType.INFORMATION, "No Results", "No books found for the query: " + query);
            } else {
                updateBookList();
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to fetch books. Please try again.");
            e.printStackTrace();
        }
    }

    private void updateBookList() {
        bookListView.getItems().clear();
        for (Book book : books) {
            bookListView.getItems().add(book.getTitle() + " by " + book.getAuthor());
        }

        bookListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                int selectedIndex = bookListView.getSelectionModel().getSelectedIndex();
                if (selectedIndex >= 0) {
                    handleViewDetailsButton(books.get(selectedIndex));
                }
            }
        });
    }

    @FXML
    private void handleViewDetailsButton(Book selectedBook) {
        try {
            // Load the BookDetails.fxml scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/booksearch/booksearchapp/view/BookDetails.fxml"));
            AnchorPane bookDetailsPane = loader.load();

            // Get the controller and pass the selected book
            BookDetailsController detailsController = loader.getController();
            detailsController.setSelectedBook(selectedBook);

            // Create a new scene and set it on the stage
            Scene bookDetailsScene = new Scene(bookDetailsPane);
            Stage stage = (Stage) mainPane.getScene().getWindow();
            stage.setScene(bookDetailsScene);
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load book details. Please try again.");
            e.printStackTrace();
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
