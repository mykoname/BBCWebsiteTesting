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

import static org.junit.jupiter.api.Assertions.*;

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

        @ParameterizedTest     // BBC2 Part2 Test1 (Verify that 2 specific teams )
        @MethodSource("dataForTestTeamScores")
    public void testTeamScoresDisplay(String nameOfChampionship, String month, String team1, String team2, int score1, int score2){
            BBCHomePage homePage = new BBCHomePage(driver);
            BBCSportPage sportPage = homePage.sportPageClick();
            BBCSportPage scoresPageOne = sportPage.footballPageClick().scoresPageClick().makeSearchChampionship(nameOfChampionship).monthSelectorClick(month);
            matchResults awaitedMatchResults = new matchResults(team1, team2, score1, score2);
            matchResults fromSiteResults = scoresPageOne.checkTeamsAndScore(team1, team2, score1, score2, awaitedMatchResults);
            assertEquals(awaitedMatchResults, fromSiteResults);
//            TODO: it should be done simplier with receiving boolean with checkTeamsAndScore()
        }

    @ParameterizedTest     // BBC2 Part2 Test1 ( Verify that the score at the center)
    @MethodSource("dataForTestTeamScores")
    public void testCenteredScoreDisplay(String nameOfChampionship, String month, String team1, String team2, int score1, int score2){
        BBCHomePage homePage = new BBCHomePage(driver);
        BBCSportPage sportPage = homePage.sportPageClick();
        BBCSportPage scoresPageOne = sportPage.footballPageClick().scoresPageClick().makeSearchChampionship(nameOfChampionship).monthSelectorClick(month).clickTeam(team1);
        // TODO not ended from this part:  Verify that the score at the center of the screen is also the one from your test (same value).
        // requested position here:
        System.out.println(this.driver.findElement(By.cssSelector("article > div.sp-c-fixture__wrapper")).getLocation());
        matchResults awaitedMatchResults = new matchResults(team1, team2, score1, score2);
        matchResults fromSiteResults = scoresPageOne.checkTeamsAndScore(team1, team2, score1, score2, awaitedMatchResults);
        assertEquals(awaitedMatchResults, fromSiteResults);

    }


    static Stream<Arguments> dataForTestTeamScores() {
       // name of championship | month | team1(left) | team2(right) | score1 | score2
        return Stream.of(
                Arguments.of("Scottish Championship", "MAY", "Airdrieonians", "Queen's Park", 1, 2),
                Arguments.of("Scottish Championship", "MAY", "Dunfermline", "Queen's Park", 0, 1),
                Arguments.of("Scottish Championship", "APR", "Dunfermline", "Queen of the South", 1, 2),
                Arguments.of("Danish Superliga", "FEB", "Aalborg BK", "Silkeborg IF", 1, 4),
                Arguments.of("Rangers", "MAR", "Red Star Belgrade", "Rangers", 2, 1)
                );
    }

    @AfterAll
    public static void cleanUp(){
        driver.manage().deleteAllCookies();
        driver.close();
    }
}
