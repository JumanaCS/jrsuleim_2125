
import org.junit.Test;
import static org.junit.Assert.*;

//test put, get, isEmpty, makeEmpty 

public class MapTest {
    //Test of put method, of class Map
    @Test
    public void testPut() {
        System.out.println("Put");

        Map<Integer, String> map = new Map<>(); // Create a new map
         // Add some elements to the map
        map.put(1, "a");
        map.put(2, "b");
        map.put(3, "c");
        // Check that the values were correctly stored
        assertEquals("a", map.get(1));
        assertEquals("b", map.get(2));
        assertEquals("c", map.get(3));
    }

    //Test of get method, of class Map.
    @Test
    public void testGet() {
        System.out.println("Get");
        // Create a new map
        Map instance = new Map(); 
         // Add some elements to the map
        instance.put(1, "a");
        instance.put(2, "b");
        instance.put(3, "c");
         // Check that the values were correctly stored
        assertEquals("a", instance.get(1));
        assertEquals("b", instance.get(2));
        assertEquals("c", instance.get(3));
        // Check that null is returned for non-existent keys
        assertNull(instance.get(4));

    }

    //Test of isEmpty method, of class Map.
    @Test
    public void testIsEmpty() {
        System.out.println("IsEmpty");

        Map instance = new Map();
        assertTrue(instance.isEmpty());
        instance.put(1, "a");
        instance.put(2, "b");
        instance.put(3, "c");
        // Check that the map is not empty
        assertFalse(instance.isEmpty());

    }

    //Test of makeEmpty method, of class Map.
    @Test
    public void testMakeEmpty() {
        System.out.println("MakeEmpty");
        
        Map instance = new Map();
        // Check that the map is initially empty
        assertTrue(instance.isEmpty());
        instance.put(1, "a");
        instance.put(2, "b");
        instance.put(3, "c");
        // Check that the map is not empty
        assertFalse(instance.isEmpty());
        // Clear the map
        instance.makeEmpty();
        // Check that the map is empty
        assertTrue(instance.isEmpty());
        
    }

}
