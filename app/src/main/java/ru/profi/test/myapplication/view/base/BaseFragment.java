package ru.profi.test.myapplication.view.base;

import android.support.v4.app.Fragment;

import ru.profi.test.myapplication.view.MainActivityView;

public abstract class BaseFragment extends Fragment {

    public abstract FragmentView getIView();

    public abstract void setActivityView(MainActivityView activityView);

}
