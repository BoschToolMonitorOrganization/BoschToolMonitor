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
        List<String> valueStreams = jdbcTemplate.queryForList("Select Distinct valueStream From WPCCombos;", String.class);
        List<String> prodLines = null;
        List<String> prodTypes = null;
        if (!valueStreams.isEmpty()) {
            prodLines = jdbcTemplate.queryForList("Select Distinct productionLine From WPCCombos where valueStream=\""
                    + valueStreams.get(0) + "\";", String.class);
            if (!prodLines.isEmpty()) {
                prodTypes = jdbcTemplate.queryForList("Select Distinct productType From WPCCombos where valueStream=\""
                        + valueStreams.get(0) + "\" and productionLine=\"" + prodLines.get(0) + "\";", String.class);
            }
        }
        model.addAttribute("valueStreams", valueStreams);
        model.addAttribute("prodLines", prodLines);
        model.addAttribute("prodTypes", prodTypes);
        model.addAttribute("carrier", new WorkPieceCarrier());
        return "workPieceCarrierForm";
    }

    @RequestMapping(value = "/productionLines")
    @ResponseBody
    public List<String> getProductionLines(@RequestParam String valueStream) {
        return jdbcTemplate.queryForList("Select Distinct productionLine From WPCCombos where valueStream=\"" + valueStream + "\";", String.class);
    }

    @RequestMapping(value = "/productTypes")
    @ResponseBody
    public List<String> getRegions(@RequestParam String productionLine, @RequestParam String valueStream) {
        return jdbcTemplate.queryForList("Select Distinct productType From WPCCombos where valueStream=\"" + valueStream + "\" and productionLine=\"" + productionLine + "\";", String.class);
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
