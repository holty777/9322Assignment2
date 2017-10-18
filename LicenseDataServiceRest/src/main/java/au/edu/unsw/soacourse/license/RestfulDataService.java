package au.edu.unsw.soacourse.license;

import javax.servlet.ServletContext;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import java.io.*;

import org.basex.core.*;

/**
 * COMP9322 Assignment 2 Part 3 - Restful Data Service
 * Queries the XML data and outputs the queried XML file
 * @author Jack Holt z3418559
 *
 */
@Path("/data_service_root")
public class RestfulDataService {
	@javax.ws.rs.core.Context
	ServletContext servletContext;
	private XQuerier q = new XQuerier();

	@GET
	@Path("/class-and-type")
	@Produces("application/xml")
	public String getXML(@QueryParam("filter") @DefaultValue("") String filter,
			@QueryParam("select") @DefaultValue("") String select,
			@QueryParam("orderby") @DefaultValue("") String orderby) throws BaseXException{
		String query = q.getQuery(filter,select,orderby);
		String output = "<?xml version=\"1.0\"?>\n";
		output += "	<Root>\n";
		output += query;
		output += "	</Root>\n";
		return output;
	}

	@GET
	@Path("/xmlfile")
	@Produces("application/xml")
	public InputStream getFile() {
		try {
			String base = servletContext.getRealPath("/res/licensebypostcode.xml");
			File f = new File(String.format(base));
			return new FileInputStream(f);
		} catch (FileNotFoundException e) {
			// log the error?
			return null;
		}
	}
}

