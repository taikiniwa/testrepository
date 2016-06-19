package com.ndc.nfcreadtest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		//テキストの点滅
		startMeasure();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	//タッチされた時のイベント
	public boolean onTouchEvent(MotionEvent event){
		return true;
	}
	
	private Handler hndler = new Handler();
	private ScheduledExecutorService ses;
	private TextView lbl;
	
	/*
	 * テキスト点滅用メソッド
	 * ScheduledExecutorService.scheduleWithFixedDelay
	 * (繰り返し実行したい処理,指定時間後に第一引数の処理を開始,
	 *  第一引数の処理完了後、指定時間後に再実行,第二、第三引数の単位)
	 */
	private void startMeasure(){
		lbl = (TextView)findViewById(R.id.disp_msg);
		ses = Executors.newScheduledThreadPool(2);
		ses.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run(){
				hndler.post(new Runnable(){
					@Override
					public void run(){
						lbl.setVisibility(View.VISIBLE);
				
						//Android SDKがHONEYCOMBより後の場合のみ点滅する
						if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
							anime();
						}
					}
				});
			}

			@TargetApi(Build.VERSION_CODES.HONEYCOMB)
			private void anime(){
				//Animatorのリスト
				List<Animator> animatorList = new ArrayList<Animator>();
		
				//フェードイン用Animator：1000ミリ秒でalpha値を0~1へ変化させる
				ObjectAnimator fadeIn = ObjectAnimator.ofFloat(lbl,"alpha",0f,1f);
				fadeIn.setDuration(1000);
				
				//フェードアウト用Animator：600ミリ秒かけてalpha値を0~1へ変化させる
				ObjectAnimator fadeOut = ObjectAnimator.ofFloat(lbl,"alpha",1f,0f);
				fadeOut.setDuration(600);
				
				animatorList.add(fadeIn);
				animatorList.add(fadeOut);
			
				AnimatorSet animatorSet = new AnimatorSet();
			
				//リストの順にアニメーションする
				animatorSet.playSequentially(animatorList);
				animatorSet.start();
			}
		},0,1700,TimeUnit.MILLISECONDS);
	}
}
