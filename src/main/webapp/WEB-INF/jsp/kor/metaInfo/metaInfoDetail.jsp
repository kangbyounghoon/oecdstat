<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/etc/jsp_header.jspf"%>

<div class="meta_top">
	<div class="meta_title_wrap">
		<p class="meta_title">
			<c:out value="${metaBean.up_list_code_nm}"/>(Physicians) : <span class="meta_t_span">(${metaBean.ix_no}) ${metaBean.ix_stats_nm_ko }</span>
		</p>
	</div>
	<!--meta_view_title-end-->

	<div class="meta_anchor_wrap">
		<a href="#meta_cnt_01${metaBean.ix_no }" class="meta_anchor ${empty metaBean.a_href or metaBean.a_href eq '#meta_cnt_01${metaBean.ix_no ? 'tab_on' : ''}">지표 요약 정보</a> 
		<a href="#meta_cnt_02${metaBean.ix_no }" class="meta_anchor ${metaBean.a_href eq '#meta_cnt_02${metaBean.ix_no ? 'tab_on' : ''}">지표 정의 및 작성 정보</a> 
		<a href="#meta_cnt_03${metaBean.ix_no }" class="meta_anchor ${metaBean.a_href eq '#meta_cnt_03${metaBean.ix_no ? 'tab_on' : ''}">지표 설명 자료</a>
	</div>
</div>

