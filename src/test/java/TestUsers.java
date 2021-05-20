import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TestUsers {
    private static String payload = "{\n" +
            "    \"name\": \"morpheus\",\n" +
            "    \"job\": \"leader\"\n" +
            "}";
    @Test
    public void testGetUsers(){
        given().get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200);
    }

    @Test
    public void testPost(){
        Response response = given().contentType(ContentType.JSON).body(payload).post("https://reqres.in/api/users")
                .then().log().all().statusCode(201).extract().response();
        Assertions.assertEquals("morpheus", response.jsonPath().getString("name"));
        Assertions.assertEquals("leader", response.jsonPath().getString("job"));

    }
}
