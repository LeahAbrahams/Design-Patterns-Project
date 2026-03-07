package Logger;

public interface IObserver {
    // מכיוון שמודבר בממשק, אין צורך לכתוב אבסטרקט בהגדרה
    // הפונקציה נקראת באופן אוטומטי בקמפול כאבסטרקט
    void update(String message);
}