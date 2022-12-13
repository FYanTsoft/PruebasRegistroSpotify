package ejemploSelenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MetodosNavegadorNavegacionSelenium {
    public static void main(String[] args) throws InterruptedException {

        String rutaDriver = System.getProperty("user.dir") +"\\src\\test\\resources\\drivers\\chromedriver.exe";

        System.setProperty("webdriver.chrome.driver",rutaDriver);

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.google.com");

        System.out.println("Titulo de pagina Google: " + driver.getTitle());

        Thread.sleep(5000);

        driver.navigate().to("https://www.spotify.com");

        System.out.println("Titulo de pagina Spotify: " + driver.getTitle());

        System.out.println("Url actual: " + driver.getCurrentUrl());

        System.out.println("__________________________________________________");

        System.out.println(driver.getPageSource());

        System.out.println("__________________________________________________");

        Thread.sleep(3000);
        driver.navigate().back();
        Thread.sleep(3000);
        driver.navigate().refresh();
        driver.navigate().refresh();
        driver.navigate().refresh();
        driver.navigate().refresh();
        driver.navigate().refresh();

        driver.quit();
    }


}
