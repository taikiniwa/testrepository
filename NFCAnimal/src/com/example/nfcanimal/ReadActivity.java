
package com.example.nfcanimal;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.media.AudioManager;
import android.media.SoundPool;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class ReadActivity extends BaseActivity {
  
	//グローバル変数
//	Global global;

	//文字の点滅用変数
	private Handler mHandler = new Handler();
	private ScheduledExecutorService mScheduledExecutor;
	private TextView mLblMeasuring;
	
	/** 動物名 **/
	private static final int ANIMAL_NAME_LION = 1;
	private static final int ANIMAL_NAME_CAT = 2;
	private static final int ANIMAL_NAME_DOG = 3;
	private static final int ANIMAL_NAME_ELEPHANT = 4;
	private static final int ANIMAL_NAME_SPARROW = 5;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        
        startMeasure();
    }

    @Override
    protected void onResume() {
        super.onResume();
        
       if (mNfcAdapter != null) {
   		
            // 起動中のアクティビティが優先的にNFCを受け取れるよう設定
            Intent intent = new Intent(this, this.getClass())
                    .setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0,
                    intent, 0);
            mNfcAdapter.enableForegroundDispatch(this, pendingIntent, null, null);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

       if (mNfcAdapter != null) {
            // アクテイビティが非表示になる際に優先的にNFCを受け取る設定を解除
            mNfcAdapter.disableForegroundDispatch(this);
        }
    }

    @SuppressLint("NewApi")
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String action = intent.getAction();
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)
                || NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)
                || NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)) {

	    	//NDCに入っているテキストデータを取得
        	String nfcMsg = NFCtext(intent);
        	int readNum = Integer.parseInt(nfcMsg);
        	
            if (nfcMsg != null) {
//            	TextView idmView = (TextView) findViewById(R.id.Read);
//                idmView.setText(nfcMsg);
            	
                Intent newIntent;
                
                //読み込んだテキストデータに応じて表示する動物を決定
                switch(readNum){
                case ANIMAL_NAME_LION :
        			newIntent = new Intent(ReadActivity.this, AnimalActivity.class);
        			newIntent.putExtra("ANIMAL", ANIMAL_NAME_LION);
        			try {
        				// ライオンの画面へ遷移
        				startActivity(newIntent);
        			} catch (Exception e) {
        				Toast.makeText(ReadActivity.this, "ライオンの画面へ遷移できません", Toast.LENGTH_LONG).show();
        			}
        			newIntent = null;
        			break;
                
            	case ANIMAL_NAME_CAT:
	    			newIntent = new Intent(ReadActivity.this, AnimalActivity.class);
        			newIntent.putExtra("ANIMAL", ANIMAL_NAME_CAT);
	    			try {
	    				// ネコの画面へ遷移
	    				startActivity(newIntent);
	    			} catch (Exception e) {
	    				Toast.makeText(ReadActivity.this, "ネコの画面へ遷移できません", Toast.LENGTH_LONG).show();
	    			}
	    			newIntent = null;
	    			break;
           
                case ANIMAL_NAME_DOG :
        			newIntent = new Intent(ReadActivity.this, AnimalActivity.class);
        			newIntent.putExtra("ANIMAL", ANIMAL_NAME_DOG);
        			try {
        				// イヌの画面へ遷移
        				startActivity(newIntent);
        			} catch (Exception e) {
        				Toast.makeText(ReadActivity.this, "イヌの画面へ遷移できません", Toast.LENGTH_LONG).show();
        			}
        			newIntent = null;
        			break;
        			
                case ANIMAL_NAME_ELEPHANT :
        			newIntent = new Intent(ReadActivity.this, AnimalActivity.class);
        			newIntent.putExtra("ANIMAL", ANIMAL_NAME_ELEPHANT);
        			try {
        				// イヌの画面へ遷移
        				startActivity(newIntent);
        			} catch (Exception e) {
        				Toast.makeText(ReadActivity.this, "イヌの画面へ遷移できません", Toast.LENGTH_LONG).show();
        			}
        			newIntent = null;
        			break;
        			
                case ANIMAL_NAME_SPARROW :
        			newIntent = new Intent(ReadActivity.this, AnimalActivity.class);
        			newIntent.putExtra("ANIMAL", ANIMAL_NAME_SPARROW);
        			try {
        				// イヌの画面へ遷移
        				startActivity(newIntent);
        			} catch (Exception e) {
        				Toast.makeText(ReadActivity.this, "イヌの画面へ遷移できません", Toast.LENGTH_LONG).show();
        			}
        			newIntent = null;
        			break;
        			
                default : break;
                }
            }
	    	
            
//            // UIDを取得
//            byte[] uid = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID);
//
//            // UIDを文字列に変換して表示
//            TextView tvRead = (TextView) findViewById(R.id.Read);
//            tvRead.setText(NfcUtil.bytesToHex(uid));

//            Switch swWrite = (Switch) findViewById(R.id.SwitchWrite);
//            if (swWrite.isChecked()) {
//                // NFCタグ情報を取得
//                Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
//
//                if (tag != null) {
//                    // 入力したテキストを取得
//                    EditText etWrite = (EditText) findViewById(R.id.Write);
//                    String ndefMsg = etWrite.getText().toString();
//                    if (!TextUtils.isEmpty(ndefMsg)) {
//                        // NdefRecordの作成
//                        NdefRecord[] ndefRecords = new NdefRecord[] {
//                                NdefRecord.createUri(ndefMsg),
//                        };
//                        // NdefMessageの作成
//                        NdefMessage msg = new NdefMessage(ndefRecords);
//                        write(tag, msg);
//                    } else {
//                        // 書き込むデータが存在しない場合はユーザーへ通知
//                        Toast.makeText(this, getString(R.string.error_empty_uri),
//                                Toast.LENGTH_SHORT)
//                                .show();
//                    }
//                }
//            }
        }
    }

    /**
     * NFCタグに書き込む.
     * 
     * @param tag
     * @param msg
     */
    private void write(Tag tag, NdefMessage msg) {
        try {
            List<String> techList = Arrays.asList(tag.getTechList());
            // 書き込みを行うタグにNDEFデータが格納されているか確認
            if (techList.contains(Ndef.class.getName())) {
                // NDEFが含まれている場合
                Ndef ndef = Ndef.get(tag);
                try {
                    // そのままNDEFデータ上にNDEFメッセージを書き込む
                    ndef.connect();
                    ndef.writeNdefMessage(msg);
                } catch (IOException e) {
                    throw new RuntimeException(getString(R.string.error_connect), e);
                } catch (FormatException e) {
                    throw new RuntimeException(getString(R.string.error_format), e);
                } finally {
                    try {
                        ndef.close();
                    } catch (IOException e) {
                    }
                }
            } else if (techList.contains(NdefFormatable.class.getName())) {
                // NDEFFormatableが含まれている場合
                NdefFormatable ndeffmt = NdefFormatable.get(tag);
                try {
                    // そのままNDEFにフォーマットしつつNDEFメッセージを書き込む
                    ndeffmt.connect();
                    ndeffmt.format(msg);
                } catch (IOException e) {
                    throw new RuntimeException(getString(R.string.error_connect), e);
                } catch (FormatException e) {
                    throw new RuntimeException(getString(R.string.error_format), e);
                } finally {
                    try {
                        ndeffmt.close();
                    } catch (IOException e) {
                    }
                }
            }
            Toast.makeText(this, getString(R.string.write_success), Toast.LENGTH_SHORT).show();
        } catch (RuntimeException e) {
            Toast.makeText(this, getString(R.string.write_failure), Toast.LENGTH_SHORT).show();
        }
    }
    
    private String NFCtext(Intent intent){
    	//NFCシールからのアクセスかチェック
    	String str = null;
    	byte[] payload = null;
    	
    	String action = intent.getAction();
    	if (action.equals((NfcAdapter.ACTION_NDEF_DISCOVERED))) {
	
	    	//Ndefメッセージの取得
	    	Parcelable[] raws = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
	    	NdefMessage[] msgs = new NdefMessage[raws.length];
	
	    	for (int i=0; i<raws.length; i++) {
		    	msgs[i] = (NdefMessage)raws[i];
		
		    	for (NdefRecord record : msgs[i].getRecords()) {
		
			    	//payloadを取得
			    	payload = record.getPayload();
			
			    	//payloadが空白ならブレイク
			    	if (payload == null) break;

			    	try {
						String sub = new String(payload,"UTF-8");
						str = sub.substring(4-1);
					} catch (UnsupportedEncodingException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					}
			    	
			    }
		    }

	    }
		return str;
    }
    
    //テキスト点滅用メソッド
    private void startMeasure() {
        /**
         * 点滅させたいView
         * TextViewじゃなくてもよい。
         */
        mLblMeasuring = (TextView) findViewById(R.id.message);

        /**
         * 第一引数: 繰り返し実行したい処理
         * 第二引数: 指定時間後に第一引数の処理を開始
         * 第三引数: 第一引数の処理完了後、指定時間後に再実行
         * 第四引数: 第二、第三引数の単位
         *
         * new Runnable（無名オブジェクト）をすぐに（0秒後に）実行し、完了後1700ミリ秒ごとに繰り返す。
         * （ただしアニメーションの完了からではない。Handler#postが即時実行だから？？）
         */
        mScheduledExecutor = Executors.newScheduledThreadPool(2);

        mScheduledExecutor.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mLblMeasuring.setVisibility(View.VISIBLE);

                        // HONEYCOMBより前のAndroid SDKがProperty Animation非対応のため
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                            animateAlpha();
                        }
                    }
                });
            }

            private void animateAlpha() {

                // 実行するAnimatorのリスト
                List<Animator> animatorList = new ArrayList<Animator>();

                // alpha値を0から1へ1000ミリ秒かけて変化させる。
                ObjectAnimator animeFadeIn = ObjectAnimator.ofFloat(mLblMeasuring, "alpha", 0f, 1f);
                animeFadeIn.setDuration(1000);

                // alpha値を1から0へ600ミリ秒かけて変化させる。
                ObjectAnimator animeFadeOut = ObjectAnimator.ofFloat(mLblMeasuring, "alpha", 1f, 0f);
                animeFadeOut.setDuration(600);

                // 実行対象Animatorリストに追加。
                animatorList.add(animeFadeIn);
                animatorList.add(animeFadeOut);

                final AnimatorSet animatorSet = new AnimatorSet();

                // リストの順番に実行
                animatorSet.playSequentially(animatorList);

                animatorSet.start();
            }
        }, 0, 1700, TimeUnit.MILLISECONDS);

    }
}
