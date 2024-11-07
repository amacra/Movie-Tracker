# MyTV
### A library of movies you have watched
**This application is a way of recording your watch history**  
*You can update your library regularly, see your favourites, and see the time spent on watching TV*  
*Any regular movie watcher as myself should find this application useful*


### User stories:
- As a user I want to add a movie to my library (specifying the title, duration, category, ranking (scale 1-10))
- As a user I want to be able to increase the number of times I've watched a specific movie
- As a user I want to view the list of movies in the library and be able to search by category, ranking, see up to 10 favourites
- As a user I want to be able to select a movie and write a review and be able to see these reviews
- As a user I want to see how much time I spend watching TV
- As a user, when I quit my application I want to be able to choose whether to save the movies or reviews added
- As a user, when I start my application I want to be able to choose whether to load the previous list of movies 


### Instructions for grader:
- You can reload the state of my application by clicking the load library button in the home tab
- You can save the state of my application by clicking the save library button in the home tab
- You can add movies to a movieLibrary by going to the add movies tab filling out the required fields and clicking the add movie button 
- You can see all movies by going to the movies tab and clicking the button all movies
- You can see a list of up to ten favourite (highest rated movies) by clicking the favourites button in the movies tab
- You can see the most watched movie from the library by clicking the most watched button in the movies tab
- You can see the total watch time (In days, hours and minutes) by clicking the watch time button in watch time tab
- You can locate my visual component by clicking the watch time age button in Watch Time tab that will display an image of someone who is roughly the age of you if all u had been doing is watching the movies in this library


** **
**Phase 4: Task 2**

*Example of Events Logged*
- Thu Nov 30 19:48:44 PST 2023
- Loaded Movie Library from file
- Thu Nov 30 19:48:46 PST 2023
- Requested list of all movies: returned list of size 2
- Thu Nov 30 19:49:08 PST 2023
- Movie added to list: MOVIE 3
- Thu Nov 30 19:49:13 PST 2023
- Requested list of all movies: returned list of size 3
- Thu Nov 30 19:49:13 PST 2023
- Requested list of favourites: returned list of size 3
- Thu Nov 30 19:49:14 PST 2023
- Most watched movie requested: MOVIE 11
- Thu Nov 30 19:49:16 PST 2023
- Total time watching requested: 296.6
- Thu Nov 30 19:49:18 PST 2023
- Saved My Movie Library to file

** **
**Phase 4 - Task 3**

- The design for the model in my project is a simple association, there is a list of Objects (Movie) in a MovieLibrary class. 
For this application I believe this is a good and efficient design.

*If I had more time:*
- I would replace REQUIRES clauses and if statements that satisfy them with exceptions, when making sure conditions 
are met when creating a movie object anywhere in the program.
- In the MovieLibrary I could change the List of Movies into a Map<Movie, List\<String\>> where I would map the reviews for the movies. 
A map is a good option for this application since there cannot be duplicates, and it would be much more efficient to get reviews for a specific movie,
instead of iterating over the list, you could look up easier by key.