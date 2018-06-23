/** 
===============================================================================================
1.	버   전 : common.js
2.	작 성 자 : 최인경
3.	작 성 일 : 2016. 09. 06
4.	참   고 : 공통 스크립트
===============================================================================================
*/
/**
 * 텍스트 박스 다음 포커스 이동
 * obj_id : this.id
 * targetobj_id : 이동할 id
 * obj_maxlength : 입력 갯수 ( ex:3일경우 3만큼 length 입력시 다음 포커스 이동 )
 * 
 * ex : fn_NextFocus(this.id, 'nex_id', 4);
 */
function fn_NextFocus(obj_id, targetobj_id, obj_maxlength) {
    if (event.keyCode != 8 && event.keyCode != 9 && event.keyCode != 13 && event.keyCode != 46) {
        var txtStart = document.getElementById(obj_id);
        var txtEnd = document.getElementById(targetobj_id);
        if (txtStart.value.length >= obj_maxlength) {
            txtEnd.focus();
        }
    }
}

function setCookie(cname,cvalue,exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires=" + d.toGMTString();
    document.cookie = cname+"="+cvalue+"; "+expires;
}

function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i=0; i<ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1);
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}
function deleteCookie( cookieName )
{
 var expireDate = new Date();   
 expireDate.setDate( expireDate.getDate() - 1 );
 document.cookie = cookieName + "= " + "; expires=" + expireDate.toGMTString() + "; path=/";
}



function fileCheck( file, size, img_width, img_height ) {
	  if(file == "") {
		  alert("파일을 등록해주세요.");
		  return false;
	  }
	  
		if(file.indexOf(".") >= 0) {
			ext = file.substring(file.lastIndexOf(".")+1);
			ext = ext.toLowerCase();
			if(ext.length != 3 || !(ext == "gif" || ext == "jpg" || ext == "png" || ext == "bmp")) {
				alert("파일은 gif, jpg, png 파일만 등록을 하실수 있습니다.");
				return false;
			}
		} else {
			alert("파일의 확장자가 없습니다.");
			return false;
		}
	
	/*  var width = 0, height = 0; 
      if ( "naturalWidth" in file ){ 
              width = file.naturalWidth; 
              height = file.naturalHeight; 
      } 
      else { 
              var newImg = new Image(); 
              newImg.src = file.src; 
              width = newImg.width; 
              height = newImg.height; 
      }
      
      if(img_width < width || img_hieght < height) {
    	  alert(img_width + "x" + img_height + " 이미지만 등록 가능합니다.");
    	  return false;
      }

    // 사이즈체크
    var maxSize  = size * 1024 * 1024    //30MB
    var fileSize = 0;

	// 브라우저 확인
	var browser=navigator.appName;
	// 익스플로러일 경우
	if (browser=="Microsoft Internet Explorer" || browser == "Netscape") {
		var oas = new ActiveXObject("Scripting.FileSystemObject");
		fileSize = oas.getFile( file.value ).size;
	}
	// 익스플로러가 아닐경우
	else {
		fileSize = file.files[0].size;
	}
    if(fileSize > maxSize) {
        alert("첨부파일 사이즈는 "+size+"MB 이내로 등록 가능합니다.");
        return false;
    }*/
    return true;
}

