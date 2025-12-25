package Day1;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import static io.restassured.RestAssured.*;
import Utils.ConfigLoader;

public class BaseTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = ConfigLoader.get("baseURI");

        RestAssured.requestSpecification = given()
                .header("x-api-key", ConfigLoader.get("apiKey"))
                .contentType("application/json");
    }
}
