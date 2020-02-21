package org.cofc.bosch.ToolMonitor;

import org.cofc.bosch.ToolMonitor.components.WorkPieceCarrier;
import org.cofc.bosch.ToolMonitor.controller.BaseController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WorkPieceCarrier.class)
public class BaseControllerTest {


    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void welcome() throws Exception {
        mockMvc.perform(get("/welcome"))
                .andExpect(status().isOk());/*
                .andExpect(view().name("welcome"))
                .andExpect(content().string("Welcome to your Bosch Tool Monitor!"));*/
    }

    @Test
    public void workPieceCarrierForm() throws Exception {
        mockMvc.perform(get("/workpiece"))
                .andExpect(status().isOk());
               /* .andExpect(view().name("workPieceCarrierForm"))
                .andExpect(content().string("Characteristics"))
                .andExpect(content().string("Features"))
                .andExpect(content().string("Repair Ticket Details"));*/
    }

    @Test
    public void workPieceCarrierSubmission() throws Exception {
        try {
            mockMvc.perform(post("/workpiece", new WorkPieceCarrier()))
                    .andExpect(status().isOk())
                    .andExpect(view().name("/workPieceCarrierSubmission"))
                    .andExpect(content().string("Congrats!"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
