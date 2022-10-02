package API;

import Model.CurrencyPair;

import java.util.HashMap;

public interface IAPI {
    public HashMap<String,String> getCurrencies() throws Exception;
    public CurrencyPair convertCurrency(CurrencyPair currencyPair) throws Exception;
}
