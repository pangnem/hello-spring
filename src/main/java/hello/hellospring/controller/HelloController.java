package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-mvc";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello" + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public HelloResponse helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello(name);

        return hello.response();
    }

    static class Hello {
        private final String name;

        public Hello(String name) {
            this.name = name;
        }

        public HelloResponse response() {
            return new HelloResponse(this.name);
        }
    }

    static class HelloResponse {
        private final String name;

        public HelloResponse(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
