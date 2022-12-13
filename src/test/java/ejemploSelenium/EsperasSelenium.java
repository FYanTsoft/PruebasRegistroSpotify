package ejemploSelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class EsperasSelenium {
    public static void main(String[] args) throws InterruptedException {

        String rutaDriver = System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\chromedriver.exe";

        System.setProperty("webdriver.chrome.driver", rutaDriver);

        WebDriver driver = new ChromeDriver();


        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(40,TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);

        WebDriverWait wait = new WebDriverWait(driver,10);



        driver.navigate().to("https://www.spotify.com");

        driver.manage().window().maximize();

        JavascriptExecutor js = (JavascriptExecutor) driver;

       /*
        By locatorBtnCerrarPoliticasPrivacidad = By.xpath("//button[@aria-label='Cerrar']");

        WebElement btnCerrarPoliticasPrivacidad = driver.findElement(locatorBtnCerrarPoliticasPrivacidad);

        Thread.sleep(3000);

        if (btnCerrarPoliticasPrivacidad.isDisplayed()){
            btnCerrarPoliticasPrivacidad.click();
        } */

        // crear localizador
        By localizadorBtnRegistrarse = By.xpath("//button[contains(text(),'Registrarte')]");
        // crear elemento web
        WebElement btnRegistrarse = driver.findElement(localizadorBtnRegistrarse);
        // Acción
        btnRegistrarse.click();


       // driver.findElement(By.id("email")).sendKeys("fabian.yanez@tsoftglobal.com");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email"))).sendKeys("fabian.yanez@tsoftglobal.com");

        //driver.findElement(By.name("confirm")).sendKeys("fabian.yanez@tsoftglobal.com");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("confirm"))).sendKeys("fabian.yanez@tsoftglobal.com");

        //driver.findElement(By.name("password")).sendKeys("ABc1233212.");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("password"))).sendKeys("ABc1233212.");

        //driver.findElement(By.name("displayname")).sendKeys("FabiDread");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("displayname"))).sendKeys("FabianDread");

        //driver.findElement(By.id("day")).sendKeys("27");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("day"))).sendKeys("27");

        //driver.findElement(By.id("month")).sendKeys("Febrero");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("month"))).sendKeys("Febrero");

        //driver.findElement(By.id("year")).sendKeys("1988");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("year"))).sendKeys("1988");


        WebElement opcionHombre = driver.findElement(By.xpath("//label[@for='gender_option_male']"));

        js.executeScript("arguments[0].scrollIntoView();",opcionHombre);

        opcionHombre.click();



        driver.findElement(By.xpath("//label[@for='marketing-opt-checkbox']")).click();



        driver.findElement(By.xpath("//label[@for='third-party-checkbox']")).click();


        WebElement btnRegistro = driver.findElement(By.xpath("//button[@type='submit']"));

        js.executeScript("arguments[0].scrollIntoView();",btnRegistro);

        btnRegistro.click();


        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[@type='submit']")).submit();

        String resultadoEsperado = "Confirma que no eres un robot.";
        String resultadoActual = driver.findElement(By.xpath("//div[contains(text(),'Confirma que no eres un robot.')]")).getText();

        if(resultadoActual.equals(resultadoEsperado)){
            System.out.println("Caso ok, se verifica ausencia de capcha");
        }else {
            System.out.println("Error.");
        }
        driver.quit();
    }


}
