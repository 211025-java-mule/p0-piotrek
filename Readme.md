<h1>Rick and Morty application</h1>

<h3> Description </h3>

Application allows user to pick any number from 1 to 826 and by that id searches in global API for information
about characters from Rick and Morty. 

API URL:

>https://rickandmortyapi.com/


<h3>Features</h3>
- Shows information about favorite character from Rick and Morty. There are 826 to pick from!
- Instantly adds it to Postgre database

<h3>Running</h3>
Setup DB

>docker run --name boczar -it --rm -p 5432:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=password -v C:/Users/Piortek/IdeaProjects/p0-piotrek/src/main/resources/schema.sql:/docker-entrypoint-initdb.d/schema.sql postgres

<h3>Debug</h3>

>mvn exec:java

<h3>Author</h3>

Piotr Boczar