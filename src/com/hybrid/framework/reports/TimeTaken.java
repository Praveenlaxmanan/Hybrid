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
