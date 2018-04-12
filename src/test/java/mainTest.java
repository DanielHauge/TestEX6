
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class mainTest {

    WebDriver driver;


    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Animc\\Desktop\\Selenium\\driver\\chromedriver.exe");
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Animc\\Desktop\\Selenium\\driver\\geckodriver.exe");
        com.jayway.restassured.RestAssured.given().get("http://localhost:3000/reset");
        driver = new FirefoxDriver();
        driver.get("http://localhost:3000");
        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.google.com");

        (new WebDriverWait(driver, 10)).until((ExpectedCondition<Boolean>) d -> d.getTitle().toLowerCase().startsWith("document"));
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {

        driver.quit();

    }

    @Test
    void Test1() {

        // 1 Check loading and list
        WebElement table = driver.findElement(By.id("tbodycars"));
        List<WebElement> cars = table.findElements(By.tagName("tr"));
        Assertions.assertTrue(cars.size() == 5);


    }

    @Test
    void Test2(){
        WebElement filter = driver.findElement(By.id("filter"));
        filter.sendKeys("2002");
        WebElement table = driver.findElement(By.id("tbodycars"));
        List<WebElement> cars = table.findElements(By.tagName("tr"));
        Assertions.assertTrue(cars.size() == 2);
    }

    @Test
    void Test3(){

        driver.findElement(By.id("filter")).sendKeys("2002");;
        driver.findElement(By.id("filter")).sendKeys(Keys.BACK_SPACE,Keys.BACK_SPACE,Keys.BACK_SPACE,Keys.BACK_SPACE);
        WebElement table = driver.findElement(By.id("tbodycars"));
        List<WebElement> cars = table.findElements(By.tagName("tr"));
        Assertions.assertTrue(cars.size() == 5);

    }

    @Test
    void Test4() throws InterruptedException {


        WebElement YSort = driver.findElement(By.id("h_year"));
        YSort.click();
        WebElement table = driver.findElement(By.id("tbodycars"));
        List<WebElement> cars = table.findElements(By.tagName("tr"));

        WebElement tr1 = cars.get(0);
        WebElement trlast = cars.get(cars.size()-1);
        List<WebElement> tds = tr1.findElements(By.tagName("td"));
        assertThat(tds.get(0).getText(), is("938"));
        tds = trlast.findElements(By.tagName("td"));
        assertThat(tds.get(0).getText(), is("940"));

    }

    @Test
    void Test5(){

        WebElement table = driver.findElement(By.id("tbodycars"));
        List<WebElement> cars = table.findElements(By.tagName("tr"));
        WebElement tr1 = cars.get(0);
        List<WebElement> tds;

        tr1.findElements(By.tagName("a")).get(0).click();
        driver.findElement(By.id("description")).clear();
        driver.findElement(By.id("description")).sendKeys("Cool car");
        driver.findElement(By.id("save")).click();
        tds = tr1.findElements(By.tagName("td"));
        assertThat(tds.get(5).getText(), is("Cool car"));

    }

    @Test
    void Test6(){
        driver.findElement(By.id("new")).click();
        driver.findElement(By.id("save")).click();
        WebElement error = driver.findElement(By.id("submiterr"));
        assertThat(error.getText(), is("All fields are required"));
    }


    @Test
    void Test7(){

        WebElement table;
        List<WebElement> cars;

        driver.findElement(By.id("year")).sendKeys("2008");
        driver.findElement(By.id("registered")).sendKeys("2002-5-5");
        driver.findElement(By.id("make")).sendKeys("Kia");
        driver.findElement(By.id("model")).sendKeys("Rio");
        driver.findElement(By.id("description")).sendKeys("As new");
        driver.findElement(By.id("price")).sendKeys("31000");
        driver.findElement(By.id("save")).click();

        table = driver.findElement(By.id("tbodycars"));
        cars = table.findElements(By.tagName("tr"));
        Assertions.assertTrue(cars.size() == 6);

    }

    @Test
    void Test8(){
        //TEST ALL
        WebElement table = driver.findElement(By.id("tbodycars"));
        List<WebElement> cars = table.findElements(By.tagName("tr"));
        Assertions.assertTrue(cars.size() == 5);

        WebElement filter = driver.findElement(By.id("filter"));
        filter.sendKeys("2002");
        table = driver.findElement(By.id("tbodycars"));
        cars = table.findElements(By.tagName("tr"));
        Assertions.assertTrue(cars.size() == 2);

        driver.findElement(By.id("filter")).sendKeys(Keys.BACK_SPACE,Keys.BACK_SPACE,Keys.BACK_SPACE,Keys.BACK_SPACE);
        table = driver.findElement(By.id("tbodycars"));
        cars = table.findElements(By.tagName("tr"));
        Assertions.assertTrue(cars.size() == 5);

        WebElement YSort = driver.findElement(By.id("h_year"));
        YSort.click();
        table = driver.findElement(By.id("tbodycars"));
        cars = table.findElements(By.tagName("tr"));
        WebElement tr1 = cars.get(0);
        WebElement trlast = cars.get(cars.size()-1);
        List<WebElement> tds = tr1.findElements(By.tagName("td"));
        assertThat(tds.get(0).getText(), is("938"));
        tds = trlast.findElements(By.tagName("td"));
        assertThat(tds.get(0).getText(), is("940"));

        table = driver.findElement(By.id("tbodycars"));
        cars = table.findElements(By.tagName("tr"));
        tr1 = cars.get(0);
        tr1.findElements(By.tagName("a")).get(0).click();
        driver.findElement(By.id("description")).clear();
        driver.findElement(By.id("description")).sendKeys("Cool car");
        driver.findElement(By.id("save")).click();
        tds = tr1.findElements(By.tagName("td"));
        assertThat(tds.get(5).getText(), is("Cool car"));

        driver.findElement(By.id("new")).click();
        driver.findElement(By.id("save")).click();
        WebElement error = driver.findElement(By.id("submiterr"));
        assertThat(error.getText(), is("All fields are required"));

        driver.findElement(By.id("year")).sendKeys("2008");
        driver.findElement(By.id("registered")).sendKeys("2002-5-5");
        driver.findElement(By.id("make")).sendKeys("Kia");
        driver.findElement(By.id("model")).sendKeys("Rio");
        driver.findElement(By.id("description")).sendKeys("As new");
        driver.findElement(By.id("price")).sendKeys("31000");
        driver.findElement(By.id("save")).click();

        table = driver.findElement(By.id("tbodycars"));
        cars = table.findElements(By.tagName("tr"));
        Assertions.assertTrue(cars.size() == 6);

    }


}