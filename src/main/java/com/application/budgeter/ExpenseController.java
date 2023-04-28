package com.application.budgeter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ExpenseController implements Initializable {

    // initialize tableview from fxml
    @FXML private TableView<Expense> expenseTable;
    // initialize table columns from fxml
    @FXML private TableColumn<Expense, String> expenseColumn;
    @FXML private TableColumn<Expense, String> categoryColumn;
    @FXML private TableColumn<Expense, String> dateColumn;
    @FXML private TableColumn<Expense, String> costColumn;

    @FXML private MenuButton totalMenu;
    @FXML private Label total;

    @FXML private Button addExpenseButton;

    @FXML private Button saveExpenseButton;

    @FXML private TextField addExpenseField;
    @FXML private TextField addCategoryField;
    @FXML private TextField addDateField;
    @FXML private TextField addCostField;


    // dummy data (replace with pulling from file)
    ObservableList<Expense> list = FXCollections.observableArrayList(
        new Expense("hotdog", "food", "03/03/2024", "$1.50"),
        new Expense("notdog", "food", "03/04/2023", "$2.50"),
        new Expense("hotdog", "food", "03/03/2023", "$1.50"),
        new Expense("notdog", "food", "03/04/2023", "$2.50"),
        new Expense("hotdog", "food", "03/03/2023", "$1.50"),
        new Expense("notdog", "food", "03/04/2023", "$2.50"),
        new Expense("hotdog", "food", "03/03/2023", "$1.50"),
        new Expense("notdog", "food", "03/04/2023", "$2.50"),
        new Expense("hotdog", "food", "03/03/2023", "$1.50"),
        new Expense("notdog", "food", "03/04/2023", "$2.50"),
        new Expense("hotdog", "food", "03/03/2023", "$1.50"),
        new Expense("notdog", "food", "03/04/2023", "$2.50"),
        new Expense("hotdog", "food", "03/03/2023", "$1.50"),
        new Expense("notdog", "food", "03/04/2023", "$2.50"),
        new Expense("hotdog", "food", "03/03/2023", "$1.50"),
        new Expense("notdog", "food", "03/04/2023", "$2.50"),
        new Expense("hotdog", "food", "03/03/2023", "$1.50"),
        new Expense("notdog", "food", "03/04/2023", "$2.50"),
        new Expense("hotdog", "food", "03/03/2023", "$1.50"),
        new Expense("notdog", "food", "03/02/2022", "$2.50")
    );

    // initialize method (runs when ExpenseController is created)
    @Override
    public void initialize(java.net.URL arg0, java.util.ResourceBundle arg1) {
        // set cell value factory for each column
        expenseColumn.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("expense"));
        categoryColumn.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("category"));
        dateColumn.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("date"));
        costColumn.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("cost"));

        // set tableview resize policy to it will not resize columns past the width of the tableview
        expenseTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // align columns center
        expenseColumn.setStyle("-fx-alignment: CENTER;");
        categoryColumn.setStyle("-fx-alignment: CENTER;");
        dateColumn.setStyle("-fx-alignment: CENTER;");
        costColumn.setStyle("-fx-alignment: CENTER;");

        // add columns to tableview
        expenseTable.getColumns().addAll(expenseColumn, categoryColumn, dateColumn, costColumn);

        // add dummy data to tableview
        expenseTable.setItems(list);

        // calcluate all costs from list
        double totalCost = 0;
        // adds all costs from list to totalCost removing the $ sign
        for (Expense expense : list) {
            totalCost += Double.parseDouble(expense.getCost().substring(1));
        }


        // set totalMenu to All Time
        totalMenu.setText("All Time");
        // set total label to totalCost
        total.setText("$" + totalCost);
    }

    

    // adjust font size depending on length of text
    // if months = 0 then calculate all time
    public double calcluateTotal(int months) {

        double totalCost = 0;

        if (months == 0) {
            // adds all costs from list to totalCost removing the $ sign
            for (Expense expense : list) {
                totalCost += Double.parseDouble(expense.getCost().substring(1));
            }
        }
        else // if months != 0 then calculate past months
        {
            // get current date
            LocalDate currentDate = LocalDate.now();
            // get date from months ago
            LocalDate pastDate = currentDate.minusMonths(months);
            // 

            // adds all costs from list to totalCost removing the $ sign
            for (Expense expense : list) {
                // convert expense date to localDate
                LocalDate expenseDate = LocalDate.parse(expense.getDate(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                if (expenseDate.isAfter(pastDate) && expenseDate.isBefore(currentDate) || expenseDate.isEqual(currentDate))
                {
                    totalCost += Double.parseDouble(expense.getCost().substring(1));
                }
            }
        }

        // string format 2 decimal places double parse
        totalCost = Double.parseDouble(String.format("%.2f", totalCost));

        return totalCost;
    }

    // changes menu button text to selected menu item
    public void changeMenuButton(ActionEvent event) {
        MenuItem menuItem = (MenuItem) event.getSource();
        totalMenu.setText(menuItem.getText());
        switch (menuItem.getText()) {
            case "Past 3 Months":
                total.setText("$" + calcluateTotal(3));
                break;
            case "Past 6 Months":
                total.setText("$" + calcluateTotal(6));
                break;
            case "Past 12 Months":
                total.setText("$" + calcluateTotal(12));
                break;
            case "All Time":
                total.setText("$" + calcluateTotal(0));
                break;
        }
    }
    
    
    // add data from text fields to tableview
    public void addExpense() {

    }

    // save data from tableview to file
    // save tableview to file or save data structure to file??
    public void saveExpenses() {

    }
}
