# WebServer

### **TESTING** 

**The project was tested using:**

•	unit testing (including mock testing where needed)

•	GUI tests 

•	Webserver Tests (loading pages, functional links, active css)

</br>

### **INTERFACE**

**The webserver contains a clean interface that allows users to view information regarding the current status (status, address, listening port) and also allows users to switch between states (stopped, running and maintenance):**

**STOPPED**

•	Allows user to set Port Number, Root Directory and Maintenance Directory

•	If the configurations are valid the webserver can be started.
<p align="center">
  <img src="ScreenshotsGUI\Stopped.png" width="600">
</p>


**RUNNING**

•	Allows user to change the Maintenance Directory

•	If the configurations are valid the webserver can enter maintenance, or it can be stopped.
<p align="center">
  <img src="ScreenshotsGUI\Running.png" width="600">
</p>

**MAINTENANCE**

•	Allows user to change the Root Directory

•	If the configurations are valid the webserver can go back to the running state.
<p align="center">
  <img src="ScreenshotsGUI\Maintenance.png" width="600">
</p>

If certain configurations are not valid, then the webserver will not change state. Below is an example of having a wrong root directory.
<p align="center">
  <img src="ScreenshotsGUI\Error.png" width="600">
</p>
