package org.cofc.bosch.ToolMonitor.controller;

import org.cofc.bosch.ToolMonitor.components.WorkPieceCarrier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.sql.*;

@Controller
public class BaseController {

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

            String sql = "INSERT INTO WPCFiles (workPieceCarrierNumber, valueStream, productionLine, productType, fileName, fileType, data) values (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, carrier.getWorkPieceCarrierNumber());
            statement.setString(2, carrier.getValueStream());
            statement.setString(3, carrier.getProductionLine());
            statement.setString(4, carrier.getProductType());
            statement.setString(5, carrier.getJPEG().getOriginalFilename());
            statement.setString(6, "jpeg");
            statement.setBlob(7, carrier.getJPEG().getInputStream());
            statement.addBatch();

            for(MultipartFile file : carrier.getComponentsList()) {
                statement.setString(1, carrier.getWorkPieceCarrierNumber());
                statement.setString(2, carrier.getValueStream());
                statement.setString(3, carrier.getProductionLine());
                statement.setString(4, carrier.getProductType());
                statement.setString(5, file.getOriginalFilename());
                statement.setString(6, "component");
                statement.setBlob(7, file.getInputStream());
                statement.addBatch();
            }

            for(MultipartFile file : carrier.getComponentsList()) {
                statement.setString(1, carrier.getWorkPieceCarrierNumber());
                statement.setString(2, carrier.getValueStream());
                statement.setString(3, carrier.getProductionLine());
                statement.setString(4, carrier.getProductType());
                statement.setString(5, file.getOriginalFilename());
                statement.setString(6, "drawing");
                statement.setBlob(7, file.getInputStream());
                statement.addBatch();
            }

            for(MultipartFile file : carrier.getPDFs()) {
                statement.setString(1, carrier.getWorkPieceCarrierNumber());
                statement.setString(2, carrier.getValueStream());
                statement.setString(3, carrier.getProductionLine());
                statement.setString(4, carrier.getProductType());
                statement.setString(5, file.getOriginalFilename());
                statement.setString(6, "pdf");
                statement.setBlob(7, file.getInputStream());
                statement.addBatch();
            }

            for(MultipartFile file : carrier.getCADs()) {
                statement.setString(1, carrier.getWorkPieceCarrierNumber());
                statement.setString(2, carrier.getValueStream());
                statement.setString(3, carrier.getProductionLine());
                statement.setString(4, carrier.getProductType());
                statement.setString(5, file.getOriginalFilename());
                statement.setString(6, "cad");
                statement.setBlob(7, file.getInputStream());
                statement.addBatch();
            }

            statement.executeBatch();
            statement.close();
        } catch (SQLException sqlExc) {
            sqlExc.printStackTrace();
            System.out.println("Sql exception: " + "\n" + sqlExc.getMessage() + "\n");
        } catch (ClassNotFoundException classNotFound) {
            classNotFound.printStackTrace();
            System.out.println("class not found exception occured\n" + classNotFound.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage() + "\n");
            e.printStackTrace();
        }

        return "workPieceCarrierSubmission";
    }

}