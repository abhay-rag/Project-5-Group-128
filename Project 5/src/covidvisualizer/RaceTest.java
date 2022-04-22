package covidvisualizer;

public class RaceTest extends student.TestCase {
    
    private Race latino;
    private Race invalidRace;
    private Race pacificIslander;
    
    public void setUp() {
        latino = new Race("latino", 299238, 39983);
        invalidRace = new Race("invalid", 38929, -1);
        pacificIslander = new Race("pacific islander", 598476, 79966);
    }
    
    public void testGetName() {
        assertEquals("latino", latino.getName());
        assertEquals("invalid", invalidRace.getName());
        assertEquals("pacific islander", pacificIslander.getName());
    }
    
    public void testGetCases() {
        assertEquals(299238, latino.getCases());
        assertEquals(38929, invalidRace.getCases());
        assertEquals(598476, pacificIslander.getCases());
    }
    
    public void testGetDeaths() {
        assertEquals(39983, latino.getDeaths());
        assertEquals(-1,  invalidRace.getDeaths());
        assertEquals(79966, pacificIslander.getDeaths());
    }
    
    public void testCalculateCFR() {
        assertEquals(13.4, latino.calculateCFR(), 0.1);
        assertEquals(-1, invalidRace.calculateCFR(), 0.1);
        assertEquals(13.4, pacificIslander.calculateCFR(), 0.1);
    }
    
    public void testCompareToByName() {
        assertTrue(latino.compareToByName(invalidRace) > 0);
        assertTrue(invalidRace.compareToByName(latino) < 0);
        
        assertTrue(latino.compareToByName(pacificIslander) < 0);
        assertTrue(pacificIslander.compareToByName(latino) > 0);
    }
    
    public void testCompareToByCFR() {
        assertEquals(1, latino.compareToByCFR(invalidRace));
        assertEquals(-1, invalidRace.compareToByCFR(latino));
        
        assertTrue(latino.calculateCFR() == pacificIslander.calculateCFR());
        assertTrue(latino.compareToByCFR(pacificIslander) > 0);
        assertTrue(pacificIslander.compareToByCFR(latino) < 0);
    }
}
