package com.ndc.nfcreadtest;

import android.util.SparseArray;

/**
 * Pasumo�������R�[�h�B
 * - �����Fhttp://sourceforge.jp/projects/felicalib/wiki/suica
 */
public class Rireki {
    public int termId;
    public int procId;
    public int year;
    public int month;
    public int day;
    public String kind;
    public int remain;
    public int seqNo;
    public int reasion;
    // add start
    /** �摜ID */
    public int pictId;
    // add end

    public Rireki(){
    }

    public static Rireki parse(byte[] res, int off) {
        Rireki self = new Rireki();
        self.init(res, off);
        return self;
    }

    private void init(byte[] res, int off) {
        this.termId = res[off+0]; //0: �[����
        this.procId = res[off+1]; //1: ����
        //2-3: ??
        int mixInt = toInt(res, off, 4,5);
        this.year  = (mixInt >> 9) & 0x07f;
        this.month = (mixInt >> 5) & 0x00f;
        this.day   = mixInt & 0x01f;

        if (isBuppan(this.procId)) {
            this.kind = "����";
            // add start
            this.pictId = R.drawable.item;
            // add end
        } else if (isBus(this.procId)) {
            this.kind = "�o�X";
// add start
            this.pictId = R.drawable.bus_2;
        /* �`���[�W */
        } else if (this.isCharge(this.procId)) {
        	this.kind = "�`���[�W";
        	this.pictId = R.drawable.money;
// add end
        } else {
// modify start
//            this.kind = res[off+6] < 0x80 ? "JR" : "���c/���S" ;
        	this.kind = "�d��";
// modify end
// add start
            this.pictId = R.drawable.train;
// add end
        }
        this.remain  = toInt(res, off, 11,10); //10-11: �c�� (little endian)
        this.seqNo   = toInt(res, off, 12,13,14); //12-14: �A��
        this.reasion = res[off+15]; //15: ���[�W���� 
    }

    private int toInt(byte[] res, int off, int... idx) {
        int num = 0;
        for (int i=0; i<idx.length; i++) {
            num = num << 8;
            num += ((int)res[off+idx[i]]) & 0x0ff;
        }
        return num;
    }
    private boolean isBuppan(int procId) {
        return procId == 70 || procId == 73 || procId == 74 
                || procId == 75 || procId == 198 || procId == 203;
    }
    private boolean isBus(int procId) {
        return procId == 13|| procId == 15|| procId ==  31|| procId == 35;
    }
    // add start
    private boolean isCharge(int procId) {
    	return procId == 2 || procId == 20 || procId == 21 || procId == 31 || procId == 72 || procId == 73;
    }
    // add end

    public String toString() {
        String str = seqNo
                +","+TERM_MAP.get(termId)
                +","+ PROC_MAP.get(procId)
                +","+kind
                +","+year+"/"+month+"/"+day
// modify start
//                +",�c�F"+remain+"�~"
				+","+remain+"�~"
// modify end
// add start
                +","+String.valueOf(this.pictId);
// add end
        return str;
    }
    
// add start
    public RirekiMap toMap() {
    	RirekiMap map = new RirekiMap();
    	
    	map.setTermId(this.termId);		// �[����
    	map.setProcId(this.procId);		// ����
    	map.setYear(this.year);			// �N
    	map.setMonth(this.month);		// ��
    	map.setDay(this.day);			// ��
    	map.setKind(this.kind);			// ���
    	map.setRemain(this.remain);		// �c��
    	map.setSeqNo(this.seqNo);		// �A��
    	map.setReasion(this.reasion);	// ���[�W����
    	map.setPictId(this.pictId);		// �摜ID
    	
    	return map;
    }
// add end

    public static final SparseArray<String> TERM_MAP = new SparseArray<String>();
    public static final SparseArray<String> PROC_MAP = new SparseArray<String>();
    static {
        TERM_MAP.put(3 , "���Z�@");
        TERM_MAP.put(4 , "�g�ь^�[��");
        TERM_MAP.put(5 , "�ԍڒ[��");
        TERM_MAP.put(7 , "�����@");
        TERM_MAP.put(8 , "�����@");
        TERM_MAP.put(9 , "�����@");
        TERM_MAP.put(18 , "�����@");
        TERM_MAP.put(20 , "�����@��");
        TERM_MAP.put(21 , "�����@��");
        TERM_MAP.put(22 , "���D�@");
        TERM_MAP.put(23 , "�ȈՉ��D�@");
        TERM_MAP.put(24 , "�����[��");
        TERM_MAP.put(25 , "�����[��");
        TERM_MAP.put(26 , "���D�[��");
        TERM_MAP.put(27 , "�g�ѓd�b");
        TERM_MAP.put(28 , "��p���Z�@");
        TERM_MAP.put(29 , "�A�����D�@");
        TERM_MAP.put(31 , "�ȈՓ����@");
        TERM_MAP.put(70 , "VIEW ALTTE");
        TERM_MAP.put(72 , "VIEW ALTTE");
        TERM_MAP.put(199 , "���̒[��");
        TERM_MAP.put(200 , "���̋@");

        PROC_MAP.put(1 , "�^���x��(���D�o��)");
        PROC_MAP.put(2 , "�`���[�W");
        PROC_MAP.put(3 , "���w(���C���w��)");
        PROC_MAP.put(4 , "���Z");
        PROC_MAP.put(5 , "���Z (���ꐸ�Z)");
        PROC_MAP.put(6 , "���o (���D��������)");
        PROC_MAP.put(7 , "�V�K (�V�K���s)");
        PROC_MAP.put(8 , "�T�� (�����T��)");
        PROC_MAP.put(13 , "�o�X (PiTaPa�n)");
        PROC_MAP.put(15 , "�o�X (IruCa�n)");
        PROC_MAP.put(17 , "�Ĕ� (�Ĕ��s����)");
        PROC_MAP.put(19 , "�x�� (�V�������p)");
        PROC_MAP.put(20 , "��A (���ꎞ�I�[�g�`���[�W)");
        PROC_MAP.put(21 , "�oA (�o�ꎞ�I�[�g�`���[�W)");
        PROC_MAP.put(31 , "���� (�o�X�`���[�W)");
        PROC_MAP.put(35 , "���w (�o�X�H�ʓd�Ԋ�挔�w��)");
        PROC_MAP.put(70 , "����");
        PROC_MAP.put(72 , "���T (���T�`���[�W)");
        PROC_MAP.put(73 , "���� (���W����)");
        PROC_MAP.put(74 , "���̎��");
        PROC_MAP.put(75 , "���� (���ꕨ��)");
        PROC_MAP.put(198 , "���� (�������p����)");
        PROC_MAP.put(203 , "���� (���ꌻ�����p����)");
        PROC_MAP.put(132 , "���Z (���А��Z)");
        PROC_MAP.put(133 , "���Z (���Г��ꐸ�Z)");
    }
}
