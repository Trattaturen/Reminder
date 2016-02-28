# Reminder
Application to add/remove events (however there is no DB yet so a lifecycle of them is not very good)

What it does for now:

- main page gives options of login,logout, go to profile(dashboard)

- login (only password is checked for now) gives a "name" attribute to current session

- logout invalidates current session

- dashboard can be accesed if current session has a "name" attribute

Dashboard contains:

- User info

- Add event button: forwards to Add Event Page

- Display event button: forwards to Display Event Page (displays all events + allows to use filters (Title, Day, Time)

What to fix:


- Delete button on display page

- Authorization using filters

- Html improvements/CSS implementation

- Temporary DB (File) to save events

