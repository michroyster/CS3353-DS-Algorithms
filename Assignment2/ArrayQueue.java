public class ArrayQueue{

    // Character class as an inner class of the Queue
    public class Customer 
    {
        // paramaters
        int customerID;
        String phoneNumber;
        String customerName;
        String serviceType;
    
        // Getters methods
        int getCustomerID() { return customerID; }
        String getPhoneNumber() { return phoneNumber; }
        String getCustomerName() { return customerName; }
        String getServiceType() { return serviceType; }
    
        // Setter methods
        void setCustomerID(int customerID) { this.customerID = customerID; }
        void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
        void setCustomerName(String customerName) { this.customerName = customerName; }
        void setServiceType(String serviceType) { this.serviceType = serviceType; }
    
        // Empty constructor
        public Customer() { }

        // Paramaterized constructor
        public Customer(String[] parameters)
        {
            this.setCustomerID(Integer.parseInt(parameters[0]));
            this.setPhoneNumber(parameters[1]);
            this.setCustomerName(parameters[2]);
            this.setServiceType(parameters[3]);
        }
    
        // Return string representation of the customer's parameters
        @Override
        public String toString()
        {
            return customerID + "\t" + phoneNumber + "\t" + customerName + "\t" + serviceType;
        }
        
        // Print all the details of the customer
        public void printDetails()
        {
            System.out.println(customerID + "\t" + phoneNumber + "\t" + customerName + "\t" + serviceType);
        }
    }

    // Parameters
    private Customer[] data;
    private int front;
    private int size;
    private static final int MAX_CAPACITY = 80;
    private static final int TIGHT_CONSTANT = 10;

    // Parameterized constructor
    public ArrayQueue(int capacity)
    {
        front = 0;
        data = new Customer[capacity];
    }

    // Adds a customer to the back of the queue
    // Then moves them to the correct sorted position in the queue
    public void enqueue(Customer element) throws OverflowException {
        if (size == data.length) 
            throw new OverflowException("Queue is full.");
        
        // insert at end of queue
        int position = (front + size) % data.length;
        data[position] = element;
        size++;
        
        
        for (int i = 0; i < size - 1; i++)
        {
            int endIndex = (front + (size - 1) - i) % data.length;
            int frontIndex = (front + (size - 2) - i) % data.length;
            if (data[frontIndex].getCustomerID() > data[endIndex].getCustomerID())
            {
                Customer temp = new Customer();
                temp = data[frontIndex];
                data[frontIndex] = data[endIndex];
                data[endIndex] = temp;
            }
            else
            {
                return;
            }
        }
    }
    
    //Removes a customer from the front of the queue
    public Customer dequeue() throws UnderflowException{
        if (isEmpty())
            throw new UnderflowException("Queue is empty.");

        Customer answer = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        return answer;
    }

    // Set all the references in the fixed aray to null
    public void initialize() {
        for (int i = 0; i < size; i++)
        {
            int index = (front + i) % data.length;
            data[index] = null;
        }

        front = 0;
        size = 0;
    }

    // Returns the customer at the front of the queue, null if empty
    public Customer first() 
    { 
        if (isEmpty())
            return null;

        return data[front]; 
    }

    // Returns the number of customers in the queue
    public int size() { return size; }

    // Returns true if queue is empty, false if not
    public boolean isEmpty() { return size == 0; }

    // Returns true if queue is full, false if not
    public boolean isFull() { return size == data.length; }

    // Prints a list of the queue in order
    // Also prints a visual representation of the dynamic array by customer ID
    public void printList()
    {
        if (isEmpty()){
            System.out.println("List is now empty.");
            return;
        }

        for (int i = 0; i < size; i++)
            System.out.println(data[(front + i) % data.length].toString());
        System.out.println("\n");

        for (int i = 0; i < data.length; i++)
        {
            System.out.print("| ");
            if (data[i] == null)
                System.out.print("null");
            else
                System.out.print(data[i].getCustomerID());
        }
            System.out.print("|\n");
    }

    // Creates a new customer
    public Customer createCustomer(String[] parameters)
    {
        return new Customer(parameters);
    }

    // Resizes the array based on the tight strategy
    // Creates a new array that has 10 more open positions
    // Then copies the orignal array to the new array and sets the original array to the larger array
    public void tightResize() throws OverflowException
    {
        System.out.println("Queue capacity increased by tight strategy");
        if (data.length == MAX_CAPACITY) throw new OverflowException("Queue has reached max capacity.");
    
        Customer[] newData = new Customer[data.length + TIGHT_CONSTANT];

        for (int i = 0; i < size; i++)
        {
            int index = (front + i) % data.length;
            newData[i] = data[index];
            data[index] = null;
        }

        data = newData;
        front = 0;
    }

    // Resizes the array based on the tight strategy
    // Creates a new array that is twice as big
    // Then copies the orignal array to the new array and sets the original array to the larger array
    public void growthResize() throws OverflowException
    {
        System.out.println("Queue capacity increased by growth strategy.");
        if (data.length == MAX_CAPACITY) throw new OverflowException("Queue has reached max capacity.");

        Customer[] newData = new Customer[data.length * 2];

        for (int i = 0; i < size; i++)
        {
            int index = (front + i) % data.length;
            newData[i] = data[index];
            data[index] = null;
        }

        data = newData;
        front = 0;
    }

    // Returns the current capacity of the queue
    public int getCapacity()
    {
        return data.length;
    }

    // Returns the customer at a specific index of the array (not position in queue)
    // this was created for testing purposes.
    public Customer getCustomer(int index)
    {
        return data[index];
    }
}