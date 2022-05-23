Author Name: Michael Royster
Email: micaher@okstate.edu
Date: 10/22/2020

Program Description: 
This program is implementing a priority queue using a heap ADT. The program keeps
track of all processes running on computer (loaded from text file) and stores them
in the priority queue. The max priority process will always be on top of the queue.
The heap ADT uses an array with initial size of 20 and uses a tight growth strategy
to increase size by an additional 20 when full.

The original processesInformation.txt file was copied and pasted into Excel then back into text file.
It is still in the folder called processesInformation-Original.txt
The copy/pasted file is now called processesInformation.txt.

Tested and runs on csx machine: 
Compile with: javac *.java
Run with: java Main

NOTE: The compiler on csx gives the following warning, but the program
      still compiles and runs just fine.

Note: Some input files use unchecked or unsafe operations.
Note: Recompile with -Xlint:unchecked for details.
[micaher@csx8 Assignment03]$ java Main

Driver class:
Main.java