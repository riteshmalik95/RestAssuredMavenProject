package Day2;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
/*Different ways to create Post Request Body
    1)Post Request Body by using HasMap
    2)Post Request Body creation by using org.JSON library
    3)Post Request Body creation by using POJO Class
    4)Post Request Body by using external JSON file
     */
public class WaysToCreatePostRequestBody {

    //1)Post Request Body by using HasMap(for small set of data it is preferable to use)
    //http://localhost:3000/students/  ---> Dummy API through cmd.
    // store ID globally for delete
    String studentId;

//    @Test(priority = 1)
    void testPostUsingHashMap() {

        HashMap<String, Object> data = new HashMap<>();
        data.put("name", "virat");
        data.put("location", "RCB");
        data.put("phone", "12345645");

        String courseArr[] = {"C", "C++"};
        data.put("courses", courseArr);

        studentId =
                given()
                        .contentType(ContentType.JSON)
                        .body(data)
                        .when()
                        .post("http://localhost:3000/students")
                        .then()
                        .log().all()
                        .statusCode(201)
                        .body("name", equalTo("virat"))
                        .body("location", equalTo("RCB"))
                        .body("phone", equalTo("12345645"))
                        .body("courses[0]", equalTo("C"))
                        .body("courses[1]", equalTo("C++"))
                        .header("Content-Type", containsString("application/json"))
                        .extract()
                        .jsonPath()
                        .getString("id");

        System.out.println("Created student ID: " + studentId);
    }

//    @Test(priority = 2)
    void testDelete() {

        given()
                .when()
                .delete("http://localhost:3000/students/" + studentId)
                .then()
                .log().all()
                .statusCode(anyOf(is(200), is(204)));
    }

    //2)Post Request Body creation by using org.JSON library
//    @Test(priority = 1)
    void testPostUsingJsonLibrary(){

        JSONObject data=new JSONObject();
        data.put("name", "virat");
        data.put("location", "RCB");
        data.put("phone", "12345645");
        String courseArr[] = {"C", "C++"};
        data.put("courses", courseArr);
        studentId =
                given()
                        .contentType(ContentType.JSON)
                        //By using Json Library we can not directly pass the data into body...
                        .body(data.toString())
                        .when()
                        .post("http://localhost:3000/students")
                        .then()
                        .log().all()
                        .statusCode(201)
                        .body("name", equalTo("virat"))
                        .body("location", equalTo("RCB"))
                        .body("phone", equalTo("12345645"))
                        .body("courses[0]", equalTo("C"))
                        .body("courses[1]", equalTo("C++"))
                        .header("Content-Type", containsString("application/json"))
                        .extract()
                        .jsonPath()
                        .getString("id");

        System.out.println("Created student ID: " + studentId);
    }
//    @Test(priority = 2)
    void testDeleteOrg() {

        given()
                .when()
                .delete("http://localhost:3000/students/" + studentId)
                .then()
                .log().all()
                .statusCode(anyOf(is(200), is(204)));
    }

    //3)Post Request Body creation by using POJO Class
//    @Test(priority = 1)
    void testPostUsingPOJOClass(){

        Pojo_PostRequest data=new Pojo_PostRequest();
        data.setName("virat");
        data.setLocation("RCB");
        data.setPhone("12345645");
        String courseArr[] = {"C", "C++"};
        data.setCourses(courseArr);
        studentId =
                given()
                        .contentType(ContentType.JSON)
                        //By using Json Library we can not directly pass the data into body...
                        .body(data)
                        .when()
                        .post("http://localhost:3000/students")
                        .then()
                        .log().all()
                        .statusCode(201)
                        .body("name", equalTo("virat"))
                        .body("location", equalTo("RCB"))
                        .body("phone", equalTo("12345645"))
                        .body("courses[0]", equalTo("C"))
                        .body("courses[1]", equalTo("C++"))
                        .header("Content-Type", containsString("application/json"))
                        .extract()
                        .jsonPath()
                        .getString("id");

        System.out.println("Created student ID: " + studentId);
    }
//    @Test(priority = 2)
    void testDeletePOJO() {

        given()
                .when()
                .delete("http://localhost:3000/students/" + studentId)
                .then()
                .log().all()
                .statusCode(anyOf(is(200), is(204)));
    }

    //3)Post Request Body creation by using external JSON file
    @Test(priority = 1)
    void testPostUsingExternalJson() throws FileNotFoundException {

        File f=new File(".\\body.json");
         FileReader fr=new FileReader(f);
         JSONTokener jt=new JSONTokener(fr);
         JSONObject data=new JSONObject(jt);

        studentId =
                given()
                        .contentType(ContentType.JSON)
                        //By using Json Library we can not directly pass the data into body...
                        .body(data.toString())
                        .when()
                        .post("http://localhost:3000/students")
                        .then()
                        .log().all()
                        .statusCode(201)
                        .body("name", equalTo("virat"))
                        .body("location", equalTo("RCB"))
                        .body("phone", equalTo("12345645"))
                        .body("courses[0]", equalTo("C"))
                        .body("courses[1]", equalTo("C++"))
                        .header("Content-Type", containsString("application/json"))
                        .extract()
                        .jsonPath()
                        .getString("id");

        System.out.println("Created student ID: " + studentId);
    }
        @Test(priority = 2)
    void testDeleteExternalJSON() {

        given()
                .when()
                .delete("http://localhost:3000/students/" + studentId)
                .then()
                .log().all()
                .statusCode(anyOf(is(200), is(204)));
    }

}
