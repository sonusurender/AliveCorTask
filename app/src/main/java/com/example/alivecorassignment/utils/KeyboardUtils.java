package com.example.alivecorassignment.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.alivecorassignment.base.MyApplication;

/**
 * Created by sonu on 15:36, 18/01/21
 * Copyright (c) 2021 . All rights reserved.
 */
public class KeyboardUtils {
    /**
     * method to hide the keyboard
     * @param context
     * @param view
     */
    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * method to show the keyboard
     * @param view
     */
    public static void showKeyboardFrom(EditText view) {
        view.requestFocus();
        InputMethodManager imm = (InputMethodManager) MyApplication.getInstance().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }
}
