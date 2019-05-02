/**
*This class represents a TA object
* @author Hussain Mumtaz
* @version 1.00
*/
public class TA extends UndergradStudent {
    protected double piazzaScore;
    protected double recitationScore;
    protected double officeHoursScore;
    /**
    *gets and returns TA's piazza Score
    *@return piazzaScore
    */
    public double getPiazzaScore() {
        return piazzaScore;
    }
    /**
    *gets and returns TA's recitation Score
    *@return recitationScore
    */
    public double getRecitationScore() {
        return recitationScore;
    }
    /**
    *gets and returns TA's Office Hours Score
    *@return officeHoursScore
    */
    public double getOfficeHoursScore() {
        return officeHoursScore;
    }
    /**
    *Sets a new value for piazzaScore
    *@param piazzaScore the value to set for piazzaScore, this value
    *     is bounded [0,1]
    */
    public void setPiazzaScore(double piazzaScore) {
        this.piazzaScore = piazzaScore;
        if (piazzaScore > 1) {
            this.piazzaScore = 1;
        } else if (piazzaScore < 0) {
            this.piazzaScore = 0;
        }
    }
    /**
    *Sets a new value for recitationScore
    *@param recitationScore the value to set for recitationScore, this value
    *     is bounded [0,1]
    */
    public void setRecitationScore(double recitationScore) {
        this.recitationScore = recitationScore;
        if (recitationScore > 1) {
            this.recitationScore = 1;
        } else if (recitationScore < 0) {
            this.recitationScore = 0;
        }
    }
    /**
    *Sets a new value for officeHoursScore
    *@param officeHoursScore the value to set for officeHoursScore, this value
    *     is bounded [0,1]
    */
    public void setOfficeHoursScore(double officeHoursScore) {
        this.officeHoursScore = officeHoursScore;
        if (officeHoursScore > 1) {
            this.officeHoursScore = 1;
        } else if (officeHoursScore < 0) {
            this.officeHoursScore = 0;
        }
    }
    /**
    *creates a TA object
    * @param first first name of TA
    * @param last last name of TA
    * @param user username of TA
    * @param piazzaScore initial piazzaScore of TA
    * @param recitationScore initial recitationScore of TA
    * @param officeHoursScore initial officeHoursScore of TA
    */
    TA(String first, String last, String user, double piazzaScore,
        double recitationScore, double officeHoursScore) {
        super(first, last, user);
        setPiazzaScore(piazzaScore);
        setRecitationScore(recitationScore);
        setOfficeHoursScore(officeHoursScore);
    }
}
