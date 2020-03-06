package org.cofc.bosch.ToolMonitor.components.WPCCombo;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WPCComboRowMapper implements RowMapper<WPCCombo> {

    @Override
    public WPCCombo mapRow(ResultSet rs, int rowNum) throws SQLException {

        WPCCombo combo = new WPCCombo();

        combo.setValueStream(rs.getString("valueStream"));
        combo.setProductionLine(rs.getString("productionLine"));
        combo.setProductType(rs.getString("productType"));

        return combo;
    }
}
