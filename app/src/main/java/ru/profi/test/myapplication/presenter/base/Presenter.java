package ru.profi.test.myapplication.presenter.base;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import ru.profi.test.myapplication.view.base.BaseFragment;

public interface Presenter {

    void redirectTo(FragmentManager fragmentManager, BaseFragment fragment);
    void redirectTo(FragmentManager fragmentManager, BaseFragment fragment, boolean isBackStack);
    void redirectTo(FragmentManager fragmentManager, BaseFragment fragment, Bundle args, boolean isBackStack);

}
