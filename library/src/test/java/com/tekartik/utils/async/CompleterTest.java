package com.tekartik.utils.async;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(JUnit4.class)
public class CompleterTest {

    @Test
    public void complete() throws ExecutionException, InterruptedException {
        Completer<Integer> completer = new Completer<>();
        Completer.Future future = completer.getFuture();
        assertFalse(future.isCancelled());
        assertFalse(future.isDone());

        // should timeout
        try {
            future.get(0);
            fail();
        } catch (TimeoutException ignore) {}

        completer.complete(3);
        assertFalse(future.isCancelled());
        assertTrue(future.isDone());

        assertEquals(3, future.get());
    }

    @Test
    public void completeError() throws ExecutionException, InterruptedException {
        Completer<Integer> completer = new Completer<>();
        Completer.Future future = completer.getFuture();
        assertFalse(future.isCancelled());
        assertFalse(future.isDone());

        // should timeout
        try {
            future.get(0);
            fail();
        } catch (TimeoutException ignore) {}

        completer.completeError(new Exception("test"));
        assertFalse(future.isCancelled());
        assertTrue(future.isDone());

        try {
            future.get();
            fail();
        } catch (ExecutionException e) {
            assertEquals("test", e.getCause().getMessage());
        }
    }

    @Test
    public void timeout() throws ExecutionException, InterruptedException {
        Completer<Integer> completer = new Completer<>();
        Completer.Future future = completer.getFuture();
        assertFalse(future.isCancelled());
        assertFalse(future.isDone());

        // should timeout
        try {
            future.get(1);
            fail();
        } catch (TimeoutException ignore) {}

        future.cancel(true);
        assertTrue(future.isCancelled());
        assertTrue(future.isDone());

        try {
            future.get();
            fail();
        } catch (ExecutionException e) {
            assertTrue(e.getCause() instanceof CancellationException);
        }
    }

    @Test
    public void cancel() throws ExecutionException, InterruptedException {
        Completer<Integer> completer = new Completer<>();
        Completer.Future future = completer.getFuture();
        assertFalse(future.isCancelled());
        assertFalse(future.isDone());

        // should timeout
        try {
            future.get(0);
            fail();
        } catch (TimeoutException ignore) {}

        future.cancel(true);
        assertTrue(future.isCancelled());
        assertTrue(future.isDone());

        try {
            future.get();
            fail();
        } catch (ExecutionException e) {
            assertTrue(e.getCause() instanceof CancellationException);
        }
    }

}
