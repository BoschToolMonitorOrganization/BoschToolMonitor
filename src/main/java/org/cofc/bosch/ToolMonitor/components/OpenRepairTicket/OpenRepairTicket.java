package org.cofc.bosch.ToolMonitor.components.OpenRepairTicket;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OpenRepairTicket {
    String valueStream;
    String productionLine;
    String productType;
    int workPieceCarrierNumber;
    String repairCategory;
    String repairDetail;
    String extraInfo;
    String userEntry;
    String timeStampOpened;

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

    public int getWorkPieceCarrierNumber() {
        return workPieceCarrierNumber;
    }

    public void setWorkPieceCarrierNumber(int workPieceCarrierNumber) {
        this.workPieceCarrierNumber = workPieceCarrierNumber;
    }

    public String getRepairCategory() {
        return repairCategory;
    }

    public void setRepairCategory(String repairCategory) {
        this.repairCategory = repairCategory;
    }

    public String getRepairDetail() {
        return repairDetail;
    }

    public void setRepairDetail(String repairDetail) {
        this.repairDetail = repairDetail;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public String getUserEntry() {
        return userEntry;
    }

    public void setUserEntry(String userEntry) {
        this.userEntry = userEntry;
    }

    public String getTimeStampOpened() {
        return timeStampOpened;
    }

    public void setTimeStampOpened(String timeStampOpened) {
        this.timeStampOpened = timeStampOpened;
    }

    public void enterOpenRepairTicketIntoDatabase(JdbcTemplate jdbcTemplate) {
        jdbcTemplate.execute(
                "INSERT INTO OpenRepairTicket (valueStream, productionLine, productType, workPieceCarrierNumber, repairCategory, repairDetail, extraInfo, userEntry, timeStampOpened) values (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                new PreparedStatementCallback<Boolean>() {
                    @Override
                    public Boolean doInPreparedStatement(PreparedStatement statement)
                            throws SQLException {
                        boolean retVal = true;
                        statement.setString(1, valueStream);
                        statement.setString(2, productionLine);
                        statement.setString(3, productType);
                        statement.setInt(4, workPieceCarrierNumber);
                        statement.setString(5, repairCategory);
                        statement.setString(6, repairDetail);
                        statement.setString(7, extraInfo);
                        statement.setString(8, userEntry);
                        statement.setString(9, timeStampOpened);

                        return retVal && statement.execute();
                    }
                });

    }

    public static void deleteOpenRepairTicket(JdbcTemplate jdbcTemplate, String valueStream, String productionLine, String productType,
                                              int workPieceCarrierNumber, String repairCategory, String repairDetail, String extraInfo,
                                              String userEntry, String timeStampOpened) {
        jdbcTemplate.execute("DELETE FROM OpenRepairTicket WHERE valueStream=\"" + valueStream + "\" and productionLine=\"" + productionLine +
                            "\" and productType=\"" + productType + "\" and workPieceCarrierNumber=\"" + workPieceCarrierNumber +
                            "\" and repairCategory=\"" + repairCategory + "\" and repairDetail=\"" + repairDetail +
                            "\" and extraInfo=\"" + extraInfo + "\" and userEntry=\"" + userEntry +
                            "\" and timeStampOpened=\"" + timeStampOpened + ";");
    }


}





