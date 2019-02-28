package com.tekartik.android.utils;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;

/**
 * Created by alex on 14/09/17.
 */

public class CursorUtils {

    static public Long getFirstLongAndClose(Cursor cursor) {
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    return cursor.getLong(0);
                }
            } catch (Exception e) {
            } finally {
                cursor.close();
            }
        }
        return null;
    }

    static public String getFirstStringAndClose(Cursor cursor) {
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    return cursor.getString(0);
                }
            } catch (Exception e) {
            } finally {
                cursor.close();
            }
        }
        return null;
    }

    static public Integer getFirstIntAndClose(Cursor cursor) {
        Long longValue = getFirstLongAndClose(cursor);
        if (longValue != null) {
            return longValue.intValue();
        }
        return null;
    }


    static public ContentValues getFirstContentValuesAndClose(Cursor cursor) {
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    ContentValues cv = new ContentValues();
                    DatabaseUtils.cursorRowToContentValues(cursor, cv);
                    return cv;
                }
            } catch (Exception e) {
            } finally {
                cursor.close();
            }
        }
        return null;
    }


}
