/*
 Name: Anthony Shih
 PID: A11295870
 Login: cs12wei
 */
package hw7;

public class BSTree {

    //Our first node
    BSTNode rootNode;
    BSTNode deleted;
    
    //Constructor, making new empty tree
    public BSTree() {
        rootNode = null;
    }

    /*
     Return the rootNode
     @author Anthony Shih
     @version 1.0
     @since 02-03-2016
     */
    public BSTNode getRoot() {
        return rootNode;
    }

    /*Check for valid input for name and key
     Recursively add to tree
     @author Anthony Shih
     @version 1.0
     @since 02-03-2016
     */
    public void insert(String name, int key) {
        if (name.equals("") || key < 1 || key > 200) {
            throw new IllegalArgumentException();
        }
        Person newPerson = new Person(name, key);
        BSTNode newNode = new BSTNode(null, newPerson, null);
        rootNode = insert(rootNode, newNode);
    }

    /*Recursive method for insert
     Going down the tree until we find a null node where it belongs.
     Checks key to make sure it's in the right place.
     @author Anthony Shih
     @version 1.0
     @since 02-03-2016
     */
    private BSTNode insert(BSTNode startNode, BSTNode newNode) {
        if (startNode == null) {
            //Making this node the newNode
            startNode = newNode;
        } else {
            if (startNode.getPerson().getKey()
                    <= newNode.getPerson().getKey()) {
                //Searching for the right place
                startNode.setRChild(insert(startNode.getRChild(), newNode));
            } else {
                //Either the left or the right child
                startNode.setLChild(insert(startNode.getLChild(), newNode));
            }
        }
        return startNode;
    }

    /*Check for valid input for name and key
     Recursively search for key and name
     @author Anthony Shih
     @version 1.0
     @since 02-03-2016
     */
    public boolean findPerson(int key, String name) {
        if (name.equals("") || key < 1 || key > 200) {
            throw new IllegalArgumentException();
        }
        return searchPerson(rootNode, key, name);
    }

    /*Recursive method for search.
     Goes down the tree until it finds the desired node. Returns true if found,
     False if not found.
     @author Anthony Shih
     @version 1.0
     @since 02-03-2016
     */
    private boolean searchPerson(BSTNode startNode, int key, String name) {
        int currentKey = startNode.getPerson().getKey();
        if (startNode == null) {
            return false;
        }
        if (key < currentKey) {
            return searchPerson(startNode.getLChild(), key, name);
        } else if (key > currentKey) {
            return searchPerson(startNode.getRChild(), key, name);
        }//If the keys are the same 
        else {
            //Need to check if name is the same
            if (startNode.getPerson().getName().equals(name)) {
                return true;
            } else {
                //Duplicate Keys are right children
                return searchPerson(startNode.getRChild(), key, name);
            }
        }
    }

    /*Printing to Array
     @author Anthony Shih
     @version 1.0
     @since 02-03-2016
     */
    public Person[] printToArray(BSTNode root) {
        Person[] toArray = new Person[200];
        transferToArray(root, toArray, 0);
        return toArray;
    }

    /*Helper method to make the arrray
     Adds to the array starting from the left. 
     @author Anthony Shih
     @version 1.0
     @since 02-03-2016
     */
    private void transferToArray(BSTNode rootNode, Person[] array, int index) {
        if (rootNode == null) {
            return;
        }
        transferToArray(rootNode.getLChild(), array, index);
        array[index++] = rootNode.getPerson();
        transferToArray(rootNode.getRChild(), array, index);
    }

    /*Check for valid input for key and name
     Recursively delete given key and name
     @author Anthony Shih
     @version 1.0
     @since 02-03-2016
     */
    public Person delete(int key, String name) {
        if (name.equals("") || key < 1 || key > 200) {
            throw new IllegalArgumentException();
        }
        return delete(rootNode, key, name).getPerson();
    }

