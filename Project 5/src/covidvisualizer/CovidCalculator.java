package covidvisualizer;

import java.io.FileNotFoundException;


public class CovidCalculator {

    private State[] allStates;

    public CovidCalculator(State[] states) {
        allStates = states;
    }


    public State[] getAllStates() {
        return allStates;
    }


    public void sortByAlphabet(State state) {
        SinglyLinkedList<Race> stateRaces = state.getRaces();
        Race[] races = new Race[Race.NUM_RACES];

        for (int i = 0; i < races.length; i++) {
            races[i] = stateRaces.get(i);
        }

        int first = 0;
        int last = races.length - 1;
        for (int i = first; i <= last; i++) {
            insertInOrderAlpha(races[i], races, first, i - 1);
        }
        
        stateRaces.clear();
        
        for (int i = 0; i < races.length; i++) {
            stateRaces.add(races[i]);
        }
        
        state.setRaces(stateRaces);
    }


    private void insertInOrderAlpha(
        Race race,
        Race[] races,
        int begin,
        int end) {
        
        int index = end;
        while ((index >= begin) && (race.compareToByName(races[index])) < 0) {
            races[index + 1] = races[index];
            index--;
        }
        
        races[index + 1] = race;
    }
    
    public void sortByCFR(State state) {
        SinglyLinkedList<Race> stateRaces = state.getRaces();
        Race[] races = new Race[Race.NUM_RACES];

        for (int i = 0; i < races.length; i++) {
            races[i] = stateRaces.get(i);
        }

        int first = 0;
        int last = races.length - 1;
        for (int i = first; i <= last; i++) {
            insertInOrderCFR(races[i], races, first, i - 1);
        }
        
        stateRaces.clear();
        
        for (int i = 0; i < races.length; i++) {
            stateRaces.add(races[i]);
        }
        
        state.setRaces(stateRaces);
    }
    
    private void insertInOrderCFR(
        Race race,
        Race[] races,
        int begin,
        int end) {
        
        int index = end;
        while ((index >= begin) && (race.compareToByCFR(races[index])) >= 0) {
            races[index + 1] = races[index];
            index--;
        }
        
        races[index + 1] = race;
    }

    public void output() throws FileNotFoundException {
        
        for (int i = 0; i < State.NUM_STATES; i++) {       
            StringBuilder output = new StringBuilder();
            output.append(allStates[i].getName() + "\n");
            sortByAlphabet(allStates[i]);
            output.append(allStates[i].toString());
            output.append("=====\n");
            sortByCFR(allStates[i]);
            output.append(allStates[i].toString());
            output.append("=====");
            System.out.println(output.toString());
        }
        
        
        
    }
}
