package owl.icom.test;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable {

    public String name;
    public String pid;

    public Item(JSONObject item) throws JSONException {
        name = item.getString("name");
        pid = item.getString("pid");
    }

    public String getImageURL(int width, int height) {
        return "http://www.gservis.cz/image/" + width + "x" + height + "/media/buildings/" + pid + "/foto.jpg";
    }

    public String getImage2URL(int width, int height) {
        return "http://www.gservis.cz/image/" + width + "x" + height + "/media/buildings/" + pid + "/perspektiva-1.jpg";
    }

    public String getImageURL(int width, int height, int position) {
        if (position == 0) {
            return getImageURL(width, height);
        }
        
        return getImage2URL(width, height);
    }
    
    public int getImagesCount() {
        return 2;
    }
    

    public Item(Parcel in) {
        String[] data = new String[2];

        in.readStringArray(data);
        name = data[0];
        pid = data[1];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {name, pid});
    }

    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {

        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}