    /*Private recursive method for delete
     Searches for the desired node, then places the next higher node in its
     place as its deletion. If it has no children, then return null.
     Returning the desired node, but deleting it within the method.
     @author Anthony Shih
     @version 1.0
     @since 02-03-2016
     */
    private BSTNode delete(BSTNode startNode, int key, String name) {
        if (startNode == null) {
            return startNode;
        }
        Person currentPerson = startNode.getPerson();
        BSTNode left, right;
        //Found the right key
        if (currentPerson.getKey() == key) {
            if (currentPerson.getName().equals(name)) {
                deleted = startNode;
                left = startNode.getLChild();
                right = startNode.getRChild();
                //Checking for children and adjusting their places
                //No Children
                if (left == null && right == null) {
                    return null;
                } //Right Child only
                else if (left != null && right == null) {
                    return left;
                } //Left Child only
                else if (left == null && right != null) {
                    return right;
                } //Has Both Left and Right Children
                else if (left != null && right != null) {                   
                    BSTNode c1 = right;
                    BSTNode c2 = right;
                    while (c1.getLChild() != null) {
                        c2 = c1;
                        c1 = c1.getLChild();
                    }
                    c2.setLChild(null);
                    c1.setLChild(startNode.getLChild());
                    c1.setRChild(startNode.getRChild());
                    return c1;
                }
                return startNode;
            } //Wrong name, go to right for duplicate keys
            else {
                return delete(startNode.getRChild(), key, name);
            }
        }
        //Searching left and right children for key and name
        else if (key < currentPerson.getKey()) {
            startNode.setLChild(delete(startNode.getLChild(), key, name));
            return startNode;
        } else if (key > currentPerson.getKey()){
            startNode.setRChild(delete(startNode.getRChild(), key, name));
            return startNode;
        }
        
        return null;
    }

    /*Find the node and return the depth
     @author Anthony Shih
     @version 1.0
     @since 02-03-2016
     */
    public int FindDepth(BSTNode root) {
        BSTNode goalNode = root;
        Person goalPerson = root.getPerson();
        return getDepth(rootNode, goalPerson, 0);
    }

    /*Private helper method for finding the depth
     Recursively goes down the tree until it finds the node. Returns the value
     as it goes back up.
     @author Anthony Shih
     @version 1.0
     @since 02-03-2016
     */
    private int getDepth(BSTNode startNode, Person goalPerson, int level) {
        if (startNode == null) {
            return -1;
        }
        //Found it, returning the current level
        if (startNode.getPerson().getKey() == goalPerson.getKey()
        && startNode.getPerson().getName().equals(goalPerson.getName())) {
            return level;
        }
        //Go down one level, keep searching left
        int ret = getDepth(startNode.getLChild(), goalPerson, level + 1);
        if (ret != 0) {
            return ret;
        }
        //Go down one level, keep searching right
        ret = getDepth(startNode.getRChild(), goalPerson, level + 1);
        return ret;

    }

    /*Counting leaves method
     @author Anthony Shih
     @version 1.0
     @since 02-03-2016
     */
    public int leafCount() {
        return countLeaves(rootNode);
    }

    /*Recursive helper method for leafCount
     Goes down until it finds a leaf, then returns 1. At end of code it will 
     add up total number of nodes that are leaves.
     @author Anthony Shih
     @version 1.0
     @since 02-03-2016
     */
    private int countLeaves(BSTNode startNode) {
        if (startNode == null) {
            return 0;
        }
        //Found a leaf, no children
        if (startNode.getLChild() == null && startNode.getRChild() == null) {
            return 1;
        } else {
            //Checking if Left or Right child is a leaf
            return countLeaves(startNode.getLChild())
                    + countLeaves(startNode.getRChild());
        }
    }

    /*Counting how many nodes at a level
     @author Anthony Shih
     @version 1.0
     @since 02-03-2016
     */
    public int levelCount(int level) {
        return getCountAt(rootNode, 0, level);
    }

    /*Recursive helper method for levelCount
     Goes down until it reaches the desired level and returns 1 if there's a
     node. At end of code it will add up to the total number of nodes at that
     level.
     @author Anthony Shih
     @version 1.0
     @since 02-03-2016
     */
    private int getCountAt(BSTNode startNode, int current, int desired) {
        if (startNode == null) {
            return -1;
        }
        if (current == desired) {
            return 1;
        }
        return getCountAt(startNode.getLChild(), current + 1, desired)
                + getCountAt(startNode.getRChild(), current + 1, desired);
    }

    /*Inner Class for BSTNode
     @author Anthony Shih
     @version 1.0
     @since 02-03-2016
     */
    public class BSTNode {

        //Private Variables
        private BSTNode leftChild;
        private BSTNode rightChild;
        private Person myPerson;

        //Constructor
        public BSTNode(BSTNode lChild, Person p, BSTNode rChild) {
            if (p.getName() == (null)) {
                throw new NullPointerException();
            }
            leftChild = lChild;
            myPerson = p;
            rightChild = rChild;
        }

        //Getter method for myPerson
        public Person getPerson() {
            return myPerson;
        }

        //Getter method for leftChild
        public BSTNode getLChild() {
            return leftChild;
        }

        //Getter method for rightChild
        public BSTNode getRChild() {
            return rightChild;
        }

        //Setter method for leftChild
        public void setLChild(BSTNode newLChild) {
            leftChild = newLChild;
        }

        //Setter method for rightChild
        public void setRChild(BSTNode newRChild) {
            rightChild = newRChild;
        }
    }
}
