package org.cofc.bosch.ToolMonitor.controller;

import org.cofc.bosch.ToolMonitor.components.WPCFile.WPCFile;
import org.cofc.bosch.ToolMonitor.components.WPCFile.WPCFileRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.cofc.bosch.ToolMonitor.utilities.ControllerUtilities.initWPCCombosInModel;

@Controller
public class WPCFileController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/createWPCFile")
    public String wpcFileForm(Model model) {
        initWPCCombosInModel(model, jdbcTemplate);
        model.addAttribute("wpcFile", new WPCFile());
        return "wpcFileForm";
    }

    @PostMapping("/createWPCFile")
    public String wpcFileSubmission(@ModelAttribute WPCFile wpcFile, Model model) {
        model.addAttribute("wpcFile", wpcFile);
        try {
            wpcFile.enterWPCFileIntoDatabase(jdbcTemplate);
        } catch (DataAccessException e) {
            model.addAttribute("error", e.getMessage());
            List<String> valueStreams = jdbcTemplate.queryForList("Select Distinct valueStream From WPCCombos;", String.class);
            List<String> prodLines = null;
            List<String> prodTypes = null;
            if (!valueStreams.isEmpty()) {
                prodLines = jdbcTemplate.queryForList("Select Distinct productionLine From WPCCombos where valueStream=\""
                        + wpcFile.getValueStream() + "\";", String.class);
                if (!prodLines.isEmpty()) {
                    prodTypes = jdbcTemplate.queryForList("Select Distinct productType From WPCCombos where valueStream=\""
                            + wpcFile.getValueStream() + "\" and productionLine=\"" + wpcFile.getProductionLine() + "\";", String.class);
                }
            }
            model.addAttribute("valueStreams", valueStreams);
            model.addAttribute("prodLines", prodLines);
            model.addAttribute("prodTypes", prodTypes);
            return "wpcFileForm";
        }
        return "wpcFileSubmission";
    }

    @GetMapping("/wpcFilesForWPC")
    public String wpcFilesForWPC(@RequestParam String productionLine, @RequestParam String valueStream,
                                 @RequestParam String productType, Model model) {
        List<WPCFile> wpcFiles = jdbcTemplate.query("Select * From wpcFiles where valueStream=\"" +
                valueStream + "\" and productionLine=\"" + productionLine + "\" and productType=\"" +
                productType + "\";", new WPCFileRowMapper());
        model.addAttribute("wpcFiles", wpcFiles);
        model.addAttribute("isForWPC", true);

        return "wpcFiles";
    }

    @GetMapping("/wpcFiles")
    public String wpcFiles(Model model) {
        List<WPCFile> wpcFiles = jdbcTemplate.query("Select * From wpcFiles", new WPCFileRowMapper());
        model.addAttribute("wpcFiles", wpcFiles);
        model.addAttribute("isForWPC", false);

        return "wpcFiles";
    }

    @GetMapping("/delete_WPCFile")
    public String deleteWPCFile(@RequestParam String productionLine, @RequestParam String valueStream,
                                @RequestParam String productType, @RequestParam String author, @RequestParam int revisionNumber,
                                @RequestParam String fileType, @RequestParam String fileName, @RequestParam boolean isForWPC,
                                Model model) {
        WPCFile.deleteWPCFile(jdbcTemplate, valueStream, productionLine, productType, author, revisionNumber, fileType, fileName);
        List<WPCFile> wpcFiles;
        if (isForWPC) {
            wpcFiles = jdbcTemplate.query("Select * From wpcFiles where valueStream=\"" +
                    valueStream + "\" and productionLine=\"" + productionLine + "\" and productType=\"" +
                    productType + "\";", new WPCFileRowMapper());
            model.addAttribute("isForWPC", true);
        } else {
            wpcFiles = jdbcTemplate.query("Select * From wpcFiles", new WPCFileRowMapper());
            model.addAttribute("isForWPC", false);
        }
        model.addAttribute("wpcFiles", wpcFiles);


        return "wpcFiles";
    }

    @GetMapping("/download/wpcFile")
    public @ResponseBody
    byte[] downloadWPCFile(@RequestParam String productionLine, @RequestParam String valueStream,
                           @RequestParam String productType, @RequestParam String author, @RequestParam int revisionNumber,
                           @RequestParam String fileType, @RequestParam String fileName) {
        Blob data = jdbcTemplate.query("select fileData From wpcFiles where valueStream=\"" +
                        valueStream + "\" and productionLine=\"" + productionLine + "\" and productType=\"" +
                        productType + "\" and author=\"" + author + "\" and revisionNumber=" + revisionNumber +
                        " and fileType=\"" + fileType + "\" and fileName=\"" + fileName + "\";",
                new ResultSetExtractor<Blob>() {
                    @Override
                    public Blob extractData(ResultSet rs) throws SQLException {
                        rs.next();
                        return rs.getBlob("fileData");
                    }
                });
        byte[] retVal;
        try {
            retVal = data.getBytes(1, (int) data.length());
        } catch (SQLException e) {
            retVal = new byte[0];
        }
        return retVal;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
