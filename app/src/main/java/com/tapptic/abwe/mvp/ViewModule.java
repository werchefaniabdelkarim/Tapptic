package com.tapptic.abwe.mvp;


import com.tapptic.abwe.datamodels.Item;
import com.tapptic.abwe.datamodels.ItemList;

import java.util.List;

public interface ViewModule {
    interface View {
        void showData(List<ItemList> data);

        void showError(String message);

        void showComplete();

        void showProgress();

        void hideProgress();

        void showOneItem(Item data);
    }

    interface Presenter {
        void loadAllData();
        void loadOneItem(String number);
    }
}
