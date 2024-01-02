function rightMain(url){
	$('#rightMain').attr('src', url);
}
function getNextDay(dd, dadd) {
	var a = new Date(dd);
	a = a.valueOf();
	a = a + dadd * 24 * 60 * 60 * 1000;
	a = new Date(a);
	var m = a.getMonth() + 1;
	if (m.toString().length == 1) {
		m = '0' + m;
	}
	var d = a.getDate();
	if (d.toString().length == 1) {
		d = '0' + d;
	}
	return a.getFullYear() + "-" + m + "-" + d;
}