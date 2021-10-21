package com.tekartik.android.utils.example;

import android.util.Log;
import android.widget.Toast;

import com.tekartik.android.utils.BgTask;
import com.tekartik.android.utils.ExecutorTask;
import com.tekartik.testmenu.Test;

/**
 * Created by alex on 06/10/17.
 */

public class ExecutorTaskTestMenu extends Test.Menu {

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

    protected ExecutorTaskTestMenu() {
        super("ExecutorTask");
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        initItems(
                new Item("ExecutorTask 2s") {
                    @Override
                    public void execute() {
                        new ExecutorTask() {
                            @Override
                            protected void doInBackground() throws Exception {
                                Thread.sleep(2000);
                            }

                            @Override
                            protected void onPostExecute() {
                                showToast("ExecutorTask Done " + (++id));
                                super.onPostExecute();
                            }
                        }.execute();
                    }
                },
                new Item("ExecutorTask throw") {
                    @Override
                    public void execute() {
                        new ExecutorTask() {
                            @Override
                            protected void doInBackground() throws Exception {
                                throw new Exception("throwing");
                            }

                            @Override
                            protected void onPostExecute() {
                                showToast("ExecutorTask Done " + (++id) + " " + getException().getMessage());
                                super.onPostExecute();
                            }
                        }.execute();
                    }
                },
                null


        );

    }

}
