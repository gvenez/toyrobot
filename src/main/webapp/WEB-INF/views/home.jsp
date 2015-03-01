<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Toy Robor</title>
<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
</head>
<style>
div {
	background: #ccc;
	height: 100px;
	width: 100px;
	position: absolute;
	top: 0;
	left: 0;
}

p.msg {
	height: 45px;
}
</style>
<body>
	<p align="right">
		Enter the name of the robot:<input class="robotName" type="text"
			value="walle">
	</p>
	<p class="msg"></p>
	<div id="robot"></div>
	<h1>Tap the arrow keys to move</h1>
	<h1>Click anywhere to place the robot</h1>

	<script>
		var baseurl = "http://localhost:8080/toyrobot/"
		var curLeft = 0;
		var curTop = 0;
		var curName;

		$(document).click(function(e) {
			// alert("Setting robot to " + e.pageX + ' , ' + e.pageY + " from position " + $('div').position().left);

			var position = $('div').position();
			var finalLeft = (e.pageX - position.left)
			var finalTop = (e.pageY - position.top)
			$('div').stop().animate({
				left : '+=' + finalLeft,
				top : '+=' + finalTop
			});
			//send params to backend

			curLeft = e.pageX
			curTop = e.pageY

			createRobot(curName);
		});

		$(document).keydown(function(e) {
			
			switch (e.which) {
			case 37:
				//left  key
				moveRobot(curName, "left", -10);
				break;
			case 38:
				moveRobot(curName, "top", -10);
				//up  key
				break;
			case 39:
				moveRobot(curName, "left", +10);
				
				//right  key
				break;
			case 40:
				
				moveRobot(curName, "top", +10);
				//bottom  key
				break;
			}

			//send the position to backend here

			//get the result back and post it to GUI

			//move the div here
			
		})

		$(".robotName").change(
				function() {

					var newName = $(".robotName").val();
					createRobot(newName);
				

				});

		function log(msg) {
			$(".msg").text(msg)
		}
		
		
		function createRobot(newName)
		{
			$.ajax({
				url : baseurl + newName + "/" + curLeft + "/" + curTop,
				type : "PUT"
			}).then(
					function(data) {
						log("created robot with name " + data.name
								+ " at co-ords [" + data.position.left
								+ "/" + data.position.top + "] : Server message:" + data.errorDescription);
						console.log(data);
						
						
						curName=data.name;
					});
		}
		
		function moveRobot(newName, cmd,amt)
		{
			var offsetLeft=0;
			var offsetTop=0;
			$.ajax({
				url : baseurl + newName + "/position/" + cmd + "/" + amt,
				type : "PUT"
			}).then(
					function(data) {
						log("Moved robot with name " + data.name
								+ " at co-ords [" + data.position.left
								+ "/" + data.position.top + "] : Server message:" + data.errorDescription);
						console.log(data);
						var position = $('div').position();
						offsetLeft=data.position.left-position.left;
						offsetTop=data.position.top-position.top;
						
						$('div').stop().animate({
							left : '+=' + offsetLeft,
							top : '+=' + offsetTop
						});
						
					});
			
			
			
			
		}
		
		createRobot($(".robotName").val());
	</script>


</body>
</html>
