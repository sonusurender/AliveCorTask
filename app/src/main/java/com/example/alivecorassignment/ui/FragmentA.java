package com.example.alivecorassignment.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.alivecorassignment.base.MyApplication;
import com.example.alivecorassignment.R;
import com.example.alivecorassignment.model.UserProfile;
import com.example.alivecorassignment.helper.ViewModelFactory;
import com.example.alivecorassignment.databinding.FragmentABinding;
import com.example.alivecorassignment.ui.view_model.SharedViewModel;
import com.example.alivecorassignment.utils.DateUtils;
import com.example.alivecorassignment.utils.KeyboardUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by sonu on 12:35, 18/01/21
 * Copyright (c) 2021 . All rights reserved.
 */
public class FragmentA extends Fragment implements View.OnClickListener {

    public FragmentA() {
    }

    private FragmentABinding binding;
    private final Calendar myCalendar = Calendar.getInstance();
    private SharedViewModel sharedViewModel;
    private Date dob;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentABinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        sharedViewModel = new ViewModelProvider(getActivity(), new ViewModelFactory(MyApplication.getInstance())).get(SharedViewModel.class);
        implementClickEvent();
        return view;
    }

    private void implementClickEvent() {
        binding.etDob.setOnClickListener(this);
        binding.btnNext.setOnClickListener(this);
    }

    /**
     * show date picker
     */
    DatePickerDialog.OnDateSetListener dateSetListener = (view, year, monthOfYear, dayOfMonth) -> {
        myCalendar.set(Calendar.YEAR, year);
        myCalendar.set(Calendar.MONTH, monthOfYear);
        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        updateLabel();
    };

    /**
     * update date label after selecting date
     */
    private void updateLabel() {
        dob = myCalendar.getTime();
        binding.etDob.setText(DateUtils.parseDate(myCalendar.getTime(), DateUtils.DATE_FORMAT));
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.etDob:
                KeyboardUtils.hideKeyboardFrom(getActivity(), view);
                DatePickerDialog datePickerDialog =  new DatePickerDialog(getContext(), dateSetListener, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
               datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
               datePickerDialog.show();
                break;
            case R.id.btnNext:
                validateInputs(view);
                break;
        }
    }

    /**
     * method to validate inputs and launch Fragment B
     * @param view
     */
    private void validateInputs(View view) {
        String getFirstName = binding.etFirstName.getText().toString().trim();
        String getLastName = binding.etLastName.getText().toString().trim();
        String getDob = binding.etDob.getText().toString().trim();

        if (TextUtils.isEmpty(getFirstName)) {
            binding.inputLayoutFirstName.setError(getResources().getString(R.string.error_first_name));
        } else if (TextUtils.isEmpty(getLastName)) {
            binding.inputLayoutLastName.setError(getResources().getString(R.string.error_last_name));
        } else if (TextUtils.isEmpty(getDob)) {
            binding.inputLayoutDob.setError(getResources().getString(R.string.error_dob));
        } else {
            KeyboardUtils.hideKeyboardFrom(getActivity(), view);

            UserProfile userProfile = new UserProfile();
            userProfile.setFirstName(getFirstName);
            userProfile.setLastName(getLastName);
            userProfile.setDob(dob);
            sharedViewModel.setUserProfileMutableLiveData(userProfile);

            Navigation.findNavController(view).navigate(R.id.fragmentB);
        }
    }
}
