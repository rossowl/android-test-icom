package owl.icom.test;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ItemDetailPageFragment extends Fragment {
    
    private static final ImageLoader mImageLoader = ImageLoader.getInstance();
    private static final DisplayImageOptions mDisplayImageOptions = new DisplayImageOptions.Builder()
        .cacheInMemory(false)
        .cacheOnDisk(true)
        .considerExifParams(true)
        .bitmapConfig(Bitmap.Config.RGB_565)
        .resetViewBeforeLoading(true)
        .build();

    public static Fragment newInstance(Item item, int position) {
        ItemDetailPageFragment fragment = new ItemDetailPageFragment();
        
        Bundle args = new Bundle();
        args.putParcelable("item", item);
        args.putInt("page", position);
        fragment.setArguments(args);

        return fragment;
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = (View) inflater.inflate(R.layout.fragment_detail_page, container, false);
        
        int position = getArguments().getInt("page");
        Item item = (Item) getArguments().getParcelable("item");
        ImageView imageView = (ImageView) rootView.findViewById(R.id.image);
        
        mImageLoader.displayImage(item.getImageURL(1280, 772, position), imageView, mDisplayImageOptions);
        
        return rootView;
    }
}
