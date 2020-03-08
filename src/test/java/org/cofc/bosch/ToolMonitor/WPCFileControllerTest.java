package org.cofc.bosch.ToolMonitor;

import org.cofc.bosch.ToolMonitor.controller.WPCFileController;
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
public class WPCFileControllerTest {

    private MockMvc mockMvc;
    private WPCFileController wpcFileController;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setup() {
        wpcFileController = new WPCFileController();
        wpcFileController.setJdbcTemplate(jdbcTemplate);
        MockitoAnnotations.initMocks(this);

        ViewResolver resolver = new ThymeleafViewResolver();
        ((ThymeleafViewResolver) resolver).setTemplateEngine(new SpringTemplateEngine());
        this.mockMvc = MockMvcBuilders.standaloneSetup(wpcFileController).setViewResolvers(resolver).build();
    }

    @Test
    public void wpcFileForm() throws Exception {
        mockMvc.perform(get("/createWPCFile"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("wpcFileForm"))
                .andReturn();
    }

    @Test
    public void wpcFileTable() throws Exception {
        mockMvc.perform(get("/wpcFiles"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("wpcFiles"))
                .andReturn();
    }
}
