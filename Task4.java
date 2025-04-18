package org.example;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;


public class Task4v2 {

    public static void createExcel(){
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Sheet1");
        XSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("Name");
        row.createCell(1).setCellValue("Lastname");
        row.createCell(2).setCellValue("Job_title");
        row.createCell(3).setCellValue("HighestQual");
        row.createCell(4).setCellValue("gender");
        row.createCell(5).setCellValue("YearOfExperience");
        row.createCell(6).setCellValue("Dob");


        row = sheet.createRow(1);
        row.createCell(0).setCellValue("Jaykishan");
        row.createCell(1).setCellValue("Varma");
        row.createCell(2).setCellValue("Developer");
        row.createCell(3).setCellValue("highSchool");
        row.createCell(4).setCellValue("Male");
        row.createCell(5).setCellValue("1");
        row.createCell(6).setCellValue("2000-09-09");


        try {
            FileOutputStream out = new FileOutputStream("createdExcel.xlsx");
            workbook.write(out);
            out.close();
            workbook.close();
        }
        catch (Exception e){
            System.out.println("Exception ");
        }
    }
    public static void main(String[] args)throws Exception {
        createExcel();
        List<List<String>> dataFromExcel = new ArrayList<>();

        FileInputStream file = new FileInputStream("createdExcel.xlsx");
        Workbook workbook1 = new XSSFWorkbook(file);
        Sheet sheet1 = workbook1.getSheet("Sheet1");

        for (int r = 1; r <= 1; r++) {
            List<String> rowData = new ArrayList<>();
            for (int col = 0; col < 7; col++) {
                String data = sheet1.getRow(r).getCell(col).getStringCellValue();
                rowData.add(data);
            }
            dataFromExcel.add(rowData);
        }

        workbook1.close();
        file.close();

        System.out.println(dataFromExcel);
        WebDriver driver = new ChromeDriver();
        driver.get("https://formy-project.herokuapp.com/form");
        driver.manage().window().maximize();
        JavascriptExecutor jsx = (JavascriptExecutor) driver;
        driver.findElement(By.id("first-name")).sendKeys(dataFromExcel.get(0).get(0));
        driver.findElement(By.id("last-name")).sendKeys(dataFromExcel.get(0).get(1));
        driver.findElement(By.id("job-title")).sendKeys(dataFromExcel.get(0).get(2));
        String HighQual = dataFromExcel.get(0).get(3);
//        System.out.println(HighQual);
        String InputXpath;
        if (HighQual.equals("highSchool")){
            InputXpath= "(//input[@type='radio'])[1]";
        } else if (HighQual.equals("College")) {
            InputXpath = "(//input[@type='radio'])[2]";
        }else{
            InputXpath = "(//input[@type='radio'])[3]";
        }
        driver.findElement(By.xpath(InputXpath)).click();

        String gender = dataFromExcel.get(0).get(4);

        if (gender.equals("Male")){
            InputXpath= "(//input[@type='checkbox'])[1]";
        }else if (gender.equals("Female")) {
            InputXpath = "(//input[@type='checkbox'])[2]";
        }else{
            InputXpath = "(//input[@type='checkbox'])[3]";
        }
        driver.findElement(By.xpath(InputXpath)).click();

        WebElement dropdown = driver.findElement(By.id("select-menu"));
        Select sct = new Select(dropdown);
        String s = dataFromExcel.get(0).get(5).toString();
        Integer yearOfExp = Integer.parseInt(s);
        System.out.println(yearOfExp);
        Integer vl=0;
        if (yearOfExp<=1) {
            vl=1;
        } else if (2<yearOfExp && yearOfExp<4) {
            vl=2;
        }else if (5<yearOfExp && yearOfExp<9) {
            vl=3;
        }else  {
            vl=4;
        }

        sct.selectByValue(vl.toString());
        WebElement date = driver.findElement(By.id("datepicker"));
        jsx.executeScript("arguments[0].value = arguments[1];",date,dataFromExcel.get(0).get(6));
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[@role='button']")).click();
        Thread.sleep(2000);

        TakesScreenshot ts = (TakesScreenshot) driver;
        File srcFile = ts.getScreenshotAs(OutputType.FILE);
        File destFile = new File("DatePickerForm.png");
        FileHandler.copy(srcFile, destFile);
    }
}
