package org.cofc.bosch.ToolMonitor.components.OpenRepairTicket;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OpenRepairTicketMapper implements RowMapper<OpenRepairTicket> {

    @Override
    public OpenRepairTicket mapRow(ResultSet rs, int rowNum) throws SQLException {

        OpenRepairTicket openTicket = new OpenRepairTicket();

        openTicket.setValueStream(rs.getString("valueStream"));
        openTicket.setProductionLine(rs.getString("productionLine"));
        openTicket.setProductType(rs.getString("productType"));
        openTicket.setWorkPieceCarrierNumber(rs.getInt("workPieceCarrierNumber"));
        openTicket.setRepairCategory(rs.getString("repairCategory"));
        openTicket.setRepairDetail(rs.getString("repairDetail"));
        openTicket.setExtraInfo(rs.getString("extraInfo"));
        openTicket.setUserEntry(rs.getString("userEntry"));
        openTicket.setTimeStampOpened(rs.getString("timeStampOpened"));

        return openTicket;
    }
}
