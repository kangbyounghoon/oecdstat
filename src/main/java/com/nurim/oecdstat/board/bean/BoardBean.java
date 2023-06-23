package com.nurim.oecdstat.board.bean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.nurim.oecdstat.common.bean.CommonBean;
import com.nurim.oecdstat.common.bean.FileBean;


/**
 * 게시판 Bean
 */
@Component
public class BoardBean extends CommonBean {

	private static final long serialVersionUID = -7436063077177206285L;

	private String board_class;	
	private String board_seq;	
	private String up_board_seq;	
	private String subject;	
	private String content;	
	private String summary;
	private String user_write_yn;
	private String answer_yn;
	private String writer;
	private String writer_ip;
	private String password;	
	private String cont_type;
	private String cont_org;
	private String cont_link;
	private String cont_date;
	private String cont_class;
	private String cont_img;
	private String cont_sort;
	private String use_yn;
	private String reg_nm;
	private String reg_date;
	private String nwend_date;
	private int view_count;
	private String updateYn;
	private String private_yn; //문의사항 게시글 공개여부
	private String ntt_pblct_issno;
	private String real_name;
	
	private String next_board_seq;
	private String next_subject;
	private String next_password;
	private String prev_board_seq;
	private String prev_subject;
	private String prev_password;
	private String answer_cnt;

	private int num;
	
	private String kaptcha; //자동등록방지
	
	private List<FileBean> fileList = new ArrayList<FileBean>();
	
	private String top_news_at;
	private String cont_hash;
	
	private String src_status; 		// 검색조건 : 진행상황
	private String srcContDate;	// 검색조건 : 발행일
	private String srcContClass;	// 검색조건 : 구분

	private String cntyp_nm;		// CONT_TYPE명
	private String cntyp_eng_nm;	// CONT_TYPE 영문명

	private int srcPageUnit = 3; //동영상 게시글 리스트 수 : 3개
	private String srcContYear; //동영상 년도 선택
	private String listYn;		 	//웹진, 리스트 구분
	private String choiseBoardSeq;	// 통합검색 및 메인 선택 시 전달받는 시퀀스

