package com.nurim.oecdstat.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nurim.oecdstat.board.bean.BoardBean;
import com.nurim.oecdstat.board.service.BoardService;
import com.nurim.oecdstat.common.bean.FileBean;
import com.nurim.oecdstat.common.dao.CommonDAO;
import com.nurim.oecdstat.common.util.BoardUtil;
import com.nurim.oecdstat.common.util.FileProperties;
import com.nurim.oecdstat.common.util.HtmlUtil;

@Controller
public class BoardController {

	@Autowired private HttpServletRequest request;

	@Autowired private HttpSession session;

	@Autowired private BoardService boardService;
	
	@Autowired private CommonDAO commonDao;

	boolean success = false;

	/**
	 * 게시판 목록
	 * @param boardBean, response
	 * @throws Exception
	 * */
	@RequestMapping("kor/board/BoardList.html")
	private String selectBoardList(BoardBean boardBean) throws Exception {
		BoardUtil boardUtil = new BoardUtil(); 

		String listYn = "";

		boardBean.setPageUnit(getPageUnit(boardBean.getPageUnit(), boardBean.getBoard_class(), boardBean.getListYn()));

		if(boardBean.getBoard_class().equals("BOARD30") && boardBean.getBoard_class() != null) {
//			int page_unit = boardBean.getSrcPageUnit(); //홍보동영상 화면에 동영상 3개씩 출력을 위함
//			boardBean.setPageUnit(page_unit);

			List<BoardBean> contYear = boardService.selectBeanList("board.selectContYear", boardBean);
			if(contYear.size() > 0) {
				if(boardBean.getSrcContYear() != null && !"".equals(boardBean.getSrcContYear())) {
					request.setAttribute("srcContYear", boardBean.getSrcContYear());
				} else {
					request.setAttribute("srcContYear", contYear.get(0).getSrcContYear());
				}
			} else {
				boardBean.setSrcContYear(null);
				request.setAttribute("contYear", null);
			}

			request.setAttribute("contYear", contYear);
		}

		if("BOARD17".equals(boardBean.getBoard_class()) || "BOARD20".equals(boardBean.getBoard_class())) {
			boardBean.setSrcContDate("SEARCH");
		}

		// 웹진, 리스트 구분
		if("BOARD15".equals(boardBean.getBoard_class())) {
			boardBean.setSrcContDate("SEARCH");
			
			if(boardBean.getListYn() != null && !"".equals(boardBean.getListYn())) {
				listYn = boardBean.getListYn();
				
				if("N".equals(boardBean.getListYn())) {
					boardBean.setPageUnit(10);
					if(boardBean.getLastIndex() == 10) {
						boardBean.setLastIndex(10);
					}
				}
			} else {
				listYn = "N";
				boardBean.setPageUnit(10);
				if(boardBean.getLastIndex() == 10) {
					boardBean.setLastIndex(10);
				}
			}
		}

		if(boardBean.getKeyword() == null || "".equals(boardBean.getKeyword())) {
			if(boardBean.getChoiseBoardSeq() != null && !"".equals(boardBean.getChoiseBoardSeq())) {
				int pageNum = boardService.selectInt("board.selectBoardPageNum", boardBean);

				if(pageNum > 0) {
					boardBean.setPageIndex(pageNum);
				}

				if(!"Y".equals(boardBean.getUnitySearchAt())) request.setAttribute("choiseBoardSeq", boardBean.getChoiseBoardSeq());
			}
		}

		boardBean = (BoardBean)boardUtil.setPagination(boardBean);

		List<BoardBean> boardList = boardService.selectBeanList("board.selectBoardList", boardBean);

		if(boardList != null && boardList.size() > 0) {
			boardBean.setTotCount(boardList.get(0).getTotCount());
			boardBean.setTotalPage(boardUtil.getTotalPage(boardBean.getPageUnit(), boardBean.getTotCount()));
			boardBean.setPageUrl("BoardList.html");

			if("BOARD17".equals(boardBean.getBoard_class()) || "BOARD20".equals(boardBean.getBoard_class())) {
				request.setAttribute("pagination", boardUtil.getSmallPageIndex(boardBean, request.getContextPath(), "Y"));
			} else {
				request.setAttribute("pagination", boardUtil.getPageIndex(boardBean, request.getContextPath(), "Y"));
			}

			//홍보동영상 영상 파일(공지사항, 문의사항은 리스트 목록에서는 파일을 사용하지 않음. 필요에 따라 해당 내용은 변경)
//			if(boardBean.getBoard_class() != null && boardBean.getBoard_class().equals("BOARD30")) {
			if("BOARD17".equals(boardBean.getBoard_class()) || "BOARD30".equals(boardBean.getBoard_class())) {
				List<FileBean> temp = commonDao.selectBeanList("file.selectBoardAttachList", boardBean.getBoard_seq());

				if(temp.size() > 0) {
					boardBean.setFileList(temp);
				}
			}
		}

		request.setAttribute("boardList", boardList);
		request.setAttribute("boardBean", boardBean);
		request.setAttribute("thumbnailPath", FileProperties.getProperty("cmsUrl") + FileProperties.getProperty("thumbnailPath"));
		request.setAttribute("uploadFilePath", FileProperties.getProperty("cmsUrl") + FileProperties.getProperty("filePath"));
		request.setAttribute("mediaPath", FileProperties.getProperty("cmsUrl") + FileProperties.getProperty("mediaPath"));
		request.setAttribute("codeList", getCodeList(boardBean.getBoard_class()));

		return getReturnUrl(0, boardBean.getBoard_class(), listYn);
	}

