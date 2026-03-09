package Logger;

import java.util.ArrayList;
import java.util.List;

public class Informer implements IInformer {
    private final List<IObserver> observers = new ArrayList<>();
    private static Informer instance;

    private Informer() {
    }

    public static Informer getInformer() {
        synchronized (Informer.class) {
            if (instance == null) {
                instance = new Informer();
            }
        }
        return instance;
    }

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