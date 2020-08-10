package com.accounter.controller;

import com.accounter.restapi.controller.AccountController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testInsertOne() throws Exception {

        String jsonPayload = "";

        mockMvc.perform(post("/api/accounts/add")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonPayload))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("", is(equalTo(""))))
                    .andExpect(jsonPath("", is(equalTo(""))))
                    .andExpect(jsonPath("", is(equalTo(""))))
                    .andExpect(jsonPath("", is(equalTo(""))))
                    .andExpect(jsonPath("", is(equalTo(""))))
                    .andExpect(jsonPath("", is(equalTo(""))))
                    .andExpect(jsonPath("", is(equalTo(""))));
    }

    @Test
    public void testListAll() throws Exception {

    }

    @Test
    public void testListOne() throws Exception {

    }

    @Test
    public void testDeleteOne() throws Exception {

    }

}
