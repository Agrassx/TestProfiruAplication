package ru.profi.test.myapplication.view.friends;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.SharedElementCallback;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.ChangeBounds;
import android.transition.Scene;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ru.profi.test.myapplication.R;
import ru.profi.test.myapplication.model.adapter.FriendsAdapter;
import ru.profi.test.myapplication.model.classes.Friend;
import ru.profi.test.myapplication.presenter.FriendPresenter;
import ru.profi.test.myapplication.view.FullImageView.FullImageViewFragment;
import ru.profi.test.myapplication.view.MainActivityView;
import ru.profi.test.myapplication.view.base.BaseFragment;
import ru.profi.test.myapplication.view.base.FragmentView;


public class FriendsFragment extends BaseFragment implements FriendsView {
    private final static String TAG = FriendsFragment.class.getName();

    private MainActivityView activityView;
    private static final String TRANSITION_NAME = "sharedElement";
    private FriendsAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ViewGroup sceneRoot;
    private Scene scene2;
    private FriendPresenter presenter;
    private FriendsView view;

    public static FriendsFragment newInstance(MainActivityView activityView) {

        Bundle args = new Bundle();
        FriendsFragment fragment = new FriendsFragment();
        fragment.setActivityView(activityView);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        mAdapter = new FriendsAdapter(new ArrayList<>());
        mAdapter.setOnClickListener((item, sharedView) -> {
            FullImageViewFragment fragment = FullImageViewFragment.newInstance(activityView, item);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Slide slideTransition = new Slide(Gravity.LEFT);
                slideTransition.setDuration(1000);
                this.setEnterTransition(slideTransition);
                Transition transition = transition = TransitionInflater.from(getContext())
                            .inflateTransition(R.transition.change_bounds);
                fragment.setEnterTransition(transition);
                activityView.redirectWithBackStackTo(fragment, sharedView, TRANSITION_NAME);
            }

        });
        presenter = new FriendPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friends, container, false);
        mRecyclerView = view.findViewById(R.id.listViewFriends);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        if (savedInstanceState == null) {
            presenter.loadFriends();
        }
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

    @Override
    public void showFriends(List<Friend> friendList) {
        mAdapter.addAll(friendList);
        mAdapter.notifyDataSetChanged();
    }

}

