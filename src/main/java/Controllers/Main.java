package Controllers;

import API.API;
import Controllers.ConverterWindow;
import Controllers.WindowLoader;
import Database.SQLiteDatabase;
import Database.Singleton;
import Model.CurrencyPair;

public class Main {
    public static void main(String[] args) throws Exception {
        Singleton db_singleton = Singleton.getInstance();
        WindowLoader.initLoaders();
        ConverterWindow window = new ConverterWindow();
        window.view();
    }
}
