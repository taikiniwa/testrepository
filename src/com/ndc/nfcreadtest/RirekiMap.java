/**
 * �����}�b�v
 */
package com.ndc.nfcreadtest;

public class RirekiMap {
    private int termId;		// �[����
    private int procId;		// ����
    private int year;		// �N
    private int month;		// ��
    private int day;		// ��
    private String kind;	// ���
    private int remain;		// �c��
    private int seqNo;		// �A��
    private int reasion;	// ���[�W����
    private int pictId;		// �摜ID
    
    /**
     * �[������擾
     * @return termId �[����
     */
    public int getTermId() {
    	return this.termId;
    }
    /**
     * �[�����ݒ�
     * @param term_id �[����
     */
    public void setTermId(int term_id) {
    	this.termId = term_id;
    }
    
    /**
     * �������擾
     * @return procId ����
     */
    public int getProcId() {
    	return this.procId;
    }
    /**
     * ������ݒ�
     * @param proc_id ����
     */
    public void setProcId(int proc_id) {
    	this.procId = proc_id;
    }
    
    /**
     * �N���擾
     * @return year �N
     */
    public int getYear() {
    	return this.year;
    }
    /**
     * �N��ݒ�
     * @param year �N
     */
    public void setYear(int year) {
    	this.year = year;
    }
    
    /**
     * �����擾
     * @return month ��
     */
    public int getMonth() {
    	return this.month;
    }
    /**
     * ����ݒ�
     * @param month ��
     */
    public void setMonth(int month) {
    	this.month = month;
    }
    
    /**
     * �����擾
     * @return day ��
     */
    public int getDay() {
    	return this.day;
    }
    /**
     * ����ݒ�
     * @param day ��
     */
    public void setDay(int day) {
    	this.day = day;
    }
    
    /**
     * ��ʂ��擾
     * @return kind ���
     */
    public String getKind() {
    	return this.kind;
    }
    /**
     * ��ʂ�ݒ�
     * @param kind ���
     */
    public void setKind(String kind) {
    	this.kind = kind;
    }
    
    /**
     * �c�����擾
     * @return remain �c��
     */
    public int getRemain() {
    	return this.remain;
    }
    /**
     * �c����ݒ�
     * @param remain �c��
     */
    public void setRemain(int remain) {
    	this.remain = remain;
    }
    
    /**
     * �A�Ԃ��擾
     * @return seqNo �A��
     */
    public int getSeqNo() {
    	return this.seqNo;
    }
    /**
     * �A�Ԃ�ݒ�
     * @param seq_no �A��
     */
    public void setSeqNo(int seq_no) {
    	this.seqNo = seq_no;
    }
    
    /**
     * ���[�W�������擾
     * @return reasion ���[�W����
     */
    public int getReasion() {
    	return this.reasion;
    }
    /**
     * ���[�W������ݒ�
     * @param reasion ���[�W����
     */
    public void setReasion(int reasion) {
    	this.reasion = reasion;
    }
    
    /**
     * �摜ID���擾
     * @return pictId �摜ID
     */
    public int getPictId() {
    	return this.pictId;
    }
    /**
     * �摜ID��ݒ�
     * @param pict_id �摜ID
     */
    public void setPictId(int pict_id) {
    	this.pictId = pict_id;
    }
}