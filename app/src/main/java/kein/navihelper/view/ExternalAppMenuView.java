package kein.navihelper.view;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import kein.navihelper.R;
import kein.navihelper.tool.CallExternalApp;

/**
 * Created by kein on 2017. 1. 23..
 */

public class ExternalAppMenuView extends LinearLayout {


    private OnCloseListener mOnCloseListener = null;
    public interface OnCloseListener {
        void onClosed();
    }

    public ExternalAppMenuView(final Context context, final String address, OnCloseListener listener) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.external_app_menu_view, this);

        mOnCloseListener = listener;

        findViewById(R.id.btnClose).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                close();
            }
        });

        ((TextView)findViewById(R.id.tvText)).setText(address);

        ImageButton btnDaumMap = (ImageButton)findViewById(R.id.btnDaumMap);
        btnDaumMap.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                close();
                CallExternalApp.callDaumMap(context, address);
            }
        });

        btnDaumMap.setImageDrawable(getAppIcon("net.daum.android.map"));


    }


    private Drawable getAppIcon(String pkgName) {
        Drawable drawable = null;
        PackageManager pm = getContext().getPackageManager();
        try {
            drawable = pm.getApplicationIcon(pkgName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return drawable;
    }

    private void close() {
        if(mOnCloseListener != null) {
            mOnCloseListener.onClosed();
        }
    }


}
