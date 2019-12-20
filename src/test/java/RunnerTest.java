import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", tags = "not @ignore", plugin = {"pretty", "html:target/report-html"}, dryRun = false)
public class RunnerTest {

    @BeforeClass
    public static void reset(){
        System.setProperty("webdriver.chrome.driver", "/home/gabriel/Estudos/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("http://srbarriga.herokuapp.com/");
        driver.findElement(By.id("email")).sendKeys("bdd@teste.com");
        driver.findElement(By.id("senha")).sendKeys("senha123");
        driver.findElement(By.tagName("button")).click();
        driver.findElement(By.linkText("reset")).click();
        driver.quit();
    }

}
