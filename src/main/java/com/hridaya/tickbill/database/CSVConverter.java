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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;

public class CSVConverter {

    @SuppressWarnings("deprecation")
    private boolean exportFile(String sqlQuery, String csvFilePath) {
        try (PreparedStatement stmt = DbConnection.getConnection().prepareStatement(sqlQuery);
             BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath));
             ResultSet rs = stmt.executeQuery();
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

    @SuppressWarnings("deprecation")
    private boolean importFile(String sqlQuery, String csvFilePath, Savepoint savepoint) throws SQLException {
        try (PreparedStatement stmt = DbConnection.getConnection().prepareStatement(sqlQuery);
             FileReader reader = new FileReader(csvFilePath)) {
            CSVParser parser = new CSVParser(reader, CSVFormat.EXCEL.withHeader());

            for (CSVRecord record : parser) {
                for (int i = 0; i < record.size(); i++) {
                    stmt.setString(i + 1, record.get(i));
                }
                stmt.addBatch();
            }
            stmt.executeBatch();
            DbConnection.getConnection().commit();
            return true;
        } catch (IOException | SQLException ex) {
            Utils.showError("Failed importing file. " + ex.getMessage());
            Utils.showInfo("Rolling back to previous state.");
            DbConnection.getConnection().rollback(savepoint);
            return false;
        } finally {
            DbConnection.getConnection().setAutoCommit(true);
        }
    }

    public void importCSV(String sqlQuery, String csvFilePath, Savepoint savepoint) {
        try {
            boolean success = importFile(sqlQuery, csvFilePath, savepoint);
            if (success) {
                Utils.showInfo("File imported successfully.");
            }
        } catch (SQLException ex) {
            Utils.showError("Error during CSV import: " + ex.getMessage());
        }

    }
}
