package com.booksearch.booksearchapp.utils;

import com.booksearch.booksearchapp.model.Book;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ApiClient {
    private static final String API_URL = "https://openlibrary.org/search.json?title=";

    public static List<Book> fetchBooks(String query) throws Exception {
        String formattedQuery = query.replace(" ", "+");
        URL url = new URL(API_URL + formattedQuery);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        InputStreamReader reader = new InputStreamReader(conn.getInputStream());
        JsonObject jsonResponse = JsonParser.parseReader(reader).getAsJsonObject();

        JsonArray docs = jsonResponse.getAsJsonArray("docs");
        List<Book> books = new ArrayList<>();
        if (docs != null) {
            for (int i = 0; i < docs.size(); i++) {
                JsonObject bookJson = docs.get(i).getAsJsonObject();

                Book book = new Book();
                book.setTitle(bookJson.has("title") ? bookJson.get("title").getAsString() : "Unknown Title");
                book.setAuthor(bookJson.has("author_name") ? bookJson.getAsJsonArray("author_name").get(0).getAsString() : "Unknown Author");
                book.setDescription(bookJson.has("first_publish_year") ? "First published in " + bookJson.get("first_publish_year").getAsString() : "No publication year available.");
                book.setImageUrl("https://covers.openlibrary.org/b/id/" + (bookJson.has("cover_i") ? bookJson.get("cover_i").getAsInt() : 0) + "-L.jpg");

                books.add(book);
            }
        }
        return books;
    }
}
