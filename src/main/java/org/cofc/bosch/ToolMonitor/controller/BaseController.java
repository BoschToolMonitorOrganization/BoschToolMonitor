package org.cofc.bosch.ToolMonitor.controller;

import org.cofc.bosch.ToolMonitor.components.WorkPieceCarrier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.*;

@Controller
public class BaseController {

    private static String upload_dir = "F:/springfileupload/";

    @RequestMapping("/welcome")
    public ModelAndView firstPage() {
        return new ModelAndView("welcome");
    }

    @GetMapping("/workpiece")
    public String workPieceCarrierForm(Model model) {
        model.addAttribute("WorkPieceCarrier", new WorkPieceCarrier());
        return "workPieceCarrierForm";
    }

    @PostMapping("/workpiece")
    public String workPieceCarrierSubmit(@ModelAttribute WorkPieceCarrier carrier) {
        System.out.println(carrier.getAuthor());

        String host = "jdbc:mariadb://localhost:3306/workpiececarriers";
        String userName = "root";
        String password = "barrett01";
        Connection con;

        try {
            Class.forName("org.mariadb.jdbc.Driver");

            con = DriverManager.getConnection(host, userName, password);
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.addBatch("insert into WorkPieceCarriers values(\""
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
            stmt.executeBatch();
            stmt.close();
        } catch (SQLException sqlExc) {
            sqlExc.printStackTrace();
            System.out.println("Failed to connect to: " + host + "\n" + sqlExc.getMessage());
        } catch(ClassNotFoundException classNotFound) {
            classNotFound.printStackTrace();
            System.out.println("class not found exception occured\n" + classNotFound.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage() + "\n");
            e.printStackTrace();
        }


        return "workPieceCarrierSubmission";
    }

}