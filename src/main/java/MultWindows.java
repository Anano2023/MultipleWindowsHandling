import java.util.Iterator;
import java.util.Set;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class MultWindows {

        public static void main(String[] args) throws InterruptedException {
            System.setProperty("webdriver.chrome.driver", "D:\\seleniumServer\\chromedriver-win64//chromedriver.exe");
            WebDriver driver = new ChromeDriver();
            JavascriptExecutor js = (JavascriptExecutor) driver;

            //Navigate to the webpage
            driver.get("https://demoqa.com/browser-windows");
            driver.manage().window().maximize();
            WebElement newWindow = driver.findElement(By.id("windowButton"));
            Thread.sleep(2000);

            //Get parent window handle
            String parentWindow = driver.getWindowHandle();
            System.out.println(driver.getTitle());

            //Open windows
            for (int i = 0; i < 1; i++) {
                newWindow.click();
                Thread.sleep(2000);
            }
            //Retrieving a set of unique identifiers for all currently opened browser windows
            Set<String> allWindowHandle = driver.getWindowHandles();

            //Iterate through these window handles one by one
            Iterator<String> iterator = allWindowHandle.iterator();

            while (iterator.hasNext()) {
                String child = iterator.next();

                if (!parentWindow.equals(child)) {
                    driver.switchTo().window(child);
                    driver.get("https:jobs.ge");
                    driver.manage().window().maximize();
                    System.out.println(driver.switchTo().window(child).getTitle());
                    Thread.sleep(2000);

                    // You can interact with the elements on these page here
                    driver.findElement(By.cssSelector("input[name='q']")).sendKeys("developer");
                    Thread.sleep(2000);
                    driver.findElement(By.className("searchBtn")).click();
                    Thread.sleep(2000);
                    js.executeScript("window.scrollBy(0,600)");
                    Thread.sleep(2000);
                    WebElement link = driver.findElement(By.linkText("Middle Angular დეველოპერი"));
                    Actions action = new Actions(driver);
                    action.keyDown(Keys.SHIFT).click(link).keyUp(Keys.SHIFT).build().perform();
                    Thread.sleep(2000);

                }
            }
            // Going back to the main window
            driver.switchTo().window(parentWindow);
            Thread.sleep(2000);
            driver.quit();


        }
    }

