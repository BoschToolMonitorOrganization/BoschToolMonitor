package org.cofc.bosch.ToolMonitor.components.WorkPieceCarrier;

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

    public  void enterWPCIntoDatabase(JdbcTemplate jdbcTemplate) {
        jdbcTemplate.execute("insert into WPCs values(\""
                + valueStream + "\", \""
                + productionLine + "\", \""
                + productType + "\", "
                + workPieceCarrierNumber + ")");
    }

    public static void deleteWPC(String valueStream, String productionLine, String productType, int workPieceCarrierNumber, JdbcTemplate jdbcTemplate) {
        jdbcTemplate.execute("Delete From WPCs where valueStream=\"" + valueStream +
                "\" and productionLine=\"" + productionLine +
                "\" and productType=\"" + productType + "\" and " +
                "workPieceCarrierNumber=" +  workPieceCarrierNumber + ";");
    }
}
