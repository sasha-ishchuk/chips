package component.pin;

import component.pin.Observer;

public interface Publisher {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
