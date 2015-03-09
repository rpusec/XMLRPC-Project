<?php 
	include 'XmlRpc/xmlrpc.inc';
	include 'XmlRpc/xmlrpcs.inc';
	include 'XmlRpc/xmlrpc_wrappers.inc';
	include "MVC/Controller.class.php";
	
	//signatures
	$getPrice_sig = array(array($xmlrpcValue, $xmlrpcString));
	$setPrice_sig = array(array($xmlrpcValue, $xmlrpcString, $xmlrpcString));
	
	//documentation
	$getMethods_doc 	= 'Retrieves all of the methods available to use. ';
	$getPrices_doc 		= 'Returns the price of a beer. ';
	$setPrices_doc 		= 'Sets the price of a beer. ';
	$getBeers_doc 		= 'Returns all beers. ';
	$getCheapest_doc	= 'Returns the cheapest beer. ';
	$getCostliest_doc 	= 'Returns the costliest beer. ';
	
	new xmlrpc_server(array(
		"beers.getMethods" => 	array("function" => "Controller::getMethods", "docstring" => $getMethods_doc),
		"beers.getPrice" => 	array("function" => "Controller::getPrice", "signature" => $getPrice_sig, "docstring" => $getPrices_doc),
		"beers.setPrice" => 	array("function" => "Controller::setPrice", "signature" => $setPrice_sig, "docstring" => $setPrices_doc),
		"beers.getBeers" => 	array("function" => "Controller::getBeers", "docstring" => $getBeers_doc),
		"beers.getCheapest" => 	array("function" => "Controller::getCheapest", "docstring" => $getCheapest_doc),
		"beers.getCostliest" => array("function" => "Controller::getCostliest", "docstring" => $getCostliest_doc)
	));
?>