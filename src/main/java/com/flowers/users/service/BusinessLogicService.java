package com.flowers.users.service;

import com.flowers.users.exception.UserUpdateDetailsNotFound;
import com.flowers.users.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * User: Pankaj
 * Date: 5/2/22
 * Time: 11:51 AM
 * To change this template use File | Settings | File Templates.
 */
@Service
public class BusinessLogicService implements IBusinessLogicService {

    @Autowired
    private IuserService service;

    public Long getUserCountFromUserList() throws Exception{
        List<User> users = service.getUserListFromJson();
        return users.stream().map(User::getUserId).distinct().count();
    }

    public List<User> getUpdateUserDetailsFromList(Long id, String details) throws Exception{
        Optional<Long> longOptional = Optional.ofNullable(id);
        Long userID = longOptional.orElseThrow(Exception::new);

        Optional<String> stringOptional = Optional.ofNullable(details);
        if (!stringOptional.isPresent())throw new UserUpdateDetailsNotFound();

        List<User> users = service.getUserListFromJson();
        //Check if ID is present in the list or not
        if(!users.stream().anyMatch(u-> u.getId()==userID)) throw new Exception();

        return users.stream().map(u -> {
            if(u.getId() == id){
                u.setBody(stringOptional.get());
                u.setTitle(stringOptional.get());
                return u;
            }
            return u;
        }).collect(Collectors.toList()) ;
    }
}
