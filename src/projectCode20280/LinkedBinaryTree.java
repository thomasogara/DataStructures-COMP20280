package projectCode20280;

/**
 * Concrete implementation of a binary tree using a node-based, linked structure.
 */
public class LinkedBinaryTree<E extends Comparable<E>> extends AbstractBinaryTree<E> {
    /**
     * The root of the binary tree
     */
    protected Node<E> root = null;
    /**
     * The number of nodes in the binary tree
     */
    private int size = 0;

    /**
     * Construts an empty binary tree.
     */
    public LinkedBinaryTree() {

    }

    public static void main(String[] args) {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();

        int[] arr = {12, 25, 31, 58, 36, 42, 90, 62, 75};
        for (int i : arr) {
            bt.insert(i);
        }
        System.out.println("bt (size = " + bt.size() + "):\n" + bt);

    }

    /**
     * Factory function to create a new node storing element e.
     */
    protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) {
        return new Node<E>(e, parent, left, right);
    }

    /**
     * Verifies that a Position belongs to the appropriate class, and is
     * not one that has been previously removed. Note that our current
     * implementation does not actually verify that the position belongs
     * to this particular list instance.
     *
     * @param p a Position (that should belong to this tree)
     * @return the underlying Node instance for the position
     * @throws IllegalArgumentException if an invalid position is detected
     */
    protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node))
            throw new IllegalArgumentException("Not valid position type");
        Node<E> node = (Node<E>) p;       // safe cast
        if (node.parent == node)     // our convention for defunct node
            throw new IllegalArgumentException("p is no longer in the tree");
        return node;
    }

    // accessor methods (not already implemented in AbstractBinaryTree)

    /**
     * Returns the number of nodes in the tree.
     *
     * @return number of nodes in the tree
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the root Position of the tree (or null if tree is empty).
     *
     * @return root Position of the tree (or null if tree is empty)
     */
    @Override
    public Position<E> root() {
        return root;
    }

    /**
     * Returns the Position of p's parent (or null if p is root).
     *
     * @param p A valid Position within the tree
     * @return Position of p's parent (or null if p is root)
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.parent;
    }

    /**
     * Returns the Position of p's left child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the left child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    @Override
    public Position<E> left(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.left;
    }

    /**
     * Returns the Position of p's right child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the right child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    @Override
    public Position<E> right(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.right;
    }

    // update methods supported by this class

    /**
     * Places element e at the root of an empty tree and returns its new Position.
     *
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalStateException if the tree is not empty
     */
    public Position<E> addRoot(E e) throws IllegalStateException {
        if (root != null) throw new IllegalStateException("root already exists for this tree");
        else this.root = new Node<>(e);
        return this.root;
    }

    public void insert(E e) {
        //recursively add from root
        if(root == null) addRoot(e);
        else addRecursive(root, e);
        ++size;
    }

    private Node<E> addRecursive(Node<E> p, E e) {
        if(p.left == null){
            addLeft(p, e);
            return p.left;
        }
        if(p.right == null){
            addRight(p, e);
            return p.right;
        }

        SinglyLinkedList<Position<E>> leftChildren = new SinglyLinkedList<>();
        SinglyLinkedList<Position<E>> rightChildren = new SinglyLinkedList<>();
        inorderSubtree(p.left, leftChildren);
        inorderSubtree(p.right, rightChildren);

        if(leftChildren.size() <= rightChildren.size()){
            return addRecursive(p.left, e);
        }else{
            return addRecursive(p.right, e);
        }
    }


    /**
     * Creates a new left child of Position p storing element e and returns its Position.
     *
     * @param p the Position to the left of which the new element is inserted
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     * @throws IllegalArgumentException if p already has a left child
     */
    public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
        if (validate(p).left != null) throw new IllegalStateException("left already exists at this node");
        else validate(p).left = new Node<E>(e, validate(p), null, null);
        return validate(p).left;
    }

    /**
     * Creates a new right child of Position p storing element e and returns its Position.
     *
     * @param p the Position to the right of which the new element is inserted
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     * @throws IllegalArgumentException if p already has a right child
     */
    public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
        if (validate(p).right != null) throw new IllegalStateException("right already exists at this node");
        else validate(p).right = new Node<E>(e, validate(p), null, null);
        return validate(p).right;
    }

    /**
     * Replaces the element at Position p with element e and returns the replaced element.
     *
     * @param p the relevant Position
     * @param e the new element
     * @return the replaced element
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        node.data = e;
        return node.data;
    }

    /**
     * Attaches trees t1 and t2, respectively, as the left and right subtree of the
     * leaf Position p. As a side effect, t1 and t2 are set to empty trees.
     *
     * @param p  a leaf of the tree
     * @param t1 an independent tree whose structure becomes the left child of p//recursively add Nodes to binary tree in proper position
     * @param t2 an independent tree whose structure becomes the right child of p
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     * @throws IllegalArgumentException if p is not a leaf
     */
    public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) throws IllegalArgumentException {

    }

    /**
     * Removes the node at Position p and replaces it with its child, if any.
     *
     * @param p the relevant Position
     * @return element that was removed
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     * @throws IllegalArgumentException if p has two children.
     */
    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        if(node.left != null && node.right != null) throw new IllegalArgumentException("cannot remove position which has two children");
        Node<E> parent = node.parent;
        if(parent.left == node){
            parent.left = (node.left == null ? node.right : node.left);
        }else{
            parent.right = (node.left == null ? node.right : node.left);
        }
        node.parent = node; // invalidate node
        return node.data;

    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("inorder: [");
        List<Position<E>> inorders = inorder();
        for(int i = 0; i < inorders.size(); i++){
            sb.append(inorders.get(i).getElement());
            sb.append(", ");
        }
        sb.append("]\n");

        sb.append("preorder: [");
        List<Position<E>> preorders = preorder();
        for(int i = 0; i < preorders.size(); i++){
            sb.append(preorders.get(i).getElement());
            sb.append(", ");
        }
        sb.append("]\n");
        return sb.toString();
    }

    /**
     * Nested static class for a binary tree node.
     */
    protected static class Node<E> implements Position<E> {
        Node<E> parent;
        Node<E> left;
        Node<E> right;
        E data;

        Node(E e, Node<E> parent, Node<E> left, Node<E> right) {
            this.data = e;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        Node(E e){
            this.data = e;
        }

        public E getElement() {
            return this.data;
        }
    }
} 

