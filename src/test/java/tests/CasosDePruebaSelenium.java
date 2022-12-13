package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class CasosDePrueba {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    private String rutaDriver= System.getProperty("user.dir")+"\\src\\test\\resources\\drivers\\chromedriver.exe";
    private String propertyDriver = "webdriver.chrome.driver";

    @AfterMethod
    public void posCondicion(){
        driver.close();
    }

    @BeforeMethod
    public void preCondiciones(){

        System.setProperty(propertyDriver,rutaDriver);

        driver = new ChromeDriver();

        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(40, TimeUnit.SECONDS);

        wait = new WebDriverWait(driver,10);

        js = (JavascriptExecutor) driver;

        driver.navigate().to("https://www.spotify.com/");

        driver.manage().window().maximize();
    }

    @Test
    public void CP001_Registro_Fallido_Captcha_en_blanco() {

        By localizadorBtnRegistrarse = By.xpath("//button[contains(text(),'Registrarte')]");

        WebElement btnRegistrarse = driver.findElement(localizadorBtnRegistrarse);

        btnRegistrarse.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email"))).sendKeys("fabian.yanez@tsoftglobal.com");

        driver.findElement(By.name("confirm")).sendKeys("fabian.yanez@tsoftglobal.com");

        driver.findElement(By.name("password")).sendKeys("ABc1233212.");

        driver.findElement(By.name("displayname")).sendKeys("Fabi");

        driver.findElement(By.id("day")).sendKeys("27");

        Select cmbMes = new Select(driver.findElement(By.id("month")));

        cmbMes.selectByValue("02");

        driver.findElement(By.name("year")).sendKeys("1988");


        driver.findElement(By.xpath("//label[@for='gender_option_male']")).click();

        driver.findElement(By.xpath("//label[@for='marketing-opt-checkbox']")).click();

        driver.findElement(By.xpath("//label[@for='third-party-checkbox']")).click();


        WebElement btnRegistro  = driver.findElement(By.xpath("//button[@type='submit']"));

        js.executeScript("arguments[0].scrollIntoView();", btnRegistro);

        btnRegistro.click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),'Confirma que no eres un robot.')]")).getText(),"Confirma que no eres un robot.");

    }

    @Test
    public void CP002_Registro_Fallido_Captcha_en_blanco() {

        By localizadorBtnRegistrarse = By.xpath("//button[contains(text(),'Registrarte')]");

        WebElement btnRegistrarse = driver.findElement(localizadorBtnRegistrarse);

        btnRegistrarse.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email"))).sendKeys("fabian.yanez@tsoftglobal.com");

        driver.findElement(By.name("confirm")).sendKeys("fabian,yanez@tsoftglobal.com");

        driver.findElement(By.name("password")).sendKeys("ABc1233212.");

        driver.findElement(By.name("displayname")).sendKeys("Fabi");

        driver.findElement(By.id("day")).sendKeys("27");

        Select cmbMes = new Select(driver.findElement(By.id("month")));

        cmbMes.selectByValue("02");


        driver.findElement(By.name("year")).sendKeys("1988");

        WebElement optionMale = driver.findElement(By.xpath("//label[@for='gender_option_male']"));

        js.executeScript("arguments[0].scrollIntoView();", optionMale);

        optionMale.click();


        driver.findElement(By.xpath("//label[@for='marketing-opt-checkbox']")).click();


        driver.findElement(By.xpath("//label[@for='third-party-checkbox']")).click();

        WebElement btnRegistro  = driver.findElement(By.xpath("//button[@type='submit']"));

        js.executeScript("arguments[0].scrollIntoView();", btnRegistro);

        btnRegistro.click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),'Las direcciones de correo')]")).getText(),"Las direcciones de correo electrónico no coinciden.");
    }

    /*
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    private String rutaDriver = System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\chromedriver.exe";
    private String propertyDriver = "webdriver.chrome.driver";

    @AfterTest
            public void posCondicion(){
        driver.quit();
    }

    @BeforeTest
            public void preCondiciones(){


        System.setProperty(propertyDriver, rutaDriver);

        driver = new ChromeDriver();

        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(40,TimeUnit.SECONDS);

        wait = new WebDriverWait(driver,10);

        js = (JavascriptExecutor) driver;

        driver.navigate().to("https://www.spotify.com");

        driver.manage().window().maximize();

    }

    @Test
    public void  CP001_Registro_Fallido_Captcha_en_Blanco(){

        By localizadorBtnRegistrarse = By.xpath("//button[contains(text(),'Registrarte')]");
        WebElement btnRegistrarse = driver.findElement(localizadorBtnRegistrarse);
        btnRegistrarse.click();


        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email"))).sendKeys("fabian.yanez@tsoftglobal.com");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("confirm"))).sendKeys("fabian.yanez@tsoftglobal.com");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("password"))).sendKeys("ABc1233212.");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("displayname"))).sendKeys("Fabi");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("day"))).sendKeys("27");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("month"))).sendKeys("Febrero");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("year"))).sendKeys("1988");

        WebElement opcionHombre = driver.findElement(By.xpath("//label[@for='gender_option_male']"));
        js.executeScript("arguments[0].scrollIntoView();",opcionHombre);
        opcionHombre.click();

        driver.findElement(By.xpath("//label[@for='marketing-opt-checkbox']")).click();

        driver.findElement(By.xpath("//label[@for='third-party-checkbox']")).click();


        WebElement btnRegistro = driver.findElement(By.xpath("//button[@type='submit']"));
        js.executeScript("arguments[0].scrollIntoView();",btnRegistro);
        btnRegistro.click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),'Confirma que no eres un robot.')]")).getText(),"Confirma que no eres un robot.");


    }

    @Test
    public void  CP002_Registro_Fallido_Captcha_en_Blanco(){

        By localizadorBtnRegistrarse = By.xpath("//button[contains(text(),'Registrarte')]");
        WebElement btnRegistrarse = driver.findElement(localizadorBtnRegistrarse);
        btnRegistrarse.click();


        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email"))).sendKeys("fabian.yanez@tsoftglobal.com");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("confirm"))).sendKeys("fabiaan.yanez@tsoftglobal.com");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("password"))).sendKeys("ABc1233212.");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("displayname"))).sendKeys("FabianDread");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("day"))).sendKeys("27");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("month"))).sendKeys("Febrero");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("year"))).sendKeys("1988");

        WebElement opcionHombre = driver.findElement(By.xpath("//label[@for='gender_option_male']"));
        js.executeScript("arguments[0].scrollIntoView();",opcionHombre);
        opcionHombre.click();

        driver.findElement(By.xpath("//label[@for='marketing-opt-checkbox']")).click();

        driver.findElement(By.xpath("//label[@for='third-party-checkbox']")).click();


        WebElement btnRegistro = driver.findElement(By.xpath("//button[@type='submit']"));
        js.executeScript("arguments[0].scrollIntoView();",btnRegistro);
        btnRegistro.click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),'Las direcciones de correo.')]")).getText(),"Las direcciones de correo electrónico no coinciden.");
    }
*/

}
