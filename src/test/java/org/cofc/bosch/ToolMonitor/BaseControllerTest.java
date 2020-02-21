package org.cofc.bosch.ToolMonitor;

import org.cofc.bosch.ToolMonitor.controller.BaseController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class BaseControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    BaseController baseController;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(baseController).setViewResolvers(new InternalResourceViewResolver()).build();
    }

    @Test
    public void welcome() throws Exception {
        mockMvc.perform(get("/welcome"))
                .andExpect(status().isOk());
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
