import java.util.Comparator;

// Example from lecture.

public abstract class AbstractPriorityQueue<K,V> implements PriorityQueue<K,V> {
    
    protected static class PQEntry<K,V> implements Entry<K,V> {
        private K k;
        private V v;
        public PQEntry(K key, V value){
            k = key;
            v = value;
        }

        public K getKey() { return k; }
        public V getValue() { return v; }

        protected void setKey(K key) { k = key; }
        protected void setValue(V value) { v = value; }
    }

    // The comparator defining the ordering of keys in the priority queue
    private Comparator<K> comp;

    // Creates an empty priority queue using the given comparator to order keys
    protected AbstractPriorityQueue(Comparator<K>c) { comp = c; }

    // Creates an empty priority queueu based on the natrual ordering of its keys
    protected AbstractPriorityQueue() { this(new DefaultComparator<K>()); }

    // Method for comparing two entries by key
    protected int compare (Entry<K,V> a, Entry<K,V> b)
    {
        return comp.compare(a.getKey(), b.getKey());
    }

    // Determines whetehr a key is valid
    protected boolean checkKey(K key) throws IllegalArgumentException
    {
        try {
            return (comp.compare(key,key) == 0);
        } catch(ClassCastException e) {
            throw new IllegalArgumentException("Incompatible key");
        }
    }

}
