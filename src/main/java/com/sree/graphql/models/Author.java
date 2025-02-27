package com.sree.graphql.models;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public record Author (String id, String firstName, String lastName) {

    private static List<Author> authors = Arrays.asList(
            new Author("author-1", "Joshua", "Bloch"),
            new Author("author-2", "Douglas", "Adams"),
            new Author("author-3", "Bill", "Bryson"),
            new Author("author-4", "Bill", "Gates")
    );

    public static Author getById(String id) {
        return authors.stream()
                .filter(author -> author.id().equals(id))
                .findFirst()
                .orElse(null);
    }

    public static List<Author> getByName(String id) {
        return authors.stream()
                .filter(author -> author.id.equals(id))
                .toList();
    }
}
