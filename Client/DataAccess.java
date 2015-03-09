import org.apache.xmlrpc.XmlRpcClient;
import org.apache.xmlrpc.XmlRpc;
import org.apache.xmlrpc.XmlRpcException;

import java.io.IOException;
import java.util.Vector;

/**
 * 
 * This class will communicate with the XML-RPC server and will
 * access the information from the server. 
 * @author Roman Pusec
 * 
 */
public class DataAccess
{
	private XmlRpcClient client;
	
	/**
	 * 
	 * Constructor which will connect the
	 * user to the XML-RPC server with the
	 * specified server path
	 * @param serverPath Path to the server
	 * 
	 */
	public DataAccess(String serverPath)
	{
		try
		{
			//establishes the connection to the server
			client = new XmlRpcClient(serverPath);
		}
		catch(IOException e)	{System.out.println("IO error: " + e.getMessage());}
	}
	
	/**
	 * 
	 * Returns the list of methods.
	 * @return Methods
	 * 
	 */
	public String getMethods()
	{
		try
		{
			Vector params = new Vector();
			Object result = client.execute("beers.getMethods", params);
			return result.toString();
		}
		catch(IOException ioe)		{System.out.println("IO error: " + ioe.getMessage());}
		catch(XmlRpcException xre)	{System.out.println("XmlException error: " + xre.getMessage());}
		catch(Exception excp)		{System.out.println("Exception error: " + excp.getMessage());}
		
		return null;
	}
	
	/**
	 * 
	 * Retrieves the price of a specified beer.
	 * @param beer 
	 * @return Beer's price
	 * 
	 */
	public String getPrice(String beer)
	{
		try
		{
			Vector params = new Vector();
			params.add(beer);
			Object result = client.execute("beers.getPrice", params);
			return result.toString();
		}
		catch(IOException ioe)		{System.out.println("IO error: " + ioe.getMessage());}
		catch(XmlRpcException xre)	{System.out.println("XmlException error: " + xre.getMessage());}
		catch(Exception excp)		{System.out.println("Exception error: " + excp.getMessage());}
		
		return null;
	}
	
	/**
	 * 
	 * Sets the price of a beer.
	 * @param beer Selected beer
	 * @param price The chosen price
	 * @return Confirmation of the modified price
	 * 
	 */
	public String setPrice(String beer, String price)
	{
		try
		{
			Vector params = new Vector();
			params.add(beer);
			params.add(price);
			Object result = client.execute("beers.setPrice", params);
			return result.toString();
		}
		catch(IOException ioe)		{System.out.println("IO error: " + ioe.getMessage());}
		catch(XmlRpcException xre)	{System.out.println("XmlException error: " + xre.getMessage());}
		catch(Exception excp)		{System.out.println("Exception error: " + excp.getMessage());}
		
		return null;
	}
	
	/**
	 * 
	 * Returns all beers.
	 * @return beers
	 * 
	 */
	public String getBeers()
	{
		try
		{
			Vector params = new Vector();
			Object result = client.execute("beers.getBeers", params);
			return result.toString();
		}
		catch(IOException ioe)		{System.out.println("IO error: " + ioe.getMessage());}
		catch(XmlRpcException xre)	{System.out.println("XmlException error: " + xre.getMessage());}
		catch(Exception excp)		{System.out.println("Exception error: " + excp.getMessage());}
		
		return null;
	}
	
	/**
	 * 
	 * Returns the cheapest beer.
	 * @return cheapest beer
	 * 
	 */
	public String getCheapest()
	{
		try
		{
			Vector params = new Vector();
			Object result = client.execute("beers.getCheapest", params);
			return result.toString();
		}
		catch(IOException ioe)		{System.out.println("IO error: " + ioe.getMessage());}
		catch(XmlRpcException xre)	{System.out.println("XmlException error: " + xre.getMessage());}
		catch(Exception excp)		{System.out.println("Exception error: " + excp.getMessage());}
		
		return null;
	}
	
	/**
	 * 
	 * Returns the costliest beer.
	 * @return costliest beer
	 * 
	 */
	public String getCostliest()
	{
		try
		{
			Vector params = new Vector();
			Object result = client.execute("beers.getCostliest", params);
			return result.toString();
		}
		catch(IOException ioe)		{System.out.println("IO error: " + ioe.getMessage());}
		catch(XmlRpcException xre)	{System.out.println("XmlException error: " + xre.getMessage());}
		catch(Exception excp)		{System.out.println("Exception error: " + excp.getMessage());}
		
		return null;
	}
}