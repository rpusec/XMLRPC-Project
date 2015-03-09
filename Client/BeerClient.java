import java.awt.*;
import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.event.*;
import java.util.*;

/**
 * 
 * This class contains the GUI for the application.
 * @author Roman Pusec
 * 
 */
public class BeerClient extends JFrame implements ActionListener, IObserver{
	
	//GUI
	private JPanel jpCenter, jpEast, jpSouth, jpGetBeerPrice, jpSetBeerPrice;
	private JTextArea jtaOutput;
	private JTextField jtfGetBeer, jtfGetBeerPrice, jftSetPrice;
	private JButton jbGetMethods, jbGetPrice, jbSetPrice, jbGetBeers, jbGetCheapest, jbGetCostliest;
	private JMenuBar menuBar;
	private JMenu jmFile, jmOptions, jmHelp;
	private JMenuItem jmiExit, jmiClear, jmiAbout;
	
	//Controller
	private Controller controller;
	
	/**
	 * 
	 * Constructor which generates the GUI, also requires the path to the XML-RPC server.
	 * @param serverPath Path to the server. 
	 * 
	 */
	public BeerClient(String serverPath)
	{
		this.setTitle("Beer Client");
		
		//create menu
		createMenu();
		
		//panels
		jpCenter = new JPanel(new BorderLayout());
		jpEast = new JPanel(new GridLayout(4,0,45,45));
		jpSouth = new JPanel(new GridLayout(0,1));
		jpGetBeerPrice = new JPanel(new GridBagLayout());
		jpSetBeerPrice = new JPanel(new GridBagLayout());
		
		//titles
		jpCenter.setBorder(BorderFactory.createTitledBorder("Output"));
		jpEast.setBorder(BorderFactory.createTitledBorder("Options"));
		jpGetBeerPrice.setBorder(BorderFactory.createTitledBorder("Get Price"));
		jpSetBeerPrice.setBorder(BorderFactory.createTitledBorder("Set Price"));
		
		//output window
		jtaOutput = new JTextArea();
		jtaOutput.setEnabled(false);
		jtaOutput.setDisabledTextColor(Color.BLACK);
		jtaOutput.setWrapStyleWord(true);
		jtaOutput.setLineWrap(true);
		DefaultCaret c = (DefaultCaret)jtaOutput.getCaret();
		c.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		JScrollPane jsp = new JScrollPane(jtaOutput);
		
		//buttons
		jbSetPrice = new JButton("Set Price");
		jbGetMethods = new JButton("Get Methods");
		jbGetPrice = new JButton("Get Price");
		jbGetBeers = new JButton("Get Beers");
		jbGetCheapest = new JButton("Get Cheapest");
		jbGetCostliest = new JButton("Get Costliest");
		
		//JTextFields
		jtfGetBeer = new JTextField(10);
		jtfGetBeerPrice = new JTextField(10);
		jftSetPrice = new JTextField(10);
		
		//adding elements to JPanels
		jpCenter.add(jsp);
		jpEast.add(jbGetMethods);
		jpEast.add(jbGetBeers);
		jpEast.add(jbGetCheapest);
		jpEast.add(jbGetCostliest);
		jpGetBeerPrice.add(new JLabel("Beer: ", JLabel.CENTER));
		jpGetBeerPrice.add(jtfGetBeer);
		jpGetBeerPrice.add(jbGetPrice);
		jpSetBeerPrice.add(new JLabel("Beer: ", JLabel.CENTER));
		jpSetBeerPrice.add(jtfGetBeerPrice);
		jpSetBeerPrice.add(new JLabel("New Price", JLabel.CENTER));
		jpSetBeerPrice.add(jftSetPrice);
		jpSetBeerPrice.add(jbSetPrice);
		jpSouth.add(jpGetBeerPrice, "North");
		jpSouth.add(jpSetBeerPrice, "South");
		
		//adding panels to the frame
		this.add(jpCenter, "Center");
		this.add(jpEast, "East");
		this.add(jpSouth, "South");
		
		//action listeners
		jbGetMethods.addActionListener(this);
		jbGetPrice.addActionListener(this);
		jbSetPrice.addActionListener(this);
		jbGetBeers.addActionListener(this);
		jbGetCheapest.addActionListener(this);
		jbGetCostliest.addActionListener(this);
		jmiExit.addActionListener(this);
		jmiClear.addActionListener(this);
		jmiAbout.addActionListener(this);
		
		//options
		this.setSize(600,450);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		//initializing controller
		this.controller = new Controller(serverPath);
      controller.subscribe(this);
	}
	
	/**
	 * 
	 * Creates the JMenuBar.
	 * 
	 */
	private void createMenu()
	{
		menuBar = new JMenuBar();
		
		jmFile = new JMenu("File");
		jmOptions = new JMenu("Options");
		jmHelp = new JMenu("Help");
		
		jmiExit = new JMenuItem("Exit");
		jmiClear = new JMenuItem("Clear");
		jmiAbout = new JMenuItem("About");
		
		jmFile.add(jmiExit);
		jmOptions.add(jmiClear);
		jmHelp.add(jmiAbout);
		
		menuBar.add(jmFile);
		menuBar.add(jmOptions);
		menuBar.add(jmHelp);
		
		this.setJMenuBar(menuBar);
	}
	
	/**
	 * 
	 * Overriden actionPerformed method.
	 * 
	 */
	public void actionPerformed(ActionEvent e)
	{	
		//creates the waiting cursor
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		
		if(e.getSource().equals(jbGetMethods))
		{
			controller.getMethods();
		}
		else if(e.getSource().equals(jbGetPrice))
		{
			if(!jtfGetBeer.getText().equals(""))
				controller.getPrice(jtfGetBeer.getText());
			else
				jtaOutput.append("The beer input is empty. \n");
		}
		else if(e.getSource().equals(jbSetPrice))
		{
			if(!jtfGetBeerPrice.getText().equals("") && !jftSetPrice.getText().equals(""))
				controller.setPrice(jtfGetBeerPrice.getText(), jftSetPrice.getText());
			else
				jtaOutput.append("Please check if the beer and price inputs aren't empty. \n");
		}
		else if(e.getSource().equals(jbGetBeers))
		{
			controller.getBeers();
		}
		else if(e.getSource().equals(jbGetCheapest))
		{
			controller.getCheapest();
		}
		else if(e.getSource().equals(jbGetCostliest))
		{
			controller.getCostliest();
		}
		
		//returns back the default cursor
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		
		if(e.getSource().equals(jmiExit))
			System.exit(0);
		
		else if(e.getSource().equals(jmiClear))
			jtaOutput.setText("");
		
		else if(e.getSource().equals(jmiAbout))
		{
			JOptionPane.showMessageDialog(null, 
					"Project 2 - Beer client\n" + 
					"ISTE.341.02 - Server Programming\n" +
					"Roman Pusec, 2014",
					"About", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
   public void notifyObserver(String log)
   {
      jtaOutput.append(log);
   }
   
	public static void main(String [] args)
	{
		if(args.length == 0)
			new BeerClient("http://localhost/rpusec_xmlrpc/xmlrpc_server.php"); //defaults to this path
		else
			new BeerClient(args[0]); 
	}
}
