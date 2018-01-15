/* ==============================
 *  Extra credit Assignment
 * ==============================
 * Author: Adam Andrade
 * Date: 3/19/13
 * Due: 3/19/13 11AM
 * I certify that this work  was created by only the Author listed above.
 * 
 * _______________________________________________________________
 * Purpose: to create 2 methods that perform a merge sort without
 * loops and implements recursion
 */

//main merge sort method
public static <T extends Comparable<T>> List<T> mergeSort(List<T> list) {
    //base case, if the list one or fewer elements
    if (list.size() <= 1) {
        return list;// return the sorted list
    }
    //half of the list's size to divide it into 2
    int middle = list.size() / 2;
    //defines the left half of the list
    List<T> leftList = mergeSort(list.subList(0, middle));
    //defines the right side
    List<T> rightList = mergeSort(list.subList(middle, list.size()));
    //creates a sorted list as a result of the merge method
    List<T> sortedList = (merge(new ArrayList(), leftList, rightList));
    return sortedList;//returns the sorted list
}//end mergesort

//method to sort and merge two arrays
private static <T extends Comparable<T>> List<T> merge(List<T> list, List<T> left, List<T> right) {
    //if one of the lists are empty, check which one is not empty,
    //add the next element from it and recursive call merge method, or
    //if both are empty, return the sorted list
    if (left.size() < 1 || right.size() < 1) {
        if (right.size() >= 1) {
            list.add(right.get(0));
            merge(list, left, right.subList(1, right.size()));
        }//end checking right half
        if (left.size() >= 1) {
            list.add(left.get(0));
            merge(list, left.subList(1, left.size()), right);
        }//end checking left half
        return list;
    }//end if
    //if the first element of the left half is smaller
    if (left.get(0).compareTo(right.get(0)) < 0) {
        //add it to the sorted list and recursively call
        //the merge method, but get a sublist of the left
        //half with one fewer elements
        list.add(left.get(0));        
        merge(list, left.subList(1, left.size()), right);            
    }//end if
    else {
        //otherwise, the first element of the right half is
        //smaller, thus add it and repeat with one fewer elements
        list.add(right.get(0));           
        merge(list, left, right.subList(1, right.size()));            
    }//end else
    return list;// return the sorted lis
}//end merge