# Reminder (Task 7 in progress)

## DONE:

- Logging in now available:

1. AuthenticationFilter checks if session has "user" attribute on Add/Display servlets if no - redirects to login.jsp

2. LoginServlet POST method is filtered by LoginFilter (to check if fields are empty). If empty - redirecting back to login.jsp else:

3. LoginServlet searches (with help of UserRepoHandler) for provided user, password. If not found - redirecting back to login.jsp else:

4. LoginServlet creates session and sets "user" attribute, adds "user" cookie and redirects to index.jsp.

5. All JSP`s check (by means of JSTL) if "user" parameter is set to current session if not: displaying "Sign Up|Log In" else: "UserName|Log out"    


## TODO:

- Hashcode, cookies implementation;


(Task 6 DONE)

## DONE:

- Created User model, repo, utils

- Created Registration Servlet, JSP:
	- Checking given parameters by means of RegEx;
	- If correct - creating new user and adding to DB;
	
- Created Filters to check POST requests for Registration

- Moved log4j.properties file to proper position :)

## Fixed (Review #2):

- Changed project structure

- Migrated to log4j2.5

- Changed logging information

- Changed registration.jsp (error messages moved to third column of table)




