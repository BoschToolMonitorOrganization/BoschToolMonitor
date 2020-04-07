package org.cofc.bosch.ToolMonitor.controller;

import org.cofc.bosch.ToolMonitor.components.WPCCombo.WPCCombo;
import org.cofc.bosch.ToolMonitor.components.WPCCombo.WPCComboRowMapper;
import org.cofc.bosch.ToolMonitor.utilities.ControllerUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class WPCComboController {
    @Autowired
    private JdbcTemplate jdbcTemplate;


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
            if (e instanceof DuplicateKeyException) {
                model.addAttribute("error", "It looks like that product type already exists!");
            } else {
                model.addAttribute("error", ControllerUtilities.buildErrorLog(e));
            }
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
        return WPCCombos(model);
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
