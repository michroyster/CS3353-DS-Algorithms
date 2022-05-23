public class AVLTree<K extends Number, V> {
    private Node<K,V> root;
    public Node<K,V> getRoot() { return root; }

    protected static class Node<K,V>
    {
        private K key;
        private V value;
        private int balance;
        private int height;
        private Node<K,V> left, right, parent;

        public K getKey() { return key; }
        public V getValue() { return value; }
        public void setKey(K key) { this.key = key; }

        Node(K key, V value, Node<K,V> parent)
        {
            this.key = key;
            this.value = value;
            this.parent = parent;
            height = 1;
        }
    }

    // Searches for a node recursively and returns it
    public Node<K,V> search(Node<K,V> root, K key)
    {
        if (root == null || root.key == key)
            return root;

        if (compare(key, root.key) < 0)
            return search(root.left, key);
        else
            return search(root.right, key);
    }

    // Inserts a node at the correct position in the tree
    public void insert(K key, V value)
    {
        if (root == null)
        {
            root = new Node<K,V>(key, value, null);
            return;
        }
        Node<K,V> cursor = root;
        Node<K,V> trailer = null;

        while (cursor != null)
        {
            trailer = cursor;
            if (compare(key, cursor.key) < 0)
                cursor = cursor.left;
            else
                cursor = cursor.right;
        }

        if (compare(key, trailer.key) < 0)
        {
            trailer.left = new Node<K,V>(key, value, trailer);
        }
        else
        {
            trailer.right = new Node<K,V>(key, value, trailer);
        }
        rebalance(trailer);
    }

    public Node<K,V> removeMin()
    {
        Node<K,V> minimum = min(root);
        return delete(minimum);

    }

    // Deletes the node from the tree and returns it
    public Node<K,V> delete(Node<K,V> target)
    {
        Node<K,V> parent = target.parent;
        if (target.left == null)
        {
            transplant(target, target.right);
        }
        else if (target.right == null)
        {
            transplant(target, target.left);
        }
        else
        {
            Node<K,V> min = min(target.right);
            if (compare(min.parent.key, target.key) != 0)
            {
                transplant(min, min.right);
                min.right = target.right;
                min.right.parent = min;
            }
            transplant(target, min);
            min.left = target.left;
            min.left.parent = min;

        }
        if (parent != null)
            rebalance(parent);
        else if (root != null)
            rebalance(root);
        // else
        //     System.out.println("Tree is now empty!");
        cleanNode(target);
        return target;
    }
    
    // Helper function for delete. Replaces one subtree as a child of its parent with another subtree
    private void transplant(Node<K,V> branch, Node<K,V> stem)
    {
        if (branch.parent == null)
            root = stem;
        else if (compare(branch.key, branch.parent.left.key) == 0)
            branch.parent.left = stem;
        else
            branch.parent.right = stem;
        
            if (stem != null) stem.parent = branch.parent;
    }
    
    private void cleanNode(Node<K,V> node)
    {
        node.parent = null;
        node.left = null;
        node.right = null;
        node.height = 1;
    }
    
    /////////////////// ROTATIONS ///////////////////////////

    // Rebalances the given node and rebalnces all ancestor nodes above it
    private void rebalance(Node<K,V> node)
    {
        setBalance(node);

        if (node.balance > 1)                                      // left skewed
        {
            if (height(node.left.left) >= height(node.left.right))
                node = rotateRight(node);
            else
                node = rotateLR(node);

        }
        else if (node.balance < -1)                                  // right skewed
        {
            if(height(node.right.right) >= height(node.right.left))
                node = rotateLeft(node);
            else
                node = rotateRL(node);
        }

        // if the subroot of the rotation is not overall root, rebalance. else, make it root
        if (node.parent != null)
        {
            rebalance(node.parent);
        }
        else
        {
            root = node;
        }
    }

    // Rotate to the left around a node
    private Node<K,V> rotateLeft(Node<K,V> pivot)
    {
        Node<K,V> side = pivot.right;
        side.parent = pivot.parent;

        pivot.right = side.left;

        if (side.left != null) side.left.parent = pivot;

        side.left = pivot;
        pivot.parent = side;

        if (side.parent != null)
        {
            if (side.parent.right == pivot)
                side.parent.right = side;
            else
                side.parent.left = side;
        }

        setBalance(pivot);
        setBalance(side);

        return side;

    }

    // Rotate to the right around a node
    private Node<K,V> rotateRight(Node<K,V> pivot)
    {
        Node<K,V> side = pivot.left;
        side.parent = pivot.parent;

        pivot.left = side.right;

        if (side.right != null) side.right.parent = pivot;

        side.right = pivot;
        pivot.parent = side;

        if (side.parent != pivot)
        {
            if (side.parent.right == pivot)
                side.parent.right = side;
            else
                side.parent.left = side;
        }

        setBalance(pivot);
        setBalance(side);

        return side;

    }

    // Rotate to the left and then rotate to the right
    private Node<K,V> rotateLR(Node<K,V> pivot)
    {
        pivot.left = rotateLeft(pivot.left);
        return rotateRight(pivot);
    }

    // Rotate to the right and then rotate to the left
    private Node<K,V> rotateRL(Node<K,V> pivot)
    {
        pivot.right = rotateRight(pivot.right);
        return rotateLeft(pivot);
    }

    /////////////////// HELPER methods //////////////////////

    // Returns height of a node
    private int height(Node<K,V> node)
    {
        if (node == null) return 0;
        return node.height;
    }

    // recalculates the height of a node
    private void reheight(Node<K,V> node)
    {
        if (node != null)
        {
            node.height = Math.max(height(node.left), height(node.right)) + 1;
        }
    }

    // Resets the balance of a node
    private void setBalance(Node<K,V> node)
    {
        reheight(node);
        node.balance = height(node.left) - height(node.right);
    }

    // returns node with minimum key
    public Node<K,V> min(Node<K,V> root)
    {
        Node<K,V> cursor = root;
        while (cursor.left != null)
            cursor = cursor.left;
        return cursor;
    }

    // Compare two keys. -1 if a < b, 0 if a==b, 1 if a > b
    public int compare(K a, K b)
    {
        int first = a.intValue();
        int second = b.intValue();
        if (first < second)
            return -1;
        else if (first == second)
            return 0;
        else
            return 1;
        
    }

    // Print the tree inorder with details
    public void printInorder(Node<K,V> root)
    {
        Node<K,V> cursor = root;
        if (cursor != null)
        {
            printInorder(cursor.left);
            System.out.println("Key: " + cursor.key + "\tHeight: " + cursor.height + "\tBalance: " + cursor.balance);
            printInorder(cursor.right);
        }
    }
}
