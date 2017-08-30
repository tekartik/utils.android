package com.tekartik.utils.utils.device;

public class DeviceUtils {
	// To call using Build.PRODUCT
	public static boolean isEmulator(String buildProduct) {
		// 2017-08-29 Emulator Android 5.1.1/6.0/7.1.1: sdk_google_phone_x86
		return (buildProduct != null) && (((buildProduct.equals("sdk"))
				|| (buildProduct.equals("sdk_x86"))
				|| (buildProduct.equals("sdk_google_phone_x86"))));
	}
}