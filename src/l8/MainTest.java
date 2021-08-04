package l8;

import org.junit.Test;
import org.junit.Assert;

public class MainTest extends BasicTest{
		
	@Test
	public void inputFieldPresent() {  
			driver.get(baseUrl);
			Assert.assertTrue("The input field is not present.", inputField.isDisplayed());				
	}
	
	@Test
	public void inputFieldEnabled() {
		driver.get(baseUrl);
		Assert.assertTrue("The input field is not enabled.", inputField.isEnabled());
	}
	
	@Test
	public void inputFieldBlank() {
		driver.get(baseUrl);
		Assert.assertTrue("The input field is not blank. ", 
							inputField.getAttribute("value").equals(""));
	}
	
	@Test
	public void inputFieldTextPresent() {
		driver.get(baseUrl);
		String randomTxt = getRandomText(10);
		inputField.sendKeys(randomTxt);
		Assert.assertTrue("The text is not present in input field.", 
							inputField.getAttribute("value").equals(randomTxt));
	}
	
	@Test
	public void inputFieldClearText() {
		driver.get(baseUrl);
		inputField.clear();
		Assert.assertTrue("The input field is not blank. ", 
							inputField.getAttribute("value").equals(""));
	
	}
	
	@Test
	public void inputFieldTime() {
		driver.get(baseUrl);
		
		inputField.sendKeys(chicagoTimeMs());
		Assert.assertTrue("The time is not entered in input field.", 
							inputField.getAttribute("value").equals(chicagoTimeMs()));	
		
	}
	
}
