package pages;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import constants.CreditCards;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CartPage {

	WebDriver driver;
	
    @FindBy(how = How.XPATH, using = "//a[@class='button button--tertiary']")
    WebElement deleteButton;
    
    @FindBy(how = How.XPATH, using = "//a[@id='cart-icon-bubble']")
    WebElement cartButton;
    
    @FindBy(how = How.XPATH, using = "//div[@class='cart-count-bubble']/span[1]")
    WebElement cartValue;
    
    @FindBy(how = How.XPATH, using = "//h1[@class='cart__empty-text']")
    WebElement cartMSG;
    String cartMsg;
    
    @FindBy(how = How.XPATH, using = "//button[@class='quantity__button no-js-hidden'][2]")
    WebElement addCartQuantity;
    
    @FindBy(how = How.XPATH, using = "(//span[@class='price price--end'])[2]")
    WebElement addCartAmount;
    
    @FindBy(how = How.XPATH, using = "//button[@id='checkout']")
    WebElement checkOut;
    
    @FindBy(how = How.XPATH, using = "//input[@id='email']")
    WebElement email;
    
    @FindBy(how = How.XPATH, using = "//input[@name='lastName']")
    WebElement lastName;
    
    @FindBy(how = How.XPATH, using = "//input[@id='address1']")
    WebElement address;
    
    @FindBy(how = How.XPATH, using = "//input[@name='city']")
    WebElement city;
    
    @FindBy(how = How.XPATH, using = "//input[@name='postalCode']")
    WebElement pincode;
  
    @FindBy(how = How.XPATH, using = "//button[@type='submit']")
    WebElement continueToShipping;
    
    @FindBy(how = How.XPATH, using = "//select[@name='zone']")
    WebElement selectStateDropdown;
   
    
    //input[@placeholder='Card number']
    @FindBy(how = How.XPATH, using = "//iframe[@class='card-fields-iframe']")
    WebElement cardNumber;
    
    @FindBy(how = How.XPATH, using = "(//iframe[@class='card-fields-iframe'])[2]")
    WebElement cardHolderName;
    
    @FindBy(how = How.XPATH, using = "(//iframe[@class='card-fields-iframe'])[3]")
    WebElement expiryDate;

    @FindBy(how = How.XPATH, using = "(//iframe[@class='card-fields-iframe'])[4]")
    WebElement securityCode;
	//public String selectStateDropdown;
    
    
    public CartPage(WebDriver driver) {
    	this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void Wait(int timeToSleep) {
  		WebDriverWait wait = new WebDriverWait(driver,timeToSleep);
          wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(cartMsg)));
    }
    
    public void clearCart() {
        deleteButton.click(); 
       	wait(3);
    }
    
    public void addQuantity(int quantity) {
    	String amountBeforeAddingQuantity = addCartAmount.getText();
    	String [] arrBefore = amountBeforeAddingQuantity.split(" ", 2);
    	System.out.println("Amount before quantity added : " + arrBefore[1]);
    	
    	for (int i = 1; i < quantity; i++) {
    		addCartQuantity.click();
			} 
    	wait(2);
    	
    	String amount = addCartAmount.getText();
    	System.out.println(amount);
    	String [] arrAfter = amount.split(" ", 2);
    	System.out.println("Amount after quantity added : " + arrAfter[1] + "(Split up :- " + arrBefore[1] + "x 2" + " = " + arrAfter[1] + ")");
    }
    
    
    public void getAmount() {
    	addCartAmount.getText(); 
    }
    
    public void checkOutButton() {
    	checkOut.click();
    	wait(2);
    }
    
    public void paymentCreditCard() {
    	int CN=CreditCards.VISA_CARD.cardNumber;
    	String CH=CreditCards.VISA_CARD.cardHolderName;
    	int EM=CreditCards.VISA_CARD.expiryMonth;
    	int EY=CreditCards.VISA_CARD.expiryYear;
    	int SC=CreditCards.VISA_CARD.securityCode;
    	
    	String cardNo=Integer.toString(CN);
    	String expMonth=Integer.toString(EM);
    	String expYear=Integer.toString(EY);
    	String secCode=Integer.toString(SC);
        System.out.println(CN + " " +CH + " " +EM + " " + EY + " " + SC);

        cardNumber.sendKeys(cardNo);
        wait(1);
        cardHolderName.click();
        cardHolderName.sendKeys(CH);
        wait(1);
        expiryDate.sendKeys(expMonth);
        wait(1);
        expiryDate.sendKeys("25");
        wait(1);
        securityCode.sendKeys(secCode);
       
    }
    
    /**
     * supposed to do this as credit card method like getting from entities and constants,
     * due to weekdays/time constrain in my current organization, I am directly passing values for customer information in check out page.
     * but credit card method is done without breaking the requirement.
     */
    public void Information() {
    	email.sendKeys("yogananthpr@gmail.com");
    	lastName.sendKeys("Yoga");
    	address.sendKeys("Door No:1");
    	city.sendKeys("Madurai");
    	// Create object of the Select class
    	 Select se = new Select(selectStateDropdown);
 		
     	// Select the option by index
     	se.selectByVisibleText("Tamil Nadu");
    	pincode.sendKeys("625014");
    	continueToShipping.click();
    	wait(5);
    }
    
    
    public void goToPayment() {
    	 continueToShipping.click();
    	 wait(5);
    }
   
    
    
    
    public String cartMessage(){
        String msg =  cartMSG.getText();
        System.out.println(msg);
        return msg;   
    }
    
    public int getCartCount() {
    	String f = cartValue.getText();
        System.out.println("Actual cart count is :" + f);
    	int j=Integer.parseInt(f); 
    	return j;
    }
    
    
    
    
    public void wait(int timeToSleep)  {
    	TimeUnit time = TimeUnit.SECONDS;
    	try {
			time.sleep(timeToSleep);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
