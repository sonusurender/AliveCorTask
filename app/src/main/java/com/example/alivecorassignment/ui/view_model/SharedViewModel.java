package com.example.alivecorassignment.ui.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.alivecorassignment.model.UserProfile;

/**
 * Created by sonu on 13:53, 18/01/21
 * Copyright (c) 2021 . All rights reserved.
 */
public class SharedViewModel extends AndroidViewModel {

    private final MutableLiveData<UserProfile> userProfileMutableLiveData = new MutableLiveData<>();

    public SharedViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * update the live date value
     * @param userProfile
     */
    public void setUserProfileMutableLiveData(UserProfile userProfile) {
        userProfileMutableLiveData.postValue(userProfile);
    }

    /**
     * observe the live data
     * @return
     */
    public MutableLiveData<UserProfile> getUserProfileMutableLiveData() {
        return userProfileMutableLiveData;
    }
}
