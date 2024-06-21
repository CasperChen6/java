package com.example.assign1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Map;
import java.util.ResourceBundle;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class lineChartController {
    @FXML
    private LineChart<String, Number> lineChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;
    private String year;
    private Number percentage;


    public void setData(Map<String, XYChart.Series<String, Number>> nameSeriesMap) {

        lineChart.getData().clear();

        for (Map.Entry<String, XYChart.Series<String, Number>> entry : nameSeriesMap.entrySet()) {

            XYChart.Series<String, Number> series = entry.getValue();

            series.setName(entry.getKey());

            lineChart.getData().add(series);
        }
    }

    @FXML
    protected void goToMain(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("linechartview.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}