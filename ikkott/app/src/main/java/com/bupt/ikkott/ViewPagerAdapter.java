package com.bupt.ikkott;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bupt.ikkott.player.VideoPlayerIJK;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.PagerViewHolder> {
    private static int viewHolderCount;
    private final int initPosition;
    private int mNumberItems;
    private VideoParser parser;

    public ViewPagerAdapter(int initPos) {
        viewHolderCount = 0;
        initPosition = initPos;
        parser = new VideoParser();
        mNumberItems = parser.size();
    }

    @NonNull
    @Override
    public PagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layout_id = R.layout.viewpager_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layout_id, parent, shouldAttachToParentImmediately);
        PagerViewHolder holder = new PagerViewHolder(view);
        viewHolderCount++;
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PagerViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }

    @Override
    public void onViewAttachedToWindow(@NonNull PagerViewHolder holder) {
        //页面出现时开始播放
        super.onViewAttachedToWindow(holder);
        holder.ijkPlayer.start();
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull PagerViewHolder holder) {
        //页面消失时停止播放
        super.onViewDetachedFromWindow(holder);
        holder.ijkPlayer.pause();
    }

    public class PagerViewHolder extends RecyclerView.ViewHolder {
        private VideoPlayerIJK ijkPlayer;
        private VideoPlayer videoPlayer;

        public PagerViewHolder(@NonNull View itemView) {
            super(itemView);
            ijkPlayer = itemView.findViewById(R.id.ijk_player_view);

        }

        public void bind(int position) {
            int currentPos = (position + initPosition) % mNumberItems;
            String resourceURI = parser.getFeedURL(currentPos);
            videoPlayer = new VideoPlayer(ijkPlayer, resourceURI);
        }
    }
}
