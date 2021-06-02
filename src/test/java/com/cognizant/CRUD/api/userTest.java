package com.cognizant.CRUD.api;

import com.cognizant.CRUD.api.controller.userController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.Matchers.is;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(userController.class)
public class userTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getUsers() throws Exception {

        RequestBuilder request = get("/users");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.email", is("edgar@email.com")));
    }

    @Test
    public void postUsersTest() throws Exception {

        String userJson = getJSON("C:\\Users\\855961\\IdeaProjects\\CRUD-api\\src\main\\resources\\user.json");
        RequestBuilder request = post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.email", is("edgar@email.com")));
    }
    private String getJSON(String path) throws Exception {
        return new String(Files.readAllBytes(Paths.get(path)));
    }

    // ***
    @Test
    public void getUserByIdTest() throws Exception {
        RequestBuilder request = get("/users/5");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(null)))
                .andExpect(jsonPath("$.email", is("edgar@email.com")));
    }

    // patch can replace individually
    // put replace everything at once
    // ***
    @Test
    public void patchUserIdTest() throws Exception{
        String userJson = getJSON("C:\\Users\\855961\\IdeaProjects\\CRUD-api\\src\main\\resources\\user.json");
        RequestBuilder request = patch("/users/5")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.email", is("edgar@email.com")));
    }

    @Test
    public void deleteUserIdTest() throws Exception{

        RequestBuilder request = delete("/users/5");

        this.mvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    public void postUserAuthenticationTest() throws Exception{
        String userJson = getJSON("C:\\Users\\855961\\IdeaProjects\\CRUD-api\\src\main\\resources\\user.json");
        RequestBuilder request = post("/users/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson)
                // send a post to get the token, then submit the post w/token
//        ResultActions result = mockMvc.perform(post("/oauth/token") .params(params)
//                .with(httpBasic("fooClientIdPassword","secret")) .accept("application/json;charset=UTF-8"))
//                .andExpect(status().isOk()) .andExpect(content().contentType("application/json;charset=UTF-8"));
//         .headers();

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.email", is("edgar@email.com")));

    }
}