	/**
	 * 게시판 목록(Ajax 조회)
	 * @param boardBean, response, model
	 * @throws Exception
	 * */
	@RequestMapping("kor/board/SearchBoardAjaxList.html")
	private String selectBoardAjaxList(BoardBean boardBean, Model model) throws Exception {
		BoardUtil boardUtil = new BoardUtil(); 

		if(boardBean.getBoard_class().equals("BOARD30") && boardBean.getBoard_class() != null) {
			int page_unit = boardBean.getSrcPageUnit(); //홍보동영상 화면에 동영상 3개씩 출력을 위함
			boardBean.setPageUnit(page_unit);
		}

		boardBean = (BoardBean)boardUtil.setPagination(boardBean);

		List<BoardBean> boardList = boardService.selectBeanList("board.selectBoardList", boardBean);

		if(boardList != null && boardList.size() > 0) {
			boardBean.setTotCount(boardList.get(0).getTotCount());
			boardBean.setTotalPage(boardUtil.getTotalPage(boardBean.getPageUnit(), boardBean.getTotCount()));
			boardBean.setPageUrl("BoardList.html");

			model.addAttribute("pagination", boardUtil.getPageIndex(boardBean, request.getContextPath(), "Y"));

			if("BOARD17".equals(boardBean.getBoard_class()) || "BOARD30".equals(boardBean.getBoard_class())) {
				List<FileBean> temp = commonDao.selectBeanList("file.selectBoardAttachList", boardBean.getBoard_seq());

				if(temp.size() > 0) {
					boardBean.setFileList(temp);
				}
			}
		}

		model.addAttribute("boardList", boardList);
		model.addAttribute("boardBean", boardBean);
		model.addAttribute("thumbnailPath", FileProperties.getProperty("cmsUrl") + FileProperties.getProperty("thumbnailPath"));
		model.addAttribute("mediaPath", FileProperties.getProperty("mediaPath"));
		model.addAttribute("codeList", getCodeList(boardBean.getBoard_class()));

		return "jsonView";
	}

	/**
	 * 게시판 등록 및 수정화면
	 * @param boardBean, response
	 * @throws Exception
	 * */
	@RequestMapping("kor/board/BoardForm.html")
	private String selectBoardForm(BoardBean boardBean, HttpServletResponse response) throws Exception {
		// 페이징 후 상세에서 목록으로 이동 시 페이징
		if(boardBean.getPageIdx() != null) {
			boardBean.setPageIndex(boardBean.getPageIdx());
		}	

		if(boardBean != null) {
			BoardBean resultBean = boardService.selectBean("board.selectBoardDetail", boardBean);

			if(resultBean != null) {
				boardBean.setUpdateYn("Y");

				List<FileBean> temp = commonDao.selectBeanList("file.selectBoardAttachList", boardBean.getBoard_seq());
				if(temp.size() > 0) {
					resultBean.setFileList(temp);
				}
			} else {
				if("!BOARD03".equals(boardBean.getBoard_class())) {
					HtmlUtil.UrlMove("조회에 실패하였습니다.", "BoardList.html?board_class="+boardBean.getBoard_class()+"&menuId="+boardBean.getMenu_id(), response);
				}
			}

			request.setAttribute("resultBean", resultBean);
		}

		request.setAttribute("boardBean", boardBean);

		return getReturnUrl(1, boardBean.getBoard_class(), "");
	}

