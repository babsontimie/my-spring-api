package com.example.api.controller;

import com.example.api.model.GreetRequest;
import org.junit.jupiter.api.Test;

import org.springframework.http.ResponseEntity;

import java.lang.reflect.Field;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HelloControllerTest {

    private void injectField(Object target, String name, String value) throws Exception {
        Field f = target.getClass().getDeclaredField(name);
        f.setAccessible(true);
        f.set(target, value);
    }

    @Test
    void rootShouldReturnApplicationAndEnvironment() throws Exception {
        HelloController controller = new HelloController();
        injectField(controller, "appEnv", "test");
        injectField(controller, "appName", "my-test-app");

        ResponseEntity<Map<String, String>> resp = controller.root();
        assertNotNull(resp);
        assertEquals(200, resp.getStatusCodeValue());
        Map<String, String> body = resp.getBody();
        assertNotNull(body);
        assertEquals("my-test-app", body.get("application"));
        assertEquals("API is running", body.get("message"));
        assertEquals("test", body.get("environment"));
    }

    @Test
    void helloShouldReturnGreeting() {
        HelloController controller = new HelloController();
        ResponseEntity<Map<String, String>> resp = controller.hello("Alice");
        assertEquals(200, resp.getStatusCodeValue());
        Map<String, String> body = resp.getBody();
        assertNotNull(body);
        assertEquals("Hello, Alice!", body.get("message"));
    }

    @Test
    void greetShouldReturnWelcomeMessage() {
        HelloController controller = new HelloController();
        GreetRequest req = new GreetRequest("Bob");
        ResponseEntity<Map<String, String>> resp = controller.greet(req);
        assertEquals(200, resp.getStatusCodeValue());
        Map<String, String> body = resp.getBody();
        assertNotNull(body);
        assertEquals("Welcome, Bob!", body.get("message"));
    }
}
