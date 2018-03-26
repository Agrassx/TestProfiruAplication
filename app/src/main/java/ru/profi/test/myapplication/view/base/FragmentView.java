package ru.profi.test.myapplication.view.base;

import ru.profi.test.myapplication.view.MainActivityView;

public interface FragmentView {
    void setActivityView(MainActivityView activityView);
    void showMessage(String message);
}
