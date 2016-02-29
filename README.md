# Reminder
Application to add/remove events (however there is no DB yet so a lifecycle of them is not very good)

# What it does for now:

- main page gives options of login, logout, go to profile(dashboard);

- login (only password is checked for now) gives a "name" attribute to current session;

- logout invalidates current session;

- dashboard can be accessed if current session has a "name" attribute.

# Dashboard contains:

1. User info

2. Add event button: forwards to Add Event Page

3. Display event button: forwards to Display Event Page:
           3.1. Displays all events
           3.2. Allows to use filters (Title, Day, Time)
           3.3. Allows to delete random quantity of displayed events 

# What to fix:

- (done) Delete button on display page

- (done) Authorization using filters 

- (always in progress) Html improvements

- (next lesson) CSS implementation

- (after learning JSP) clean all code from HTML mess 

- Temporary DB (File) to save events

- (need review) do something with RepoHandler class

