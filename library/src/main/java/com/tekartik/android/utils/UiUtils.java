package com.tekartik.android.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

/**
 * Created by alex on 16/12/16.
 */

public class UiUtils {

    static public void setFocusAndShowKeyboard(View view) {
        view.requestFocus();
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    static public void hideKeyboard(Activity activity) {
        InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        try {
            View view = activity.getCurrentFocus();
            //If no view currently has focus, create a new one, just so we can grab a window token from it
            if (view == null) {
                view = new View(activity);
            }
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0); //InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Need after async operation to make sure the activity is still present
    static public boolean isValid(Activity activity) {
        return activity != null && (!activity.isFinishing()); // && (!activity.isDestroyed());
    }

    // Need after async operation to make sure the activity is still present
    static public boolean isValid(Fragment fragment) {
        return !fragment.isDetached() && isValid(fragment.getActivity());
    }

    // deprecate to catch usage
    @Deprecated
    static public void toastNotImplemented(Context context) {
        Toast.makeText(context, "NOT IMPLEMENTED", Toast.LENGTH_SHORT).show();
    }

    static public int getTop(View view) {
        int[] location = new int[2];
        view.getLocationInWindow(location);
        return location[1];
    }
    /*
    static public boolean isValid(Context context) {
        if (context instanceof  Fragment) {
            return isValid((Fragment)context);
        } else if (context instanceof  Activity) {
            return isValid((Fragment)context);
        }
        return false;
    }
    */
}
