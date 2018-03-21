package com.hybrid.framework.reports;

import java.text.SimpleDateFormat;

public class TimeTaken {
	
public static SimpleDateFormat sdf = new SimpleDateFormat("ss");

	
	public static String getCurrentTime(String startTime, String endTime){
		
		

		int timetaken = Integer.valueOf(endTime)-Integer.valueOf(startTime);
		if(timetaken>60){
		timetaken = timetaken/60;
		return timetaken+" Min(s)";
		}else{
			return timetaken + " sec(s)";
	}
	
	}
	
	
	

}



Use:
1. public synchronized WebElement selectByLocatorType(String getValueFromPOM){

		WebElement element = null;
		if(getValueFromPOM==null){
			ExtentTestManager.reportStepFail(driver, "Given Locator or key is not available in the Page Object Class. Check Component or POM.", false);
			return null;
		}
		
		String locatorFromPOM=getValueFromPOM;

		try{
			String locatorType = getValueFromPOM.split("#")[0];
			String locatorValue = getValueFromPOM.split("#")[1];
			
			String loc = locatorType;
			switch(loc.toLowerCase()){
			case "id":
				element = driver.findElement(By.id(locatorValue));
				break;
			case "xpath":
				element = driver.findElement(By.xpath(locatorValue));
				break;
			case "css":
				element = driver.findElement(By.cssSelector(locatorValue));
				break;
			case "classname":
				element = driver.findElement(By.className(locatorValue));
				break;
			case "name":
				element = driver.findElement(By.name(locatorValue));
				break;
			case "linktext":
				element = driver.findElement(By.linkText(locatorValue));
				break;
			case "tagname":
				element = driver.findElement(By.tagName(locatorValue));
				break;
			case "partiallinktext":
				element = driver.findElement(By.partialLinkText(locatorValue));
				break;
			default:
				throw new IllegalArgumentException("Check the given locator Type in POM");
			}	
		}catch(StaleElementReferenceException e){
			return selectByLocatorType(locatorFromPOM);
		}catch (NoSuchElementException e) {
			ExtentTestManager.reportStepInfo("No such element '"+ getValueFromPOM +"' found or dislayed");
			//return (WebElement) e;
			return null;
		}catch (Exception e) {
			ExtentTestManager.reportStepInfo("Exception occured while finding the element'"+ getValueFromPOM +"'. Exception is "+e);
			//return (WebElement) e;
			return null;
		}

		//for ie browser and chrome bring the element to view and perform any actions
		if(element!=null){
			String browserName = browserProperty.getProperty("testBrowser");
			if (browserName.equalsIgnoreCase("firefox")||browserName.equalsIgnoreCase("ie")) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {}
			}
		}

		return element;
	}

2. String browserName=browserProperty.getProperty("testBrowser");
		if (browserName.equalsIgnoreCase("chrome")||browserName.equalsIgnoreCase("ie")) {
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e2) {
				e2.printStackTrace();
			}
			Date s = new GregorianCalendar().getTime();
			ExtentTestManager.reportStepPass("Page load Start Time " + s);
			//Report_Functions.ReportEventSuccess(doc,"4","","Page load Start Time " + s,3);
			int flag = 0;
			String docStatus;
			JavascriptExecutor js = (JavascriptExecutor) driver;
			try {
				for (int i = 0; i < 60; i++) {
					if (js.executeScript("return document.readyState").toString().equals("complete")) {

						flag = 1;
						break;
					}

					else {
						docStatus = js.executeScript("return document.readyState").toString();
						Thread.sleep(1000);
						// Report_Functions.ReportEventSuccess(doc,"4","","Else condition page loading status :" +docStatus, 3);
						ExtentTestManager.reportStepPass("Else condition page loading status :" + docStatus);
					}

				}

			} catch (InterruptedException e) {
				log.info("InterruptedException Occured " + e);

			} 
			
