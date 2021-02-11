package sma.containers;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.gui.GuiEvent;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sma.agents.ConsommateurAgent;

public class ConsommateurContainer extends Application {
    private ConsommateurAgent consommateurAgent;
    private ObservableList<String> observableList;
    public static void main(String[] args){
        launch(ConsommateurContainer.class);
    }

    public void startContainer(){
        try{
            Runtime runtime = Runtime.instance();
            Profile profile = new ProfileImpl(false);
            profile.setParameter(Profile.MAIN_HOST, "localhost");
            AgentContainer agentContainer = runtime.createAgentContainer(profile);

            //on créé l'agent en prennant bien soin de passer en paramètre notre container
            AgentController agentController = agentContainer.createNewAgent("consommateur1","sma.agents.ConsommateurAgent", new Object[]{this});
            agentController.start();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        //on demarre le contenneur
        startContainer();


        //création de l'interface, en vrai OSEF
        stage.setTitle("consommateur");
        BorderPane borderPane = new BorderPane();

        HBox hBox= new HBox();
        hBox.setPadding(new Insets(10));
        hBox.setSpacing(10);

        Label labelLivre = new Label("Livre:");
        TextField textFieldLivre = new TextField();
        Button buttonAcheter = new Button("Acheter");
        hBox.getChildren().add(labelLivre);
        hBox.getChildren().add(textFieldLivre);
        hBox.getChildren().add(buttonAcheter);
        borderPane.setTop(hBox);

        VBox vBox = new VBox();
        GridPane gridPane = new GridPane();
        observableList = FXCollections.observableArrayList();
        ListView<String> listViewMessages = new ListView<String>(observableList);
        gridPane.add(listViewMessages, 0, 0);
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(10);
        vBox.getChildren().add(gridPane);
        borderPane.setCenter(vBox);

        buttonAcheter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String livre = textFieldLivre.getText();
                //on génère un événement pour que l'agent fassee quelque chose
                GuiEvent guiEvent = new GuiEvent(this, 1);
                guiEvent.addParameter(livre);
                consommateurAgent.onGuiEvent(guiEvent);


            }
        });
        Scene scene = new Scene(borderPane, 400, 500);
        stage.setScene(scene);
        stage.show();
    }

    public void viewMessage(GuiEvent guiEvent){

        String msg = guiEvent.getParameter(0).toString();
        observableList.add(msg);
    }
    public ConsommateurAgent getConsommateurAgent() {
        return consommateurAgent;
    }

    public void setConsommateurAgent(ConsommateurAgent consommateurAgent) {
        this.consommateurAgent = consommateurAgent;
    }

}