	/**
	 * 게시글 상세보기
	 * @param boardBean
	 * @throws Exception
	 * */
	@RequestMapping("kor/board/BoardDetail.html")
	private String selectBoardDetail(BoardBean boardBean, HttpServletResponse response) throws Exception {
		// 페이징후상세에서 목록으로 이동 시 페이징
		if(boardBean.getPageIdx() != null) {
			boardBean.setPageIndex(boardBean.getPageIdx());
		}

		if(boardBean != null) {
			if(!"".equals(boardBean.getBoard_seq()) && boardBean.getBoard_seq() != null) {
				boardService.updateViewCount("board.updateViewCount", boardBean);

				BoardBean resultBean = boardService.selectBean("board.selectBoardDetail", boardBean);
				if(resultBean != null) {
					//문의사항 문의 내용 '\r\n'문자 : 조회 시 <br>로 치환
					if("BOARD03".equals(boardBean.getBoard_class())) {
						resultBean.setContent(resultBean.getContent().replace("\r\n", "<br>"));
					}
					
					List<FileBean> temp = commonDao.selectBeanList("file.selectBoardAttachList", boardBean.getBoard_seq());

					if(temp.size() > 0) {
						resultBean.setFileList(temp);
					}

					boardBean.setUpdateYn("Y");

					//문의사항 답변 조회
					if("BOARD03".equals(boardBean.getBoard_class())) {
						BoardBean answerBean = boardService.selectBean("board.selectBoardAnswer", boardBean);

						if(answerBean != null) {
							//문의사항 답변 내용 '\r\n'문자 : 조회 시 <br>로 치환
							if("BOARD03".equals(boardBean.getBoard_class())) {
								answerBean.setContent(answerBean.getContent().replace("\r\n", "<br>"));
							}
							List<FileBean> answer_temp = commonDao.selectBeanList("file.selectBoardAttachList", answerBean.getBoard_seq());

							if(answer_temp.size()>0) {
								answerBean.setFileList(answer_temp);
							}
						}

						if(answerBean != null && answerBean.getBoard_seq() != null && !"".equals(answerBean.getBoard_class())) {
							request.setAttribute("answerBean", answerBean);
						}
					}
				} else {
					HtmlUtil.UrlMove("조회에 실패하였습니다.", "BoardList.html?board_class="+boardBean.getBoard_class()+"&menuId="+boardBean.getMenu_id(), response);
				}

				request.setAttribute("resultBean", resultBean);
			} else {
				HtmlUtil.UrlMove("조회에 실패하였습니다.", "BoardList.html?board_class="+boardBean.getBoard_class()+"&menuId="+boardBean.getMenu_id(), response);
			}
		}

		request.setAttribute("boardBean", boardBean);
		request.setAttribute("imgPath", FileProperties.getProperty("cmsUrl") + FileProperties.getProperty("imgPath"));

		return getReturnUrl(2, boardBean.getBoard_class(), "");
	}

