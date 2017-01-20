package kein.navihelper.service;

import android.app.Service;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

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
        clipBoard.addPrimaryClipChangedListener(new ClipboardManager.OnPrimaryClipChangedListener() {
            @Override
            public void onPrimaryClipChanged() {
                ClipboardManager clipBoard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
                if(checkClipboard(clipBoard)) {
                    ClipData clip = clipBoard.getPrimaryClip();
                    ClipData.Item item = clip.getItemAt(0);
                    String text = item.getText().toString();
                    Toast.makeText(ClipboardListenerService.this, text, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    /** 클립보드 컨텐츠가 있는지, 텍스트 인지 */
    private boolean checkClipboard(ClipboardManager cm) {
        if(cm != null && cm.hasPrimaryClip() && cm.getPrimaryClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
            return true;
        }
        return false;
    }

}
