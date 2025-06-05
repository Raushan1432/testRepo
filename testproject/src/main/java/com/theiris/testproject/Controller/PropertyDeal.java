package com.theiris.testproject.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/propertydeal")
public class PropertyDeal {
    @GetMapping("/property")
    public String getMessage(){
        return "you can acess this property now";
    }
    @PostMapping("/add")
    public String addProperty(){
        return "user can add the property";

    }
}
