package org.cofc.bosch.ToolMonitor.components.WPCCombo;

import org.cofc.bosch.ToolMonitor.components.ProductionLine;
import org.springframework.jdbc.core.JdbcTemplate;

public class WPCCombo extends ProductionLine {

    protected String productType;

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    @Override
    public void enterIntoDB(JdbcTemplate jdbcTemplate) {
        jdbcTemplate.execute("insert into WPCCombos values(\""
                + valueStream + "\", \""
                + productionLine + "\", \""
                + productType + "\")");
    }

    public static void deleteWPCCombo(String valueStream, String productionLine, String productType, JdbcTemplate jdbcTemplate) {
        String where =
                " where valueStream=\"" + valueStream +
                        "\" and productionLine=\"" + productionLine +
                        "\" and productType=\"" + productType + "\";";
        jdbcTemplate.batchUpdate(
                "Delete From RepairTickets" + where,
                "Delete From WPCs" + where,
                "Delete From wpcFiles" + where,
                "Delete From WPCCombos" + where);
    }
}
