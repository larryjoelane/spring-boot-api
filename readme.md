##Spring Boot Api Starter Project

### creating the MySQL database
#### note: the database must match the name in the application.properties file
example: spring.datasource.url = jdbc:mysql://localhost:3306/articles_app?useSSL=false

Login into you mysql terminal by typing in 

`mysql -u yourusernamegoeshere -p`

You will be prompted for a password. After entering your password enter the
command below in your mysql command prompt.

`CREATE DATABASE articles_app;`

The above command can be run from the mysql terminal to create the database
needed for the spring.datasource.url value located within the application.properties
file. It is a good practice to not use the root user for the database that way 
you will have more control over securing your application. Below is an example of how to 
create a database user(It is assumed at this point that you have already created the database needed). 

CREATE USER 'articlesapp'@'localhost' IDENTIFIED BY 'yourchoosenpasswordhere';

Now this new user will not have any privileges so the next step is to set those up. So in the same mysql 
command prompt enter the following command.

`GRANT ALL PRIVILEGES ON articles_app . * TO 'articlesapp'@'localhost';`

Syntax structure for the previously given command(An asterisk(*) is indicates all).

`GRANT [type of permission] ON [database name].[table name] TO ‘[username]’@'localhost’;`

This will grant the user all privileges on the articles_app database and its tables. Once you are done 
with development you may choose to further restrict what the user can do.

Revoke permissions syntax.

`REVOKE [type of permission] ON [database name].[table name] FROM ‘[username]’@‘localhost’;`

List of additional permission commands.

* ALL PRIVILEGES- this would allow a MySQL user all access to a designated database 
  (or if no database is selected, across the system)
* CREATE- allows them to create new tables or databases
* DROP- allows them to them to delete tables or databases
* DELETE- allows them to delete rows from tables
* INSERT- allows them to insert rows into tables
* SELECT- allows them to use the Select command to read through databases
* UPDATE- allow them to update table rows
* GRANT OPTION- allows them to grant or remove other users' privileges

Command to make changes take effect after changing permissions

`FLUSH PRIVILEGES;`