3. WebDriverWait wait = new WebDriverWait(driver,intElementDisplayTimeout);
			
			switch(locatorType.toLowerCase()){

			case "id":	element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
			if(element.isDisplayed()) functionStatus = true;
			else functionStatus = false;
			break;

			case "xpath": element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
			if(element.isDisplayed()) functionStatus = true;
			else functionStatus = false;
			break;

			case "css": element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
			if(element.isDisplayed()) functionStatus = true;
			else functionStatus = false;
			break;

			case "classname": element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locatorValue)));
			if(element.isDisplayed()) functionStatus = true;
			else functionStatus = false;
			break;

			case "name": element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
			if(element.isDisplayed()) functionStatus = true;
			else functionStatus = false;
			break;

			case "linktext": element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue)));
			if(element.isDisplayed()) functionStatus = true;
			else functionStatus = false;
			break;

			case "tagname": element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(locatorValue)));
			if(element.isDisplayed()) functionStatus = true;
			else functionStatus = false;
			break;

			case "partiallinktext": element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(locatorValue)));
			if(element.isDisplayed()) functionStatus = true;
			else functionStatus = false;
			break;
			default:
				functionStatus = false;
				throw new IllegalArgumentException("Check the Given Locator Type");
			}

		}catch(NullPointerException e){
			ExtentTestManager.reportStepFail(driver,"Exception occured while locationg the Locator '"+strLocator+"'. Exception is "+e,false);
			return false;
		}

4. WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			//alert.getText();
			alert.accept();

5. WebElement element = selectByLocatorType(getValueFromPOM);
			Select se = new Select(element);
			se.selectByVisibleText(testData);

6. JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", selectByLocatorType(getValueFromPOM));

7. Until Disappear:
Thread.sleep(500);
			if(selectByLocatorType(getValueFromPOM).isDisplayed()){
				for(int i=0; i<90; i++){
					Thread.sleep(1000);
					if(!selectByLocatorType(getValueFromPOM).isDisplayed()){
						disappear = true;
						Thread.sleep(1000);
						ExtentTestManager.reportStepPass("'The Element '"+ strTestObject +"' is not appearing in the Page");
						break;
					}
				}
				
				
8. selectedValue = new Select(selectByLocatorType(getValueFromPOM)).getFirstSelectedOption().getText();
9. JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("document.getElementById('"+getValueFromPOM+"').value = '"+currentDate+"'");
				
10. WebElement elementenable=selectByLocatorType(getValueFromPOM);
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", elementenable);
				
11. ((JavascriptExecutor)driver).executeScript("arguments[0].click()",selectByLocatorType(getValueFromPOM));
				
12. WebEditEnterCalenderText
	JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].setAttribute('onkeydown',arguments[1]);",selectByLocatorType(getValueFromPOM),"return true;");
			selectByLocatorType(getValueFromPOM).sendKeys(strData);
				
13. filepath = strData + filename;
			log.info("filepath is "+filepath);
			//Set the Path for DLL
			File file = new File("lib", "jacob-1.14.3-x86.dll");
			//Get the absolute path for DLL
			System.setProperty(LibraryLoader.JACOB_DLL_PATH, file.getAbsolutePath());
			//Get the Jacob DLL path from local
			File jacobDLLPath = new File(property.getProperty("AutoIT_Jacob_DLL_Path"));
			String dLLAbsolutePath = jacobDLLPath.getAbsolutePath();
			//Get the Jacob DLL absolute path
			System.setProperty(LibraryLoader.JACOB_DLL_PATH, dLLAbsolutePath);
			LibraryLoader.loadJacobLibrary();
			File localPath = new File(filepath);
			//Get the upload file absolute path
			String absoluteFilepath = localPath.getAbsolutePath();
			AutoItX autoIT = new AutoItX();
			
			log.info("browser is "+getBrowserName);

			//Based on Browsers AutoIT tool will be executed
			if(getBrowserName.equalsIgnoreCase("IE")){
				autoIT.winActivate("Choose File to Upload");
				if(autoIT.winWaitActive("Choose File to Upload", "", 10)){
					if(autoIT.winExists("Choose File to Upload")){
						autoIT.sleep(1000);
						autoIT.send(absoluteFilepath);
						autoIT.send("{Enter}",false);	
						log.info("File has been uploaded successfully in IE browser");
						elementStatus = true;
					}
				}
				elementStatus = true;
			} 
				
