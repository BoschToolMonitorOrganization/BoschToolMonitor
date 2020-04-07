package org.cofc.bosch.ToolMonitor.controller;

import org.cofc.bosch.ToolMonitor.components.RepairCode.RepairCode;
import org.cofc.bosch.ToolMonitor.components.RepairCode.RepairCodeMapper;
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
public class RepairCodeController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/RepairCodes")
    public String repairCodes(Model model) {
        model.addAttribute("repairCode", new RepairCode());
        model.addAttribute("repairCodes", jdbcTemplate.query("select * from RepairCodes;", new RepairCodeMapper()));

        List<String> valueStreams = jdbcTemplate.queryForList("Select Distinct valueStream From WPCCombos;", String.class);
        List<String> prodLines = null;
        if (!valueStreams.isEmpty()) {
            prodLines = jdbcTemplate.queryForList("Select Distinct productionLine From WPCCombos where valueStream=\""
                    + valueStreams.get(0) + "\";", String.class);
        }
        model.addAttribute("valueStreams", valueStreams);
        model.addAttribute("prodLines", prodLines);

        return "RepairCodes";
    }

    @PostMapping("/createRepairCode")
    public String createRepairCode(@ModelAttribute RepairCode repairCode, Model model) {
        List<String> valueStreams = jdbcTemplate.queryForList("Select Distinct valueStream From WPCCombos;", String.class);
        List<String> prodLines = null;
        try {
            repairCode.enterIntoDB(jdbcTemplate);
            model.addAttribute("repairCode", new RepairCode());
            if (!valueStreams.isEmpty()) {
                prodLines = jdbcTemplate.queryForList("Select Distinct productionLine From WPCCombos where valueStream=\""
                        + valueStreams.get(0) + "\";", String.class);
            }
        } catch (DataAccessException e) {
            if (e instanceof DuplicateKeyException) {
                model.addAttribute("error", "It looks like this repair code already exists!");
            } else {
                model.addAttribute("error", ControllerUtilities.buildErrorLog(e));
            }
            model.addAttribute("repairCode", repairCode);
            if (!valueStreams.isEmpty()) {
                prodLines = jdbcTemplate.queryForList("Select Distinct productionLine From WPCCombos where valueStream=\""
                        + repairCode.getValueStream() + "\";", String.class);
            }
        }
        model.addAttribute("repairCodes", jdbcTemplate.query("select * from RepairCodes;", new RepairCodeMapper()));
        model.addAttribute("valueStreams", valueStreams);
        model.addAttribute("prodLines", prodLines);

        return "RepairCodes";
    }

    @GetMapping("/delete_repair_code")
    public String deleteRepairCode(@RequestParam String valueStream, @RequestParam String productionLine,
                                   @RequestParam String repairCategory, @RequestParam String repairDetail,
                                   Model model) {
        try {
            RepairCode.deleteFromDB(valueStream, productionLine, repairCategory, repairDetail, jdbcTemplate);
        } catch (DataAccessException e) {
            model.addAttribute("error", e.getMessage());
        }
        model.addAttribute("repairCode", new RepairCode());
        model.addAttribute("repairCodes", jdbcTemplate.query("select * from RepairCodes;", new RepairCodeMapper()));
        return "RepairCodes";
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
