package com.application.budgeter;

import java.io.IOException;
import javafx.fxml.FXML;

public class ExpenseController {

    @FXML
    private void switchToDashboard() throws IOException {
        App.setRoot("DashboardPage");
    }

    @FXML
    private void switchToBudget() throws IOException {
        App.setRoot("BudgetPage");
    }
}