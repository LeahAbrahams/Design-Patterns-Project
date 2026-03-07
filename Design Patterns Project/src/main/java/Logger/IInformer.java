package Logger;

import java.util.ArrayList;
import java.util.List;

public interface IInformer {
    void attach(IObserver observer);
    void detach(IObserver observer);
    void notifyObservers(String message);
}
