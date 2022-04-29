package com.flowers.users.service;

import com.flowers.users.exception.UserListNotFound;
import com.flowers.users.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Pankaj
 * Date: 4/29/22
 * Time: 12:37 PM
 * To change this template use File | Settings | File Templates.
 */
@Service
public class UserService implements IuserService{
    private static final Logger LOGGER= LoggerFactory.getLogger(UserService.class);

    @Value(("${json.url}"))
    private String url;

    public List<User> getUserListFromJson() throws Exception{
        JSONArray jsonArray = readJsonFromUrl(url);
        Optional<JSONArray> arrayOptional = Optional.ofNullable(jsonArray);
        List<User> userList = new ArrayList<>();
        if(arrayOptional.isPresent()){
            jsonArray.forEach(o -> userList.add(fromJsonObject((JSONObject)o)));
        }else {
            throw new UserListNotFound();
        }
       LOGGER.info("input array from the URL : {}",jsonArray.toString());
       return userList;
    }

    //Converting JSON object to POJO class
    public User fromJsonObject(JSONObject jsonObject){
        User user = new User();
        user.setBody(jsonObject.getString("body"));
        user.setTitle(jsonObject.getString("title"));
        user.setId(jsonObject.getLong("id"));
        user.setUserId(jsonObject.getLong("userId"));
        return  user;
    }

    public String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public JSONArray  readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONArray json = new JSONArray (jsonText);
            return json;
        } finally {
            is.close();
        }
    }
}
