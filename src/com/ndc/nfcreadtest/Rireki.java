package com.ndc.nfcreadtest;

import android.util.SparseArray;

/**
 * PasumoπR[hB
 * - ΏFhttp://sourceforge.jp/projects/felicalib/wiki/suica
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
    /** ζID */
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
        this.termId = res[off+0]; //0: [ν
        this.procId = res[off+1]; //1: 
        //2-3: ??
        int mixInt = toInt(res, off, 4,5);
        this.year  = (mixInt >> 9) & 0x07f;
        this.month = (mixInt >> 5) & 0x00f;
        this.day   = mixInt & 0x01f;

        if (isBuppan(this.procId)) {
            this.kind = "¨Μ";
            // add start
            this.pictId = R.drawable.item;
            // add end
        } else if (isBus(this.procId)) {
            this.kind = "oX";
// add start
            this.pictId = R.drawable.bus_2;
        /* `[W */
        } else if (this.isCharge(this.procId)) {
        	this.kind = "`[W";
        	this.pictId = R.drawable.money;
// add end
        } else {
// modify start
//            this.kind = res[off+6] < 0x80 ? "JR" : "φc/S" ;
        	this.kind = "dΤ";
// modify end
// add start
            this.pictId = R.drawable.train;
// add end
        }
        this.remain  = toInt(res, off, 11,10); //10-11: c (little endian)
        this.seqNo   = toInt(res, off, 12,13,14); //12-14: AΤ
        this.reasion = res[off+15]; //15: [W 
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
//                +",cF"+remain+"~"
				+","+remain+"~"
// modify end
// add start
                +","+String.valueOf(this.pictId);
// add end
        return str;
    }
    
// add start
    public RirekiMap toMap() {
    	RirekiMap map = new RirekiMap();
    	
    	map.setTermId(this.termId);		// [ν
    	map.setProcId(this.procId);		// 
    	map.setYear(this.year);			// N
    	map.setMonth(this.month);		// 
    	map.setDay(this.day);			// ϊ
    	map.setKind(this.kind);			// νΚ
    	map.setRemain(this.remain);		// c
    	map.setSeqNo(this.seqNo);		// AΤ
    	map.setReasion(this.reasion);	// [W
    	map.setPictId(this.pictId);		// ζID
    	
    	return map;
    }
// add end

    public static final SparseArray<String> TERM_MAP = new SparseArray<String>();
    public static final SparseArray<String> PROC_MAP = new SparseArray<String>();
    static {
        TERM_MAP.put(3 , "ΈZ@");
        TERM_MAP.put(4 , "gΡ^[");
        TERM_MAP.put(5 , "ΤΪ[");
        TERM_MAP.put(7 , "@");
        TERM_MAP.put(8 , "@");
        TERM_MAP.put(9 , "όΰ@");
        TERM_MAP.put(18 , "@");
        TERM_MAP.put(20 , "@");
        TERM_MAP.put(21 , "@");
        TERM_MAP.put(22 , "όD@");
        TERM_MAP.put(23 , "ΘΥόD@");
        TERM_MAP.put(24 , "ϋ[");
        TERM_MAP.put(25 , "ϋ[");
        TERM_MAP.put(26 , "όD[");
        TERM_MAP.put(27 , "gΡdb");
        TERM_MAP.put(28 , "ζpΈZ@");
        TERM_MAP.put(29 , "AόD@");
        TERM_MAP.put(31 , "ΘΥόΰ@");
        TERM_MAP.put(70 , "VIEW ALTTE");
        TERM_MAP.put(72 , "VIEW ALTTE");
        TERM_MAP.put(199 , "¨Μ[");
        TERM_MAP.put(200 , "©Μ@");

        PROC_MAP.put(1 , "^ΐx₯(όDoκ)");
        PROC_MAP.put(2 , "`[W");
        PROC_MAP.put(3 , "w(₯Cwό)");
        PROC_MAP.put(4 , "ΈZ");
        PROC_MAP.put(5 , "ΈZ (όκΈZ)");
        PROC_MAP.put(6 , "o (όDϋ)");
        PROC_MAP.put(7 , "VK (VK­s)");
        PROC_MAP.put(8 , "T (ϋT)");
        PROC_MAP.put(13 , "oX (PiTaPan)");
        PROC_MAP.put(15 , "oX (IruCan)");
        PROC_MAP.put(17 , "Δ­ (Δ­s)");
        PROC_MAP.put(19 , "x₯ (V²όp)");
        PROC_MAP.put(20 , "όA (όκI[g`[W)");
        PROC_MAP.put(21 , "oA (oκI[g`[W)");
        PROC_MAP.put(31 , "όΰ (oX`[W)");
        PROC_MAP.put(35 , "w (oXHΚdΤιζwό)");
        PROC_MAP.put(70 , "¨Μ");
        PROC_MAP.put(72 , "ΑT (ΑT`[W)");
        PROC_MAP.put(73 , "όΰ (Wόΰ)");
        PROC_MAP.put(74 , "¨ΜζΑ");
        PROC_MAP.put(75 , "ό¨ (όκ¨Μ)");
        PROC_MAP.put(198 , "¨» (»ΰΉp¨Μ)");
        PROC_MAP.put(203 , "ό¨ (όκ»ΰΉp¨Μ)");
        PROC_MAP.put(132 , "ΈZ (ΌΠΈZ)");
        PROC_MAP.put(133 , "ΈZ (ΌΠόκΈZ)");
    }
}
