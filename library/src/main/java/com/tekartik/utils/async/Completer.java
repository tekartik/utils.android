package com.tekartik.utils.async;

import androidx.annotation.NonNull;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by alex on 09/09/17.
 */

public class Completer<T> {

    boolean cancelled = false;
    boolean done = false;
    T value;
    Exception exception;

    static public class Void extends Completer<java.lang.Void> {
        public boolean complete() {
            return complete(null);
        }
    }

    public class Future implements java.util.concurrent.Future<T> {
        @Override
        public boolean cancel(boolean mayInterruptIfRunning) {
            cancelled = true;
            return completeError(new CancellationException());
        }

        @Override
        public boolean isCancelled() {
            return cancelled;
        }

        @Override
        public boolean isDone() {
            return done;
        }

        private T doneGetOrThrow() throws ExecutionException {
            if (exception != null) {
                throw new ExecutionException(exception);
            }
            return value;
        }

        /**
         * Wait for the operation to complete (infinite)
         *
         * @return
         * @throws InterruptedException
         * @throws ExecutionException
         */
        @Override
        public T get() throws ExecutionException {
            if (isDone()) {
                return doneGetOrThrow();
            }
            synchronized (lock) {
                while (true) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (isDone()) {
                        return doneGetOrThrow();
                    }
                }

            }
        }

        /**
         * Wait for the operation to complete (millisecond precision)
         *
         * @param timeout
         * @param unit
         * @return
         * @throws InterruptedException
         * @throws ExecutionException
         * @throws TimeoutException
         */
        @Override
        public T get(long timeout, @NonNull TimeUnit unit) throws ExecutionException, TimeoutException {
            if (isDone()) {
                return doneGetOrThrow();
            }
            if (timeout == 0) {
                throw new TimeoutException();
            }
            synchronized (lock) {
                while (true) {
                    try {
                        lock.wait(unit.toMillis(timeout));
                        // Either timeout or done
                        if (isDone()) {
                            return doneGetOrThrow();
                        } else {
                            throw new TimeoutException();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (isDone()) {
                        return doneGetOrThrow();
                    }
                }
            }
        }

        /**
         * Wait for the operation to complete (millisecond precision)
         *
         * @param msTimeout
         * @return
         * @throws InterruptedException
         * @throws ExecutionException
         * @throws TimeoutException
         */
        public T get(long msTimeout) throws ExecutionException, TimeoutException {
            return get(msTimeout, TimeUnit.MILLISECONDS);
        }
    }
    final Object lock = new Object();
    Future future = new Future();

    /**
     * complete the future with an error if not completed yet
     * @param e
     * @return true if not completed before
     */
    synchronized public boolean completeError(Exception e) {
        if (!future.isDone()) {
            synchronized (lock) {
                exception = e;
                this.done = true;
                lock.notifyAll();
            }
            return true;
        }
        return false;
    }

    /**
     * complete the future if not completed yet
     *
     * @param value
     * @return true if not completed before
     */
    synchronized public boolean complete(T value) {
        if (!future.isDone()) {
            synchronized (lock) {
                this.value = value;
                this.done = true;
                lock.notifyAll();
            }
            return true;
        }
        return false;
    }

    /**
     * The completer future
     *
     * @return
     */
    public Future getFuture() {
        return future;
    }
}
