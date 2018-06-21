package egovframework.MNG.util.sms;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Socket;

public class SMSComponent {
	// 일반적으로 사용하게될 변수입니다.
	Socket socket;
	DataOutputStream out;
	DataInputStream in;

	public void connect() throws Exception {
		socket	= new Socket(SMSConfig.SOCKET_HOST, SMSConfig.SOCKET_PORT);
		socket.setSoTimeout (5000);

		out		= new DataOutputStream(socket.getOutputStream());
		in		= new DataInputStream(socket.getInputStream());
	}

	public void disconnect() throws Exception {
		in.close();
		out.close();
		socket.close();
	}

	// 실제 발송하는 과정입니다.
	public String SendMsg(String[] strDestList, String strCallBack, String strSubject, String strDate, String strData, int nCount) throws IOException
	{

		int i = 0;
		String result = "", msgid = "";
		
		strData = strData.replaceAll("\r\n", "\n"); // 엔터값 1자리로 변경.
		strData = subString(strData, 0, 2000);
		
		if(byteLength(strData) <= 90) {
			strSubject = ""; // SMS 발송으로 판된되면 제목을 제거한다.
		} else {
			strSubject = strSubject.replaceAll("\r\n", " "); // 엔터값 공백으로 변경.
			strSubject = subString(strSubject, 0, 30);
		}
		
		// 발송내역 유니코드로 변환.
		String jsonB = "\"msg\":\""+strData.trim()+"\","
				+ "\"title\":\""+strSubject.trim()+"\"}";
		jsonB = encode(jsonB);

		for (int k=0;k<nCount;k++) {
			// 나머지 JSON 코드를 생성한다.
			String json = "{"
					+ "\"key\":\""+SMSConfig.TOKEN+"\","
					+ "\"tel\":\""+strDestList[k]+"\","
					+ "\"cb\":\""+strCallBack+"\","
					+ "\"date\":\""+strDate+"\"," + jsonB;

			// 자리수를 계산한다.
			String lenJson = String.format("%04d", byteLength(json));

			// 소켓을 보낸다.
			out.writeBytes("06"); // Msg Type
			out.writeBytes(lenJson); // Msg Len
			out.writeBytes(json); // json Text
			out.flush();

			boolean inputExist = true;

			do {
				try{									
					byte buffer[] = new byte[2];
					for(i=0 ; i < 2; i++)buffer[i] = in.readByte();
					String msgType = new String(buffer);
					byte temp[] = new byte[4];
					for(i = 0; i < 4 ; i++) temp[i] = in.readByte();
					String sLen = new String(temp);
					sLen = sLen.trim();

					int nLen = Integer.valueOf(sLen).intValue();
					buffer = new byte[nLen];

					if(msgType.equals("02")) {
						inputExist = false;

						for(i=0 ; i < nLen; i++)buffer[i] = in.readByte();
						result = new String(buffer);

						//result 의 구조는 성공/실패 2 char, 전화번호 12 char , msgid = 10 char
						if(result.substring(0,2).equals("00"))
							msgid += "발송완료: ";
						else 
							msgid += "발송실패: ";
						msgid += result + "\n";
					}
				}catch (EOFException e){
					inputExist = false;

				}catch (InterruptedIOException e){

				}
			}while(inputExist);
		}

		return msgid.trim();
	}

	
	/**
	 * 원하는 문자열의 길이를 바이트 단위로 구한다.
	 *
	 * @param	String	text	원하는 문자열입니다.
	 * @return	int				확인된 길이를 받습니다.
	 */
	public int byteLength(String text) {
    	byte[] temp = text.getBytes();
    	int len = temp.length;
		return len;
    }
	
	/**
	 * 받은 문자열의 한글을 유니코드로 치환합니다.
	 *
	 * @param	String	text	원하는 문자열입니다.
	 * @return	String			변경된 유니코드 문자열입니다.
	 */
	public String encode(String text) {
		StringBuffer str = new StringBuffer();
		  
		for (int i = 0; i < text.length(); i++) {
			int ch = text.charAt(i);
			if( 12593 <= ch && ch >= 12622 ||
				12623 <= ch && ch >= 12641 ||
				44032 <= ch && ch >= 55203) {
				str.append("\\u");
				str.append(Integer.toHexString((int) text.charAt(i)));
			} else {
				str.append(text.charAt(i));
			}
		}
  
		return str.toString();
	}
	
	/**
	 * 받은 문자열을 바이트 단위로 자릅니다.
	 *
	 * @param	String	text		원하는 문자열입니다.
	 * 			int		startIndex	시작시점입니다.
	 * 			int		length		자를 길이입니다.
	 * @return	String				잘린 문자열입니다.
	 */
	public String subString(String text, int startIndex, int length) {
		byte[] bytes = text.getBytes();
		if(bytes.length > startIndex + length) {
			text = new String(text.getBytes(), startIndex, length); 
		}
		return text;
	} 	
}