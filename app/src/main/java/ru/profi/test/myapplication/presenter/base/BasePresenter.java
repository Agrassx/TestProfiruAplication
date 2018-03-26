package ru.profi.test.myapplication.presenter.base;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import ru.profi.test.myapplication.R;
import ru.profi.test.myapplication.model.Model;
import ru.profi.test.myapplication.model.ModelImpl;
import ru.profi.test.myapplication.model.api.ApiInterface;
import ru.profi.test.myapplication.model.api.ApiModule;
import ru.profi.test.myapplication.view.base.BaseFragment;
import ru.profi.test.myapplication.view.base.FragmentView;

public class BasePresenter implements Presenter {

    protected Model model = new ModelImpl();
    protected ApiInterface apiInterface = new ApiModule();
    private FragmentView fragmentView;


    public BasePresenter() {

    }

    @Override
    public void redirectTo(FragmentManager fragmentManager, BaseFragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentView = fragment.getIView();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void redirectTo(FragmentManager fragmentManager, BaseFragment fragment, boolean isBackStack) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentView = fragment.getIView();
        fragmentTransaction.replace(R.id.container, fragment);
        if (isBackStack) fragmentTransaction.addToBackStack(fragment.getTag());
        fragmentTransaction.commit();
    }

    @Override
    public void redirectTo(FragmentManager fragmentManager,
                           BaseFragment fragment, Bundle args, boolean isBackStack) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentView = fragment.getIView();
        fragment.setArguments(args);
        fragmentTransaction.replace(R.id.container, fragment);
        if (isBackStack) fragmentTransaction.addToBackStack(fragment.getTag());
        fragmentTransaction.commit();
    }
}
