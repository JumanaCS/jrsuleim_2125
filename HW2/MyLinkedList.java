public class MyLinkedList<AnyType> { 

    // The number of elements in the list
    private int size;

    // The first node in the list
    private Node firstNode;

    // The last node in the list
    private Node lastNode;

    // The number of elements that have been marked for deletion
    private int deleted;

    public MyLinkedList() {
        // Initialize the size, deleted count, and first/last nodes
        this.size = 0;
        this.deleted = 0;
        this.firstNode = null;
        this.lastNode = null;
    }

    public int size() {
        // Return the number of elements in the list
        return this.size;
    }

    public void add(AnyType element) {
        // Create a new node to store the element
        Node newNode = new Node(element);
// walk out along Node linkages until we hit a node
// that does not have a next node - at that point,
// currentNode will be the last node
// while this will work, its HORRIBLY inefficient Theta(N)
// there's a better way, below, by keeping around a reference
// to the last node
/*
Node currentNode = firstNode;
while (currentNode.getNext() != null)
currentNode = currentNode.getNext();
// tell currentNode that its next node is the one we just built!
currentNode.setNext(newNode);
*/
        // If the list is empty, make the new node the first and last node
        if (size == 0) {
            this.firstNode = newNode;
            this.lastNode = newNode;
        }
        // Otherwise, add the new node to the end of the list
        else {
            this.lastNode.setNext(newNode);
            this.lastNode = newNode;
        }

        // Increment the size
        size++;
    }

    // Insert an element at the given index
    public void add(AnyType element, int index) {
        // Check if the index is out of bounds
        if (index > size)
            throw new IndexOutOfBoundsException("index is too big!");
        else if (index < 0)
            throw new IndexOutOfBoundsException("Have you lost your mind?");

        Node currentNode = firstNode;
        int currentIndex = 0;
        while (currentIndex < index) {
            while (currentNode!=null && currentNode.isDeleted()){ // while the node is marked as deleted
                currentNode=currentNode.getNext(); // gets next node 
            }
            currentIndex++;
            currentNode = currentNode.getNext(); // gets next node 
        }

        if(currentNode.isDeleted()){ // check if the node is deleted 
            currentNode=currentNode.getNext().getNext(); // next
        }
        return currentNode.getData();
    }

    public AnyType lazyDeletion(int index) {
    // Check if the index is out of bounds
        if (index > size)
            throw new IndexOutOfBoundsException("index is too big!");
        else if (index < 0)
            throw new IndexOutOfBoundsException("Have you lost your mind?");
            // Store the element to return in a local variable
        AnyType thingToReturn = null;
        // Create a temporary node to iterate over the list
        Node t = firstNode;
        // Keep track of the current index
        int i = 0;
        // Iterate over the list until we reach the index
        while (t != null) {
            // If we've reached the index, mark the node for deletion
            if (i == index) {
            // If the node is already marked for deletion, skip it
            if (t.isDeleted()) {
                t = t.getNext();
                continue;
            }

            // Mark the node for deletion
            t.setDeleted(true);
            deleted++; // increment the deleted count

            // Store the element to return
            thingToReturn = t.getData();
            break;
        }

        // Otherwise, move to the next node
        else {
            i++;
            t = t.getNext();
        }
    }

    // If the number of deleted elements is greater than or equal to half the size of the list,
    // delete any intermediate nodes that are marked for deletion
    if (deleted >= size / 2) {
        // If the size of the list is greater than 2, delete intermediate nodes
        if (size > 2) {
            // Start at the second node in the list
            t = firstNode.getNext();
            Node te = firstNode;

            // Keep iterating until we reach the last node
            do {
                // If the current node is marked for deletion, delete it
                if (t.isDeleted()) {
                    te.setNext(t.getNext());
                    t.setNext(null);
                    size--;
                    t = te.getNext();
                }

                // Otherwise, move to the next node
                else {
                    te = te.getNext();
                    t = te.getNext();
                }

                // If we reach the end of the list, break out of the loop
                if (t == null || t.getNext() == null)
                    break;
            } while (t.getNext().getNext() != null);
        }

        // If the first node is marked for deletion, delete it
        if (firstNode.isDeleted()) {
            t = firstNode;
            firstNode = firstNode.getNext();
            t.setNext(null);
            size--;
        }

        // If the last node is marked for deletion, delete it
        if (lastNode.isDeleted()) {
            t = firstNode;
            while (t.getNext().getNext() != null) {
                t = t.getNext();
            }

            lastNode = t;
            size--;
        }

        // Reset the deleted count
        deleted = 0;
    }

        // Return the element that was marked for deletion
        return thingToReturn;
    } // end lazy 

    // remove (and return the data element)
    public AnyType remove(int index) {
        if (index > size)
            throw new IndexOutOfBoundsException("index is too big!");
        else if (index < 0)
            throw new IndexOutOfBoundsException("Have you lost your mind?");

        AnyType thingToReturn = null;

        if (index == 0) {
            thingToReturn = firstNode.getData();
            Node nodeToRemove = firstNode;
            firstNode = nodeToRemove.getNext();
            nodeToRemove.setNext(null);
            size--;
            return thingToReturn;
        }

        Node currentNode = firstNode;
        int currentIndex = 0;
        while (currentIndex < index-1) {
            currentNode = currentNode.getNext();
            currentIndex++;
        }
        Node nodeToRemove = currentNode.getNext();
        thingToReturn = nodeToRemove.getData();
        currentNode.setNext(nodeToRemove.getNext());
        if (nodeToRemove.getNext() == null)
            lastNode = currentNode;
        nodeToRemove.setNext(null);
        size--;
        return thingToReturn;
    }

    public MyIterator iterator() {

        return new MyIterator();

    }

    public class MyIterator {

        private Node currentNode;

        public MyIterator() {
            currentNode = MyLinkedList.this.firstNode;
        }

        public boolean hasNext() {
            if (currentNode == null)
                return false;
            return true;
        }

        public AnyType next() {
            while (currentNode!=null &&currentNode.isDeleted())
            {
                currentNode=currentNode.getNext();
            }

            AnyType valueToReturn = currentNode.getData();
            currentNode = currentNode.getNext();
            return valueToReturn;
        }

    } // end class MyIterator

    private class Node {

        private AnyType element;
        private Node nextNode;
        private boolean isDeleted;

        public Node(AnyType element) {
            this.element = element;
            this.nextNode = null;
            this.isDeleted=false;
        }

        public AnyType getData() {
            return this.element;
        }

        public Node getNext() {
            return this.nextNode;
        }

        public void setData(AnyType element) {
            this.element = element;
        }

        public void setNext(Node next) {
            this.nextNode = next;
        }

        public boolean isDeleted() {
            return isDeleted;
        }

        public void setDeleted(boolean deleted) {
            isDeleted = deleted;
        }
    } // end of class Node
} // end of MyLinkedList