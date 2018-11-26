package software.smartbox.smartboxtesttask.ui.list;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

import software.smartbox.smartboxtesttask.data.Location;

public class ListItemDiffCallback extends DiffUtil.ItemCallback<Location> {
    @Override
    public boolean areItemsTheSame(@NonNull Location oldItem, @NonNull Location newItem) {
        return oldItem.getId().equals(newItem.getId());
    }

    @Override
    public boolean areContentsTheSame(@NonNull Location oldItem, @NonNull Location newItem) {
        return oldItem == newItem;
    }
}
