package Wowhead;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.InstanciationDriver;
import utils.Logging;
import utils.Outils;
import utils.SeleniumTools;

public class AbstractFullPage extends Logging {

    protected WebDriver driver;
    protected WebDriverWait wait;

    protected SeleniumTools seleniumTools = new SeleniumTools(driver);
    protected Outils outils = new Outils(driver);

    public AbstractFullPage(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

}
