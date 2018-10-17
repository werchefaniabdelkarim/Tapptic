package com.tapptic.abwe.mvp;

import com.tapptic.abwe.datamodels.Item;
import com.tapptic.abwe.datamodels.ItemList;
import com.tapptic.abwe.api.NetworkTappticService;

import java.util.List;
import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PresenterImpl implements ViewModule.Presenter {

    NetworkTappticService networkService;
    ViewModule.View mView;

    @Inject
    public PresenterImpl(NetworkTappticService apiInterface, ViewModule.View mView) {
        this.networkService = apiInterface;
        this.mView = mView;
    }

    @Override
    public void loadAllData() {

        mView.showProgress();

        networkService.getAllData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ItemList>>() {

                    @Override
                    public void onError(Throwable e) {
                        mView.showError("Error occurred");
                        mView.hideProgress();
                    }

                    @Override
                    public void onComplete() {
                        mView.showComplete();
                        mView.hideProgress();
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        mView.showProgress();
                    }

                    @Override
                    public void onNext(List<ItemList> cityListResponse) {
                        mView.showData(cityListResponse);
                    }
                });
    }

    @Override
    public void loadOneItem(String number) {
        mView.showProgress();

        networkService.getOneItem(number)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Item>() {

                    @Override
                    public void onError(Throwable e) {
                        mView.showError("Error occurred");
                        mView.hideProgress();
                    }

                    @Override
                    public void onComplete() {
                        mView.showComplete();
                        mView.hideProgress();
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        mView.showProgress();
                    }

                    @Override
                    public void onNext(Item item) {
                        mView.showOneItem(item);
                    }
                });
    }
}
