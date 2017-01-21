package kein.navihelper;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import kein.navihelper.service.ClipboardListenerService;
import kein.navihelper.tool.PermissionCheck;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService(new Intent(this, ClipboardListenerService.class));

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        checkOverlayPermission();
    }

    private void checkOverlayPermission() {
        if(PermissionCheck.hasOverlayPermission(this) == false) {
            showRequestPermission();
        }
    }

    private void showRequestPermission() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("권한 요청");
        builder.setMessage("다른앱 위에 그리기 권한이 필요합니다. 확인을 누르신 후 권한을 켜주세요.");
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
                startActivity(intent);
            }
        });
        builder.show();
    }


    private void test() {
        String text = ((EditText)findViewById(R.id.edittext)).getText().toString().trim();

        ClipboardManager clipboardManager = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("label", text);
        clipboardManager.setPrimaryClip(clipData);
    }


}
