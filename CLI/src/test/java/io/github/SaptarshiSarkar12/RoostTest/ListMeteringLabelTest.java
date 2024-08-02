// ********RoostGPT********
/*
Test generated by RoostGPT for test ra-integration using AI Type Open AI and AI Model gpt-4

ROOST_METHOD_HASH=96c4a9f895
ROOST_METHOD_SIG_HASH=263770cec3

 ########## Scenario ########## 

{
  feature: 'Feature: Metering Labels API',
  background: 'Background:\n    Given the API is available',
  rule: null,
  scenario: { title: 'Scenario: List Metering Label', steps: '', examples: '' }
}

*/

// ********RoostGPT********
package io.github.SaptarshiSarkar12.RoostTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListMeteringLabelTest {

    private String[] testData;
    private Map<String, String> headers = new HashMap<>();

    @BeforeEach
    public void setUp() throws Exception {
        String payload = new String(Files.readAllBytes(Paths.get("src", "test", "java", "io", "github", "SaptarshiSarkar12", "RoostTest", "ListMeteringLabelTest.csv")));
        testData = payload.split("\\^\\|\\^");
        headers.put("Content-Type", "application/json");
    }

    @Test
    public void testListMeteringLabel() {
        for (int i=1; i<testData.length; i++) {
            String[] apiData = testData[i].split("\\^\\|\\^");
            String method = apiData[0];
            String url = apiData[1];
            String reqBody = apiData[3];
            int responseCode = Integer.parseInt(apiData[4]);

            RequestSpecification request = RestAssured.given().baseUri(url).headers(headers).body(reqBody);

            Response response;
            switch (method.toLowerCase()) {
                case "get":
                    response = request.when().get(url);
                    break;
                case "post":
                    response = request.when().post(url);
                    break;
                case "delete":
                    response = request.when().delete(url);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid method type");
            }

            // Validate Response
            assertEquals(responseCode, response.getStatusCode(), "Incorrect response code");
            assertEquals(apiData[5], response.getBody().asString(), "Incorrect response body");
        }
    }
}
