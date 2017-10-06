package com.tekartik.android.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * adb -e shell setprop log.tag.tk VERBOSE
 * 
 * @author alex
 * 
 */
public class LogUtils {

	static int LOG_LEN_MAX = 64;

	static boolean debugLoggedOnce = false;

	private static void logOnce(String prefix) {
		if (!debugLoggedOnce) {
			Log.v(prefix, "LogPrefix " + prefix);
			debugLoggedOnce = true;
		}
	}

	/**
	 * Must be done right at the beginning
	 * 
	 * @param prefix
	 */
	static public void setPrefix(String prefix) {
		if (!prefix.equals(PREFIX)) {
			logOnce(prefix);
		}
		PREFIX = prefix;
	}

	static private String PREFIX = "tk";

	public static String tag(String name) {
		if (!debugLoggedOnce) {
			logOnce(PREFIX);
		}
		String tag = String.format("%s%s", PREFIX, name);
		if (isLoggable(PREFIX, Log.VERBOSE)) {
			Log.v(PREFIX, "logtag " + tag);
		}
		return tag;
	}

	public static String tag(Class<?> klass) {
		return tag(klass.getSimpleName());
	}

	public static boolean isLoggable(String tag, int level) {
		return (Log.isLoggable(tag, level));
	}

	static public java.text.DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss", Locale.getDefault());

	public static String getDateText(long ms) {
		return dateFormat.format(new Date(ms));
	}

	@Deprecated
	static public void logBundle(String tag, String msg, Bundle bundle) {
		Log.v(tag, msg);
		for (String key : bundle.keySet()) {
			Object value = bundle.get(key);
			Log.v(tag, String.format("%s %s (%s)", key, value.toString(), value.getClass().getName()));
		}

	}


	static private boolean shouldBeTruncated(String text) {
		return (!TextUtils.isEmpty(text) && text.length() > LOG_LEN_MAX);
	}

	static public String truncateJsonString(String text) {
		if (shouldBeTruncated(text)) {
			return text.substring(0, LOG_LEN_MAX / 4) + "[...]" + text.substring(text.length() - LOG_LEN_MAX / 4);
		}
		return text;

	}

	static public String truncateString(String text) {
		if (shouldBeTruncated(text)) {
			return text.substring(0, LOG_LEN_MAX / 2) + "[...]" + text.substring(text.length() - LOG_LEN_MAX / 2);
		}
		return text;

	}

	static public JSONObject truncateJson(JSONObject json) {
		List<String> keys = new ArrayList<>();
		Iterator<String> iterator = json.keys();
		while (iterator.hasNext()) {
			keys.add(iterator.next());
		}

		for (String key : keys) {
			try {
				Object value = json.opt(key);
				if (value instanceof String) {
					if (shouldBeTruncated((String) value)) {
						json.put(key, truncateJsonString((String) value));
					}
				} else if (value instanceof JSONObject) {
					json.put(key, truncateJson((JSONObject) value));
				}
			} catch (JSONException e) {
				// Something went wrong!
			}
		}

		return json;
	}

	static public String truncateLog(String text) {
		// if json object, truncate text content
		// Otherwise limit to 32 bytes each string, display 16 starts and 16 end chars
		try {
			JSONObject json = new JSONObject(text);
			json = truncateJson(json);
			text = json.toString();
		} catch (Exception e) {
			return truncateString(text);
		}
		return text;
	}
}
