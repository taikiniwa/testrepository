package com.ndc.nfcreadtest;

import java.util.ArrayList;
import java.util.List;

public class MultiLineListRowImpl implements MultiLineListRow {
	
	private Integer prefixImage;
	private Integer suffixImage;
	
	List<String> texts;
	List<Float> size;
	
	public static MultiLineListRowImpl create() {
		
		MultiLineListRowImpl m = new MultiLineListRowImpl();
		m.texts = new ArrayList<String>();
		m.size = new ArrayList<Float>();
		return m;
	}
	public MultiLineListRowImpl prefixImage(Integer id) {
		this.prefixImage = id;
		return this;
	}
	public MultiLineListRowImpl suffixImage(Integer id) {
		this.suffixImage = id;
		return this;
	}
	public MultiLineListRowImpl addText(String text, float size) {
		this.texts.add(text);
		this.size.add(size);
		return this;
	}
	
	public MultiLineListRowImpl addText(String text) {
		this.texts.add(text);
		this.size.add(0f);
		return this;
	}
	
	public Integer getPrefixImageId() {
		return prefixImage;
	}

	public Integer getSuffixImageId() {
		return suffixImage;
	}

	public String getText(int position) {
		return texts.get(position);
	}

	public float getTextSize(int position) {
		return size.get(position);
	}

	public int sieze() {
		return texts.size();
	}
	
}