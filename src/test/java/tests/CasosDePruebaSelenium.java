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

public class CasosDePruebaSelenium {
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
    public void CP001_Registro_Fallido_Captcha_En_Blanco() {

        By localizadorBtnRegistrarse = By.xpath("//button[contains(text(),'Registrarte')]");
        WebElement btnRegistrarse = driver.findElement(localizadorBtnRegistrarse);
        btnRegistrarse.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Pon tu correo electrónico.']"))).sendKeys("fabian.yanez@tsoftglobal.com");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Vuelve a poner tu correo electrónico.']"))).sendKeys("fabian.yanez@tsoftglobal.com");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Crea una contraseña.']"))).sendKeys("ABc1233212.");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Pon un nombre de perfil.']"))).sendKeys("Fabi");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='DD']"))).sendKeys("27");

        Select cmbMes = new Select(driver.findElement(By.id("month")));
        cmbMes.selectByValue("02");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='AAAA']"))).sendKeys("1988");

        //driver.findElement(By.xpath("//label[@for='gender_option_male']")).click();
        WebElement opcionHombre = driver.findElement(By.xpath("//label[@for='gender_option_male']"));
        js.executeScript("arguments[0].scrollIntoView();",opcionHombre);
        opcionHombre.click();


        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[@for='marketing-opt-checkbox']"))).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[@for='third-party-checkbox']"))).click();


        WebElement btnRegistro  = driver.findElement(By.xpath("//button[@type='submit']"));
        js.executeScript("arguments[0].scrollIntoView();", btnRegistro);
        btnRegistro.click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),'Confirma que no eres un robot.')]")).getText(),"Confirma que no eres un robot.");

    }

    @Test
    public void CP002_Registro_Fallido_MailConfirm_Invalido() {

        By localizadorBtnRegistrarse = By.xpath("//button[contains(text(),'Registrarte')]");
        WebElement btnRegistrarse = driver.findElement(localizadorBtnRegistrarse);
        btnRegistrarse.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Pon tu correo electrónico.']"))).sendKeys("fabian.yanez@tsoftglobal.com");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Vuelve a poner tu correo electrónico.']"))).sendKeys("fabian,.yanez@tsoftglobal.com");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Crea una contraseña.']"))).sendKeys("ABc1233212.");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Pon un nombre de perfil.']"))).sendKeys("Fabi");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='DD']"))).sendKeys("27");

        Select cmbMes = new Select(driver.findElement(By.id("month")));
        cmbMes.selectByValue("02");


        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='AAAA']"))).sendKeys("1988");

        WebElement optionMale = driver.findElement(By.xpath("//label[@for='gender_option_male']"));
        js.executeScript("arguments[0].scrollIntoView();", optionMale);
        optionMale.click();


        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[@for='marketing-opt-checkbox']"))).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[@for='third-party-checkbox']"))).click();

        WebElement btnRegistro  = driver.findElement(By.xpath("//button[@type='submit']"));
        js.executeScript("arguments[0].scrollIntoView();", btnRegistro);
        btnRegistro.click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),'Las direcciones de correo')]")).getText(),"Las direcciones de correo electrónico no coinciden.");
    }

    @Test
    public void CP003_Registro_Fallido_Contraseña_Insegura() {

        By localizadorBtnRegistrarse = By.xpath("//button[contains(text(),'Registrarte')]");
        WebElement btnRegistrarse = driver.findElement(localizadorBtnRegistrarse);
        btnRegistrarse.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Pon tu correo electrónico.']"))).sendKeys("fabian.yanez@tsoftglobal.com");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Vuelve a poner tu correo electrónico.']"))).sendKeys("fabian.yanez@tsoftglobal.com");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Crea una contraseña.']"))).sendKeys("12345678");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Pon un nombre de perfil.']"))).sendKeys("Fabi");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='DD']"))).sendKeys("27");

        Select cmbMes = new Select(driver.findElement(By.id("month")));
        cmbMes.selectByValue("02");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='AAAA']"))).sendKeys("1988");

        WebElement optionMale = driver.findElement(By.xpath("//label[@for='gender_option_male']"));
        js.executeScript("arguments[0].scrollIntoView();", optionMale);
        optionMale.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[@for='marketing-opt-checkbox']"))).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[@for='third-party-checkbox']"))).click();

        WebElement btnRegistro  = driver.findElement(By.xpath("//button[@type='submit']"));
        js.executeScript("arguments[0].scrollIntoView();", btnRegistro);
        btnRegistro.click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),'Tu contraseña no es segura. Utiliza una más compleja.')]")).getText(),"Tu contraseña no es segura. Utiliza una más compleja.");
    }

    @Test
    public void CP004_Registro_Fallido_Contraseña_Corta() {

        By localizadorBtnRegistrarse = By.xpath("//button[contains(text(),'Registrarte')]");
        WebElement btnRegistrarse = driver.findElement(localizadorBtnRegistrarse);
        btnRegistrarse.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Pon tu correo electrónico.']"))).sendKeys("fabian.yanez@tsoftglobal.com");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Vuelve a poner tu correo electrónico.']"))).sendKeys("fabian.yanez@tsoftglobal.com");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Crea una contraseña.']"))).sendKeys("aAbBcCd");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Pon un nombre de perfil.']"))).sendKeys("Fabi");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='DD']"))).sendKeys("27");


        Select cmbMes = new Select(driver.findElement(By.id("month")));
        cmbMes.selectByValue("02");


        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='AAAA']"))).sendKeys("1988");

        WebElement optionMale = driver.findElement(By.xpath("//label[@for='gender_option_male']"));
        js.executeScript("arguments[0].scrollIntoView();", optionMale);
        optionMale.click();


        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[@for='marketing-opt-checkbox']"))).click();


        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[@for='third-party-checkbox']"))).click();

        WebElement btnRegistro  = driver.findElement(By.xpath("//button[@type='submit']"));
        js.executeScript("arguments[0].scrollIntoView();", btnRegistro);
        btnRegistro.click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),'Tu contraseña es demasiado corta.')]")).getText(),"Tu contraseña es demasiado corta.");
    }

    @Test
    public void CP005_Registro_Fallido_Dia_Valido_Del_Mes()  {

        By localizadorBtnRegistrarse = By.xpath("//button[contains(text(),'Registrarte')]");
        WebElement btnRegistrarse = driver.findElement(localizadorBtnRegistrarse);
        btnRegistrarse.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Pon tu correo electrónico.']"))).sendKeys("fabian.yanez@tsoftglobal.com");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Vuelve a poner tu correo electrónico.']"))).sendKeys("fabian.yanez@tsoftglobal.com");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Crea una contraseña.']"))).sendKeys("ABc1233212.");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Pon un nombre de perfil.']"))).sendKeys("Fabi");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='DD']"))).sendKeys("32");

        Select cmbMes = new Select(driver.findElement(By.id("month")));
        cmbMes.selectByValue("02");


        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='AAAA']"))).sendKeys("1988");

        WebElement optionMale = driver.findElement(By.xpath("//label[@for='gender_option_male']"));
        js.executeScript("arguments[0].scrollIntoView();", optionMale);
        optionMale.click();


        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[@for='marketing-opt-checkbox']"))).click();


        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[@for='third-party-checkbox']"))).click();

        WebElement btnRegistro  = driver.findElement(By.xpath("//button[@type='submit']"));
        js.executeScript("arguments[0].scrollIntoView();", btnRegistro);
        btnRegistro.click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),'Indica un día válido del mes.')]")).getText(),"Indica un día válido del mes.");
    }

    @Test
    public void CP006_Registro_Fallido_Año_Invalido() {

        By localizadorBtnRegistrarse = By.xpath("//button[contains(text(),'Registrarte')]");
        WebElement btnRegistrarse = driver.findElement(localizadorBtnRegistrarse);
        btnRegistrarse.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Pon tu correo electrónico.']"))).sendKeys("fabian.yanez@tsoftglobal.com");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Vuelve a poner tu correo electrónico.']"))).sendKeys("fabian.yanez@tsoftglobal.com");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Crea una contraseña.']"))).sendKeys("ABc1233212.");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Pon un nombre de perfil.']"))).sendKeys("Fabi");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='DD']"))).sendKeys("27");


        Select cmbMes = new Select(driver.findElement(By.id("month")));
        cmbMes.selectByValue("02");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='AAAA']"))).sendKeys("2023");

        WebElement optionMale = driver.findElement(By.xpath("//label[@for='gender_option_male']"));
        js.executeScript("arguments[0].scrollIntoView();", optionMale);
        optionMale.click();


        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[@for='marketing-opt-checkbox']"))).click();


        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[@for='third-party-checkbox']"))).click();

        WebElement btnRegistro  = driver.findElement(By.xpath("//button[@type='submit']"));
        js.executeScript("arguments[0].scrollIntoView();", btnRegistro);
        btnRegistro.click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),'Lo sentimos, pero no cumples los requisitos de edad de Spotify.')]")).getText(),"Lo sentimos, pero no cumples los requisitos de edad de Spotify.");


    }

    @Test
    public void CP007_Registro_Btn_Mujer() {

        By localizadorBtnRegistrarse = By.xpath("//button[contains(text(),'Registrarte')]");
        WebElement btnRegistrarse = driver.findElement(localizadorBtnRegistrarse);
        btnRegistrarse.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Pon tu correo electrónico.']"))).sendKeys("fabian.yanez@tsoftglobal.com");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Vuelve a poner tu correo electrónico.']"))).sendKeys("fabian.yanez@tsoftglobal.com");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Crea una contraseña.']"))).sendKeys("ABc1233212.");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Pon un nombre de perfil.']"))).sendKeys("Fabi");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='DD']"))).sendKeys("27");


        Select cmbMes = new Select(driver.findElement(By.id("month")));
        cmbMes.selectByValue("02");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='AAAA']"))).sendKeys("1988");

        WebElement optionFemale = driver.findElement(By.xpath("//label[@for='gender_option_female']"));
        js.executeScript("arguments[0].scrollIntoView();", optionFemale);
        optionFemale.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[@for='marketing-opt-checkbox']"))).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[@for='third-party-checkbox']"))).click();

        WebElement btnRegistro  = driver.findElement(By.xpath("//button[@type='submit']"));
        js.executeScript("arguments[0].scrollIntoView();", btnRegistro);
        btnRegistro.click();

        Assert.assertEquals(driver.findElement(By.xpath("//label[@for='gender_option_female']")).getText(),"Mujer");


    }

}
