<h1>Rick and Morty application</h1>

<h3> Description </h3>

Application shows detailed information about characters from Rick and Morty cartoon, based on global API.

API URL:

>https://rickandmortyapi.com/


<h3>Features</h3>
Application shows information about favorite character from Rick and Morty. There are 826 to pick from!
Here are options how to look:
- Search by id
- Search by name
- Get random character

All the characters are instantly adds it to Postgre database

<h3>Running</h3>
Setup DB

>docker run --name boczar -it --rm -p 5432:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=password -v C:/Users/Piortek/IdeaProjects/p0-piotrek/src/main/resources/schema.sql:/docker-entrypoint-initdb.d/schema.sql postgres

<h3>Debug</h3>

>mvn exec:java

<h3>Author</h3>

Piotr Boczar