package owl.icom.test;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ItemDetailPagerAdapter extends FragmentStatePagerAdapter {

    private Item mItem;

    public ItemDetailPagerAdapter(FragmentManager fm, Item item) {
        super(fm);
        
        mItem = item;
    }

    @Override
    public Fragment getItem(int position) {
        return ItemDetailPageFragment.newInstance(mItem, position);
    }

    @Override
    public int getCount() {
        return mItem.getImagesCount();
    }
}
