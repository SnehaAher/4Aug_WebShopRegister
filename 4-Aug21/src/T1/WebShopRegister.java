package T1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Appender;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WebShopRegister {
	
WebDriver driver;

Properties pro = new Properties();

static Logger Log = Logger.getLogger(WebShopRegister.class.getName());

@BeforeSuite
public void browser() throws IOException
{
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\sneha\\Downloads\\chromedriver_win32\\chromedriver.exe");
	FileInputStream fis = new FileInputStream("H:\\Sneha2019-03\\4-Aug21\\src\\T1\\abc.properties");
	pro.load(fis);
	System.out.println("Open Browser");
	System.out.println("URL==" + pro.getProperty("url"));
	driver = new ChromeDriver();
	driver.manage().window().maximize();
	System.out.println("Browser Open Successfully..");
	
}

@BeforeTest
public void url()
{
System.out.println("Enter Url");
driver.get(pro.getProperty("url"));
}

@BeforeMethod
public void getcookies()
{
System.out.println("Cookies");	
}

@Test
public void register() throws IOException
{
	BasicConfigurator.configure();
	
	Layout l = new PatternLayout();
	
	Appender ap = new FileAppender(l, "abc.txt");
	
	Log.addAppender(ap);
	
	
//	Log.debug("Debug");
//	Log.info("Info");
//	Log.warn("Warn");
//	Log.error("Error");
//	Log.fatal("Fatal");
	
	System.out.println("Hello Log4j");
	
		System.out.println(pro.getProperty("gender"));
		System.out.println(pro.getProperty("fnm"));
		System.out.println(pro.getProperty("lnm"));
		System.out.println(pro.getProperty("eml"));
		System.out.println(pro.getProperty("pswwd"));
		System.out.println(pro.getProperty("confpaswd"));
		
		if( Integer.valueOf(pro.getProperty("gender")) == 1) {
			driver.findElement(By.xpath("//input[@id='gender-male']")).click();
		} else {
			driver.findElement(By.xpath("//input[@id='gender-female']")).click();
		}
		
		//driver.findElement(By.xpath("//input[@id='gender-female']")).click();
	
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys(pro.getProperty("fnm"));
		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys(pro.getProperty("lnm"));
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(pro.getProperty("eml"));
		driver.findElement(By.xpath("//input[@id='Password']")).clear();
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(pro.getProperty("pswwd"));
		driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).clear();
		driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys(pro.getProperty("confpaswd"));
		driver.findElement(By.xpath("//input[@id='register-button']")).submit();
		
		System.out.println("Registration done Successfully..");
		

}

@AfterMethod
public void CaptureScreenshot() throws IOException
{
File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
FileUtils.copyFile(src, new File("H:\\Sneha2019-03\\4-Aug21\\src\\T1\\Reg.jpg"));
System.out.println("Screenshot Taken Successfully..");
}

@AfterClass
public void cookies()
{
System.out.println("Delete Cookies..");	
}

@AfterSuite
public void close()
{
//driver.close();
System.out.println("Driver close..");
}

}
