package com.hridaya.tickbill.database;

import com.hridaya.tickbill.view.Utils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CSVExporter {

    private boolean exportFile(String sqlQuery, String csvFilePath) {
        Connection conn = DbConnection.getConnection();
        boolean isSuccess = true;
        PreparedStatement stmt;
        ResultSet rs;
        BufferedWriter writer;

        try {
            stmt = conn.prepareStatement(sqlQuery);
            rs = stmt.executeQuery();

            writer = new BufferedWriter(new FileWriter(csvFilePath));

            CSVPrinter printer = new CSVPrinter(writer, CSVFormat.EXCEL.withHeader(rs));
            printer.printRecords(rs);
            printer.close(true);
            writer.close();
            rs.close();
        } catch (IOException | SQLException ex) {
            System.err.print(ex.getMessage());
            isSuccess = false;
        }
        return isSuccess;
    }

    public void exportAsCSV(String sqlQuery, String csvFilePath) {
        CSVExporter exporter = new CSVExporter();
        boolean success = exporter.exportFile(sqlQuery, csvFilePath);
        if (success) {
            Utils.showInfo("File exported.");
        }
    }

}
