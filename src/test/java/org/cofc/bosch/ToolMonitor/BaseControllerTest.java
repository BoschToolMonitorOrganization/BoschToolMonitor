package org.cofc.bosch.ToolMonitor;

import org.cofc.bosch.ToolMonitor.controller.BaseController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@WebMvcTest(controllers = BaseController.class)
public class BaseControllerTest {


    //@Autowired
    protected MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(BaseController.class).build();
    }


    // Get request with Param
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

}

