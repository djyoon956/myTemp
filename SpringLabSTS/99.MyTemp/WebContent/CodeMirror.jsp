<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.48.4/codemirror.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.48.4/mode/javascript/javascript.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.48.4/mode/sql/sql.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.48.4/mode/xml/xml.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.48.4/addon/edit/closetag.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.48.4/addon/hint/show-hint.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.48.4/addon/hint/css-hint.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.48.4/mode/htmlembedded/htmlembedded.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.48.4/mode/htmlmixed/htmlmixed.min.js"></script>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.48.4/codemirror.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.48.4/theme/eclipse.min.css" />
<script type="text/javascript">
var editor;
$(function(){
	var type = $("#codeType option:selected").val();

	editor = CodeMirror.fromTextArea($('#editor')[0],{
		mode : type,
		theme : "eclipse",
		lineNumbers : true,
		autoCloseTags : true
	});
	editor.setSize("700", "700");

	$("#codeType").change(function(){
		type = $(this).val();
		console.log(type);
		editor.setOption("mode", type);
	});
})
</script>
</head>
<body>
	<select name="codeType" id="codeType">
		<option value="text/x-java">java</option>
		<option value="javascript">javascript</option>
		<option value="css">css</option>
		<option value="xml">xml</option>
		<option value="sql">sql</option>
		<option value="text/html">html</option>
	</select>

	<textarea id="editor">test</textarea>
	<script>
			
		</script>
</body>
</html>