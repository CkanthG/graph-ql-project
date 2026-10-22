package com.sree.graphql;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureGraphQlTester
class GraphQlProjectApplicationTests {

    @Autowired
    private GraphQlTester graphQlTester;

    @Test
    void shouldReturnBookDetailsWithAuthorForBookById() {
        var response = graphQlTester.document("""
                query {
                  bookById(id: "book-1") {
                    id
                    name
                    pageCount
                    author {
                      id
                      firstName
                      lastName
                    }
                  }
                }
                """)
                .execute();

        response.path("bookById.id").entity(String.class).isEqualTo("book-1");
        response.path("bookById.name").entity(String.class).isEqualTo("Effective Java");
        response.path("bookById.pageCount").entity(Integer.class).isEqualTo(416);
        response.path("bookById.author.id").entity(String.class).isEqualTo("author-1");
        response.path("bookById.author.firstName").entity(String.class).isEqualTo("Joshua");
        response.path("bookById.author.lastName").entity(String.class).isEqualTo("Bloch");
    }

    @Test
    void shouldReturnBooksByNameWithAuthorDetails() {
        var response = graphQlTester.document("""
                query {
                  bookByName(name: "Down Under") {
                    id
                    name
                    author {
                      id
                      firstName
                      lastName
                    }
                  }
                }
                """)
                .execute();

        var books = response.path("bookByName").entityList(Map.class).get();

        assertThat(books).hasSize(2);
        assertThat(books)
                .allSatisfy(book -> assertThat((String) book.get("name")).isEqualTo("Down Under"));

        var authorFirstNames = books.stream()
                .map(book -> (Map<String, Object>) book.get("author"))
                .map(author -> (String) author.get("firstName"))
                .toList();

        assertThat(authorFirstNames).containsExactlyInAnyOrder("Bill", "Bill");
        assertThat(authorFirstNames).allMatch(firstName -> firstName.equals("Bill"));
    }
}
