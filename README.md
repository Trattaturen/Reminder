# Reminder (Task 5 in progress REVIEW #1)

## DONE:

- Created "docs" folder with instructions on configuring "Maven to Tomcat" plugin 

- Created profiles in POM.xml:

	- Student (default) - deploying to "http://localhost:8080/manager/text"
	- Mentor (start with "-P Mentor" flag) - deploying to "http://localhost:8181/manager/text" 

- Changed LOG_FILE name to reminder.log

- Moved reminder.log to ${catalina.base}/logs

- Added Method name to logging information

- Changed logging layout pattern

- Improved logging levels

## What to fix:

- Move log4j.properties file

- Get review of logging levels