package util;


import model.CreditCard;
import java.sql.SQLException;

public class Validation {
    public Boolean informationValidation(CreditCard creditCard, Long amount,String CVV2,String secondPassword) throws SQLException {
        if (creditCard.getBalance() > amount + 600 && creditCard.getCVV2().equals(CVV2) &&
                creditCard.getSecondPassword().equals(secondPassword)) {
            return true;
        } else{
            System.out.println("Your balance is not enough!!! ");
            return false;
        }
    }
}
