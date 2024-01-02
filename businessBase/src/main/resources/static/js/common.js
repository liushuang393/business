var submitFlg = false;
$(document).ready(function() {
	// コミットする
	$("#submitForm").submit(function() {
		if (submitFlg == true) {
			return false;
		}
		$("#modal-normal").click();

		$("[href]").each(function() {
			// $(this).attr("disabled",true);
		});

		$(":button").each(function() {
			// $(this).attr("disabled",true);
		});
		submitFlg = true;
		return true;
	});

});

function submitForm(url) {
    $("#submitForm").attr("action",
    		contextPath + url)
            .submit();
}

function selectOrClearAllCheckbox(obj) {
	var checkStatus = $(obj).attr("checked");
	if (checkStatus == "checked") {
		$("input[type='checkbox']").attr("checked", true);
	} else {
		$("input[type='checkbox']").attr("checked", false);
	}
}
document.onkeydown = check;
function check(e) {
    var code;
    if (!e) var e = window.event;
    if (e.keyCode) code = e.keyCode;
    else if (e.which) code = e.which;
if (((event.keyCode == 8) &&                                                    //BackSpace
         ((event.srcElement.type != "text" &&
         event.srcElement.type != "textarea" &&
         event.srcElement.type != "password") ||
         event.srcElement.readOnly == true)) ||
        ((event.ctrlKey) && ((event.keyCode == 78) || (event.keyCode == 82)) ) ||    //CtrlN,CtrlR
        (event.keyCode == 116) ) {                                                   //F5
        event.keyCode = 0;
        event.returnValue = false;
    }
return true;
}