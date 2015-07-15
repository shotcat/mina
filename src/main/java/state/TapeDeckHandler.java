package state;

import org.apache.mina.statemachine.annotation.State;
import org.apache.mina.statemachine.annotation.Transition;
import org.apache.mina.statemachine.annotation.Transitions;
import org.apache.mina.statemachine.event.Event;

public class TapeDeckHandler {

	@State public static final String EMPTY   = "Empty";
    @State public static final String LOADED  = "Loaded";
    @State public static final String PLAYING = "Playing";
    @State public static final String PAUSED  = "Paused";
    
    @Transition(on="load",in=EMPTY,next = LOADED)
    public void loadTape(String nameOfTape) {
    	System.err.println("xxxx" +nameOfTape);
    }
    
    @Transitions({  
        @Transition(on = "start", in = LOADED, next = PLAYING),  
        @Transition(on = "start", in = PAUSED, next = PLAYING)  
    }) 
    public void playTape() {
        System.out.println("Playing tape");
    }

    @Transition(on = "pause", in = PLAYING, next = PAUSED)
    public void pauseTape() {
        System.out.println("Tape paused");
    }

    @Transitions({  
        @Transition(on = "stop", in = PLAYING, next = LOADED),  
        @Transition(on = "stop", in = PAUSED, next = LOADED)  
    }) 
    @Transition(on = "", in = PLAYING, next = LOADED)
    public void stopTape() {
        System.out.println("Tape stopped");
    }

    @Transition(on = "eject", in = LOADED, next = EMPTY)
    public void ejectTape() {
        System.out.println("Tape ejected");
    }
    
    @Transitions({  
        @Transition(on = "*", in = EMPTY, weight = 100),  
        @Transition(on = "*", in = LOADED, weight = 100),  
        @Transition(on = "*", in = PLAYING, weight = 100),  
        @Transition(on = "*", in = PAUSED, weight = 100)  
    })  
    public void error(Event event) {  
        System.out.println("Cannot '" + event.getId() + "' at this time");  
    }  
}
