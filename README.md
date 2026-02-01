 			Media Streaming API







Project Overview:

This project is a console-based Media Streaming App implemented in Java using JDBC and SQL.

The app allows users to manage media content such as Movies and Series, as well as Episodes

for series. The system demonstrates advanced OOP principles, SQL knowledge, Custom exceptons .







			OOP Design



	Abstract Class

MediaContent is an abstract base class with common fields:

id,title.

Abstract methods:

getDuration(),getType()

concrete method:
toString().



	Subclasses

Movie – represents a movie with fixed duration.

Series– represents a series consisting of multiple episodes.

Both classes override abstract methods from `MediaContent`.



	Composition / Aggregation:

Series contains a list of Episodes.

Each episode has a name and duration.



	Interfaces

IDB – database connection abstraction.

IMediaRepository , IEpisodeRepository – repository contracts.

IMediaController – controller contract.



Polymorphism

Polymorphism is demonstrated by:

Storing Movie and Series objects as MediaContent

Calling overridden methods (`getDuration()`, `getType()`) 

    



		Database Design



	Tables

media_content

id (PK)

title

type (MOVIE or SERIES)

duration



episodes

id (PK)

series_id (FK → media_content.id)

episode_name

`minute



Relationships

One Series -> Many Episodes (foreign key constraint)







	JDBC Repository Layer

Uses DriverManager for connection

Uses PreparedStatement for all SQL operations

Supports full CRUD operations for media content

Episodes are handled via a separate repository







	Controller Layer

   MediaController:

validates input

handles business rules

throws custom exceptions:

  -InvalidMediaException`

  -MediaNotFoundException`

  -DatabaseException







	Exception Handling

Custom runtime exceptions are used to:

 - validate user input

 - prevent invalid operations (e.g., adding episode to movie)

 - report missing resources





	How to Run



Requirements:

- Java 17+

- PostgreSQL

- Environment variables:

  - DB_HOST

  - DB_USER

  - DB_PASSWORD

  - DB_NAME



	Run

javac Main.java

java Main


after 1.0 version updates:

ADDED FULLY SOLID PRINCIPLES :

	- Controller: Handles user input and output only
	- Service: Contains validation and business rules
	- Repository: Manages database operations only
	- Utils: Contain helper logic (sorting, reflection)
OCP PRINCIPLES(OPEN-CLOSED):
	My code allows extensio w/o modification
	new media can be added , w/o changing all logic
LCP PRINCIPLES:
	Wherever mediacontent is expected , there we can use subclasses movie and series
	exmpl:
	MediaContent media = new Movie(1, "Inception", 148);


Now projects follow strict architecture: 
		application->Controller -> Service -> Repository -> Database

New interfaces: IMediaService , CRUD 

ADDED LAMBDA EXPRESSION FOR SORTING:
		SORT BY DURATION , TITLE ;
		EXMPL:
		list.stream()
    	.sorted(Comparator.comparing(MediaContent::getTitle))
    	.toList();
ADDED REFLECTION TO SHOW CLASS NAME, SUPER CLASS , FIELDS ,ETC.

OVERALL APP FEATURES:

Add movies

Add series

Add episodes to series

View media by ID

View all media

Update media

Delete media

Sort media using lambdas

Inspect media using reflection





