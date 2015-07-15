package state;

public interface TapeDeck {

	void load(String nameOfTape);
    void eject();
    void start();
    void pause();
    void stop();
}
