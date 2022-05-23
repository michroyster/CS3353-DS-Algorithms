public class LinearHashTable<K extends Number,V> {
    private HashEntry<K,V>[] table;
    private HashEntry<K,V> TOMBSTONE = new HashEntry<K,V>(null, null);
    private int size;
    private int capacity;
    private int prime;
    private float loadFactor;
    private int scale = 1;
    private int shift = 0;

    // entries into hash table
    protected static class HashEntry<K, V> implements Entry<K,V>{
        K key;
        V value;

        public K getKey() { return key; }
        public V getValue() { return value; }

        protected void setKey(K key) { this.key = key; }
        protected void setValue(V value) { this.value = value; }

        public HashEntry(K key, V value){
            setKey(key);
            setValue(value);
        }
    }

    public int getSize() { return size; }
    public int getCapacity() { return capacity; }
    public int getPrime() { return prime; }
    public float getLoadFactor() {
        loadFactor();
        return loadFactor; 
    }
    public int getScale() { return scale; }
    public int getShift() { return shift; }

    // parameterized constructor
    public LinearHashTable(int capacity)
    {
        table = (HashEntry<K,V>[]) new HashEntry[capacity];
        this.capacity = capacity;
        prime = getNextPrime(capacity);
    }

    // Convert a double to integer through bitwise operations
    public int generateKey(double d)
    {
        long bits = Double.doubleToLongBits(d);
        return (int)(bits ^ (bits >>> 32));
    }

    // takes the key and generates the appropriate hash code
    public int hashFunction(K k)
    {
        double isbn13 = k.doubleValue();
        int key = generateKey(isbn13); 
        int hashIndex = (Math.abs(scale * key + shift) % prime) % capacity;

        if (isAvailable(hashIndex))
        {
            return hashIndex;
        }
        else
        {
            for (int i = 0; i < capacity; i++)
            {
                int position = (hashIndex + i) % capacity;
                if(isAvailable(position))
                    return position;
            }
        }
        return -1;
    }

    // insert a new item into hash table
    public void insert(K key, V value)
    {
        HashEntry<K, V> entry = new HashEntry<K,V>(key, value);
        int hashIndex = hashFunction(key);

        if (hashIndex >= 0)
        {
            table[hashIndex] = entry;
            size++;
        }
        else
        {
            System.out.println("Object could not be inserted.");
        }

        if (loadFactor() > 0.5)
        {
            rehash();
        }

    }

    // find an item in hash table by key
    public V find(K k)
    {
        double isbn13 = k.doubleValue();
        int key = generateKey(isbn13); 
        int hashIndex = (Math.abs(scale * key + shift) % prime) % capacity;

        if (k == table[hashIndex].getKey())
        {
            return table[hashIndex].getValue();
        }
        else
        {
            for (int i = 0; i < capacity; i++)
            {
                int position = (hashIndex + i) % capacity;
                if(!isAvailable(position))
                    if(k.doubleValue() == table[position].getKey().doubleValue())
                        return table[position].getValue();
            }
        }
        System.out.println("could not find object.");
        return null;
    }

    // delete an item from the hash table by key
    public void delete(K k)
    {
        double isbn13 = k.doubleValue();
        int key = generateKey(isbn13); 
        int hashIndex = (Math.abs(scale * key + shift) % prime) % capacity;

        if (k == table[hashIndex].getKey())
        {
            table[hashIndex] = TOMBSTONE;
            size--;
        }
        else
        {
            for (int i = 0; i < capacity; i++)
            {
                int position = (hashIndex + i) % capacity;
                if(!isAvailable(position))
                    if(k.doubleValue() == table[position].getKey().doubleValue())
                    {
                        table[position] = TOMBSTONE;
                        size--;
                        return;
                    }
            }
        }
        System.out.println("object could not be deleted. it was not found.");
    }

    // Resize the table and re-insert all items into new hash table
    public void rehash()
    {
        HashEntry<K,V>[] buffer = (HashEntry<K,V>[]) new HashEntry[capacity];
        int newCap = getNextPrime(capacity * 3);
        for (int i = 0; i < table.length; i++)
        {
            buffer[i] = table[i];
        }
        
        HashEntry<K,V>[] newTable = (HashEntry<K,V>[]) new HashEntry[newCap];;
        table = newTable;
        capacity = newCap;
        prime = getNextPrime(newCap);
        size = 0;

        for(int i = 0; i < buffer.length; i++)
        {
            if (buffer[i] != null)
                insert(buffer[i].getKey(), buffer[i].getValue());
        }
        System.out.println("Hash table has been expanded to " + newCap + " and re-hashed.");
    }

    // returns the load factor
    private float loadFactor()
    { 
        loadFactor = (float) size /  (float) capacity;
        return loadFactor;
    }

    // returns true if the location in the table is available
    private boolean isAvailable(int j)
    {
        return (table[j] == null || table[j] == TOMBSTONE);
    }

    // Find the next prime number after n
    public int getNextPrime(int n)
    {
        n++;
        while(true)
        {
            boolean p = true;
            for (int i = 2; i < n/2; i++)
            {
                if (n % i == 0)
                {
                    p = false;
                }
            }
            if (p == true)
                return n;
            else
                n++;
        }
    }

    // Find the most previous prime number before n
    public int getPrevPrime(int n)
    {
        n--;
        while(true)
        {
            boolean p = true;
            for (int i = 2; i < n/2; i++)
            {
                if (n % i == 0)
                {
                    p = false;
                }
            }
            if (p == true)
                return n;
            else
                n--;
        }
    }

    // prints the entire table
    public void printTable()
    {
        System.out.println("prime: " + getPrime() + " Capacity: " + capacity);
        for (int i = 0; i < table.length; i++)
        {
            if (table[i] == null)
                System.out.println("i: " + i + " null");
            else
                System.out.println("i: " + i + " key: " + table[i].getKey());
        }
    }
}
