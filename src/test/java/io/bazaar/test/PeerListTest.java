package io.bazaar.test;


import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class PeerListTest
{

    private WebDriver driver;
    private Map<String, Object> vars;
    private JavascriptExecutor js;


    @Before
    public void setUp()
    {
        // Initialize the WebDriver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);

        js = ( JavascriptExecutor ) driver;
        vars = new HashMap<>();
    }


    @After
    public void tearDown()
    {
        // Quit the WebDriver after the test
        driver.quit();
    }


    @Test
    public void run()
    {
        // Navigate to the login page
        driver.get( "https://bazaar.subutai.io/login" );
        driver.manage().window().setSize( new Dimension( 1920, 973 ) );

        // Click on an element with id "contentContainer"
        driver.findElement( By.id( "contentContainer" ) ).click();

        // Assert text on the page
        assertThat( driver.findElement( By.cssSelector( ".doorTitle" ) ).getText(),
                is( "Share and exchange computer resources with users and peers from around the globe." ) );

        // Click on a link with the text "GO AHEAD"
        driver.findElement( By.linkText( "GO AHEAD" ) ).click();

        // Click on an element with class "login-page__sidebar"
        driver.findElement( By.cssSelector( ".login-page__sidebar" ) ).click();

        // Assert text on the page
        assertThat( driver.findElement( By.cssSelector( ".login-forms-text" ) ).getText(),
                is( "Please note the Subutai Bazaar does not use your social profile data without your explicit "
                        + "permission. Your profile data maybe used by you when you request specific friends and "
                        + "colleagues to share resources with you. We also use social network information to evaluate"
                        + " your reputation in the system and to establish your identity as a legitimate user. There "
                        + "will NEVER be any commercial or marketing purpose for using your social network "
                        + "information. Your social network information is NEVER shared or sold to any other "
                        + "individual, partner, or commercial entity." ) );

        // Fill in the email/username field and password field
        driver.findElement( By.name( "email_or_username" ) ).click();
        driver.findElement( By.name( "email_or_username" ) ).sendKeys( Utils.getEmail() );
        driver.findElement( By.id( "loginPassword" ) ).click();
        driver.findElement( By.id( "loginPassword" ) ).sendKeys( Utils.getPassword() );
        driver.findElement( By.id( "loginPassword" ) ).sendKeys( Keys.ENTER );


        // Perform mouse hover actions using Actions class
        WebElement peersLink = driver.findElement( By.linkText( "Peers" ) );
        Actions builder = new Actions( driver );
        builder.moveToElement( peersLink ).perform();

        // Click on the "Peers" link
        driver.findElement( By.cssSelector( "#ug-menu-peers .sidebar-menu-item__caption" ) ).click();

        // Click on specific elements and assert text
        clickAndAssertText( driver, ".even:nth-child(4) > td:nth-child(1)", "prod-peer-eu-2" );
//        clickAndAssertText( driver, ".odd:nth-child(5) > td:nth-child(1)", "prod-peer-eu-3" );
//        clickAndAssertText( driver, ".even:nth-child(6) > td:nth-child(1)", "prod-peer-us-1" );
//        clickAndAssertText( driver, ".odd:nth-child(7) > td:nth-child(1)", "prod-peer-us-2" );
//        clickAndAssertText( driver, ".even:nth-child(8) > td:nth-child(1)", "prod-peer-us-3" );
    }


    private void clickAndAssertText( WebDriver driver, String selector, String expectedText )
    {
        driver.findElement( By.cssSelector( selector ) ).click();
        assertThat( driver.findElement( By.linkText( expectedText ) ).getText(), is( expectedText ) );
    }
}