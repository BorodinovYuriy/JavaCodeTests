import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;

import java.util.HashMap;
import java.util.Map;

public class BaseApiTest {
    private static RequestSpecification requestSpec;
    private static ResponseSpecification responseSpec;

    @BeforeAll
    public static void setup() {
        requestSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri("https://reqres.in/api")
                .build();

        responseSpec = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .build();

    }

    public static RequestSpecification getRequestSpec() {
        return requestSpec;
    }

    public static ResponseSpecification getResponseSpec() {
        return responseSpec;
    }


    protected Map<String,String> createValidRegistrationData() {
        Map<String,String> requestData = new HashMap<>();
        requestData.put("email", "eve.holt@reqres.in");
        requestData.put("password", "pistol");
        return requestData;
    }

    protected Map<String,String> createInvalidRegistrationData() {
        Map<String, String> requestData = new HashMap<>();
        requestData.put("email", "eve.holt@reqres.in");
        return requestData;
    }

    protected Map<String,String> createPatchUserData(){
        Map<String,String> requestData = new HashMap<>();
        requestData.put("name", "morpheus");
        requestData.put("job", "zion resident");
        return requestData;
    }
}
