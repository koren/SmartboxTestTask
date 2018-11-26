package software.smartbox.smartboxtesttask.binding;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

public class CommonBindingUtils {

    @BindingAdapter("app:imageUrl")
    public static void bind(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(view);
    }
}
