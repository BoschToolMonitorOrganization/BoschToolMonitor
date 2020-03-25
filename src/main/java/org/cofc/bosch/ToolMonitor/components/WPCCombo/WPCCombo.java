package org.cofc.bosch.ToolMonitor.components.WPCCombo;

import org.springframework.jdbc.core.JdbcTemplate;

public class WPCCombo {

    private String valueStream;
    private String productionLine;
    private String productType;


    public String getValueStream() {
        return valueStream;
    }

    public void setValueStream(String valueStream) {
        this.valueStream = valueStream;
    }

    public String getProductionLine() {
        return productionLine;
    }

    public void setProductionLine(String productionLine) {
        this.productionLine = productionLine;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }


    public void enterWPCComboIntoDB(JdbcTemplate jdbcTemplate) {
        jdbcTemplate.execute("insert into WPCCombos values(\""
                + valueStream + "\", \""
                + productionLine + "\", \""
                + productType + "\")");
    }

    public static void deleteWPCCombo(String valueStream, String productionLine, String productType, JdbcTemplate jdbcTemplate) {
        jdbcTemplate.execute("Delete From WPCs where valueStream=\"" + valueStream +
                "\" and productionLine=\"" + productionLine +
                "\" and productType=\"" + productType + "\";");
        jdbcTemplate.execute("Delete From wpcFiles where valueStream=\"" + valueStream +
                "\" and productionLine=\"" + productionLine +
                "\" and productType=\"" + productType + "\";");
        jdbcTemplate.execute("Delete From WPCCombos where valueStream=\"" + valueStream +
                "\" and productionLine=\"" + productionLine +
                "\" and productType=\"" + productType + "\";");
    }
}
