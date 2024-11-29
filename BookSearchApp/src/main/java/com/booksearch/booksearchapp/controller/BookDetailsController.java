package com.booksearch.booksearchapp.controller;

import com.booksearch.booksearchapp.model.Book;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BookDetailsController {

    @FXML
    private Label titleLabel;

    @FXML
    private Label authorLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private ImageView bookImageView;

    public void setSelectedBook(Book book) {
        if (book != null) {
            titleLabel.setText(book.getTitle());
            authorLabel.setText(book.getAuthor());
            descriptionLabel.setText(book.getDescription());
            bookImageView.setImage(new Image(book.getImageUrl(), true));
        }
    }
}
