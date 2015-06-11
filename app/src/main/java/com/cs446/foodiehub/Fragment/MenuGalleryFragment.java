package com.cs446.foodiehub.Fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.cs446.foodiehub.Adapter.ImageAdapter;
import com.cs446.foodiehub.R;
import com.cs446.foodiehub.model.MenuItem;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Alex on 15-06-10.
 */
public class MenuGalleryFragment extends Fragment {

    private GridView gridView;
    private ArrayList<MenuItem> mPhotoUrls = new ArrayList<>();
    private ImageAdapter mImageAdapter;

    /**
     * Called when the activity is first created.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_gallery, container, false);


        final Button submit = (Button) rootView.findViewById(R.id.btn_submit);
        final EditText tableNumber = (EditText) rootView.findViewById(R.id.et_table_number);

        loadMenu();
        gridView = (GridView) rootView.findViewById(R.id.gv_menu_gallery);


        mImageAdapter = new ImageAdapter(getActivity(), mPhotoUrls);
        gridView.setAdapter(mImageAdapter);

//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View v,
//                                    int position, long id) {
//                Toast.makeText(
//                        getActivity().getApplicationContext(),
//                        ((TextView) v.findViewById(R.id.grid_item_label))
//                                .getText(), Toast.LENGTH_SHORT).show();
//            }
//        });

        return rootView;
    }

    private View.OnClickListener mCheckmarkListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    private void loadMenu() {

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    // clear existing results
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mPhotoUrls.clear();
                            mImageAdapter.notifyDataSetChanged();
                        }
                    });

                    // do a google image search, get the ~10 paginated results
                    int start = 0;
                    final ArrayList<String> urls = new ArrayList<String>();
                    while (start < 40) {
                        DefaultHttpClient client = new DefaultHttpClient();
                        HttpGet get = new HttpGet(String.format("https://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=%s&start=%d&imgsz=medium", Uri.encode("Food"), start));
                        HttpResponse resp = client.execute(get);
                        HttpEntity entity = resp.getEntity();
                        InputStream is = entity.getContent();
                        final JSONObject json = new JSONObject(readToEnd(is));
                        is.close();
                        final JSONArray results = json.getJSONObject("responseData").getJSONArray("results");
                        for (int i = 0; i < results.length(); i++) {
                            JSONObject result = results.getJSONObject(i);
                            urls.add(result.getString("url"));
                        }

                        start += results.length();
                    }
                    // add the results to the adapter
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            for (String url : urls) {
                                mPhotoUrls.add(new MenuItem(url, Integer.toString((int)Math.random()*10+1)));
                                mImageAdapter.notifyDataSetChanged();
                            }
                        }
                    });
                } catch (final Exception ex) {
                    // explodey error, lets toast it
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MenuGalleryFragment.this.getActivity(), ex.toString(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        };
        thread.start();
    }

    // turn a stream into a string
    private static String readToEnd(InputStream input) throws IOException {
        DataInputStream dis = new DataInputStream(input);
        byte[] stuff = new byte[1024];
        ByteArrayOutputStream buff = new ByteArrayOutputStream();
        int read = 0;
        while ((read = dis.read(stuff)) != -1) {
            buff.write(stuff, 0, read);
        }

        return new String(buff.toByteArray());
    }
}
