package com.flowers.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flowers.users.controller.UserController;
import com.flowers.users.exception.UserExceptionController;
import com.flowers.users.model.User;
import com.flowers.users.service.IBusinessLogicService;
import com.flowers.users.service.IuserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.CoreMatchers.is;

import java.util.ArrayList;
import java.util.List;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Created with IntelliJ IDEA.
 * User: Pankaj
 * Date: 4/26/22
 * Time: 1:52 PM
 * To change this template use File | Settings | File Templates.
 */

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @InjectMocks
    private UserController userController;

    @Mock
    private User user;

    @Mock
    private IuserService service;

    @Mock
    private IBusinessLogicService logicService;

    @Mock
    private UserExceptionController userExceptionController;

    @Autowired
    private MockMvc mockMvc;

    private List<User> userList;

    private static ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        this.userList = new ArrayList<>();
        this.userList.add(new User(1L, 1L, "sunt aut facere repellat provident occaecati excepturi optio reprehenderit","quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto"));
        this.userList.add(new User(1L, 2L, "sunt aut facere repellat provident occaecati excepturi optio reprehenderit","quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto"));
        this.userList.add(new User(2L, 3L, "sunt aut facere repellat provident occaecati excepturi optio reprehenderit","quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto"));
        this.userList.add(new User(3L, 4L, "sunt aut facere repellat provident occaecati excepturi optio reprehenderit","quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto"));
        this.userList.add(new User(3L, 5L, "sunt aut facere repellat provident occaecati excepturi optio reprehenderit","quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto"));
        this.userList.add(new User(4L, 6L, "sunt aut facere repellat provident occaecati excepturi optio reprehenderit","quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto"));

    }

    @Test
    public void testGetUserCount() throws Exception{
        String json = mapper.writeValueAsString(userList);
        this.mockMvc.perform(get("/user/count").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andExpect(jsonPath("$.count",is(10)));
    }

    /*@Test
    public void testGetUserCountWithoutBody() throws Exception{
        this.mockMvc.perform(get("/user/count").contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isBadRequest());
    }*/

    /*@Test
    public void testUpdateUserCountWithoutBody() throws Exception{
        this.mockMvc.perform(put("/user/updateUser/{id}",4).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isBadRequest());
    }*/

    @Test
    void shouldReturn404WhenUpdateUserCountWithoutPosition() throws Exception {
        this.mockMvc.perform(put("/user/updateUser").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateUserCount() throws Exception{
        this.mockMvc.perform(put("/user/updateUser/{id}/updatedDetails/1800Flowers",4).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
    }

    @Test
    public void testUpdateUserCountDataVerification() throws Exception{
        this.mockMvc.perform(put("/user/updateUser/{id}/updatedDetails/1800Flowers",4).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andExpect(jsonPath("$[3].body", is("1800Flowers"))).andExpect(jsonPath("$[3].title", is("1800Flowers")))
                .andExpect(jsonPath("$[3].id", is(4)));
    }
}
