package owl.icom.test;

import org.json.JSONArray;
import org.json.JSONException;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;

public class ItemListAdapter implements ListAdapter {

    private Context mContext;
    private JSONArray mData;
    private ImageLoader mImageLoader = ImageLoader.getInstance();
    private DisplayImageOptions mDisplayImageOptions;

    public ItemListAdapter(Context context, JSONArray data) {
        super();

        mContext = context;
        mData = data;
        mDisplayImageOptions = new DisplayImageOptions.Builder()
            .cacheInMemory(false)
            .cacheOnDisk(true)
            .considerExifParams(true)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .resetViewBeforeLoading(true)
            .build();
    }

    @Override
    public int getCount() {
        return mData.length();
    }

    @Override
    public Item getItem(int position) {
        try {
            return new Item(mData.getJSONObject(position));
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;

        if (itemView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = inflater.inflate(R.layout.fragment_list_item, null);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.image = (ImageView) itemView.findViewById(R.id.image);

            itemView.setTag(viewHolder);
        }

        Item item = getItem(position);

        if (item != null) {
            mImageLoader.displayImage(item.getImageURL(300, 181), ((ViewHolder) itemView.getTag()).image, mDisplayImageOptions);
        }

        return itemView;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver arg0) {
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver arg0) {
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    private static class ViewHolder {
        public ImageView image;
    }
}
