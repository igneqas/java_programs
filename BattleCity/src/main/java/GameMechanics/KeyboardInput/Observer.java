package GameMechanics.KeyboardInput;

import java.awt.event.KeyEvent;

public interface Observer {
    void processKeyInput(KeyEvent keyEvent);
}
