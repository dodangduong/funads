package funs.adds.android.sdk.component.observer;

import java.util.ArrayList;
import java.util.List;

import funs.adds.android.sdk.component.model.networkmodel.Data;

public class AdsServices implements Subject {

    private Data data;

    private List<FunObserver> list = new ArrayList<>();

    public AdsServices(Data data) {
        this.data = data;
    }

    @Override
    public void attach(FunObserver observer) {
        if (!list.contains(observer)) {
            list.add(observer);
        }
    }

    @Override
    public void detach(FunObserver observer) {

        if (list.contains(observer)) {
            list.remove(observer);
        }
    }

    @Override
    public void notifyAllObserver() {

        for (FunObserver observer : list) {
            observer.updateAds(data);
        }
    }

    public void removeAllObserver() {
        if (list!= null && list.size() > 0) {
            list.isEmpty();
        }
    }

    public int getObserver() {
        return list.size();
    }
}
