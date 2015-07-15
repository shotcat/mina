package state;

import org.apache.mina.statemachine.StateMachine;
import org.apache.mina.statemachine.StateMachineFactory;
import org.apache.mina.statemachine.StateMachineProxyBuilder;
import org.apache.mina.statemachine.annotation.Transition;

public class StateTest {

	public static void main(String[] args) {
		TapeDeckHandler deckHandler = new TapeDeckHandler();
		StateMachine machine  = StateMachineFactory.getInstance(Transition.class).create(TapeDeckHandler.EMPTY, deckHandler);
		TapeDeck deck = new StateMachineProxyBuilder().create(TapeDeck.class, machine);
		deck.load("dongtian");
		deck.start();
		deck.pause();
		deck.start();
		deck.pause();
		deck.stop();
		deck.eject();
	}
	
}
