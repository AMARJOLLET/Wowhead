import com.browserup.bup.BrowserUpProxy;
import com.browserup.bup.BrowserUpProxyServer;
import com.browserup.bup.client.ClientUtil;
import com.browserup.bup.proxy.CaptureType;
import com.opencsv.CSVWriter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Logging;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AbstractSelenium extends Logging {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected String os = "windows";
    protected String navigateur = "chrome";
    protected int implicitlyWait = 5;
    protected int explicitlyWait = 20;

    boolean proxyOn = false;
    protected BrowserUpProxy proxy;
    long startTime;
    long endTime;


    @BeforeEach
    void setDriver(){
        startTime = System.currentTimeMillis();
        Proxy seleniumProxy = null;

        if (proxyOn) {
            proxy = new BrowserUpProxyServer();
            proxy.setTrustAllServers(true);
            proxy.start();
            seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
            proxy.disableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.REQUEST_HEADERS, CaptureType.RESPONSE_CONTENT, CaptureType.RESPONSE_HEADERS);
        }

        LOGGER.info("Setup driver");
        switch (navigateur.toLowerCase()){
            case "firefox" :
                if(os.equals("windows")){
                    System.setProperty("webdriver.gecko.driver", "src/main/resources/driver/geckodriver.exe");
                } else if (os.equals("linux")){
                    System.setProperty("webdriver.gecko.driver", "/home/firefoxcnaf/geckodriver");
                }
                FirefoxOptions options = new FirefoxOptions();
                options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
                options.setPageLoadStrategy(PageLoadStrategy.EAGER);
                if (proxyOn) {
                    options.setProxy(seleniumProxy);
                    options.setAcceptInsecureCerts(true);
                    options.setCapability(CapabilityType.PROXY, seleniumProxy);
                }
                driver = new FirefoxDriver();
                break;
            case "chrome" :
                if(os.equals("windows")){
                    System.setProperty("webdriver.chrome.driver", "src/main/resources/driver/chromedriver.exe");
                } else if (os.equals("linux")){
                    System.setProperty("webdriver.chrome.driver", "/home/firefoxcnaf/chromedriver");
                }
                ChromeOptions optionsChrome = new ChromeOptions();
                optionsChrome.setCapability("chrome.switches", Collections.singletonList("--ignore-certificate-errors"));
                if (proxyOn) {
                    optionsChrome.setProxy(seleniumProxy);
                    optionsChrome.setAcceptInsecureCerts(true);
                    optionsChrome.setCapability(CapabilityType.PROXY, seleniumProxy);
                }
                optionsChrome.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
                optionsChrome.setPageLoadStrategy(PageLoadStrategy.EAGER);
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("download.prompt_for_download", false);
                prefs.put("plugins.always_open_pdf_externally", true);
                optionsChrome.setExperimentalOption("prefs", prefs);
                driver = new ChromeDriver(optionsChrome);
                break;
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitlyWait));
        wait = new WebDriverWait(driver, Duration.ofSeconds(explicitlyWait));
        LOGGER.info("Setup OK");
    }

    @AfterEach
    void tearDown() throws IOException {
        LOGGER.info("Fermeture du driver");
        driver.quit();
        LOGGER.info("Fermeture OK");
        endTime = System.currentTimeMillis();

        int timeExecution = Math.toIntExact((endTime - startTime) / 1000);

        String fichier = "target/timeBrowser.csv";
        CSVWriter writer;
        String[] record;
        File file = new File(fichier);

        if(!file.exists()){
            file.createNewFile();
        }
        writer = new CSVWriter(new FileWriter(fichier, true));
        record = new String[]{navigateur, String.valueOf(timeExecution)};
        writer.writeNext(record);
        writer.close();


        LOGGER.info(String.valueOf(timeExecution));
    }

}
