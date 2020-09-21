package com.kboxglobal.naydogdu.assignment;

import com.google.gson.Gson;
import com.kboxglobal.naydogdu.assignment.controller.ViewController;
import com.kboxglobal.naydogdu.assignment.paging.CursorPager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class ViewControllerTests {
    @Autowired
    MockMvc mvc;
    @Autowired
    ViewController viewController;

    @Test
    void contextLoads() {
        assertThat(viewController).isNotNull();
    }

    @Test
    public void getProfileViews() throws Exception {
        Gson gson = new Gson();
        MvcResult mvcResult = this.mvc.perform(get("/api/views")).andReturn();
        CursorPager fromJson = gson.fromJson(mvcResult.getResponse().getContentAsString(), CursorPager.class);
        assertThat(fromJson.getItems().size() <= 20);
    }
}
