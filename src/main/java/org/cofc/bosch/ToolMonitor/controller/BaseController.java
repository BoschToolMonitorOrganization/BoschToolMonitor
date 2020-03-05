package org.cofc.bosch.ToolMonitor.controller;

import org.cofc.bosch.ToolMonitor.WPCCombo.WPCCombo;
import org.cofc.bosch.ToolMonitor.WPCCombo.WPCComboRowMapper;
import org.cofc.bosch.ToolMonitor.components.WorkPieceCarrier.WorkPieceCarrier;
import org.cofc.bosch.ToolMonitor.components.WorkPieceCarrier.WorkPieceCarrierMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
        model.addAttribute("carrier", new WorkPieceCarrier());
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
    public String workPieceCarrierSubmit(@ModelAttribute WorkPieceCarrier carrier, Model model) {
        model.addAttribute("carrier", carrier);
        try {
            WorkPieceCarrier.enterWPCIntoDatabase(carrier, jdbcTemplate);
        } catch (DataAccessException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("valueStreams", new ArrayList<>());
            return "workPieceCarrierForm";
        }
        return "workPieceCarrierSubmission";
    }

    @GetMapping("/workpiececarriers")
    public String workPieceCarriers(Model model) {
        List<WorkPieceCarrier> carriers = jdbcTemplate.query("Select * from WPCs", new WorkPieceCarrierMapper());
        model.addAttribute("carriers", carriers);

        return "workPieceCarriers";
    }

    @GetMapping("/wpccombos")
    public String WPCCombos(Model model) {
        List<WPCCombo> carriers = jdbcTemplate.query("Select * from WPCCombos", new WPCComboRowMapper());
        model.addAttribute("combos", carriers);
        model.addAttribute("combo", new WPCCombo());

        return "wpcCombos";
    }

    @PostMapping("/wpccombo")
    public String wpcComboSubmit(@ModelAttribute WPCCombo combo, Model model) {
        try {
            combo.enterWPCComboIntoDB(jdbcTemplate);
            model.addAttribute("combo", new WPCCombo());
        } catch (DataAccessException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("combo", combo);
        }
        List<WPCCombo> combos = jdbcTemplate.query("Select * from WPCCombos", new WPCComboRowMapper());
        model.addAttribute("combos", combos);

        return "wpcCombos";
    }

    @GetMapping("/wpccombo/delete")
    public String deleteWPCCombo(@RequestParam String productionLine, @RequestParam String valueStream, @RequestParam String productType, Model model) {
        try {
            WPCCombo.deleteWPCCombo(valueStream, productionLine, productType, jdbcTemplate);
        } catch (DataAccessException e) {
            model.addAttribute("error", e.getMessage());
        }
        List<WPCCombo> combos = jdbcTemplate.query("Select * from WPCCombos", new WPCComboRowMapper());
        model.addAttribute("combos", combos);
        model.addAttribute("combo", new WPCCombo());

        return "wpcCombos";
    }


    //for mocking in tests (mockito sucks)
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
