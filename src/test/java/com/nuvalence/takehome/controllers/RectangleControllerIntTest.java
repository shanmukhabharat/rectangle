package com.nuvalence.takehome.controllers;

import com.nuvalence.takehome.domain.Rectangle;
import com.nuvalence.takehome.models.requests.RectanglesRelationParam;
import com.nuvalence.takehome.models.response.ShapesRelationResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.awt.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;
import static com.nuvalence.takehome.domain.Adjacency.NOT_ADJACENT;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RectangleControllerIntTest {

    @LocalServerPort
    private int randomServerPort;

    private final RestTemplate restTemplate = new RestTemplate();

    @Test
    @DisplayName("Endpoint success check")
    public void test_getRectanglesRelationSuccess() throws URISyntaxException, NullPointerException {
        final String baseUrl = "http://localhost:" + randomServerPort + "/rectangle/relation";
        URI uri = new URI(baseUrl);
        RectanglesRelationParam rectanglesRelationParam = new RectanglesRelationParam();
        Rectangle r1 = new Rectangle(new Point(200, 200), 100, 100);
        Rectangle r2 = new Rectangle(new Point(400, 200), 100, 100);
        rectanglesRelationParam.setR1(r1);
        rectanglesRelationParam.setR2(r2);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<RectanglesRelationParam> request = new HttpEntity<>(rectanglesRelationParam, headers);
        ResponseEntity<ShapesRelationResponse> result = restTemplate.postForEntity(uri, request, ShapesRelationResponse.class);

        //Verify response
        Assertions.assertEquals(200, result.getStatusCodeValue());
        Assertions.assertEquals(NOT_ADJACENT, Objects.requireNonNull(result.getBody()).getAdjacency());
        Assertions.assertEquals(Boolean.FALSE, result.getBody().isIntersects());
        Assertions.assertEquals(Boolean.FALSE, result.getBody().isContains());
        Assertions.assertNull(result.getBody().getIntersectionPoints());
    }

    @Test
    @DisplayName("Endpoint exceptions check")
    public void test_getRectanglesRelation_clientErrors() throws URISyntaxException {
        final String baseUrl = "http://localhost:" + randomServerPort + "/rectangle/relation";
        URI uri = new URI(baseUrl);
        RectanglesRelationParam rectanglesRelationParam = new RectanglesRelationParam();
        Rectangle r1 = new Rectangle(new Point(100, 200), 100, 100);
        rectanglesRelationParam.setR1(r1);
        rectanglesRelationParam.setR2(null);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<RectanglesRelationParam> request = new HttpEntity<>(rectanglesRelationParam, headers);
        try {
            restTemplate.postForEntity(uri, request, ShapesRelationResponse.class);
        } catch (Exception e) {
            //Verify response
            Assertions.assertEquals("422 : \"{\"message\":\"Please change your values for the rectangles.\",\"reason\":\"Either of the rectangles cannot be null for this operation.\"}\"", e.getLocalizedMessage());
        }
    }
}
