# Space IRS

### By the Galactic Federation of Tax People

This is Galactic Federations tax website...

## Running Site

### Database

Load schema.sql and example_data.sql onto a sequel database.

### Server

Create an application.properties file in the resources folder of the Server part of the project. This for should be based on the application.properties.template file and pointed at your SQL database. 
After this file is set up correctly, simply run the SpaceIRSApp.java file!

### Website

First you need to install the node packages, this is done by running...

```
npm i
```
This must be done in the Website/ directory. 

Then if you wish only to host the website run...

```
npm run host
```

However if you are going to be editing the code run

```
npm run dev
```

This is so tailwind continues to update it's tailout.css file.

### Test

Load test_schema.sql file to run test methods.
