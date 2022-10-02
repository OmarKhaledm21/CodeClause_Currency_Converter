package Database;

import API.API;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SQLiteDatabase implements IDB {
    private static Connection dbConnection;
    private final String url = "jdbc:sqlite:";
    private final String dbPath = "db/Currencies.db";
    private static Statement statement;

    public SQLiteDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
            File file = new File(this.dbPath);
            boolean exist = file.exists();
            dbConnection = DriverManager.getConnection(this.url + this.dbPath);
            statement = dbConnection.createStatement();
            if (!exist) {
                CreateDB();
                System.out.println("Database Created.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void CreateDB() throws Exception {
        API api = new API();
        HashMap<String, String> code_name_pairs = api.getCurrencies();

        String currenciesTable = "CREATE TABLE Currencies(" +
                "name VARCHAR(50)," +
                "code VARCHAR(50) PRIMARY KEY" +
                ");";
        try {
            statement.execute(currenciesTable);
            for (Map.Entry<String, String> entry : code_name_pairs.entrySet()) {
                insertCurrencies(entry.getKey(), entry.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertCurrencies(String code, String name) {
        String query = "INSERT INTO Currencies(code, name) VALUES(?,?);";
        try {
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
            preparedStatement.setString(1, code);
            preparedStatement.setString(2, name);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public HashMap<String, String> getCurrencyList() {
        HashMap<String,String> currency_list = new HashMap<>();
        String query = "SELECT * FROM Currencies;";
        try {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                currency_list.put(resultSet.getString("code"),resultSet.getString("name"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return currency_list;
    }

    @Override
    public String getName(String code) {
        String query = "SELECT name FROM Currencies WHERE code = ?;";
        try {
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
            preparedStatement.setString(1,code);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return resultSet.getString("name");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<String> getAllCodes() throws Exception {
        ArrayList<String> code_list = new ArrayList<>();
        String query = "SELECT code FROM Currencies;";
        try {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                code_list.add(resultSet.getString("code"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return code_list;
    }

    @Override
    public ArrayList<String> getAllNames() throws Exception {
        ArrayList<String> name_list = new ArrayList<>();
        String query = "SELECT name FROM Currencies;";
        try {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name_list.add(resultSet.getString("name"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return name_list;
    }

    @Override
    public String getCode(String name) {
        String query = "SELECT code FROM Currencies WHERE name = ?;";
        try {
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
            preparedStatement.setString(1,name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return resultSet.getString("code");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean codeExists(String code) {
        String query = "SELECT * FROM Currencies WHERE code = ?;";
        try {
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
            preparedStatement.setString(1,code);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean nameExists(String name) {
        String query = "SELECT * FROM Currencies WHERE name = ?;";
        try {
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
            preparedStatement.setString(1,name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
