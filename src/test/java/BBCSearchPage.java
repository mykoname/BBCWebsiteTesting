import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BBCSearchPage extends BBCPageObject {

    @FindBy (id = "search-input")
    private WebElement searchBar;
    @FindBy (xpath = "//button[@type='submit']")
    private WebElement startSearch;
    // <ul role="list" spacing="responsive"
    @FindBy(xpath = "//ul[@spacing='responsive']//li[1]//a[@href]") //(xpath = "//div/div/ul/li/div/div/div/a")//"//ul[@spacing='responsive']")// (css = "ul li a") //xpath = "//div[@id ='main-content']//li")
    private WebElement firstResult;

    public BBCSearchPage(WebDriver driver) {super (driver); }

//    public String getWaitSelector(){
//        return "#search-input";
//    }

    public BBCSearchPage makeSearch(String searchTerm){
        this.searchBar.sendKeys(searchTerm);
        this.startSearch.click();
        BBCSearchPage searchPageOne = new BBCSearchPage(driver);
        return searchPageOne;
    }

    public String firstSearchResult(String searchTerm){
        String result = this.firstResult.getText();
        return result;
    }
}
