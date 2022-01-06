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
		ArrayList<String> statitem = new ArrayList<String>();

		String Search = "lardeur";

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
		int index = 15;
		//PageItem pageitem = new PageItem(driver);
		//pageitem.LectureCSV();
		//pageitem.listAllStatItem.get(15);
		LOGGER.info(pageLoot.loot_list.size()+" Items a check");
		for (int i = 0; i < pageLoot.loot_list.size(); i++) {
			LOGGER.info("ITEM " + (i + 1));
			PageItem pageItem = pageLoot.clickItem(i);
			//assertEquals("rgba(0, 112, 221, 1)", pageLoot.rarete);
			pageItem.stat = "";
			for (int j = 0; j < pageItem.stat_list.size(); j++) {
				pageItem.getAllStat(j);
				//Assert.assertTrue(statitem.get(i).contains(listAllStatItem.get(index)));
			}
			statitem.add(pageItem.stat);
			index = index +15;
			LOGGER.info("ITEM " + (i + 1) + " EST OK");
			driver.navigate().back();
		}

		/*
		 * for (int i=0;i<statitem.size();i++) {
		 * Assert.assertTrue(statitem.get(i).contains("okokTuMarches"));
		 * 
		 * }
		 */
		LOGGER.info("FINI !!");

	}

	@After
	public void Close() {
		super.cleanUp();
		super.teardown();
	}
}