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
		
		//�e�L�X�g�̓_��
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
	//�^�b�`���ꂽ���̃C�x���g
	public boolean onTouchEvent(MotionEvent event){
		return true;
	}
	
	private Handler hndler = new Handler();
	private ScheduledExecutorService ses;
	private TextView lbl;
	
	/*
	 * �e�L�X�g�_�ŗp���\�b�h
	 * ScheduledExecutorService.scheduleWithFixedDelay
	 * (�J��Ԃ����s����������,�w�莞�Ԍ�ɑ������̏������J�n,
	 *  �������̏���������A�w�莞�Ԍ�ɍĎ��s,���A��O�����̒P��)
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
				
						//Android SDK��HONEYCOMB����̏ꍇ�̂ݓ_�ł���
						if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
							anime();
						}
					}
				});
			}

			@TargetApi(Build.VERSION_CODES.HONEYCOMB)
			private void anime(){
				//Animator�̃��X�g
				List<Animator> animatorList = new ArrayList<Animator>();
		
				//�t�F�[�h�C���pAnimator�F1000�~���b��alpha�l��0~1�֕ω�������
				ObjectAnimator fadeIn = ObjectAnimator.ofFloat(lbl,"alpha",0f,1f);
				fadeIn.setDuration(1000);
				
				//�t�F�[�h�A�E�g�pAnimator�F600�~���b������alpha�l��0~1�֕ω�������
				ObjectAnimator fadeOut = ObjectAnimator.ofFloat(lbl,"alpha",1f,0f);
				fadeOut.setDuration(600);
				
				animatorList.add(fadeIn);
				animatorList.add(fadeOut);
			
				AnimatorSet animatorSet = new AnimatorSet();
			
				//���X�g�̏��ɃA�j���[�V��������
				animatorSet.playSequentially(animatorList);
				animatorSet.start();
			}
		},0,1700,TimeUnit.MILLISECONDS);
	}
}
