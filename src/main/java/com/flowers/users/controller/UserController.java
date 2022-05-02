package com.flowers.users.controller;

import com.flowers.users.exception.UserUpdateDetailsNotFound;
import com.flowers.users.model.User;
import com.flowers.users.service.IBusinessLogicService;
import com.flowers.users.utility.response.CountResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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

    private static final Logger LOGGER= LoggerFactory.getLogger(UserController.class);

    @Value("${modified.user.details}")
    private String updatedDetails;

    @Value(("${json.url}"))
    private String url;

    @Autowired
    private IBusinessLogicService service;

    //Get endpoint to get unique user count      /// add layer , need to implemets to java8
    @GetMapping(value="/count")
    public ResponseEntity<CountResponse> getUserCount() throws Exception {
        LOGGER.info("Getting Unique user count from User list");
        Long count = service.getUserCountFromUserList();
        LOGGER.info("unique user count from the list is : {}",count);
        return new ResponseEntity<>(new CountResponse(count),HttpStatus.OK) ;
    }

    //Update endpoint based on id on which update will perform
    @PutMapping(value="/updateUser/{id}/updatedDetails/{details}")
    public ResponseEntity<List<User>> updateUserDetails( @PathVariable Long id,@PathVariable String details) throws Exception{
        LOGGER.info("Update call to update the user's details with ID is : {}",id);
        List<User> users = service.getUpdateUserDetailsFromList(id,details);

        LOGGER.info("User details updated successfully, status is : {}",HttpStatus.OK);
        return new ResponseEntity<>(users,HttpStatus.OK) ;
    }
}

