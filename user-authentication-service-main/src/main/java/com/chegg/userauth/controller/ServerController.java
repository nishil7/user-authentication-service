package com.chegg.userauth.controller;

import com.chegg.userauth.model.ServerUser;
import com.chegg.userauth.service.ServerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/server")
public class ServerController {
    private final ServerService serverService;

    public ServerController(ServerService serverService) {
        this.serverService = serverService;
    }

    @GetMapping("/listofuser")
    List<ServerUser>func(){
        return serverService.getAllUsers();
    }

    @GetMapping("/checkUser/{UserName}/{role}")
    Boolean func2(@PathVariable String UserName, @PathVariable Integer role){
        return serverService.doesUserExists(UserName,role);
    }
}






