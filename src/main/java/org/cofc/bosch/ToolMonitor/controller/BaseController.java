package org.cofc.bosch.ToolMonitor.controller;

import org.cofc.bosch.ToolMonitor.components.WorkPieceCarrier;
import org.cofc.bosch.ToolMonitor.components.WorkPieceCarrierMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BaseController {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @GetMapping("/")
    public String homePage() {
        return "welcome";
    }

    @GetMapping("/welcome")
    public String firstPage() {
        return "welcome";
    }

    @GetMapping("/workpiece")
    public String workPieceCarrierForm(Model model) {
        List<String> valueStreams = new ArrayList<>();
        valueStreams.add("CRIN");
        valueStreams.add("CRINV2");
        model.addAttribute("valueStreams", valueStreams);
        return "workPieceCarrierForm";
    }

    @RequestMapping(value = "/productionLines")
    @ResponseBody
    public List<String> getProductionLines(@RequestParam String valueStream) {
        List<String> productionLines = new ArrayList<>();

        productionLines.add("productionLine1");
        productionLines.add("productionLine2");
        productionLines.add("productionLine3");
        productionLines.add("productionLine4");
        productionLines.add("productionLine5");

        return productionLines;
    }

    @RequestMapping(value = "/productTypes")
    @ResponseBody
    public List<String> getRegions(@RequestParam String productionLine, @RequestParam String valueStream) {
        List<String> productTypes = new ArrayList<>();

        productTypes.add("productType1");
        productTypes.add("productType2");
        productTypes.add("productType3");
        productTypes.add("productType4");
        productTypes.add("productType5");

        return productTypes;
    }

    @PostMapping("/workpiece")
    public String workPieceCarrierSubmit(@ModelAttribute WorkPieceCarrier carrier) {
        WorkPieceCarrier.enterWPCIntoDatabase(carrier, jdbcTemplate);
        return "workPieceCarrierSubmission";
    }

    @GetMapping("/workpiececarriers")
    public String workPieceCarriers(Model model) {
        List<WorkPieceCarrier> carriers = jdbcTemplate.query("Select * from WorkPieceCarriers", new WorkPieceCarrierMapper());
        model.addAttribute("carriers", carriers);

        return "workPieceCarriers";
    }

    @RequestMapping(value = "/getwpcs")
    public @ResponseBody
    List<WorkPieceCarrier> getWPCs(Model model) {
        return jdbcTemplate.query("Select * from WorkPieceCarriers", new WorkPieceCarrierMapper());
    }


    //for mocking in tests (mockito sucks)
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
