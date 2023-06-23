<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
 <HEAD>
  <TITLE> New Document </TITLE>
  <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
 </HEAD>

 <BODY>
	<textarea name="content" id="editor"></textarea>

	<script>
	 $(function() {
		    $.ajax("http://192.168.0.220:8080/api/wordCloud/v1.0/words/%ED%86%B5%EA%B3%84?channels=10,20,30&startDate=20200624&endDate=20200724&rowCount=200&useExcludeSearchKeyword=N&useExcludeKeyword=Y")
		        .done(function(msg) {
		          alert(msg);
		        })
		        .fail(function() {
		          alert("fail");
		        });
		  });


	</script>
</BODY>
</HTML>