	/**
	 * 게시글 상세보기(Ajax 조회)
	 * @param boardBean, response, model
	 * @throws Exception
	 * */
	@RequestMapping("kor/board/ViewBoardAjaxDetail.html")
	private String selectBoardAjaxDetail(BoardBean boardBean, HttpServletResponse response, Model model) throws Exception {
		// 페이징 후 상세에서 목록으로 이동 시 페이징
		String result = "S";

		if(boardBean.getPageIdx() != null) {
			boardBean.setPageIndex(boardBean.getPageIdx());
		}

		if(boardBean != null) {
			if(!"".equals(boardBean.getBoard_seq()) && boardBean.getBoard_seq() != null) {
				boardService.updateViewCount("board.updateViewCount", boardBean);

				BoardBean resultBean = boardService.selectBean("board.selectBoardDetail", boardBean);
				if(resultBean != null) {
					List<FileBean> temp = commonDao.selectBeanList("file.selectBoardAttachList", boardBean.getBoard_seq());

					if(temp.size() > 0) {
						resultBean.setFileList(temp);
					}

					boardBean.setUpdateYn("Y");

					//문의사항 답변 조회
					if("BOARD03".equals(boardBean.getBoard_class())) {
						BoardBean answerBean = boardService.selectBean("board.selectBoardAnswer", boardBean);

						if(answerBean != null) {
							List<FileBean> answer_temp = commonDao.selectBeanList("file.selectBoardAttachList", answerBean.getBoard_seq());

							if(answer_temp.size()>0) {
								answerBean.setFileList(answer_temp);
							}
						}

						if(answerBean != null && answerBean.getBoard_seq() != null && !"".equals(answerBean.getBoard_class())) {
							request.setAttribute("answerBean", answerBean);
						}
					}
				} else {
					result = "F";
				}

				model.addAttribute("resultBean", resultBean);
			} else {
				result = "F";
			}
		}

		model.addAttribute("result", result);
		model.addAttribute("boardBean", boardBean);
		model.addAttribute("imgPath", FileProperties.getProperty("cmsUrl") + FileProperties.getProperty("imgPath"));

		return "jsonView";
	}

	/**
	 * 서비스 화면 미리보기(상세화면, 관리자)
	 * @param boardBean
	 * @throws Exception
	 * */
	@RequestMapping("kor/board/popup/PreviewDetail.html")
	private String selecPreviewtBoardDetail(BoardBean boardBean, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if(boardBean != null) {
			if(!"".equals(boardBean.getBoard_seq()) && boardBean.getBoard_seq() != null) {
				BoardBean resultBean = boardService.selectBean("board.selectPreviewBoardDetail", boardBean);
				if(resultBean != null) {
					List<FileBean> temp = commonDao.selectBeanList("file.selectBoardAttachList", boardBean.getBoard_seq());

					if(temp.size() > 0) {
						resultBean.setFileList(temp);
					}
					
				} else {
					HtmlUtil.UrlMove("조회에 실패하였습니다.", "BoardList.html?board_class="+boardBean.getBoard_class()+"&menuId="+boardBean.getMenu_id(), response);
				}

				request.setAttribute("resultBean", resultBean);
			} else {
				HtmlUtil.UrlMove("조회에 실패하였습니다.", "BoardList.html?board_class="+boardBean.getBoard_class()+"&menuId="+boardBean.getMenu_id(), response);
			}
		}

		request.setAttribute("boardBean", boardBean);
		request.setAttribute("imgPath", FileProperties.getProperty("cmsUrl") + FileProperties.getProperty("imgPath"));

		return getReturnPreviewUrl(boardBean.getBoard_class(), true);
	}
	
	/**
	 * 서비스 화면 미리보기(리스트형, 관리자)
	 * @param boardBean, response
	 * @throws Exception
	 * */
	@RequestMapping("kor/board/popup/PreviewList.html")
	private String selectPreviewBoardList(BoardBean boardBean) throws Exception {
		BoardUtil boardUtil = new BoardUtil(); 
		
		if(boardBean != null) {
			boardBean = (BoardBean)boardUtil.setPagination(boardBean);
		
			if(boardBean.getBoard_seq() != null && !"".equals(boardBean.getBoard_seq())) {
				BoardBean boardList = boardService.selectBean("board.selectPreviewList", boardBean);
				
				if(boardList != null) {
					boardBean.setTotCount(boardList.getTotCount());
					boardBean.setTotalPage(boardUtil.getTotalPage(boardBean.getPageUnit(), boardBean.getTotCount()));
					
					List<FileBean> temp = commonDao.selectBeanList("file.selectBoardAttachList", boardBean.getBoard_seq());
					
					if(temp.size() > 0) {
						boardBean.setFileList(temp);
					}
				}

				request.setAttribute("boardList", boardList);
			}
		}

		request.setAttribute("pagination", boardUtil.getPageIndex(boardBean, request.getContextPath(), "Y"));
		request.setAttribute("boardBean", boardBean);
		request.setAttribute("mediaPath", FileProperties.getProperty("mediaPath"));
		request.setAttribute("codeList", getCodeList(boardBean.getBoard_class()));

		return getReturnPreviewUrl(boardBean.getBoard_class(), false);
	}
	
