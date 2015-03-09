# XMLRPC-Project

<b>This project was originally released in April 3rd, 2014</b>

This was a small custom XML-RPC Java connection server programming project. We had to create our own custom XML-RPC and a 
client written in Java for the connection. 

Client side
-------
In order to run the client, double click on the `compile.bat` to compile all of the java files, and open the `client.bat` file to run the client. 

You can also specify the location of the XML-RPC server by altering the `client.bat` file to: 
`...pc-2.0.jar BeerClient *your custom XML-RPC server location*`

Server side
-------
The server side is stored inside of the `rpusec_xmlrpc` folder. Copy that folder into your `htdocs` directory of your environment that runs the Apache web server (since the XML-RPC directory defaults to `http://localhost/rpusec_xmlrpc`). 
