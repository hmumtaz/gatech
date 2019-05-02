import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
/**
* Represents a Server Object.
*
* @author Hussain Mumtaz
* @version 1.0
*/
public class Server {
    private ArrayList<Person> senders = new ArrayList<>(Arrays.asList(
            new Person("Arthur Dent", "aDent@theguide.org"),
            new Person("Ford Prefect", "fPrefect@theguide.org"),
            new Person("Zaphod Beeblebrox", "president@theguide.org"),
            new Person("Trillian", "trillz@theguide.org"),
            new Person("Marvin", "badNews@theguide.org"),
            new Person("Slartibartfast", "sbfast@theguide.org"),
            new Person("Benjy Mouse", "bMouse@theguide.org"),
            new Person("Frankie Mouse", "fMouse@theguide.org"),
            new Person("Prostetnic Vogon Jeltz", "pvj@theguide.org"),
            new Person("Douglas Adams", "42@theguide.org")));;

    private ArrayList<String> subject = new ArrayList<>(Arrays.asList(
            "So Long and Thanks for the Fish...",
            "42",
            "The Ultimate Question",
            "Don't Panic",
            "Deep Thought",
            "Babel Fish",
            "Depressed",
            "In the Beginning...",
            "Pan Glactic Gargle Blaster",
            "Don't Forget Your Towel"));;
    /**
    * generates a random sender from senders
    *
    * @return senders.get(val)
    */
    public Person randSender() {
        Random random = new Random();
        int val = random.nextInt(9);
        return senders.get(val);
    }
    /**
    * generates a random subject from subject
    *
    * @return subject.get(val)
    */
    public String randSubject() {
        Random random = new Random();
        int val = random.nextInt(9);
        return subject.get(val);
    }
    /**
    * generates a random body string
    *
    * @return str.toString())
    */
    public String randBody() {
        Random random = new Random();
        StringBuffer str = new StringBuffer(50);
        for (int x = 0; x < 50; x = x + 1) {
            int val = random.nextInt(264);
            str.append((char) val);
        }
        return str.toString();
    }
    /**
    * returns current date and time
    *
    * @return loc
    */
    public Date date() {
        Date loc = new Date();
        return loc;
    }
    /**
    * gets and returns the a message
    *
    * @return msg
    */
    public Message getMessage() {
        Person sender = randSender();
        String subject = randSubject();
        String body = randBody();
        Date date = date();
        Message msg = new Message(sender, senders, subject, date, body);
        return msg;
    }
}