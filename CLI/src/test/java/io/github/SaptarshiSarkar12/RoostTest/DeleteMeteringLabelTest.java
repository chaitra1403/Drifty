// ********RoostGPT********
/*
Test generated by RoostGPT for test ra-integration using AI Type Open AI and AI Model gpt-4

ROOST_METHOD_HASH=76b9c9cffe
ROOST_METHOD_SIG_HASH=09158f32e1

 ########## Scenario ########## 

{
  feature: 'Feature: Metering Labels API',
  background: 'Background:\n    Given the API is available',
  rule: null,
  scenario: { title: 'Scenario: Delete Metering Label', steps: '', examples: '' }
}

*/

// ********RoostGPT********
package io.github.SaptarshiSarkar12.RoostTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DeleteMeteringLabelTest {
    private static final String PAYLOAD_FILE_PATH = Paths.get("src", "test", "java", "io", "github", "SaptarshiSarkar12", "RoostTest", "DeleteMeteringLabelTest.csv").toString();
    private static final String DELIMITER = "\\^\\|\\^";
    private HashMap<String, String> headers;
    private String payload;
    private String URL;
    private int responseCode;

    @BeforeClass
    public void setup() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(PAYLOAD_FILE_PATH));
        String line = reader.readLine(); // Skip the header line
        while ((line = reader.readLine()) != null) {
            if (line.isEmpty()) continue;
            String[] data = line.split(DELIMITER);
            URL = data[1];
            payload = data[3];
            responseCode = Integer.parseInt(data[4]);
            // Assuming headers are in JSON format, parse them into a HashMap
            headers = new Gson().fromJson(data[2], new TypeToken<HashMap<String, String>>() {}.getType());
        }
        reader.close();
    }

    @Test
    public void deleteMeteringLabelTest() {
        // Given the API is available
        RestAssured.baseURI = "http://localhost:8080";

        // When a DELETE request is sent
        Response response = given().headers(headers).contentType(ContentType.JSON).and().body(payload)
                .when().delete(URL)
                .then().assertThat().statusCode(responseCode).and().contentType(ContentType.JSON).and()
                .body(equalTo("{}")).extract().response();

        // Then the response should be 204 with no response body
        Assert.assertEquals(response.statusCode(), responseCode);
        Assert.assertEquals(response.asString(), "{}");
    }
}
