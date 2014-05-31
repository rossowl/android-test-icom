package owl.icom.test;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

public class JSONLoader extends AsyncTaskLoader<JSONArray> {

    private String mUrl;

    public JSONLoader(Context context, String url) {
        super(context);

        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public JSONArray loadInBackground() {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(mUrl).openConnection();
            InputStream input = new BufferedInputStream(connection.getInputStream());
            String body = streamToString(input);

            return new JSONArray(body);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String streamToString(InputStream stream) throws UnsupportedEncodingException {
        Writer writer = new StringWriter();
        InputStreamReader input = new InputStreamReader(new BufferedInputStream(stream), "UTF-8");

        try {
            final char[] buffer = new char[1024];
            int read;

            while ((read = input.read(buffer)) != -1) {
                writer.write(buffer, 0, read);
            }
            input.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return writer.toString();
    }
}
