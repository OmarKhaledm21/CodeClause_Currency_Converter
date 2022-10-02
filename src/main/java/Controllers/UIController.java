package Controllers;

import API.API;
import API.IAPI;
import Database.Singleton;
import Model.CurrencyPair;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UIController implements Initializable {
    public TextField amountField;
    public TextField code1;
    public TextField code2;
    public Label code1_rate;
    public Label code2_rate;
    public Label code1_amt;
    public Label code2_amt;
    public MenuButton choose1;
    public MenuButton choose2;

    ArrayList<String> codes;
    CurrencyPair pair = new CurrencyPair();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("LOGGED");
        try {
            MenuInit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        code1.textProperty().addListener((observable, oldValue, newValue) -> {
            code1_text();
        });
        code2.textProperty().addListener((observable, oldValue, newValue) -> {
            code2_text();
        });

        ObservableList<CheckMenuItem> items1 = FXCollections.observableArrayList();

        ObservableList<CheckMenuItem> items2 = FXCollections.observableArrayList();


        for (int i = 0; i < codes.size(); i++) {
            CheckMenuItem menuItem = new CheckMenuItem(codes.get(i));
            menuItem.setOnAction((actionEvent) -> {
                choose1.setText(menuItem.getText().toUpperCase());
                code1.setText(menuItem.getText().toUpperCase());
            });
            items1.add(menuItem);
        }

        for (int i = 0; i < codes.size(); i++) {
            CheckMenuItem menuItem = new CheckMenuItem(codes.get(i));
            menuItem.setOnAction((actionEvent) -> {
                choose2.setText(menuItem.getText().toUpperCase());
                code2.setText(menuItem.getText().toUpperCase());
            });
            items2.add(menuItem);
        }

        choose2.getItems().addAll(items2);
        choose1.getItems().addAll(items1);

    }

    @FXML
    public void MenuInit() throws Exception {
        if (this.codes == null) {
            Singleton db_singleton = Singleton.getInstance();
            this.codes = db_singleton.getDB().getAllCodes();
            System.out.println(this.codes);
        }
    }


    @FXML
    protected void onConvertButtonClick() {
        pair.setFrom_code(code1.getText().toLowerCase());
        pair.setTo_code(code2.getText().toLowerCase());
        pair.setAmount(Double.parseDouble(amountField.getText()));

        IAPI currency_api = new API();
        try {
            currency_api.convertCurrency(pair);
        } catch (Exception e) {
            e.printStackTrace();
        }
        double r2 = pair.getTo_equivalent();
        r2 = Double.parseDouble(String.format("%.3f", r2));

        String rate1 = String.valueOf(pair.getFrom_equivalent()) + " " + code1.getText().toUpperCase();
        String rate2 = r2 + " " + code2.getText().toUpperCase();

        code1_rate.setText(rate1);
        code2_rate.setText(rate2);

        String amt1 = String.valueOf(pair.getAmount()) + " " + code1.getText().toUpperCase();

        double eqv = pair.getAmountEquivalent();
        eqv = Double.parseDouble(String.format("%.3f", eqv));

        String amt2 = eqv + " " + code2.getText().toUpperCase();

        code1_amt.setText(amt1);
        code2_amt.setText(amt2);
    }

    public void code1_text() {
        choose1.setText(code1.getText().toUpperCase());
    }

    public void code2_text() {
        choose2.setText(code2.getText().toUpperCase());
    }
}