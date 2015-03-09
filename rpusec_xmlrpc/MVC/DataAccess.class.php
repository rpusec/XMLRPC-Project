<?php
	include "Beer.class.php";
	
	class DataAccess
	{
		private $path; //location of the xml file
		
		public function __construct($path)
		{
			$this->path = $path; //specifies the path
		}
		
		public function retrieveItems()
		{
			$arrBeers = array(); //will store beers
			
			//creates the DomDocument object
			$dom = new DomDocument();
			$dom->load($this->path);
			
			$allBeers = $dom->getElementsByTagName('beer'); //gets all elements 
			
			foreach($allBeers as $beer){
				//gets the node values for name and price and stores them in the specified variables
				$name = $beer->getElementsByTagName('name')->item(0)->nodeValue;
				$price = $beer->getElementsByTagName('price')->item(0)->nodeValue;
				
				array_push($arrBeers, new Beer($name, $price)); //creates a new beer object and adds it to the array
			}
			
			return $arrBeers; //returns beers
		}
		
		public function changeItem($index, $element, $subElement, $newValue)
		{
			$dom = new DomDocument();
			$dom->load($this->path);
			
			$allElements = $dom->getElementsByTagName($element); //gets all elements specified by $element
			$chosenItem = $allElements->item($index); //chosen item, specified by the index
			$chosenSubItem = $chosenItem->getElementsByTagName($subElement)->item(0)->nodeValue = $newValue; //replaces the previous value with the $newValue
			
			//saves the xml
			$dom->saveXML();
			$dom->save($this->path);
		}
	}
?>