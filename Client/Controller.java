import java.util.ArrayList;

/**
 * 
 * This class serves as the Controller for the application.
 * @author Roman Pusec
 * 
 */
public class Controller implements IObservable
{
	//Attributes
	private DataAccess dao;
	private ArrayList<IObserver> observers;
	
	/**
	 * 
	 * Constructor which takes the server path and creates the DAO.
	 * @param serverPath Path to the server
	 * 
	 */
	public Controller(String serverPath)
	{
		dao = new DataAccess(serverPath);
		observers = new ArrayList<IObserver>();
	}
	
	/**
	 * 
	 * Returns the list of methods.
	 * 
	 */
	public void getMethods()
	{
		notifyObservers("The methods are: " + dao.getMethods() + "\n");
	}
	
	/**
	 * 
	 * Retrieves the price of a specified beer.
	 * @param beer 
	 * 
	 */
	public void getPrice(String beer)
	{
		notifyObservers(dao.getPrice(beer) + "\n");
	}
	
	/**
	 * 
	 * Sets the price of a beer.
	 * @param beer Selected beer
	 * @param price The chosen price
	 * 
	 */
	public void setPrice(String beer, String price)
	{
		notifyObservers(dao.setPrice(beer, price) + "\n");
	}
	
	/**
	 * 
	 * Returns all beers.
	 * 
	 */
	public void getBeers()
	{
		notifyObservers("The beers are: " + dao.getBeers() + "\n");
	}
	
	/**
	 * 
	 * Returns the cheapest beer.
	 * 
	 */
	public void getCheapest()
	{
		notifyObservers("The cheapest beer is: " + dao.getCheapest() + "\n");
	}
	
	/**
	 * 
	 * Returns the costliest beer.
	 * 
	 */
	public void getCostliest()
	{
		notifyObservers("The costliest beer is: " + dao.getCostliest() + "\n");
	}
   
   public void subscribe(IObserver observer)
   {
      if(!observers.contains(observer))
         observers.add(observer);
   }
   
   public void unsubscribe(IObserver observer)
   {
      if(observers.contains(observer))
         observers.remove(observer);
   }
   
   public void notifyObservers(String log)
   {
      for(IObserver observer : observers)
         observer.notifyObserver(log);
   }
}