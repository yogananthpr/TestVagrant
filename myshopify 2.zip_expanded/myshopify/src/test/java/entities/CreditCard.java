package entities;
import constants.CreditCards;
public class CreditCard {

    public int cardNumber;
    public String cardHolderName;
    public int expiryMonth;
    public int expiryYear;
    public int securityCode;

    public CreditCard(int cardNumber, String cardHolderName, int expiryMonth, int expiryYear, int securityCode) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;
        this.securityCode = securityCode;
    }
       
}
