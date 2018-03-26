package ru.profi.test.myapplication.model.adapter;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.transition.Transition;
import android.transition.TransitionSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ru.profi.test.myapplication.R;
import ru.profi.test.myapplication.model.asynctasks.ImageLoaderTask;
import ru.profi.test.myapplication.model.cache.CacheUtil;
import ru.profi.test.myapplication.model.classes.Friend;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.ViewHolder> {
    private static final String TAG = FriendsAdapter.class.getSimpleName();

    private List<Friend> list;
    private OnItemClickListener listener;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextViewName;
        public ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            this.mTextViewName = view.findViewById(R.id.textViewName);
            this.imageView = view.findViewById(R.id.imageViewAvatar);
        }

        public void bind(final Friend item, final OnItemClickListener listener) {
            itemView.setOnClickListener(v -> listener.onItemClick(item, imageView));

            mTextViewName.setText(String.format(
                    "%s %s",
                    item.getFirst_name(),
                    item.getLast_name())
            );
//            Тут я бы использовал Picasso, но по заданию нельзя :(
            String id = item.getId();
            if (CacheUtil.getBitmapFromMemCache(id) == null) {
                new ImageLoaderTask(imageView, id).execute(item.getPhoto_100());
                return;
            }
            imageView.setImageBitmap(CacheUtil.getBitmapFromMemCache(id));
        }
    }

    public FriendsAdapter(List<Friend> friendList) {
        this.list = friendList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_friend, parent, false);
        return new ViewHolder(view);
    }

    public void setOnClickListener(OnItemClickListener onItemClickListener) {
        this.listener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addAll(@NonNull List<Friend> list) {
        this.list.addAll(list);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(list.get(position), listener);
    }

    public interface OnItemClickListener {
        void onItemClick(Friend item, View sharedView);
    }
}
