package egovframework.MNG.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * <p>
 * 클레스의 멤버들을 접근 가능한 상태로 설정 후 맴버 반환 유틸리티 클레스
 * </p>
 *
 * @author
 * @date 2009. 10. 7. $Id: $
 */
public class ReflectionUtil {

    private static final Logger logger = Logger.getLogger(ReflectionUtil.class);

    /**
     * 대상 클레스와 모든 부모클레스의 (모든 클레스의 최상위 클레스인 <code>Object.class</code> 제외)
     * <code>Field</code>를 모두 가져온다.
     *
     * @param clazz
     *            <code>Field</code>를 추출할 대상 <code>Class</code>
     * @return Field[] 배열 반환
     */
    public static Field[] getFields(Class clazz) {
        List al = new ArrayList();
        int total = 0;

        do {
            Field[] fild = clazz.getDeclaredFields();
            total += fild.length;
            al.add(fild);
            clazz = clazz.getSuperclass();
        }
        while (clazz != Object.class);

        Field[] allField = new Field[total];
        int position = 0;
        int alSize = al.size();
        for (int i = 0; i < alSize; i++) {
            Field[] thisField = (Field[]) al.get(i);
            System
                    .arraycopy(thisField, 0, allField, position,
                            thisField.length);
            position += thisField.length;
        }

        Field.setAccessible(allField, true);

        return allField;
    }

    /**
     * 대상 클레스와 모든 부모클레스의(모든 클레스의 최상위 클레스인 <code>Object.class</code> 제외)
     * <code>Field</code>를 가져온다.
     *
     * @param clazz
     * @param fieldName
     * @return Field 반환
     * @throws NoSuchFieldException
     */
    public static Field getField(Class clazz, String fieldName) {
        Field field = null;
        do {
            try {
                field = clazz.getDeclaredField(fieldName);
            }
            catch (NoSuchFieldException e) {
                // 아무것도 하지 않음.
            }
        }
        while (field == null && (clazz = clazz.getSuperclass()) != Object.class);

        if (field == null) {
            logger.error(fieldName + " 이름의  Field 객체를 찾을 수 없습니다.");
        }
        else {
            try {
                field.setAccessible(true);
            }
            catch (SecurityException e) {
                logger.error("접근 가능한 필드로 변경할 수 없습니다.", e);
            }
        }
        return field;
    }
}
