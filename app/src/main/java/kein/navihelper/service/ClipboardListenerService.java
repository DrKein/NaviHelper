package kein.navihelper.service;

import android.app.Service;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import kein.navihelper.tool.ClipChangedListener;

/**
 * Created by kein on 2017. 1. 21..
 *
 * 1. 클립보드 변경 이벤트는 broadcast receiver 로 전달되지 않는군.
 * 2. 내가 서비스를 만들어서 클립보드 매니저에 리스너를 걸어야 함.
 * 3. 내 서비스는 백그라운드에 항상 대기 되야 함
 */

public class ClipboardListenerService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        setClipboardListener();
        return super.onStartCommand(intent, flags, startId);
    }

    private void setClipboardListener() {
        ClipboardManager clipBoard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
        clipBoard.addPrimaryClipChangedListener(new ClipChangedListener(this));
    }



}
