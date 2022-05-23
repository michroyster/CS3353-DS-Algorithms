Author Name: Michael Royster
Email: micaher@okstate.edu
Date: 11/7/2020
Program Description:
This program implements two hash tables. One hash table uses a double hashing method
and the other uses a linear probing method. The driver program in Main allows the 
user to insert, find, and delete books from the double hashing table. The compare
option inserts, finds and deletes 1500 books from an instance of each hash table and
measures the time to perform the tasks.

Tested and runs on CSX machine:
Compile with: javac *.java
Run with: java Main

Two main classes for the assignment:

DoubleHashTable.java - uses open addresssing, double hash function

LinearHashTable.java - uses open addressing, linear probe function 

The compare() function is in the driver class Main.java. It loads all
files from the booksInfo.txt file into an array and then measures the
time performance of insertion, find, and deletion of 1500 objects using
System.currentTimeMillis().