	public String getBoard_class() {
		return board_class;
	}
	public void setBoard_class(String board_class) {
		this.board_class = board_class;
	}
	public String getBoard_seq() {
		return board_seq;
	}
	public void setBoard_seq(String board_seq) {
		this.board_seq = board_seq;
	}
	public String getUp_board_seq() {
		return up_board_seq;
	}
	public void setUp_board_seq(String up_board_seq) {
		this.up_board_seq = up_board_seq;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getUser_write_yn() {
		return user_write_yn;
	}
	public void setUser_write_yn(String user_write_yn) {
		this.user_write_yn = user_write_yn;
	}
	public String getAnswer_yn() {
		return answer_yn;
	}
	public void setAnswer_yn(String answer_yn) {
		this.answer_yn = answer_yn;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getWriter_ip() {
		return writer_ip;
	}
	public void setWriter_ip(String writer_ip) {
		this.writer_ip = writer_ip;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCont_type() {
		return cont_type;
	}
	public void setCont_type(String cont_type) {
		this.cont_type = cont_type;
	}
	public String getCont_org() {
		return cont_org;
	}
	public void setCont_org(String cont_org) {
		this.cont_org = cont_org;
	}
	public String getCont_link() {
		return cont_link;
	}
	public void setCont_link(String cont_link) {
		this.cont_link = cont_link;
	}
	public String getCont_date() {
		return cont_date;
	}
	public void setCont_date(String cont_date) {
		this.cont_date = cont_date;
	}
	public String getCont_class() {
		return cont_class;
	}
	public void setCont_class(String cont_class) {
		this.cont_class = cont_class;
	}
	public String getCont_img() {
		return cont_img;
	}
	public void setCont_img(String cont_img) {
		this.cont_img = cont_img;
	}
	public String getCont_sort() {
		return cont_sort;
	}
	public void setCont_sort(String cont_sort) {
		this.cont_sort = cont_sort;
	}
	public String getUse_yn() {
		return use_yn;
	}
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}
	public String getReg_nm() {
		return reg_nm;
	}
	public void setReg_nm(String reg_nm) {
		this.reg_nm = reg_nm;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	
	public String getNwend_date() {
		return nwend_date;
	}
	public void setNwend_date(String nwend_date) {
		this.nwend_date = nwend_date;
	}
	public int getView_count() {
		return view_count;
	}
	public void setView_count(int view_count) {
		this.view_count = view_count;
	}
	public String getNext_board_seq() {
		return next_board_seq;
	}
	public void setNext_board_seq(String next_board_seq) {
		this.next_board_seq = next_board_seq;
	}
	public String getNext_subject() {
		return next_subject;
	}
	public void setNext_subject(String next_subject) {
		this.next_subject = next_subject;
	}
	public String getNext_password() {
		return next_password;
	}
	public void setNext_password(String next_password) {
		this.next_password = next_password;
	}
	public String getPrev_board_seq() {
		return prev_board_seq;
	}
	public void setPrev_board_seq(String prev_board_seq) {
		this.prev_board_seq = prev_board_seq;
	}
	public String getPrev_subject() {
		return prev_subject;
	}
	public void setPrev_subject(String prev_subject) {
		this.prev_subject = prev_subject;
	}
	public String getPrev_password() {
		return prev_password;
	}
	public void setPrev_password(String prev_password) {
		this.prev_password = prev_password;
	}
	public List<FileBean> getFileList() {
		return fileList;
	}
	public void setFileList(List<FileBean> fileList) {
		this.fileList = fileList;
	}
	
	public String getUpdateYn() {
		return updateYn;
	}
	public void setUpdateYn(String updateYn) {
		this.updateYn = updateYn;
	}
	
	public String getPrivate_yn() {
		return private_yn;
	}
	public void setPrivate_yn(String private_yn) {
		this.private_yn = private_yn;
	}
	public String getAnswer_cnt() {
		return answer_cnt;
	}
	public void setAnswer_cnt(String answer_cnt) {
		this.answer_cnt = answer_cnt;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getKaptcha() {
		return kaptcha;
	}
	public void setKaptcha(String kaptcha) {
		this.kaptcha = kaptcha;
	}
	public String getTop_news_at() {
		return top_news_at;
	}
	public void setTop_news_at(String top_news_at) {
		this.top_news_at = top_news_at;
	}
	public String getCont_hash() {
		return cont_hash;
	}
	public void setCont_hash(String cont_hash) {
		this.cont_hash = cont_hash;
	}
	public String getSrc_status() {
		return src_status;
	}
	public void setSrc_status(String src_status) {
		this.src_status = src_status;
	}
	public String getSrcContDate() {
		return srcContDate;
	}
	public void setSrcContDate(String srcContDate) {
		this.srcContDate = srcContDate;
	}
	public String getSrcContClass() {
		return srcContClass;
	}
	public void setSrcContClass(String srcContClass) {
		this.srcContClass = srcContClass;
	}
	public String getCntyp_nm() {
		return cntyp_nm;
	}
	public void setCntyp_nm(String cntyp_nm) {
		this.cntyp_nm = cntyp_nm;
	}
	public String getCntyp_eng_nm() {
		return cntyp_eng_nm;
	}
	public void setCntyp_eng_nm(String cntyp_eng_nm) {
		this.cntyp_eng_nm = cntyp_eng_nm;
	}
	public int getSrcPageUnit() {
		return srcPageUnit;
	}
	public void setSrcPageUnit(int srcPageUnit) {
		this.srcPageUnit = srcPageUnit;
	}
	public String getSrcContYear() {
		return srcContYear;
	}
	public void setSrcContYear(String srcContYear) {
		this.srcContYear = srcContYear;
	}
	public String getListYn() {
		return listYn;
	}
	public void setListYn(String listYn) {
		this.listYn = listYn;
	}
	public String getNtt_pblct_issno() {
		return ntt_pblct_issno;
	}
	public void setNtt_pblct_issno(String ntt_pblct_issno) {
		this.ntt_pblct_issno = ntt_pblct_issno;
	}
	public String getReal_name() {
		return real_name;
	}
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	public String getChoiseBoardSeq() {
		return choiseBoardSeq;
	}
	public void setChoiseBoardSeq(String choiseBoardSeq) {
		this.choiseBoardSeq = choiseBoardSeq;
	}
}