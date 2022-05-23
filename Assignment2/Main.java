import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/*
    Author Name: Michael Royster
    Email: micaher@okstate.edu
    Date: 10/02/2020
    Program Description:
    This program implements a data structure for an office where customers arrive
    to the office for various purposes. These customers are read from the input file
    myfile.txt. The data structure ArrayQueue is is a queue, except for when a new
    customer is enqueued, it is put in the numerically sorted position based off
    of the customer ID.

*/

class Main{
    public static void main(String[] args)
    {
        // Declaring Queue
        ArrayQueue officeQueue = new ArrayQueue(10);

        // Variables for deciding which expansion strategy
        boolean growthStrategy = false;
        boolean tightStrategy = false;

        File file = new File("myfile.txt");

        try
        {
            Scanner fileReader = new Scanner(file);
            fileReader.nextLine();
            
            Scanner scanner = new Scanner(System.in);
            String commandString = "";
            int commandNumber = 0;
            
            // Main loop taking input via scanner
            while (true)
            {
                try
                {
                    // Print menu and get the user's input, make sure it is a number befor converting to integer
                    printMenu();
                    commandString = scanner.nextLine();
                    commandString.toCharArray();
                    if (Character.isDigit(commandString.toCharArray()[0]))
                        commandNumber = Integer.parseInt(commandString);
                    else
                        commandNumber = -1;
                    
                    switch (commandNumber){
                        // Enqueue the next customer in the file
                        case 1:
                            if (fileReader.hasNextLine())
                            {
                                System.out.println("Enqueued: ");
                                String[] parameters = fileReader.nextLine().split("\t");
                                ArrayQueue.Customer customer = officeQueue.createCustomer(parameters);

                                officeQueue.enqueue(customer);
                                customer.printDetails();

                                // If the queue is full and the user has not chosen a resize strategy, choose now
                                if (officeQueue.isFull())
                                {
                                    if (growthStrategy == tightStrategy)
                                    {
                                        while (!growthStrategy && !tightStrategy)
                                        {
                                            System.out.println("Queue is full, resize using Growth or Tight strategy? (g/t)");
                                            String growth = scanner.nextLine();
                                            switch (growth){
                                                case "g":
                                                    growthStrategy = true;
                                                    break;
                                                case "t":
                                                    tightStrategy = true;
                                                    break;
                                                default:
                                                    System.out.println("Please enter g for growth or t for tight.");
                                                    break;
                                            }
                                        }
                                    }

                                    // Resize the queue based on the chosen strategy
                                    if (growthStrategy == true)
                                        officeQueue.growthResize();
                                    else
                                        officeQueue.tightResize();
                                }

                                // Continue enqueing customers as long as the user wants, then return to main menu
                                boolean enqueuing = true;
                                while (enqueuing)
                                {
                                    System.out.println("\nDo you want to enqueue more customers? (y/n)");
                                    String more = scanner.nextLine();
                                    switch(more){
                                        case "y":
                                            if (fileReader.hasNextLine())
                                            {
                                                    System.out.println("Enqueued: ");
                                                    parameters = fileReader.nextLine().split("\t");
                                                    customer = officeQueue.createCustomer(parameters);
                                                    
                                                    officeQueue.enqueue(customer);
                                                    customer.printDetails();

                                                    // If the queue is full and the user has not chosen a resize strategy, choose now
                                                    if (officeQueue.isFull())
                                                    {
                                                        if (growthStrategy == tightStrategy)
                                                        {
                                                            while (!growthStrategy && !tightStrategy)
                                                            {
                                                                System.out.println("Queue is full, resize using Growth or Tight strategy? (g/t)");
                                                                String growth = scanner.nextLine();
                                                                switch (growth){
                                                                    case "g":
                                                                        growthStrategy = true;
                                                                        break;
                                                                    case "t":
                                                                        tightStrategy = true;
                                                                        break;
                                                                    default:
                                                                        System.out.println("Please enter g for growth or t for tight.");
                                                                        break;
                                                                }
                                                            }
                                                        }
                                                        // Resize the queue based on the chosen strategy
                                                        if (growthStrategy == true)
                                                            officeQueue.growthResize();
                                                        else
                                                            officeQueue.tightResize();
                                                    }
                                            } 
                                            else
                                            {
                                                System.out.println("End of file");
                                            }
                                            break;
                                        case "n":
                                            enqueuing = false;
                                            break;
                                        default:
                                            System.out.println("Please enter y or n.");
                                            break;
                                    }
                                }
                            }
                            else
                            {
                                System.out.println("End of file");
                            }
                            break;
                        
                        // Dequeue customers from the queue
                        case 2:
                            System.out.println("Dequeueing: ");
                            officeQueue.dequeue().printDetails();

                            // Continue dequeing as long as the user wants, then return to the main menu
                            boolean dequeuing = true;
                            while (dequeuing)
                            {
                                System.out.println("\nDo you want to dequeue more customers? (y/n)");
                                String more = scanner.nextLine();

                                switch(more){
                                    case "y":
                                        System.out.println("Dequeueing: ");
                                        officeQueue.dequeue().printDetails();
                                        break;
                                    case "n":
                                        dequeuing = false;
                                        break;
                                    default:
                                        System.out.println("Please enter y or n.");
                                        break;
                                }
                            }
                            break;

                        // Initialize the queue
                        case 3:
                            officeQueue.initialize();
                            System.out.println("Initialized queue: ");
                            officeQueue.printList();
                            break;

                        // Print the first element in the queue
                        case 4:
                            if (officeQueue.first() != null)
                            {
                                System.out.println("First in the queue is: ");
                                officeQueue.first().printDetails();
                            }
                            {
                                System.out.println("The queue is empty");
                            }
                            break;

                        // Print the size of the queue
                        case 5:
                            System.out.println("The size of the queue is: " + officeQueue.size());
                            break;

                        // Check if the queue is empty
                        case 6:
                            if (officeQueue.isEmpty())
                                System.out.println("\nThe office queue is empty.");
                            else
                                System.out.println("\nThe office queue is NOT empty");
                            break;

                        // Check if the queue is full
                        case 7:
                            if (officeQueue.isFull())
                                System.out.println("\nThe office queue is full.");
                            else
                                System.out.println("\nThe office queue is NOT full.");
                            break;

                        // Print the queue in order
                        // Also, print a visual representation of the queue and which position the customers
                        // are physically in the array by index
                        case 8:
                            System.out.println("Printing list: ");
                            officeQueue.printList();
                            break;

                        // Print the capacity of the queue
                        case 9:
                            System.out.println("the capacity is: " + officeQueue.getCapacity());
                            break;

                        // Exit the program
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
                // Catch any overflow or underflow exceptions
                catch(OverflowException oe)
                {
                    System.out.println(oe.getMessage());
                }
                catch(UnderflowException ue)
                {
                    System.out.println(ue.getMessage());
                }
            }
        }
        // Catch any file error exception
        catch(IOException ioe)
        {
            System.out.println("File error!");
        }
    }

    // Prints the main menu options
    private static void printMenu() {
        System.out.println("System Menu:");
        System.out.println("1. Enqueue");
        System.out.println("2. Dequeue");
        System.out.println("3. Initialize");
        System.out.println("4. First");
        System.out.println("5. Size");
        System.out.println("6. Is Empty");
        System.out.println("7. Is Full");
        System.out.println("8. Print the queue");
        System.out.println("9. Capacity");
        System.out.println("0. Exit program");
    }
}