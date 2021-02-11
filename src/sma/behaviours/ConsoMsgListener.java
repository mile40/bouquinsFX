package sma.behaviours;

import jade.core.behaviours.CyclicBehaviour;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import sma.agents.ConsommateurAgent;
import sma.containers.ConsommateurContainer;

public class ConsoMsgListener extends CyclicBehaviour {
    ConsommateurAgent ctx;
    public ConsoMsgListener(ConsommateurAgent ctx){
        this.ctx = ctx;
    }
    @Override
    public void action() {
        //en gros on peut faire des filtres sur le type de message qu'on reçoit
        MessageTemplate messageTemplate = MessageTemplate.or(
                MessageTemplate.MatchPerformative(ACLMessage.REQUEST),
                MessageTemplate.MatchPerformative(ACLMessage.REFUSE)
        );
        ACLMessage message = getAgent().receive(messageTemplate);
        if(message != null){
            System.out.println("reception message : "+message.getContent());
            GuiEvent guiEvent = new GuiEvent(this, 1);
            guiEvent.addParameter(message.getContent());
            ctx.getGui().viewMessage(guiEvent);
        }
    }
}
