package com.bupt.ikkott;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONException;

@SuppressWarnings("WeakerAccess")
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.NumberViewHolder> {
    private static int viewHolderCount;
    private final ListItemClickListener mOnClickListener;
    private int mNumberItems;
    private Context context;

    private VideoParser parser;

    public RecyclerViewAdapter(Context context, int numListItems, ListItemClickListener listener) {
        mNumberItems = numListItems;
        mOnClickListener = listener;
        viewHolderCount = 0;
        this.context = context;
        parser = VideoParser.getInstance();
    }

    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.recycler_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        NumberViewHolder viewHolder = new NumberViewHolder(view);
        viewHolderCount++;
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder numberViewHolder, final int position) {
        try {
            numberViewHolder.bind(position);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        numberViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //设置点击事件：点击封面图片跳转到播放页面
                Intent intent = new Intent(context, ViewPagerActivity.class);

                intent.putExtra("initPos", position);//传递第几个view的视频地址信息

                context.startActivity(intent);//跳转到播放界面
            }
        });

    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    public class NumberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView desc_text;
        private final TextView author_text;
        private final ImageView imageView;


        public NumberViewHolder(@NonNull View itemView) {
            super(itemView);
            desc_text = (TextView) itemView.findViewById(R.id.desc_text);
            author_text = (TextView) itemView.findViewById(R.id.author_text);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(this);
        }

        public void bind(int position) throws JSONException {
            desc_text.setText("" + parser.getDescription(position));
            author_text.setText("用户：" + parser.getNickname(position));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Glide.with(imageView.getContext()) //使用 Glide 加载封面
                    .setDefaultRequestOptions(
                            new RequestOptions()
                                    .frame(1000000)
                                    .centerCrop()
                    )
                    .load(parser.getFeedURL(position))
                    .into(imageView);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            if (mOnClickListener != null) {
                mOnClickListener.onListItemClick(clickedPosition);
            }
        }
    }
}
