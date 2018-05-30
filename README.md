# aye2-tp-red

Algoritmos y Estructura de Datos II

**Trabajo Práctico – Primer Cuatrimestre de 2018**

Requisitos de aprobación del trabajo
- Entrega de código fuente
- Presentación del trabajo y verificación del cumplimiento de los requerimientos solicitados.

**Enunciado**

Se solicita implementar un modelo basado en el paradigma de programación orientada a objetos que represente parcialmente una red de datos de computadoras. La red estará compuesta por diversos dispositivos, los cuales se clasifican como:
  - Hubs : Interconectan a varios dispositivos entre ellos.
  - Routers : Posibilitan el reenvío de paquetes ya que interconectan distintas redes de datos.
  - Terminales : Equipos operados por el usuario que pueden enviar y recibir paquetes a través de
una conexión a una red de datos.

El sistema deberá implementarse según lo especificado en la sección en el análisis donde se establece el modelo de datos y funcionalidad del mismo. Debe contemplarse para su construcción lo siguiente.

Requerimientos mínimos del modelo

  *Extensibilidad*:  Es necesario que el modelo pueda extenderse a nuevos dispositivos (sea por marca o modelo), sistemas operativos y protocolos, lo cual lleva a nuevos tipos de paquetes.
  
  *Gestión de errores*:  El sistema debe capturar todos los errores posibles en tiempo de ejecución y clasificarlos según corresponda con una jerarquía de excepciones.
  
  *Estándar abierto*:  Considerar que cualquier equipo debe poder conectarse a la red, por lo tanto es necesario que la implementación relacionada a la conectividad de cada uno esté normalizada. 
  
  *Mantenibilidad*:  Hacer uso de los principios de POO para construir un producto que sea de fácil mantenimiento, por ejemplo evitando la duplicación de código y desacoplando los subsistemas cuando sea posible.

Elementos del modelo

La red de datos de computadoras se conforma por tres tipos de equipos o dispositivos: los hubs, los routers y las terminales. Los dos primeros son quienes se encargan del envío de los paquetes a través de la red, mientras que las terminales y los routers son generadores y consumidores de dichos paquetes. El modelo solicitado no contempla una implementación detallada de todas las capas del modelo OSI o TCP/IP, sino que simplemente se pide una abstracción del mismo considerando algunas restricciones y componentes para lograr la interconexión.

Paquetes de Red

La información se envía y recibe mediante paquetes. Los paquetes se clasifican en dos tipos:
  - Paquete de servicio
  - Paquete de ruteo

Todo paquete tiene:
  - Dirección IP de destino 
  - Dirección IP de origen 
  - TTL (time to live)

Los paquetes de servicio son de un tipo de servicio, ya que según dicho tipo de servicio serán tratados de forma particular en las terminales. Este tipo de servicio debe ser extensible, es decir, deben poder agregarse nuevos tipos de servicio sin la necesidad de modificar demasiado el sistema en general. Los paquetes de servicio dependen del servicio que los genera (si un sistema operativo ejecuta el comando who , el paquete enviado será de tipo  who)  .
El TTL es un número entero mayor o igual a 0 que determina la cantidad de veces que un paquete puede pasar por un router. Se inicializa en el emisor de un paquete según el sistema operativo que tenga instalado. Si se recibe un paquete con TTL en 0, se descarta.

Los paquetes de ruteo contienen dentro a otro paquete que puede ser de servicio o ruteo.


Dirección de Equipo y de Red

La identificación de equipos activos de la red se realiza mediante una dirección IPv4, por lo cual se abstraen las capas inferiores del modelo OSI con una simple asociación de los equipos que represente la conexión. Las direcciones IP se asignan a interfaces que son componentes de un equipo terminal o router. A su vez, existe el concepto de una dirección de red que determina si un paquete puede enviarse directamente al destino o si debe pasar por un router para atravesar a otra red de datos.
La dirección IP se compone de 4 octetos, por lo tanto cada octeto se representa de 0 a 255. Para facilitar el modelo, se definen las siguientes restricciones para el uso de direcciones:
  - La dirección de red se asume será como si cada IP tuviera asignada una máscara /24 (clase C). Por lo tanto, la dirección de red estará definida por los primeros 3 octetos de la dirección IP y el último octeto en 0.
  - El primer octeto de una dirección IP debe ser mayor a 0.
  - El último octeto de una dirección IP no puede ser 0 ni 255.

Por ejemplo:

IP: 172.20.0.15
Dirección de red: 172.20.0.0
Por lo tanto, todo equipo que tenga una IP 172.20.0.X y esté conectado físicamente en la misma red de datos podrán comunicarse directamente con ese equipo, sin la necesidad de pasar por un router, ya que ambos pertenecen a la misma red lógica.


Equipos terminales

Las terminales son componentes activos en la red, lo que significa que pueden enviar y recibir paquetes. Los paquetes que se envían se generan por el usuario según el sistema operativo instalado y siempre son paquetes de servicio, ya que se generan por dichos servicios que tiene implementado el sistema operativo.

Detalles de las terminales:

- Pueden ser tablet, celular, PC, Notebook, Server, etc.
- Pueden tener instalado cualquier Sistema Operativo que implemente el protocolo de red.
- Tienen una única interfaz de conexión que permite conectarse a otro equipo de la red, por lo cual
sólo tiene una única conexión física a la red.
- Puede comunicarse con otros equipos de la red o redes diferentes.
- Los paquetes se envían a través de dicha interfaz.
- Tiene una o más direcciones IP asignadas.
- Tiene una única dirección IP especial llamada  default gateway .