<div class="meta_cnt">

	<div id="meta_cnt_01${metaBean.ix_no }" class="cnt_box">
		<div class="cnt_title">1. 지표 요약 정보</div>
		<table class="cnt_table">
			<caption>지표 요약 정보</caption>
			<colgroup>
				<col width="15%">
				<col width="10%">
				<col width="25%">
				<col width="15%">
				<col width="10%">
				<col width="25%">
			</colgroup>
			<tbody>
				<tr class="cnt_tr">
					<th class="cnt_th" rowspan="2" scope="rowgroup">지표번호</th>
					<td class="cnt_td" rowspan="2" colspan="2">${sumryBean.ix_no }</td>
					<th class="cnt_th" rowspan="2" scope="rowgroup">지표관련 소관부서</th>
					<td class="cnt_td" colspan="2">${sumryBean.ix_relate_jrsd_dept1 }</td>
				</tr>

				<tr class="cnt_tr">
					<td class="cnt_td" colspan="5">${sumryBean.ix_relate_jrsd_dept2 }</td>
				</tr>

				<tr class="cnt_tr">
					<th class="cnt_th" rowspan="2" scope="rowgroup">제출지표(통계)명</th>
					<th class="cnt_th" scope="row">영문</th>
					<td class="cnt_td">${sumryBean.ix_stats_nm_en }</td>
					<th class="cnt_th" rowspan="2" scope="rowgroup">단위</th>
					<th class="cnt_th" scope="row">영문</th>
					<td class="cnt_td">${sumryBean.ix_unit_en }</td>
				</tr>

				<tr class="cnt_tr">
					<th class="cnt_th" scope="row">한글</th>
					<td class="cnt_td">${sumryBean.ix_stats_nm_ko }</td>
					<th class="cnt_th" scope="row">한글</th>
					<td class="cnt_td">${sumryBean.ix_unit_ko }</td>
				</tr>

				<tr class="cnt_tr">
					<th class="cnt_th" rowspan="2" scope="rowgroup">통계정의</th>
					<th class="cnt_th" scope="row">영문</th>
					<td class="cnt_td" colspan="4">${sumryBean.stats_dfn_en }</td>
				</tr>

				<tr class="cnt_tr">
					<th class="cnt_th" scope="row">한글</th>
					<td class="cnt_td" colspan="4">${sumryBean.stats_dfn_ko }</td>
				</tr>

				<tr class="cnt_tr">
					<th class="cnt_th" scope="row">산출식</th>
					<td class="cnt_td" colspan="5">${sumryBean.comput_frmla }</td>
				</tr>

				<tr class="cnt_tr">
					<th class="cnt_th" scope="row">산출방법</th>
					<td class="cnt_td" colspan="5">${sumryBean.comput_mth }	</td>
				</tr>

				<tr class="cnt_tr">
					<th class="cnt_th" scope="row">자료출처</th>
					<td class="cnt_td p_0px" colspan="5">
						<div class="table_in_box">
							<div class="table_in_title">제출 지표명</div>
							<div class="table_in_txt">${sumryBean.presentn_ix_nm }</div>
						</div>
						<div class="table_in_box">
							<div class="table_in_title">기관</div>
							<div class="table_in_txt">${sumryBean.instt }</div>
						</div>
						<div class="table_in_box">
							<div class="table_in_title">통계(자료)명</div>
							<div class="table_in_txt">${sumryBean.stats_dta_nm }</div>
						</div>
						<div class="table_in_box">
							<div class="table_in_title">승인여부</div>
							<div class="table_in_txt">${sumryBean.confm_at }</div>
						</div>
						<div class="table_in_box">
							<div class="table_in_title">공표여부</div>
							<div class="table_in_txt">${sumryBean.publict_at }</div>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<!--cnt_box-end-->

	<div id="meta_cnt_02${metaBean.ix_no }" class="cnt_box">

		<div class="cnt_title">2. 지표 정의 및 작성 정보</div>
		<table class="cnt_table">
			<caption>지표 정의 및 작성 정보</caption>
			<colgroup>
				<col width="10%">
				<col width="45%">
				<col width="45%">
			</colgroup>
			<tbody>
				<tr class="cnt_tr">
					<th class="cnt_th" scope="col">구분</th>
					<th class="cnt_th" scope="col">한글</th>
					<th class="cnt_th" scope="col">영문</th>
				</tr>
				<tr class="cnt_tr">
					<th class="cnt_th" scope="row">OECD 정의</th>
					<td class="cnt_td">임상 의사 (Practising physicians)란 환자에게 직접 서비스를
						제공하는 의사를 말한다. <br>
					<br>포함: <br>- 의대졸업자 및 면허소지자 <br>- 인턴 및 레지던트 (적절한 학위를
						갖고 있으며 의료 기관에서 졸업 후 인턴십 및 레지던트를 하는 동안 다른 의사의 관리감독을 받으며 서비스를 제공함) <br>-
						고용의사 및 자영의사 <br>- 국내에서 활동 면허소지자로 활동 중인 외국의사 <br>- 환자에게
						영상의학, 병리학, 미생물학, 혈액학, 위생학을 포함한 서비스를 제공하는 의사 <br>
					<br>제외: <br>- 미졸업자 <br>- 치과의사, 구강의학 의사, 구강 및 악안면외과의
						<br>- 행정, 연구, 또는 직접적으로 환자와 접촉하지 않는 의사 <br>- 실업 및 은퇴 의사 <br>-
						국외에서 활동 중인 의사 <br>
					<br>참고: 숫자는 연도말 수치여야 함.
					</td>
					<td class="cnt_td">Practising physicians provide services
						directly to patients. <br>
					<br>Inclusion<br>- Practising physicians who have
						completed studies in medicine at university level (granted by
						adequate diploma) and who are licensed to practice<br>-
						Interns and resident physicians (with adequate diploma and
						providing services under supervision of other medical doctors
						during their postgraduate internship or residency in a health care
						facility)<br>- Salaried and self-employed physicians
						delivering services irrespectively of the place of service
						provision<br>- Foreign physicians licensed to practice and
						actively practising in the country<br>- All physicians
						providing services for patients, including radiology, pathology,
						microbiology, hematology, hygiene. <br>
					<br>Exclusion<br>- Students who have not yet graduated<br>-
						Dentists, stomatologists, dental and maxillofacial surgeons<br>-
						Physicians working in administration, research and in other posts
						that exclude direct contact with patients<br>- Unemployed
						physicians and retired physicians<br>- Physicians working
						abroad<br>
					<br>Note: The number should be at the end of the calendar
						year.

					</td>
				<tr class="cnt_tr">
					<th class="cnt_th" scope="row">작성방법</th>
					<td class="cnt_td"></td>
					<td class="cnt_td"></td>

				</tr>

			</tbody>
		</table>

	</div>
	<!--cnt_box-end-->

	<div id="meta_cnt_03${metaBean.ix_no }" class="cnt_box">

		<div class="cnt_title">3. 지표 설명 자료</div>
		<table class="cnt_table">
			<caption>지표 설명 자료</caption>
			<colgroup>
				<col width="10%">
				<col width="10%">
				<col width="12%">
				<col width="34%">
				<col width="34%">
			</colgroup>
			<tbody>

				<tr class="cnt_tr">
					<th class="cnt_th" scope="colgroup" colspan="3">구분</th>
					<th class="cnt_th" scope="col">한글</th>
					<th class="cnt_th" scope="col">영문</th>
				</tr>

				<tr class="cnt_tr">
					<th class="cnt_th" scope="rowgroup" rowspan="14">통계 관련 정보</th>
					<th class="cnt_th th_fff" colspan="2" scope="row">통계명</th>
					<td class="cnt_td">임상의사</td>
					<td class="cnt_td">Practicing Physicians</td>
				</tr>
				<tr class="cnt_tr">
					<th class="cnt_th th_fff" colspan="2" scope="row">승인여부</th>
					<td class="cnt_td">미승인</td>
					<td class="cnt_td">Not assingment for National Statistics</td>
				</tr>
				<tr class="cnt_tr">
					<th class="cnt_th th_fff" colspan="2" scope="row">승인번호</th>
					<td class="cnt_td">-</td>
					<td class="cnt_td">-</td>
				</tr>
				<tr class="cnt_tr">
					<th class="cnt_th th_fff" colspan="2" scope="row">승인년도</th>
					<td class="cnt_td">-</td>
					<td class="cnt_td">-</td>
				</tr>
				<tr class="cnt_tr">
					<th class="cnt_th th_fff" colspan="2" scope="row">작성기관</th>
					<td class="cnt_td">건강보험심사평가원 의료자원연구부</td>
					<td class="cnt_td">Health Insurance</td>
				</tr>
				<tr class="cnt_tr">
					<th class="cnt_th th_fff" colspan="2" scope="row">작성목적</th>
					<td class="cnt_td">국제적으로 비교가능한 OECD 의료자원 통계 생산: 임상의사수 보고</td>
					<td class="cnt_td">To report the international comparable data of the number of practicing physicians related to the health care resource domain</td>
				</tr>
				<tr class="cnt_tr">
					<th class="cnt_th th_fff" colspan="2" scope="row">작성성대상</th>
					<td class="cnt_td">임상의사(Practising physicians)란 환자에게 직접 서비스를 제공하는 의사를 말한다.</td>
					<td class="cnt_td">Practising physicians provide services directly to patients.</td>
				</tr>
				<tr class="cnt_tr">
					<th class="cnt_th th_fff" colspan="2" scope="row">작성형태</th>
					<td class="cnt_td"></td>
					<td class="cnt_td"></td>
				</tr>
				<tr class="cnt_tr">
					<th class="cnt_th th_fff" colspan="2" scope="row">관련법령</th>
					<td class="cnt_td"></td>
					<td class="cnt_td"></td>
				</tr>
				<tr class="cnt_tr">
					<th class="cnt_th th_fff" colspan="2" scope="row">최초작성년도</th>
					<td class="cnt_td"></td>
					<td class="cnt_td"></td>
				</tr>
				<tr class="cnt_tr">
					<th class="cnt_th th_fff" colspan="2" scope="row">최근작성년도</th>
					<td class="cnt_td"></td>
					<td class="cnt_td"></td>
				</tr>
				<tr class="cnt_tr">
					<th class="cnt_th th_fff" colspan="2" scope="row">작성주기</th>
					<td class="cnt_td"></td>
					<td class="cnt_td"></td>
				</tr>
				<tr class="cnt_tr">
					<th class="cnt_th th_fff" colspan="2" scope="row">작성체계</th>
					<td class="cnt_td"></td>
					<td class="cnt_td"></td>
				</tr>
				<tr class="cnt_tr">
					<th class="cnt_th th_fff" colspan="2" scope="row">통계자료명</th>
					<td class="cnt_td">진료비청구명세서</td>
					<td class="cnt_td"></td>
				</tr>

				<tr class="cnt_tr">
					<th class="cnt_th" scope="rowgroup" rowspan="9">공표 관련 정보</th>
					<th class="cnt_th th_fff" scope="rowgroup" rowspan="4">발간물</th>
					<th class="cnt_th th_fff" scope="row">공표여부</th>
					<td class="cnt_td">공표</td>
					<td class="cnt_td">Report</td>
				</tr>
				<tr class="cnt_tr">
					<th class="cnt_th th_fff" scope="row">공표 주기</th>
					<td class="cnt_td">1년</td>
					<td class="cnt_td">Annual</td>
				</tr>
				<tr class="cnt_tr">
					<th class="cnt_th th_fff" scope="row">공표 일정 (시기)</th>
					<td class="cnt_td">6월 30일</td>
					<td class="cnt_td">30th June</td>
				</tr>
				<tr class="cnt_tr">
					<th class="cnt_th th_fff" scope="row">발간물명</th>
					<td class="cnt_td"></td>
					<td class="cnt_td"></td>
				</tr>
				<tr class="cnt_tr">
					<th class="cnt_th th_fff" scope="rowgroup" rowspan="4">전산망<br>(정보
						시스템)
					</th>
					<th class="cnt_th th_fff" scope="row">공표여부</th>
					<td class="cnt_td"></td>
					<td class="cnt_td"></td>
				</tr>
				<tr class="cnt_tr">
					<th class="cnt_th th_fff" scope="row">공표 주기</th>
					<td class="cnt_td"></td>
					<td class="cnt_td"></td>
				</tr>
				<tr class="cnt_tr">
					<th class="cnt_th th_fff" scope="row">공표 일정 (시기)</th>
					<td class="cnt_td"></td>
					<td class="cnt_td"></td>
				</tr>
				<tr class="cnt_tr">
					<th class="cnt_th th_fff" scope="row">사이트명</th>
					<td class="cnt_td"></td>
					<td class="cnt_td"></td>
				</tr>
				<tr class="cnt_tr">
					<th class="cnt_th th_fff" colspan="2" scope="row">미공표 사유</th>
					<td class="cnt_td"></td>
					<td class="cnt_td"></td>
				</tr>

				<tr class="cnt_tr">
					<th class="cnt_th" scope="rowgroup" rowspan="5">작성자 관련 정보</th>
					<th class="cnt_th th_fff" colspan="2" scope="row">소속 기관명</th>
					<td class="cnt_td">건강보험심사평가원</td>
					<td class="cnt_td"></td>
				</tr>
				<tr class="cnt_tr">
					<th class="cnt_th th_fff" colspan="2" scope="row">소속 부서명</th>
					<td class="cnt_td">의료자원연구부</td>
					<td class="cnt_td"></td>
				</tr>
				<tr class="cnt_tr">
					<th class="cnt_th th_fff" colspan="2" scope="row">이름</th>
					<td class="cnt_td"></td>
					<td class="cnt_td"></td>
				</tr>
				<tr class="cnt_tr">
					<th class="cnt_th th_fff" colspan="2" scope="row">전화번호</th>
					<td class="cnt_td"></td>
					<td class="cnt_td"></td>
				</tr>
				<tr class="cnt_tr">
					<th class="cnt_th th_fff" colspan="2" scope="row">이메일</th>
					<td class="cnt_td"></td>
					<td class="cnt_td"></td>
				</tr>

				<tr class="cnt_tr">
					<th class="cnt_th" scope="rowgroup" rowspan="14">지표 관련 정보</th>
					<th class="cnt_th th_fff" colspan="2" scope="row">지표명</th>
					<td class="cnt_td">임상의사</td>
					<td class="cnt_td">Practicing Physicians</td>
				</tr>
				<tr class="cnt_tr">
					<th class="cnt_th th_fff" scope="rowgroup" rowspan="4">작성방법</th>
					<th class="cnt_th th_fff" scope="row">보고내용/조사문항</th>
					<td class="cnt_td"></td>
					<td class="cnt_td"></td>
				</tr>
				<tr class="cnt_tr">
					<th class="cnt_th th_fff" scope="row">산출방법</th>
					<td class="cnt_td">해당연도 12월 31일 기준<br>포함기준: 공공 및 민간 의료기관에서 근무하는 의사, 인턴 및 레지던트, 한의사 <br>제외기준: 치과의사</td>
					<td class="cnt_td"></td>
				</tr>
				<tr class="cnt_tr">
					<th class="cnt_th th_fff" scope="row">범주</th>
					<td class="cnt_td"></td>
					<td class="cnt_td"></td>
				</tr>
				<tr class="cnt_tr">
					<th class="cnt_th th_fff" scope="row">비고</th>
					<td class="cnt_td"></td>
					<td class="cnt_td"></td>
				</tr>
				<tr class="cnt_tr">
					<th class="cnt_th th_fff" scope="rowgroup" rowspan="4">공표</th>
					<th class="cnt_th th_fff" scope="row">발간물수록 여부</th>
					<td class="cnt_td"></td>
					<td class="cnt_td"></td>
				</tr>
				<tr class="cnt_tr">
					<th class="cnt_th th_fff" scope="row">전산망수록 여부</th>
					<td class="cnt_td"></td>
					<td class="cnt_td"></td>
				</tr>
				<tr class="cnt_tr">
					<th class="cnt_th th_fff" scope="row">미수록 사유</th>
					<td class="cnt_td"></td>
					<td class="cnt_td"></td>
				</tr>

			</tbody>
		</table>

	</div>
	<!--cnt_box-end-->

</div>