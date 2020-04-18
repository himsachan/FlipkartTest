package com.flipkart.Pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.flipkart.Base.BasePage;

public class CheckoutPageFlow extends BasePage {

	static Logger logger = Logger.getLogger(CheckoutPageFlow.class);

	@FindBy(xpath = ".//input[@class='LM6RPg']")
	public WebElement searchInput;

	@FindBy(xpath = ".//button[@class='vh79eN']")
	public WebElement searchButton;

	@FindBy(xpath = ".//div[@class='_1HmYoV _35HD7C']//div[@class='bhgxx2 col-12-12']/div/div[2]")
	public WebElement selectProduct;

	@FindBy(xpath = ".//div[@class='_39pMkP']")
	public WebElement sizeChart;

	@FindBy(xpath = ".//table[@class='gY_eQx']")
	public WebElement table;

	// Constructor for initializing page elements
	public CheckoutPageFlow(WebDriver driver) {
		this.driver = driver;
		init(driver);
		PageFactory.initElements(driver, this);
	}

	public boolean searchAndSelectProduct(String product) {
		try {
			try {
				wait.until(ExpectedConditions.elementToBeClickable(searchInput));
				new Actions(driver).moveToElement(searchInput).perform();
				searchInput.sendKeys(product);
			}catch(StaleElementReferenceException e) {
				wait.until(ExpectedConditions.elementToBeClickable(searchInput));
				new Actions(driver).moveToElement(searchInput).perform();
				searchInput.sendKeys(product);
			}
			searchButton.click();
			wait.until(ExpectedConditions.visibilityOf(selectProduct));
			selectProduct.click();
			Thread.sleep(500);
			if (windowhandle(driver).equals("true")) {
				wait.until(ExpectedConditions.elementToBeClickable(sizeChart));
				sizeChart.click();
			} else {
				logger.error("Unable to navigate to new window");
				return false;
			}
			return true;
		} catch (Exception e) {
			logger.error("Error in searchAndSelectProduct :" + e.getMessage());
			return false;
		}
	}

	public void printAllSize() {
		List<WebElement> rowsList = table.findElements(By.tagName("tr"));
		List<WebElement> columnsList = null;
		logger.info("Size Chart values :");
		for (WebElement row : rowsList) {
			columnsList = row.findElements(By.tagName("td"));
			String size = columnsList.get(0).getText();
			System.out.println(size);
			logger.info(size);
			if (size.equals("XL")) {
				System.out.println("Chest :" + columnsList.get(1).getText());
				System.out.println("Brand Size :" + columnsList.get(2).getText());
				System.out.println("Shoulder :" + columnsList.get(3).getText());
			}
		}
	}
}
