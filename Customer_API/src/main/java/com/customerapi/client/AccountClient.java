package com.customerapi.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "internal-db-api", url = "http://localhost:1010/api/users")
public interface AccountClient {

    @GetMapping("/validate")
    Integer validateUser(@RequestParam String username, @RequestParam String password);
}