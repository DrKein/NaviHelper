package kein.navihelper.tool;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import kein.navihelper.view.ExternalAppMenuView;

import static android.content.Context.WINDOW_SERVICE;

/**
 * Created by kein on 2017. 1. 21..
 *
 * 주소 또는 전화번호로 판단되면 다음지도 또는 카카오내비로 연결되는 버튼이 있는 토스트를 노출 한다.
 *
 */
public class ShowMenuView {

    private static WindowManager mWindowManager;
    private static View mToastView;
    private static Handler mHandler = new Handler();

    public static void show(Context ctx, String address) {
        mWindowManager = (WindowManager) ctx.getSystemService(WINDOW_SERVICE);

        mToastView = new ExternalAppMenuView(ctx, address, new ExternalAppMenuView.OnCloseListener(){
            @Override
            public void onClosed() {
                dismiss();
            }
        });

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        params.x = 0;
        params.y = 100;

        mWindowManager.addView(mToastView, params);

        setAutoDismiss();
    }

    private static void dismiss() {
        mWindowManager.removeViewImmediate(mToastView);
        mHandler.removeCallbacksAndMessages(null); // remove all.
    }

    private static void setAutoDismiss() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dismiss();
            }
        }, 5000);
    }


}
