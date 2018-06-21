package egovframework.MNG.util;


/**
 * <p>
 * 유효성 검증 유틸리티 클레스
 * </p>
 *
 * @author
 * @date 2009. 10. 7. $Id: $
 */
public class AssertUtil {

	/**
	 * 대상 객체가 <code>null</code>이 아니면 오류
	 *
	 * @param obj
	 *            null 체크 대상 객체
	 * @param msg
	 *            오류 메시지
	 * @throws Exception 
	 * 
	 *             대상이 <code>null</code>이 아닌 경우
	 */
	public static void isNull(Object obj, String msg) throws Exception {
		if (obj != null)
			throw new Exception(msg);
	}

	/**
	 * 대상 객체가 <code>null</code>이 아니면 오류
	 *
	 * @param obj
	 *            null 체크 대상 객체
	 * @throws Exception 
	 * 
	 *             대상이 <code>null</code>이 아닌 경우
	 */
	public static void isNull(Object obj) throws Exception {
		isNull(obj, "[오류] 대상 객체는 null이 아닙니다. ");
	}

	/**
	 * 객체 배열에 <code>null</code>이 아닌 항목이 있으면 오류
	 *
	 * @param objs
	 * @throws Exception 
	 * 
	 *             대상이 <code>null</code>이 아닌 항목이 있는 경우
	 */
	public static void isAllNull(Object[] objs) throws Exception {
		isNotNull(objs, "[오류] null 체크 대상 배열 객체가 null입니다.");
		int objLength = objs.length;
		for (int i = 0; i < objLength; i++) {
			isNull(objs[i], "[오류] null이 아닌 항목이 포함되어 있습니다.");
		}
	}

	/**
	 * 대상 객체가 <code>null</code>이면 오류
	 *
	 * @param obj
	 *            null 체크 대상 객체
	 * @param msg
	 *            오류 메시지
	 * @throws Exception 
	 * 
	 *             대상 객체가 <code>null</code>인 경우
	 */
	public static void isNotNull(Object obj, String msg) throws Exception {
		if (obj == null)
			throw new Exception(msg);
	}

	/**
	 * 대상 객체가 <code>null</code>이면 오류
	 *
	 * @param obj
	 *            null 체크 대상 객체
	 * @throws Exception 
	 * 
	 *             대상 객체가 <code>null</code>인 경우
	 */
	public static void isNotNull(Object obj) throws Exception {
		isNotNull(obj, "[오류] 대상 객체는 null 입니다.");
	}

	/**
	 * 객체 배열이 <code>null</code>인 항목이 있으면 오류
	 *
	 * @param objs
	 * @throws Exception 
	 * 
	 *             대상이 <code>null</code>인 항목이 있는 경우
	 */
	public static void isAllNotNull(Object[] objs, String msg) throws Exception {
		isNotNull(objs, "[오류] null 체크 대상 배열 객체가 null입니다.");
		int objLength = objs.length;
		for (int i = 0; i < objLength; i++) {
			isNotNull(objs[i], "[오류] null인 항목이 포함되어 있습니다.");
		}
	}

	/**
	 * 대상 객체가 <code>null</code> 또는, objectType과 유형이 다르면 오류
	 *
	 * @param objectType
	 *            obj 비교 유형
	 * @param obj
	 *            비교할 <code>Object</code>
	 * @param msg
	 *            오류 메시지
	 * @throws Exception 
	 *  대상 객체가 <code>null</code>이거나 유형이 다른 경우
	 */
	public static void isTypeEqual(Class objectType, Object obj, String msg) throws Exception {
		isNotNull(obj, msg);
		if (!objectType.isInstance(obj))
			throw new Exception(msg);
	}

	/**
	 * 대상 객체가 <code>null</code>이 아니고, objectType과 유형이 다르면 오류
	 *
	 * @param objectType
	 *            obj 비교 유형
	 * @param obj
	 *            비교할 <code>Object</code>
	 * @throws Exception 
	 * 
	 *             대상 객체가 유형이 다른 경우
	 */
	public static void isTypeEqual(Class objectType, Object obj) throws Exception {
		isTypeEqual(objectType, obj, "[오류] "
				+ (obj != null ? obj.getClass().getName() : "null") + "은(는)"
				+ objectType + " 유형이 아닙니다.");
	}

	/**
	 * 대상 문자열이 숫자만(<code>Integer</code>)으로 되어 있지 않으면 오류
	 *
	 * @param str
	 *            <code>Integer</code>로 변환 체크 대상 문자열
	 * @param msg
	 *            오류 메시지
	 * @throws Exception 
	 *             <code>Integer.parseInt() </code>이 불가능 할 경우
	 */
	public static void isNumber(String str, String msg) throws Exception {
		isNotNull(str);
		try {
			Integer.parseInt(str);
		} catch (Exception e) {
			throw new Exception(msg);
		}
	}

	/**
	 * 대상 문자열이 숫자만(<code>Integer</code>)으로 되어 있지 않으면 오류
	 *
	 * @param str
	 *            <code>Integer</code>로 변환 체크 대상 문자열
	 * @throws Exception 
	 *             <code>Integer.parseInt() </code>이 불가능 할 경우
	 */
	public static void isNumber(String str) throws Exception {
		isNumber(str, "[오류] '" + str + "' 문자열은 숫자로 변환이 불가능합니다.");
	}

	/**
	 * 문자열이 <code>null</code> 이거나, String.trim() 후의 문자열이 비어 있으면 오류
	 *
	 * @param str
	 *            체크대상 문자열
	 * @param msg
	 *            오류 메시지
	 * @throws Exception 
	 */
	public static void isNotEmpty(String str, String msg) throws Exception {
		isNotNull(str, msg);
		if (str == null || str.trim().length() <= 0)
			throw new Exception(msg);
	}

	/**
	 * 문자열이 <code>null</code> 이거나 빈 문자열이면 오류
	 *
	 * @param str
	 *            체크대상 문자열
	 * @throws Exception 
	 */
	public static void isNotEmpty(String str) throws Exception {
		isNotEmpty(str, "[오류] null 또는 값이 없는 빈 문자열입니다.");
	}

	/**
	 * 두 문자 배열의 길이가 동일하지 않으면 오류
	 *
	 * @param objArr1
	 *            비교문자 배열 1
	 * @param objArr2
	 *            비교문자 배열 2
	 * @param msg
	 *            오류 메시지
	 * @throws Exception 
	 */
	public static void isEqualArrLength(String[] objArr1, String[] objArr2,
			String msg) throws Exception {
		isNotNull(objArr1, "[오류] 비교 대상 문자 배열 1이 null입니다.");
		isNotNull(objArr2, "[오류] 비교 대상 문자 배열 2이 null입니다.");
		if (objArr1.length != objArr2.length) {
			throw new Exception(msg);
		}
	}

	/**
	 * 두 문자 배열의 길이가 동일하지 않으면 오류
	 *
	 * @param objArr1
	 *            비교문자 배열 1
	 * @param objArr2
	 *            비교문자 배열 2
	 * @throws Exception 
	 */
	public static void isEqualArrLength(String[] objArr1, String[] objArr2) throws Exception {
		isEqualArrLength(objArr1, objArr2, "[오류] 배열의 길이가 다릅니다.");
	}

}