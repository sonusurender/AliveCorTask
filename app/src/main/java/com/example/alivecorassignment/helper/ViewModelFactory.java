package com.example.alivecorassignment.helper;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.alivecorassignment.ui.view_model.SharedViewModel;

/**
 * Created by sonu on 13:57, 18/01/21
 * Copyright (c) 2021 . All rights reserved.
 */
public class ViewModelFactory extends ViewModelProvider.AndroidViewModelFactory {

    private final Application application;
    /**
     * Creates a {@code AndroidViewModelFactory}
     *
     * @param application an application to pass in {@link AndroidViewModel}
     */
    public ViewModelFactory(@NonNull Application application) {
        super(application);
        this.application = application;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(SharedViewModel.class)) {
            return (T) new SharedViewModel(application);
        }
        throw new IllegalArgumentException("Wrong ViewModel class");
    }
}