	/**
	 * 게시글 등록 및 수정
	 * @param boardBean, response
	 * @throws Exception
	 * */
	@RequestMapping("kor/board/InsertBoard.html")
	private void InsertBoard(BoardBean boardBean, HttpServletResponse response) throws Exception {
		String kaptcha_session_key = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		
		String kaptchaReceived = boardBean.getKaptcha();
		
		if(kaptchaReceived == null || !kaptchaReceived.equalsIgnoreCase(kaptcha_session_key)) {
			HtmlUtil.BeforeUrl("자동 입력 방지 글자를 다시 확인하여 주십시오.", response);
		} else {
			if(boardBean != null && "Y".equals(boardBean.getUpdateYn()) && boardBean.getUpdateYn() != null) {
				success = boardService.update("board.updateBoard", boardBean);

				if(success == true) {				
					HtmlUtil.UrlMove("정상적으로 수정되었습니다.", "BoardList.html?board_class="+boardBean.getBoard_class()+"&menuId="+boardBean.getMenu_id(), response);
				} else {
					HtmlUtil.UrlMove("수정에 실패하였습니다." , "BoardList.html?board_class="+boardBean.getBoard_class()+"&menuId="+boardBean.getMenu_id(), response);
				}
			} else {
				// board_seq가 없는 경우
				if(boardBean != null && "".equals(boardBean.getBoard_seq())) {
					boardBean.setBoard_seq(boardService.selectString("board.selectNextBoardSeq", boardBean));
				}

				if(boardBean != null && !"".equals(boardBean.getBoard_seq()) && boardBean.getBoard_seq() != null) {
					if("BOARD03".equals(boardBean.getBoard_class())) {
						boardBean.setAnswer_yn("N");
					}
					success = boardService.insert("board.insertBoard",boardBean);

					if(success == true) {
						HtmlUtil.UrlMove("정상적으로 등록되었습니다.", "BoardList.html?board_class="+boardBean.getBoard_class()+"&menuId="+boardBean.getMenu_id(), response);
					} else {
						HtmlUtil.UrlMove("등록에 실패하였습니다.", "BoardList.html?board_class="+boardBean.getBoard_class()+"&menuId="+boardBean.getMenu_id(), response);
					}
				}
			}
		}
	}
	
	/**
	 * 다음번호조회
	 * @param boardBean, response
	 * @throws Exception
	 * */
	@RequestMapping("kor/board/NextBoardSeq.html")
	private void selectNextBoardSeq(BoardBean boardBean, HttpServletResponse response) throws Exception {
		String board_seq = boardService.selectString("board.selectNextBoardSeq",boardBean);

		response.getWriter().print(board_seq);
	}
	
	/**
	 * 홍보동영상 조회수 증가
	 * @param boardBean, response
	 * @throws Exception
	 * */
	@RequestMapping("kor/board/VideoViewCount.html")
	private void selecVideoViewCount(BoardBean boardBean, HttpServletResponse response) throws Exception {
		boardService.selectString("board.updateViewCount",boardBean);
	}
	
	/**
	 * 게시글 삭제
	 * @param boardBean, response
	 * @throws Exception
	 * */
	@RequestMapping("kor/board/DeleteBoard.html")
	private void DeleteBoard(BoardBean boardBean, HttpServletResponse response) throws Exception {
		
		if(boardBean != null && !"".equals(boardBean.getBoard_seq()) && boardBean.getBoard_seq() != null) {
			success = boardService.delete("board.deleteBoard", boardBean.getBoard_seq());
			
			if (success == true) {
				HtmlUtil.UrlMove("정상적으로 삭제되었습니다.", "BoardList.html?board_class=" + boardBean.getBoard_class(), response);
			} else {
				HtmlUtil.UrlMove("삭제에 실패하였습니다.", "BoardList.html?board_class=" + boardBean.getBoard_class(), response);
			}
		} else {
			HtmlUtil.UrlMove("삭제에 실패하였습니다.", "BoardList.html?board_class=" + boardBean.getBoard_class(), response);
		}
	}
	
	/**
	 * 팝업화면 이동
	 * @param boardBean
	 * @throws Exception
	 * */
	@RequestMapping("kor/board/popup/pwCheckPopup.html")
	private String qnaPwdPopup(BoardBean boardBean) throws Exception {
		request.setAttribute("boardBean", boardBean);
		
		return "kor/board/popup/pwCheckPopup";
	} 
	
