// 탭메뉴
$(function(){
	$(".tabcons").hide();
	$(".tabcons:nth-child(1)").show();
	$(".tabmenu li:nth-child(1)").addClass("sel");
	$(".tabmenu li").click(function() {
		var list = $(this).index();
		$(".tabmenu li").removeClass("sel");
		$(this).addClass("sel");
		$(".tabcons").hide();
		$(".tabcons").eq(list).show();
	});
})

function setComma(n) {
	n += '';
	var x = n.split('.');
	var x1 = x[0].replace(/,/g, '');
	var x2 = x.length > 1 ? '.' + x[1] : '';
	x1 = x1.replace(/^(0+)(\d+)/, '$2');
	var rgx = /(\d+)(\d{3})/;
	while (rgx.test(x1)) {
		x1 = x1.replace(rgx, '$1' + ',' + '$2');
	}
	return x1 + x2;
}

function copyToClipboard(val) {
	const t = document.createElement("textarea");
	document.body.appendChild(t);
	t.value = val;
	t.select();
	document.execCommand('copy');
	document.body.removeChild(t);
}

function copyShortUrl(url) {
	copyToClipboard(url);
	alert('공유URL을 복사하였습니다.');
}

function zzim(id){
	let zzim = $("#bidding_"+ id).hasClass("sel");
	console.log(zzim);


	if (zzim) {
		$.ajax({
			url: "/api/zzim/product/del/"+ id,
			type: 'DELETE',
			success: function(result) {
				$("#bidding_"+ id).removeClass("sel");
				$("#bidding2_"+ id).removeClass("sel");
				$("#bidding3_"+ id).removeClass("sel");
				$("#bidding4_"+ id).removeClass("sel");
			}
		});
	}
	else {
		$.ajax({
			url: "/api/zzim/product/add/"+ id,
			type: 'POST',
			success: function(result) {
				$("#bidding_"+ id).addClass("sel");
				$("#bidding2_"+ id).addClass("sel");
				$("#bidding3_"+ id).addClass("sel");
				$("#bidding4_"+ id).addClass("sel");
			}
		});
	}

	event.preventDefault();
}

function zzim_desc(id){
	let zzim = $("#bidding_"+ id).attr('src')
	console.log(zzim);


	if (zzim === '../img/i_zzim_on.png') {
		$.ajax({
			url: "/api/zzim/product/del/"+ id,
			type: 'DELETE',
			success: function(result) {
				$("#bidding_"+ id).attr('src', '../img/i_zzim_off2.png');
			}
		});
	}
	else {
		$.ajax({
			url: "/api/zzim/product/add/"+ id,
			type: 'POST',
			success: function(result) {
				$("#bidding_"+ id).attr('src', '../img/i_zzim_on.png');
			}
		});
	}

	event.preventDefault();
}


function zzim_influence(id){
	let zzim = $("#zzim_influence_"+ id).hasClass("sel");

	if (zzim) {
		$.ajax({
			url: "/api/zzim/influner/del/"+ id,
			type: 'DELETE',
			success: function(result) {
				$("#zzim_influence_"+ id).removeClass("sel");
			}
		});
	}
	else {
		$.ajax({
			url: "/api/zzim/influner/add/"+ id,
			type: 'POST',
			success: function(result) {
				$("#zzim_influence_"+ id).addClass("sel");
			}
		});
	}

	event.preventDefault();
}

function adultAuth(){
	// $("#iframeAdult").attr('src', '/test/test');
	location.href='/test/test';
	// window.open('/test/test', '_adult','width=100%, height=100%');
}

function biddingProcess(itemNo){
	// $("#iframePg").attr('src', '/test/payOrder?itemNo='+ itemNo);
	location.href= '/test/payOrder?itemNo='+ itemNo;
}