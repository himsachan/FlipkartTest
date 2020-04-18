package com.flipkart.Pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.flipkart.Base.BasePage;

public class LoginPage extends BasePage {

	static Logger logger = Logger.getLogger(LoginPage.class);

	@FindBy(xpath = "//input[@class='_2zrpKA _1dBPDZ']")
	public WebElement email;

	@FindBy(xpath = "//input[@type='password']")
	public WebElement password;

	@FindBy(xpath = "//button[@class='_2AkmmA _1LctnI _7UHT_c']")
	public WebElement loginButton;

	public String loginPageUrl;

	// Constructor
	public LoginPage(WebDriver driver, String baseUrl) {
		this.driver = driver;
		init(driver);
		PageFactory.initElements(driver, this);
		loginPageUrl = baseUrl + "account/login?ret=/";

	}

	public boolean login(String userName, String Password) {
		try {
			driver.get(loginPageUrl);
			waitForLoad(driver);
			email.sendKeys(userName);
			password.sendKeys(Password);
			loginButton.click();
			wait.until(ExpectedConditions.visibilityOf(
					driver.findElement(By.xpath("//div[@class='_1jcwFN _3dmQRh']//div[@class='_2aUbKa']"))));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
