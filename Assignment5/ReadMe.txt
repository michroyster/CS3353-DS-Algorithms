Author Name: Michael Royster
Email: micaher@okstate.edu
Date: 11-19-2020
Program Description:
This program reads Processes from a test file and inserts them into an 
AVL Tree. Once all items have been inserted the driver program will
repeatedly:
    1. remove minimum key-value
    2. decrease burst time unitl it reaches zero
    3. increase virtual run time
    4. re-insert into the AVL Tree as long as burst time is greater than 0

Tested and runs on CSX machine:
Compile with: javac *.java
Run with: java Main

Two classes outside of Main driver class:
    1. AVLTree - self-balancing AVLTree
    2. Process - for storing process information

Notes:
The input file has an extra tab in between the second and fourth column
to handle this my code parses the lines into an array with four members.
The Process class is instantiated with parameters in positions 0, 1, and 3
for this reason. If you change the input file, you must also make sure that
the columns are exactly the same.

