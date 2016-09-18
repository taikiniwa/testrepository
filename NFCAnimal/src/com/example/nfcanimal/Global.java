package com.example.nfcanimal;

import android.app.Application;
import android.media.SoundPool;

public class Global extends Application{

	private int lion_voice;
	private int cat_voice;
	private int dog_voice;
	
	//初期化用メソッド
	public void GlobalAllInit() {
		this.lion_voice = 0;
		this.cat_voice = 0;
		this.dog_voice = 0;
	}
	
	public int getLion_voice() {
		return lion_voice;
	}

	public void setLion_voice(int lion_voice) {
		this.lion_voice = lion_voice;
	}

	public int getCat_voice() {
		return cat_voice;
	}

	public void setCat_voice(int cat_voice) {
		this.cat_voice = cat_voice;
	}

	public int getDog_voice() {
		return dog_voice;
	}

	public void setDog_voice(int dog_voice) {
		this.dog_voice = dog_voice;
	}


}
