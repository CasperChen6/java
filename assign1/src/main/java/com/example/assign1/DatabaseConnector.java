package com.example.assign1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.*;

public class DatabaseConnector {
    Connection conn = null;

    public static Connection ConnectDb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/countrydata");
            JOptionPane.showMessageDialog(null, "ConnectionEstablished");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    public static ObservableList<country> getDatacountry() {
        Connection conn = ConnectDb();
        ObservableList<country> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from countrydata");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                list.add(new country(Integer.parseInt(rs.getString("id")), rs.getString("name"), rs.getString("year"), rs.getDouble("percentage")));
            }
        } catch (Exception e) {
        }
        return list;
    }
}

