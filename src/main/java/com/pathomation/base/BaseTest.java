package com.pathomation.base;

import com.pathomation.util.ConstantsPma2;
import com.pathomation.util.reporter.Reporter;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITest;
import org.testng.annotations.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;


public class BaseTest implements ITest {
    public int countOfBrowserLaunching = 0;
    public static WebDriver driver;
    public static boolean isDriverIE = false;
    protected String currentAnnotatedMethodName = "";


    @AfterMethod
    public void setNullTestName() {
        currentAnnotatedMethodName = null;
    }
    @Override
    public String getTestName() {
        return currentAnnotatedMethodName;
    }

    @BeforeMethod
    public void setTestName(Method method) {
        currentAnnotatedMethodName = "";
        try {
            String testName = method.getAnnotation(Test.class).testName();
            if (!testName.isEmpty())
                currentAnnotatedMethodName = testName;
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    protected void startInternetExplorer() {
        String platform = System.getProperty("os.name");
        String pathToDriver = null;
        DesiredCapabilities capabilities = null;
        String driversFolder = ConstantsPma2.DEFAULT_LIB_DIR + File.separator;

        if (System.getProperty("path.to.driver.ie") != null &&
                !System.getProperty("path.to.driver.ie")
                        .isEmpty()) {
            pathToDriver = System.getProperty("path.to.driver.ie");
        } else {
            pathToDriver = (platform.contains("Wind")) ?
                    driversFolder + "IEDriverServer.exe" :
                    "/usr/local/bin/IEDriverServer";
        }
        System.setProperty("webdriver.ie.driver", pathToDriver);
        capabilities = DesiredCapabilities.internetExplorer();
        capabilities.setCapability(
                InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
                true);
        //capabilities.setPlatform(platform);

        capabilities.setCapability(
                CapabilityType.TAKES_SCREENSHOT, true);
        capabilities.setCapability(
                CapabilityType.ACCEPT_SSL_CERTS, true);
        capabilities.setCapability(
                InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR, true);
        capabilities.setCapability(
                InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
        capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
        //capabilities.setCapability("requireWindowFocus", true);
        capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);

         /* Following line resolves 'NoSuchWindowException' when starting browser. If 'NoSuchWindowException'
         * still persists at runtime on a Windows machine, refer to
         * https://github.com/seleniumhq/selenium-google-code-issue-archive/issues/6511
         * especially to post #29 which suggests Windows registry editing. */
        capabilities.setCapability(
                InternetExplorerDriver.INITIAL_BROWSER_URL,
                ConstantsPma2.BASE_URL);

        capabilities.setJavascriptEnabled(true);
        capabilities.setCapability(
                "nativeEvents",
                false);
        driver = new InternetExplorerDriver(capabilities);
        isDriverIE = true;
        driver.manage().
                timeouts().
                implicitlyWait(
                        BasePage.ELEMENT_EXTRALONG_TIMEOUT_SECONDS,
                        TimeUnit.SECONDS);

        driver.manage().window().maximize();
    }

    protected String getCurrentURLWithFormating(String url) {
        String newURL = url.replace("%2F", "/");
        System.out.println(newURL);
        return newURL;
    }

    protected void startFirefox() {
        String platform = System.getProperty("os.name");
        DesiredCapabilities capabilities = null;
        FirefoxProfile fireFoxProfile = new FirefoxProfile();

        capabilities = DesiredCapabilities.firefox();
        capabilities.setBrowserName("firefox");
        fireFoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "text/plain");
        capabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);

        String driversFolder = ConstantsPma2.DEFAULT_LIB_DIR + File.separator;
        String pathToDriver = null;

        if (System.getProperty("path.to.driver.firefox") != null &&
                !System.getProperty("path.to.driver.firefox").isEmpty()) {
            pathToDriver = System.getProperty("path.to.driver.firefox");
        } else {
            pathToDriver = (platform.contains("Wind")) ?
                    driversFolder + "geckodriver.exe" :
                    "/usr/local/bin/geckodriver";
        }

        System.setProperty(
                "webdriver.gecko.driver",
                pathToDriver
        );
        capabilities.setCapability(FirefoxDriver.PROFILE, fireFoxProfile);
        driver = new FirefoxDriver(capabilities);

        driver.manage().timeouts().implicitlyWait(ConstantsPma2.ELEMENT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    protected void setupFirefoxRemoteDriver(String hubUrl, String platformName) throws IOException {
//        Platform platform = (platformName != null) ? Platform.valueOf(platformName) : Platform.ANY;
//
//        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
//        capabilities.setBrowserName("firefox");
//        capabilities.setPlatform(platform);
//        capabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
//
//        driver = new CustomRemoteWebDriver(new URL(hubUrl), capabilities);
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(ConstantsPma2.ELEMENT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
    }

    protected void startChrome() {
        String platform = System.getProperty("os.name");

		        String downloadFilepath = System.getProperty("user.home");
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", downloadFilepath);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
		
		
        String driversFolder = ConstantsPma2.DEFAULT_LIB_DIR + File.separator;
        String pathToDriver = null;
        if (System.getProperty("path.to.driver.chrome") != null && !System.getProperty("path.to.driver.chrome").isEmpty()) {
            pathToDriver = System.getProperty("path.to.driver.chrome");
            Reporter.log(pathToDriver);
        } else {
            pathToDriver = (platform.contains("Wind")) ? driversFolder + "chromedriver.exe" : "/usr/local/bin/chromedriver";
        }

        System.setProperty("webdriver.chrome.driver", pathToDriver);
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(capabilities) {
            @Override
            public WebElement findElement(By by) {
                try {
                    return by.findElement(this);
                } catch (NoSuchElementException nse) {
                    Field f = null;
                    try {
                        f = Throwable.class.getDeclaredField("detailMessage");
                    } catch (NoSuchFieldException e) {
                        throw nse;
                    }
                    f.setAccessible(true);
                    try {
                        String error = "\n" + by.toString() + "\n" + f.get(nse);
                        f.set(nse, error);
                    } catch (IllegalAccessException ia) {
                    }
                    throw nse;
                }
            }
        };
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(ConstantsPma2.ELEMENT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
    }

    protected void startEdge() {
        System.setProperty("webdriver.edge.driver", System.getProperty("path.to.driver.edge"));
        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(ConstantsPma2.ELEMENT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    protected void setupChromeRemoteDriver(String hubUrl, String platformName) throws IOException {
        Platform platform = (platformName != null) ? Platform.valueOf(platformName) : Platform.ANY;

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setBrowserName("chrome");
        capabilities.setPlatform(platform);
        capabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);

        driver = new CustomRemoteWebDriver(new URL(hubUrl), capabilities);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(ConstantsPma2.ELEMENT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
    }

    protected void closeBrowser() {
        if (driver != null)
            driver.quit();
    }


    @BeforeMethod
    public void startBrowser() throws IOException {
		       /* System.setProperty("java.awt.headless", "true");
        Toolkit tk = Toolkit.getDefaultToolkit();
        tk.beep();
        // Check whether the application is
        // running in headless mode.
        GraphicsEnvironment ge =
                GraphicsEnvironment.getLocalGraphicsEnvironment();
        System.out.println("Headless mode: " + ge.isHeadless());*/
        String message = "* Starting pathomation " + this.getClass().toString();
        Reporter.log("\n" + message);
        System.out.println(message);

        String hubUrl = System.getProperty("hub");
        String browser = ConstantsPma2.DEFAULT_BROWSER;
        String platform = System.getProperty("sun.desktop"); //or  os.name


        //if browser doesn't specified in .pom or in .bat file
        if (browser == null) {
            browser = "chrome";
        }
        if (browser.equalsIgnoreCase("chrome") ||
                browser.equalsIgnoreCase("default")) {
            if (hubUrl != null && !hubUrl.isEmpty()) {
                this.setupChromeRemoteDriver(hubUrl, platform);
            } else {
                this.startChrome();
            }

        } else if (browser.equalsIgnoreCase("firefox")) {
            if (hubUrl != null && !hubUrl.isEmpty()) {
                this.setupFirefoxRemoteDriver(hubUrl, platform);
            } else {
                this.startFirefox();
            }
        } else if (browser.equalsIgnoreCase("InternetExplorer")) {
            startInternetExplorer();
        } else if (browser.equalsIgnoreCase("Edge")) {
            startEdge();
        }
        if (countOfBrowserLaunching == 0) {
            saveRightReportDir();
        }
        ++countOfBrowserLaunching;

    }

    public void saveRightReportDir() {
        File newFolderNameFile = new File("uploads/newFolderName.txt");
        File oldFolderNameFile = new File("uploads/oldFolderName.txt");
        try {
            if (!newFolderNameFile.exists()) {
                newFolderNameFile.createNewFile();
            }
            PrintWriter newFolderName = new PrintWriter(newFolderNameFile.getAbsoluteFile());
            PrintWriter oldFolderName = new PrintWriter(oldFolderNameFile.getAbsoluteFile());
            try {
                oldFolderName.print(System.getProperty("report.dir"));
                newFolderName.print(
                        System.getProperty("report.dir").replace(System.getProperty("folder.name"), "")
                                + System.getProperty("browser")
                                + "_v"
                                + defineBrowserVersion()
                );
            } finally {
                oldFolderName.close();
                newFolderName.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String defineBrowserVersion() {
        String userAgentInfo = (String) ((JavascriptExecutor) driver).executeScript("return navigator.userAgent");
        UserAgent userAgent = new UserAgent(userAgentInfo);
        System.out.println(userAgentInfo);
        Version version = userAgent.getBrowserVersion();
        return version.getVersion();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        Reporter.log("Stopping WebDriver");
        closeBrowser();


    }


}
