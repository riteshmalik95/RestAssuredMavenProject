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
      int id;
    @Test(priority = 1)
    void getUsers() {


        given()
                .when()
                .get("/api/users?page=2")
                .then()
                .statusCode(200)
                .body("page", equalTo(2))
                .body("data.size()", greaterThan(0))
                .log().all();
    }
    @Test(priority =2 )
    void createUser(){
        //Creating the user means we have to pass some user information
        Map data = new HashMap<>();
        //This particular data object we have to send along with the post request.
        //We have generated the data by using HashMap.
        data.put("name", "ritesh");
        data.put("job", "automation");

        id=given()

//                .contentType(ContentType.JSON)
                .contentType("application/json")
                .body(data)
                .when()
                .post("api/users")
                .jsonPath().getInt("id");

//                .then()
//                .statusCode(201)
//                .log().all();
        //from the response we have to capture the id and using the id then we can easily update or delete
        //the user which we have already created.............
    }
    @Test(priority = 3,dependsOnMethods = {"createUser"})
    void UpdateUser(){

        Map data = new HashMap<>();

        data.put("name", "Amar");
        data.put("job", "Teacher");

        given()

//                .contentType(ContentType.JSON)
                .contentType("application/json")
                .body(data)
                .when()
                .put("api/users/"+id)


                .then()
                .statusCode(200)
                .log().all();

    }
    @Test(priority = 4)
    void deleteUser(){
        given()
                .when()
                .delete("api/users/"+id)
                .then()
                .statusCode(204)
                .log().all();

    }
}

