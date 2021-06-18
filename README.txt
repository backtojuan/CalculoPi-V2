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


---------Distribución en máquinas de Liasón--------
Inicialmente defina qué máquinas desea utilizar, un mínimo de 1 es soportado, pero se pueden utilizar cuantas máquinas sea necesario.
Una vez definida la cantidad, defina en qué máquinas estarán hosteados los diferentes módulos. Para ello, recomendamos que una máquina se encarge de hostear el módulo broker, y utilizar máquinas adicionales por cada módulo cliente y servidor que se desee agregar.
Finalmente, modifique los archivos .composite de server, client y broker para incluir los nombres de red de las máquinas que desea utilizar, tal cómo se recomienda a continuación:

-Para el componente Server:
Módifique la línea 12:
<property name="serveruri">rmi://localhost:1099/rmiservice2</property>
para incluir en vez de localhost, el nombre de red de la máquina en la que tendrá alojado su módulo server, en este caso hgrid4
<property name="serveruri">rmi://hgrid4:1099/rmiservice2</property>

En la línea 24, modifique localhost:
<frascati:binding.rmi host="localhost" serviceName="rmiservice3" port="2001"/>
Y reemplacelo por el nombre de red de la máquina dónde aloja su módulo broker
<frascati:binding.rmi host="hgrid3" serviceName="rmiservice3" port="2001"/>
Finalmente regrese a la carpeta raíz del proyecto y recompile con los pasos de arriba

-Para el componente Client:
Módifique la línea 17:
<property name="clienturi">rmi://localhost:2003/rmiservice1</property>
para incluir en vez de localhost, el nombre de red de la máquina en la que tendrá alojado su módulo client, en este caso, hgrid5:
<property name="clienturi">rmi://hgrid5:2003/rmiservice1</property>

En la línea 29, reemplace localhost:
<frascati:binding.rmi host="localhost" serviceName="rmiservice3" port="2001"/>
con el nombre de red de la máquina en dónde aloja su módulo broker:
<frascati:binding.rmi host="hgrid3" serviceName="rmiservice3" port="2001"/>

Repita el proceso en la línea 33
<frascati:binding.rmi host="localhost" serviceName="rmiservice4" port="2002"/>

<frascati:binding.rmi host="hgrid3" serviceName="rmiservice4" port="2002"/>
Finalmente regrese a la carpeta raíz del proyecto y recompile con los pasos de arriba