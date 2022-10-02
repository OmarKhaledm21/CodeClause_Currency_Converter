package Model;

public class CurrencyPair {

    public CurrencyPair() {
        this.from_equivalent = 1.0;
        this.amount = 1.0;
    }

    private String from_code;
    private String to_code;

    private String from_name;
    private String to_name;

    private double to_equivalent;
    private double from_equivalent;

    private double amount;

    private double amountEquivalent;

    public double getAmountEquivalent() {
        return amountEquivalent;
    }

    public void setAmountEquivalent(double amountEquivalent) {
        this.amountEquivalent = amountEquivalent;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getFrom_equivalent() {
        return from_equivalent;
    }

    public void setFrom_equivalent(double from_equivalent) {
        this.from_equivalent = from_equivalent;
    }

    public double getTo_equivalent() {
        return to_equivalent;
    }

    public void setTo_equivalent(double to_equivalent) {
        this.to_equivalent = to_equivalent;
        this.amountEquivalent = this.amount * to_equivalent;
    }

    public String getFrom_code() {
        return from_code;
    }

    public void setFrom_code(String from_code) {
        this.from_code = from_code;
    }

    public String getTo_code() {
        return to_code;
    }

    public void setTo_code(String to_code) {
        this.to_code = to_code;
    }

    public String getFrom_name() {
        return from_name;
    }

    public void setFrom_name(String from_name) {
        this.from_name = from_name;
    }

    public String getTo_name() {
        return to_name;
    }

    public void setTo_name(String to_name) {
        this.to_name = to_name;
    }
}
