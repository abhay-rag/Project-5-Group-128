package covidvisualizer;

import java.util.Iterator;

public class State {

    private String name;
    private SinglyLinkedList<Race> races;
    public static final int NUM_STATES = 6;
    
    public State(String n, SinglyLinkedList<Race> r) {
        name = n;
        races = r;
    }
    
    public String getName() {
        return name;
    }
    
    public SinglyLinkedList<Race> getRaces(){
        return races;
    }
    
    public void setRaces(SinglyLinkedList<Race> sortedRaces) {
        races = sortedRaces;
    }
    
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        
        Iterator<Race> raceIterator = races.iterator();
        
        while (raceIterator.hasNext()) {
            Race race = raceIterator.next();
            stringBuilder.append(race.getName() + ": ");
            stringBuilder.append(race.getCases() + " cases, ");
            if (race.calculateCFR() >= 0) {
                if (race.calculateCFR() == Math.floor(race.calculateCFR())) {
                    stringBuilder.append((int) race.calculateCFR() + "% CFR");
                }
                else 
                {
                    stringBuilder.append(race.calculateCFR() + "% CFR");
                }
            }
            
            else 
            {
                stringBuilder.append("-1% CFR");
            }

            stringBuilder.append("\n");
        }
        
        return stringBuilder.toString();
    }
}
