package com.example.proxy;

import com.example.dto.UserAccountDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(name = "auth-server")
public interface AuthProxy {

    @GetMapping("/auth/profile")
    ResponseEntity<UserAccountDto> getUserProfile(@RequestHeader(value = "Authorization") String authorizationHeader);

}
