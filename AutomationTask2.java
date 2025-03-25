import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EcomAutomation {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://amazon.in");
        driver.manage().window().maximize();
        WebElement input = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
        input.sendKeys("toys");
        input.sendKeys(Keys.ENTER);
        JavascriptExecutor jsx = (JavascriptExecutor)driver;
        jsx.executeScript("window.scroll(0,250)");

        WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(8));
        WebElement item1 = wt.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='a-autoid-3-announce']")));
        item1.click();
        WebElement item2 = wt.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='a-autoid-4-announce']")));
//        item2.click();
        WebElement item3 = wt.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='a-autoid-5-announce']")));
//        item3.click();
        WebElement item4 = wt.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='a-autoid-6-announce']")));
        item4.click();
        WebElement cart = wt.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/gp/cart/view.html?ref_=nav_cart']")));
        cart.click();
//
        System.out.println(driver.findElement(By.xpath("//div[@class='sc-badge-price-to-pay sc-apex-cart-price-to-pay']//span[@class='a-offscreen']")).getText());
    }
}
