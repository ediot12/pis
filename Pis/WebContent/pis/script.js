function writeSave() {
	if (document.writeform.writer.value == "") {
		alert("작성자를 입력하십시요.");
		document.writeform.writer.focus();
		return false;
	}
	if (document.writeform.subject.value == "") {
		alert("제목을 입력하십시요.");
		document.writeform.subject.focus();
		return false;
	}

	if (document.writeform.content.value == "") {
		alert("내용을 입력하십시요.");
		document.writeform.content.focus();
		return false;
	}
	
}