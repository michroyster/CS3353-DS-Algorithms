import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/*
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
*/

class Main {
    public static void main(String[] args){

        // Initializing an empty tree
        AVLTree<Integer, Process> tree = new AVLTree<Integer, Process>();

        // Reading lines from file and inserting them all into the tree.
        insertFromFile(tree, "processesInformation.txt");

        // Iterating through the loaded tree
        while (tree.getRoot() != null)
        {
            AVLTree.Node<Integer,Process> executed = tree.removeMin();
            executed.getValue().printInfo();
            executed.getValue().decrementBursttime();
            if (executed.getValue().getBurstTime() != 0)
            {
                executed.getValue().incrementRuntime();
                executed.setKey(executed.getValue().getVirtualRunTime());
                tree.insert(executed.getKey(), executed.getValue());
            }
        }
       
    }

    // Loads the file into the tree
    public static void insertFromFile(AVLTree<Integer, Process> tree, String filename)
    {
        File file = new File(filename);
        try
        {
            Scanner fileReader = new Scanner(file);
            fileReader.nextLine();

            while (fileReader.hasNextLine())
            {
                String[] parameters = fileReader.nextLine().split("\t");
                Process process = new Process(parameters);
                tree.insert(process.getVirtualRunTime(), process);
            }

            fileReader.close();
        }
        catch (IOException ioe)
        {
            System.out.println("error reading file, it must be named processesInformation.txt");
        }
    }
}