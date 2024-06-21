package com.example.assign1;

import com.mysql.cj.jdbc.CallableStatementWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class country {
    private Integer id;
    private String name;
    private String year;
    private Double percentage;

    public country(Integer id, String name, String year, double percentage) {
        this.id = id;
        this.name = name;
        this.year = this.year;
        this.percentage = this.percentage;
    }

    // Getter methods
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getYear() {
        return year;
    }

    public Double getPercentage() {
        return percentage;
    }

    // Setter methods
    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }
}