14. Wait for file to download
	while(true){
				File file = new File(System.getProperty("user.dir")+property.getProperty(strdownloadfilepath)+"\\");
				File files[] = file.listFiles();
				if(files.length > 0){

					first = files[0].length()/1024;
					Thread.sleep(1000);
					file = new File(System.getProperty("user.dir")+property.getProperty(strdownloadfilepath)+"\\");
					files = file.listFiles();
					second = files[0].length()/1024;
					if(first != second){
					}else{
						files[0].renameTo(new File(System.getProperty("user.dir")+property.getProperty(strdownloadfilepath)+"\\"+strData));
						iflag = 1;
						break;
					}
				}
			}
				
15. javaScriptDatePicker
	JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("document.getElementById('"+getValueFromPOM+"').value = '"+elementValue+"'");
				
				
16. public synchronized String Login_Page(String locator){

		try{
			Hashtable<String, String> hs = new Hashtable<String, String>();		
			hs.put("txtbox_UserName", "id#UserName");
			hs.put("txtbox_Password", "id#Password");
			hs.put("btn_LogIn", "id#Login");
			return hs.get(locator);
		}catch(Exception e){
			System.out.println("Error occurred in POM classes :"+e);
			return null;
		}

	}				
17. public static String getScreenshot(String xpath) throws IOException{
		
		
		WebElement autoCapt = driver.findElement(By.xpath(xpath));   
		//Get entire page screenshot
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		BufferedImage bi = ImageIO.read(screenshot);
		//Get the location of element on the page
		Point p = autoCapt.getLocation();
		bi.getTransparency();
		Graphics g = bi.getGraphics();
		g.setColor(Color.RED.brighter());
		
		//Get width and height of the element
		g.drawRect(p.getX(), p.getY(), autoCapt.getSize().width, autoCapt.getSize().height);
		g.setFont(new Font("verdana", Font.BOLD, 16));
		g.setColor(Color.RED.brighter());
		g.dispose();
		ImageIO.write(bi, "png", screenshot);
		//Copy the element screenshot to disk
		File newFile = new File(screenPath + Parameterization.testCaseID + ".png");
		FileUtils.copyFile(screenshot, newFile );
		filePath=newFile.getAbsolutePath();
		return filePath;
		
		
	}
	
	

}


//Switch window	
	public static WebDriver switchToWindow(String pageTitle){
		
		for(String id : driver.getWindowHandles()){
			
			driver.switchTo().window(id);
			
			if(driver.getTitle().contains(pageTitle)){
				
				return driver.switchTo().window(id);
				
			}
			
			
		}
		
		return driver;
		
	}
	
//Switch main window
	public static WebDriver mainWindow(){
		
		for(String id : driver.getWindowHandles()){
			
			return driver.switchTo().window(id);
			
		}
		return driver;
		
	}


