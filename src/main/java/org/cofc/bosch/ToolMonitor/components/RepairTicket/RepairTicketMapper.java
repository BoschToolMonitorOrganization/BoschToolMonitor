package org.cofc.bosch.ToolMonitor.components.RepairTicket;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RepairTicketMapper implements RowMapper<RepairTicket> {

    @Override
    public RepairTicket mapRow(ResultSet rs, int rowNum) throws SQLException {

        RepairTicket openTicket = new RepairTicket();

        openTicket.setValueStream(rs.getString("valueStream"));
        openTicket.setProductionLine(rs.getString("productionLine"));
        openTicket.setProductType(rs.getString("productType"));
        openTicket.setWorkPieceCarrierNumber(rs.getInt("workPieceCarrierNumber"));
        openTicket.setRepairCategory(rs.getString("repairCategory"));
        openTicket.setRepairDetail(rs.getString("repairDetail"));
        openTicket.setExtraInfo(rs.getString("extraInfo"));
        openTicket.setUserEntry(rs.getString("userEntry"));
        openTicket.setTimeStampOpened(rs.getString("timeStampOpened"));
        openTicket.setTimeStampClosed(rs.getString("timeStampClosed"));
        openTicket.setRepairDetails(rs.getString("repairDetails"));

        return openTicket;
    }
}
