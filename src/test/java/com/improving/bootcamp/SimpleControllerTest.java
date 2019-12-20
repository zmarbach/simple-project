package com.improving.bootcamp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@WebMvcTest (controllers = SimpleController.class)//this sets up Spring before the tests actually run. Will only set up things necessary for SimpleController to work
public class SimpleControllerTest {

    @Autowired
    private MockMvc mockMvc; //give us a "fake" MVC framework to work with without actually committing any changes and then wire it in just like it would with any Bean class

    @Test
    public void test() throws Exception {
        mockMvc.perform(get("/home"))
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("books"));
    }

}