package com.selenium.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebTables {

    public static WebDriver driver;
    public static String headerColVal =  null;
    public static int i = 0;
    public static List<String> ls = new ArrayList<String>();
    public static List<String> lsSecRow = new ArrayList<String>();
    public static List<String> lsActualVal = new ArrayList<String>();
    
    public static void getValues(){
    
        try{
        
        String primaryHeaderColName = "Country";
        String primaryRowName = "Taipei 101";
        
        String secRow = "Taiwan|Taipei|509m|2004|3|details";
        
        String[] secRowVal = secRow.split("\\|");
        
        for(int secRowValue = 0; secRowValue < secRowVal.length; secRowValue++){
            
            lsSecRow.addAll(Arrays.asList(secRowVal[secRowValue]));
            
        }
        
            System.setProperty("webdriver.chrome.driver", "Input/chromedriver.exe");
            driver = new ChromeDriver();
            driver.get("http://toolsqa.com/automation-practice-table");
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.manage().window().maximize();

            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='content']/table/thead/tr/th")));

            List<WebElement> headerCol = driver.findElements(By.xpath("//div[@id='content']/table/thead/tr/th"));    

            for(i = 1; i < headerCol.size(); i++){

                headerColVal = driver.findElement(By.xpath("//div[@id='content']/table/thead/tr/th["+i+"]")).getText();
                ls.add(headerColVal);

            }

            for(int ii = 0; ii < ls.size(); ii++){
                String vals = ls.get(ii);
                if(vals.equalsIgnoreCase(primaryHeaderColName)){

                    //Get the primary rows names
                    List<WebElement> primaryHeaderRows = driver.findElements(By.xpath("//div[@id='content']/table/tbody/tr/th"));
                    //Get primary row size
                    System.out.println("primaryHeaderRows :"+primaryHeaderRows.size());
                    //Looping through to get the rows
                    for(int priRowNames = 1; priRowNames <= primaryHeaderRows.size(); priRowNames++){

                        String strPrimaryRowsNames =  driver.findElement(By.xpath("//div[@id='content']/table/tbody/tr["+priRowNames+"]/th")).getText();

                        if(strPrimaryRowsNames.equalsIgnoreCase(primaryRowName)){
                            for(int getCol = 1; getCol < headerCol.size(); getCol++){
                                //Get the table data
                                String getColData = driver.findElement(By.xpath("//div[@id='content']/table/tbody/tr["+priRowNames+"]/td["+getCol+"]")).getText();
//                                System.out.println("Column Values :"+getColData);
                                lsActualVal.add(getColData);
                                
                            }
                        }
                    }    
                }
            }

            
            for(int x = 0; x < lsSecRow.size(); x++){
                
                
                for(int y = 0; y < lsActualVal.size(); y++){
                    
                    if(lsSecRow.get(x).equalsIgnoreCase(lsActualVal.get(y))){
                        
                        System.out.println("Actual value is '"+lsActualVal.get(y)+"' matched with the expected value '"+lsSecRow.get(x)+"' ");
                        
                    }
                }
            }
            
            
        }catch(Exception e){

            e.printStackTrace();
        }

        driver.quit();

    }

    
    public static void main(String[] args) {
        
        getValues();
        
    }
    
    
}

//************************
du -sh - > To check the folders size
df -h - > To check the drive details


grep 

xargs

 ps -ef | grep -i rrbs | cut -d " " -f2 | xargs kill -9 

