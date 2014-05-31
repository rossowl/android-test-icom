package owl.icom.test;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ItemDetailFragment extends Fragment {

    private ImageLoader mImageLoader = ImageLoader.getInstance();
    private DisplayImageOptions mDisplayImageOptions;

    public ItemDetailFragment() {
        super();

        setRetainInstance(true);

        mDisplayImageOptions = new DisplayImageOptions.Builder()
            .cacheInMemory(false)
            .cacheOnDisk(true)
            .considerExifParams(true)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .resetViewBeforeLoading(true)
            .build();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        Item item = (Item) getArguments().getParcelable("item");
        ImageView imageView = (ImageView) rootView.findViewById(R.id.image);

        mImageLoader.displayImage(item.getImageURL(1280, 772), imageView, mDisplayImageOptions);

        return rootView;
    }
}
