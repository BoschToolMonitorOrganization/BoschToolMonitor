package org.cofc.bosch.ToolMonitor.components.RepairCode;

import org.cofc.bosch.ToolMonitor.components.WorkPieceCarrier.WorkPieceCarrier;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RepairCodeMapper implements RowMapper<RepairCode> {

    @Override
    public RepairCode mapRow(ResultSet rs, int rowNum) throws SQLException {
        RepairCode code = new RepairCode();

        code.setValueStream(rs.getString("valueStream"));
        code.setProductionLine(rs.getString("productionLine"));
        code.setRepairCategory(rs.getString("repairCategory"));
        code.setRepairDetail(rs.getString("repairDetail"));

        return code;
    }
}
