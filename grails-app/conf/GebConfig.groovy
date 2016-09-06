/**
 * Created by HP on 8/24/2016.
 */
//import org.openqa.selenium.phantomjs.PhantomJSDriver
//import org.openqa.selenium.remote.DesiredCapabilities


/*

driver = {

    DesiredCapabilities caps = new DesiredCapabilities();
    caps.setCapability("takesScreenshot", false);
    new PhantomJSDriver(caps)

}*/

import org.openqa.selenium.chrome.ChromeDriver

driver = {
    System.setProperty('webdriver.chrome.driver', "/Users/HP/chromedriver.exe")
    new ChromeDriver()
}