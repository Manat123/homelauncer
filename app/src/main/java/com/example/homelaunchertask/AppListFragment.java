package com.example.homelaunchertask;

import android.os.Bundle;

import androidx.fragment.app.ListFragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import java.util.ArrayList;

public class AppListFragment extends ListFragment implements LoaderManager.LoaderCallbacks<ArrayList<ch.arnab.simplelauncher.AppModel>> {
    ch.arnab.simplelauncher.AppListAdapter mAdapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setEmptyText("No Applications");

        mAdapter = new ch.arnab.simplelauncher.AppListAdapter(getActivity());
        setListAdapter(mAdapter);

        // till the data is loaded display a spinner
        setListShown(false);

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
            setListShown(true);
        } else {
            setListShownNoAnimation(true);
        }
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<ch.arnab.simplelauncher.AppModel>> loader) {
        mAdapter.setData(null);
    }
}
