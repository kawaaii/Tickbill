package com.hridaya.tickbill.database;

import com.hridaya.tickbill.view.Utils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CSVConverter {

    private boolean exportFile(String sqlQuery, String csvFilePath) {
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlQuery);
             ResultSet rs = stmt.executeQuery();
             BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath));
             CSVPrinter printer = new CSVPrinter(writer, CSVFormat.EXCEL.withHeader(rs))) {

            printer.printRecords(rs);
            return true;
        } catch (IOException | SQLException ex) {
            Utils.showError("Error during CSV export: " + ex.getMessage());
            return false;
        }
    }

    public void exportAsCSV(String sqlQuery, String csvFilePath) {
        boolean success = exportFile(sqlQuery, csvFilePath);
        if (success) {
            Utils.showInfo("File exported successfully.");
        }
    }

    private boolean importFile(String sqlQuery, String csvFilePath) {
        try (Connection conn = DbConnection.getConnection();
             FileReader reader = new FileReader(csvFilePath);
             CSVParser parser = new CSVParser(reader, CSVFormat.EXCEL.withHeader());
             PreparedStatement stmt = conn.prepareStatement(sqlQuery)) {

            for (CSVRecord record : parser) {
                for (int i = 0; i < record.size(); i++) {
                    stmt.setString(i + 1, record.get(i));
                }
                stmt.addBatch();
            }
            stmt.executeBatch();
            return true;
        } catch (IOException | SQLException ex) {
            Utils.showError("Error during CSV import: " + ex.getMessage());
            // since we delete all the existing datas
            Utils.showError("CONTACT ADMIN. ALL DATAS ARE WIPED OUT.");
            return false;
        }
    }

    public void importCSV(String sqlQuery, String csvFilePath) {
        boolean success = importFile(sqlQuery, csvFilePath);
        if (success) {
            Utils.showInfo("File imported successfully.");
        }
    }
}
