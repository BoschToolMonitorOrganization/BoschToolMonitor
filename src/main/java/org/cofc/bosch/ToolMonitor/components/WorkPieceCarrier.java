package org.cofc.bosch.ToolMonitor.components;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class WorkPieceCarrier {
    private String author;
    private String revisionNumber;
    private String revisionReason;
    private String fileType;

    private MultipartFile[] componentsList;
    private String workPieceCarrierNumber;
    private String valueStream;
    private String productionLine;
    private String productType;

    private MultipartFile[] drawingLibrary;

    private String reasonForChange;
    private String reasonCategory;
    private boolean toolLifeAchieved;
    private String locationRepairTicket;
    private String downTimeImpact;

    private MultipartFile[] PDFs;
    private MultipartFile[] CADs;
    private MultipartFile JPEG;

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getRevisionNumber() {
        return revisionNumber;
    }

    public void setRevisionNumber(String revisionNumber) {
        this.revisionNumber = revisionNumber;
    }

    public String getRevisionReason() {
        return revisionReason;
    }

    public void setRevisionReason(String revisionReason) {
        this.revisionReason = revisionReason;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public MultipartFile[] getComponentsList() {
        return componentsList;
    }

    public void setComponentsList(MultipartFile[] componentsList) {
        this.componentsList = componentsList;
    }

    public String getWorkPieceCarrierNumber() {
        return workPieceCarrierNumber;
    }

    public void setWorkPieceCarrierNumber(String workPieceCarrierNumber) {
        this.workPieceCarrierNumber = workPieceCarrierNumber;
    }

    public MultipartFile[] getDrawingLibrary() {
        return drawingLibrary;
    }

    public void setDrawingLibrary(MultipartFile[] drawingLibrary) {
        this.drawingLibrary = drawingLibrary;
    }

    public String getReasonForChange() {
        return reasonForChange;
    }

    public void setReasonForChange(String reasonForChange) {
        this.reasonForChange = reasonForChange;
    }

    public String getReasonCategory() {
        return reasonCategory;
    }

    public void setReasonCategory(String reasonCategory) {
        this.reasonCategory = reasonCategory;
    }

    public boolean isToolLifeAchieved() {
        return toolLifeAchieved;
    }

    public void setToolLifeAchieved(boolean toolLifeAchieved) {
        this.toolLifeAchieved = toolLifeAchieved;
    }

    public String getLocationRepairTicket() {
        return locationRepairTicket;
    }

    public void setLocationRepairTicket(String locationRepairTicket) {
        this.locationRepairTicket = locationRepairTicket;
    }

    public String getDownTimeImpact() {
        return downTimeImpact;
    }

    public void setDownTimeImpact(String downTimeImpact) {
        this.downTimeImpact = downTimeImpact;
    }

    public MultipartFile[] getPDFs() {
        return PDFs;
    }

    public void setPDFs(MultipartFile[] PDFs) {
        this.PDFs = PDFs;
    }

    public MultipartFile[] getCADs() {
        return CADs;
    }

    public void setCADs(MultipartFile[] CADs) {
        this.CADs = CADs;
    }

    public MultipartFile getJPEG() {
        return JPEG;
    }

    public void setJPEG(MultipartFile JPEG) {
        this.JPEG = JPEG;
    }

    public static void enterWPCIntoDatabase(WorkPieceCarrier carrier, JdbcTemplate jdbcTemplate) {
        jdbcTemplate.execute("insert into WorkPieceCarriers values(\""
                + carrier.getAuthor() + "\", \""
                + carrier.getRevisionNumber() + "\", \""
                + carrier.getRevisionReason() + "\", \""
                + carrier.getFileType() + "\", \""
                + carrier.getWorkPieceCarrierNumber() + "\", \""
                + carrier.getValueStream() + "\", \""
                + carrier.getProductionLine() + "\", \""
                + carrier.getProductType() + "\", \""
                + carrier.getReasonForChange() + "\", \""
                + carrier.getReasonCategory() + "\", "
                + carrier.isToolLifeAchieved() + ", \""
                + carrier.getLocationRepairTicket() + "\", \""
                + carrier.getDownTimeImpact() + "\");");

        String sql = "INSERT INTO WPCFiles (workPieceCarrierNumber, valueStream, productionLine, productType, fileName, fileType, data) values (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.execute(sql, new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement statement)
                    throws SQLException {
                try {
                    if (carrier.getJPEG() != null && !carrier.getJPEG().isEmpty()) {
                        statement.setString(1, carrier.getWorkPieceCarrierNumber());
                        statement.setString(2, carrier.getValueStream());
                        statement.setString(3, carrier.getProductionLine());
                        statement.setString(4, carrier.getProductType());
                        statement.setString(5, carrier.getJPEG().getOriginalFilename());
                        statement.setString(6, "jpeg");
                        statement.setBlob(7, carrier.getJPEG().getInputStream());
                        statement.addBatch();
                    }

                    if (carrier.getComponentsList() != null) {
                        for (MultipartFile file : carrier.getComponentsList()) {
                            if (!file.isEmpty()) {
                                statement.setString(1, carrier.getWorkPieceCarrierNumber());
                                statement.setString(2, carrier.getValueStream());
                                statement.setString(3, carrier.getProductionLine());
                                statement.setString(4, carrier.getProductType());
                                statement.setString(5, file.getOriginalFilename());
                                statement.setString(6, "component");
                                statement.setBlob(7, file.getInputStream());
                                statement.addBatch();
                            }
                        }
                    }

                    if (carrier.getDrawingLibrary() != null) {
                        for (MultipartFile file : carrier.getDrawingLibrary()) {
                            if (!file.isEmpty()) {
                                statement.setString(1, carrier.getWorkPieceCarrierNumber());
                                statement.setString(2, carrier.getValueStream());
                                statement.setString(3, carrier.getProductionLine());
                                statement.setString(4, carrier.getProductType());
                                statement.setString(5, file.getOriginalFilename());
                                statement.setString(6, "drawing");
                                statement.setBlob(7, file.getInputStream());
                                statement.addBatch();
                            }
                        }
                    }
                    if (carrier.getPDFs() != null) {
                        for (MultipartFile file : carrier.getPDFs()) {
                            if (!file.isEmpty()) {
                                statement.setString(1, carrier.getWorkPieceCarrierNumber());
                                statement.setString(2, carrier.getValueStream());
                                statement.setString(3, carrier.getProductionLine());
                                statement.setString(4, carrier.getProductType());
                                statement.setString(5, file.getOriginalFilename());
                                statement.setString(6, "pdf");
                                statement.setBlob(7, file.getInputStream());
                                statement.addBatch();
                            }
                        }
                    }

                    if (carrier.getCADs() != null) {
                        for (MultipartFile file : carrier.getCADs()) {
                            if (!file.isEmpty()) {
                                statement.setString(1, carrier.getWorkPieceCarrierNumber());
                                statement.setString(2, carrier.getValueStream());
                                statement.setString(3, carrier.getProductionLine());
                                statement.setString(4, carrier.getProductType());
                                statement.setString(5, file.getOriginalFilename());
                                statement.setString(6, "cad");
                                statement.setBlob(7, file.getInputStream());
                                statement.addBatch();
                            }
                        }
                    }
                } catch (IOException e) {
                    System.out.println(e.getCause() + "\n" + e.getMessage() + "\n");
                }

                int[] success = statement.executeBatch();
                boolean retVal = true;
                for (int curr : success) {
                    if (curr == Statement.EXECUTE_FAILED) {
                        retVal = false;
                    }
                }

                return retVal;
            }
        });
    }
}
