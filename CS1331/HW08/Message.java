import java.util.Arrays;
import java.util.ArrayList;
import java.util.Date;
/**
* Represents a Message object.
*
* @author Hussain Mumtaz
* @version 1.0
*/
public class Message implements Comparable<Message> {
    private Person sender;
    private ArrayList<Person> recipients;
    private String subject;
    private String body;
    private Date dateTime;
    /**
    * Constructs a Message Object
    *
    * @param sender Person who sent the message
    * @param recipients People who recieved the message
    * @param subject message description
    * @param dateTime date and time message was recieved
    * @param body Message body
    */
    public Message(Person sender, ArrayList<Person> recipients,
        String subject, Date dateTime, String body) {
        this.sender = sender;
        this.recipients = recipients;
        this.subject = subject;
        this.dateTime = dateTime;
        this.body = body;
    }
    /**
    * overrides equals method comparing it to Message parameters instead
    *
    * @param other message checked for equality
    * @return this.getDateTime().equals(that.getDateTime())
    *       && this.getSubject().equals(that.getSubject())
    *       && this.getSender().equals(that.getSender())
    *       && this.getRecipients().equals(that.getRecipients())
    *       && this.getBody().equals(that.getBody())
    */
    @Override
    public boolean equals(Object other) {
        if (null == other) {
            return false;
        }
        if (this == other) {
            return true;
        }
        if (!(other instanceof Message)) {
            return false;
        }
        Message that = (Message) other;
        return this.getDateTime().equals(that.getDateTime())
            && this.getSubject().equals(that.getSubject())
            && this.getSender().equals(that.getSender())
            && this.getRecipients().equals(that.getRecipients())
            && this.getBody().equals(that.getBody());
    }
    /**
    * overrides object's hashCode method
    * @return result
    */
    public int hashCode() {
        int result = 7;
        result = 31 * result * getDateTime().hashCode()
            * getSubject().hashCode() * getSender().hashCode()
            * getRecipients().hashCode() * getBody().hashCode();
        return result;
    }
    /**
    * compares a Message with inputted message
    *
    * @param other Message used for comparison
    * @return 0
    */
    public int compareTo(Message other) {
        if (this.getDateTime().equals(other.getDateTime())) {
            if (this.getSender().equals(other.getSender())) {
                if (this.getSubject().equals(other.getSubject())) {
                    if (this.getBody().equals(other.getBody())) {
                        if (this.getRecipients().equals(other
                            .getRecipients())) {
                            return 0;
                        }
                        return this.getRecipients().hashCode() - other
                            .getRecipients().hashCode();
                    }
                    return this.getBody().hashCode() - other.getBody()
                        .hashCode();
                }
                return this.getSubject().hashCode() - other.getSubject()
                    .hashCode();
            }
            return this.getSender().hashCode() - other.getSender().hashCode();
        } else {
            return this.getDateTime().hashCode() - other.getDateTime()
                .hashCode();
        }
    }
    /**
    * gets and returns the dateTime
    *
    * @return dateTime.toString()
    */
    public String getDateTime() {
        return dateTime.toString();
    }
    /**
    * gets and returns the sender
    *
    * @return sender.toString()
    */
    public String getSender() {
        return sender.toString();
    }
    /**
    * gets and returns the subject
    *
    * @return subject
    */
    public String getSubject() {
        return subject;
    }
    /**
    * gets and returns a formatted body message
    *
    * @return msg
    */
    public ArrayList<String> getBody() {
        ArrayList<String> msg = new ArrayList<>(Arrays.asList(
            "Sender: " + sender,
            "Subject: " + subject,
            "Date: " + dateTime,
            "Body: ",
            body
        ));
        return msg;
    }
    /**
    * gets and returns the recipients
    *
    * @return recipients
    */
    public ArrayList<Person> getRecipients() {
        return recipients;
    }
    /**
     * overrides object's toString method
     * @return (getSender() + ": \n" + getSubject())
     */
    @Override
    public String toString() {
        return (getSender() + ": \n" + getSubject());
    }
}