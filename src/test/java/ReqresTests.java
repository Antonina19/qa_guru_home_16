import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class ReqresTests {
    @Test
    void usersCreate() {
        given()
                .contentType(JSON)
                .body("{\n" +
                        "    \"name\": \"user11\",\n" +
                        "    \"job\": \"leader\"\n" +
                        "}")
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("name", is("user11"))
                .body("job", is("leader"));
    }

    @Test
    void usersGet() {
        given()
                .contentType(JSON)
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .body("data.email", is("janet.weaver@reqres.in"))
                .body("data.first_name", is("Janet"));
    }

    @Test
    void usersListGet() {
        given()
                .contentType(JSON)
                .when()
                .get("https://reqres.in/api/users?page=1")
                .then()
                .statusCode(200)
                .body("page", is(1))
                .body("per_page", is(6))
                .body("total", is(12));
    }

    @Test
    void usersListGetFindText() {
        given()
                .contentType(JSON)
                .when()
                .get("https://reqres.in/api/users?page=3")
                .then()
                .statusCode(200)
                .body("support.text", is("To keep ReqRes free, contributions towards server costs are appreciated!"));
    }

    @Test
    void usersDelete() {
        given()
                .when()
                .delete("https://reqres.in/api/users/5")
                .then()
                .statusCode(204);
    }
}
