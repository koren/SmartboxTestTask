package software.smartbox.smartboxtesttask.databinding;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.List;

import software.smartbox.smartboxtesttask.data.Location;
import software.smartbox.smartboxtesttask.ui.list.ListAdapter;

public class CommonBindingUtils {

    @BindingAdapter("app:imageUrl")
    public static void bind(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(view);
    }

    @BindingAdapter({"app:adapter", "app:data"})
    public static void bind(RecyclerView view, ListAdapter adapter, List<Location> data) {
        view.setAdapter(adapter);
        adapter.submitList(data);
    }
}
