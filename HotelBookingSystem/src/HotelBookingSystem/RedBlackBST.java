package HotelBookingSystem;

/**
 * The following logic has been modified from the code
 * found in Sedgewick & Wayne's 4th Edition "Algorithms"
 * textbook. A link to the textbook's version of the
 * algorithm can be found here:
 * https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/RedBlackBST.java.html
 * @author Robert Sedgewick
 * @author Kevin Wayne
 * @author Jonathan Janzen
 *
 */
public class RedBlackBST<Key extends Comparable<Key>, Value> {
	
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	
	private Node root;
	
	/**
	 * Private class to define the Node datatype (for use in the BST)
	 * @author Robert Sedgewick
	 * @author Kevin Wayne
	 * @author Jonathan Janzen
	 *
	 */
	private class Node {
		private Key key;
		private Value val;
		Node left, right;
		private boolean colour;	//colour of parent link
		private int size;		//number of subtrees
		
		/**
		 * Constructor for a Node.
		 * @param _key Key (of type Comparable) for the node.
		 * @param _val Value that corresponds to the given value.
		 * @param _colour Boolean to represent whether the given
		 * node is RED or BLACK.
		 * @param _size The number of subtrees for a given node.
		 */
		public Node(Key _key, Value _val, boolean _colour, int _size) {
			this.key = _key;
			this.val = _val;
			this.colour = _colour;
			this.size = _size;
		}
	}
	
	/**
	 * Constructor for an empty BST.
	 */
	public RedBlackBST() {
	}
	
	/**
	 * Function to determine if a given node is red.
	 * @param x Node to be tested.
	 * @return True if the node is red, false otherwise (including if 
	 * the node is null).
	 */
	private boolean isRed(Node x) {
		if (x == null) return false;
		return x.colour == RED;
	}
	
	/**
	 * Function to determine the size of a given node.
	 * @param x Node to determine the size of.
	 * @return The size of the node (0 if the node is null).
	 */
	private int size(Node x) {
		if (x == null) return 0;
		return x.size;
	}
	
	/**
	 * Number of key-value pairs in the tree.
	 * @return The number of key-value pairs in the tree.
	 */
	public int size() {
		return size(root);
	}
	
	/**
	 * Function to determine if the tree is empty.
	 * @return Returns true if the tree is empty and false otherwise.
	 */
	public boolean isEmpty() {
		return root == null;
	}
	
	/**
	 * Function to return the value associated with a given key.
	 * @param key Key to find the value of.
	 * @return The value associated with the given key.
	 */
	public Value get(Key key) {
		if (key == null) throw new IllegalArgumentException("Argument to get() is null");
		return get(root, key);
	}
	
	/**
	 * Gets the value of a node with a particular key.
	 * @param x Node to be checked.
	 * @param key Key to be searched for.
	 * @return The value of the given key. If it does not exist, return null.
	 */
	private Value get(Node x, Key key) {
		while (x != null) {
			int cmp = key.compareTo(x.key);
			if (cmp < 0) x = x.left;
			else if (cmp > 0) x = x.right;
			else return x.val;
		}
		return null;
	}
	
	/**
	 * Determines if the BST contains the given key.
	 * @param key The key to be found.
	 * @return True if the key is found, false otherwise.
	 */
	public boolean contains(Key key) {
		return get(key) != null;
	}
	
	/**
	 * Insert the key-value pair specified into the BST, overwriting the previous
	 * value if it already exists.
	 * @param key Key to be added.
	 * @param val Value to be added with the given key.
	 */
	public void put(Key key, Value val) {
		if (key == null) throw new IllegalArgumentException("First argument to put() is null.");
//		if (val == null) {
//			delete(key);
//			return;
//		}
		
		root = put(root, key, val);
		root.colour = BLACK;
	}
	
	/**
	 * Insert a key-value pair into the subtree rooted at h.
	 * @param h The root node.
	 * @param key The key to be inserted at.
	 * @param val The value to be inserted.
	 * @return The node that was inserted.
	 */
	private Node put(Node h, Key key, Value val) {
		if (h == null) return new Node(key, val, RED, 1);
		
		int cmp = key.compareTo(h.key);
		if (cmp < 0) h.left = put(h.left, key, val);
		else if (cmp > 0) h.right = put(h.right, key, val);
		else h.val = val;
		
		//Correct any links that are leaning right
		if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
		if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
		if (isRed(h.left) && isRed(h.right)) flipColours(h);
		h.size = size(h.left) + size(h.right) + 1;
		
		return h;
	}
	
	/**
	 * Function to make a left leaning link lean to the right.
	 * @param h Node upon which to rotate.
	 * @return Parent node post-rotation
	 */
	private Node rotateRight(Node h) {
		assert (h != null) && isRed(h.left);
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.colour = h.colour;
		h.colour = RED;
		x.size = h.size;
		h.size = size(h.left) + size(h.right) + 1;
		return x;
	}
	
	/**
	 * Function to make a right learning link lean to the left.
	 * @param h Node upon which to rotate.
	 * @return Parent node post-rotation.
	 */
	private Node rotateLeft(Node h) {
		assert (h != null) && isRed(h.right);
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.colour = h.colour;
		h.colour = RED;
		x.size = h.size;
		h.size = size(h.left) + size(h.right) + 1;
		return x;
	}
	
	/**
	 * Flips the colours of a node and its two children.
	 * @param h Parent node to flip.
	 */
	private void flipColours(Node h) {
		h.colour = !h.colour;
		h.left.colour = !h.left.colour;
		h.right.colour = !h.right.colour;
	}
}
