package org.cofc.bosch.ToolMonitor.components.WPCFile;

import org.cofc.bosch.ToolMonitor.components.WPCCombo.WPCCombo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WPCFile extends WPCCombo {

    protected String author;
    protected int revisionNumber;
    protected String revisionReason;
    protected String fileType;
    protected String fileName;
    protected MultipartFile fileData;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getRevisionNumber() {
        return revisionNumber;
    }

    public void setRevisionNumber(int revisionNumber) {
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public MultipartFile getFileData() {
        return fileData;
    }

    public void setFileData(MultipartFile fileData) {
        this.fileData = fileData;
    }

    @Override
    public void enterIntoDB(JdbcTemplate jdbcTemplate) {
        jdbcTemplate.execute(
                "INSERT INTO WPCFiles (valueStream, productionLine, productType, author, revisionNumber, revisionReason, fileType, fileName, fileData) values (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                new PreparedStatementCallback<Boolean>() {
                    @Override
                    public Boolean doInPreparedStatement(PreparedStatement statement)
                            throws SQLException {
                        boolean retVal = true;
                        statement.setString(1, valueStream);
                        statement.setString(2, productionLine);
                        statement.setString(3, productType);
                        statement.setString(4, author);
                        statement.setInt(5, revisionNumber);
                        statement.setString(6, revisionReason);
                        statement.setString(7, fileType);
                        statement.setString(8, fileData.getOriginalFilename());
                        try {
                            statement.setBlob(9, fileData.getInputStream());
                        } catch (IOException e) {
                            System.out.println(e.getCause() + "\n" + e.getMessage() + "\n");
                            retVal = false;
                        }
                        return retVal && statement.execute();
                    }
                });
    }

    public static void deleteWPCFile(JdbcTemplate jdbcTemplate, String valueStream, String productionLine, String productType,
                                     String author, int revisionNumber, String fileType, String fileName) {
        jdbcTemplate.execute("Delete From WPCFiles where valueStream=\"" +
                valueStream + "\" and productionLine=\"" + productionLine + "\" and productType=\"" +
                productType + "\" and author=\"" + author + "\" and revisionNumber=" + revisionNumber +
                " and fileType=\"" + fileType + "\" and fileName=\"" + fileName + "\";");
    }


}
