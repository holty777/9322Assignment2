package au.edu.unsw.soacourse.license;

import org.basex.core.*;
import org.basex.core.cmd.*;

/**
 * Class which uses the basex library to faciliate XQuery queries on an XML file
 * @author Jack Holt z3418559
 *
 */
public class XQuerier {

	Context context = new Context();
	public XQuerier(){

	}

	/**
	 * Of note in this class is the location of the XML File. It is hosted on the server so if ran off
	 * a different port only the "localhost:8080" will need to be modified if this is run elsewhere
	 * 
	 * @param filters
	 * @param selects
	 * @param orderby
	 * @return queried Xml file
	 * @throws BaseXException
	 */
	public String getQuery(String filters, String selects, String orderby) throws BaseXException{

		String query = "for $e in doc('http://localhost:8080/LicenseDataServiceRest/data_service_root/xmlfile')/Root/Entry\n";
		filters = filters.replace("%20", " ");
		if(!filters.equals("")){
			String[] filterList = filters.split("and");
			query += "where ";
			//quarter eq 1
			for(int i = 0; i < filterList.length; i++){
				if(i > 0){
					query+=" and ";
				}
				filterList[i].trim();
				String[] finalList = filterList[i].split("eq");
				String output = finalList[0].trim().substring(0, 1).toUpperCase() + finalList[0].trim().substring(1).toLowerCase();
				query += "$e/" + output + " = \"" + finalList[1].trim() +"\"";
			}
			if(filterList.length == 0){
				String[] finalList = filters.split("eq");
				query += "$e/" + finalList[0].trim() + " = \"" + finalList[1].trim() +"\"";
			}
			query +="\n";

			String order = orderby.replace("%20", "");
			order = orderby.replace(" ", "");
			if(!order.equals("")){
				query += "order by xs:integer($e/" + order + ")\n";
			}
		}
		String[] selectList = selects.split(",");
		if(selects.equals(""))
			query+="return $e";
		else{
			query+="return\n";
			query+="		<Entry>\n";
			for(int i = 0; i < selectList.length; i++){
				selectList[i] = selectList[i].replace("%20", "");
				selectList[i] = selectList[i].replace(" ", "");
				query+="			{$e/" + selectList[i].trim().substring(0, 1).toUpperCase() + selectList[i].trim().substring(1) +"}\n";	
			}
			query+="		</Entry>\n";
		}
		System.out.println(query);
		return new XQuery(query).execute(context);

	}
}