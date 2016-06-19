package com.ndc.nfcreadtest;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 複行行表示可能なListのAdapterです。
 * 
 * templateはcom_multi_line_row.xmlにあるのでそちらも参照してください。

 *
 */
public class MultiLineListRowAdapter extends ArrayAdapter<MultiLineListRow> {
	
	private static final String TAG = "MultiLineListRowAdapter";
	
	/** displayed row */
	private List<MultiLineListRow> items;
	
	/** viewをクリックしたときのlistener */
	private OnClickListener listener;
	
	private LayoutInflater inflater;
	
	private int resourceId;
	
	public MultiLineListRowAdapter(Context context, int resourceId, List<MultiLineListRow> items) {
		this(context, resourceId, items, null);
	}
	
	public MultiLineListRowAdapter(Context context, int resourceId, List<MultiLineListRow> items, OnClickListener listener) {
		super(context, resourceId, items);
		this.resourceId = resourceId;
		this.items = items;
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.listener = listener;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView; 
		// 初回はnullがわたってくる。
		// 2回目以降は以前作成したものがわたってくるらしい。
		if (view == null) {
			// view = inflater.inflate(R.layout.com_multiline_row, null);
			view = inflater.inflate(resourceId, null);
		}
		view = populateView(position, view, parent);
		return view;
	}
	
	protected View populateView (int position, View convertView, ViewGroup parent) {
//		MainActivity main = new MainActivity();
		Log.d(TAG, "populateView position [" + position + "]");
		MultiLineListRow item = items.get(position);
		if (item.getPrefixImageId() != null) {
//	        Bitmap bit1 = BitmapFactory.decodeResource(main.getResources(), R.drawable.charge);
//	        Bitmap bit2 = Bitmap.createScaledBitmap(bit1, 100, 100, false);
			ImageView imageView = (ImageView) convertView.findViewById(R.id.row_prefix_image);
			imageView.setImageResource(item.getPrefixImageId());
//	        imageView.setImageBitmap(bit2);
		}
		
		if (item.getSuffixImageId() != null) {
			ImageView imageView = (ImageView) convertView.findViewById(R.id.row_suffix_image);
			imageView.setImageResource(item.getSuffixImageId());
		}
		
		LinearLayout layout = (LinearLayout) convertView.findViewById(R.id.row_list_area);
		// 全て消しているが・・・
		// パフォーマンスを考えると他の方法を探したほうがいいかも。
		// もっといい方法をご存知の方はご連絡下さい m(_ _)m
		layout.removeAllViews();
		Log.d(TAG, "PopulateTextView size [" + item.sieze() + "]");
		for (int i = 0, n = item.sieze(); i < n; i++) {
			TextView textView = new TextView(parent.getContext());
			// TODO test用
			textView.setText(item.getText(i));
//			textView.setText(String.valueOf(position));
			// TODO test用
			
			// サイズ指定する場合
			if (item.getTextSize(i) > 1) {
				textView.setTextSize(item.getTextSize(i));
			}
// add start
			// ２行目(金額)は右寄りにする
			if (i == 2) {
				textView.setGravity(Gravity.RIGHT);
			}
			textView.setTextColor(Color.parseColor("#543f32"));
// add end
			Log.d(TAG, "Add TextView text [" + item.getText(i) + "]");
			layout.addView(textView, i);
		}
		
		// 奇数行
		if (position%2 == 0) {
			convertView.setBackgroundColor(Color.parseColor("#fffaf0"));
		// 偶数行
		} else {
			convertView.setBackgroundColor(Color.WHITE);
		}
		
		
		if (listener != null) {
			convertView.setOnClickListener(listener);
		}
		return convertView;
	}
}