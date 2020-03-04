package org.cofc.bosch.ToolMonitor.components;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkPieceCarrierMapper implements RowMapper<WorkPieceCarrier> {

    @Override
    public WorkPieceCarrier mapRow(ResultSet rs, int rowNum) throws SQLException {

        WorkPieceCarrier carrier = new WorkPieceCarrier();

        carrier.setWorkPieceCarrierNumber(rs.getInt("workpieceCarrierNumber"));
        carrier.setValueStream(rs.getString("valueStream"));
        carrier.setProductionLine(rs.getString("productionLine"));
        carrier.setProductType(rs.getString("productType"));

        return carrier;
    }
}