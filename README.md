#about
Handles guitar hero drums input and send it to output midi device  

#download
Download directory out. It includes .jar file and its natives required to for jinput

#running
## linux & windows
To run application input command in CLI: java -Djava.library.path=./lib -Djinput.loglevel=OFF -jar gh-drums.jar 

## windows problems
Sometimes settings -Djava.library.path does not work as expected. In case of any exception natives are not found, just pust them at location: C:\Windows\System32