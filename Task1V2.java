package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Task1V2 {
    public static void main(String[] args) throws Exception{
       https://www.investing.com/
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.investing.com/");
        driver.manage().window().maximize();

        JavascriptExecutor jsx = (JavascriptExecutor) driver;
        jsx.executeScript("window.scroll(0,1050)");


        TreeMap<Double, String> mpp = new TreeMap<>();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        List<WebElement> stockContainer = driver.findElements(By.xpath("//tr[@class='datatable-v2_row__hkEus dynamic-table-v2_row__ILVMx']"));
        for (int i=0;i<10;i++) {

            WebElement changeTd = stockContainer.get(i).findElement(By.xpath(".//td[contains(text(),'%')]"));

            String changeText = changeTd.getText().trim(); // e.g., "+0.74%"

            // Check if the change is positive
            if (changeText.startsWith("+")) {
                double change = Double.parseDouble(changeText.replace("+", "").replace("%", ""));

                // Optionally: extract stock name or title from <a> tag inside this row
                String stockTitle = stockContainer.get(i).findElement(By.xpath(".//a")).getAttribute("title");
                js.executeScript("arguments[0].style.border='2px solid lime'; arguments[0].style.background='rgba(144,238,144,0.3)';", stockContainer.get(i));
                System.out.println("Stock: " + stockTitle + " " + changeText);
                mpp.put(change, stockTitle);
            }


        }


            double topChange = mpp.lastKey();
            String topStock = mpp.get(topChange);
            System.out.println("Most positive stock: " + topStock + " with +" + topChange + "%");

        WebElement topStockElement = driver.findElement(By.xpath("//a[@title='" + topStock + "']"));

        js.executeScript(
                "arguments[0].style.border='2px solid lime'; arguments[0].style.background='rgba(144,238,144,0.3)';arguments[0].style.fontWeight='bold';",
                topStockElement
        );
        Thread.sleep(3000);
        topStockElement.click();
        Thread.sleep(3000);
        String h1 = driver.findElement(By.xpath("//h1")).getText();
        System.out.println(h1);
        System.out.println(topStock);
        if (h1.startsWith(topStock)) {
            System.out.println("Validated");
        }
        TakesScreenshot ts = (TakesScreenshot) driver;
        File srcFile = ts.getScreenshotAs(OutputType.FILE);
        File destFile = new File("Extracted Stock.png");
        FileHandler.copy(srcFile, destFile);

    }

    }
}
