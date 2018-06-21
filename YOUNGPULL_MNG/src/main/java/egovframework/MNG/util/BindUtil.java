package egovframework.MNG.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * <p>
 * Java Class 필드 멤버에 값을 바인딩 유틸리티 클레스
 * </p>
 *
 * @author
 * @date 2009. 10. 7. $Id: $
 */
public class BindUtil {

	private static final Logger logger = Logger.getLogger(BindUtil.class);
	private static final HashMap primitiveType = new HashMap(7);
	static {
		/*
		 * Reflection 특성상 Object Type을 취하기 때문에 기본형의 경우는 레퍼 클레스를 사용하여 레핑하여야 한다.
		 * 아래 Class 배열은 기본형에 대한 정의이다.
		 */
		Class[][] pClasses = { { boolean.class, Boolean.class },
				{ byte.class, Byte.class }, { int.class, Integer.class },
				{ float.class, Float.class }, { double.class, Double.class },
				{ short.class, Short.class }, { long.class, Long.class } };

		// 생성자 파라미터
		Class[] args = { String.class };

		for (int i = 0; i < pClasses.length; i++) {
			try {
				primitiveType.put(pClasses[i][0],
						pClasses[i][1].getDeclaredConstructor(args));
			} catch (NoSuchMethodException e) { /* ^-^ */
			}
		}

	}

	/**
	 * bindObj 객체의 fieldName 필드 변수를 fieldValue 값으로 설정한다.
	 *
	 * @param bindObj
	 *            대상 <code>Object</code>
	 * @param fieldName
	 *            대상 <code>Object</code>의 <code>Field</code> 명칭
	 * @param fieldValue
	 *            대상 <code>Field</code>에 설정할 값
	 * @throws Exception 
	 */
	public static void bind(Object bindObj, String fieldName, Object fieldValue) throws Exception {
		AssertUtil.isNotNull(bindObj, "[오류] Bindding 대상 Object가 null입니다.");
		AssertUtil.isNotNull(fieldValue, "[오류] Bindding 값이 null입니다.");
		AssertUtil.isNotNull(fieldName,
				"[오류] Bindding 대상 Object의 Field 명칭이 null입니다.");

		try {
			Field field = ReflectionUtil
					.getField(bindObj.getClass(), fieldName);
			if ((field.getModifiers() & 16) == 0) {
				Class fieldType = field.getType();
				if (fieldType.isPrimitive()
						&& !(fieldValue instanceof String[])) {
					field.set(bindObj, wrapping(fieldType, fieldValue));
				} else {
					field.set(bindObj, fieldValue);
				}
			}
		} catch (IllegalArgumentException iae) {
			logger.error("Argument를 다시 확인하십시오.", iae);
		} catch (IllegalAccessException iae) {
			logger.error("접근권한이 없는 Field입니다. final 필드는 아닌지 확인하십시오.", iae);
		}
	}

	/**
	 * bindObj 객체의 필드명과 값을 Map으로 받아서 Key를 필드명과 대입하고, Value를 Key 필드의 값으로 설정한다.
	 *
	 * @param bindObj
	 *            대상 <code>Object</code>
	 * @param keyValue
	 *            <code>Field</code>명과 설정 값을 담은 <code>Map</code>
	 * @throws Exception 
	 */
	public static void bind(Object bindObj, Map keyValue) throws Exception {
		AssertUtil.isNotNull(bindObj, "[오류] Bindding 대상 Object가 null입니다.");
		AssertUtil.isNotNull(keyValue, "[오류] Bindding 값이 null입니다.");

		Field[] fields = ReflectionUtil.getFields(bindObj.getClass());
		// 기본형은 String으로 한다.
		int fCnt = fields.length;
		String fieldName = "";

		Class fieldType = String.class;
		Object setObj = null;
		for (int i = 0; i < fCnt; i++) {
			try {
				if ((fields[i].getModifiers() & 16) == 0) {
					fieldType = fields[i].getType();
					fieldName = fields[i].getName();
					setObj = keyValue.get(fieldName);
					if (null != setObj) {
						if (fieldType.isPrimitive()
								&& !(setObj instanceof String[])) {
							fields[i].set(bindObj, wrapping(fieldType, setObj));
						} else {
							fields[i].set(bindObj, setObj);
						}
					}
				}
			} catch (IllegalArgumentException iae) {
				if (logger.isDebugEnabled()) {
					logger.debug("Argument를 다시 확인하십시오. 맴버번수 : " + fieldName
							+ ", 설정 값 : " + setObj.toString(), iae);
				}
			} catch (IllegalAccessException iae) {
				if (logger.isDebugEnabled()) {
					logger.debug("접근권한이 없는 Field입니다. final 필드는 아닌지 확인하십시오.",
							iae);
				}
			}
		}
	}

	/**
	 * fieldType이 기본형이라면 레핑하여 Object Type으로 반환하고, <code>Object</code> 형이라면
	 * fieldType를 그대로 반환한다.
	 *
	 * @param fieldType
	 *            유형을 확인하기 위한 <code>Class</code>
	 * @param fieldValue
	 *            <code>Field</code>에 설정할 <code>Object</code> 유형의 값
	 * @return
	 */
	private static Object wrapping(Class fieldType, final Object fieldValue) {
		Object wrappedObj = fieldValue;

		if (fieldType.isPrimitive() && fieldValue != null) {
			try {
				if (!fieldType.equals(char.class)) {
					wrappedObj = ((Constructor) primitiveType.get(fieldType))
							.newInstance(new Object[] { fieldValue });
				} else {
					wrappedObj = new Character(((String) fieldValue).charAt(0));
				}
			} catch (Exception e) {
				if (logger.isDebugEnabled()) {
					logger.debug("레퍼 클레스  생성이 실패되었습니다. 대상 필드맴버 타입 : "
							+ fieldType.getName() + ", 설정할 값 : " + fieldValue
							+ " ", e);
				}
			}
		}

		return wrappedObj;
	}

}
