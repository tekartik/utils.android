package com.tekartik.android.utils.example;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.tekartik.testmenu.Test;
import com.tekartik.utils.device.DeviceUtils;

public class MainMenuActivity extends Test.MenuActivity {

    static public class MainTestMenu extends Test.Menu {

        protected MainTestMenu() {
            super("Main Menu");
        }

        @Override
        protected void onCreate() {
            super.onCreate();
            Log.i(TAG, "MainMenu");
            initItems(
                    new Item("isEmulator") {
                        @Override
                        public void execute() {
                            showToast(Build.PRODUCT + "\nDeviceUtils.isEmulator(): " + DeviceUtils.isEmulator(Build.PRODUCT));
                        }
                    }


            );

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Test.BuildConfig.DEBUG = BuildConfig.DEBUG;
        Test.Menu.setStartMenu(new MainTestMenu());
    }
}
