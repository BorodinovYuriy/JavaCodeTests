//    Задание 1 - Арі тесты Rest Assured
//    ссылка на API - https://reqres.in/
//    Кейс 1:
//      Протестировать регистрацию пользователя в системе, необходимо создание 2 тестов:
//      успешная регистрация с валидными данными
//      регистрация с ошибкой из-за отсутствия пароля и проверить,что статус-код
//      в ответе 400
//    Кейс 2:
//      1.Получить список пользователей страницы
//      2.Убедиться, что email пользователей имеет окончание @reqres.in
//    Кейс 3:
//      Удалить второго пользователя и проверить что статус-код 204
//    Кейс 4:
//      Обновить информацию о пользователе методом patch и сравнить дату обновления с
//      текущей датой в системе
//    В реализации необходимо использовать request/response specifications

import org.junit.jupiter.api.Test;
import io.restassured.RestAssured;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.notNullValue;

public class TestsAPI {

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

    @Test
    public void usersEmailEndCheck(){

    }

    @Test
    public void userDeleteCheck(){

    }

    @Test
    public void userPatchDateCheck(){

    }


















}