ls -lrt | xargs rm -rf
/**************************

SELECT name, MAX(salary) as salary FROM employee select MAX(FaveValue) as FaveValue from tablename


Read Excel:

String path = "Input/Selenium.xlsx";
        FileInputStream fs = null;
        XSSFWorkbook wb = null;
        XSSFSheet sheet = null;
        XSSFRow row = null;
        XSSFCell cell = null;
        
        try{
            
            fs = new FileInputStream(new File(path));
            wb = new XSSFWorkbook(fs);
            sheet = wb.getSheet("Sheet1");
            
            int rowCount = sheet.getLastRowNum();
            
            for(int i = 0; i < rowCount; i++){
                
                row = sheet.getRow(0);
                int columnCount = row.getLastCellNum();
                
                for(int j = 0; j < columnCount; j++){
                    
                    cell = row.getCell(j);
                    String value = cell.getRichStringCellValue().getString();
                    if(value.equalsIgnoreCase(columnName)){
                        row = sheet.getRow(i);
                        cell = row.getCell(j);
                        System.out.println(cell.getStringCellValue());
                    }
                }
            }
            
        }catch(Exception e){
            
            e.printStackTrace();
            
        }
        
Write Excel:

try{
        
        String excelFileName = "Input/Write.xlsx";
        
        String colAutoScript = "Automation_Script";
        String colStatus = "Status";
        
        FileInputStream fs = new FileInputStream(new File(excelFileName));
        
        XSSFWorkbook wb = new XSSFWorkbook(fs);
        XSSFSheet sheet = wb.createSheet("Sheet2");
        XSSFRow rowHead = sheet.createRow(0);
        rowHead.createCell(0).setCellValue(colAutoScript);
        rowHead.createCell(1).setCellValue(colStatus);
        
        XSSFRow row = sheet.createRow(1);
        row.createCell(0).setCellValue("Test");
        
        
        FileOutputStream fos = new FileOutputStream(excelFileName);
        wb.write(fos);
        fos.close();
        
        }catch(Exception e){
            
            e.printStackTrace();
            
        }

try{
            
            fs = new FileInputStream(path);
            workbook = new XSSFWorkbook(fs);
            sheet = workbook.getSheet("Sheet1");
            
            String[] values = {"one", "two", "three", "four", "five"};
            for(int rowItr=0; rowItr < 1; rowItr ++){
                XSSFRow row = sheet.createRow(rowItr);
                for(int i = 0; i < values.length; i++){
                    row.createCell(i).setCellValue(values[i]);
                }
            }
            //Closing the Workbook
            FileOutputStream outputFile = new FileOutputStream(new File(path));
            workbook.write(outputFile);
            outputFile.close();
            
        }catch(Exception e){
            e.printStackTrace();
        }
		
		
		
Edge Browser:
System.setProperty("webdriver.edge.driver", driverPath+"MicrosoftWebDriver.exe");
driver = new EdgeDriver();
Chrome browser:
ChromeOptions options = new ChromeOptions();
options.addArguments("--no-sandbox");		



Check Browser Capabilities:

public static void checkInIEBrowser(){

        try{

            
            DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
            capabilities.setCapability("ignoreZoomSetting", true);
            capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            
            System.setProperty("webdriver.ie.driver", "Input/IEDriverServer.exe");
            driver = new InternetExplorerDriver(capabilities);
            
//            System.setProperty("webdriver.gecko.driver", "Input/geckodriver.exe");
//            driver = new FirefoxDriver();
            
//            System.setProperty("webdriver.chrome.driver", "Input/chromedriver.exe");
//            driver = new ChromeDriver();
            
            driver.get("https://www.softwaretestingmaterial.com");
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            Thread.sleep(10000);
            getScreenshot("Input/normalScreenshot.png");
            getVerticalScreenshot("Input/normalVerticalScreenshot.png");

        }catch(Exception e){

            e.printStackTrace();

        }

        driver.quit();

    }

    getScreenshot:
	
    public static void getScreenshot(String path){
        
        try{
        
        File scr = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scr, new File(path));
        
        }catch(Exception e){
            
            e.printStackTrace();
        }
    }
    
	getVerticalScreenshot:
	
    public static void getVerticalScreenshot(String path){
        
        try{
        
        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
        ImageIO.write(screenshot.getImage(), "PNG", new File(path));
        
        }catch(Exception e){
            e.printStackTrace();
        }
    }
	
	//ASCII values  of alphabets: A – Z = 65 to 90, a – z = 97 to 122
    public static void upperLowerCase(){
        
        String str = "AutoProcess";
        
        for(int i =0; i < str.length(); i++){
            char c = str.charAt(i);
            if(c >= 'a' && c <= 'z' ){
                c = (char) (c -32);
                System.out.println("Uppercase :"+c);
            }else if(c >= 'A' && c <= 'Z'){
                c = (char) (c + 32);
                System.out.println("Lowercase :"+c);
            }
        }
    }

	dateDifference:
	
public static void dateDifference(){
        
        
        LocalDate fromDate = LocalDate.of(2018, Month.MARCH, 21);
        LocalDate toDate = LocalDate.of(2018, Month.MAY, 23);
        
        long difference = ChronoUnit.DAYS.between(fromDate, toDate);
        System.out.println(difference);
        
    }

	
	childWindow:
	
	public static WebDriver childWindow(String pageTitle){
        
        for(String id : driver.getWindowHandles()){
            driver.switchTo().window(id);
            if(driver.getTitle().contains(pageTitle)){
                return driver.switchTo().window(id);
            }
        }
        return null;
    }
    
    public static WebDriver parentWindow(){
        
        for(String id : driver.getWindowHandles()){
            return driver.switchTo().window(id);
        }
        
        return null;
    }
    
    
    switchTabs:
	
    public static void switchTabs(){
        
        launchBrowser("http://toolsqa.com/automation-practice-switch-windows/");
        waitUntilExist("//button[@onclick='newBrwTab()']");
        driver.findElement(By.xpath("//button[@onclick='newBrwTab()']")).click();
        
        List<String> ls = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(ls.get(1));
        
        waitUntilExist("(//span[@class='menu-text' and contains(text(), 'HOME')])[1]");
        System.out.println(driver.getTitle());
        
        driver.switchTo().window(ls.get(0));
        waitUntilExist("//button[@onclick='newBrwTab()']");
        System.out.println(driver.getTitle());
        
        driver.quit();
        
        Actions actions = new Actions(driver);
        WebElement element = null;
        actions.contextClick(element).click().build().perform();
        
    }
	
	WebTables:
	
	public class WebTables {

    public static WebDriver driver;
    public static String headerColVal =  null;
    public static int i = 0;
    public static List<String> ls = new ArrayList<String>();
    public static List<String> lsSecRow = new ArrayList<String>();
    public static List<String> lsActualVal = new ArrayList<String>();
    
    public static void getValues(){
    
        try{
        
        String primaryHeaderColName = "Country";
        String primaryRowName = "Taipei 101";
        
        String secRow = "Taiwan|Taipei|509m|2004|3|details";
        
        String[] secRowVal = secRow.split("\\|");
        
        for(int secRowValue = 0; secRowValue < secRowVal.length; secRowValue++){
            
            lsSecRow.addAll(Arrays.asList(secRowVal[secRowValue]));
            
        }
        
            System.setProperty("webdriver.chrome.driver", "Input/chromedriver.exe");
            driver = new ChromeDriver();
            driver.get("http://toolsqa.com/automation-practice-table");
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.manage().window().maximize();

            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='content']/table/thead/tr/th")));

            List<WebElement> headerCol = driver.findElements(By.xpath("//div[@id='content']/table/thead/tr/th"));    

            for(i = 1; i < headerCol.size(); i++){

                headerColVal = driver.findElement(By.xpath("//div[@id='content']/table/thead/tr/th["+i+"]")).getText();
                ls.add(headerColVal);

            }

            for(int ii = 0; ii < ls.size(); ii++){
                String vals = ls.get(ii);
                if(vals.equalsIgnoreCase(primaryHeaderColName)){

                    //Get the primary rows names
                    List<WebElement> primaryHeaderRows = driver.findElements(By.xpath("//div[@id='content']/table/tbody/tr/th"));
                    //Get primary row size
                    System.out.println("primaryHeaderRows :"+primaryHeaderRows.size());
                    //Looping through to get the rows
                    for(int priRowNames = 1; priRowNames <= primaryHeaderRows.size(); priRowNames++){

                        String strPrimaryRowsNames =  driver.findElement(By.xpath("//div[@id='content']/table/tbody/tr["+priRowNames+"]/th")).getText();

                        if(strPrimaryRowsNames.equalsIgnoreCase(primaryRowName)){
                            for(int getCol = 1; getCol < headerCol.size(); getCol++){
                                //Get the table data
                                String getColData = driver.findElement(By.xpath("//div[@id='content']/table/tbody/tr["+priRowNames+"]/td["+getCol+"]")).getText();
//                                System.out.println("Column Values :"+getColData);
                                lsActualVal.add(getColData);
                                
                            }
                        }
                    }    
                }
            }

            
            for(int x = 0; x < lsSecRow.size(); x++){
                
                
                for(int y = 0; y < lsActualVal.size(); y++){
                    
                    if(lsSecRow.get(x).equalsIgnoreCase(lsActualVal.get(y))){
                        
                        System.out.println("Actual value is '"+lsActualVal.get(y)+"' matched with the expected value '"+lsSecRow.get(x)+"' ");
                        
                    }
                }
            }
            
            
        }catch(Exception e){

            e.printStackTrace();
        }

        driver.quit();

    }

