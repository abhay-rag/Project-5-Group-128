package covidvisualizer;

import java.util.Arrays;

public class SinglyLinkedListTest extends student.TestCase {
    
    private SinglyLinkedList<String> emptyListA;
    private SinglyLinkedList<String> emptyListB;
    private SinglyLinkedList<String> smallListA;
    private SinglyLinkedList<String> smallListB;
    private SinglyLinkedList<String> bigListA;
    private SinglyLinkedList<String> bigListB;
    private String nullObject;
    
    public void setUp() {
        emptyListA = new SinglyLinkedList<String>();
        emptyListB = new SinglyLinkedList<String>();

        smallListA = new SinglyLinkedList<String>();
        smallListB = new SinglyLinkedList<String>();

        smallListA.add("soccer");
        smallListA.add("swimming");
        smallListA.add("gymnastics");

        smallListB.add("soccer");
        smallListB.add("swimming");
        smallListB.add("gymnastics");

        bigListA = new SinglyLinkedList<String>();

        for (int i = 0; i < 100; i++) {
            bigListA.add("sport" + i);
        }

        bigListB = new SinglyLinkedList<String>();
        for (int i = 0; i < 100; i++) {
            bigListB.add("sport" + i);
        }

        // to be explicit
        nullObject = null;
    }
    
    public void testEqualsEmptyList() {
        assertEquals(emptyListA, emptyListA);
        assertEquals(emptyListA, emptyListB);
        assertFalse(emptyListA.equals(nullObject));
        assertFalse(emptyListA.equals("soccer"));
        assertFalse(emptyListA.equals(smallListA));
        assertFalse(smallListA.equals(emptyListA));
        emptyListB.add("jump roping");
        assertFalse(emptyListA.equals(emptyListB));
        smallListA.clear();
        assertEquals(emptyListA, smallListA);
    }
    
    public void testEqualsSmallList() {
        assertEquals(smallListA, smallListA);
        assertEquals(smallListA, smallListB);
        assertFalse(smallListA.equals(nullObject));
        assertFalse(smallListA.equals("soccer"));
        assertFalse(smallListA.equals(bigListA));
        assertFalse(smallListA.equals(emptyListA));
        smallListB.add("jump roping");
        assertFalse(smallListA.equals(smallListB));

        // Make smallListA and smallListB differ in
        // content, but have the same size
        smallListA.add("rope jumping");
        assertFalse(smallListA.equals(smallListB));

        // Replace the last element in smallListA
        // to make smallListA and smallListB equal again
        smallListA.remove("rope jumping");
        smallListA.add("jump roping");
        assertEquals(smallListA, smallListB);
    }
    
    public void testEqualsBigList() {
        assertEquals(bigListA, bigListA);
        assertEquals(bigListA, bigListB);
        assertFalse(bigListA.equals(nullObject));
        assertFalse(bigListA.equals("soccer"));
        assertFalse(bigListA.equals(smallListA));
        assertFalse(bigListA.equals(emptyListA));
        bigListB.add("jump roping");
        assertFalse(bigListA.equals(bigListB));

        // Same content, same size, but reversed
        bigListB.clear();
        assertFalse(bigListA.equals(bigListB));
        for (int i = 100; i > 0; i--) {
            bigListB.add("sport" + i);
        }
        assertFalse(bigListA.equals(bigListB));

        // one a subset of the other but with dups
        bigListB.clear();
        assertFalse(bigListA.equals(bigListB));
        for (int i = 0; i < 50; i++) {
            bigListB.add("sport" + i);
        }
        for (int i = 0; i < 50; i++) {
            bigListB.add("sport" + i);
        }
        assertFalse(bigListA.equals(bigListB));

        // make them equal again
        bigListB.clear();
        assertFalse(bigListA.equals(bigListB));
        for (int i = 0; i < 100; i++) {
            bigListB.add("sport" + i);
        }
        assertEquals(bigListA, bigListB);

    }
    
