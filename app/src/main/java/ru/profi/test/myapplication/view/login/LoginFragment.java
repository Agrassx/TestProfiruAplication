package ru.profi.test.myapplication.view.login;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;

import ru.profi.test.myapplication.R;
import ru.profi.test.myapplication.presenter.LoginPresenter;
import ru.profi.test.myapplication.view.MainActivityView;
import ru.profi.test.myapplication.view.base.BaseFragment;
import ru.profi.test.myapplication.view.base.FragmentView;

public class LoginFragment extends BaseFragment implements LoginView {

    private MainActivityView activityView;
    private LoginPresenter presenter;
    private LoginView view;

    private Button buttonLogin;

    private static final String[] sMyScope = new String[]{
            VKScope.FRIENDS,
            VKScope.PHOTOS,
            VKScope.NOHTTPS
    };

    public static LoginFragment newInstance(MainActivityView activityView) {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setActivityView(activityView);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new LoginPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        buttonLogin = view.findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(v -> VKSdk.login(getActivity(), sMyScope));
        return view;
    }

    @Override
    public FragmentView getIView() {
        return this;
    }

    @Override
    public void setActivityView(MainActivityView activityView) {
        this.activityView = activityView;
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
