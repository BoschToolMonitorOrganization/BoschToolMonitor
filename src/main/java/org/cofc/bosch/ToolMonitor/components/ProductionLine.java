package org.cofc.bosch.ToolMonitor.components;

import org.springframework.jdbc.core.JdbcTemplate;

public abstract class ProductionLine {
    protected String valueStream;
    protected String productionLine;

    protected abstract void enterIntoDB(JdbcTemplate jdbcTemplate);

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
}
