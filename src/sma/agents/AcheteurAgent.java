package sma.agents;

import jade.core.AID;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;
import sma.behaviours.AcheteurMsgListener;
import sma.behaviours.ConsoMsgListener;
import sma.containers.AcheteurContainer;
import sma.containers.ConsommateurContainer;

public class AcheteurAgent extends GuiAgent{

    private AcheteurContainer gui;
    //initialisaiton
    @Override
    protected void setup() {
        super.setup();
        //on recupère le container pour le lier au gui
        gui = (AcheteurContainer) getArguments()[0];
        //on précise au container que le GUI est lié à cet agent
        gui.setAcheteurAgent(this);
        System.out.println("Initialisation de l'agent "+getAID().getName());
        addBehaviour(new AcheteurMsgListener(this));
    }

    //avant destructioin
    @Override
    protected void takeDown() {
        super.takeDown();
        System.out.println("Destruction de l'agent "+getAID().getName());
    }

    @Override
    public void onGuiEvent(GuiEvent guiEvent) {

    }

    public AcheteurContainer getGui(){
        return this.gui;
    }
}