Protocolo de Envío de paquetes

Si la dirección destino del paquete a enviar pertenece a la misma red que alguna de las direcciones IP propias asignadas, entonces el paquete de servicio se envía al equipo conectado mediante la interfaz. De lo contrario, el paquete de servicio a enviar se coloca dentro de un paquete nuevo de ruteo, y este último tendrá como dirección destino la IP del default gateway que tiene configurado el equipo. Finalmente el paquete de ruteo se envía al equipo conectado en la interfaz.

Protocolo de Recepción de paquetes

El paquete recibido sólo es aceptado si la dirección IP destino del mismo coincide con alguna de las direcciones IP asignadas a este equipo. Caso contrario, se descarta sin realizar acción adicional.

El paquete recibido y aceptado se tratará según su tipo de servicio:

- WHO : Envía al equipo que originó el paquete un nuevo paquete de tipo SendMessage que contenga información del Sistema Operativo local (nombre, versión, IP, etc).
- ICMPRequest : Envía al equipo que originó el paquete un nuevo paquete de tipo ICMPResponse. - ICMPResponse : Imprime en pantalla “ Recibido ICMP desde  equipo_origen [timestamp] ”
- SendMessage : Imprime en pantalla el texto dentro del paquete.

Si el paquete recibido y aceptado tiene un tipo desconocido, se descarta y no realiza acción adicional.


Equipos Hubs

Los hubs son componentes pasivos de la red, por lo cual no pueden generar paquetes para enviar a la red, sino que simplemente reenvían los paquetes recibidos. Disponen de un número finito de puertos, que puede variar según marca y modelo del equipo, donde cada puerto puede conectarse a un equipo en la red. Por lo tanto, son útiles para interconectar varios equipos juntos.

Protocolo de transmisión

No poseen inteligencia, entonces cuando un hub recibe un paquete en alguno de sus puertos lo reenvía a todos los equipos que estén conectados a los puertos del hub. El paquete reenviado es el mismo al original y no sufre modificaciones.

Equipos Routers

Los routers son componentes activos en la red, ya que pueden generar algunos tipos de paquetes que suelen ser generalmente de diagnóstico. Pero su función principal está en que proveen interconexión a dos redes lógicas distintas. Las distinción entre red lógica y física está en que la última se limita a las conexiones que hay entre equipo, es decir cómo están asociados según sus interfaces y puertos. La red lógica se determina por la dirección de red, donde dos equipos pueden pertenecer a una misma red física (ambos están conectados mediante un hub en el medio), pero si no pertenecen a la misma red lógica deben comunicarse mediante un router.

Detalles de los routers:

  - Poseen una tabla de ruteo, la cual se define con 2 columnas: dirección de red destino e interfaz
saliente.
  - Puede tener una interfaz asignada como default.
  - Tienen 2 o más interfaces, según lo defina el fabricante.
  - Tiene asignada una única dirección IP a cada interfaz.
  - La tabla de ruteo puede ser modificada por el usuario (agregar y eliminar rutas).

El agregado y borrado de rutas de la tabla de ruteo sólo es válido para rutas que no sean las definidas por cada IP asignada al router. Por defecto, cuando se asigna una dirección IP a una interfaz de un router se genera automáticamente una ruta (que no puede ser eliminada por el usuario) que tiene como campos la dirección de red de dicha IP asignada y la interfaz a cual se la asigna.

Protocolo de Envío de paquetes

Será posible generar sólo paquetes de tipo ICMPRequest ya que puede ser útil para el diagnósitco de la red.
  1) Si la dirección destino del paquete a enviar pertenece a alguna red en la tabla de ruteo (primer campo de la tabla), entonces el paquete de servicio se envía al equipo conectado mediante la interfaz que especifica esa entrada en la tabla de ruteo (segundo campo de la tabla).
  2) De lo contrario, se devuelve un mensaje al usuario en consola indicando que el destino es inaccesible.

Protocolo de Recepción de paquetes

Si se recibe un paquete de servicio y la dirección IP destino del mismo coincide con alguna dirección IP asignada al router, el paquete es aceptado y procesado según el tipo:
  - WHO : Envía al equipo que originó el paquete un nuevo paquete de tipo SendMessage que contenga información del Sistema Operativo local (nombre, versión, IP, etc) y la tabla de ruteo.
  - ICMPRequest : Envía al equipo que originó el paquete un nuevo paquete de tipo ICMPResponse.
  - ICMPResponse : Imprime en consola“ Recibido ICMP desde  equipo_origen [timestamp] ”

Si se recibe un paquete de ruteo primero se valida:

1) Se verifica que la dirección destino de dicho paquete coincida con alguna de las direcciones IP
asignadas al router. En tal caso se acepta el paquete.
2) Se decrementa en 1 el TTL y se comprueba que el TTL sea mayor a 0, si no se descarta el
paquete.
3) Se obtiene el paquete que lleva adentro y se hace lo siguiente con dicho paquete:
    1) Si la IP destino pertenece a alguna red de las definidas en la tabla de ruteo, entonces se
envía el paquete al equipo conectado a la interfaz asociada a dicha red.
    2) Si la IP destino no pertenece a alguna red de la tabla de ruteo, se envía un nuevo
paquete de ruteo (agregando el paquete dentro) a la interfaz default, si es que tiene
configurada.
    3) Caso contrario, se envía un paquete SendMessage informormando a quien generó el
paquete que no es posible enviarlo a destino.
