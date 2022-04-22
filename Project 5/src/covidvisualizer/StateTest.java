package covidvisualizer;

public class StateTest extends student.TestCase {

    private State newJersey;
    private State newYork;

    public void setUp() {
        SinglyLinkedList<Race> njRaces = new SinglyLinkedList<Race>();
        njRaces.add(new Race("latino", 43432, 234));
        njRaces.add(0, new Race("pacific islander", 24332, 476));
        njRaces.add(new Race("asian", 92394, 293));
        njRaces.add(new Race("white", 82920, 3932));
        njRaces.add(new Race("other", 28202, 2828));

        newJersey = new State("New Jersey", njRaces);
    }


    public void testGetName() {
        assertEquals("New Jersey", newJersey.getName());
        assertEquals("pacific islander", newJersey.getRaces().get(0).getName());
        assertEquals("asian", newJersey.getRaces().get(2).getName());
        assertEquals("latino", newJersey.getRaces().get(1).getName());
    }
    
}
