package com.accounter.controller;

import com.accounter.restapi.controller.CompanyController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(CompanyController.class)
public class CompanyControllerTest {

    @Autowired
    MockMvc mockMvc;

//    @Test
//    public void testListAll() throws Exception {
//
//    }

    @Test
    public void testListOne() throws Exception {

    }

//    @Test
//    public void testInsertOne() throws Exception {
//
//    }

    @Test
    public void testDeleteOne() throws Exception {

    }

}
