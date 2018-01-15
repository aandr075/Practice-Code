// Homework 2. To be solved individually and submitted on Moodle.

//1. Let's do some pattern matching
//a. [2p] What does 'getThird1 list1' return?
// 3
let list1 = [1; 2; 3; 4; 5]
let getThird1 list = List.head (List.tail (List.tail list));
getThird1 list1;

//b. [2p] Fill in the '??' so the function has the same functionality as before
//   [2p] What happens if the list doesn't have exactly five elements? A match failure exception
let getThird2 = function
    | [_;_;a;_;_] -> a;
getThird2 list1;

//c. [2p] Fill in the ?? so the function works the same as before. 
//   [2p] What is the value of t if the function is called with list1? [ 4; 5]
//   [2p] What are the benefits of this function over getThird2? works with lists of 3 or more elements
let getThird3 = function 
    | _::_::l3::t -> l3;
getThird3 list1;

//d. [2p] Replace the ?? in the same way you did before. However, there's an error in how this function is
//   written. You will notice if trying to call 'getThird4 list1'. Fix the error. (HINT: how many arguments does getThird4 take? Why?)
let getThird4 list = 
    match (list) with
    | _::_::l3::t -> l3;

//e. This function replaces the third element in the list with x. However, there's a syntax error in the code.
//   [2p] Fix the error using the function constructs
//   [2p] Fix the error using the match construct
//   [2p] Write a test case
let replaceThird1 x = function
    | l1::l2::l3::t -> l1::l2::x::t;

let replaceThird2 x list = 
    match (list) with
    | l1::l2::l3::t -> l1::l2::x::t;

let listTestFunction = [ 1; 2; 3; 4; 5; 6]
replaceThird1 7 listTestFunction;

let listTestMatch = [ 7; 4; 6; 3; 2; 9]
replaceThird2 7 listTestMatch;

//f. [2p] Write a function that reverses a list of three elements; write a test case

let reverseListThree list  = 
    if (List.length(list) = 3) then List.rev list else list;

let listTestReverse = [ 3; 6; 1]
reverseListThree listTestReverse;


//g. [2p] Use the first function as a model to fill in the next ones.
//      (HINT: play with [x;y;z;...] vs. x::y::z)
let isEmpty = function
    | [] -> true
    | _ -> false;

let hasOne = function
    | _::x -> isEmpty(x) 
    | _ -> false;  


let hasMoreThanOne = function
    | _::x ->  isEmpty(x) = false 
    | _ -> false;

let hasTwo = function
    | _::_::x ->  isEmpty(x)   
    | _ -> false;

let hasMoreThanTwo = function
    | _::_::x -> isEmpty(x) = false 
    | _ -> false;





//now on to more complex lists
let list2 = [[1;2]; [3;4]; [5;6]];


//h. [2p] Syntax error, fix it.
//   [2p] Rewrite with match
let pickSecond = function
    | _::s::t -> s;

let pickSecondMatch list = 
    match list with 
    | _::s::t -> s;

//h. [2p] Check the function below. Then write a function that reverses all the sublists in list2 (results would be [[2;1]; [4;3]; [6;5])
//   [2p] Write a function that reverses both the list and the sublist
let pickSecondSecond1 = function
    | _::[first;second]::_ -> second;
pickSecondSecond1 list2;

let rec reverseSubs = function
    | [] -> []
    | x::xs -> (List.rev x)::reverseSubs(xs);

reverseSubs list2;

let reverseListAndSubs = function
    | x -> List.rev(reverseSubs(x))

reverseListAndSubs list2;

//i. [2p] Using the same idea from 1g, fill in the following functions:
let firstSublistNotEmpty list = 
    match list with
    | x::t -> isEmpty(x) = false 
    | _ -> false;

let secondSublistNotEmpty list = 
    match list with
    | _::x::t -> isEmpty(x) = false
    | _ -> false;

let thirdSublistNotEmpty list = 
    match list with
    | _::_::x::t -> isEmpty(x) = false
    | _ -> false;

let secondSublistHasOne list = 
    match list with
    | _::x::t -> hasOne x
    | _ -> false;

let secondSublistHasMoreThanOne list = 
    match list with
    | _::x::t -> hasMoreThanOne x
    | _ -> false;


firstSublistNotEmpty list2;
secondSublistNotEmpty list2;
thirdSublistNotEmpty list2;
secondSublistHasOne list2;
secondSublistHasMoreThanOne list2;

//j. [2p] Wrap you head around this version of pickSecondSecond. What is different from pickSecondSecond1? Find an input that will yield different results.
    //second sublist can be a list of more than 2 entries, where in pickSecondSecond1 only took lists of two items.
let list2 = [ [ 1; 2]; [ 3; 4; 5]; [3; 7] ];

let pickSecondSecond2 = function
    | _::(first::second::t)::_ -> second;
pickSecondSecond2 list2;

//k. [2p] The function below does some error handling to address the case when there isn't a second list element in the main list.
//       Write a function that also addresses the case when there isn't a second element in the second sublist (ex: [[1;2]; []; [5;6]])
//       (HINT1: touch 'second' and nothing else)
//       (HINT2: you can use pattern matching on the left side of -> to decompose an input, and use :: on the right side to recompose it).
let pickSecondSecond3 = function
    | [] -> -1;
    | [one] -> -1;
    | _::(first::second::t)::_ -> second;

let pickSecondSecond3Revised = function
    | [] -> -1;
    | [one] -> -1;
    | _::[]::_ -> -1;
    | _::(first::second::t)::_ -> second;

