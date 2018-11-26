package software.smartbox.smartboxtesttask.ui.list;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.AsyncDifferConfig;
import android.support.v7.recyclerview.extensions.AsyncListDiffer;
import android.support.v7.util.AdapterListUpdateCallback;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import software.smartbox.smartboxtesttask.R;
import software.smartbox.smartboxtesttask.data.ELocationType;
import software.smartbox.smartboxtesttask.data.Location;


public class ListAdapter extends RecyclerView.Adapter<ListItemHolder> {
    private AsyncListDiffer<Location> helper;
    private ELocationType type;
    public Callback callback;

    public ListAdapter(ListItemDiffCallback diffCallback) {
        helper = new AsyncListDiffer<>(new AdapterListUpdateCallback(this), new AsyncDifferConfig.Builder<>(diffCallback).build());
    }

    @NonNull
    @Override
    public ListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        return new ListItemHolder(
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.getContext()),
                        R.layout.list_item, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemHolder holder, int position) {
        Location item = helper.getCurrentList().get(position);
        holder.bind(item, type, callback);
    }

    @Override
    public int getItemCount() {
        return helper.getCurrentList().size();
    }

    public void submitList(List<Location> items, ELocationType type) {
        this.type = type;
        helper.submitList(items);
    }
}
