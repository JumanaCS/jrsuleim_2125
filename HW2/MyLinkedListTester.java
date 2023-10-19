import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

class MyLinkedListTester{


    @Test
    void sizeTest() {
        MyLinkedList<Integer> myList = new MyLinkedList<>(); // create linked list
        myList.add(1);
        myList.add(2);
        myList.add(3);
        myList.add(4);
        myList.add(5);
        myList.add(6);

        assertEquals(6,myList.size()) ;
        myList.lazyDeletion(2);
        myList.lazyDeletion(4);
        // the size of list remains the same after lazy deletion 
        assertEquals(6,myList.size());
        myList.lazyDeletion(1);
        // size changes cause half of the list was marked for deletion 
        //as 3 nodes are marked as deleted, the size becomes 3
        assertEquals(3,myList.size());
    }

    @Test
    void iteratorTest()
    {
        MyLinkedList<Integer> myList = new MyLinkedList<>(); // create linked list
        myList.add(0);
        myList.add(1);
        myList.add(2);
        myList.add(3);
        myList.add(4);
        myList.add(5);
        myList.add(6);

        MyLinkedList.MyIterator iterator = myList.iterator();
        assertEquals(0,iterator.next());
        assertEquals(1,iterator.next());
        assertEquals(2,iterator.next());
        assertEquals(3,iterator.next());
        assertEquals(4,iterator.next());
        assertEquals(5,iterator.next());
        assertEquals(6,iterator.next());

        //lazy deletion
        myList.lazyDeletion(0);
        myList.lazyDeletion(2);
        myList.lazyDeletion(4);
        iterator = myList.iterator();
        //1 is now at inx 0
        assertEquals(1,iterator.next());
        assertEquals(3,iterator.next());
    }



    @Test
    void getTest() {

        MyLinkedList<Integer> myList = new MyLinkedList<>(); // create linked list
        myList.add(1);
        myList.add(2);
        myList.add(3);
        myList.add(4);
        myList.add(5);
        myList.add(6);
        myList.add(7);
        myList.add(8);
      
        assertEquals(4,myList.get(3));

        myList.lazyDeletion(2);
        myList.lazyDeletion(2);

        assertEquals(6,myList.get(3));



    }

}
