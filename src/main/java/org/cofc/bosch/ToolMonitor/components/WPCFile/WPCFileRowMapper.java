package org.cofc.bosch.ToolMonitor.components.WPCFile;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WPCFileRowMapper implements RowMapper<WPCFile> {

    @Override
    public WPCFile mapRow(ResultSet rs, int rowNum) throws SQLException {

        WPCFile file = new WPCFile();
        file.setValueStream(rs.getString("valueStream"));
        file.setProductionLine(rs.getString("productionLine"));
        file.setProductType(rs.getString("productType"));
        file.setAuthor(rs.getString("author"));
        file.setRevisionNumber(rs.getInt("revisionNumber"));
        file.setRevisionReason(rs.getString("revisionReason"));
        file.setFileType(rs.getString("fileType"));
        file.setFileName(rs.getString("fileName"));

        return file;
    }
}
