// ********RoostGPT********
/*
Test generated by RoostGPT for test ra-integration using AI Type Open AI and AI Model gpt-4

ROOST_METHOD_HASH=39b9703bd1
ROOST_METHOD_SIG_HASH=5215613e02

 ########## Scenario ########## 

{
  feature: 'Feature: Metering Labels API',
  background: 'Background:\n    Given the API is available',
  rule: null,
  scenario: {
    title: 'Scenario: List Metering Labels',
    steps: 'When I send a GET request to "/v2.0/metering/metering-labels"\n' +
      'Then the response status code should be 200\n' +
      'And the response should be in JSON\n' +
      'And the response should contain the following metering labels:\n' +
      '      | id                                   | tenant_id                           | description            | name   |\n' +
      '      | a6700594-5b7a-4105-8bfe-723b346ce866 | 45345b0ee1ea477fac0f541b2cb79cd4   | label1 description     | label1 |\n' +
      '      | e131d186-b02d-4c0b-83d5-0c0725c4f812 | 45345b0ee1ea477fac0f541b2cb79cd4   | label2 description     | label2 |',
    examples: ''
  }
}

*/

// ********RoostGPT********
package io.github.SaptarshiSarkar12.RoostTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListMeteringLabelsTest {

    @BeforeEach
    public void setup() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/test/java/io/github/SaptarshiSarkar12/RoostTest/ListMeteringLabelsTest.csv"));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] payload = line.split("\\^\\|\\^");
            if (payload.length > 0 && !payload[0].equals("METHOD")) {
                String method = payload[0];
                String url = payload[1];
                String headers = payload[2];
                String requestBody = payload[3];
                int responseCode = Integer.parseInt(payload[4]);
                String responseBody = payload[5];

                RestAssured.baseURI = url;
                RequestSpecification request = RestAssured.given();
                request.header("Content-Type", "application/json");
                Response response = request.get();

                assertEquals(responseCode, response.getStatusCode());
                assertEquals(responseBody, response.getBody().asString());
            }
        }
        reader.close();
    }

    @Test
    public void testListMeteringLabels() {
        // Test for the listing of metering labels
        Response response = given().when().get("/v2.0/metering/metering-labels").then().extract().response();
        assertEquals(200, response.getStatusCode());

        String[] expectedIds = {"a6700594-5b7a-4105-8bfe-723b346ce866", "e131d186-b02d-4c0b-83d5-0c0725c4f812"};
        String[] expectedTenantIds = {"45345b0ee1ea477fac0f541b2cb79cd4", "45345b0ee1ea477fac0f541b2cb79cd4"};
        String[] expectedDescriptions = {"label1 description", "label2 description"};
        String[] expectedNames = {"label1", "label2"};

        String[] actualIds = response.path("metering labels.id").toString().replace("[","").replace("]","").split(",");
        String[] actualTenantIds = response.path("metering labels.tenant_id").toString().replace("[","").replace("]","").split(",");
        String[] actualDescriptions = response.path("metering labels.description").toString().replace("[","").replace("]","").split(",");
        String[] actualNames = response.path("metering labels.name").toString().replace("[","").replace("]","").split(",");

        assertEquals(Arrays.asList(expectedIds), Arrays.asList(actualIds));
        assertEquals(Arrays.asList(expectedTenantIds), Arrays.asList(actualTenantIds));
        assertEquals(Arrays.asList(expectedDescriptions), Arrays.asList(actualDescriptions));
        assertEquals(Arrays.asList(expectedNames), Arrays.asList(actualNames));
    }
}
