import java.io.File;
import java.io.IOException;
import java.util.Scanner;

class Main{

    /*
        Author Name: Michael Royster
        Email: micaher@okstate.edu
        Date: 10/22/2020
        Program Description: 
        This program is implementing a priority queue using a heap ADT. The program keeps
        track of all processes running on computer (loaded from text file) and stores them
        in the priority queue. The max priority process will always be on top of the queue.
        The heap ADT uses an array with initial size of 20 and uses a tight growth strategy
        to increase size by an additional 20 when full.
    */

    public static void main(String[] args)
    {
        HeapPriorityQueue<Integer, Process> heap = new HeapPriorityQueue<Integer, Process>(20);

        File file = new File("processesInformation.txt");

        try{
           Scanner fileReader = new Scanner(file);
           fileReader.nextLine();
           
           Scanner scanner = new Scanner(System.in);
           String commandString = "";
           int commandNumber = 0;

           // Main loop for the driver program
           // each option from the menu calls methods of the HeapPriorityQueue class
           while(true)
           {
                printMenu();
                commandString = scanner.nextLine();
                commandString.toCharArray();
                if (Character.isDigit(commandString.toCharArray()[0]))
                    commandNumber = Integer.parseInt(commandString);
                else
                    commandNumber = -1;

                switch (commandNumber){
                    case 1:
                        if (fileReader.hasNextLine())
                        {
                            System.out.println("inserting");
                            String[] parameters = fileReader.nextLine().split("\t");
                            Process process = new Process(parameters);

                            if (heap.isFull())
                                heap.tightResize();

                            heap.insert(process.getPriority(), process);

                            boolean inserting = true;
                            while (inserting)
                            {
                                System.out.println("\nDo you want to insert more processes? (y/n)");
                                String more = scanner.nextLine();
                                switch(more){
                                    case "y":
                                        if (fileReader.hasNextLine())
                                        {
                                            System.out.println("inserted.");
                                            parameters = fileReader.nextLine().split("\t");
                                            process = new Process(parameters);

                                            if (heap.isFull())
                                                heap.tightResize();

                                            heap.insert(process.getPriority(), process);
                                        }
                                        break;
                                    case "n":
                                        inserting = false;
                                        break;
                                    default:
                                        System.out.println("Please enter y or n");
                                }
                            }
                        }
                        else
                        {
                            System.out.println("End of file!");
                        }
                        break;
                    case 2:
                        if (heap.isEmpty())
                        {
                            System.out.println("The heap is empty");
                        }
                        else
                        {
                            System.out.println("\nThe max node is: ");
                            heap.max().getValue().printValue();
                        }
                        break;
                    case 3:
                        if(heap.isEmpty())
                        {
                            System.out.println("The heap is empty");
                        }
                        else
                        {
                            System.out.println("Max node removed:");
                            heap.removeMax().getValue().printValue();
                        }
                        break;
                    case 4:
                        if (heap.isEmpty())
                            System.out.println("Heap is empty.");
                        else
                        {
                            System.out.println("Enter the new priority for the max: ");
                            String inputString = scanner.nextLine();
                            int input = Integer.parseInt(inputString);
                            heap.update(heap.max().getKey(), input);
                            System.out.println("Priority updated");

                            boolean updating = true;
                            while(updating)
                            {
                                System.out.println("Would you like to continue updating? (y/n)");
                                inputString = scanner.nextLine();
                                if (inputString.equals("y"))
                                {
                                    System.out.println("Enter the new priority for the max: ");
                                    inputString = scanner.nextLine();
                                    input = Integer.parseInt(inputString);
                                    heap.update(heap.max().getKey(), input);
                                    System.out.println("Priority updated");
                                }
                                else if (inputString.equals("n"))
                                {
                                    updating = false;
                                }
                                else
                                {
                                    System.out.println("Please enter y or n.");
                                }
                            }
                            
                        }
                        break;
                    case 5:
                        if (heap.isEmpty())
                            System.out.println("\nHeap is empty.");
                        else
                            System.out.println("\nHeap is NOT empty.");
                        break;
                    case 6:
                        if (heap.isFull())
                            System.out.println("\nHeap is full");
                        else
                            System.out.println("\nHeap is NOT full");
                        break;
                    case 7:
                        heap.printHeap();
                        break;
                    case 0:
                        fileReader.close();
                        scanner.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Please enter a valid menu option");
                        break;
                        
                }
           }

        }catch (IOException ioe){
            System.out.println("File error!");
        }
    }

    // Print the menu
    public static void printMenu()
    {
        System.out.println("1. insert");
        System.out.println("2. max");
        System.out.println("3. removeMax");
        System.out.println("4. update");
        System.out.println("5. isEmpty");
        System.out.println("6. isFull");
        System.out.println("7. printHeap");
        System.out.println("0. Exit");
    }
}