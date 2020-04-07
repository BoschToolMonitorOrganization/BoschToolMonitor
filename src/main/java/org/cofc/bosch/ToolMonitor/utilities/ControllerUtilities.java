package org.cofc.bosch.ToolMonitor.utilities;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;

import java.util.List;

public class ControllerUtilities {

    public static void initWPCCombosInModel(Model model, JdbcTemplate jdbcTemplate) {
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
    }

    public static String buildErrorLog(Exception e) {
        StringBuilder retVal = new StringBuilder();
        retVal.append("Error Type   : " + e.getClass().getName());
        retVal.append("Error Message: " + e.getMessage());
        return retVal.toString();
    }
}
