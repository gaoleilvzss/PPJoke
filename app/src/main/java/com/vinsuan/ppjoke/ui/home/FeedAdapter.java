package com.vinsuan.ppjoke.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vinsuan.ppjoke.databinding.LayoutFeedTypeImageBinding;
import com.vinsuan.ppjoke.databinding.LayoutFeedTypeVideoBinding;
import com.vinsuan.ppjoke.model.Feed;

public class FeedAdapter extends PagedListAdapter<Feed, FeedAdapter.ViewHolder> {
    private final LayoutInflater inflater;
    private Context mContext;
    private String mCategory;

    protected FeedAdapter(Context context,String category) {
        super(new DiffUtil.ItemCallback<Feed>() {
            @Override
            public boolean areItemsTheSame(@NonNull Feed oldItem, @NonNull Feed newItem) {
                return oldItem.id == newItem.id;
            }

            @Override
            public boolean areContentsTheSame(@NonNull Feed oldItem, @NonNull Feed newItem) {
                return false;
            }
        });
        inflater = LayoutInflater.from(context);
        mContext = context;
        mCategory = category;
    }

    @Override
    public int getItemViewType(int position) {
        Feed feed = getItem(position);
        return feed.itemType;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding;
        if (viewType == Feed.TYPE_IMAGE_TEXT) {
            binding = LayoutFeedTypeImageBinding.inflate(inflater);
        } else {
            binding = LayoutFeedTypeVideoBinding.inflate(inflater);
        }


        return new ViewHolder(binding.getRoot(),binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(getItem(position));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding mBinding;

        public ViewHolder(@NonNull View itemView, ViewDataBinding binding) {
            super(itemView);
            mBinding = binding;
        }

        public void bindData(Feed item) {
            if(mBinding instanceof LayoutFeedTypeImageBinding){
                LayoutFeedTypeImageBinding imageBinding = (LayoutFeedTypeImageBinding) mBinding;
                imageBinding.setFeed(item);
                imageBinding.feedImage.bindData(item.width,item.height,16,item.cover);
            }else{
                LayoutFeedTypeVideoBinding videoBinding = (LayoutFeedTypeVideoBinding) mBinding;
                videoBinding.setFeed(item);
                videoBinding.listPlayerView.bindData(mCategory,item.width,item.height,item.cover,item.url);
            }
        }
    }
}
