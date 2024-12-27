//    Задание 1 - Арі тесты Rest Assured
//    ссылка на API - https://reqres.in/
//    В реализации необходимо использовать request/response specifications

import io.restassured.RestAssured;
import org.example.helpers.utill.TimeDiff;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestsAPI extends BaseApiTest {

    //    Кейс 1:
    //Протестировать регистрацию пользователя в системе, необходимо создание 2 тестов:
    //успешная регистрация с валидными данными
    //регистрация с ошибкой из-за отсутствия пароля и проверить,что статус-код 400
    //REGISTER_SUCCESFUL
    @Test
    public void positiveRegistrationCheck() {
        RestAssured.given()
                .spec(getRequestSpec())
                .body(createValidRegistrationData())
                .when()
                .post("/register")
                .then()
                .spec(getResponseSpec())
                .log().body()
                .statusCode(200);
    }
    //REGISTER_UNSUCCESFUL
    @Test
    public void negativeRegistrationCheck(){
        RestAssured.given()
                .spec(getRequestSpec())
                .body(createInvalidRegistrationData())
                .when()
                .post("/register")
                .then()
                .spec(getResponseSpec())
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
                .spec(getRequestSpec())
                .when()
                .get("/users?page=2")
                .then()
                .spec(getResponseSpec())
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
                .spec(getRequestSpec())
                .when()
                .delete("/users/2")
                .then()
                .statusCode(204);
    }
    //    Кейс 4:
    //Обновить информацию о пользователе методом patch и сравнить дату обновления с текущей датой в системе
    //PATCH
    @Test
    public void userPatchDateCheck() {
        String updatedAt = RestAssured.given()
                .spec(getRequestSpec())
                .body(createPatchUserData())
                .when()
                .patch("/users/2")
                .then()
                .spec(getResponseSpec())
                .statusCode(200)
                .extract()
                .path("updatedAt");

        System.out.println("Задержка ответа при UPDATE: "+TimeDiff.parseAndReturnDiff(updatedAt)+" sec");
        assertThat(TimeDiff.parseAndReturnDiff(updatedAt), lessThanOrEqualTo(5L));
    }
}















