package covidvisualizer;

import java.io.FileNotFoundException;

public class Input {

    public static void main(String[] args) throws FileNotFoundException {

        if (args.length == 1) {
            CovidDataReader dataReader = new CovidDataReader(args[0]);
        }
        else {
            CovidDataReader dataReader = new CovidDataReader(
                "Cases_and_Deaths_by_race_RANDOM_NUMBERS.csv");
        }
        
    }

}
