$.ajax({
	url : '/param/getBackInfo.do',
	type : 'POST',
	dataType : 'json',
	success : function(data) {
		if (data.code == 100) {
			$('#itemid').attr("value", data.result.id);
			$('#name').attr("value", data.result.bankUserName);
			$('#cardId').attr("value", data.result.bankCardId);
			$('#backName').attr("value", data.result.bankName);
			$('#remark').attr("value", data.result.remark);
		} else {
			alert("获取失败");
		}
	}
});
$.ajax({
	url : '/userset/getUserSet.do',
	type : 'POST',
	dataType : 'json',
	success : function(data) {
		if (data.code == 100) {
			$('#limitloginid').attr("value", data.result.id);
			$('#limittime').attr("value", data.result.opertion_time);
			$('#time').attr("value", data.result.opertion_number);
		} else {
			alert("获取失败");
		}
	}
});
function savebackInfo() {
	var id = $('#itemid').val();
	var name = $('#name').val();
	var cardId = $('#cardId').val();
	var backName = $('#backName').val();
	var remark = $('#remark').val();
	$.ajax({
		url : '/param/modifyBankInfo.do',
		type : 'POST',
		dataType : 'json',
		data : {
			id : id,
			name : name,
			cardId : cardId,
			backName : backName,
			remark : remark
		},
		success : function(data) {
			if (data.code == 100) {
				alert('更新成功');
			} else {
				alert('更新失败');
			}
			window.location.href = '/param/backInfo.do';
		}
	});
}

function saveLoginSet() {
	var id = $('#limitloginid').val();
	var limittime = $('#limittime').val();
	var time = $('#time').val();
	var username = $('#username').text();
	var ip = $('#ip').text();
	$.ajax({
		url : '/userset/modifyuserset.do',
		type : 'POST',
		dataType : 'json',
		data : {
			id : id,
			limittime : limittime,
			time : time,
			username : username,
			ip : ip,
		},
		success : function(data) {
			if (data.code == 100) {
				alert('更新成功');
			} else {
				alert('更新失败');
			}
			window.location.href = '/userset/userset.do';
		}
	});
}
