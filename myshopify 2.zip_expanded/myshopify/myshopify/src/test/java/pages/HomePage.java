package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class HomePage {

    WebDriver driver;

    @FindBy(how = How.CSS, using = ".footer-block.grid__item")
    private WebElement ourMissionText;

    @FindBy(how = How.CSS, using = ".icon-search")
    public WebElement searchButton;

    @FindBy(how = How.CSS, using = ".icon-cart")
    public WebElement cartButton;

    @FindBy(how = How.ID, using = "Search-In-Modal")
    public WebElement searchTextBox;


    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getOurMissionText() {
        return ourMissionText.getText().trim();
    }

    public void search(String text) {
        searchButton.click();
        searchTextBox.sendKeys(text);
    }

    public void addProductsToCart(int productsCount) {
        List<WebElement> elements = driver.findElements(By.cssSelector(".product-grid .grid__item"));

        for (int i = 0; i < productsCount; i++) {

            elements.get(i).click();
            new ProductPage(driver).addToCart();

        }
    }
    
    public void searchAssert (String searchText) {
        //Get the values from the Search suggestion/result, save them in list.
        List<WebElement> p = driver.findElements(By.xpath("//ul[@id='predictive-search-results-list']/li/a/div/h3"));
        int resultCount = p.size(); 
        System.out.println(resultCount);
       
        //iterate through list
        for( WebElement i: p){
        	String resultText = i.getText();
              System.out.println(resultText);
              Assert.assertTrue(resultText.contains(searchText),"Failure message");  //Returns FALSE - if the case is not matched.
        }
    }
}
