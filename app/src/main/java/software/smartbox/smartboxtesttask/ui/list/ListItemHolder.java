package software.smartbox.smartboxtesttask.ui.list;

import android.support.v7.widget.RecyclerView;

import software.smartbox.smartboxtesttask.data.Location;
import software.smartbox.smartboxtesttask.databinding.ListItemBinding;

class ListItemHolder extends RecyclerView.ViewHolder {

    private final ListItemBinding binding;

    ListItemHolder(ListItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    void bind(Location location, Callback callback) {
        binding.setViewModel(new ListItemViewModel(location));
        binding.setClickListener(v -> callback.onLocationSelected(location));
        binding.executePendingBindings();
    }
}
