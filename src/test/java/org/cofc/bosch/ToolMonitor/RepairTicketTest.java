package org.cofc.bosch.ToolMonitor;

import org.cofc.bosch.ToolMonitor.controller.RepairTicketController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class RepairTicketTest {


    private MockMvc mockMvc;
    private RepairTicketController repairTicketController;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setup() {
        repairTicketController = new RepairTicketController();
        repairTicketController.setJdbcTemplate(jdbcTemplate);

        MockitoAnnotations.initMocks(this);

        ViewResolver resolver = new ThymeleafViewResolver();
        ((ThymeleafViewResolver) resolver).setTemplateEngine(new SpringTemplateEngine());
        this.mockMvc = MockMvcBuilders.standaloneSetup(repairTicketController).setViewResolvers(resolver).build();
    }

    @Test
    public void repairTicketOpenForm() throws Exception {
        mockMvc.perform(get("/createOpenRepairTicket"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("openRepairTicketForm"))
                .andReturn();
    }

    @Test
    public void repairTicketTable() throws Exception {
        mockMvc.perform(get("/repairTickets"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("repairTickets"))
                .andReturn();
    }

}
