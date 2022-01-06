package fr.eql.wowhead;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageItem extends PageObject {
	public PageItem(WebDriver driver) {
		super(driver);
	}

	String stat;
	ArrayList<String> listAllStatItem = new ArrayList<String>();


	@FindBy(xpath = "//table[@style='width: 100%;']/tbody")
	List<WebElement> stat_list;

	public void getAllStat(int n) {
		waitElementsXpath("//table[@style='width: 100%;']/tbody");
		stat = stat + stat_list.get(n).getText();
	}

	public void LectureCSV() throws FileNotFoundException {
		String fileName = "src\\main\\resources\\ItemLardeur.csv";
		Scanner scanner = new Scanner(new File(fileName));
		scanner.useDelimiter(";");
		
		while (scanner.hasNext()) {
				listAllStatItem.add(scanner.next());
			}
		scanner.close();
	}
		/*
		public static void main(String[] args) throws FileNotFoundException{
			LectureCSV();
			for (int i=15;i<30;i++){
				System.out.println(listAllStatItem.get(i));
			}
			
		}
*/
	


	}


/*
 * 
 * ArrayList <String> list = new ArrayList<String>(); URL Loot =
 * PageItem.class.getClassLoader().getResource(
 * "/wowhead/src/main/ressource/ItemLardeur.csv"); File ReadCSV = new
 * File(Loot.getFile()); Scanner scanner = new Scanner(ReadCSV, "UTF-8");
 * while(scanner.hasNext()){ list.add(scanner.nextLine()); } scanner.close();
 * 
 * System.out.println(list.get(0)); }
 */

/*
 * String item = "Item1"; public List<String> loadFile(String Item) throws
 * FileNotFoundException { URL Loot =
 * PageLoot.class.getClassLoader().getResource("/wowhead/src/main/ressource/" +
 * Item + ".txt"); File totoResource = new File(Loot.getFile());
 * 
 * Scanner s = new Scanner(totoResource, "UTF-8"); ArrayList<String> list = new
 * ArrayList<String>(); while (s.hasNextLine()){ list.add(s.nextLine()); }
 * s.close();
 * 
 * return list; }
 */
