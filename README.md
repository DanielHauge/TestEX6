# Test Assignment (Selenium)
This repository is a exercise project for Software development (PBA) Test course. Daniel (cph-dh136)

# Description
This is a test assignment which goal is to do system testing. Using selenium it is possible to do UI testing, hence system testing. The assignment has a pre-existing system to test upon. The assignment description can be found [Here](https://github.com/datsoftlyngby/soft2018spring-test-teaching-material/blob/master/exercises/SeleniumExerciseV2.pdf)

## Assignment

### Tests
Tests can be found in [mainTest.java](/src/test/java/mainTest.java)
[![https://gyazo.com/61ccd6736b7aeaaedb955ad63af9ee64](https://i.gyazo.com/61ccd6736b7aeaaedb955ad63af9ee64.png)](https://gyazo.com/61ccd6736b7aeaaedb955ad63af9ee64)

### Pros and Cons of manual & automated tests.
#### Time
It is alot easier and straightforward to do manual testing on UI, when it is running, you most of the times determine immedietly if it fails to furfill the requirements or not. Hence, it does not require any time to setup. However it is slower to "run" than what an aumated test can. When the automated tests are created, it will run through the tests very quickly. Additionaly we can use seleniums htmldriver instead of a chrome or firefox graphical driver, which will make the automated tests even faster to run.

ie. Short-term manual testing cost is low, but high in the long term, where it's reversed with automation. Very expensive short-term but cost-effective on the long run.

#### Regression
Here, it is litle difficult to evaluate. I would say it is smart and good, that you can test if the UI still works after a few changes, so you wouldn't have to manually test everything once a change has occured. But i would also argue, that the UI is unstable and can be changed more regularly, so the tests might become antiquated more quickly. ie. Manual testing cannot be reused where as automated tests can. Another thing is, that it might be quite boring to fill out the same form time and time again, so it is not so stimulating to do manual testing. Also automated tests, can be reused for other drivers, such as chrome, internet explorer, safari on mac or even iphones to test for mobile compability.

#### The Human Intuition
There is also limits to the automated tests which can be made with selenium. Things that might not be that tangible, which is alot easier for humans to spot. eg. Image color, font size and finding real user issues. eg. if you accidently double click on the button, and that results in an error. These errors are more likely to be spotted by a human than a robot that is deterministic and can do a task the same way every time in a very short time.

### Pyramid of "Test"
Test Pyramid is a concept descriping speed and time conplexity of having tests in different layers. There is 3 layers. Foundation is the small, fast, cheap unit tests, which we want many of to support the rest of the "pyramid". The 2nd layer, is the integration tests, which are medium sized, medium speed and medium priced tests that we want not to many of, but not to little of either. Then there's the top layer of the pyramid which is the System tests, or UI tests. These are big, slow and expensive to make. These we don't want too many of, because they are very expensive and slow to make. Furthermore they are very fragile, when it comes to their durability, and longevity.

I would argue this assigmment supports the idea. We only have the bare minimum tests

- Can the site load?
- Does the filter work at input
- Does the filter work when clearing
- Does sort by id work
- Does edit work
- Does form error handling work (Just 1 case)
- Does create new car work.

We coulda seen alot more. Like: delete, all different errors, more filtering tests, alot more sorting tests and more, testing that when edit all feilds gets changed and so forth. But we should not, they are expensive, slow and big to make, furthermore they might break after a small change.

### Longevity of GUI tests
Systems tests as GUI tests are very fragile or vulnerable. This is because that, usualy the UI is the thing that might change the most. One example is porting to mobile, the UI might change alot hence it doesn't work the same. That is a little bit of a problem when it comes to the longevity of GUI tests, because they can very quickly become antiquated. A small change to the GUI can completely destroy any number of tests. Which should be changed, the GUI or the tests?. Most likely the change to the GUI was intentional, so it has to be the tests.

another problem with GUI testing is as also stated previusly, the human components. GUI verification can be subjective. How can you write a ```void Create_Car_Form_Looks_And_Works_Amazing()``` test?. For some people it might be Amazing, but others may say it is terrible. GUI testing is very much about communication, and alot of it isn't realy 100% tangible for a deterministic computer. So it ends with being a "people" problem, so trying to neglect these human components by doing automation will make the tests very prone to being fragile.

### Selenium
There is seven basic steps to creating a Selenium test script.
- Create a webdriver instance (eg. Openning firefox)
- Navigate to a web page (eg. go to www.google.dk)
- Locate an HTML element on the web page (eg. locate searchbar)
- Perform an action on that element (eg. Write stuff in the searchbar and submit)
- Assert some repsonse to that action (eg. Assert search result bar contains a list of links)
- Running test and recording the results using a framework (eg. Using JUnit framework)
- Conclude the test (ie. Running the tests and get the results to conclude the test)

Using java, i'm using JUnit 5 as the framework. I could've used the selenium HTMLDriver, but i used the firefox graphical driver instead. So i have firefox installed on my computer aswell. To have JUnit use firefox a Firefox driver is used. That needs to be set as a env var in my system. So before each test i run this line 

```java
System.setProperty("webdriver.gecko.driver", "C:\\Users\\Animc\\Desktop\\Selenium\\driver\\geckodriver.exe");
```

Also open a firefox instance with this, which is also run in the beginning of each:

```java
driver = new FirefoxDriver();
```

Then navigate to a webpage
```java
driver.get("http://localhost:3000");
```

Then locate a web element

```java
WebElement YSort = driver.findElement(By.id("h_year"));
```

Then perform some action/actions

```java
YSort.click();
```

Then assert some response (Note: i've located some other web elements to check the response)

```java
assertThat(tds.get(0).getText(), is("940"));
```

Then basicly, just running the test in my prefered IDE, IntelliJ.

To read/manipulate the DOM elements on the webpage, i've used mostly "findElement" or "findElements" with "By.id()" or "By.tagName". I could've used Xpath to find and read the elements too, but i thought it was just as easy to focus in on what i need. eg. 

```java
        WebElement table = driver.findElement(By.id("tbodycars"));
        List<WebElement> cars = table.findElements(By.tagName("tr"));
```

The full tests can be found in [mainTest.java](/src/test/java/mainTest.java)

### Rapid selenium
There will arise a problem with how fast the tests / selenium is compared to when the webpage actully arives. ie. It is a problem to start the test, if the page has not yet been loaded since it is a webpage from USA. This is why it is neccesary to have the tests wait for the webpage to have loaded, otherwise we will not find any webelements and therefor the tests will fail. The problem can be solved by this line:

```java
        driver.get("http://localhost:3000");
        (new WebDriverWait(driver, 10)).until((ExpectedCondition<Boolean>) d -> d.getTitle().toLowerCase().startsWith("document"));
```
This one, will create an annynomous WebDriverWait, on the driver which wait for a condition or until 10 seconds have passed. The condition is that the page title starts with "document", when it does it is assumed the page has loaded and are therefor ready for testing. This extends to all situations where selenium tests might be faster what the webpage can handle. Create a wait on it, until some condition has been meet or "2nd parameter in seconds" has gone by.
