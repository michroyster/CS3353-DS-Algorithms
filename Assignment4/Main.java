import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/* 
    Author Name: Michael Royster
    Email: micaher@okstate.edu
    Date: 11/7/2020
    Program Description:
    This program implements two hash tables. One hash table uses a double hashing method
    and the other uses a linear probing method. The driver program in Main allows the 
    user to insert, find, and delete books from the double hashing table. The compare
    option inserts, finds and deletes 1500 books from an instance of each hash table and
    measures the time to perform the tasks.
*/

class Main{
    public static void main(String[] args)
    {
        DoubleHashTable<Double, Book> dubHash = new DoubleHashTable<Double, Book>(13);

        File file = new File("booksInfo.txt");

        try
        {
            Scanner fileReader = new Scanner(file);
            fileReader.nextLine();

            Scanner scanner = new Scanner(System.in);
            String commandString = "";
            int commandNumber = 0;

        
            while (true)
            {
                printMenu();
                commandString = scanner.nextLine();
    
                if (Character.isDigit(commandString.toCharArray()[0]))
                {
                    commandNumber = Integer.parseInt(commandString);
                }
    
                switch (commandNumber)
                {
                    // INSERTING books into hash table
                    case 1:
                    String[] parameters = fileReader.nextLine().split("\t");
                    Book book = new Book(parameters);
                    dubHash.insert(book.getISBN13(), book);
                    System.out.println("Next book inserted into hash table.");
                    String more = "";

                    boolean inserting = true;

                    do{
                        System.out.println("Do you want to continue inserting? (y/n): ");
                        more = scanner.nextLine();
                        switch(more)
                        {
                            case "y":
                            parameters = fileReader.nextLine().split("\t");
                            book = new Book(parameters);
                            dubHash.insert(book.getISBN13(), book);
                            System.out.println("next book inserted..");
                            break;
                            case "n":
                            inserting = false;
                            break;
                            default:
                            System.out.println("Please enter (y/n): ");
                            break;
                        }
                    } while(inserting);
                    break;
                    
                    // FINDING books in hash table
                    case 2:
                    System.out.println("Enter the key of the book to find: ");
                    commandString = scanner.nextLine();
                    double key = Double.parseDouble(commandString);
                    if (key != 0)
                    {
                        Book foundBook = dubHash.find(key);
                        if (foundBook != null)
                            foundBook.printDetails();
                    }
                    break;
    
                    // DELETING books from hash table
                    case 3:
                    System.out.println("Enter the key of the book to delete: ");
                    commandString = scanner.nextLine();
                    double deleteKey = Double.parseDouble(commandString);
                    if (deleteKey != 0)
                    {
                        Book foundBook = dubHash.delete(deleteKey);
                        if (foundBook != null)
                        {
                            foundBook.printDetails();
                            System.out.println("book deleted");
                        }
                    }
                    break;
    
                    // Compares performance of linear probe and double hash tables
                    case 4:
                    System.out.println("Comparison of time performance for linear probe vs double hash:");
                    compare();
                    break;
    
                    // print table for testing
                    case 5:
                    dubHash.printTable();
                    break;

                    // get size for testing
                    case 6:
                    System.out.println("Size: " + dubHash.getSize());
                    break;

                    case 9:
                    fileReader.close();
                    scanner.close();
                    System.exit(0);
                    default:
                    System.out.println("Please enter a valid command");
                    break;
                }
            }
        }catch(IOException ioe){
            System.out.println("file error!");
        }
    }

    // Compares the performance of linear probe vs double hash methods with 1500 books
    public static void compare()
    {
        Book[] library = new Book[10414];
        loadFiles("booksInfo.txt", library);

        DoubleHashTable<Double, Book> dubHash = new DoubleHashTable<Double, Book>(31);
        LinearHashTable<Double, Book> linearHash = new LinearHashTable<Double, Book>(31);

        // Linear Hash works
        compareLinear(linearHash, library);
        System.out.println("Size: " + linearHash.getSize());

        // Double Hash works
        compareDoubleHash(dubHash, library);
        System.out.println("Size: " + dubHash.getSize());
    }

    // Loads all the books into a fixed array
    public static void loadFiles(String f, Book[] library)
    {
        File file = new File(f);
        try
        {
            Scanner fileReader = new Scanner(file);
            fileReader.nextLine();
            int count= 0;
            while (fileReader.hasNextLine())
            {
                String[] parameters = fileReader.nextLine().split("\t");
                Book book = new Book(parameters);
                library[count] = book;
                count++;
            }
            System.out.println("Books stored in array: " + count);
            fileReader.close();
        }
        catch(IOException ioe)
        {
            System.out.println("File Error!");
        }
    }