	/**
	 * 비밀번호 확인
	 * 
	 * @param boardBean, response
	 * @throws Exception
	 */
	@RequestMapping("kor/board/pwdChk.html")
	private void pwdChk(BoardBean boardBean, HttpServletResponse response) throws Exception {

		String pwd = request.getParameter("pwd");
		String conpwd = boardService.selectBoardPwd("board.selectBoardPwd", boardBean);

		if (pwd.equals(conpwd)) {
			HtmlUtil.popUpClose("확인되었습니다.", response, true);
		} else {
			HtmlUtil.popUpClose("잘못된 비밀번호 입니다.", response, false);
		}
	}

/** [Private : 게시판 관련 조회 함수] ************************************************************************************************************************/
	/**
	 * BOARD_CLASS에 따라 필요한 코드 목록 조회
	 * 
	 */
	private List<Map<String, Object>> getCodeList(String board_class) throws Exception {
		List<Map<String, Object>> resultList = null;
		int boardNum = Integer.parseInt(StringUtils.defaultIfEmpty(board_class, "0").replaceAll("[^0-9]", ""));
		String code_id = "";

		switch(boardNum) {
			case 15: code_id = "PBL0000"; break;
			case 17: code_id = "STRP000"; break;
			default: code_id = ""; break;
		}

		if(!"".equals(code_id)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("up_code", code_id);
			map.put("board_class", board_class);

			resultList = boardService.selectMapList("board.selectBoardCodeList", map);
		}

		return resultList;
	}

	/**
	 * BOARD_CLASS에 따라 이동할 URL 조회
	 * urlType : 0 - List, 1 - Form, 2 - Detail
	 */
	private String getReturnUrl(int urlType, String board_class, String listYn) {
		String url = "";
		int boardNum = Integer.parseInt(StringUtils.defaultIfEmpty(board_class, "0").replaceAll("[^0-9]", ""));
		switch(boardNum) {
			case 3: url = (urlType == 2 ? "kor/board/qnaDetail" : urlType == 1 ? "kor/board/qnaForm" : "kor/board/qnaList"); break;	// 문의사항 화면
			case 15: url = ("Y".equals(listYn) ? "kor/board/publicationList" : "kor/board/publThumbnail"); break;								// 발간물 화면
			case 17: url = (urlType == 2 ? "kor/board/kisdiStatReportList" : "kor/board/kisdiStatReportList"); break;							// KISDI STAT REPORT 화면
			case 20: url = (urlType == 2 ? "kor/board/statCardNewsDetail" : "kor/board/statCardNewsList"); break;							// 통계카드뉴스 화면
			case 30: url = (urlType == 2 ? "" : "kor/board/promotionVideoList"); break;															// 동영상 관리 화면
			default: url = (urlType == 2 ? "kor/board/boardDetail" : "kor/board/boardList"); break;											// 공지사항 및 기타 게시판
		}

		return url;
	}
	
	/**
	 * BOARD_CLASS에 따라 이동할 URL 지정
	 */
	private String getReturnPreviewUrl(String board_class, boolean isDetail) {
		String url = "";
		switch (board_class) {
			case "BOARD02": url = "kor/board/popup/previewDetail/boardPreviewPopup"; break;
			case "BOARD17": url = isDetail ? "kor/board/popup/previewDetail/kisdiStatReportDetailPopup" : "kor/board/popup/previewList/kisdiStatReportPrevPopup"; break;
			case "BOARD20": url = "kor/board/popup/previewDetail/statCardNewsPrevPopup"; break;
			case "BOARD30": url = "kor/board/popup/previewList/videoPreviewPopup"; break;
			default:break;	
		}
		return url;
	}

	/**
	 * BOARD_CLASS에 따라 조회 건수 선택
	 * 
	 */
	private int getPageUnit(int defPageUnit, String board_class, String listYn) throws Exception {
		int page_unit = defPageUnit;
		int boardNum = Integer.parseInt(StringUtils.defaultIfEmpty(board_class, "0").replaceAll("[^0-9]", ""));

		if(!"Y".equals(listYn)) {
			switch(boardNum) {
				case 15: page_unit = 6; break;
				case 17: page_unit = 12; break;
				case 20: page_unit = 8; break;
				case 30: page_unit = 3; break;
				default: page_unit = defPageUnit; break;
			}
		}

		return page_unit;
	}
}