package GameMechanics.KeyboardInput;

import java.awt.event.KeyEvent;

public interface Observable {
    void NotifyObservers(KeyEvent keyEvent);
}
