package q1;

/**
 *
 * @author Alexander Clarke (A00887548)
 */
public class HouseHold {

    private final int id;
    private final int members;
    private final int income;
    
    /**
     * 
     * <p>Create a new HouseHold with a unique ID, number of members and
     * annual income.</p>
     */
    public HouseHold(int id, int members, int income){
        
        this.id = id;
        this.members = members;
        this.income = income;
        
    }

    /**
     * 
     * <p>Return the ID of the household.</p>
     */
    public int getId() {
        return id;
    }

    /**
     * 
     * <p>Return the number of members of the household.</p>
     */
    public int getMembers() {
        return members;
    }

    /**
     * 
     * <p>Return the annual income of the household.</p>
     */
    public int getIncome() {
        return income;
    }
    
    
    
}
