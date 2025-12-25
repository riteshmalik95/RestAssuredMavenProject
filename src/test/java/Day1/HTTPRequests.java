package Day1;
//Whenever you create Rest Assured Test Cases then by default it will follow the
//BDD(Behavior-Driven Development) style
//Gherkins - Keywords
//What we will pass-----
//It will contain all prerequisites
//given()-content type,set cookies,add auth,add param,set headers info etc......
//It will contain all Requests
//when()-get,post,put,delete.......
//It will contain Validation Part
//then()-validate status code,extract response,extract headers cookies and response body etc.....

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

    public class HTTPRequests extends BaseTest {

    @Test(priority = 1)
    void getUsers() {


        given()
                .when()
                .get("/api/users?page=2")
                .then()
                .statusCode(200)
                .body("page", equalTo(2))
                .log().all();
    }
    @Test
    void createUser(){
        //Creating the user means we have to pass some user information
        Map data = new HashMap<>();
        //This particular data object we have to send along with the post request.
        //We have generated the data by using HashMap.
        data.put("name", "ritesh");
        data.put("job", "automation");

        given()

                .contentType(ContentType.JSON)
                .body(data)
                .when()
                .post("api/users")
                .then()
                .statusCode(201)
                .log().all();
    }
}