    // Prints out time performance of insert, find and delete operations for a linear hash table
    public static void compareLinear(LinearHashTable<Double, Book> linearHash, Book[] library)
    {
        System.out.println("Linear Hash Insert: "); ///////////////////////////////////////////// Linear Hash Insert

        long[] timeMeasure = new long[15];
        int position = 0;

        long start = System.currentTimeMillis();

        // Insert and gather the time
        for(int i = 0; i < 1500; i++)
        {
            linearHash.insert(library[i].getISBN13(), library[i]);
            if (i % 100 == 0)
            {
                timeMeasure[position] = System.currentTimeMillis() - start;
                position++;
            }
        }

        // Total time
        long end = System.currentTimeMillis();

        // Print
        for (int i = 0; i < timeMeasure.length; i++)
            System.out.println((i+1) * 100 + " | " + timeMeasure[i]);

        // calculate total time
        long time = end - start;
        System.out.println("Total time: " + time);
        System.out.println("Size: " + linearHash.getSize());

        System.out.println("Linear Hash Find: "); ///////////////////////////////////////////// Linear Hash Find
        
        // Find and gather time
        start = System.currentTimeMillis();
        position = 0;

        for (int i = 0; i < 1500; i++)
        {
            linearHash.find(library[i].getISBN13());
            if (i % 100 == 0)
            {
                timeMeasure[position] = System.currentTimeMillis() - start;
                position++;
            }
        }

        // Total time
        end = System.currentTimeMillis();

        // Print
        for (int i = 0; i < timeMeasure.length; i++)
            System.out.println((i+1) * 100 + " | " + timeMeasure[i]);

        // calculate total time
        time = end - start;
        System.out.println("Total time: " + time);

        System.out.println("Linear Hash Delete: "); ///////////////////////////////////////////// Linear Hash Delete

        // Find and gather time
        start = System.currentTimeMillis();
        position = 0;

        for (int i = 0; i < 1500; i++)
        {
            linearHash.delete(library[i].getISBN13());
            if (i % 100 == 0)
            {
                timeMeasure[position] = System.currentTimeMillis() - start;
                position++;
            }
        }

        // Total time
        end = System.currentTimeMillis();

        // Print
        for (int i = 0; i < timeMeasure.length; i++)
            System.out.println((i+1) * 100 + " | " + timeMeasure[i]);

        // calculate total time
        time = end - start;
        System.out.println("Total time: " + time);
    }

    // Prints out time performance of insert, find and delte operations for double hash table
    public static void compareDoubleHash(DoubleHashTable<Double, Book> dubHash, Book[] library)
    {
        // Store the time for each 100 in this array
        long[] timeMeasure = new long[15];
        int position = 0;

        System.out.println("Double Hash Insertion: "); ////////////////////////////////////// Double Hash Insertion

        // Start time of insertion for measuring double hash insertion performance
        long start = System.currentTimeMillis();

        // Insert and gather the time
        for(int i = 0; i < 1500; i++)
        {
            dubHash.insert(library[i].getISBN13(), library[i]);
            if (i % 100 == 0)
            {
                timeMeasure[position] = System.currentTimeMillis() - start;
                position++;
            }
        }

        // Total time
        long end = System.currentTimeMillis();

        // Print
        for (int i = 0; i < timeMeasure.length; i++)
            System.out.println((i+1) * 100 + " | " + timeMeasure[i]);

        // calculate total time
        long time = end - start;
        System.out.println("Total time: " + time);
        System.out.println("Size: " + dubHash.getSize());

        System.out.println("Double Hash Find: "); ///////////////////////////////////////////// Double Hash Find
        
        // Find and gather time
        start = System.currentTimeMillis();
        position = 0;

        for (int i = 0; i < 1500; i++)
        {
            dubHash.find(library[i].getISBN13());
            if (i % 100 == 0)
            {
                timeMeasure[position] = System.currentTimeMillis() - start;
                position++;
            }
        }

        // Total time
        end = System.currentTimeMillis();

        // Print
        for (int i = 0; i < timeMeasure.length; i++)
            System.out.println((i+1) * 100 + " | " + timeMeasure[i]);

        // calculate total time
        time = end - start;
        System.out.println("Total time: " + time);

        System.out.println("Double Hash Delete: "); ///////////////////////////////////////////// Double Hash Delete
        
        // Find and gather time
        start = System.currentTimeMillis();
        position = 0;

        for (int i = 0; i < 1500; i++)
        {
            dubHash.delete(library[i].getISBN13());
            if (i % 100 == 0)
            {
                timeMeasure[position] = System.currentTimeMillis() - start;
                position++;
            }
        }

        // Total time
        end = System.currentTimeMillis();

        // Print
        for (int i = 0; i < timeMeasure.length; i++)
            System.out.println((i+1) * 100 + " | " + timeMeasure[i]);

        // calculate total time
        time = end - start;
        System.out.println("Total time: " + time);
    }

    // Print menu
    public static void printMenu()
    {
        System.out.println("1. insert");
        System.out.println("2. find");
        System.out.println("3. delete");
        System.out.println("4. compare");
        System.out.println("5. print list");
        System.out.println("6. size");
        System.out.println("9. exit");
    }
}