package testBase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
public static WebDriver driver;
public Logger logger;
public Properties p;
	
	@BeforeClass(groups = {"Sanity","Regression","master"})
	@Parameters({"os","browser"})
	public void setup(String os,String br) throws IOException
	{
		FileReader file=new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		logger=LogManager.getLogger(this.getClass());
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities desiredCapabilities=new DesiredCapabilities();
			if (os.equalsIgnoreCase("Windows")) 
			{
				desiredCapabilities.setPlatform(Platform.WIN11);
			}
			else if (os.equalsIgnoreCase("mac"))
			{
				desiredCapabilities.setPlatform(Platform.MAC);
			}
			else if (os.equalsIgnoreCase("linux"))
			{
				desiredCapabilities.setPlatform(Platform.LINUX);
			}
			else 
			{
				System.out.println("No matching os");
				return;
			}
			switch (br.toLowerCase()) {
			case "chrome":desiredCapabilities.setBrowserName("chrome");break;
			case "edge":desiredCapabilities.setBrowserName("MicrosoftEdge");break;
			case "firefox":desiredCapabilities.setBrowserName("firefox");break;
			

			default:
				System.out.println("No matching os");
				break;
			}
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),desiredCapabilities);
		
			
		}
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
		switch (br.toLowerCase()) {
		case "chrome": driver=new ChromeDriver();break;
		case "edge": driver=new EdgeDriver();
		case "firefox": driver=new FirefoxDriver();break;
		default:System.out.println("Invalid broser name");return;
			
		}
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize();
		
	}
	@AfterClass(groups = {"Sanity","Regression","master"})
	public void teardown()
	{
		driver.quit();
		
	}
	public String randomeString() {
		String generateString= RandomStringUtils.randomAlphabetic(5);
		return generateString;
	}
	public String randomeNumber() {
		String generateNumber= RandomStringUtils.randomNumeric(10);
		return generateNumber;
	}
	public String randomeAlfaNumaric() {
		
		String generateString= RandomStringUtils.randomAlphabetic(5);
		String generateNumber= RandomStringUtils.randomNumeric(3);
		return (generateString+"@"+generateNumber);
	}
	
	public String captureScreen(String tname) {
		String timeStamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takesScreenshot=(TakesScreenshot)driver;
		File sourceFile=takesScreenshot.getScreenshotAs(OutputType.FILE);
		String targetFilePath=System.getProperty("user.dir")+"\\screenshorts\\"+tname+timeStamp;
		File targetFile=new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;
		
		
	}

}
