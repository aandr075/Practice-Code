//Homework 4. Solve in groups of 2. Each member of the groups submit on Moodle.  

//Please note that this assignment is due slightly earlier on the due day! That's because I need to look through them before class that day. Any late assignment will incur a penalty of 20points (out of 100). 
    
//This code lodes some movie data which was extracted from the IMDB database (check the included file "MovieSmall.txt"). 
// !!! You may need to play with the path of MovieSmall.txt !!!
//Otherwise, leave this code alone.
let readLines filePath = System.IO.File.ReadLines(filePath);
let lines = Seq.toList (readLines "C:\Users\khaos\Desktop\School Work\programming languages\MovieSmall.txt");
let lines2 = List.map (fun (s:string) -> Array.toList (s.Split([|'\t'|]))) lines;
let imdb = List.map (fun (l1::l2::l3::l4::l5::l6::[]) -> [l1]::[l2]::[l3]:: (Array.toList ((l4:string).Split[|','|])) ::[l5]:: (Array.toList ((l6:string).Split[|','|])) ::[]) lines2;;

//1. [5p] The imdb identifier points to the movie data. What is the structure/type of this data (ex: list)?
    //String list list list. it is a list, of lists of lists of strings. eg ["Crime"; " Drama"] is a list of strings from imdb, and this is stored in a list of attributes of a movie. these movie attributes are in a list of movies.

//2. [5p] Here's a recursive function that traverses imdb and finds the rating of a movie. However, there's a small
//   error in the code. Fix it. (HINT: think about types: what is the function supposed to return? What is the type of 'title'? What is the type of 'c'?)
//   [5p] Explain why you are getting the "Incomplete pattern match warning". Can you fix it?
       //because there are cases not addressed by the pattern matching. any of those letters may be either a string, or a list of strings, so making them wildcards solves this. titles and ratings are always just one string for all movies.
let rec get_rating movie = function
    | [] -> "Not found";
    | [[title]; _; [c]; _; _; _]::t -> if title=movie then c else get_rating movie t;

get_rating "Batman Begins" imdb;

//3. [5p] Rewrite the function above to use match instead of function. (HINT: how many parameters does get_rating take? What are they called?)
let rec get_rating (movie,imdb) = 
    match imdb with
    | [] -> "Not found"
    | [[title]; _; [c]; _; _; _]::xs -> if title = movie then c else get_rating (movie,xs);

get_rating ("Batman Begins",imdb);

//4. [5p] Let's rewrite the function above with a different pattern matching. Implement the function (???) so that it works, 
//       without changing anything of the given code. (HINT: you may use List.?? functions or 'match to' expressions)
let rec get_rating_v2 movie = function
    | [] -> "Not found";
    | [a; b; c; d; e; f]::t -> ???;

//5. [5p] Same as four 4.
let rec get_rating_v2 movie = function
    | [] -> "Not found";
    | h::t -> ???;


//2. Here's a recursive function that keeps only data of movies newer than which_year
let rec year_filter which_year = function
        | [] -> []
        | [a;[year];c;d;e;f]::t -> if year>which_year then [a;[year];c;d;e;f]::(year_filter which_year t) else (year_filter which_year t)
        | h :: t -> (year_filter which_year t);

// a. [5p] The line below has a type error. Fix it (don't touch the function above). 
let new_movies = year_filter 2000 imdb;

// b. [5p] Rewrite the function using 'match to' expressions rather than the 'function' form.



// 3. [10p]. Use the year_filter function as a model and write a rating_filter function. You will need the stringToFloat function.

let stringToFloat (s:string) = float s;

let rating_filter which_rating = function 
        | ?
        | ?
        | ?;

rating_filter 9. imdb;

// 4. [15p] Write a function 'allMovies actor' which lists all movies that the actor acted in.

// 5. [15p] Write a function 'commonMovies actor1 actor2' which lists all movies that two actors have been together in. 

// 6. [20p] Write a function 'preferredGenre actor' which lists the most prevalent genre of the movies that the actor has been in.



