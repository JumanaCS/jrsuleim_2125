public class MyDoubleLinkedList<AnyType> {

    // instance variables
    private int size;
    private Node firstNode;
    private Node lastNode;
    private int deleted; // how many are deleted 

    public MyDoubleLinkedList() { // con
        this.size = 0;
        this.deleted=0; // added deleted
        this.firstNode = null;
        this.lastNode = null;
    }

    public int size() {
        return this.size;
    }

    public void add(AnyType element) {

        Node newNode = new Node(element);

        if (size == 0){
            this.firstNode = newNode;
            this.lastNode = newNode;
        } 

        else {
            this.lastNode.setNext(newNode);
            newNode.setPrevNode(lastNode); // come back 
            newNode.setNext(null);
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
            firstNode.setPrevNode(newNode);
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

        /*newNode.setNext(currentNode.getNext());
        currentNode.setNext(newNode);*/
        Node pre=currentNode;
        Node next=currentNode.getNext();
        newNode.setNext(next);
        next.setPrevNode(newNode);
        pre.setNext(newNode);

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

     public AnyType lazyDeletion(int index){
        if (index > size) // exceptions 
            throw new IndexOutOfBoundsException("index is too big!");
        else if (index < 0)
            throw new IndexOutOfBoundsException("Have you lost your mind?");
        AnyType thingToReturn = null;

        Node t = firstNode; // temporary node 
        int i=0;

        while (t!= null){ // needs to have something in it to enter 
            if(i == index){
                if(t.isDeleted()){

                    t=t.getNext();
                    continue;
                }

                t.setDeleted(true);
                deleted++; // element has been marked for deletion 
                thingToReturn=t.getData();
                break;
            }

            else{
                i++;
                t=t.getNext();
            }
        }


        if(deleted==size/2){
        //delete intermidate nodes
            if(size>2){
                t=firstNode.getNext();
                Node te = firstNode;

                do{
                    if(t.isDeleted()){
                        te.setNext(t.getNext());
                        t.setNext(null);
                        size--;
                        t=te.getNext();
                    }

                    else{
                        te = te.getNext();
                        t = te.getNext();
                    }

                    if(t==null || t.getNext()==null)
                        break;
                } 

                while (t.getNext().getNext()!=null);
            }

            /*if(lastNode.isDeleted()){
                t=firstNode;
                while (t.getNext().getNext()!=null){
                    t=t.getNext();
                }

                lastNode=t;
                size--;
            }

            deleted=0;
        }*/
            if (firstNode.isDeleted()){ // for double 
                t=firstNode;
                firstNode=firstNode.getNext();
                t.setNext(null);
                size--;
            }

            if(lastNode.isDeleted())
            {
                lastNode=lastNode.getPrevNode();
                lastNode.setNext(null);

                size--;
            }

            deleted=0;
        }

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
        private Node lastCurrentNode; // last node 

        public MyIterator() { // con
            currentNode = MyDoubleLinkedList.this.firstNode;
            lastCurrentNode=MyDoubleLinkedList.this.lastNode;
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
        // has prior and get prior here
        public boolean hasPrior() 
        {
            if (lastCurrentNode == null)
                return false;
            return true;
        }

        public AnyType getPrior() 
        {
            while (lastCurrentNode!=null &&lastCurrentNode.isDeleted())
            {
                lastCurrentNode=lastCurrentNode.getPrevNode();
            }

            AnyType valueToReturn = lastCurrentNode.getData();
            lastCurrentNode = lastCurrentNode.getPrevNode();
            return valueToReturn;

        }


    } // end class MyIterator

    private class Node {

        private AnyType element;
        private Node nextNode;
        private Node prevNode; // prev node
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

        // add pre nodes
        public Node getPrevNode() { 
            return prevNode;
        }

        public void setPrevNode(Node prevNode) {
            this.prevNode = prevNode;
        }
    } // end of class Node





} // end of MyLinkedList