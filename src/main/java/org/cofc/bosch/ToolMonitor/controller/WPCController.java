package org.cofc.bosch.ToolMonitor.controller;

import org.cofc.bosch.ToolMonitor.components.WorkPieceCarrier.WorkPieceCarrier;
import org.cofc.bosch.ToolMonitor.components.WorkPieceCarrier.WorkPieceCarrierMapper;
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

import static org.cofc.bosch.ToolMonitor.utilities.ControllerUtilities.buildErrorLog;
import static org.cofc.bosch.ToolMonitor.utilities.ControllerUtilities.initWPCCombosInModel;

@Controller
public class WPCController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/workpiece")
    public String workPieceCarrierForm(Model model) {
        initWPCCombosInModel(model, jdbcTemplate);
        model.addAttribute("carrier", new WorkPieceCarrier());
        return "workPieceCarrierForm";
    }

    @PostMapping("/workpiece")
    public String workPieceCarrierSubmit(@ModelAttribute WorkPieceCarrier carrier, Model model) {
        model.addAttribute("carrier", carrier);
        try {
            carrier.enterIntoDB(jdbcTemplate);
        } catch (DataAccessException e) {
            if (e instanceof DuplicateKeyException) {
                model.addAttribute("error", "It looks like this work piece carrier already exists!");
            } else {
                model.addAttribute("error", buildErrorLog(e));
            }
            List<String> valueStreams = jdbcTemplate.queryForList("Select Distinct valueStream From WPCCombos;", String.class);
            List<String> prodLines = null;
            List<String> prodTypes = null;
            if (!valueStreams.isEmpty()) {
                prodLines = jdbcTemplate.queryForList("Select Distinct productionLine From WPCCombos where valueStream=\""
                        + carrier.getValueStream() + "\";", String.class);
                if (!prodLines.isEmpty()) {
                    prodTypes = jdbcTemplate.queryForList("Select Distinct productType From WPCCombos where valueStream=\""
                            + carrier.getValueStream() + "\" and productionLine=\"" + carrier.getProductionLine() + "\";", String.class);
                }
            }
            model.addAttribute("valueStreams", valueStreams);
            model.addAttribute("prodLines", prodLines);
            model.addAttribute("prodTypes", prodTypes);
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

    @GetMapping("/delete_wpc")
    public String deleteWPC(Model model, @RequestParam String productionLine, @RequestParam String valueStream,
                            @RequestParam String productType, @RequestParam int workPieceCarrierNumber) {
        WorkPieceCarrier.deleteWPC(valueStream, productionLine, productType, workPieceCarrierNumber, jdbcTemplate);
        model.addAttribute("carriers", jdbcTemplate.query("Select * from WPCs", new WorkPieceCarrierMapper()));

        return "workPieceCarriers";
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
