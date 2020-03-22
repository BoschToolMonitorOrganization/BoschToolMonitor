package org.cofc.bosch.ToolMonitor.controller;

import org.cofc.bosch.ToolMonitor.components.RepairCode.RepairCode;
import org.cofc.bosch.ToolMonitor.components.RepairCode.RepairCodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RepairCodeController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/RepairCodes")
    public String repairCodes(Model model) {
        model.addAttribute("repairCode", new RepairCode());
        model.addAttribute("repairCodes", jdbcTemplate.query("select * from RepairCodes;", new RepairCodeMapper()));

        return "RepairCodes";
    }

    @PostMapping("/createRepairCode")
    public String createRepairCode(@ModelAttribute RepairCode repairCode, Model model) {
        try {
            repairCode.enterIntoDB(jdbcTemplate);
            model.addAttribute("repairCode", new RepairCode());
        } catch (DataAccessException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("repairCode", repairCode);
        }
        model.addAttribute("repairCodes", jdbcTemplate.query("select * from RepairCodes;", new RepairCodeMapper()));

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
