package GameMechanics.KeyboardInput;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class KeyboardMonitor implements KeyListener, Observable {

    private List<Observer> observerList;

    public KeyboardMonitor() {
        this.observerList = new ArrayList<>();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        NotifyObservers(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public void NotifyObservers(KeyEvent keyEvent) {
        for (Observer observer : observerList) {
            observer.processKeyInput(keyEvent);
        }
    }

    public void AddObserver(Observer obs) {
        if (obs != null)
            observerList.add(obs);
    }

    public void DeleteObserver(Observer obs) {
        if (obs != null)
            observerList.remove(obs);
    }
}
