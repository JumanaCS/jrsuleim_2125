public class MyLinkedList<AnyType> {

    // instance variables
    private int size;
    private Node firstNode;
    private Node lastNode;

    public MyLinkedList() {
        this.size = 0;
        this.firstNode = null;
        this.lastNode = null;
    }

    public int size() {
        return this.size;
    }

    public void add(AnyType element) {

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

        if (size == 0) {
            this.firstNode = newNode;
            this.lastNode = newNode;
        } else {
            this.lastNode.setNext(newNode);
            this.lastNode = newNode;
        }

        // increment the size
        size++;

    }

    // insertion style add
    public void add(AnyType element, int index) {
        if (index > size)
            throw new IndexOutOfBoundsException("index is too big!");
        else if (index < 0) 
            throw new IndexOutOfBoundsException("Have you lost your mind?");

        if (index == size - 1) {
            add(element);
            return;
        }

        Node newNode = new Node(element);

        if (index == 0) {
            newNode.setNext(firstNode);
            firstNode = newNode;
            if (size == 0)
                lastNode = newNode;
            size++;
            return;
        }
    
        Node currentNode = firstNode;
        int currentIndex = 0;   
        while (currentIndex < index-1) {
            currentNode = currentNode.getNext();
            currentIndex++;
        }
        newNode.setNext(currentNode.getNext());
        currentNode.setNext(newNode);
        size++;
    }

    public AnyType get( int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException("index is too big!");
        else if (index < 0) 
            throw new IndexOutOfBoundsException("Have you lost your mind?");
    
        Node currentNode = firstNode;
        int currentIndex = 0;   
        while (currentIndex < index) {
            currentNode = currentNode.getNext();
            currentIndex++;
        }
        return currentNode.getData();
    }

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
            AnyType valueToReturn = currentNode.getData();
            currentNode = currentNode.getNext();
            return valueToReturn;
        }

    } // end class MyIterator

    private class Node {

        private AnyType element;
        private Node    nextNode;

        public Node(AnyType element) {
            this.element = element;
            this.nextNode = null;
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

    } // end of class Node


} // end of MyLinkedList
