import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentJourneye2e {

    @LocalServerPort
    private Integer port;
    private WebDriver webDriver = null;
    private String baseUrl;

    @BeforeClass
    public static void setUpChromeDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setUp() {
        webDriver = new ChromeDriver();
        baseUrl = "http://localhost:" + port + "/calculator";
    }

    @After
    public void tearDown() {
        if (null != webDriver) {
            webDriver.quit();
        }
    }

    @Test
    public void aStudentUsesTheCalculatorToMultiplyTwoBySixteen() {
        webDriver.get(baseUrl);
        WebElement submitButton = webDriver.findElement(By.id("submit"));
        WebElement leftField = webDriver.findElement(By.id("left"));
        WebElement rightField = webDriver.findElement(By.id("right"));
        WebElement typeDropdown = webDriver.findElement(By.id("type"));

        // Fill in 3 x 2
        leftField.sendKeys("3");
        typeDropdown.sendKeys("x");
        rightField.sendKeys("2");
        submitButton.click();

        WebDriverWait waiter = new WebDriverWait(webDriver, 5);
        WebElement solutionElement = waiter.until(
                ExpectedConditions.presenceOfElementLocated(By.id("solution")));
        String solution = solutionElement.getText();
        assertThat(solution, is(equalTo("6"))); // 3 x 2
    }
}

