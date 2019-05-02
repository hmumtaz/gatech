import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Arrays;
/**
* Represents a MailReader object.
*
* @author Hussain Mumtaz
* @version 1.0
*/
public class MailReader extends Application {
    private ObservableList<Mailbox> mailboxes;
    private Mailbox inbox = new Inbox();
    private Mailbox important = new Important();
    private Mailbox trash = new Trash();
    /**
    * Instantiates a MailReader Object
    *
    * @param stage stage for MailReader
    */
    public void start(Stage stage) {
        mailboxes = FXCollections.observableArrayList(inbox, important, trash);
        ListView<Mailbox> boxList = new ListView<Mailbox>(mailboxes);
        ObservableList<Message> initList = FXCollections
            .observableArrayList(inbox.getMessages());
        ListView<Message> msgList = new ListView<Message>(initList);
        ArrayList<String> initMsg = new ArrayList<>(Arrays.asList(
            "No Message to Display"));
        ObservableList<String> iMsg = FXCollections
            .observableArrayList(initMsg);
        ListView<String> mssg = new ListView<String>(iMsg);

        Button refresh = new Button("Refresh");
        refresh.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                Server srv = new Server();
                inbox.getMessages().add(0, srv.getMessage());
                boxList.getSelectionModel().selectLast();
                boxList.getSelectionModel().selectFirst();
            }
        });

        Button flag = new Button("Flag");
        flag.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (boxList.getSelectionModel().getSelectedItem() == inbox) {
                    important.add(inbox.getMessages().remove(
                        inbox.getMessages().indexOf(msgList.getSelectionModel()
                        .getSelectedItem())));
                    boxList.getSelectionModel().selectLast();
                    boxList.getSelectionModel().selectFirst();
                }
                if (boxList.getSelectionModel().getSelectedItem() == trash) {
                    important.add(trash.getMessages().remove(
                        trash.getMessages().indexOf(msgList.getSelectionModel()
                        .getSelectedItem())));
                    boxList.getSelectionModel().selectFirst();
                    boxList.getSelectionModel().selectLast();
                }
            }
        });

        Button delete = new Button("Delete");
        delete.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                int x = mailboxes.indexOf(boxList.getSelectionModel()
                    .getSelectedItem());
                if (boxList.getSelectionModel().getSelectedItem() == inbox) {
                    trash.add(inbox.getMessages().remove(
                        inbox.getMessages().indexOf(msgList.getSelectionModel()
                        .getSelectedItem())));
                    boxList.getSelectionModel().selectIndices(1);
                    boxList.getSelectionModel().selectFirst();
                }
                if (boxList.getSelectionModel().getSelectedItem()
                    == important) {
                    trash.add(important.getMessages().remove(
                        important.getMessages().indexOf(msgList
                        .getSelectionModel().getSelectedItem())));
                    boxList.getSelectionModel().selectFirst();
                }
                if (boxList.getSelectionModel().getSelectedItem() == trash) {
                    trash.getMessages().remove(msgList.getSelectionModel()
                        .getSelectedItem());
                    boxList.getSelectionModel().selectFirst();
                }
                boxList.getSelectionModel().selectIndices(x);
            }
        });

        Button dateSort = new Button("Sort by Date");
        dateSort.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                int x = mailboxes.indexOf(boxList.getSelectionModel()
                    .getSelectedItem());
                boxList.getSelectionModel().getSelectedItem().dateSort();
                boxList.getSelectionModel().selectLast();
                boxList.getSelectionModel().selectFirst();
                boxList.getSelectionModel().selectIndices(x);
            }
        });

        Button senderSort = new Button("Sort by Sender");
        senderSort.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                int x = mailboxes.indexOf(boxList.getSelectionModel()
                    .getSelectedItem());
                boxList.getSelectionModel().getSelectedItem().senderSort();
                boxList.getSelectionModel().selectLast();
                boxList.getSelectionModel().selectFirst();
                boxList.getSelectionModel().selectIndices(x);
            }
        });

        Button subjectSort = new Button("Sort by Subject");
        subjectSort.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                int x = mailboxes.indexOf(boxList.getSelectionModel()
                    .getSelectedItem());
                boxList.getSelectionModel().getSelectedItem().subjectSort();
                boxList.getSelectionModel().selectLast();
                boxList.getSelectionModel().selectFirst();
                boxList.getSelectionModel().selectIndices(x);
            }
        });

        boxList.getSelectionModel().selectedItemProperty()
            .addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        ObservableList<Message> messages = FXCollections
                            .observableArrayList(newValue.getMessages());
                        msgList.setItems(messages);
                    }
                });

        msgList.getSelectionModel().selectedItemProperty()
            .addListener((observable, oldValue, newValue) -> {
                    ObservableList<String> message;
                    if (newValue != null) {
                        message = FXCollections
                            .observableArrayList(newValue.getBody());
                        mssg.setItems(message);
                    } else {
                        mssg.setItems(iMsg);
                    }
                });

        HBox buttons = new HBox();
        buttons.getChildren().addAll(refresh, flag, delete);

        HBox sorts = new HBox();
        sorts.getChildren().addAll(dateSort, senderSort, subjectSort);

        VBox mboxes = new VBox();
        mboxes.getChildren().addAll(boxList);

        VBox mssgs = new VBox();
        mssgs.getChildren().addAll(msgList);

        VBox messg = new VBox();
        mssgs.getChildren().addAll(mssg);

        VBox mail = new VBox();
        mail.getChildren().addAll(buttons, mboxes, mssgs, messg, sorts);

        Scene scene = new Scene(mail);
        stage.setScene(scene);
        stage.setTitle("Galaxy Mail");
        stage.show();
    }
}