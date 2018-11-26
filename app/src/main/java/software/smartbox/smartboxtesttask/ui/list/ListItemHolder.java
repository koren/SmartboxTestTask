package software.smartbox.smartboxtesttask.ui.list;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import software.smartbox.smartboxtesttask.data.Location;
import software.smartbox.smartboxtesttask.databinding.ListItemBinding;

public class ListItemHolder extends RecyclerView.ViewHolder {

    private final ListItemBinding binding;

    public ListItemHolder(ListItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Location item) {
        binding.setViewModel(new ListItemViewModel(item));
        binding.executePendingBindings();
    }
}
