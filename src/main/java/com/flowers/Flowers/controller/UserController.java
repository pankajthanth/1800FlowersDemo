package com.flowers.Flowers.controller;

import com.flowers.Flowers.model.User;
import com.flowers.Flowers.utility.response.CountResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * User: Pankaj
 * Date: 4/26/22
 * Time: 12:36 PM
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Value("${modified.user.details}")
    private String updatedDetails;

    @GetMapping(value="/count")
    public ResponseEntity<CountResponse> getUserCount(@RequestBody List<User> users){

        Long count = users.stream().map(User::getUserId).distinct().count();

        return new ResponseEntity<>(new CountResponse(count),HttpStatus.OK) ;
    }

    @PutMapping(value="/updateUser/{itemNumber}")
    public ResponseEntity<List<User>> updateUserDetails(@RequestBody List<User> users, @PathVariable Long itemNumber){

        List<User> userList = users.stream().map(u -> {
            if(u.getId() == itemNumber){
                u.setBody(updatedDetails);
                u.setTitle(updatedDetails);
                return u;
            }
            return u;
        }).collect(Collectors.toList());

        return new ResponseEntity<>(userList,HttpStatus.OK) ;
    }
}

