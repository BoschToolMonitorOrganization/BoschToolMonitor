package org.cofc.bosch.ToolMonitor.components.RepairCode;

import org.springframework.jdbc.core.JdbcTemplate;

public class RepairCode {

    String valueStream;
    String productionLine;
    String repairCategory;
    String repairDetail;

    public void enterIntoDB(JdbcTemplate jdbcTemplate) {
        jdbcTemplate.execute(String.format("Insert into RepairCodes values(\"%s\", \"%s\", \"%s\", \"%s\");",
                valueStream, productionLine, repairCategory, repairDetail));
    }

    public static void deleteFromDB(String valueStream, String productionLine, String repairCategory, String repairDetail, JdbcTemplate jdbcTemplate) {
        String where = String.format(" where " +
                        "valueStream=\"%s\" " +
                        "and productionLine=\"%s\" " +
                        "and repairCategory=\"%s\" " +
                        "and repairDetail=\"%s\";",
                valueStream, productionLine, repairCategory, repairDetail);
        jdbcTemplate.batchUpdate("Delete From RepairTickets" + where, "Delete From RepairCodes" + where);
    }

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
}
