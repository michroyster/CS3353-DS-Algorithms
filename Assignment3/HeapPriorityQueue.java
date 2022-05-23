import java.util.Comparator;

public class HeapPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {
    // primary collection of priority queue entries
    protected Entry<K,V>[] heap;
    private int size;

    // Creates an empty priority queue based on the natural ordering of its keys
    public HeapPriorityQueue(int capacity) { 
        super(); 
        heap = new Entry[capacity];
        size = 0;
    }

    // Creates an empty priority queue using the given comparator to order keys
    public HeapPriorityQueue(Comparator<K> comp) { super(comp); }

    // Protected utilities
    protected int parent(int j) { return j/2; } //truncating division
    protected int left(int j) { return 2*j; }
    protected int right(int j) { return 2*j + 1; }
    protected boolean hasLeft(int j) { return left(j) < size; }
    protected boolean hasRight(int j) { return right(j) < size; }

    // Exchanges the entries at indices i and j of the array list
    protected void swap(int i, int j)
    {
        Entry<K, V> temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // Moves the entry at index j higher, if necessary, to restore the heap property
    // used when new key-value pair is inserted in the heap. 
    protected void upheap(int j)
    {
        while(j > 1) // continue until reaching root (or break statement)
        {
            int p = parent(j);
            if (compare(heap[j], heap[p]) <= 0)
                break; // heap property verified
            swap(j, p);
            j = p; // continue from the parent's location
        }
    }

    // moves the entry at index j lower, if necessary to restore the heap propperty.
    protected void downheap(int j)
    {
        while (hasLeft(j)) // continue to bottom (or break statement)
        {
            int leftIndex = left(j);
            int largeChildIndex = leftIndex; // although right may be smaller
            if (hasRight(j))
            {
                int rightIndex = right(j);
                if (compare(heap[leftIndex], heap[rightIndex]) < 0)
                    largeChildIndex = rightIndex; // right child is larger
            }
            if (compare(heap[largeChildIndex], heap[j]) <= 0)
                break; // heap property has been restored
                
            swap(j, largeChildIndex);
            j = largeChildIndex; // continue at position of the child
        }
    }

    // public methods:

    // returns the number of items in the priority queue
    public int size() { return size; }

    // Checks if the heap is empty
    public boolean isEmpty() { return size() == 0; }

    // Checks if the heap is full
    public boolean isFull() { return size() == heap.length - 1; }

    // returns (but does not remove) an entry with maximum key (if any)
    public Entry<K,V> max() 
    {
        if (isEmpty()) return null;

        return heap[1];
    }

    // Inserts a key-value pair and returns the entry created
    // calls tightResize if the heap is full.
    public Entry<K,V> insert(K key, V value) throws IllegalArgumentException
    {
        checkKey(key); //auxiliary key-checking method (could throw exception)
        Entry<K,V> newest = new PQEntry<>(key, value);
        heap[size + 1] = newest; // add to the end of the list
        size++;
        upheap(size); //  upheap newly added entry
        return newest;
    }

    // Removes and returns an entry with the minimum key (if any)
    public Entry<K,V> removeMax()
    {
        if (isEmpty()) return null;

        Entry<K, V> answer = heap[1];
        swap(1, size); // put maximum item at the end
        heap[size] = null; // and remove it from the list
        size--;
        downheap(1); // then fix the new root
        return answer;
    }

    // Change the priority key of the max node
    public Entry<K, V> update(K oldKey, K newKey) throws IllegalArgumentException 
    {
        // Check if empty
        if (isEmpty()) return null;
        
        // Store value of max
        V value = this.max().getValue();

        // remove max from heap
        this.removeMax();

        // insert max with the newKey value
        insert(newKey, value);

        // return the new max node
        return max();
    }

    // Resize the heap array by adding 20 to the length
    public void tightResize()
    {
        Entry<K,V>[] newHeap = new Entry[heap.length + 20];
        
        for (int i = 1; i < size + 1; i++)
        {
            newHeap[i] = heap[i];
            heap[i] = null;
        }

        heap = newHeap;

        System.out.println("Heap expanded to: " + (heap.length));
    }

    // Used for testing - print the priority and name of the heap
    public void printHeap()
    {
        for (int i = 1; i < size() + 1; i++)
        {
            System.out.println("Position " + i + ": Key: " + heap[i].getKey());
        }
    }
}