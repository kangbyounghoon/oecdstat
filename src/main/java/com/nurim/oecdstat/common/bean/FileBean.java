package com.nurim.oecdstat.common.bean;

import org.springframework.stereotype.Component;

/**
 * 게시판 Bean
 * 
 * @author Administrator
 *
 */
@Component
public class FileBean {

/** 공통 */
	private String file_seq;
	private String oldFileSeq;

/** 첨부 파일 */
	private String link_name;
	private String link_eng_name;
	private String file_name;
	private String real_name;
	private String directory;
	private String file_flag;

	private String down_count;
	private String attech_type;
	
	private String img_path;

/** 추가 */
	private String board_seq;		// 게시판 일련번호
	private String boardType;		// 게시판 타입
	private String cont_seq;		// KWDI 원시자료 일련번호
	private int sort_odr;			// 자료관리 공통 정렬 순서
	private String delFileSeq;		// 자료관리 공통 삭제 파일 일련번호
	private String pub_seq;		// 한국의 성 인지 통계 일련번호
	private String rd_seq;		// 원자료 관리 일련번호
	private String project_seq;		// 게시판 일련번호
	
	private String[] contSeqList;
	private String extention;

/** KISDI 주요보고서 */
	/** 보고서 일련번호 */
	private String reprt_sn;
	/** 기본 파일 여부 */
	private String bass_file_at;
	/** 데이터 유형 */
	private String data_ty;
	/** 데이터 순번 */
	private String data_sn;
	/** 파일 확장자 */
	private String file_extsn;

/** 공통 */
	public String getFile_seq() {
		return file_seq;
	}
	public void setFile_seq(String file_seq) {
		this.file_seq = file_seq;
	}

	public String getOldFileSeq() {
		return oldFileSeq;
	}
	public void setOldFileSeq(String oldFileSeq) {
		this.oldFileSeq = oldFileSeq;
	}

/** 첨부 파일 */
	public String getLink_name() {
		return link_name;
	}
	public void setLink_name(String link_name) {
		this.link_name = link_name;
	}

	public String getLink_eng_name() {
		return link_eng_name;
	}
	public void setLink_eng_name(String link_eng_name) {
		this.link_eng_name = link_eng_name;
	}

	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getReal_name() {
		return real_name;
	}
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public String getDirectory() {
		return directory;
	}
	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public String getFile_flag() {
		return file_flag;
	}
	public void setFile_flag(String file_flag) {
		this.file_flag = file_flag;
	}


	public String getDown_count() {
		return down_count;
	}
	public void setDown_count(String down_count) {
		this.down_count = down_count;
	}

	public String getAttech_type() {
		return attech_type;
	}
	public void setAttech_type(String attech_type) {
		this.attech_type = attech_type;
	}

/** 추가 */
	public String getBoard_seq() {
		return board_seq;
	}
	public void setBoard_seq(String board_seq) {
		this.board_seq = board_seq;
	}

	public String getBoardType() {
		return boardType;
	}
	public void setBoardType(String boardType) {
		this.boardType = boardType;
	}

	public String getCont_seq() {
		return cont_seq;
	}
	public void setCont_seq(String cont_seq) {
		this.cont_seq = cont_seq;
	}

	public int getSort_odr() {
		return sort_odr;
	}
	public void setSort_odr(int sort_odr) {
		this.sort_odr = sort_odr;
	}

	public String getDelFileSeq() {
		return delFileSeq;
	}
	public void setDelFileSeq(String delFileSeq) {
		this.delFileSeq = delFileSeq;
	}

	public String getPub_seq() {
		return pub_seq;
	}
	public void setPub_seq(String pub_seq) {
		this.pub_seq = pub_seq;
	}

	public String getRd_seq() {
		return rd_seq;
	}
	public void setRd_seq(String rd_seq) {
		this.rd_seq = rd_seq;
	}
	public String[] getContSeqList() {
		return contSeqList;
	}
	public void setContSeqList(String[] contSeqList) {
		this.contSeqList = contSeqList;
	}
	public String getProject_seq() {
		return project_seq;
	}
	public void setProject_seq(String project_seq) {
		this.project_seq = project_seq;
	}
	public String getImg_path() {
		return img_path;
	}
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
	public String getExtention() {
		return extention;
	}
	public void setExtention(String extention) {
		this.extention = extention;
	}
	public String getReprt_sn() {
		return reprt_sn;
	}
	public void setReprt_sn(String reprt_sn) {
		this.reprt_sn = reprt_sn;
	}
	public String getBass_file_at() {
		return bass_file_at;
	}
	public void setBass_file_at(String bass_file_at) {
		this.bass_file_at = bass_file_at;
	}
	public String getData_ty() {
		return data_ty;
	}
	public void setData_ty(String data_ty) {
		this.data_ty = data_ty;
	}
	public String getData_sn() {
		return data_sn;
	}
	public void setData_sn(String data_sn) {
		this.data_sn = data_sn;
	}
	public String getFile_extsn() {
		return file_extsn;
	}
	public void setFile_extsn(String file_extsn) {
		this.file_extsn = file_extsn;
	}
}