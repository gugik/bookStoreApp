package org.api;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.json.JSONObject;

import java.util.List;

import static io.restassured.RestAssured.given;

public class BookStoreApi {

    public String createUser(String userName, String password) {
        JSONObject requestBody = new JSONObject()
                .put("userName", userName)
                .put("password", password);
        ValidatableResponse response = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .and()
                .body(requestBody.toString())
                .when()
                .post("Account/v1/User")
                .then().
                assertThat()
                .statusCode(HttpStatus.SC_CREATED);
        return response.extract().body().as(User.class).getUserID();
    }

    public String generateToken(String userName, String password) {
        JSONObject requestBody = new JSONObject()
                .put("userName", userName)
                .put("password", password);
        ValidatableResponse response = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .and()
                .body(requestBody.toString())
                .when()
                .post("Account/v1/GenerateToken")
                .then().
                assertThat()
                .statusCode(HttpStatus.SC_OK);
        return response.extract().body().as(GenerateTokenResponseBody.class).getToken();
    }

    public List<Book> getBooks() {
        ValidatableResponse response = given()
                .contentType(ContentType.JSON)
                .get("BookStore/v1/Books")
                .then().
                assertThat()
                .statusCode(HttpStatus.SC_OK);
        return response.extract().body().as(Books.class).getBooks();
    }

    public List<Isbn> postBooks(String userId, List<Isbn> isbns, String token) {
        JSONObject requestBody = new JSONObject()
                .put("userId", userId)
                .put("collectionOfIsbns", isbns);
        ValidatableResponse response = given()
                .header("authorization", "Bearer " + token)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .and()
                .body(requestBody.toString())
                .when()
                .post("BookStore/v1/Books")
                .then().
                assertThat()
                .statusCode(HttpStatus.SC_CREATED);
        return response.extract().body().as(Isbns.class).getBooks();
    }

    public User getUser(String userId, String token) {
        ValidatableResponse response = given()
                .header("authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .get("Account/v1/User/" + userId)
                .then().
                assertThat()
                .statusCode(HttpStatus.SC_OK);
        return response.extract().body().as(User.class);
    }
}
