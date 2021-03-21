package com.example.alarstudios.fragments.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alarstudios.R;
import com.example.alarstudios.constants.WebConstants;
import com.example.alarstudios.databinding.DataItemBinding;
import com.example.alarstudios.fragments.data_list.DataListContract;
import com.example.alarstudios.models.Human;
import com.example.alarstudios.utils.DownloadPictureTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter for list of data which you get from server after successful login
 *
 * @author Vladislav Kuzmin
 * @since 1.0
 */
public class DataListAdapter extends RecyclerView.Adapter<DataListAdapter.ViewHolder> {
    private List<Human> humans;
    public DataListContract.Fragment fragment;

    public DataListAdapter(DataListContract.Fragment fragment) {
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DataItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.data_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(humans.get(position));
    }

    @Override
    public int getItemCount() {
        return humans.size();
    }

    /**
     * Setting data to Recycler View
     *
     * @param list - list of humans. Our data
     * @since 1.0
     */
    public void setData(List<Human> list) {
        if (humans == null) {
            humans = new ArrayList<>();
        }
        humans.addAll(list);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private DataItemBinding binding;

        public ViewHolder(DataItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        /**
         * Method which binds data to RecyclerView item
         *
         * @param human - data item
         * @since 1.0
         */
        public void bind(Human human) {
            binding.tvName.setText(human.getName());
            binding.ivPicture.setTag(R.id.glide_tag, WebConstants.PICTURE_URL + getLayoutPosition());
            new DownloadPictureTask().execute(binding.ivPicture);
            binding.container.setOnClickListener(view -> fragment.goToDetailedInfo(human));
        }
    }


    /**
     * Callback that works if you will scroll to the last position in the RecyclerView
     *
     * @param holder - ViewHolder
     */
    @Override
    public void onViewAttachedToWindow(@NonNull ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        int layoutPosition = holder.getLayoutPosition();
        if (layoutPosition == getItemCount() - 1) {
            fragment.setDataToRecycler();
        }
    }
}
