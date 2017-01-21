package kein.navihelper;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import static android.content.Context.WINDOW_SERVICE;

/**
 * Created by kein on 2017. 1. 21..
 *
 * 주소 또는 전화번호로 판단되면 다음지도 또는 카카오내비로 연결되는 버튼이 있는 토스트를 노출 한다.
 * (토스트는 Toss 의 그것같은..)
 */
public class ShowAppToast {

    private static WindowManager mWindowManager;
    private static View mToastView;
    private static Handler mHandler = new Handler();

    public static void show(Context ctx, String address) {
        mWindowManager = (WindowManager) ctx.getSystemService(WINDOW_SERVICE);

        mToastView = LayoutInflater.from(ctx).inflate(R.layout.show_app_toast, null);

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
        mToastView.startAnimation(getShowingAnim());

        ((TextView)mToastView.findViewById(R.id.tvText)).setText(address);

        mToastView.findViewById(R.id.btnClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

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

    private static Animation getShowingAnim() {
        TranslateAnimation anim = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 0);
        anim.setDuration(300);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        return anim;
    }
}
