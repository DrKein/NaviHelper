package kein.navihelper.tool;

import android.support.annotation.NonNull;

import java.util.regex.Pattern;

/**
 * Created by kein on 2017. 1. 21..
 *
 * 전달된 텍스트가 주소 또는 전화번호 인지 판단 한다.
 * 일단 주소로 ....
 */

public class AddressDetector {

    public static boolean isAddress(@NonNull String text) {

        // 지번 => 동,면,리 등장?
        if(text.contains("동")||text.contains("면")||text.contains("리")) {
            return true;
        }

        // 도로명 => 로 뒤에 숫자 포함
        int indexAvenue = 0;
        if((indexAvenue = text.indexOf("로")) > 0) {
            String left = text.substring(indexAvenue);
            return hasNumbers(left);
        }
        return false;
    }

    private static boolean hasNumbers(String text) {
        return Pattern.matches("^.*[0-9]*$", text); // ?????
    }


}
