/*
 * ==============================
 *  Assignment 5
 * ==============================
 * Author: Adam Andrade
 * Date: 4/3/13
 * Due: 4/4/13 11AM
 * I certify that this work is built upon Bill Kraynek's Polynomial.java and was modified by only the Author listed 
 * above.
 * 
 * _______________________________________________________________
 * Purpose: to create a Polynomial class that stores polynomials as a linked
 * list of polynomials stored as terms inside of nodes and to provide methods
 *  to add and multiply polynomials recursively
 */

import javax.swing.JOptionPane;

/**
 * @author Bill Kraynek
 * @modifier Adam Andrade
 */
//the Polynomial class holds a polynomial value
public class Polynomial {

    private Node next;
    /*==================================================
     *                constructors
     ===================================================*/
    //no paramaters

    public Polynomial() {
        //create a Polynomial 0x^0
        this(0, 0);
    }
    //takes in a coeffecient and exponent

    public Polynomial(int coef, int expon) {
        //creates a polynomial coef*x^expon
        this.next = new Node(new Term(coef, expon), null);
    }
    //takes a node as a parameter

    private Polynomial(Node next) {
        //the node is set to the node parameter
        this.next = next;
    }
    //end constructors

    /*==================================================
     *          Main Method
     ===================================================*/
    //written by Bill Kraynek
    public static void main(String[] args) {

        Polynomial p1 = new Polynomial(5, 2);
        Polynomial p2 = new Polynomial(3, 8);
        Polynomial p0 = new Polynomial();
        Polynomial p11 = new Polynomial(4, 5);
        p1 = p1.polyAdd(p11);
        p11 = new Polynomial(3, 3);
        p1 = p1.polyAdd(p11);
        p11 = new Polynomial(1, 2);
        p1 = p1.polyAdd(p11);
        p11 = new Polynomial(5, 6);
        p1 = p1.polyAdd(p11);
        p11 = new Polynomial(2, 5);
        p2 = p2.polyAdd(p11);
        p11 = new Polynomial(1, 3);
        p2 = p2.polyAdd(p11);
        Polynomial p3 = p1.polyAdd(p2);
        JOptionPane.showMessageDialog(null, "p1 is " + p1 + "\np2 is " + p2 + "\np1+p2 is " + p3);
        Polynomial p4 = p1.polyMultiply(p2);
        JOptionPane.showMessageDialog(null, "p1 is " + p1 + "\np2 is " + p2 + "\np1*p2 is " + p4);
        Polynomial p5 = p2.polyMultiply(p2);
        JOptionPane.showMessageDialog(null, "p2 is " + p2 + "\np2*p2 is " + p5);
        Polynomial p6 = p0.polyMultiply(p2);
        JOptionPane.showMessageDialog(null, "p0 is " + p0 + "\n" + "p2 is " + p2 + "\np0*p2 is " + p6);
        Polynomial p7 = p0.polyAdd(p2);
        JOptionPane.showMessageDialog(null, "p0 is " + p0 + "\n" + "p2 is " + p2 + "\np0+p2 is " + p7);
        p1 = p1.polyAdd(p2);
        JOptionPane.showMessageDialog(null, "After p1 = p1+p2  p1 is " + p1);
        p2 = p2.polyMultiply(p2);
        JOptionPane.showMessageDialog(null, "After p2 = p2*p2  p2 is " + p2);
    }//end main

    /*==================================================
     *                      Methods
     ===================================================*/
    //this method takes a polynomial and multiplies it by the
    //polynomial calling the method
    public Polynomial polyMultiply(Polynomial poly) {
        //creates a duplicate polynomial
        Polynomial p = this.polyCopy();
        //base case is the polynomial is empty
        //return one that is 0x^0
        if (poly.next == null) {
            return new Polynomial();
        }
        //recursive call n-1
        Polynomial p2 = p.polyMultiply(new Polynomial(poly.next.next));
        //helper method to multiply a term by "this" entire polynomial
        Polynomial p1 = p.polyMultHelper(poly);
        p1 = p1.polyAdd(p2);//adds the recursive call to helper call
        return p1;//returns a polynomial
    }//end polyMultiply

    //helper for multiplication, recursive call for each term of this polynomial
    private Polynomial polyMultHelper(Polynomial poly) {
        //creates a copy of this polynomial
        Polynomial p = this.polyCopy();
        //if this node is null, return the polynomial
        if (p.next == null) {
            return p;
        }
        //stores a created single term to recursively add to itself
        Polynomial temp = new Polynomial(p.next.data.coef * poly.next.data.coef,
                p.next.data.expon + poly.next.data.expon)
                .polyAdd(new Polynomial(p.next.next).polyMultHelper(poly));
        return temp;//return the terms
    }//end polyMultiply

