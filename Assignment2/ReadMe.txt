Author Name: Michael Royster
Email: micaher@okstate.edu
Date: 10/02/2020
Program Description:
This program implements a data structure for an office where customers arrive
to the office for various purposes. These customers are read from the input file
myfile.txt. The data structure ArrayQueue is is a queue, except for when a new
customer is enqueued, it is put in the numerically sorted position based off
of the customer ID.

Tested and runs on csx machine: 
Compile with: javac *.java
Run with: java Main

Data Structure class:
ArrayQueue.java

Driver class:
Main.java

Exception Handling Classes:
OverflowException.java
UnderflowException.java

Runtime analysis is in the pdf file: Assignment2 RuntimeAnalysis

Notes:
The main menu options are as below: 

1. Enqueue -- adds customer to queue then shifts to correct numerical position based on customer ID
2. Dequeue -- removes first customer from queue
3. Initialize -- sets all queue positions with reference to null
4. First -- returns front customer in queue
5. Size -- returns size of queue (number of customers)
6. Is Empty -- returns true if queue is empty, false if not
7. Is Full -- returns true if queue is full, false if not
8. Print the queue -- This was not specified in the Assignment, but is useful for testing, represents queue in two ways:
                      1. Prints queue in order with all customer ID
                      2. Prints physical representation of array, by customer ID (the exact index customers are in)
9. Capacity -- Not specified in the Assignment, but is useful for testing: returns the capacity of the array
0. Exit program -- Terminates program with exit code 0

Important: When the array becomes full, 
           the driver program prompts the user for a response on either tight or growth strategy.
           Enter g for growth strategy -- calls the growthResize() method
           Enter t for tight strategy -- calls the tightResize() method
           Once chosen, this strategy will be used automatically until program is terminated.