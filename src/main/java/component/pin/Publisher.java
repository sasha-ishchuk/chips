package component.pin;

public interface Publisher {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
