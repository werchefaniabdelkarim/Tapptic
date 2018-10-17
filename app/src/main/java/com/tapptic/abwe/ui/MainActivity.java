package com.tapptic.abwe.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.tapptic.abwe.TappticApplication;
import com.tapptic.abwe.datamodels.Item;
import com.tapptic.abwe.datamodels.ItemList;
import com.tapptic.abwe.R;
import com.tapptic.abwe.component.ApplicationComponent;
import com.tapptic.abwe.component.DaggerMainActivityComponent;
import com.tapptic.abwe.component.MainActivityComponent;
import com.tapptic.abwe.module.MainActivityContextModule;
import com.tapptic.abwe.module.MainActivityMvpModule;
import com.tapptic.abwe.mvp.PresenterImpl;
import com.tapptic.abwe.mvp.ViewModule;

import java.util.List;

import javax.inject.Inject;


/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link DetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class MainActivity extends AppCompatActivity implements ViewModule.View {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    ProgressBar progressBar;
    Toolbar toolbar;


    private boolean mTwoPane;

    MainActivityComponent mainActivityComponent;

    public SimpleItemRecyclerViewAdapter simpleItemRecyclerViewAdapter;

    @Inject
    PresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);


        ApplicationComponent applicationComponent = TappticApplication.get(this).getApplicationComponent();

       mainActivityComponent = DaggerMainActivityComponent.builder()
                .mainActivityContextModule(new MainActivityContextModule(this))
                .mainActivityMvpModule(new MainActivityMvpModule(this))
                .applicationComponent(applicationComponent)
                .build();

        mainActivityComponent.injectMainActivity(this);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        progressBar = findViewById(R.id.progressBar);

        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
        View recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {

        simpleItemRecyclerViewAdapter = new SimpleItemRecyclerViewAdapter(this, mTwoPane);
        recyclerView.setAdapter(simpleItemRecyclerViewAdapter);
        presenter.loadAllData();
    }

    @Override
    public void showData(List<ItemList> marketList) {
        if (marketList != null && marketList.size() != 0) {
            simpleItemRecyclerViewAdapter.setData(marketList);


        } else {
            Toast.makeText(this, "NO RESULTS FOUND",
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showComplete() {

    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showOneItem(Item data) {

    }
}
