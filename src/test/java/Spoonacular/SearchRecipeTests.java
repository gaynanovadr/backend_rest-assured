package Spoonacular;

import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class SearchRecipeTests extends AbstractTest {
    @Test
    void getSimpleRecipeTest() {
        given()
                .queryParam("apiKey", getApikey())
                .queryParam("query", "Pasta")
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .statusCode(200);
    }

    @Test
    void getSearchCuisineTest() {
        given()
                .queryParam("apiKey", getApikey())
                .queryParam("cuisine", "italian")
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .statusCode(200);
    }

    @Test
    void postCuisineTitleSearchTest() {
        given()
                .queryParam("apiKey", getApikey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Thai Sausage Salad")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .statusCode(200);
    }

    @Test
    void getResponseData() {
        Response response = given()
                .queryParam("apiKey", getApikey())
                .queryParam("cuisine", "italian")
                .when()
                .get(getBaseUrl() + "recipes/complexSearch");

        Headers allHeaders = response.getHeaders();
        System.out.println("Content-Encoding: " + response.getHeader("Content-Encoding"));
        System.out.println("StatusLine: " + response.getStatusLine());
        System.out.println("Code: " + response.getStatusCode());

        String cuisine = given()
                .queryParam("apiKey", getApikey())
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .path("cuisine");
        System.out.println("cuisine: " + cuisine);

        response = given()
                .queryParam("apiKey", getApikey())
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .then().extract().response();

        String confidence = given()
                .queryParam("apiKey", getApikey())
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .then().extract()
                .jsonPath()
                .get("confidence")
                .toString();

        System.out.println("confidence: " + confidence);


    }
}