package com.tekartik.android.utils.example;

import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;

import com.tekartik.testmenu.Test;
import com.tekartik.utils.device.DeviceUtils;

public class MainMenuActivity extends Test.MenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Test.BuildConfig.DEBUG = BuildConfig.DEBUG;
        Test.Menu.setStartMenu(new MainTestMenu());
    }

    static public class MainTestMenu extends Test.Menu {

        protected MainTestMenu() {
            super("Main Menu");
        }

        @Override
        protected void onCreate() {
            super.onCreate();
            Log.i(TAG, "MainMenu");
            initItems(
                    new MenuItem(new HandlerTestMenu()),
                    new MenuItem(new BgTaskTestMenu()),
                    new MenuItem(new ExecutorTaskTestMenu()),
                    new Item("isEmulator") {
                        @Override
                        public void execute() {
                            showToast(Build.PRODUCT + "\nDeviceUtils.isEmulator(): " + DeviceUtils.isEmulator(Build.PRODUCT));
                        }
                    },
                    new Item("DeviceInfo") {

                        @Override
                        public void execute() {
                            StringBuilder sb = new StringBuilder();

                            Display display;
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
                                display = getActivity().getDisplay();
                            } else {
                                display = getActivity().getWindowManager().getDefaultDisplay();
                            }
                            //Display display = getActivity().getWindowManager().getDefaultDisplay();
                            DisplayMetrics outMetrics = new DisplayMetrics();
                            display.getMetrics(outMetrics);

                            sb.append(outMetrics.widthPixels + "x" + outMetrics.heightPixels + " px\n");
                            float density = getActivity().getResources().getDisplayMetrics().density;
                            float dpHeight = outMetrics.heightPixels / density;
                            float dpWidth = outMetrics.widthPixels / density;
                            sb.append(Math.round(dpWidth) + "x" + Math.round(dpHeight) + " dp\n");
                            showToast(sb.toString());
                        }
                    },
                    null


            );

        }

    }
}
