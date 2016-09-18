package com.example.nfcanimal;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AnimalActivity extends BaseActivity {

	/** 動物名 **/
	private static final int ANIMAL_NAME_LION = 1;
	private static final int ANIMAL_NAME_CAT = 2;
	private static final int ANIMAL_NAME_DOG = 3;
	private static final int ANIMAL_NAME_ELEPHANT = 4;
	private static final int ANIMAL_NAME_SPARROW = 5;
	
	//音声再生クラス
    MediaPlayer mp = null;
	
	//NFCアダプタ
//    protected NfcAdapter mNfcAdapter;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal);
        
        ImageView img = (ImageView) findViewById(R.id.animalImg);
        
		//NFCから読み込んだデータに応じて表示する画像を変更
		Intent intent = getIntent();
		int animanInt = intent.getIntExtra("ANIMAL", 0);
		
	    // 音声データのロード
		mp = MediaPlayer.create(this, R.raw.cat);
	
	    // 動物の名前
	    TextView animal_name = (TextView) findViewById(R.id.animal_name);
	    
		switch(animanInt){
		case ANIMAL_NAME_LION :
			img.setImageResource(R.drawable.lion_a10);
		    // 音声データのロード
			mp = MediaPlayer.create(this, R.raw.lion);
		    // 再生
		    mp.start();
		    //　動物名の設定
		    animal_name.setText(R.string.lion);
			break;
		case ANIMAL_NAME_CAT :
			img.setImageResource(R.drawable.cat_a05);
		    // 音声データのロード
			mp = MediaPlayer.create(this, R.raw.cat);
		    // 再生
		    mp.start();
		    //　動物名の設定
		    animal_name.setText(R.string.cat);
			break;
		case ANIMAL_NAME_ELEPHANT :
			img.setImageResource(R.drawable.dog_a27);
		    // 音声データのロード
			mp = MediaPlayer.create(this, R.raw.dog);
		    // 再生
		    mp.start();
		    //　動物名の設定
		    animal_name.setText(R.string.dog);
			break;
		case ANIMAL_NAME_SPARROW :
			img.setImageResource(R.drawable.dog_a27);
		    // 音声データのロード
			mp = MediaPlayer.create(this, R.raw.dog);
		    // 再生
		    mp.start();
		    //　動物名の設定
		    animal_name.setText(R.string.dog);
			break;
		case ANIMAL_NAME_DOG :
			img.setImageResource(R.drawable.dog_a27);
		    // 音声データのロード
			mp = MediaPlayer.create(this, R.raw.dog);
		    // 再生
		    mp.start();
		    //　動物名の設定
		    animal_name.setText(R.string.dog);
			break;
		}
	}

	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();
		
        // 起動中のアクティビティが優先的にNFCを受け取れるよう設定
        Intent intent = new Intent(this, this.getClass())
                .setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0,
                intent, 0);
        mNfcAdapter.enableForegroundDispatch(this, pendingIntent, null, null);
	}

	@Override
	protected void onPause() {
		// TODO 自動生成されたメソッド・スタブ
		super.onPause();
		
       if (mNfcAdapter != null) {
            // アクテイビティが非表示になる際に優先的にNFCを受け取る設定を解除
            mNfcAdapter.disableForegroundDispatch(this);
        }
	}

	//画面タッチ時のイベント
    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        Log.d("TouchEvent", "X:" + event.getX() + ",Y:" + event.getY());
        Intent newIntent;
        
        //読み込んだテキストデータに応じて表示する動物を決定

			newIntent = new Intent(AnimalActivity.this, ReadActivity.class);
			newIntent.putExtra("ANIMAL", ANIMAL_NAME_LION);
			try {
				// トップ画面へ遷移
				startActivity(newIntent);
			} catch (Exception e) {
				Toast.makeText(AnimalActivity.this, "トップ画面へ遷移できません", Toast.LENGTH_LONG).show();
			}
			newIntent = null;

        return true;
    }

}
