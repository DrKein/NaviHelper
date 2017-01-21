package kein.navihelper.tool;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;

/**
 * Created by kein on 2017. 1. 21..
 */

public class PermissionCheck {

    public static boolean hasOverlayPermission(Context ctx) {
        boolean result = true;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            result = Settings.canDrawOverlays(ctx);
        }
        return result;
    }
}
