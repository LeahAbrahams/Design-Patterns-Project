package Logger;

public interface IInformer {
    void attach(IObserver observer);
    void detach(IObserver observer);
    void notifyObservers(String message);
}
