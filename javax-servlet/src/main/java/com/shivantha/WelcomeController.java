package com.shivantha;

import java.util.Map;

@MyController
public class WelcomeController {
    @MyPath(path = "/welcome")
    public String welcome(Map<String, Object> model) {
        model.put("title", "Welcome");
        return "welcome";
    }

    @MyPath(path = "/sayHello")
    public String sayHello(Map<String, Object> model) {
        model.put("title", "Hello World");
        return "welcome";
    }
}
