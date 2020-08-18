# Cab Data Client
__Description__

This is a simple console program written in java to invoke the REST APIs in the cab_data_research project.<br>

The program works as follows:<br>
 - provides the main menu which provides the following options:<br>
 	1. Get Trip Count For A Cab<br>
	2. Enter Admin Mode<br>
	3. Exit<br>
 - When the user chooses any option, the program provides that action<br>

__Build__
<br>To build the executable Jar, run the following maven command in the project root directory:<br>
``$ mvn clean package``

It will create a jar cab_data_client.jar in <project root>/target directory.<br>

__Run__
<br>To run the program:<br>
``$ java -jar target/cab_data_client.jar``

__Sample Run__

Below is a sample run of the program.<hr>

======================================<br>
	<tab>NY Cab Data Lookup CLI<br>
======================================<br>

Please Eneter your choice:
1. Get Trip Count For A Cab
2. Enter Admin Mode
3. Exit
Your Answer? 2

Available actions:
1. Clear Cache
2. Exit
Your Answer? 1

Please enter admin username: admin<br>
Please enter admin password: password<br>
Success<br>

Available actions:
1. Clear Cache
2. Exit
Your Answer? 2<br>
Bye!!




