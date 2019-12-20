package steps;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;

public class CadastroContasSteps {

    private WebDriver driver;

    @Dado("que estou acessando a aplicação")
    public void que_estou_acessando_a_aplicação() {
        System.setProperty("webdriver.chrome.driver", "/home/gabriel/Estudos/chromedriver");
        driver = new ChromeDriver();
        driver.get("http://srbarriga.herokuapp.com/");
    }

    @Quando("informo o usuário {string}")
    public void informo_o_usuário(String string) {
        driver.findElement(By.id("email")).sendKeys(string);
    }
    @E("a senha {string}")
    public void a_senha(String string) {
        driver.findElement(By.id("senha")).sendKeys(string);
    }
    @E("seleciono entrar")
    public void seleciono_entrar() {
        driver.findElement(By.tagName("button")).click();
    }
    @Então("visualizo a página inicial")
    public void visualizo_a_página_inicial() {
        String texto = driver.findElement(By.xpath("/html/body/div[1]")).getText();
        Assert.assertEquals("Bem vindo, cursobdd!", texto);
    }
    @Quando("seleciono Contas")
    public void seleciono_Contas() {
        driver.findElement(By.linkText("Contas")).click();
    }
    @E("seleciono Adicionar")
    public void seleciono_Adicionar() {
        driver.findElement(By.linkText("Adicionar")).click();
    }
    @Quando("informo a conta {string}")
    public void informo_a_conta(String string) {
        driver.findElement(By.id("nome")).sendKeys(string);
    }
    @E("seleciono Salvar")
    public void seleciono_Salvar() {
        driver.findElement(By.tagName("button")).click();
    }
    @Então("recebo a mensagem {string}")
    public void recebo_a_mensagem(String string) {
        String texto = driver.findElement(By.xpath("//div[starts-with(@class, 'alert alert-')]")).getText();
        Assert.assertEquals(string, texto);
    }

    @Dado("que desejo adicionar uma conta")
    public void que_desejo_adicionar_uma_conta() {
        System.setProperty("webdriver.chrome.driver", "/home/gabriel/Estudos/chromedriver");
        driver = new ChromeDriver();
        driver.get("http://srbarriga.herokuapp.com/");
        driver.findElement(By.id("email")).sendKeys("bdd@teste.com");
        driver.findElement(By.id("senha")).sendKeys("senha123");
        driver.findElement(By.tagName("button")).click();
        driver.findElement(By.linkText("reset")).click();
        driver.findElement(By.linkText("Contas")).click();
        driver.findElement(By.linkText("Adicionar")).click();
    }

    @Quando("adiciono a conta {string}")
    public void adiciono_a_conta(String string) {
        driver.findElement(By.id("nome")).sendKeys(string);
        driver.findElement(By.tagName("button")).click();
    }

    @After(order = 1)
    public void screenshot(Scenario scenario){
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File("target/screenshot/" + scenario.getId().split(":")[2] + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After(order = 0)
    public void fecharNavegador(){
        driver.quit();
    }

}