    public void testToArrayEmpty() {

        Object[] emptyArray = {};
        assertTrue(Arrays.equals(emptyListA.toArray(), emptyArray));
        assertTrue(Arrays.equals(emptyListA.toArray(), emptyListB.toArray()));
        assertFalse(Arrays.equals(emptyListA.toArray(), smallListB.toArray()));
        Object[] oneItemArray = { "one thing" };
        emptyListA.add("one thing");
        assertTrue(Arrays.equals(emptyListA.toArray(), oneItemArray));

    }
    
    public void testToArrayContents() {

        Object[] origArray = { "soccer", "swimming", "gymnastics" };
        assertTrue(Arrays.equals(smallListA.toArray(), origArray));
        assertTrue(Arrays.equals(emptyListA.toArray(), emptyListB.toArray()));
        assertFalse(Arrays.equals(smallListA.toArray(), bigListB.toArray()));

    }
    
    public void testSize() {
        assertEquals(0, emptyListA.size());
        assertEquals(0, emptyListB.size());
        assertEquals(3, smallListA.size());
        assertEquals(3, smallListB.size());
        assertEquals(100, bigListA.size());
        assertEquals(100, bigListB.size());
        emptyListA.add("soccer");
        assertEquals(1, emptyListA.size());
        emptyListA.remove(0);
        assertFalse(emptyListA.contains("soccer"));
        assertEquals(0, emptyListA.size());
        emptyListA.add(0, "soccer");
        assertEquals(1, emptyListA.size());
        emptyListA.remove("soccer");
        assertEquals(0, emptyListA.size());

        bigListA.clear();
        assertEquals(0, bigListA.size());
    }
    
    public void testAddToEnd() {
        Exception e = null;

        try {
            emptyListA.add(null);
        }
        catch (IllegalArgumentException exception) {
            e = exception;
        }

        assertNotNull(e);

        assertEquals(3, smallListA.size());
        smallListA.add("tennis");
        assertEquals("tennis", smallListA.get(3));
        assertEquals(4, smallListA.size());

        assertEquals(0, emptyListA.size());
        emptyListA.add("tennis");
        assertEquals("tennis", emptyListA.get(0));
        assertEquals(1, emptyListA.size());
    }
    
    public void testIsEmpty() {

        assertTrue(emptyListA.isEmpty());
        emptyListA.add("tennis");
        assertFalse(emptyListA.isEmpty());
        emptyListA.remove("tennis");
        assertTrue(emptyListA.isEmpty());

        assertFalse(bigListB.isEmpty());
    }
    
    public void testGet() {
        assertEquals("gymnastics", smallListB.get(2));

        Exception e = null;

        try {
            smallListB.get(3);
        }
        catch (IndexOutOfBoundsException exception) {
            e = exception;
        }

        assertNotNull(e);

        emptyListA.add("soccer");
        assertEquals("soccer", emptyListA.get(0));
        emptyListA.add("basketball");
        emptyListA.add("tennis");
        assertEquals("basketball", emptyListA.get(1));
        assertEquals("tennis", emptyListA.get(2));
    }
    
    public void testClear() {

        assertEquals(0, emptyListA.size());
        emptyListA.clear();
        assertEquals(0, emptyListA.size());

        assertEquals(3, smallListB.size());
        assertEquals("gymnastics", smallListB.get(2));
        smallListB.clear();

        Exception e = null;

        try {
            smallListB.get(2);
            smallListB.get(1);
            smallListB.get(0);
        }
        catch (IndexOutOfBoundsException exception) {
            e = exception;
        }
    }
    
    public void testToString() {
        assertEquals("{}", emptyListA.toString());
        assertEquals("{soccer, swimming, gymnastics}", smallListA.toString());
        smallListA.add("basketball");
        assertEquals("{soccer, swimming, gymnastics, basketball}", smallListA
            .toString());
        smallListA.remove(1);
        assertEquals("{soccer, gymnastics, basketball}", smallListA.toString());
        smallListA.add(2, "baseball");
        assertEquals("baseball", smallListA.get(2));
        assertEquals("{soccer, gymnastics, baseball, basketball}", smallListA
            .toString());
        smallListA.clear();
        assertEquals("{}", smallListA.toString());
    }
    
