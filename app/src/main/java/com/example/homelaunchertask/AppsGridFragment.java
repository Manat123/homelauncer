package com.example.homelaunchertask;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import java.util.ArrayList;


public class AppsGridFragment extends GridFragment implements LoaderManager.LoaderCallbacks<ArrayList<ch.arnab.simplelauncher.AppModel>> {

    ch.arnab.simplelauncher.AppListAdapter mAdapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setEmptyText("No Applications");

        mAdapter = new ch.arnab.simplelauncher.AppListAdapter(getActivity());
        setGridAdapter(mAdapter);

        // till the data is loaded display a spinner
        setGridShown(false);

        // create the loader to load the apps list in background
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<ArrayList<ch.arnab.simplelauncher.AppModel>> onCreateLoader(int id, Bundle bundle) {
        return new ch.arnab.simplelauncher.AppsLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<ch.arnab.simplelauncher.AppModel>> loader, ArrayList<ch.arnab.simplelauncher.AppModel> apps) {
        mAdapter.setData(apps);

        if (isResumed()) {
            setGridShown(true);
        } else {
            setGridShownNoAnimation(true);
        }
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<ch.arnab.simplelauncher.AppModel>> loader) {
        mAdapter.setData(null);
    }

    @Override
    public void onGridItemClick(GridView g, View v, int position, long id) {
        ch.arnab.simplelauncher.AppModel app = (ch.arnab.simplelauncher.AppModel) getGridAdapter().getItem(position);
        if (app != null) {
            Intent intent = getActivity().getPackageManager().getLaunchIntentForPackage(app.getApplicationPackageName());

            if (intent != null) {
                startActivity(intent);
            }
        }
    }
}
