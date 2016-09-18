
package com.example.nfcanimal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class BaseActivity extends Activity {
    protected NfcAdapter mNfcAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // NFCを扱うためのインスタンスを取得
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);

        // NFCが搭載されているかチェック
        if (mNfcAdapter != null) {
            // NFC機能が有効になっているかチェック
            if (!mNfcAdapter.isEnabled()) {
                // NFC機能が無効の場合はユーザーへ通知
                Toast.makeText(getApplicationContext(), getString(R.string.error_nfc_disable),
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            // NFC非搭載の場合はユーザーへ通知
            Toast.makeText(getApplicationContext(), getString(R.string.error_nfc_nosupport),
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // メニューアイテムを生成
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
