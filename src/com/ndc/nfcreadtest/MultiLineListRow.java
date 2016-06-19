package com.ndc.nfcreadtest;

/**
 * �����s�\���\��List��Row�ł��B

 *
 */
public interface MultiLineListRow {
	
	/**
	 * �擪�ɕt�^����C���[�W��ID���擾���܂��B
	 * 
	 * �����擪�ɃC���[�W��\�����Ȃ��ꍇ��null���ԋp����܂��B
	 * @return �擪�ɕt�^����C���[�W��ID
	 */
	Integer getPrefixImageId();
	
	/**
	 * �����ɕt�^����C���[�W��ID���擾���܂��B
	 * 
	 * ���������ɃC���[�W��\�����Ȃ��ꍇ��null���ԋp����܂��B
	 * @return �����ɕt�^����C���[�W��ID
	 */
	Integer getSuffixImageId();
	
	/**
	 * �\������e�L�X�g�̍s�����擾���܂��B
	 * @return �e�L�X�g�̍s��
	 */
	int sieze();
	
	/**
	 * �\������e�L�X�g���擾���܂��B
	 * 
	 * @param position �\������ʒu
	 * @return �\������e�L�X�g
	 */
	String getText(int position);
	
	/**
	 * �\������e�L�X�g�̃T�C�Y���擾���܂��B
	 * 
	 * @param position �\������ʒu
	 * @return �\������e�L�X�g�̃T�C�Y
	 */
	float getTextSize(int position);
}