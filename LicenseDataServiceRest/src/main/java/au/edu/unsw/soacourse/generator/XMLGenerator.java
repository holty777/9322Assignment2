package au.edu.unsw.soacourse.generator;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Cleaner;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

/**
 * XML Generator Class
 * This class can by used to generate XML files as a String from the RMS website.
 * It is applicable to the License Class by Postcode and License Type by Postcode.
 * This code generates only from 2016 data, however by replacing 2016 with a different 
 * year between 2005 - 2017 in the links that years data can be generated.
 *  
 * @author Jack Holt z3418559
 *
 */
public class XMLGenerator {

	private static ArrayList<Entry> q1Entries = new ArrayList<Entry>();
	private static ArrayList<Entry> q2Entries = new ArrayList<Entry>();
	private static ArrayList<Entry> q3Entries = new ArrayList<Entry>();
	private static ArrayList<Entry> q4Entries = new ArrayList<Entry>();

	public static void main(String[] args) throws IOException{
		//Building the single XML file
		//Loops through license classes 
		for(int i = 1; i <= 4; i++){
			String webPage = "http://www.rms.nsw.gov.au/about/corporate-publications/statistics/registrationandlicensing/tables/table215_2016q" + i + ".html";
			String html = Jsoup.connect(webPage).get().html();
			Document dirtyDoc = Jsoup.parse(html);
			Document cleanDoc = new Cleaner(Whitelist.relaxed()).clean(dirtyDoc);
			Elements rows = cleanDoc.select("tr");
			rows.remove(0);
			for(Element row : rows){
				Entry newEntry = new Entry();
				newEntry.setQuarter(Integer.toString(i));
				Elements tds = row.select("td");
				if(tds.size() > 1){
					newEntry.setPostcode(row.select("th").text());
					newEntry.setTotal(tds.get(0).text().replace(",",""));
					newEntry.setClassC(tds.get(1).text().replace(",",""));
					newEntry.setClassLR(tds.get(2).text().replace(",",""));
					newEntry.setClassMR(tds.get(3).text().replace(",",""));
					newEntry.setClassHR(tds.get(4).text().replace(",",""));
					newEntry.setClassHC(tds.get(5).text().replace(",",""));
					newEntry.setClassMC(tds.get(6).text().replace(",",""));
					newEntry.setClassR(tds.get(7).text().replace(",",""));
					switch(i){
					case 1:
						q1Entries.add(newEntry);
						break;
					case 2:
						q2Entries.add(newEntry);
						break;
					case 3:
						q3Entries.add(newEntry);
						break;
					case 4:
						q4Entries.add(newEntry);
						break;
					}

				}
			}
		}
		//Loops through license types
		for(int i = 1; i <= 4; i++){
			String webPage = "http://www.rms.nsw.gov.au/about/corporate-publications/statistics/registrationandlicensing/tables/table225_2016q" + i + ".html";
			String html = Jsoup.connect(webPage).get().html();
			Document dirtyDoc = Jsoup.parse(html);
			Document cleanDoc = new Cleaner(Whitelist.relaxed()).clean(dirtyDoc);
			Elements rows = cleanDoc.select("tr");
			rows.remove(0);
			switch(i){
			case 1:
				for(Element row : rows){
					for(Entry e : q1Entries){
						if(e.getPostcode().equals(row.select("th").text())){
							Elements tds = row.select("td");
							e.setLearner(tds.get(1).text().replace(",",""));
							e.setP1(tds.get(2).text().replace(",",""));
							e.setP2(tds.get(3).text().replace(",",""));
							e.setUnrestricted(tds.get(4).text().replace(",",""));
							break;
						}
					}
				}
				break;
			case 2:
				for(Element row : rows){
					for(Entry e : q2Entries){
						if(e.getPostcode().equals(row.select("th").text())){
							Elements tds = row.select("td");
							e.setLearner(tds.get(1).text().replace(",",""));
							e.setP1(tds.get(2).text().replace(",",""));
							e.setP2(tds.get(3).text().replace(",",""));
							e.setUnrestricted(tds.get(4).text().replace(",",""));
							break;
						}
					}
				}
				break;
			case 3:
				for(Element row : rows){
					for(Entry e : q3Entries){
						if(e.getPostcode().equals(row.select("th").text())){
							Elements tds = row.select("td");
							e.setLearner(tds.get(1).text().replace(",",""));
							e.setP1(tds.get(2).text().replace(",",""));
							e.setP2(tds.get(3).text().replace(",",""));
							e.setUnrestricted(tds.get(4).text().replace(",",""));
							break;
						}
					}
				}
				break;
			case 4:
				for(Element row : rows){
					for(Entry e : q4Entries){
						if(e.getPostcode().equals(row.select("th").text())){
							Elements tds = row.select("td");
							e.setLearner(tds.get(1).text().replace(",",""));
							e.setP1(tds.get(2).text().replace(",",""));
							e.setP2(tds.get(3).text().replace(",",""));
							e.setUnrestricted(tds.get(4).text().replace(",",""));
							break;
						}
					}
				}
				break;
			}
		}
		String XMLFile = createXML();
		System.out.println(XMLFile);
	}

	/**
	 *
	 * @return XML file as a String
	 * @throws IOException
	 */
	public static String createXML() throws IOException{
		ArrayList<Entry> finalEntries = new ArrayList<Entry>();
		finalEntries.addAll(q1Entries);
		finalEntries.addAll(q2Entries);
		finalEntries.addAll(q3Entries);
		finalEntries.addAll(q4Entries);
		String finalXML = "<?xml version=\"1.0\"?>\n";
		finalXML += "	<Root>\n";
		for(Entry e : finalEntries){
			finalXML += "		<Entry>\n";	
			finalXML += "			<Quarter>" + e.getQuarter() + "</Quarter>\n";
			finalXML += "			<Postcode>" + e.getPostcode() + "</Postcode>\n";
			finalXML += "			<Total>" + e.getTotal() + "</Total>\n";
			finalXML += "			<ClassC>" + e.getClassC() + "</ClassC>\n";
			finalXML += "			<ClassLR>" + e.getClassLR() + "</ClassLR>\n";
			finalXML += "			<ClassMR>" + e.getClassMR() + "</ClassMR>\n";
			finalXML += "			<ClassHR>" + e.getClassHR() + "</ClassHR>\n";
			finalXML += "			<ClassHC>" + e.getClassHC() + "</ClassHC>\n";
			finalXML += "			<ClassMC>" + e.getClassMC() + "</ClassMC>\n";
			finalXML += "			<ClassR>" + e.getClassR() + "</ClassR>\n";
			finalXML += "			<Learner>" + e.getLearner() + "</Learner>\n";
			finalXML += "			<P1>" + e.getP1() + "</P1>\n";
			finalXML += "			<P2>" + e.getP2() + "</P2>\n";
			finalXML += "			<Unrestricted>" + e.getUnrestricted() + "</Unrestricted>\n";
			finalXML += "		</Entry>\n";
		}
		finalXML += "	</Root>\n";
		return finalXML;
	}
}
