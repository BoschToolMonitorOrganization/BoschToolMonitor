package org.cofc.bosch.ToolMonitor.components.RepairCode;

import org.cofc.bosch.ToolMonitor.components.ProductionLine;
import org.springframework.jdbc.core.JdbcTemplate;

public class RepairCode extends ProductionLine {

    protected String repairCategory;
    protected String repairDetail;

    @Override
    public void enterIntoDB(JdbcTemplate jdbcTemplate) {
        jdbcTemplate.execute(String.format("Insert into RepairCodes values(\"%s\", \"%s\", \"%s\", \"%s\");",
                valueStream, productionLine, repairCategory, repairDetail));
    }

    public static void deleteRepairCode(String valueStream, String productionLine, String repairCategory, String repairDetail, JdbcTemplate jdbcTemplate) {
        String where = String.format(" where " +
                        "valueStream=\"%s\" " +
                        "and productionLine=\"%s\" " +
                        "and repairCategory=\"%s\" " +
                        "and repairDetail=\"%s\";",
                valueStream, productionLine, repairCategory, repairDetail);
        jdbcTemplate.batchUpdate("Delete From RepairTickets" + where, "Delete From RepairCodes" + where);
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
