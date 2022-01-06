package fr.eql.wowhead;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestWowhead extends PageObjectTest {
	Logger LOGGER = LoggerFactory.getLogger(TestWowhead.class);

	@Before
	public void SetupDriver() {
		super.SetupDriver();
	}

	@Test
	public void navigateWowhead() throws FileNotFoundException {
		String Search = "lardeur";
		int index = 15;		// Index de la liste .get du CSV
		int NombreStatVerifier = 14;

		PageAccueil pageAccueil = new PageAccueil(driver);
		LOGGER.info("INITIALISATION");
		// AcceptAll
		pageAccueil.clickAcceptCookie();
		pageAccueil.clickAcceptNotification();

		// Search item
		LOGGER.info("Search Lardeur");
		PageSearch pageSearch = pageAccueil.Search(Search);
		PageLoot pageLoot = pageSearch.clickBoss(Search);

		// Item
		LOGGER.info(pageLoot.loot_list.size()+" Items a check");
		
			// Boucle pour cliquer sur l'item
			for (int i = 0; i < pageLoot.loot_list.size(); i++) {
				LOGGER.info("ITEM " + (i + 1));
				PageItem pageItem = pageLoot.clickItem(i);
				
				assertEquals("rgba(0, 112, 221, 1)", pageLoot.rarete); // Assert de la rareté de l'item
					
				// Boucle pour avoir les stats de l'item
	
				for (int j = 0; j < pageItem.stat_list.size(); j++) {
					pageItem.getAllStat(j);
				}
				for (int k=0;k<NombreStatVerifier;k++) {
					Assert.assertTrue(pageItem.stat.contains(pageItem.listAllStatItem.get(index+k))); // Assert de toutes les stats de l'item
				}
				
				index = index +15;
				LOGGER.info("ITEM " + (i + 1) + " EST OK");
				driver.navigate().back();
			}
		LOGGER.info("FINI !!");
	}

	@After
	public void Close() {
		super.cleanUp();
		super.teardown();
	}
}