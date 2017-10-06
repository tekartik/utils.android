package com.tekartik.android.utils;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

import com.tekartik.utils.core.IntegerUtils;

import static com.tekartik.android.utils.CursorUtils.getFirstIntAndClose;

/**
 * Created by alex on 14/09/17.
 */

public class SqlCursorUtils {

    static public String COUNT_PROJECTION[] = {"COUNT(*) AS " + BaseColumns._COUNT};

    static public int count(ContentResolver cr, Uri contentUri, String selection, String selectionArgs[]) {
        Cursor cursor = cr.query(contentUri, COUNT_PROJECTION, selection, selectionArgs, null);
        return (int) IntegerUtils.nonNull(getFirstIntAndClose(cursor), 0);
    }
}
