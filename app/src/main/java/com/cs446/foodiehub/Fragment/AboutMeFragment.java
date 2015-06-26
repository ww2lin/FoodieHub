package com.cs446.foodiehub.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cs446.foodiehub.Api.UserRequest;
import com.cs446.foodiehub.Interface.ServerResponse;
import com.cs446.foodiehub.R;
import com.cs446.foodiehub.Util.Util;
import com.cs446.foodiehub.model.AboutMe;

/**
 * Created by Alex on 15-06-09.
 */
public class AboutMeFragment extends FoodieHubFragment {

    private ImageView profilePhoto;
    private TextView email;
    private TextView name;
    private TextView balance;

    public AboutMeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_aboutme, container, false);

        profilePhoto = (ImageView) rootView.findViewById(R.id.iv_profile);
        email = (TextView) rootView.findViewById(R.id.tv_email);
        name = (TextView) rootView.findViewById(R.id.tv_name);
        balance = (TextView) rootView.findViewById(R.id.tv_balance);

        UserRequest.getUser(serverResponse);

        return rootView;
    }

    private ServerResponse serverResponse = new ServerResponse() {
        @Override
        public void onSuccess(int statusCode, String responseString) {
            try {
                AboutMe aboutMe = Util.getMapper().readValue(responseString, AboutMe.class);
                email.setText(aboutMe.getEmail());
                name.setText(aboutMe.getName());
                balance.setText(aboutMe.getBalance());
            } catch (Exception e){
            }
        }

        @Override
        public void onFailure(int statusCode, String responseString) {
            // redirect to login screen if this happens.
        }
    };
}
