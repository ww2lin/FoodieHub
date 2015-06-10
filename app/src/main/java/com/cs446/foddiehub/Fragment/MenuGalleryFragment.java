package com.cs446.foddiehub.Fragment;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.cs446.foddiehub.Adapter.ImageAdapter;
import com.cs446.foddiehub.R;
import com.koushikdutta.urlimageviewhelper.UrlImageViewCallback;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

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

    private ListView mListView;
    private MyAdapter mAdapter;

    private class Row extends ArrayList {

    }

    private class MyGridAdapter extends BaseAdapter {
        public MyGridAdapter(Adapter adapter) {
            mAdapter = adapter;
            mAdapter.registerDataSetObserver(new DataSetObserver() {
                @Override
                public void onChanged() {
                    super.onChanged();
                    notifyDataSetChanged();
                }

                @Override
                public void onInvalidated() {
                    super.onInvalidated();
                    notifyDataSetInvalidated();
                }
            });
        }

        Adapter mAdapter;

        @Override
        public int getCount() {
            return (int) Math.ceil((double) mAdapter.getCount() / 4d);
        }

        @Override
        public Row getItem(int position) {
            Row row = new Row();
            for (int i = position * 4; i < 4; i++) {
                if (mAdapter.getCount() < i)
                    row.add(mAdapter.getItem(i));
                else
                    row.add(null);
            }
            return row;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater(null).inflate(R.layout.row, null);
            LinearLayout row = (LinearLayout) convertView;
            LinearLayout l = (LinearLayout) row.getChildAt(0);
            for (int child = 0; child < 4; child++) {
                int i = position * 4 + child;
                LinearLayout c = (LinearLayout) l.getChildAt(child);
                c.removeAllViews();
                if (i < mAdapter.getCount()) {
                    c.addView(mAdapter.getView(i, null, null));
                }
            }

            return convertView;
        }

    }

    private class MyAdapter extends ArrayAdapter<String> {

        public MyAdapter(Context context) {
            super(context, 0);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null)
                convertView = getLayoutInflater(null).inflate(R.layout.panel_image, null);

            final ImageView iv = (ImageView) convertView.findViewById(R.id.image);

            iv.setAnimation(null);
            // yep, that's it. it handles the downloading and showing an interstitial image automagically.
            UrlImageViewHelper.setUrlDrawable(iv, getItem(position), R.drawable.loading, new UrlImageViewCallback() {
                @Override
                public void onLoaded(ImageView imageView, Bitmap loadedBitmap, String url, boolean loadedFromCache) {
                    if (!loadedFromCache) {
                        ScaleAnimation scale = new ScaleAnimation(0, 1, 0, 1, ScaleAnimation.RELATIVE_TO_SELF, .5f, ScaleAnimation.RELATIVE_TO_SELF, .5f);
                        scale.setDuration(300);
                        scale.setInterpolator(new OvershootInterpolator());
                        imageView.startAnimation(scale);
                    }
                }
            });

            return convertView;
        }
    }


    private int count;
    private Bitmap[] thumbnails;
    private boolean[] thumbnailsselection;
    private String[] arrPath;
    private ImageAdapter imageAdapter;


    /**
     * Called when the activity is first created.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_gallery, container, false);


        final Button submit = (Button) rootView.findViewById(R.id.btn_submit);
        final EditText tableNumber = (EditText) rootView.findViewById(R.id.et_table_number);

        mListView = (ListView) rootView.findViewById(R.id.results);
        mAdapter = new MyAdapter(getActivity());
        MyGridAdapter a = new MyGridAdapter(mAdapter);
        mListView.setAdapter(a);
        loadMenu();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Fragment mFragment = new FoodOrderFragment();
//
//                FragmentTransaction ft = getFragmentManager().beginTransaction();
//                //Replacing using the id of the container and not the fragment itself
//                ft.replace(R.id.container, mFragment);
//                ft.commit();
            }
        });
        return rootView;
    }

    private void loadMenu() {

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    // clear existing results
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mAdapter.clear();
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
                                mAdapter.add(url);
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
}
