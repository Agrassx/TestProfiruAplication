package ru.profi.test.myapplication.view;

import android.view.View;

import ru.profi.test.myapplication.view.base.BaseFragment;

public interface MainActivityView {

    void redirectTo(BaseFragment fragment);
    void redirectWithBackStackTo(BaseFragment fragment, View sharedElement, String name);
}