    public void testRemoveByObject() {

        assertTrue(smallListA.remove("soccer"));
        assertFalse(emptyListA.remove("basketball"));

        assertTrue(smallListB.remove("gymnastics"));
        assertEquals(2, smallListB.size());
        assertTrue(smallListB.remove("swimming"));
        assertEquals(1, smallListB.size());
        assertFalse(smallListB.remove("tennis"));

        assertFalse(bigListB.remove("tennis"));
        
        assertTrue(bigListA.remove("sport42"));
    }
    
    public void testRemoveByIndex() {
        Exception e = null;

        try {
            smallListB.remove(-1);
        }
        catch (IndexOutOfBoundsException exception) {
            e = exception;
        }
        
        assertNotNull(e);
        
        e = null;
        
        try {
            smallListB.remove(4);
        }
        catch (IndexOutOfBoundsException exception) {
            e = exception;
        }
        
        assertNotNull(e);
        e = null;
        
        try {
            emptyListB.remove(3);
        }
        catch (IndexOutOfBoundsException exception) {
            e = exception;
        }

        assertNotNull(e);

        emptyListB.add("baseball");
        assertTrue(emptyListB.remove(0));
        assertTrue(emptyListB.isEmpty());
        assertTrue(smallListB.remove(1));
        assertTrue(bigListB.remove(43));

    }
    
    public void testLastIndexOf() {
        assertEquals(-1, emptyListB.lastIndexOf("baseball"));
        assertEquals(0, smallListA.lastIndexOf("soccer"));
        assertEquals(2, smallListA.lastIndexOf("gymnastics"));
        smallListA.add("gymnastics");
        smallListA.add("soccer");
        assertEquals(3, smallListA.lastIndexOf("gymnastics"));
        assertEquals(4, smallListA.lastIndexOf("soccer"));
        assertEquals(-1, smallListA.lastIndexOf("tennis"));
    }
    
    public void testContains() {
        assertFalse(emptyListA.contains("baseball"));
        
        assertTrue(smallListB.contains("swimming"));
        smallListB.remove(1);
        assertFalse(smallListB.contains("swimming"));
        
        smallListB.remove("gymnastics");
        assertFalse(smallListB.contains("gymnastics"));
        
        assertFalse(smallListB.contains("tennis"));
        smallListB.add("tennis");
        assertTrue(smallListB.contains("tennis"));
        
        assertFalse(smallListB.contains("bowling"));
        smallListB.add(2, "bowling");
        assertTrue(smallListB.contains("bowling"));
        
    }
    
    public void testAddByIndex() {
        Exception e = null;

        try {
            emptyListA.add(-1, null);
        }
        catch (IllegalArgumentException exception) {
            e = exception;
        }

        assertNotNull(e);

        e = null;

        try {
            emptyListA.add(-1, "football");
            smallListB.add(-1, "soccer");
        }
        catch (IndexOutOfBoundsException exception) {
            e = exception;
        }
        
        assertNotNull(e);
        
        e = null;
        
        try {
            smallListA.add(10, "tennis");
        }
        catch (IndexOutOfBoundsException exception) {
            e = exception;
        }

        assertNotNull(e);

        emptyListA.add(0, "football");
        assertEquals(1, emptyListA.size());
        assertEquals("football", emptyListA.get(0));

        assertEquals(3, smallListB.size());
        smallListB.add(0, "tennis");
        assertEquals("{tennis, soccer, swimming, gymnastics}", smallListB
            .toString());
        assertEquals(4, smallListB.size());
        assertEquals("tennis", smallListB.get(0));

        assertEquals(3, smallListA.size());
        smallListA.add(2, "tennis");
        assertEquals("{soccer, swimming, tennis, gymnastics}", smallListA
            .toString());
    }
    
    public void testIterator() {
        
    }

}
