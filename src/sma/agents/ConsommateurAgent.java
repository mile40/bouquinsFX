package sma.agents;

import jade.core.AID;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;
import sma.behaviours.ConsoMsgListener;
import sma.containers.ConsommateurContainer;

public class ConsommateurAgent extends GuiAgent{

    private ConsommateurContainer gui;
    //initialisaiton
    @Override
    protected void setup() {
        super.setup();
        //on recupère le container pour le lier au gui
        gui = (ConsommateurContainer) getArguments()[0];
        //on précise au container que le GUI est lié à cet agent
        gui.setConsommateurAgent(this);
        System.out.println("Initialisation de l'agent "+getAID().getName());
        addBehaviour(new ConsoMsgListener(this));
    }

    //avant destructioin
    @Override
    protected void takeDown() {
        super.takeDown();
        System.out.println("Destruction de l'agent "+getAID().getName());
    }

    @Override
    public void onGuiEvent(GuiEvent guiEvent) {
        switch (guiEvent.getType()){
            case 1:
                ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
                String livre =guiEvent.getParameter(0).toString();
                msg.setContent(livre);
                msg.addReceiver(new AID("acheteur", AID.ISLOCALNAME));
                send(msg);

        }
    }

    public ConsommateurContainer getGui(){
        return this.gui;
    }
}
