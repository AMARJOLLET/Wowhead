package Wowhead.PageObject;

import Wowhead.AbstractFullPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageAccueil extends AbstractFullPage {
    public PageAccueil(WebDriver driver, WebDriverWait wait){
        super(driver, wait);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "onetrust-accept-btn-handler")
    protected WebElement acceptCookies;

    @FindBy(xpath = "//button[@class=\"notifications-dialog-buttons-decline btn\"]")
    protected WebElement declineNotification;

    @FindBy(xpath = "//input[@name=\"q\"]")
    protected WebElement inputSearch;

    @FindBy(xpath = "//div[@data-icon='boss']/following-sibling::span[@class='result-name']")
    protected WebElement bossSearch;

    public void clickAcceptCookies() {
        seleniumTools.clickOnElement(wait, acceptCookies);
    }

    public void clickDeclineNotification() throws InterruptedException {
        outils.clickOnElementBoucle(driver, wait, declineNotification);
    }

    public void searchBoss(String bossName) {
        seleniumTools.sendKey(wait, inputSearch, bossName);
    }

    public PageBoss clickBoss(){
        LOGGER.info("Clique sur le boss");
        seleniumTools.clickOnElement(wait, bossSearch);
        return new PageBoss(driver, wait);
    }

}
