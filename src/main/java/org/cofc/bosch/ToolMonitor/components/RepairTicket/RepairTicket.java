package org.cofc.bosch.ToolMonitor.components.RepairTicket;

import org.cofc.bosch.ToolMonitor.components.RepairCode.RepairCode;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RepairTicket extends RepairCode {

    protected String productType;
    protected int workPieceCarrierNumber;
    protected String extraInfo;
    protected String userEntry;
    protected String timeStampOpened;
    protected String timeStampClosed;
    protected String repairDetails;

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getTimeStampClosed() {
        return timeStampClosed;
    }

    public void setTimeStampClosed(String timeStampClosed) {
        this.timeStampClosed = timeStampClosed;
    }

    public String getRepairDetails() {
        return repairDetails;
    }

    public void setRepairDetails(String repairDetails) {
        this.repairDetails = repairDetails;
    }

    public int getWorkPieceCarrierNumber() {
        return workPieceCarrierNumber;
    }

    public void setWorkPieceCarrierNumber(int workPieceCarrierNumber) {
        this.workPieceCarrierNumber = workPieceCarrierNumber;
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

    @Override
    public void enterIntoDB(JdbcTemplate jdbcTemplate) {
        jdbcTemplate.execute(
                "INSERT INTO RepairTickets (valueStream, productionLine, productType, workPieceCarrierNumber, repairCategory, repairDetail, extraInfo, userEntry, timeStampOpened) values (?, ?, ?, ?, ?, ?, ?, ?, ?)",
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

    public static RepairTicket getRepairTicket(JdbcTemplate jdbcTemplate, String valueStream, String productionLine,
                                               String productType, int workPieceCarrierNumber,
                                               String repairCategory, String repairDetail,
                                               String userEntry, String timeStampOpened) {
        return jdbcTemplate.query(String.format("select * from RepairTickets where " +
                        "valueStream=\"%s\" and " +
                        "productionLine=\"%s\" and " +
                        "productType=\"%s\" and " +
                        "workPieceCarrierNumber=%d and " +
                        "repairCategory=\"%s\" and " +
                        "repairDetail=\"%s\" and " +
                        "userEntry=\"%s\" and " +
                        "timeStampOpened=\"%s\";",
                valueStream, productionLine, productType, workPieceCarrierNumber, repairCategory,
                repairDetail, userEntry, timeStampOpened),
                new RepairTicketMapper()).get(0);
    }

    public void closeSelf(JdbcTemplate jdbcTemplate) {
        jdbcTemplate.execute(String.format("UPDATE RepairTickets " +
                        "SET timeStampClosed=\"%s\", repairDetails=\"%s\"" +
                        " where " +
                        "valueStream=\"%s\" and " +
                        "productionLine=\"%s\" and " +
                        "productType=\"%s\" and " +
                        "workPieceCarrierNumber=%d and " +
                        "repairCategory=\"%s\" and " +
                        "repairDetail=\"%s\" and " +
                        "userEntry=\"%s\" and " +
                        "timeStampOpened=\"%s\";", timeStampClosed, repairDetails, valueStream, productionLine, productType,
                workPieceCarrierNumber, repairCategory, repairDetail, userEntry, timeStampOpened));

    }

    public static void deleteRepairTicket(JdbcTemplate jdbcTemplate, String valueStream, String productionLine, String productType,
                                          int workPieceCarrierNumber, String repairCategory, String repairDetail,
                                          String userEntry, String timeStampOpened) {
        jdbcTemplate.execute("DELETE FROM RepairTickets WHERE valueStream=\"" + valueStream + "\" and productionLine=\"" + productionLine +
                "\" and productType=\"" + productType + "\" and workPieceCarrierNumber=\"" + workPieceCarrierNumber +
                "\" and repairCategory=\"" + repairCategory + "\" and repairDetail=\"" + repairDetail +
                "\" and userEntry=\"" + userEntry +
                "\" and timeStampOpened=\"" + timeStampOpened + "\";");
    }


}





