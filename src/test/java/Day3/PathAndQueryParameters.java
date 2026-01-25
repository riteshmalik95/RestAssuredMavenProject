package Day3;

import Day1.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

public class PathAndQueryParameters extends BaseTest {
    private static final Logger log = LoggerFactory.getLogger(PathAndQueryParameters.class);

//https://reqres.in/api/users?page=2&id=5

    @Test(priority = 1)
    void testQueryAndPathParameters(){
        given()

                .pathParams("mypath","users")//path parameters-routing the parameter where it should go
                .queryParam("page",2) //query parameters-filtering the data
                .queryParam("id",5)// query parameters


                .when()
                        .get("https://reqres.in/api/{mypath}")//
                .then()
                .statusCode(200)
                .log().all();


    }
}
