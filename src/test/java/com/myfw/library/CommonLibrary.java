
package com.myfw.library;

import com.myfw.Utility.GetAndSetdatafromExcel;
import io.cucumber.java.Scenario;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.ConfigurationFactory;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.time.Duration;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CommonLibrary {
	public static WebDriver driver = null;
	public static WebElement element = null;
	static WebDriverWait browserWithElementWait = null;
	public static String systemUsername = null;
	public static Configuration config = null;
	static String destDir;
	public static String driverpath = System.getProperty("user.dir");
	public static String URL = null;
	public static String mainWindow = null;
	public static String Latesprojectpath = driverpath.replaceAll("\\\\", "\\\\\\\\");
	public static String chromepathLatest = Latesprojectpath + File.separator + "\\Killdriver" + File.separator
			+ "\\Killchrome1.bat";
	public static FileWriter reportFile = null;

	public CommonLibrary() throws ConfigurationException {
		ConfigurationFactory factory = new ConfigurationFactory("src/test/resources/config/config.xml");
		config = factory.getConfiguration();
	}

	/*
	 * 
	 * Method to Quit application
	 * 
	 */

	public static void closeApplication() throws InterruptedException {

		// driver.quit();
//		driver.close();

	}


	public static void takeScreenShot(Scenario scenario) throws IOException {
		final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		scenario.attach(screenshot, "image/png", "image");
	}

	public static void dynamicDataValidation_regExpression(String pattern, String objectProperty) {

		element = getElementByProperty(objectProperty, driver);
		String actualText = element.getText();
		try {
			Pattern r = Pattern.compile(pattern);

			// Now create matcher object.
			Matcher m = r.matcher(actualText);
			if (m.find()) {
				System.out.println("The format of value is as expected " + m.group(0));

			} else {

				throw new Exception("NO MATCHING DATA FOUND");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void Assert(boolean Method, String Result) throws Exception {
		boolean e1 = Method;

		if (e1 == true) {
			System.out.println(Result);
		} else {

			CommonLibrary.closeApplication();
			throw new Exception(Result + ": False");

		}
	}


	/*
	 * 
	 * Method to Launch Application
	 * 
	 */
	public static void App_Launch() throws IOException {

		Object getConfigUrl = config.getProperty("applicationURL");

		/*
		 * systemUsername = System.getProperty("user.name"); String envBrowser =
		 * System.getProperty("envBrowser"); String[] parts = envBrowser.split("-");
		 * String env = parts[0]; // 004 String browser = parts[1];
		 */
		String browser = "chrome";

		if (browser.contains("chrome")) {
			System.getProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/Driver/chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-dev-shm-usage");
			//options.setExperimentalOption("useAutomationExtension", false);
			if (browser.contains("headless")) {
				options.addArguments("headless");
				options.addArguments("window-size=1200x800");
			}
			driver = new ChromeDriver(options);
			driver.manage().deleteAllCookies();
		} else if (browser.contains("firefox")) {

			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/Driver/geckodriver");
			FirefoxOptions options = new FirefoxOptions();
			if (browser.contains("headless")) {
				options.addArguments("-headless");
				options.addArguments("window-size=1200x800");
			}
			driver = new FirefoxDriver(options);
			driver.manage().deleteAllCookies();
		}



		// driver = new HtmlUnitDriver();
		URL = getConfigUrl.toString();
		System.out.println(URL);
		driver.get(URL);

	}

	public static void App_Launch1() throws IOException {

		Object getConfigUrl = config.getProperty("applicationURL");
		systemUsername = System.getProperty("user.name");
		String envBrowser = System.getProperty("envBrowser");
		String[] parts = envBrowser.split("-");
		String env = parts[0]; // 004
		String browser = parts[1];

		if (browser.contains("chrome")) {
			System.getProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "/DriverJenkins/chromedriver");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-dev-shm-usage");
			options.setExperimentalOption("useAutomationExtension", false);
			if (browser.contains("headless")) {
				options.addArguments("headless");
				options.addArguments("window-size=1200x800");
			}
			driver = new ChromeDriver(options);
			driver.manage().deleteAllCookies();
		} else if (browser.contains("firefox")) {

			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/DriverJenkins/geckodriver");
			FirefoxOptions options = new FirefoxOptions();
			if (browser.contains("headless")) {
				options.addArguments("-headless");
				options.addArguments("window-size=1200x800");
			}
			driver = new FirefoxDriver(options);
			driver.manage().deleteAllCookies();
		}


		// driver = new HtmlUnitDriver();
		// URL = "https://uat:watchoutforsharks@uat.purple.com/";
		URL = "https://purple" + env + getConfigUrl.toString();
		System.out.println(URL);
		driver.get(URL);

	}



	public static void closeReportFile() throws IOException {
		reportFile.flush();
		reportFile.close();
	}


	public static void killChromeSession() throws Throwable {
		String os = System.getProperty("os.name");
		if (os != null && os.contains("Mac")) {
			String target = "Killdriver/Killchrome2.sh";
			Runtime rt = Runtime.getRuntime();
			Process proc = rt.exec(target);
			proc.waitFor();
			StringBuffer output = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String line = "";
			while ((line = reader.readLine()) != null) {
				output.append(line + "\t");
				System.out.println("#" + output);
			}
		} else if (os != null && os.contains("Window")) {

			Runtime.getRuntime().exec("cmd /c start " + chromepathLatest);
		} else {
			System.out.println("OS is not supported to kill Chrome session");
		}
	}

	/*
	 * Method for Text validation
	 */

	public static boolean text_Validation(String objectProperty, String expectedText) {
		boolean Textvalidation = false;
		try {
			element = getElementByProperty(objectProperty, driver);
			String actualText = element.getText().replace('\n', ' ');
			if (actualText.equalsIgnoreCase(expectedText)) {
				Textvalidation = true;
				// System.out.println("Text expected and actual text are Same:" + actualText);
			} else {
				System.out.println(" Text expected and actual text are not Same:");
				System.out.println(" Text - Actual : " + actualText);
				System.out.println(" Text -Expected : " + expectedText);

			}
		} catch (Exception e) {

		} finally {
		}
		return Textvalidation;
	}

	// Added temporarily
	public static boolean text_ValidationUsingContains(String objectProperty, String expectedText) {
		boolean Textvalidation = false;
		try {
			element = getElementByProperty(objectProperty, driver);
			String actualText = element.getText().replace('\n', ' ');
			if (actualText.contains(expectedText)) {
				Textvalidation = true;
				// System.out.println(" Text expected and actual text are Same:" + actualText);
			} else {
				System.out.println(" Text expected and actual text are not Same:");
				System.out.println(" Text - Actual : " + actualText);
				System.out.println(" Text -Expected : " + expectedText);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return Textvalidation;
	}

	/*
	 * Method for Image Accessibility
	 */

	public static void accessibilityValidation(String objectProperty, String Text, String imgName) {
		try {
			element = getElementByProperty(objectProperty, driver);
			String alt = element.getAttribute("alt");
			if (alt.equalsIgnoreCase(Text)) {
				System.out.println(imgName + " Image accessibility expected and actual name are Same");
			} else {
				System.out.println(imgName + " Image accessibility expected and actual name are not Same");
				System.out.println("Accessibility-Actual : " + alt);
				System.out.println("Accessibility-Expected : " + Text);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	/*
	 * Method for Link Text Validation
	 */

	public static void linkText_Validation(String objectProperty, String Text) {
		try {
			element = getElementByProperty(objectProperty, driver);
			String linkText = element.getText();

			if (linkText.equalsIgnoreCase(Text)) {
				System.out.println("Link Text expected and actual text are Same");
			} else {
				System.out.println("Link Text expected and actual text are not Same");
				System.out.println("Link Text - Actual : " + linkText);
				System.out.println("Link Text -Expected : " + Text);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	/*
	 * Method for performance validation
	 */



	/*
	 * Method for identifying Web-element by ID/CLASS/NAME/XPATH
	 */

	public static WebElement getElementByProperty(String objectProperty, WebDriver webDriver) {
		element = null;

		String propertyType = null;
		 browserWithElementWait = null;
		browserWithElementWait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
		try {

			propertyType = StringUtils.substringAfter(objectProperty, "~");
			objectProperty = StringUtils.substringBefore(objectProperty, "~");
			if (propertyType.equalsIgnoreCase("CSS")) {
				element = browserWithElementWait
						.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(objectProperty)));
				highlightElement(element, webDriver);
			} else if (propertyType.equalsIgnoreCase("ID")) {
				element = browserWithElementWait
						.until(ExpectedConditions.visibilityOfElementLocated((By.id(objectProperty))));
				// highlightElement(webElement, browser);
			} else if (propertyType.equalsIgnoreCase("NAME")) {
				element = browserWithElementWait
						.until(ExpectedConditions.visibilityOf(driver.findElement(By.name(objectProperty))));
				highlightElement(element, webDriver);
			} else if (propertyType.equalsIgnoreCase("LINKTEXT")) {
				element = browserWithElementWait
						.until(ExpectedConditions.visibilityOf(driver.findElement(By.linkText(objectProperty))));
				highlightElement(element, webDriver);
			} else if (propertyType.equalsIgnoreCase("CLASS")) {
				element = browserWithElementWait
						.until(ExpectedConditions.visibilityOf(driver.findElement(By.className(objectProperty))));
				highlightElement(element, webDriver);
			} else {
				element = browserWithElementWait
						.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(objectProperty))));

			}
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", element);
		} catch (Exception e) {

		}

		return element;
	}

	/*
	 * Common Methods for Element Verification
	 */

	public static boolean isElementPresent(String objectProperty) throws Exception {
		boolean isElementPresent = false;

		try {
			element = getElementByProperty(objectProperty, driver);
			if (element != null) {
				isElementPresent = true;
			} else {
				// throw new Exception("Object Couldn't be retrieved and
				// verified");
			}
		} catch (Exception e) {
			System.out.println(e);

		}
		return isElementPresent;
	}

	public static boolean isElementNotPresentVerification(String objectProperty) throws Exception {
		boolean isElementPresent = false;

		try {
			element = getElementByProperty(objectProperty, driver);
			if (element == null) {
				isElementPresent = true;
				// t2=System.currentTimeMillis();
			} else {
				throw new Exception("Element Present");
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return isElementPresent;
	}

	public static boolean isElementSelected(String objectProperty) throws Exception {
		boolean isElementSelected = false;
		try {
			element = getElementByProperty(objectProperty, driver);
			if (element.isSelected()) {
				isElementSelected = true;
			} else {
				throw new Exception("Given Element not selected");
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

		return isElementSelected;

	}

	public static boolean isElementDisplayed(String objectProperty) throws Exception {
		boolean isElementDisplayed = false;
		try {
			element = getElementByProperty(objectProperty, driver);
			if (element.isEnabled()) {
				isElementDisplayed = true;
			} else {
				throw new Exception("Given Element is avilable");
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

		return isElementDisplayed;

	}

	// To check the Element is Enable and disabled

	public static boolean isElementEnabled(String objectProperty, String elementState) throws Exception {
		boolean isElementEnable = false;
		try {
			element = getElementByProperty(objectProperty, driver);
			String att = element.getAttribute("enabled");
			if (elementState.equals("enabled")) {
				if (att.equals("true")) {
					isElementEnable = true;
					System.out.println("Element is :" + elementState);
				} else {
					throw new Exception("Given Element is avilable");
				}
			}

			if (elementState.equals("disabled")) {
				if (att.equals("false")) {
					isElementEnable = true;
					System.out.println("Element is :" + elementState);
					// t2=System.currentTimeMillis();
				} else {
					throw new Exception("Given Element is avilable");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

		return isElementEnable;

	}

	/*
	 * Created this method to verify if an element is not clickable and this in turn
	 * verify that the element is also not editable
	 * 
	 */
	public static boolean isElementNotClickable(String objectProperty, String elementState) throws Exception {
		boolean isElementclickable = false;
		try {
			element = getElementByProperty(objectProperty, driver);
			String att = element.getAttribute("clickable");
			if (elementState.equals("clickable")) {
				if (att.equals("false")) {
					isElementclickable = true;
					System.out.println("Element is Not: " + elementState);
				} else {
					throw new Exception("Given Element clickable");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		return isElementclickable;

	}

	public static boolean moveToElement(String objectProperty) throws Exception {
		boolean isElementPresent = false;

		try {
			element = getElementByProperty(objectProperty, driver);
			if (element != null) {
				Actions action = new Actions(driver);
				action.moveToElement(element).pause(1000).build().perform();
				isElementPresent = true;
			} else {
				// throw new Exception("Object Couldn't be retrieved and
				// verified");
			}
		} catch (Exception e) {
			System.out.println(e);

		}
		return isElementPresent;
	}

	public static boolean moveToElementAndClick(String objectProperty) throws Exception {
		boolean isElementPresent = false;

		try {
			element = getElementByProperty(objectProperty, driver);
			if (element != null) {
				Actions action = new Actions(driver);
				action.moveToElement(element).build().perform();
				action.click().build().perform();
				isElementPresent = true;
			} else {
				// throw new Exception("Object Couldn't be retrieved and
				// verified");
			}
		} catch (Exception e) {
			System.out.println(e);

		}
		return isElementPresent;
	}

	/*
	 * Method for Entering text in to a field
	 */

	public static boolean clearAndEnterText(String objectProperty, String Text) throws IOException {
		boolean isTextEnteredResult = false;

		try {
			if ("-".equals(Text)) {
				// ignore this field
				isTextEnteredResult = true;
			} else {
				WebElement textBox = getElementByProperty(objectProperty, driver);
				textBox.clear();
				Thread.sleep(1000);
				textBox.sendKeys(Text);
				isTextEnteredResult = true;

			}
		} catch (Exception e) {
			e.printStackTrace();
			;
		}
		return isTextEnteredResult;
	}

	/*
	 * Method for Highlight the Elements
	 */
	public static void highlightElement(WebElement element, WebDriver webDriver) {
		for (int i = 0; i < 1; i++) {
			JavascriptExecutor js = (JavascriptExecutor) webDriver;
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
					"color: black; border: 3px solid black;");

		}
	}

	/*
	 * Method for Click Element if available
	 */

	public static boolean isElementClick(String objectProperty) throws InterruptedException {
		boolean isVerifiedAndClicked = false;

		browserWithElementWait = new WebDriverWait(driver, Duration.ofSeconds(60));
		try {
			element = getElementByProperty(objectProperty, driver);
			browserWithElementWait.until(ExpectedConditions.elementToBeClickable(element));

			if (element != null) {

				element.click();
				isVerifiedAndClicked = true;

			} else {
				throw new Exception("Object Couldn't be retrieved and clicked");
			}
		} catch (Exception e) {
			element = null;
		}
		return isVerifiedAndClicked;
	}

    public static WebElement elementOfElement(WebElement welement, String objectProperty) throws InterruptedException {
        String propertyType = null;
        browserWithElementWait = new WebDriverWait(driver, Duration.ofSeconds(60));
        try {
            propertyType = StringUtils.substringAfter(objectProperty, "~");
            objectProperty = StringUtils.substringBefore(objectProperty, "~");
            if (propertyType.equalsIgnoreCase("CSS")) {
                element = welement.findElement(By.cssSelector(objectProperty));
            } else if (propertyType.equalsIgnoreCase("ID")) {
                element = welement.findElement(By.id(objectProperty));
            } else if (propertyType.equalsIgnoreCase("NAME")) {
                element = welement.findElement(By.name(objectProperty));
            } else if (propertyType.equalsIgnoreCase("LINKTEXT")) {
                element = welement.findElement(By.linkText(objectProperty));
            } else if (propertyType.equalsIgnoreCase("CLASS")) {
                element = welement.findElement(By.className(objectProperty));
            } else {
                element = welement.findElement(By.xpath(objectProperty));
            }

        } catch (Exception e) {
            element = null;
        }
        return element;
    }

	public static boolean isElementClickJavaScript(String objectProperty) throws InterruptedException {
		boolean isVerifiedAndClicked = false;

		browserWithElementWait = new WebDriverWait(driver, Duration.ofSeconds(60));
		try {
			element = getElementByProperty(objectProperty, driver);
			browserWithElementWait.until(ExpectedConditions.elementToBeClickable(element));

			if (element != null) {

				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", element);
				isVerifiedAndClicked = true;

			} else {
				throw new Exception("Object Couldn't be retrieved and clicked");
			}
		} catch (Exception e) {
			element = null;
		}
		return isVerifiedAndClicked;
	}

	public static boolean selectFromDropDown(String objectProperty,String dropdownValue) throws InterruptedException {
		boolean selectDropdownValue = false;

		browserWithElementWait = new WebDriverWait(driver, Duration.ofSeconds(60));
		try {
			element = getElementByProperty(objectProperty, driver);
			//browserWithElementWait.until(ExpectedConditions.elementToBeClickable(element));

			if (element != null) {
				Select select = new Select(element);
				select.selectByVisibleText(dropdownValue);
				System.out.println(dropdownValue+ " Selected from dropdown");
				Thread.sleep(5000);

			} else {
				throw new Exception("Object Couldn't be retrieved and Selected");
			}
		} catch (Exception e) {
			element = null;
		}
		return selectDropdownValue;
	}

	// switch to frame
	public static boolean switchtoFrame(String frameid) throws Exception {
		boolean switchtoframe = false;

		browserWithElementWait = new WebDriverWait(driver, Duration.ofSeconds(60));
		browserWithElementWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameid));
		try {

			if (frameid != null) {

				driver.switchTo().frame(frameid);
				switchtoframe = true;
				System.out.println(switchtoframe);

			} else {
				throw new Exception("Frame Couldn't be retrieved");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return switchtoframe;
	}

	// switch to default content
	public static boolean switchtodefault() throws Exception {
		boolean switchtoframe = false;

		try {

			driver.switchTo().defaultContent();
			switchtoframe = true;

		} catch (Exception e) {
			throw new Exception("Frame Couldn't be retrieved");
		}
		return switchtoframe;
	}

	/*
	 * Methods for fetching excel data
	 */

	public static String getXlsTestData(String sheetName,String colName,String rownum) throws Exception {
		int rowNum = Integer.parseInt(rownum);
		GetAndSetdatafromExcel excelReader = new GetAndSetdatafromExcel();
		excelReader.setExcelFile(sheetName);
		String testData = excelReader.getCellData(colName, rowNum);


		return testData;
	}

	public static void setXlsData(String sheetName,String iteration,String colName,String value) throws Exception {
		GetAndSetdatafromExcel excelWriter = new GetAndSetdatafromExcel();
		excelWriter.setExcelFile(sheetName);
		excelWriter.setCellData(iteration,colName,value);


	}
	
	 


	public static void switchToNewWindow() throws Throwable {
		// killChromeSession();
		try {
			Set<String> windowNames = driver.getWindowHandles();
			for (String windowName : windowNames) {

				if (windowName != null) {
					driver.switchTo().window(windowName);
				} else {
					throw new Exception("New window could not be retrived");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void switchToMainWindow() throws Throwable {
		// killChromeSession();
		try {
			driver.switchTo().window(mainWindow);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String getMainWindow() throws Throwable {
		String mainWindow = null;
		try {
			mainWindow = driver.getWindowHandle();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mainWindow;
	}

	public static void shortSleep() throws Throwable {
		// killChromeSession();
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void longSleep() throws Throwable {
		// killChromeSession();
		try {
			Thread.sleep(8000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}