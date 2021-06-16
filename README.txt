--------Autores-------------------------------------------------------

Lina Salinas
Juan José Valencia
Jhon Edward Mora

---------Compilar y correr Server-------------------------------------

frascati compile server/src CalculoPi-server
frascati run CalculoPi-server -libpath CalculoPi-server.jar -s r -m run

---------Compilar y correr Client--------------------------------------

frascati compile client/src CalculoPi-client
frascati run CalculoPi-client -libpath CalculoPi-client.jar -s r -m run

---------Compilar y correr Broker-------------------------------------

frascati compile broker/src CalculoPi-broker
frascati run CalculoPi-broker -libpath CalculoPi-broker.jar 