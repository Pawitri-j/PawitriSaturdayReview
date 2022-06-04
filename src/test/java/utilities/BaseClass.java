package utilities;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	
	//driver
	private static WebDriver driver;

	public static WebDriver getDriver() {

		if (driver == null) {

			switch (BaseClass.getStringProperty("browser")) {

			case "chrome":
				driver = WebDriverManager.chromedriver().create();
				break;

			case "firefox":
				driver = WebDriverManager.firefoxdriver().create();
				break;

			case "edge":
				driver = WebDriverManager.edgedriver().create();
				break;

			case "safari":
				driver = WebDriverManager.safaridriver().create();
				break;

			case "headless":
				System.out.println("headless");
				break;

			default:
				driver = WebDriverManager.chromedriver().create();
				break;

			}
		}

		return driver;
	}
	
	// close driver
	public static void teardown() {
		if (driver != null) {
			driver.close();
		}
	}
	
	//congfigFile
	private static Properties configFile;
	
	static {
		try {
			
			String path = "./resources/propertiesFolder/Data.properties";
			FileInputStream input = new FileInputStream(path);
			
			configFile = new Properties();
			configFile.load(input);
			
			input.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	//get property method
	public static String getStringProperty(String key) {
		
		return configFile.getProperty(key);
	}

}
