package com.flowers.users.service;

import com.flowers.users.model.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Pankaj
 * Date: 5/2/22
 * Time: 11:51 AM
 * To change this template use File | Settings | File Templates.
 */
public interface IBusinessLogicService {
    public Long getUserCountFromUserList() throws Exception;

    public List<User> getUpdateUserDetailsFromList(Long id, String details) throws Exception;
}
