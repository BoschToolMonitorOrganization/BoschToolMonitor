package org.cofc.bosch.ToolMonitor.controller;

import org.cofc.bosch.ToolMonitor.components.WorkPieceCarrier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

@Controller
public class BaseController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/welcome")
    public @ResponseBody String firstPage() {
        return "welcome";
    }

    @GetMapping("/workpiece")
    public String workPieceCarrierForm(Model model) {
        model.addAttribute("WorkPieceCarrier", new WorkPieceCarrier());
        return "workPieceCarrierForm";
    }

    @PostMapping("/workpiece")
    public String workPieceCarrierSubmit(@ModelAttribute WorkPieceCarrier carrier) {
        jdbcTemplate.execute("insert into WorkPieceCarriers values(\""
                + carrier.getAuthor() + "\", \""
                + carrier.getRevisionNumber() + "\", \""
                + carrier.getRevisionReason() + "\", \""
                + carrier.getFileType() + "\", \""
                + carrier.getWorkPieceCarrierNumber() + "\", \""
                + carrier.getValueStream() + "\", \""
                + carrier.getProductionLine() + "\", \""
                + carrier.getProductType() + "\", \""
                + carrier.getReasonForChange() + "\", \""
                + carrier.getReasonCategory() + "\", "
                + carrier.isToolLifeAchieved() + ", \""
                + carrier.getLocationRepairTicket() + "\", \""
                + carrier.getDownTimeImpact() + "\");");

        String sql = "INSERT INTO WPCFiles (workPieceCarrierNumber, valueStream, productionLine, productType, fileName, fileType, data) values (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.execute(sql, new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement statement)
                    throws SQLException {
                try {
                    if (carrier.getJPEG() != null && !carrier.getJPEG().isEmpty()) {
                        statement.setString(1, carrier.getWorkPieceCarrierNumber());
                        statement.setString(2, carrier.getValueStream());
                        statement.setString(3, carrier.getProductionLine());
                        statement.setString(4, carrier.getProductType());
                        statement.setString(5, carrier.getJPEG().getOriginalFilename());
                        statement.setString(6, "jpeg");
                        statement.setBlob(7, carrier.getJPEG().getInputStream());
                        statement.addBatch();
                    }

                    if (carrier.getComponentsList() != null) {
                        for (MultipartFile file : carrier.getComponentsList()) {
                            if (!file.isEmpty()) {
                                statement.setString(1, carrier.getWorkPieceCarrierNumber());
                                statement.setString(2, carrier.getValueStream());
                                statement.setString(3, carrier.getProductionLine());
                                statement.setString(4, carrier.getProductType());
                                statement.setString(5, file.getOriginalFilename());
                                statement.setString(6, "component");
                                statement.setBlob(7, file.getInputStream());
                                statement.addBatch();
                            }
                        }
                    }

                    if (carrier.getDrawingLibrary() != null) {
                        for (MultipartFile file : carrier.getDrawingLibrary()) {
                            if (!file.isEmpty()) {
                                statement.setString(1, carrier.getWorkPieceCarrierNumber());
                                statement.setString(2, carrier.getValueStream());
                                statement.setString(3, carrier.getProductionLine());
                                statement.setString(4, carrier.getProductType());
                                statement.setString(5, file.getOriginalFilename());
                                statement.setString(6, "drawing");
                                statement.setBlob(7, file.getInputStream());
                                statement.addBatch();
                            }
                        }
                    }
                    if (carrier.getPDFs() != null) {
                        for (MultipartFile file : carrier.getPDFs()) {
                            if (!file.isEmpty()) {
                                statement.setString(1, carrier.getWorkPieceCarrierNumber());
                                statement.setString(2, carrier.getValueStream());
                                statement.setString(3, carrier.getProductionLine());
                                statement.setString(4, carrier.getProductType());
                                statement.setString(5, file.getOriginalFilename());
                                statement.setString(6, "pdf");
                                statement.setBlob(7, file.getInputStream());
                                statement.addBatch();
                            }
                        }
                    }

                    if (carrier.getCADs() != null) {
                        for (MultipartFile file : carrier.getCADs()) {
                            if (!file.isEmpty()) {
                                statement.setString(1, carrier.getWorkPieceCarrierNumber());
                                statement.setString(2, carrier.getValueStream());
                                statement.setString(3, carrier.getProductionLine());
                                statement.setString(4, carrier.getProductType());
                                statement.setString(5, file.getOriginalFilename());
                                statement.setString(6, "cad");
                                statement.setBlob(7, file.getInputStream());
                                statement.addBatch();
                            }
                        }
                    }
                } catch (IOException e) {
                    System.out.println(e.getCause() + "\n" + e.getMessage() + "\n");
                }

                int[] success = statement.executeBatch();
                boolean retVal = true;
                for (int curr : success) {
                    if (curr == Statement.EXECUTE_FAILED) {
                        retVal = false;
                    }
                }

                return retVal;
            }
        });

        return "workPieceCarrierSubmission";
    }

}