package com.ndc.nfcreadtest;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

//import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.NfcF;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;



public class MainActivity extends Activity implements OnClickListener{

	private static final String TAG = "NFCReaderTest";
	private NfcAdapter mNfcAdapter;
	private PendingIntent mPendingIntent;
	private IntentFilter[] mIntentFilters;
	
	  ListView listView;
	  Button addButton;
	  //static ArrayAdapter<Book> adapter;
	  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
//        listView = (ListView)findViewById(R.id.listView1);   
        
        //Intent intent = this.getIntent();
        //String action = intent.getAction();
        /*
         * Foreground Dispatch の設定
         * このアプリが優先して Intent を受け取れるようにする
         * 実際の設定・解除は onResume と onPause で実行
         */
        mNfcAdapter = NfcAdapter.getDefaultAdapter(getApplicationContext());

        Intent intent = new Intent(getApplicationContext(), getClass());
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        mPendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

        IntentFilter intentFilter = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
        mIntentFilters = new IntentFilter[]{intentFilter};
        
        String action = intent.getAction();
        
        // NFC���ǂ���Action�̔���
//        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)
//                ||  NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)
//                ||  NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)) {
            
            // IDmを読み取る
//            String idm = this.getIdm(this.getIntent());
//            if (idm != null) {
//                TextView idmView = (TextView) findViewById(R.id.zandaka);
//                idmView.setText(idm);
//            }
            
            // EXTRA_NDEF_MESSAGES��\��������
//            String nfcMsg = this.NfcfRead(this.getIntent());
        	String nfcMsg = this.NFCtext(this.getIntent());
            if (nfcMsg != null) {
            	TextView idmView = (TextView) findViewById(R.id.tagmsg);
                idmView.setText(nfcMsg);
            }
//            
//            // ���ǂݍ��񂾃f�[�^����ʂɐݒ肷��
//            if (nfcMsg != null && nfcMsg.length() > 0) {
//	            // 1�s������
//	            String[] nfcMsgList = nfcMsg.split("\n");
//	            if (nfcMsgList != null) {
//	            	// �������X�g
//	            	List<MultiLineListRow> items = new ArrayList<MultiLineListRow>();
//	            	
//	            	for (int i = 0; i < nfcMsgList.length; i++) {
//	            		// �J���}���Ƃɋ�؂�
//	            		String msgRow = nfcMsgList[i];
//	            		String[] msg = msgRow.split(",");
//	            		
//	            		// **�c��**
//	            		// 1�s�ڂ��擾�ł����ꍇ�̂ݐݒ�
//	            		if (i == 0) {
//	            			TextView zandakaView = (TextView) findViewById(R.id.zandaka);
//	            			zandakaView.setText(msg[5]);
//	            		}
//	            		
//	            		// **�������X�g**
//	            		// ���X�g�ɐݒ肷��
//	            		items.add(MultiLineListRowImpl.create()
//	            				.prefixImage(Integer.parseInt(msg[6]))
//	            				.addText(msg[4])
//	            				.addText(msg[2], 20)
//	            				.addText(msg[5]));
////	            				.prefixImage(Integer.getInteger(msg[6])));
//	            	}
//	            	
//	            	// ��ʂ̃��X�g�Ƀf�[�^��ݒ�
//	            	ListView listView = (ListView) findViewById(R.id.listView1);
//	            	listView.setAdapter(new MultiLineListRowAdapter(this, R.layout.com_multiline_row, items));
//	            }
//            }
//        }
        
        
//        this.populateContentsListView();
    }
    
    /**
     * IDm���擾����
     * @param intent ��M�C���e���g
     * @return IDm������
     */
    private String getIdm(Intent intent) {
		String idm = null;
		StringBuffer idmByte = new StringBuffer();
		byte[] rawIdm = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID);
		if (rawIdm != null) {
			for (int i = 0; i < rawIdm.length; i++) {
				idmByte.append(Integer.toHexString(rawIdm[i] & 0xff));
			}
			idm = idmByte.toString();
		}
		return idm;
    }
    
// modify start
    private String NfcfRead(Intent intent) {
//    private ArrayList<RirekiMap> NfcfRead(Intent intent) {
// modify end
    	byte[] cardId = new byte[] { 0 };
    	
	    NfcF felica= null;
	    Tag tag = (Tag) intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
	    
	    if (tag == null) {
	        return null;
	    }

	    cardId = tag.getId(); // �J�[�h�̃��j�[�NID���擾
        felica= NfcF.get(tag);
        
        try {
        	Felicalib fLib = new Felicalib();
        	
        	felica.connect();
            byte[] req = fLib.readWithoutEncryption(cardId, 10);
            Log.d(TAG, "req:"+fLib.toHex(req));
            // �J�[�h�Ƀ��N�G�X�g���M
            byte[] res = felica.transceive(req);
            Log.d(TAG, "res:"+fLib.toHex(res));
            felica.close();
            // ���ʂ𕶎���ɕϊ����ĕ\��
            return fLib.parse(res);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage() , e);
            return e.toString();
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
			
			    	int idx = 0;

			    	try {
						String sub = new String(payload,"UTF-8");
						str = sub.substring(4-1);
					} catch (UnsupportedEncodingException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					}
			    	
		//	    	for(byte data : payload) {
		//		    	if(idx > 2){
		//		    		str += String.format("%c", data);
		//		    	}
		//		    	idx++;
		//		    }
			    }
		    }
	    	
		    //textView.setText(str);

	    }
		return str;
    }
    
    private void populateContentsListView() {
        ListView contentsView = (ListView) findViewById(R.id.listView1);
        
        // create dummy list
        List<MultiLineListRow> items = new ArrayList<MultiLineListRow>();
        
        items.add(MultiLineListRowImpl.create().addText("��N�̌Ǔ�", 15));
        items.add(MultiLineListRowImpl.create().addText("�R���e���c�P").addText("�֗��ȃ��X�g").prefixImage(R.drawable.densha));
        items.add(MultiLineListRowImpl.create().addText("�����̔ӌ��", 20).addText("���[����").addText("�L�q").prefixImage(R.drawable.densha).suffixImage(android.R.drawable.ic_media_play));
        contentsView.setAdapter(new MultiLineListRowAdapter(this, R.layout.com_multiline_row, items));
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
	public void onClick(DialogInterface dialog, int which) {
		
	}
	
	@Override
	protected void onResume() {
	    super.onResume();

	    // Resumed に移る前に、 Foreground Dispatch を有効にしておく
	    mNfcAdapter.enableForegroundDispatch(this, mPendingIntent, mIntentFilters, null);
	}

	@Override
	protected void onPause() {
	    super.onPause();

	    // Paused に移る前に、 Foreground Dispatch を解除しておく
	    mNfcAdapter.disableForegroundDispatch(this);
	}
}
