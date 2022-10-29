package kpn;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class ApplicationController {

    @GetMapping(value = "/info")
    public String info() {
        return "info";
    }

    @GetMapping(value = "/logout")
    public String login() {
        return "logout";
    }
}
