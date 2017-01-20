package kein.navihelper.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import kein.navihelper.service.ClipboardListenerService;

/**
 * Created by kein on 2017. 1. 21..
 */

public class BootCompleteReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(context, ClipboardListenerService.class));
    }
}
