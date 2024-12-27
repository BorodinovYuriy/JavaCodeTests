//    Задание 1 - Арі тесты Rest Assured
//    ссылка на API - https://reqres.in/
//    В реализации необходимо использовать request/response specifications

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestsAPI {
//    Кейс 1:
    //Протестировать регистрацию пользователя в системе, необходимо создание 2 тестов:
    //успешная регистрация с валидными данными
    //регистрация с ошибкой из-за отсутствия пароля и проверить,что статус-код 400
    //REGISTER_SUCCESFUL
    @Test
    public void positiveRegistrationCheck() {
        Map<String,String> requestData = new HashMap<>();
        requestData.put("email", "eve.holt@reqres.in");
        requestData.put("password", "pistol");

        RestAssured.given()
                .contentType("application/json")
                .body(requestData)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .log().body()
                .statusCode(200);
    }
    //REGISTER_UNSUCCESFUL
    @Test
    public void negativeRegistrationCheck(){
        Map<String,String> requestData = new HashMap<>();
        requestData.put("email", "eve.holt@reqres.in");

        RestAssured.given()
                .contentType("application/json")
                .body(requestData)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .log().body()
                .statusCode(400);
    }
//    Кейс 2:
    //1. Получить список пользователей страницы
    //2. Убедиться, что email пользователей имеет окончание @reqres.in
    //LIST USERS
    @Test
    public void usersEmailEndCheck(){
        RestAssured.given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().body()
                .body("data.email",not(hasItem(nullValue())))
                .body("data.email", everyItem(endsWith("@reqres.in")))
                .statusCode(200);
    }
//    Кейс 3:
    //Удалить второго пользователя и проверить что статус-код 204
    //DELETE
    @Test
    public void userDeleteCheck(){
        RestAssured.given()
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .statusCode(204);
    }
//    Кейс 4:
    //Обновить информацию о пользователе методом patch и сравнить дату обновления с текущей датой в системе
    //PATCH
    @Test
    public void userPatchDateCheck() {
        Map<String, String> requestData = new HashMap<>();
        requestData.put("name", "morpheus");
        requestData.put("job", "zion resident");

        String updatedAt = RestAssured.given()
                .contentType("application/json")
                .body(requestData)
                .when()
                .patch("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .extract()
                .path("updatedAt");
    // 1. Преобразовать updatedAt в ZonedDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        ZonedDateTime actualDateTime = ZonedDateTime.parse(
                updatedAt,
                formatter.withZone(ZoneId.of("UTC")));
    // 2. Получить текущее время
        ZonedDateTime currentDateTime = ZonedDateTime.now(ZoneId.of("UTC"));
    // 3. Получить разницу
        long diffInSeconds = Math.abs(Duration.between(actualDateTime, currentDateTime).getSeconds());
    // 4. Проверить, что разница не больше 5 секунд
        assertThat(diffInSeconds, lessThanOrEqualTo(5L));


    }















}
