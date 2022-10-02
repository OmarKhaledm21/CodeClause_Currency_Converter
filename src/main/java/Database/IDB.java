package Database;

import java.util.ArrayList;
import java.util.HashMap;

public interface IDB {
    public void insertCurrencies(String code,String name);
    public HashMap<String,String> getCurrencyList();

    public String getName(String code);
    public String getCode(String name);

    public boolean codeExists(String code);
    public boolean nameExists(String name);

    public ArrayList<String> getAllCodes() throws Exception;
    public ArrayList<String> getAllNames() throws Exception;

}
