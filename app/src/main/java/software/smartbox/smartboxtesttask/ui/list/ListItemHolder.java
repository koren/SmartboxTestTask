package software.smartbox.smartboxtesttask.ui.list;

import android.support.v7.widget.RecyclerView;

import software.smartbox.smartboxtesttask.data.ELocationType;
import software.smartbox.smartboxtesttask.data.Location;
import software.smartbox.smartboxtesttask.databinding.ListItemBinding;

public class ListItemHolder extends RecyclerView.ViewHolder {

    private final ListItemBinding binding;

    public ListItemHolder(ListItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Location location, ELocationType type, Callback callback) {
        binding.setViewModel(new ListItemViewModel(location, type));
        binding.setClickListener(v -> callback.onLocationSelected(location));
        binding.executePendingBindings();
    }
}
