package model;

public class Transaction {
    private Integer id;
    private String showBalance;
    private String withdraw;
    private String deposit;
    private String transfer;
    private Integer creditCardId;
    private CreditCard creditCard;

    public Transaction(Integer id, String showBalance, String withdraw, String deposit, String transfer,Integer creditCardId, CreditCard creditCard) {
        this.id = id;
        this.showBalance = showBalance;
        this.withdraw = withdraw;
        this.deposit = deposit;
        this.transfer = transfer;
        this.creditCardId = creditCardId;
        this.creditCard = creditCard;
    }

    public Integer getCreditCardId() {
        return creditCardId;
    }

    public void setCreditCardId(Integer creditCardId) {
        this.creditCardId = creditCardId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShowBalance() {
        return showBalance;
    }

    public void setShowBalance(String showBalance) {
        this.showBalance = showBalance;
    }

    public String getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(String withdraw) {
        this.withdraw = withdraw;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getTransfer() {
        return transfer;
    }

    public void setTransfer(String transfer) {
        this.transfer = transfer;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }
}
