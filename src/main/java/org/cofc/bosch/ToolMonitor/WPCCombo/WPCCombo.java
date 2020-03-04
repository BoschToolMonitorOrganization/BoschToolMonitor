package org.cofc.bosch.ToolMonitor.WPCCombo;

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


    public static void enterWPCComboIntoDB(WPCCombo combo, JdbcTemplate jdbcTemplate) {
        jdbcTemplate.execute("insert into WPCCombos values(\""
                + combo.getValueStream() + "\", \""
                + combo.getProductionLine() + "\", \""
                + combo.getProductType() + "\")");
    }
}
