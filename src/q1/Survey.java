package q1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * <p>This program reads household income survey data from a file, and prints it
 * out in tabular format.</p>
 *
 * @author Alexander Clarke (A00887548)
 * @version 1.0
 */
public class Survey {

    /**
     * <p>ArrayList that stores each HouseHold object read in from the file.</p>
     *
     */
    public static final ArrayList<HouseHold> HOUSEHOLDS = new ArrayList<>();
    
    /**
     * <p>This array stores low income thresholds for households depending
     * on their number of members. The index of each element represents
     * the number of members - 1, e.g. index 4 represents the low income
     * threshold for a household with 5 members.</p>
     *
     */
    public static final int[] LOW_INCOMES =
    {22229, 27674, 34022, 41307, 46850, 52838, 58827};
    
    /**
     * <p>This represents the additional income per member 
     * added to the low income threshold for a household 
     * exceeding 7 members.</p>
     *
     */
    public static final int ADDITIONAL_INCOME_THRESHOLD = 5989;
    
    /**
     * <p>This is the main method (entry point) that gets called by the JVM.</p>
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        
        new Survey().run();

    }

    public void run() {

        try {

            final File surveyFile = new File(this.getClass()
                    .getResource("survey.txt").toURI());
            
            try (FileReader fileReader = new FileReader(surveyFile);
                 BufferedReader buffer = new BufferedReader(fileReader)) {

                while (buffer.ready()) {

                    String line = buffer.readLine();
                    HouseHold house = createHouseHold(line.split(" "));
                    if (house == null) {
                        System.out.println("An error occurred reading the line: "
                                + line);
                        System.out.println("Program terminating.");
                        System.exit(0);
                    } else {
                        HOUSEHOLDS.add(house);
                    }

                }
                
            }

        } catch (URISyntaxException ex) {
            System.out.println("There was an error reading the survey file.");
            System.out.println("Program terminating.");
            System.exit(0);
        } catch (FileNotFoundException ex2) {
            System.out.println("Survey file does not exist!");
            System.out.println("Program terminating.");
            System.exit(0);
        } catch (IOException ex3) {
            System.out.println("An unknown I/O error occurred.");
            System.out.println("Program terminating.");
            System.exit(0);
        }

        final int[] incomes = new int[HOUSEHOLDS.size()];
        
        System.out.print(String.format("%-6s", "-ID-"));
        System.out.print(String.format("%-11s", "-Members-"));
        System.out.print(String.format("%-11s", "-Income-"));
        System.out.println();

        for(HouseHold house : HOUSEHOLDS){

            incomes[HOUSEHOLDS.indexOf(house)] = house.getIncome();
            System.out.print(String.format("%-6s", house.getId()));
            System.out.print(String.format("%-11s", house.getMembers()));
            System.out.print(String.format("%-11s", house.getIncome()));
            System.out.println();

        }
        
        final int average = averageIncome(incomes);
        
        System.out.print("\nAverage household income: ");
        System.out.print(average + "\n");
        
        System.out.println("\nHouses that exceed average income:");
        for(HouseHold house : HOUSEHOLDS){
            
            if(house.getIncome() > average)
                System.out.println(" - #" + house.getId());
            
        }
        
        System.out.println("\nLow income households:");
        
        for

    }

    public HouseHold createHouseHold(String[] array) {

        try {

            int id = Integer.parseInt(array[0]);
            int members = Integer.parseInt(array[1]);
            int income = Integer.parseInt(array[2]);

            return new HouseHold(id, members, income);

        } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
            return null;
        }

    }
    
    public int averageIncome(int[] incomes){
        
        int sum = 0;
        for(int income : incomes)
            sum += income;
        
        return sum / incomes.length;
        
    }
    
}
