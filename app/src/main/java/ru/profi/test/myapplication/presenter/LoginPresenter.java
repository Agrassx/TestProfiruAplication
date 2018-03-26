package ru.profi.test.myapplication.presenter;


import ru.profi.test.myapplication.presenter.base.BasePresenter;
import ru.profi.test.myapplication.view.login.LoginView;

public class LoginPresenter extends BasePresenter {

    private static final String LOG = LoginPresenter.class.getName();
    private LoginView view;


    public LoginPresenter(LoginView view) {
        this.view = view;
    }


}
