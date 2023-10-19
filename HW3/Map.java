
public class Map<KeyType, ValueType> {

    // The underlying hash table that stores the map's entries
    private SeparateChainingHashTable<MapEntry<KeyType, ValueType>> elements;
    // The number of entries in the map
    private int size;

    public Map() {
        // Create a new hash table with a default initial capacity
        elements = new SeparateChainingHashTable<>();
        // Initialize the size to 0
        size = 0;
    }

    public void put(KeyType key, ValueType val) { //come back
        /* 
        MapEntry<KeyType, ValueType> entry = elements.get(new MapEntry<>(key, null));
        if(entry != null){
            elements.insert(new MapEntry<>(key, )); 
        }
        else {
            
        }
        */ // Look for an existing entry with the given key
        MapEntry<KeyType, ValueType> entry = elements.get(new MapEntry<>(key, null));
         // Update the value for the existing entry
        if (entry != null) {
            entry.value = val;
        } else {
             // Add a new entry to the hash table
            elements.insert(new MapEntry<>(key, val));
            size++;
        }
    }

    public ValueType get(KeyType key) { 
        MapEntry<KeyType, ValueType> entry = elements.get(new MapEntry<>(key, null));
        if (entry == null) {  // Return null if no entry was found
            return null;
        }
        // Return the value from the found entry
        return entry.value;
    }

    public boolean isEmpty() { // asserttrue test  
        return size == 0;
    }

    public void makeEmpty() { // use isEmpty for testing
        elements.makeEmpty(); // Clear the hash table
        size = 0;
    }

    private static class MapEntry<KeyType, ValueType> { 
        // The key and value for this entry
        private KeyType key; 
        private ValueType value;

        public MapEntry(KeyType key, ValueType value) { // construct
            this.key = key;
            this.value = value;
        }

        // not finished come bac ck
        // hascode, equals and toString here
        // Override the hashCode, equals, and toString methods
        // to make it easier to search for entries in the hash table

        @Override
        public int hashCode() {
            // Use the key's hash code as this entry's hash code
            return key.hashCode();
        }

        @Override
        public boolean equals(Object o) { 
             // Compare the keys of this entry and the given object
            return key.equals(((MapEntry) o).key);
        }

        @Override
        public String toString() {  // Return a string representation of this entry
            return key + "=" + value;
        }

    } // end class MapEntry

} // end class Map
