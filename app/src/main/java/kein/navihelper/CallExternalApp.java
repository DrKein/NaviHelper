package kein.navihelper;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by kein on 2017. 1. 21..
 *
 * 외부 앱을 호출 한다.
 * 1. 다음지도 : 주소 검색창에 텍스트 입력
 * 2. 카카오 내비 : 길찾기 목적지 입력
 */

public class CallExternalApp {

    public static void callDaumMap(Context ctx, String address) {
        String url = "daummaps://search?q="+address;
        callIntent(ctx, Uri.parse(url));
    }


    private static void callIntent(Context ctx, Uri uri) {
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        ctx.startActivity(intent);
    }

}
