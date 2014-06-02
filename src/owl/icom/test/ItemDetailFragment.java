package owl.icom.test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viewpagerindicator.UnderlinePageIndicator;

public class ItemDetailFragment extends Fragment {

    private ViewPager mPager;
    private UnderlinePageIndicator mIndicator;

    public ItemDetailFragment() {
        super();

        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        
        Item item = (Item) getArguments().getParcelable("item");

        mPager = (ViewPager) rootView.findViewById(R.id.pager);
        mPager.setAdapter(new ItemDetailPagerAdapter(getActivity().getSupportFragmentManager(), item));
        mPager.setOffscreenPageLimit(1);
        
        mIndicator = (UnderlinePageIndicator) rootView.findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);

        return rootView;
    }
}
