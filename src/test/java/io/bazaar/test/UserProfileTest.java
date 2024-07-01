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


public class UserProfileTest extends AbstractTest
{
    private WebDriver driver;
    private Map<String, Object> vars;


    private String email;
    private String password;



    JavascriptExecutor js;
    @Before
    public void setUp() {
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }
    @After
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void run() {

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


        try
        {
            Thread.sleep( 2000 );
        }
        catch ( InterruptedException e )
        {
            throw new RuntimeException( e );
        }

        driver.manage().window().setSize(new Dimension(1920, 1055));
        driver.findElement(By.cssSelector(".profile__text:nth-child(2)")).click();
        assertThat(driver.findElement(By.linkText("Settings")).getText(), is("Settings"));
        assertThat(driver.findElement(By.linkText("Billing")).getText(), is("Billing"));
        assertThat(driver.findElement(By.linkText("My peers")).getText(), is("My peers"));
        assertThat(driver.findElement(By.linkText("My domains")).getText(), is("My domains"));
        assertThat(driver.findElement(By.linkText("Access Control")).getText(), is("Access Control"));
        driver.findElement(By.linkText("Settings")).click();
        assertThat(driver.findElement(By.cssSelector(".sidebar-menu__item_active .sidebar-menu-item__caption")).getText(), is("General"));
        assertThat(driver.findElement(By.cssSelector(".js-user-nav > .sidebar-menu__item:nth-child(2) .sidebar-menu-item__caption")).getText(), is("Security"));
        assertThat(driver.findElement(By.cssSelector(".js-user-nav > .sidebar-menu__item:nth-child(3) .sidebar-menu-item__caption")).getText(), is("Other"));
        driver.findElement(By.cssSelector("legend:nth-child(1)")).click();
        assertThat(driver.findElement(By.cssSelector("legend:nth-child(1)")).getText(), is(" System settings "));
        driver.findElement(By.cssSelector(".acc-set-fld:nth-child(2) > .fld-lbl")).click();
        driver.findElement(By.cssSelector(".acc-set-fld:nth-child(2) > .fld-lbl")).click();
        {
            WebElement element = driver.findElement(By.cssSelector(".acc-set-fld:nth-child(2) > .fld-lbl"));
            Actions builder = new Actions(driver);
            builder.doubleClick(element).perform();
        }
        assertThat(driver.findElement(By.cssSelector(".acc-set-fld:nth-child(2) > .fld-lbl")).getText(), is("Username:"));
    }


}
