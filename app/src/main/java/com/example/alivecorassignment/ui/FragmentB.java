package com.example.alivecorassignment.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.alivecorassignment.R;
import com.example.alivecorassignment.base.MyApplication;
import com.example.alivecorassignment.helper.ViewModelFactory;
import com.example.alivecorassignment.databinding.FragmentBBinding;
import com.example.alivecorassignment.ui.view_model.SharedViewModel;
import com.example.alivecorassignment.utils.DateUtils;

/**
 * Created by sonu on 12:35, 18/01/21
 * Copyright (c) 2021 . All rights reserved.
 */
public class FragmentB extends Fragment {

    private static final String TAG = FragmentB.class.getSimpleName();

    public FragmentB() {
    }

    private FragmentBBinding binding;
    private SharedViewModel sharedViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentBBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        sharedViewModel = new ViewModelProvider(getActivity(), new ViewModelFactory(MyApplication.getInstance())).get(SharedViewModel.class);
        updateUserProfileUI();
        onBackClick();
        return view;
    }

    private void onBackClick() {
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigateUp();
            }
        });
    }

    /**
     * method to update the views via LiveData
     */
    private void updateUserProfileUI() {
        sharedViewModel.getUserProfileMutableLiveData().observe(getViewLifecycleOwner(), userProfile -> {
            if (userProfile != null) {
                binding.tvFirstName.setText(String.format(getResources().getString(R.string.first_name_format), userProfile.getFirstName()));
                binding.tvLastName.setText(String.format(getResources().getString(R.string.last_name_format), userProfile.getLastName()));
                binding.tvAge.setText(String.format(getResources().getString(R.string.age_format), DateUtils.getAge(userProfile.getDob())));
            } else {
                Log.e(TAG, "UserProfile object is null.");
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
