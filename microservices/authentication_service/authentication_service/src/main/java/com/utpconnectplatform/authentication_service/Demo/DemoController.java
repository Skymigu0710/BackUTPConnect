package com.utpconnectplatform.authentication_service.Demo;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.apache.el.util.Validation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class DemoController {

    @PostMapping(value = "demo")
    public String welcome()
    {
        return "Welcome form secure endpoint";
    }
}
