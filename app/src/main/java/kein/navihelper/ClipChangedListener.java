package kein.navihelper;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;

import static android.content.Context.CLIPBOARD_SERVICE;

/**
 * Created by kein on 2017. 1. 21..
 */

public class ClipChangedListener implements ClipboardManager.OnPrimaryClipChangedListener {

    private Context mCtx;
    public ClipChangedListener(Context context) {
        mCtx = context;
    }

    @Override
    public void onPrimaryClipChanged() {
        ClipboardManager clipBoard = (ClipboardManager)mCtx.getSystemService(CLIPBOARD_SERVICE);
        if(checkClipboard(clipBoard)) {
            ClipData clip = clipBoard.getPrimaryClip();
            ClipData.Item item = clip.getItemAt(0);
            String text = item.getText().toString();

            if(AddressDetector.isAddress(text)) {
//                 ShowAppToast.show(mCtx, text);
                CallExternalApp.callDaumMap(mCtx, text);

            }
        }
    }

    /** 클립보드 컨텐츠가 있는지, 텍스트 인지 */
    private boolean checkClipboard(ClipboardManager cm) {
        if(cm != null && cm.hasPrimaryClip() && cm.getPrimaryClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
            return true;
        }
        return false;
    }

}
