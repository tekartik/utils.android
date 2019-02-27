package com.tekartik.android.utils;

import android.content.Context;
import android.database.MatrixCursor;
import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by alex on 30/08/16.
 * <p>
 * test fetching data
 */
@RunWith(AndroidJUnit4.class)
public class CursorUtilsTest {


    static private Context getContext() {
        return InstrumentationRegistry.getContext();
    }

    MatrixCursor singleColumnCursor() {
        return new MatrixCursor(new String[]{"test"});
    }
    @Test
    public void firstNull() throws ExecutionException, InterruptedException {
        MatrixCursor cursor = singleColumnCursor();
        assertFalse(cursor.isClosed());
        assertNull(CursorUtils.getFirstStringAndClose(cursor));
        assertTrue(cursor.isClosed());
        cursor = singleColumnCursor();
        assertFalse(cursor.isClosed());
        assertNull(CursorUtils.getFirstIntAndClose(cursor));
        assertTrue(cursor.isClosed());
        cursor = singleColumnCursor();
        assertFalse(cursor.isClosed());
        assertNull(CursorUtils.getFirstIntAndClose(cursor));
        assertTrue(cursor.isClosed());
        cursor = singleColumnCursor();
        assertFalse(cursor.isClosed());
        assertNull(CursorUtils.getFirstContentValuesAndClose(cursor));
        assertTrue(cursor.isClosed());
    }

    @Test
    public void firstString() throws ExecutionException, InterruptedException {
        MatrixCursor cursor = new MatrixCursor(new String[] {"test"});
        cursor.addRow(new String[] {"value"});
        assertFalse(cursor.isClosed());
        assertEquals("value",CursorUtils.getFirstStringAndClose(cursor));
        assertTrue(cursor.isClosed());
    }

    @Test
    public void firstInt() throws ExecutionException, InterruptedException {
        MatrixCursor cursor = new MatrixCursor(new String[] {"test"});
        cursor.addRow(new Integer[] { 1 });
        assertFalse(cursor.isClosed());
        assertEquals(1, (int)CursorUtils.getFirstIntAndClose(cursor));
        assertTrue(cursor.isClosed());
    }

    @Test
    public void firstLong() throws ExecutionException, InterruptedException {
        MatrixCursor cursor = new MatrixCursor(new String[] {"test"});
        cursor.addRow(new Long[] { 1234567890123456L });
        assertFalse(cursor.isClosed());
        assertEquals(1234567890123456L, (long)CursorUtils.getFirstLongAndClose(cursor));
        assertTrue(cursor.isClosed());
    }
}
