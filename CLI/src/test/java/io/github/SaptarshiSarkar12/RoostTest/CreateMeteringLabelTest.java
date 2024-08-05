// ********RoostGPT********
/*
Test generated by RoostGPT for test ra-integration using AI Type Open AI and AI Model gpt-4

ROOST_METHOD_HASH=283395715f
ROOST_METHOD_SIG_HASH=9393d39a4c

 ########## Scenario ########## 

{
  feature: 'Feature: Metering Labels API',
  background: 'Background:\n    Given the API is available',
  rule: null,
  scenario: { title: 'Scenario: Create Metering Label', steps: '', examples: '' }
}

*/

// ********RoostGPT********
package io.github.SaptarshiSarkar12.RoostTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateMeteringLabelTest {

    private String basePath;
    private String fileName = "CreateMeteringLabelTest.csv";
    private String method;
    private String url;
    private Map<String, String> headers;
    private JSONObject requestBody;
    private int responseCode;
    private String responseBody;

    @BeforeEach
    public void setUp() throws IOException, ParseException {
        Path path = FileSystems.getDefault().getPath("src", "test", "java", "io", "github", "SaptarshiSarkar12", "RoostTest", fileName);
        BufferedReader reader = new BufferedReader(new FileReader(path.toString()));
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.isEmpty()) {
                continue;
            }
            String[] parts = line.split("\\^\\|\\^");
            if (parts[0].equalsIgnoreCase("METHOD")) {
                continue;
            }
            method = parts[0];
            url = parts[1];
            headers = new HashMap<>();
            if (!parts[2].equals("{}")) {
                JSONParser parser = new JSONParser();
                JSONObject jsonHeaders = (JSONObject) parser.parse(parts[2]);
                for (Object key : jsonHeaders.keySet()) {
                    headers.put((String) key, (String) jsonHeaders.get(key));
                }
            }
            if (!parts[3].equals("{}")) {
                JSONParser parser = new JSONParser();
                requestBody = (JSONObject) parser.parse(parts[3]);
            }
            responseCode = Integer.parseInt(parts[4]);
            responseBody = parts[5];
        }
        reader.close();
    }

    @Test
    public void testCreateMeteringLabel() {
        RestAssured.basePath = basePath;
        Response response = RestAssured
                .given()
                .headers(headers)
                .body(requestBody)
                .when()
                .post(url)
                .then()
                .extract()
                .response();

        assertEquals(responseCode, response.getStatusCode());
        assertEquals(responseBody, response.getBody().asString());
    }
}
