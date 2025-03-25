package com.bytedance.douyinclouddemo.controller;

import com.bytedance.douyinclouddemo.model.JsonResponse;
import com.bytedance.douyinclouddemo.model.SetNameRequest;
import com.bytedance.douyinclouddemo.service.HelloService;
import com.bytedance.douyinclouddemo.service.impl.HelloServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
public class HelloController {

    @Value("${cloud.env}")
    private String envMark;

    @GetMapping("/api/hello")
    public JsonResponse hello(@RequestParam(value = "target", defaultValue = "mongodb") String target) {
        JsonResponse response = new JsonResponse();
        try {
            response.success("env:" + envMark + " hello");
        } catch (Exception e) {
            response.failure("unknown error");
        }
        return response;
    }

    // 新增的路由，使用java.net包发送GET请求
    @GetMapping("/api/fetch_url")
    public JsonResponse fetchUrl(@RequestParam String url) {
        JsonResponse response = new JsonResponse();
        try {
            StringBuilder result = new StringBuilder();
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                result.append(inputLine);
            }
            in.close();

            response.success(result.toString());
        } catch (IOException e) {
            response.failure("Failed to fetch URL: " + e.getMessage());
        }
        return response;
    }
}
