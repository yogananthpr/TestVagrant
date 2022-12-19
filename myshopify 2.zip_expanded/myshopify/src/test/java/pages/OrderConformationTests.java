package pages;

import constants.CreditCards;
import entities.CreditCard;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import entities.CreditCard;
public class OrderConformationTests {
	
	
	/**
	 * Test to assert the user contact and payment information on the check out page.
	 */
    @Test(description = "LEVEL-3")
    public static void shouldAssertThePaymentDetailsInOrderConfirmationPage() throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        
        driver.get("https://testvagrant.myshopify.com/");

        //Search for a product.
        HomePage homePage = new HomePage(driver);
        String searchText = "ADIDAS";
        homePage.search(searchText);
        
        homePage.returnSearch();
        
        //Add product to the cart.
        homePage.addProductsToCart(1);
       
        //Go to view cart.
        homePage.viewCart();
        
        //Click check out.
        CartPage cartPage = new CartPage(driver);
        cartPage.checkOutButton();
        
        //Go to Information page and fill the details.
        cartPage.Information();
        cartPage.goToPayment();
        
        //Go to payment details and fill the credit card details.
        cartPage.paymentCreditCard();
        
        System.out.println("Completed Level 3 - shouldAssertThePaymentDetailsInOrderConfirmationPage.");
        
        driver.quit();
    }

    

	public static void main(String[] args) throws InterruptedException {
    	shouldAssertThePaymentDetailsInOrderConfirmationPage();
    }

}
