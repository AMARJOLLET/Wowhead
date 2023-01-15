package Wowhead.PageObject;

import Wowhead.AbstractFullPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Logging;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageBoss extends AbstractFullPage {

    public PageBoss(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//table[@class='listview-mode-default']//th[.//span[./text()] and not(.//text()='Versions') and not(.//text()='Faction')]")
    protected List<WebElement> listLibelleItem;

    @FindBy(xpath = "//table[@class='listview-mode-default']//tr[@class='listview-row']")
    protected List<WebElement> listRowItem;


    public Map<String, Map<String, String>> mapTableauItem(){
        Map<String, Map<String, String>> mapTableauItem = new HashMap<>();

        LOGGER.info("Récupération de " + listRowItem.size() + " items");
        for(WebElement row : listRowItem){
            Map<String, String> mapRowTableau = new HashMap<>();
            List<WebElement> listValeurRow = row.findElements(By.xpath(".//td[.//text()]"));

            for(int i = 0; i < listValeurRow.size(); i ++){
                mapRowTableau.put(listLibelleItem.get(i).getText(), listValeurRow.get(i).getText().replaceAll("\\r\\n|\\r|\\n", " "));
            }
            mapTableauItem.put(mapRowTableau.get("Nom"), mapRowTableau);
        }
        LOGGER.info("Récupération terminée");

        return mapTableauItem;
    }

    public boolean clickOnItem(String itemName) {
        String xpath = "//a[contains(@href,\""+itemName+"\")]";
        return outils.clickOnElementBoolean(driver, wait, xpath);
    }
}
