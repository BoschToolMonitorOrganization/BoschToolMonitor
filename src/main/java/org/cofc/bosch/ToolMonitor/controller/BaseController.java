package org.cofc.bosch.ToolMonitor.controller;

import org.cofc.bosch.ToolMonitor.components.WorkPieceCarrier;
import org.cofc.bosch.ToolMonitor.components.WorkPieceCarrierMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
        model.addAttribute("WorkPieceCarrier", new WorkPieceCarrier());
        return "workPieceCarrierForm";
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

}