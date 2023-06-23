package com.nurim.oecdstat.common.util;

/**
 * Camelcase 변환 클래스
 * <p><b>NOTE:</b>
 * @author 공공사업1팀 함아름
 * @since 2020.09.06
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일			수정자           수정내용
 *  ---------			----------		---------------------------
 *   2020.09.06		함아름          최초 생성
 *
 * </pre>
 */
import java.util.HashMap;

import org.springframework.jdbc.support.JdbcUtils;

@SuppressWarnings("serial")
public class CustomMap extends HashMap<String, Object> {
    @Override
    public Object put(String key, Object value) {
        return super.put(JdbcUtils.convertUnderscoreNameToPropertyName(key), value);
    }
}