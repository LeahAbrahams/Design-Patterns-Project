package Logger;

import java.util.ArrayList;
import java.util.List;

public class Informer implements IInformer {
    private List<IObserver> observers = new ArrayList<>();

    @Override
    public void attach(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void detach(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (IObserver observer : observers) {
            observer.update(message);
        }
    }
}
