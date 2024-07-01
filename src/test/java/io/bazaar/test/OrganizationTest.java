package io.bazaar.test;


import java.util.HashMap;
import java.util.Map;

import org.hamcrest.core.Is;
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
import org.openqa.selenium.interactions.Actions;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class OrganizationTest extends AbstractTest
{
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;

    @Override
    @Before
    public void setUp() {
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }
    @Override
    @After
    public void tearDown() {
        driver.quit();
    }

    @Override
    @Test
    public void run() {

        // Navigate to the login page
        driver.get( "https://bazaar.subutai.io/login" );
        driver.manage().window().setSize( new Dimension( 1920, 973 ) );

        // Click on an element with id "contentContainer"
        driver.findElement( By.id( "contentContainer" ) ).click();

        // Assert text on the page
        assertThat( driver.findElement( By.cssSelector( ".doorTitle" ) ).getText(),
                Is.is( "Share and exchange computer resources with users and peers from around the globe." ) );

        // Click on a link with the text "GO AHEAD"
        driver.findElement( By.linkText( "GO AHEAD" ) ).click();

        // Click on an element with class "login-page__sidebar"
        driver.findElement( By.cssSelector( ".login-page__sidebar" ) ).click();

        // Assert text on the page
        assertThat( driver.findElement( By.cssSelector( ".login-forms-text" ) ).getText(),
                Is.is( "Please note the Subutai Bazaar does not use your social profile data without your explicit "
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



        driver.get("https://bazaar.subutai.io/organizations");
        driver.manage().window().setSize(new Dimension(1920, 1055));
        {
            WebElement element = driver.findElement(By.linkText("Products"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        {
            WebElement element = driver.findElement(By.tagName("body"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element, 0, 0).perform();
        }
        {
            WebElement element = driver.findElement(By.linkText("Products"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        {
            WebElement element = driver.findElement(By.tagName("body"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element, 0, 0).perform();
        }
        {
            WebElement element = driver.findElement(By.linkText("Map of peers"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        {
            WebElement element = driver.findElement(By.tagName("body"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element, 0, 0).perform();
        }
        {
            WebElement element = driver.findElement(By.cssSelector("#ug-menu-organizations > .sidebar-menu-item"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        {
            WebElement element = driver.findElement(By.tagName("body"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element, 0, 0).perform();
        }
        driver.findElement(By.cssSelector("#ug-menu-organizations .sidebar-menu-item__caption")).click();
        {
            WebElement element = driver.findElement(By.cssSelector("#ug-menu-organizations .sidebar-menu-item__caption"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        {
            WebElement element = driver.findElement(By.tagName("body"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element, 0, 0).perform();
        }
        driver.findElement(By.cssSelector(".card:nth-child(1) .card-content__details")).click();
        assertThat(driver.findElement(By.linkText("CodeDragons")).getText(), is("CodeDragons"));
        driver.findElement(By.cssSelector(".card:nth-child(5) .card-details__title")).click();
        assertThat(driver.findElement(By.linkText("Subutai")).getText(), is("Subutai"));
    }
}
