package org.cofc.bosch.ToolMonitor.components;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkPieceCarrierMapper implements RowMapper<WorkPieceCarrier> {

    @Override
    public WorkPieceCarrier mapRow(ResultSet rs, int rowNum) throws SQLException {

        WorkPieceCarrier carrier = new WorkPieceCarrier();

        carrier.setAuthor(rs.getString("author"));
        carrier.setRevisionNumber(rs.getString("revisionNumber"));
        carrier.setRevisionReason(rs.getString("revisionReason"));
        carrier.setFileType(rs.getString("fileType"));
        carrier.setWorkPieceCarrierNumber(rs.getString("workpieceCarrierNumber"));
        carrier.setValueStream(rs.getString("valueStream"));
        carrier.setProductionLine(rs.getString("productionLine"));
        carrier.setProductType(rs.getString("productType"));
        carrier.setReasonForChange(rs.getString("reasonForChange"));
        carrier.setReasonCategory(rs.getString("reasonCategory"));
        carrier.setToolLifeAchieved(rs.getBoolean("toolLifeAchieved"));
        carrier.setLocationRepairTicket(rs.getString("locationRepairTicket"));
        carrier.setDownTimeImpact(rs.getString("downTimeImpact"));

        return carrier;
    }
}