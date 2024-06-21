package com.example.assign1;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class TableViewController implements Initializable {

    @FXML
    private TableView<country> table_country;

    @FXML
    private TableColumn<country, Integer> col_id;

    @FXML
    private TableColumn<country, String> col_name;

    @FXML
    private TableColumn<country, String> col_year;

    @FXML
    private TableColumn<country, Double> col_percentage;

    ObservableList<country> listM;

    int index = -1;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        col_id.setCellValueFactory(new PropertyValueFactory<country, Integer>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<country, String>("name"));
        col_year.setCellValueFactory(new PropertyValueFactory<country, String>("year"));
        col_percentage.setCellValueFactory(new PropertyValueFactory<country, Double>("percentage"));

        listM = DatabaseConnector.getDatacountry();
        table_country.setItems(listM);
    }
}


    private ObservableList<country> data;

    @FXML
    private void initialize() {

        data = FXCollections.observableArrayList();
        loadDataFromDatabase();
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        percentageColumn.setCellValueFactory(new PropertyValueFactory<>("percentage"));

        CountryData country = new CountryData();
        tableView.setItems(data);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
    //load data
    private void loadDataFromDatabase() {

        try (Connection connection = databaseConnector.connect();

             Statement statement = connection.createStatement();

             ResultSet resultSet = statement.executeQuery("SELECT id, name, year, percentage FROM countrydata")) {


            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String year = resultSet.getString("year");
                double percentage = resultSet.getDouble("percentage");

                data.add(new country(id, name, year, percentage));
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error loading data from database.");
            alert.showAndWait();
        }
    }

    @FXML
    private void showGraph() {
        ObservableList<country> selectedOSList = tableView.getSelectionModel().getSelectedItems();

        if (selectedOSList != null && !selectedOSList.isEmpty()) {
            try {

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("tableview.fxml"));

                Parent root = fxmlLoader.load();

                lineChartController lineChartController = fxmlLoader.getController();

                Map<String, XYChart.Series<String, Number>> osSeriesMap = new HashMap<>();

                for ( os : selectedOSList) {

                    nameSeriesMap.putIfAbsent(os.getOs(), new XYChart.Series<>());

                    nameSeriesMap.get(os.getOs()).getData().add(new XYChart.Data<>(os.getMonthYear(), os.getMarketShare()));
                }

                graphController.setData(osSeriesMap);

                Stage stage = new Stage();
                stage.setTitle("Market Share Graph");
                stage.setScene(new Scene(root));
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error loading graph view.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please select an item from the table.");
            alert.showAndWait();
        }
    }


    @FXML
    public void tableViewButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("tableview.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
}
    @FXML
    public void lineChartViewButton(ActionEvent actionEvent) throws IOException {
        ObservableList<country> selectedOSList = tableView.getSelectionModel().getSelectedItems();

        if (selectedOSList != null && !selectedOSList.isEmpty()) {
            try {

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("linechartview.fxml"));

                Parent root = fxmlLoader.load();

                lineChartController graphController = fxmlLoader.getController();

                Map<String, XYChart.Series<String, Number>> osSeriesMap = new HashMap<>();

                for (country name : selectedOSList) {

                    osSeriesMap.putIfAbsent(name.getName(), new XYChart.Series<>());

                    osSeriesMap.get(name.getName()).getData().add(new XYChart.Data<>(name.getYear(), name.getPercentage()));
                }

                lineChartController.setData(nameSeriesMap);

                Stage stage = new Stage();
                stage.setTitle("Renewable Energy Share");
                stage.setScene(new Scene(root));
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error loading graph view.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please select an item from the table.");
            alert.showAndWait();
        }
    }
}

