package com.flowers.users.service;

import com.flowers.users.model.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Pankaj
 * Date: 4/29/22
 * Time: 12:37 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IuserService {
    List<User> getUserListFromJson() throws Exception;
}
