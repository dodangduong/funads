package funs.adds.android.sdk.component.observer;

public interface Subject {
        void attach(FunObserver observer);
        void detach(FunObserver observer);
        void notifyAllObserver();
}
