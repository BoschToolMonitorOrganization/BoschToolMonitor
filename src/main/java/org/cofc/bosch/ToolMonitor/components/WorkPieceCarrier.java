package org.cofc.bosch.ToolMonitor.components;

import org.springframework.jdbc.core.JdbcTemplate;

public class WorkPieceCarrier {

    private int workPieceCarrierNumber;
    private String valueStream;
    private String productionLine;
    private String productType;

    public int getWorkPieceCarrierNumber() {
        return workPieceCarrierNumber;
    }

    public void setWorkPieceCarrierNumber(int workPieceCarrierNumber) {
        this.workPieceCarrierNumber = workPieceCarrierNumber;
    }

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


    public static void enterWPCIntoDatabase(WorkPieceCarrier carrier, JdbcTemplate jdbcTemplate) {
        jdbcTemplate.execute("insert into WPCs values(\""
                + carrier.getValueStream() + "\", \""
                + carrier.getProductionLine() + "\", \""
                + carrier.getProductType() + "\", "
                + carrier.getWorkPieceCarrierNumber() + ")");
    }

}
