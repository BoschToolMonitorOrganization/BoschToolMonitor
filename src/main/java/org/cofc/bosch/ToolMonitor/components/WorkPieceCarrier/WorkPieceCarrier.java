package org.cofc.bosch.ToolMonitor.components.WorkPieceCarrier;

import org.cofc.bosch.ToolMonitor.components.WPCCombo.WPCCombo;
import org.springframework.jdbc.core.JdbcTemplate;

public class WorkPieceCarrier extends WPCCombo {

    protected int workPieceCarrierNumber;

    public int getWorkPieceCarrierNumber() {
        return workPieceCarrierNumber;
    }

    public void setWorkPieceCarrierNumber(int workPieceCarrierNumber) {
        this.workPieceCarrierNumber = workPieceCarrierNumber;
    }

    @Override
    public void enterIntoDB(JdbcTemplate jdbcTemplate) {
        jdbcTemplate.execute("insert into WPCs values(\""
                + valueStream + "\", \""
                + productionLine + "\", \""
                + productType + "\", "
                + workPieceCarrierNumber + ")");
    }

    public static void deleteWPC(String valueStream, String productionLine, String productType, int workPieceCarrierNumber, JdbcTemplate jdbcTemplate) {
        String where =
                " where valueStream=\"" + valueStream +
                        "\" and productionLine=\"" + productionLine +
                        "\" and productType=\"" + productType +
                        "\" and workPieceCarrierNumber=" + workPieceCarrierNumber + ";";
        jdbcTemplate.batchUpdate(
                "Delete From RepairTickets" + where,
                "Delete From WPCs" + where);
    }
}
