package com.tekartik.utils.version;

/**
 * Handle version "1.2.3" or "v1.2.3"
 */

public class Version {

    private int major;
    private int minor;
    private int patch;

    public Version(int major, int minor, int patch) {
        this.major = major;
        this.minor = minor;
        this.patch = patch;
    }

    static public Version parse(String versionName) {
        try {
            if (versionName != null) {
                if (versionName.startsWith("v")) {
                    versionName = versionName.substring(1);
                }
                String[] parts = versionName.split("\\.");
                int major = Integer.parseInt(parts[0]);
                int minor = 0;
                int patch = 0;
                if (parts.length > 1) {
                    minor = Integer.parseInt(parts[1]);
                    if (parts.length > 2) {
                        patch = Integer.parseInt(parts[2].split("[-+]")[0]);
                    }
                }
                return new Version(major, minor, patch);

            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public String toString() {
        return major + "." + minor + "." + patch;
    }

    public int getMajor() {
        return major;
    }

    public int getMinor() {
        return minor;
    }
}
