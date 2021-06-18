--------Autores-------------------------------------------------------

Lina Salinas
Juan José Valencia
Jhon Edward Mora

Para compilar y ejecutar, asegurése de tener instalado frascati 1.4 y java 1.6

---------Compilación--------
Para compilar, úbiquese en la carpeta raiz del proyecto, y ejecute estos comandos

frascati compile server/src CalculoPi-server
frascati compile client/src CalculoPi-client
frascati compile broker/src CalculoPi-broker

---------Ejecución--------

Para ejecutar, úbiquese en la carpeta contenedora de los .jar y ejecute estos comandos

frascati run CalculoPi-broker -libpath CalculoPi-broker.jar -s r -m run
frascati run CalculoPi-server -libpath CalculoPi-server.jar -s r -m run
frascati run CalculoPi-client -libpath CalculoPi-client.jar -s r -m run
