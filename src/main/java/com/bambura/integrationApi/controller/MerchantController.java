package com.bambura.integrationApi.controller;

import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MerchantController {

   @PostMapping
    public HttpResponse checkUser(HttpRequest httpRequest){
        return null;
    }
}