    //adds 2 Polynomials together recursively
    public Polynomial polyAdd(Polynomial poly) {
        //creates a duplicate of this polynomial
        Polynomial p = this.polyCopy();
        //if either polynomial has run out of terms
        if (poly.next == null || p.next == null) {
            //if only this polynomial has run out of terms
            if (poly.next != null) {
                p.next = poly.next;//add the rest of the other polynomial
            }
            return p;//return this polynomial
        }
        //if the term's exponents are equal
        if (poly.next.data.expon == p.next.data.expon) {
            //add coefficients and move to the next term for both polynomials
            p.next.data.coef += poly.next.data.coef;
            p.next.next = new Polynomial(p.next.next).polyAdd(
                    new Polynomial(poly.next.next)).next;
        }
        //if poly's exponent is greater than this one's
        if (poly.next.data.expon > p.next.data.expon) {
            //leave this term, and recurse with the next one from this polynomial
            p.next.next = new Polynomial(p.next.next).polyAdd(poly).next;
        }
        //if it is smaller
        if (poly.next.data.expon < p.next.data.expon) {
            //insert the term here and move to the next term of poly
            p.next = new Node(poly.next.data, p.next);
            p.next.next = new Polynomial(p.next.next).polyAdd(new Polynomial(poly.next.next)).next;
        }
        return p;//return added Polynomials
    }//end polyAdd

    //these methods create a copy this polynomial recursively
    //copies the polynomial
    public Polynomial polyCopy() {
        //creates a new polynomial then copies the nodes
        Polynomial copy = new Polynomial();
        copy.next = polyNodeCopy(this.next);
        return copy;//returns duplicate polynomial
    }
    //copies the nodes
    private Node polyNodeCopy(Node p) {
        if (p == null) {
            return null;
        }//base case
        //copies each node recursively
        Node copy = new Node(new Term(p.data.coef, p.data.expon), null);
        copy.next = polyNodeCopy(p.next);
        return copy;//return the copy of linked nodes
    }//end polyCopy

    @Override //overridden toString method for a Polynomial
    public String toString() {
        //calls the polynomial's node's toString method
        if (this.next != null) {
            return this.next.toString();
        }
        return "null";//return null if node is null
    }//end toString
    /*==================================================
     *                      Inner Classes
     ===================================================*/
    //inner Term class, represents a term in the polynomial
    private static class Term {

        int coef;//polynomial's coefficient
        int expon;//polynomial's exponent
        //constructor
        public Term(int coef, int expon) {
            //takes in parameters and sets fields accordingly
            this.coef = coef;
            this.expon = expon;
        }
    }//end Term class
    //node inner class, used to make linked list
    private static class Node {

        Term data;//the actual polynomial
        Node next;//a link to the next polynomial
        //constructor

        public Node(Term data, Node node) {
            //sets fields based on parameters
            this.data = data;
            this.next = node;
        }
        @Override //node's overridden recursive toString method
        public String toString() {
            //recursively displays all terms of the polynomial
            return (this.data.coef != 0
                    ? (this.data.coef != 1 ? this.data.coef
                    : (this.data.expon != 0 ? "" : this.data.coef))
                    + (this.data.expon != 0 ? "x"
                    + (this.data.expon != 1 ? exponentFixer(this.data.expon) : "")
                    : "") + (this.next != null ? " + " : "")
                    : (this.next != null ? "" : "0" /*base case*/))
                    + (this.next != null ? next.toString() : "");
        }
        //makes exponential ints appear as superscript exponents
        private String exponentFixer(int expon) {
            //converts exponent into a string, then replaces chars
            String convert = String.valueOf(expon);
            convert = convert.replaceAll("0", "\u2070");
            convert = convert.replaceAll("1", "\u00B9");
            convert = convert.replaceAll("2", "\u00B2");
            convert = convert.replaceAll("3", "\u00B3");
            convert = convert.replaceAll("4", "\u2074");
            convert = convert.replaceAll("5", "\u2075");
            convert = convert.replaceAll("6", "\u2076");
            convert = convert.replaceAll("7", "\u2077");
            convert = convert.replaceAll("8", "\u2078");
            convert = convert.replaceAll("9", "\u2079");
            return convert;
        }
    }// end Node class
}
