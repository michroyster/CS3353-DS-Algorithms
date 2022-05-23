/** Interface for the priority queue ADT. */
public interface PriorityQueue<K,V> { 
	int size( );
	boolean isEmpty( );
	Entry<K,V> insert(K key, V value) throws IllegalArgumentException;
	Entry<K,V> max( );			
	Entry<K,V> removeMax( );	// remove the process with highest priority 
	Entry<K,V> update(K oldKey, K newKey) throws IllegalArgumentException;
}