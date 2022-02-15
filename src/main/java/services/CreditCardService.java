package services;

import model.CreditCard;
import repositories.CreditCardRepository;
import util.ConnectionProvider;
import util.Utilities;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;

public class CreditCardService {
    private final CreditCardRepository creditCardRepository;
    private Utilities validation = new Utilities(ConnectionProvider.getConnection());

    public CreditCardService(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    public void createTableCustomer() throws SQLException {
        creditCardRepository.creatCreditCardTable();
    }

    public Integer insertIntoCreditCard(Integer password, Long secondPassword, Long cardNumber, Long CVV2, Timestamp expirationDate,Integer accountId) throws SQLException {
        return creditCardRepository.insertIntoCreditCard(password,secondPassword,cardNumber,CVV2,expirationDate,accountId);
    }

    public void updateCreditCard(Integer id ,Integer password, Long balance, Long secondPassword, Long cardNumber, Long CVV2, Date expirationDate,Integer accountId) throws SQLException {
        creditCardRepository.update(id,password,balance,secondPassword,cardNumber,CVV2,expirationDate,accountId);
    }

    public void deleteCreditCard(Integer id) throws SQLException {
        creditCardRepository.delete(id);
    }

    public CreditCard findById(Integer id) throws SQLException {
        CreditCard creditCard = creditCardRepository.findByIdGetObject(id);
        return creditCard;
    }

    public Boolean existsById(Integer id) throws SQLException {
        return creditCardRepository.existsById(id);
    }

    public void transferMoneyToAnotherCard(String cardNumber,String destinationCardNumber, Double amount,String CVV2,String secondPassword) throws SQLException {
        CreditCard fromCard = creditCardRepository.findByCardNumber(cardNumber);
        CreditCard toCard = creditCardRepository.findByCardNumber(destinationCardNumber);
        if( fromCard != null && validation.informationValidation(fromCard.getAccount().getId(),fromCard,amount,CVV2,secondPassword) && toCard != null){
            Double balance = (fromCard.getAccount().getBalance() - 600) - amount;
            fromCard.getAccount().setBalance(balance);
            creditCardRepository.updateToGetObject(fromCard);
            Double transferMoney = toCard.getAccount().getBalance() + amount;
            toCard.getAccount().setBalance(transferMoney);
            creditCardRepository.updateToGetObject(toCard);
            System.out.println("Transfer operation was successful");
        }
    }

    public void editPassword(Integer newPassword,Integer creditCardId) throws SQLException {
        CreditCard fromPassword = creditCardRepository.findByIdGetObject(creditCardId);
        if(fromPassword != null){
            if(String.valueOf(newPassword).length() == 4 ) {
                creditCardRepository.updatePassword(newPassword,creditCardId);
                System.out.println("Your password changed successfully");
            }else System.out.println("The number of password digits must be 4!!!");
        }else System.out.println("You dont have an account!!!");
    }

    public void editSecondPassword(String newSecondPassword,Integer creditCardId) throws SQLException {
        CreditCard fromPassword = creditCardRepository.findByIdGetObject(creditCardId);
            if(fromPassword != null){
                if(newSecondPassword.length() >= 8 ) {
                    creditCardRepository.updateSecondPassword(newSecondPassword,creditCardId);
                    System.out.println("Your second password changed successfully");
                }else System.out.println("Characters should be more than 8!!!");
        }else System.out.println("You dont have an account!!!");
    }
}
