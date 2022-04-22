package covidvisualizer;

import java.text.DecimalFormat;

public class Race {

    private String name;
    private int cases;
    private int deaths;
    public static final int NUM_RACES = 5;

    public Race(String n, int c, int d) {
        name = n;
        cases = c;
        deaths = d;
    }


    public String getName() {
        return name;
    }


    public int getCases() {
        return cases;
    }


    public int getDeaths() {
        return deaths;
    }


    public double calculateCFR() {
        
        if (deaths == -1 || cases == -1) {
            return -1;
        }

        DecimalFormat decimalFormat = new DecimalFormat(".#");
        String formattedCFR = decimalFormat.format(((Double.valueOf(deaths)
            / Double.valueOf(cases)) * 100));
        
        return (Double.valueOf(formattedCFR));
    }


    public int compareToByCFR(Race otherRace) {
        if (this.calculateCFR() > otherRace.calculateCFR()) {
            return 1;
        }
        else if (this.calculateCFR() < otherRace.calculateCFR()) {
            return -1;
        }
        else {
            return -1 * this.compareToByName(otherRace);
        }

    }


    public int compareToByName(Race otherRace) {
        return (this.getName().compareTo(otherRace.getName()));
    }

}
