package ejemploSelenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HolaMundoSelenium {
    public static void main(String[] args) throws InterruptedException {

        // paso 1 agregar chromeDriver(WebDriver)
        // paso 2 enlazar

        String rutaDriver = System.getProperty("user.dir") +"\\src\\test\\resources\\drivers\\chromedriver.exe";

        System.setProperty("webdriver.chrome.driver",rutaDriver);

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.google.com");

        Thread.sleep(5000);

        driver.quit();
    }


}
