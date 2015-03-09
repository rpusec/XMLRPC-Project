<?php
	include "DataAccess.class.php";
	
	class Controller
	{
		private static $path = "XML/xmlBeers.xml"; //path to the database
		
		public static function getMethods()
		{	
			//creates the array of methods from the service
			$arrMethods = array("getMethods()", "getPrice()", "setPrice()", "getBeers()", "getCheapest()", "getCostliest()", "getPrices()");
			$storeMethods = array(); //this array will store the xmlrpcvals
			
			foreach($arrMethods as $method)
				$storeMethods[] = new xmlrpcval($method, "string");
			
			$rpcMethods = new xmlrpcval($storeMethods, "array");
			
			return new xmlrpcresp($rpcMethods);
		}
		
		public static function getPrice($params)
		{
			$arrBeers = Controller::retrieveBeers(); //retrieves the beers
			$whichBeer = $params->getParam(0)->scalarval(); //gets the first parameter
			
			//loops through the arrBeers array
			foreach($arrBeers as $beer)
			{
				//tests if beer is equivalent to the user's input
				if($beer->getName() == $whichBeer)
				{
					//gets the price and returns an xmlrpcresp
					$recievedPrice = $beer->getPrice();
					$outputMsg = "The price for " . $whichBeer . " is: $" . $recievedPrice;
					return new xmlrpcresp(new xmlrpcval($outputMsg, "string"));
				}
			}
			
			$msg = "Beer \"$whichBeer\" was not found. ";
			return new xmlrpcresp(new xmlrpcval($msg, "string"));
		}
		
		public static function setPrice($params) 
		{
			//retrieved the beers and sets the parameters
			$arrBeers = Controller::retrieveBeers();
			$whichBeer = $params->getParam(0)->scalarval();
			$price = $params->getParam(1)->scalarval();
			
			//checks if it's a legitimate number
			if(preg_match("/(^-?\d\d*\.\d*$)|(^-?\d\d*$)|(^-?\.\d\d*$)/", $price))
			{
				$dot = strrpos($price, "."); //last occurance of the dot (eg. $12.15)
				
				//checks if the dot exists
				if($dot !== FALSE)
				{
					//checks in which position is the dot
					if($dot > 2)
					{
						//if it's not on the second position or less, the price is too big
						$msg = "Number must contain at least two digits. ";
						return new xmlrpcresp(new xmlrpcval($msg, "string"));
					}
				}
				else
				{
					//checks if the price is more than two digits long
					if(strlen($price) > 2)
					{
						$msg = "Number must contain at least two digits. ";
						return new xmlrpcresp(new xmlrpcval($msg, "string"));
					}
				}
				
				//converts to float and rounds to two decimals
				$price = floatval($price);
				$price = round($price, 2);
				
				$elemCounter = 0; //this will be the specified index of an element we want to edit
				foreach($arrBeers as $beer)
				{
					if($beer->getName() == $whichBeer)
					{
						#$priceDbl = doubleval($price); //converts the number to a double
						$dao = new DataAccess(Controller::$path);
						$dao->changeItem($elemCounter, 'beer', 'price', $price); //Dao changed the node value of the element
						return new xmlrpcresp(new xmlrpcval("The price of $whichBeer has been modified. "));
					}
					
					$elemCounter++;
				}
				
				$msg = "Beer \"$whichBeer\" not found. ";
				return new xmlrpcresp(new xmlrpcval($msg, "string"));
			}
			
			$msg = "A number is required for the price. ";
			return new xmlrpcresp(new xmlrpcval($msg, "string"));
		}
		
		public static function getBeers()
		{
			$arrBeers = Controller::retrieveBeers(); //retrieves the beers
			
			$storeBeers = array(); //temporary array which will store xmlrpcvals
			
			foreach($arrBeers as $beer)
			{
				$beerStr = $beer->__toString();
				$storeBeers[] = new xmlrpcval($beerStr, "string");
			}
			
			return new xmlrpcresp(new xmlrpcval($storeBeers, "array"));
		}
		
		public static function getCheapest()
		{	
			$arrBeers = Controller::retrieveBeers(); //retrieves beers
			
			$arrPrices = Controller::getPrices($arrBeers); //gets the prices of all beers
			$cheapestPrice = min($arrPrices); //returns the cheapest
			
			//loops through each beer
			foreach($arrBeers as $beer)
			{
				if($beer->getPrice() == $cheapestPrice)
				{
					$recievedBeer = $beer->getName(); //returns the name of the cheapest beer
					return new xmlrpcresp(new xmlrpcval($recievedBeer, "string"));
				}
			}
		}
		
		public static function getCostliest()
		{
			$arrBeers = Controller::retrieveBeers(); //gets the beers
			
			$arrPrices = Controller::getPrices($arrBeers); //gets the prices of all beers
			$costliestPrice = max($arrPrices); //returns the costliest
			
			//loops through each beer
			foreach($arrBeers as $beer)
			{
				if($beer->getPrice() == $costliestPrice)
				{
					$recievedBeer = $beer->getName(); //returns the name of the costliest beer
					return new xmlrpcresp(new xmlrpcval($recievedBeer, "string"));
				}
			}
		}
		
		private static function getPrices($items)
		{
			$arrPrices = array(); //will contain prices
			
			//loops through each beer and stores its price
			foreach($items as $beer)
			{
				$price = $beer->getPrice();
				array_push($arrPrices, $price);
			}
			
			return $arrPrices;
		}
		
		
		private static function retrieveBeers()
		{
			//creates a DataAccess object and stores the beers to $arrBeers array
			$dao = new DataAccess(Controller::$path);
			$arrBeers = $dao->retrieveItems();
			return $arrBeers;
		}
	}
?>