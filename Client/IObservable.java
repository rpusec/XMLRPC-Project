public interface IObservable
{
   void subscribe(IObserver o);
   void unsubscribe(IObserver o);
   void notifyObservers(String log);
}