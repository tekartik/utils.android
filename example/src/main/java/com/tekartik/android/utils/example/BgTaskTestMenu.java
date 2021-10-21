package com.tekartik.android.utils.example;

import android.util.Log;
import android.widget.Toast;

import com.tekartik.android.utils.BgTask;
import com.tekartik.testmenu.Test;

/**
 * Created by alex on 06/10/17.
 */

public class BgTaskTestMenu extends Test.Menu {

    private int id = 0;
    Toast toast;
    public void showToast(String text) {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT);
        toast.show();
        Log.d(TAG, "[toast] " + text);
    }

    protected BgTaskTestMenu() {
        super("BgTask");

    }


    @Override
    protected void onCreate() {
        super.onCreate();
        initItems(
                new Item("BgTask 2s") {
                    @Override
                    public void execute() {
                        new BgTask() {
                            @Override
                            protected void doInBackground() throws Exception {
                                Thread.sleep(2000);
                            }

                            @Override
                            protected void onPostExecute() {
                                showToast("Done " + (++id));
                                super.onPostExecute();
                            }
                        }.execute();
                    }
                },
                new Item("BgTask throw") {
                    @Override
                    public void execute() {
                        new BgTask() {
                            @Override
                            protected void doInBackground() throws Exception {
                                throw new Exception("throwing");
                            }

                            @Override
                            protected void onPostExecute() {
                                showToast("Done " + (++id));
                                super.onPostExecute();
                            }
                        }.execute();
                    }
                },
                null


        );

    }

}