pickSecondSecond3 [[1;2]; []; [5;6]];
pickSecondSecond3Revised [[1;2]; []; [5;6]];



//l. [5p] Fill in the function and write a few test cases. 
//   [5p] Can you write the error cases? If which1 or which2 is 0 the function should return -1. If a requested element doesn't exist, the function returns -1.
//   (don't worry about which1 or which2 being longer than the lists....
//   (HINT: use wildcards _ to not write so much).
let rec pick which1 which2 list = 
    match (which1, which2, list) with 
    | (_,_,[]) -> -1
    | (0,_,_) -> -1
    | (_,0,_) -> -1
    | (1,1, (first::second::t)::x) -> first
    | (1,2, (first::second::t)::x) -> second
    | (1,z, (first::second::t)::x) -> pick 1 (z-1) ((second::t)::x) 
    | (y,1, (first::second::t)::x) -> pick (y-1) 1 x ;


pick 3 1  list2;

////2.  Now lets move on to recursive operations on lists.

//a. [2p] Check the example below. Create a function add n list  that adds n to each element of the list;
let rec addOne = function
    | [] -> [];
    | h::t -> (h+1)::(addOne t);
addOne [1;2;3;4];

let rec add n = function
    | [] -> [];
    | h::t -> (h+n)::(add n t);
add 5 [1;2;3;4];

//b. [2p] Fill in the function below
let rec filterOdd = function
    | [] -> [];
    | h::t -> if List.length(t)%2 <> 0 then filterOdd(t) else h::filterOdd(t);

filterOdd [1;2;3;4;5;6]; 

//c. [2p] Write a function that takes a list of lists and filters out empty lists. Don't use IF expressions, use pattern matching instead!
//   ex:  filterEmpty [[1;2]; []; []; [1]]; would result in [[1;2]; [1]]; 

let rec filterEmpty = function
    | [] -> [];
    | []::x -> filterEmpty(x);
    | y::x -> y::filterEmpty(x);
filterEmpty [[1;2]; []; []; [1]];

//d. Remember that you can pass in functions as arguments?
//   [2p] Complete the general filter function below. It takes as parameter a filtering function that returns a boolean and a list. 
//   [2p] Write an 'isOdd' function for ints, and an 'isEmpty' function for lists. Use them in conjunction with 'filter' to recreate
//        the filterOdd and filterEmpty functions
//        (HINT: let filterOdd = filter isOdd ...)

let rec filter how  = function
    | [] ->  [];
    | h::t -> if how h then h::filter how t else filter how t;

let isOdd x = x%2 <> 0; 
let isEmpty = function
    | [] -> true
    | _ -> false;

filter isOdd [1 ; 6 ; 4; 7; 2];
filter isEmpty [[1;2]; []; []; [1]];
    
//e. Remember 'add n list' from 2a?
//   [2p] Create 'map func list' which applies the function func to each element of the list
//   [2p] Rewrite add x list using map (just as you did with filter).

let rec map func = function
    | [] -> [];
    | x::xs -> func x :: map func xs;

let add x list = map (fun n -> n+x) list;

list1;
add 2 list1;

//NOTE that filter and map are preimplemented List functions in F#. You can type List. to see all the functions of lists.

//f. [2p] What does the following function do? // takes a list of lists and removes the first element in the first sublist or removes the sublist if there are 1 or no elements
//   [2p] The function is missing a patterns (it doesn't address one case). Find an example for which it breaks, then fix the code to address the case. //empty sublists
let rec blah1 = function
    | [] -> [];
    | ([])::c -> c;
    | (a::b)::c -> b::(blah1 c);

//g. [2p] What does the following function do? Give a few examples of inputs and outputs. //it reverses a list of sublists, and moves the heads of each sublist to the end.
    // example: [[1; 2]; [3; 4; 5]; [3; 7]] -> [[7; 3]; [4; 5; 3]; [2; 1]]
//   [2p] There's a small 'not defined error'. Why? Fix it. //it's not recursive
//   [2p] What error do you get if you remove the [] around a and around (b@a). Why? //type mismatch, because a is one element, not a list, and you can't append single elements, b can be a single element.
let rec blah2 = function
    | [] -> []
    | (a::b)::c -> (blah2 c) @ [(b @ [a])];



//h. [2p] Write a function that removes all odd numbers from a list of list of ints. Use List.filter.
//   [2p] Same, but don't use List.filter. (i.e., do it manually, using recursive calls).

let rec filterOddSubs1 = function
    |[] -> []
    |(x)::c -> (List.filter(fun y -> y%2 <> 0) x)::filterOddSubs1 c 


let rec filter how  = function
    | [] ->  [];
    | h::t -> if how h then h::filter how t else filter how t;

let isOdd x = x%2 <> 0; 

let rec filterOddSubs2 = function
    | [] -> []
    | x::xs -> filter isOdd x :: filterOddSubs2 xs; 

filterOddSubs2 list2;;

//i. [10p] Write a function that takes the first two elements of each of list's sublists and creates a list from them. 
//   If a sublist has only one element, then take that element. If a sublist has no elements, ignore it.
//   examples: firstTwos [[1;2;3];[4;5;6]] = [1;2;4;5]
//             firstTwos [[1]; []; [2;3]; [4;5;6]] = [1;2;3;4;5];

let rec firstTwos = function
    | [] -> [];
    | ([])::c -> (firstTwos c);
    | (a::[])::c -> a::(firstTwos c);
    | (a::b)::c -> a::List.head(b)::(firstTwos c);

firstTwos [[1;2;3];[4;5;6]]
firstTwos [[1]; []; [2;3]; [4;5;6]]







