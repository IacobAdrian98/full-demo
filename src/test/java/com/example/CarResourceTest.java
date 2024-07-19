package com.example;

import com.example.model.Car;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static jakarta.ws.rs.core.HttpHeaders.*;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.Response.Status.CREATED;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class CarResourceTest {
    @Test
    void testGetAllCarEndpoint() {
        List<Car> carList = given()
                .when().get("/vehicles")
                .then()
                .statusCode(200)
                .header(CONTENT_TYPE, equalTo("application/json;charset=UTF-8"))
                .extract()
                .body()
                .as(
                        new TypeRef<List<Car>>() {
                        });
        assertEquals(carList.size(), 4);
        assertEquals(carList.get(0).model, "X5");
        assertEquals(carList.get(1).model, "M3");
        assertEquals(carList.get(2).model, "M2");
        assertEquals(carList.get(3).model, "X3");
    }

    @Test
    void testGetCarByModelAndYearEndpoint() {
        Car car = given()
                .pathParams("model", "X5")
                .pathParams("year", "2024")
                .when().get("/vehicles/{model}-{year}")
                .then()
                .statusCode(200)
                .header(CONTENT_TYPE, equalTo("application/json;charset=UTF-8"))
                .extract().body().as(Car.class);
        assertEquals(car.brand, "BMW");
        assertEquals(car.model, "X5");
        assertEquals(car.yearOfFabrication, 2024);
        assertEquals(car.color, "RED");
    }

    @Test
    @Disabled
    void testPostCarEndpoint() {
        Car car = new Car("BMW", "X5", 2024, "GREEN");

        Response response = given()
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .header(ACCEPT, APPLICATION_JSON)
                .body(car, ObjectMapperType.JSONB)
                .when()
                .post("/vehicles")
                .then()
                .statusCode(CREATED.getStatusCode())
                .extract()
                .response();

        String url = response.jsonPath().getString("url");
        assertEquals(url, "http://localhost:8080/vehicles");
    }

}
