/**
 * 履歴マップ
 */
package com.ndc.nfcreadtest;

public class RirekiMap {
    private int termId;		// 端末種
    private int procId;		// 処理
    private int year;		// 年
    private int month;		// 月
    private int day;		// 日
    private String kind;	// 種別
    private int remain;		// 残高
    private int seqNo;		// 連番
    private int reasion;	// リージョン
    private int pictId;		// 画像ID
    
    /**
     * 端末種を取得
     * @return termId 端末種
     */
    public int getTermId() {
    	return this.termId;
    }
    /**
     * 端末種を設定
     * @param term_id 端末種
     */
    public void setTermId(int term_id) {
    	this.termId = term_id;
    }
    
    /**
     * 処理を取得
     * @return procId 処理
     */
    public int getProcId() {
    	return this.procId;
    }
    /**
     * 処理を設定
     * @param proc_id 処理
     */
    public void setProcId(int proc_id) {
    	this.procId = proc_id;
    }
    
    /**
     * 年を取得
     * @return year 年
     */
    public int getYear() {
    	return this.year;
    }
    /**
     * 年を設定
     * @param year 年
     */
    public void setYear(int year) {
    	this.year = year;
    }
    
    /**
     * 月を取得
     * @return month 月
     */
    public int getMonth() {
    	return this.month;
    }
    /**
     * 月を設定
     * @param month 月
     */
    public void setMonth(int month) {
    	this.month = month;
    }
    
    /**
     * 日を取得
     * @return day 日
     */
    public int getDay() {
    	return this.day;
    }
    /**
     * 日を設定
     * @param day 日
     */
    public void setDay(int day) {
    	this.day = day;
    }
    
    /**
     * 種別を取得
     * @return kind 種別
     */
    public String getKind() {
    	return this.kind;
    }
    /**
     * 種別を設定
     * @param kind 種別
     */
    public void setKind(String kind) {
    	this.kind = kind;
    }
    
    /**
     * 残高を取得
     * @return remain 残高
     */
    public int getRemain() {
    	return this.remain;
    }
    /**
     * 残高を設定
     * @param remain 残高
     */
    public void setRemain(int remain) {
    	this.remain = remain;
    }
    
    /**
     * 連番を取得
     * @return seqNo 連番
     */
    public int getSeqNo() {
    	return this.seqNo;
    }
    /**
     * 連番を設定
     * @param seq_no 連番
     */
    public void setSeqNo(int seq_no) {
    	this.seqNo = seq_no;
    }
    
    /**
     * リージョンを取得
     * @return reasion リージョン
     */
    public int getReasion() {
    	return this.reasion;
    }
    /**
     * リージョンを設定
     * @param reasion リージョン
     */
    public void setReasion(int reasion) {
    	this.reasion = reasion;
    }
    
    /**
     * 画像IDを取得
     * @return pictId 画像ID
     */
    public int getPictId() {
    	return this.pictId;
    }
    /**
     * 画像IDを設定
     * @param pict_id 画像ID
     */
    public void setPictId(int pict_id) {
    	this.pictId = pict_id;
    }
}