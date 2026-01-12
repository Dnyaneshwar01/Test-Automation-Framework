import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.net.MalformedURLException;
import java.net.URL;

public class GridTest {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {

        String hubUrl = "http://10.73.101.248:4444/wd/hub";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--headless=new");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");

        System.out.println("Connecting to hub: " + hubUrl);

        WebDriver driver = new RemoteWebDriver(new URL(hubUrl), options);
        System.out.println("Session started, launching browser...");

        Thread.sleep(5000);
        driver.get("https://uk.marketplace.asite.com/");
        System.out.println("Page title: " + driver.getTitle());

        driver.quit();
        System.out.println("Test completed.");
    }
}
