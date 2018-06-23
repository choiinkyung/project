$(function(){
	
	
	//datepicker
	if($('.datepicker').size() > 0){
		$( '.datepicker' ).datepicker({
			closeText: '닫기',
			prevText: '이전 달',
			nextText: '다음 달',
			currentText: '오늘',			
			monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
            monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
			dayNames: ['일','월','화','수','목','금','토'],
            dayNamesShort: ['일','월','화','수','목','금','토'],
            dayNamesMin: ['일','월','화','수','목','금','토'],
			dateFormat: 'yy.mm.dd',
			showMonthAfterYear: true,
			changeMonth: true,
      		changeYear: true,
      		yearSuffix: '년',
			showOn: 'button',
			buttonText: '기간조회'
		});
	}
	
	
	//addGnb();
	$('#gnb .btnMenu').click(function(){
		if($(this).parents('#container').hasClass('on')){
			removeGnb();
		}else{
			addGnb();
		}
		return false;
	});
	
	$('#container #gnb ul li > a').click(function(){
		$(this).parent().addClass('on').siblings().removeClass('on');
		removeGnb();
		return false;
	});
	
	function addGnb(){
		$('#gnb .btnMenu').parents('#container').addClass('on');
		$('#container #gnb').stop().animate({'width':'64px'},'fast');
		$('#container #content').stop().animate({'margin-left':'64px'},'fast');
	}
	function removeGnb(){
		$('#gnb .btnMenu').parents('#container').removeClass('on');
		$('#container #gnb').stop().animate({'width':'240px'},'fast');
		$('#container #content').stop().animate({'margin-left':'240px'},'fast');
	}
	
});





















