package covidvisualizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CovidDataReader {

    private State[] states;
    
    public CovidDataReader(String inputFile) throws FileNotFoundException {
        states = new State[State.NUM_STATES];
        states = readDataFile(inputFile);
        CovidCalculator covidCalculator = new CovidCalculator(states);
        
        covidCalculator.output();
    }
    
    public State[] readDataFile(String stateFile) throws FileNotFoundException {
        State[] listOfStates = new State[State.NUM_STATES];
        int stateIndex = 0;
        Scanner file = new Scanner(new File(stateFile));
        String read = file.nextLine();
        String[] raceNames = new String[Race.NUM_RACES];
        Scanner currLine = new Scanner(read).useDelimiter(",\\s*");
        currLine.next();
        
        for (int i = 0; i < Race.NUM_RACES; i++) {
            raceNames[i] = currLine.next().split("_")[1].toLowerCase();
        }
        
        while (file.hasNextLine()) {
            read = file.nextLine();
            currLine = new Scanner(read).useDelimiter(",\\s*");
            String stateName = currLine.next();
            int[] cases = new int[Race.NUM_RACES];
            
            for (int i = 0; i < Race.NUM_RACES; i++) {
                try {
                    cases[i] = Integer.valueOf(currLine.next());
                }
                catch (Exception exception) {
                    cases[i] = -1;
                }
                
            }
            
            int[] deaths = new int[Race.NUM_RACES];
            
            for (int i = 0; i < Race.NUM_RACES; i++) {
                
                try {
                    deaths[i] = Integer.valueOf(currLine.next());
                }
                catch (Exception exception) {
                    deaths[i] = -1;
                }
            }
            
            currLine.close();
            
            SinglyLinkedList<Race> races = new SinglyLinkedList<Race>();
            
            for (int i = 0; i < Race.NUM_RACES; i++) {
                races.add(new Race(raceNames[i], cases[i], deaths[i]));
            }
            
            listOfStates[stateIndex] = new State(stateName, races);
            stateIndex++;
        }
        
        return listOfStates;
    }
}
