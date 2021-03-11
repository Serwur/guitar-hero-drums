#about
Handles guitar hero drums input and send it to output midi device  

#download
Download directory out. It includes .jar file and its natives required to for jinput

#running
## linux & windows
To run application run command in CLI: 
>java -Djava.library.path=./jinput-natives -Djinput.loglevel=OFF -jar gh-drums.jar 

## windows problems
Sometimes settings -Djava.library.path does not work as expected. 
In case of any exception saying that natives were not found, just put them at location **C:/Windows/System32**. Natives to copy:

* jinput-dx8_64.dll
* jinput-raw_64.dll

Natives can be found in **jinput-natives** directory