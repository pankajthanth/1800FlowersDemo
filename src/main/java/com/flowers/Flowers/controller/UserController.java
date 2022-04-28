package com.flowers.Flowers.controller;

import com.flowers.Flowers.model.User;
import com.flowers.Flowers.utility.response.CountResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
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

    @Value(("${json.url}"))
    private String url;

    //Get endpoint to get unique user count
    @GetMapping(value="/count")
    public ResponseEntity<CountResponse> getUserCount() throws Exception {
        List<User> users = getUserListFromJson();
        Long count = users.stream().map(User::getUserId).distinct().count();
        return new ResponseEntity<>(new CountResponse(count),HttpStatus.OK) ;
    }

    //Update endpoint based on id on which update will perform
    @PutMapping(value="/updateUser/{id}")
    public ResponseEntity<List<User>> updateUserDetails( @PathVariable Long id) throws Exception{
        List<User> users = getUserListFromJson();
        List<User> userList = users.stream().map(u -> {
            if(u.getId() == id){
                u.setBody(updatedDetails);
                u.setTitle(updatedDetails);
                return u;
            }
            return u;
        }).collect(Collectors.toList());

        return new ResponseEntity<>(userList,HttpStatus.OK) ;
    }

    //Getting List of Users from given URL
    public List<User> getUserListFromJson()throws Exception{
        JSONArray jsonArray = readJsonFromUrl(url);
        List<User> userList = new ArrayList<>();
        if(jsonArray != null){
            for (int i=0;i<jsonArray.length();i++){
                userList.add(fromJsonObject(jsonArray.getJSONObject(i)));
            }
        }
        return userList;
    }

    //Converting JSON object to POJO class
    public User fromJsonObject(JSONObject jsonObject) throws Exception{
        User user = new User();
        user.setBody(jsonObject.getString("body"));
        user.setTitle(jsonObject.getString("title"));
        user.setId(jsonObject.getLong("id"));
        user.setUserId(jsonObject.getLong("userId"));
        return  user;
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONArray  readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONArray  json = new JSONArray (jsonText);
            return json;
        } finally {
            is.close();
        }
    }
}

