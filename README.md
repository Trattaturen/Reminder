# Reminder
Application to add/remove events (however there is no DB yet so a lifecycle of them is not very good)

What it does for now:

- main page gives options of login,logout, go to profile(dashboard)

- login (only password is checked for now) gives a "name" attribute to current session

- logout invalidates current session

- dashboard can be accesed if current session has a "name" attribute

Dashboard methods:

- Add event is proceeded only if all fields are filled (otherwise - error)
(e.g. title=Java&day=&time=18.00 causes error)

- Remove event is processed any way. If  0 < filled fields < 3 - deletes all matching events. 
(e.g. title=&day=Sunday&time= removes ALL events planned on Sunday)

- Display event is processed any way (in case all fields are empty - provides all events from pool otherwise - all matching events)
(e.g. title=Java&day=&time= displays ALL events with title "Java")

What to fix:

- Implement JSP/Filters

- Create specified pages for methods, available from dashboard

- Exclude "bidlocod" such as crazy unreadable loops from Remove and Display servlets

- Organize classes by packages

- Make a little bit "prettier" (HTML improvement/CSS implementation) 
