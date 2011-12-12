function edit(id) {
	comment = $("#comment-" + id);
	editField = $("#edit-" + id);
	comment.hide();
	editField.show();
	editField.focus();
	return true;
}

function save(id) {
	comment = $("#comment-" + id);
	editField = $("#edit-" + id);
	var entry = new Object();
	entry.id = id;
	entry.comment = comment.val();
	hour = JSON.stringify($("#form_" + id).serializeObject());
	$.post("update", hour, function(data) {
		$("#commentVal-" + id).val(data.comment);
		alert('Done');
	},"json");
	comment.show();
	editField.hide();
	return true;
}

function cancel(id) {
	comment = $("#comment-" + id);
	editField = $("#edit-" + id);
	comment.show();
	editField.hide();
}

$.fn.serializeObject = function() {
	var o = {};
	var a = this.serializeArray();
	$.each(a, function() {
		if (o[this.name] !== undefined) {
			if (!o[this.name].push) {
				o[this.name] = [ o[this.name] ];
			}
			o[this.name].push(this.value || '');
		} else {
			o[this.name] = this.value || '';
		}
	});
	return o;
};
