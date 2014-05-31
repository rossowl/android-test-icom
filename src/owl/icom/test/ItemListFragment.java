package owl.icom.test;

import org.json.JSONArray;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.ListView;

public class ItemListFragment extends ListFragment implements LoaderManager.LoaderCallbacks<JSONArray> {

    private static final int JSON_LOADER_ID = 1;
    private static final String JSON_LOADER_URL = "http://www.gservis.cz/buildings.json";

    public ItemListFragment() {
        super();

        setRetainInstance(true);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getLoaderManager().initLoader(JSON_LOADER_ID, null, this);
    }

    @Override
    public Loader<JSONArray> onCreateLoader(int id, Bundle args) {
        if (id == JSON_LOADER_ID) {
            return new JSONLoader(getActivity(), JSON_LOADER_URL);
        }

        return null;
    }

    @Override
    public void onLoadFinished(Loader<JSONArray> loader, JSONArray result) {
        if (getListAdapter() == null) {
            setListAdapter(new ItemListAdapter(getActivity(), result));
        }
    }

    @Override
    public void onLoaderReset(Loader<JSONArray> loader) {
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        Item item = (Item) getListAdapter().getItem(position);

        Intent intent = new Intent(getActivity(), ItemDetailActivity.class);
        intent.putExtra("owl.icom.test.Item", item);
        startActivity(intent);
    }
}
