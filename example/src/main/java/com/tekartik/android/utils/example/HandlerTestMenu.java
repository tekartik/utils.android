package com.tekartik.android.utils.example;

import android.os.Handler;
import android.util.Log;

import com.tekartik.android.utils.BgTask;
import com.tekartik.android.utils.handler.DelayedBusyHandler;
import com.tekartik.android.utils.handler.DelayedBusyListHandler;
import com.tekartik.android.utils.handler.DelayedBusyMapHandler;
import com.tekartik.android.utils.handler.DelayedHandlerListener;
import com.tekartik.android.utils.handler.DelayedIdleHandler;
import com.tekartik.android.utils.handler.SingleOperationHandler;
import com.tekartik.testmenu.Test;
import com.tekartik.utils.core.TimestampUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by alex on 06/10/17.
 */

public class HandlerTestMenu extends Test.Menu {
    int count = 0;
    DelayedBusyHandler delayedBusyHandler;
    DelayedIdleHandler delayedIdleHandler;
    DelayedBusyMapHandler<String, String> delayedBusyMapHandler;
    DelayedBusyListHandler<String> delayedBusyListHandler;
    Handler triggerRepeatHandler = new Handler();
    long repeatStartTimestamp;
    long repeatDuration;
    long repeatDelay;
    Listener repeatTriggerListener; //DelayedHandler repeatDelayedHandler;
    SingleOperationHandler singleOperationHandler = new SingleOperationHandler(singleOperationListener);
    SingleOperationHandler.Listener singleOperationListener = new SingleOperationHandler.Listener() {
        @Override
        public void onHandle() {
            showToast("Starting 5s operation");
            new BgTask() {
                @Override
                protected void doInBackground() throws Exception {
                    Thread.sleep(5000);
                }

                @Override
                protected void onPostExecute() {
                    showToast("Ending 5s operation");
                    singleOperationHandler.done();
                }
            }.execute();
        }
    };


    protected HandlerTestMenu() {
        super("Handler");
    }

    void triggerAndRepeat() {
        repeatTriggerListener.onTrigger();
        if (!TimestampUtils.elapsed(repeatStartTimestamp, new Date().getTime(), repeatDuration)) {
            Log.d(TAG, "trigger");
            triggerRepeatHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    triggerAndRepeat();
                }
            }, repeatDelay);
        }
    }

    void triggerDuring(final long duration, long delay, final Listener triggerListener) {
        repeatDuration = duration;
        repeatDelay = delay;
        repeatTriggerListener = triggerListener;
        repeatStartTimestamp = new Date().getTime();
        triggerAndRepeat();
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        initItems(
                new Item("Busy") {
                    @Override
                    public void execute() {
                        count = 0;
                        delayedBusyHandler = new DelayedBusyHandler(3000, new DelayedHandlerListener() {
                            @Override
                            public void onHandle() {
                                Log.d(TAG, "onHandle busy");
                                showToast("busy " + ++count);
                            }
                        });
                        triggerDuring(9000, 500, new Listener() {
                            @Override
                            public void onTrigger() {
                                delayedBusyHandler.trigger();
                            }
                        });
                    }
                },
                new Item("Idle wait for 4000") {
                    @Override
                    public void execute() {
                        count = 0;
                        delayedIdleHandler = new DelayedIdleHandler(3000, new DelayedHandlerListener() {
                            @Override
                            public void onHandle() {
                                Log.d(TAG, "onHandle idle");
                                showToast("idle " + ++count);
                            }
                        });
                        triggerDuring(5000, 500, new Listener() {
                            @Override
                            public void onTrigger() {
                                delayedBusyHandler.trigger();
                            }
                        });
                    }
                },
                new Item("Busy map") {
                    @Override
                    public void execute() {
                        count = 0;
                        delayedBusyMapHandler = new DelayedBusyMapHandler<>(3000, new DelayedBusyMapHandler.Listener<String, String>() {

                            @Override
                            public void onHandle(Map<String, String> map) {
                                Log.d(TAG, "onHandle busy map " + map);
                                showToast("busy map " + map);
                            }
                        });
                        final List<String> keys = Arrays.asList("key1", "key2");
                        triggerDuring(9000, 500, new Listener() {
                            @Override
                            public void onTrigger() {
                                String key = keys.get(new Random().nextInt(keys.size()));
                                String value = new Date().toString();
                                Log.d(TAG, "Putting " + key + " " + value);
                                delayedBusyMapHandler.put(key, value);
                            }
                        });
                    }
                },

                new Item("Busy list") {
                    @Override
                    public void execute() {
                        count = 0;
                        delayedBusyListHandler = new DelayedBusyListHandler<>(3000, new DelayedBusyListHandler.Listener<String>() {

                            @Override
                            public void onHandle(List<String> list) {
                                Log.d(TAG, "onHandle busy list " + list);
                                showToast("busy list " + list);
                            }
                        });
                        triggerDuring(9000, 500, new Listener() {
                            @Override
                            public void onTrigger() {
                                String value = new Date().toString();
                                Log.d(TAG, "Adding " + value);
                                delayedBusyListHandler.add(value);
                            }
                        });
                    }
                },

                new Item("Trigger single operation") {

                    @Override
                    public void execute() {
                        singleOperationHandler.trigger();
                    }
                },
                null


        );

    }

    interface Listener {
        void onTrigger();
    }
}
