# TRAFFIC APPLICATION
This is a traffic light application which uses SpringBoot, REST APIs, Velocity templates, GSon, and threading
to simulate a series of traffic lights.

# EXECUTE
To run this traffic light application
Run
```
java -jar target/traffic-app-0.1.0.jar
```
The go to a web browser and see:
* [http://localhost:8080/traffic]

# DESIGN
## Light
id=1,2
state = RED,ORANGE,GREEN
orientation=NORTH_SOUTH, EAST_WEST
linkedLightId= AnotherLight

## Services
### LightService
getLight()
changeState()
linkLights()

### TrafficService
start()
stop()
isStarted()
getElapsedTime()
switchState()

## API
* /traffic/light/1 GET {"id": "1", "state": "RED", "orientation": "NORTH_SOUTH", linkedLightId: "2"} 
* /traffic/light/2 GET {"id": "2", "state": "GREEN", "orientation": "EAST_WEST", linkedLightId: "1"} 
* /traffic/switch/1 GET {}
* /traffic/start GET {}
* /traffic/stop GET {}
* /traffic/watch GET {}

## Front end
Uses velocity template, traffic.vm
<div id="traffic">
	<div id="time"/>
	<div id="light1"/>
	<div id="light2"/>
</div>


Time
----
0.   RED / GREEN
4.5  RED / ORANGE
5    GREEN / RED
9.5  ORANGE / RED
10   RED / GREEN
