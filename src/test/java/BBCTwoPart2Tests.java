import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BBCTwoPart2Tests {

        private static final WebDriver driver;
        static {
            System.setProperty("webdriver.chrome.driver", Utils.CHROME_DRIVER_LOCATION);
            DesiredCapabilities dc = new DesiredCapabilities();
            dc.setJavascriptEnabled(false);
            ChromeOptions javaCap = new ChromeOptions();
            javaCap.merge(dc);
            driver = new ChromeDriver(javaCap);
        }

        @ParameterizedTest     // BBC2 Part2 Test1
        @MethodSource("dataForTestTeamScores")
    public void testTeamScoresDisplay(String nameOfChampionship, String month){
            BBCHomePage homePage = new BBCHomePage(driver);
            BBCSportPage sportPage = homePage.sportPageClick();
            BBCSportPage scoresPageOne = sportPage.footballPageClick().scoresPageClick().makeSearchChampionship(nameOfChampionship).monthSelectorClick(month);

//            teamXpathBase = "article[@class ='sp-c-fixture']//abbr[@title='";
//    poolOfPlays = "//span[@role ='region']";
// System.out.println(this.driver.findElement(By.xpath("//article[@class ='sp-c-fixture']//abbr[@title='Airdrieonians'] | //article[@class ='sp-c-fixture']//abbr[@title='Queen's Park']")));
            //td[contains(text(),'Submit')]
//    List<WebElement> list =scoresPageOne.driver.findElements(By.xpath("//div//ul//li//a[contains(text(),'Airdrieonians')]"));

//            List<WebElement> list =scoresPageOne.driver.findElements(By.linkText("Airdrieonians"));
            List<WebElement> list2 =scoresPageOne.driver.findElements(By.cssSelector("a article"));
            List<WebElement> list3 =scoresPageOne.driver.findElements(By.xpath("//div//ul//li//a//article[contains(text(),'Airdrieonians')]"));
            System.out.println(scoresPageOne.driver.findElements(By.cssSelector("a article")).size());
            System.out.println(scoresPageOne.driver.findElements(By.xpath("//div//ul//li//a//article[contains(text(),'Airdrieonians')]")).size());
            for (WebElement l: list2) {
                System.out.println(l.getTagName());
                System.out.println(l.getText());
            }
            for (WebElement l: list3) {
                System.out.println(l.getTagName());
                System.out.println(l.getText());
            }
//    System.out.println(scoresPageOne.driver.findElement(By.xpath("//article[contains(text(),'Airdrieonians')]")).getText());
//    System.out.println(scoresPageOne.driver.findElement(By.xpath("//article[@class ='sp-c-fixture']//abbr[@title='Airdrieonians']")).getText());
//            System.out.println(scoresPageOne.driver.findElement(By.xpath("//article[@title='Airdrieonians']")).getText());
//            System.out.println(scoresPageOne.driver.findElement(By.xpath("//*[@title='Airdrieonians'] | //*[@title='Queen's Park']")));
        }
    static Stream<Arguments> dataForTestTeamScores() {
       // name of championship | month | team1 | team2 | score1 | score2
        return Stream.of(
                Arguments.of("Scottish Championship", "MAY")
                );
    }



//    @AfterAll
//    public static void cleanUp(){
//        driver.manage().deleteAllCookies();
//        driver.close();
//    }
}
