package com.flipkart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.flipkart.Base.BaseTest;
import com.flipkart.Pages.CheckoutPageFlow;
import com.flipkart.Pages.LoginPage;

public class FlipkartTest extends BaseTest {

	LoginPage loginPage;
	CheckoutPageFlow checkoutpage;

	String username, password;
	String browser;

	String product = "solid men round neck blue, grey t shirt";

	@BeforeClass
	@Parameters({ "BaseUrl", "webdriver.browser", "userName", "password" })
	public void beforeClass(String baseurl, String browser, String email, String password) {
		this.username = email;
		this.password = password;
		this.browser = browser;
		initlizeDriver(browser);
		loginPage = new LoginPage(driver, baseurl);
		checkoutpage = new CheckoutPageFlow(driver);
	}

	@Test(description = "Verify login, search and size flow")
	public void testFlipkartFlow() throws InterruptedException {
		Assert.assertTrue(loginPage.login(username, password));
		Assert.assertTrue(checkoutpage.searchAndSelectProduct(product));
		checkoutpage.printAllSize();
	}

}
