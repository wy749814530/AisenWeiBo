package org.aisen.android.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;

import org.aisen.android.R;
import org.aisen.android.support.inject.ViewInject;

import java.io.Serializable;

/**
 * 维护ListView的SwipeRefreshLayout控件
 *
 */
public abstract class AListSwipeRefreshFragment<T extends Serializable, Ts extends Serializable>
                                            extends AListFragment<T, Ts>
                                            implements SwipeRefreshLayout.OnRefreshListener {

    @ViewInject(idStr = "swipeRefreshLayout")
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected int inflateContentView() {
        return R.layout.comm_ui_list_swiperefresh;
    }

    @Override
    protected void setupRefreshView(ListView refreshView, Bundle savedInstanceSate) {
        super.setupRefreshView(refreshView, savedInstanceSate);

        setupSwipeRefreshLayout();
    }

    protected void setupSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                                                    android.R.color.holo_green_light,
                                                    android.R.color.holo_orange_light,
                                                    android.R.color.holo_red_light);
    }

    @Override
    public void onRefresh() {
        onPullDownToRefresh();
    }

    @Override
    public boolean setRefreshing() {
        swipeRefreshLayout.setRefreshing(true);

        return false;
    }

    @Override
    public void onRefreshViewComplete(RefreshMode mode) {
        if (mode != RefreshMode.update && swipeRefreshLayout.isRefreshing())
            swipeRefreshLayout.setRefreshing(false);
    }

    public SwipeRefreshLayout getSwipeRefreshLayout() {
        return swipeRefreshLayout;
    }

}
