import Wowhead.PageObject.PageAccueil;
import Wowhead.PageObject.PageBoss;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WowheadTest extends AbstractSelenium {
    String[] listBossName = {
            "Lardeur",
            "Magmagueule",
            "Maloriak"};

    String url = "https://www.wowhead.com/fr";

    @Test
    public void test1() throws InterruptedException {

        LOGGER.info("Ouverture de la page");
        driver.get(url);

        PageAccueil pageAccueil = new PageAccueil(driver, wait);
        LOGGER.info("Accept Cookies");
        pageAccueil.clickAcceptCookies();
        LOGGER.info("Accept Notification");
        pageAccueil.clickDeclineNotification();

        for(String bossName : listBossName){
            LOGGER.info("Search bossName : " + bossName);
            pageAccueil.searchBoss(bossName);
            pageAccueil.clickBoss();

            LOGGER.info("Page boss");
            PageBoss pageBoss = new PageBoss(driver, wait);
            Map<String, Map<String, String>> mapTableauItem = pageBoss.mapTableauItem();
            List<String> listItemName = new ArrayList<>();

            mapTableauItem.forEach((k1, v1 ) -> {
                LOGGER.info("Item : " + k1);
                listItemName.add(k1.toLowerCase()
                        .replaceAll(" : ", "-")
                        .replaceAll(" ", "-")
                        .replaceAll("’","-")
                        .replaceAll("'", ""));
                v1.forEach((k2, v2) -> {
                    LOGGER.info(k2+" = " + v2);
                });
            });

            listItemName.forEach(s -> {
                LOGGER.info("Click on " + s);
                if(pageBoss.clickOnItem(s)){
                    driver.navigate().back();
                }
            });
            driver.get(url);
        }
    }
}
