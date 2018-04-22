<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="css/styles.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
</head>
<body>	
	<nav class="navbar-custom">
   		<a class="navbar-brand" href="#">		<img src="logo.jpg" class="d-inline-block align-top" height="30" width="30">	&nbsp;&nbsp;	Glen's Media Library</a>
   	</nav>
	<div class="container">
	<br>
	<br>
	<form action="/picupload" method="POST" enctype="multipart/form-data">
	<div class="row">	
			<div class="col-9"><h2><b>Glen Sequeira</b></h2>
				
				<h5> Upload Profile Image:
					<input type="file"  name="file"/> 
					
			</div>
		</div>
		
		<div class="row">	
			<div class="col-9">
				
				<h5> Say something about yourself:</h5>
					<input type="text"  name="description"/>
					<input type="hidden" name="user"/> 
					<input type="submit" />				
			</div>
		</div>
	</form>	
	</div>

	<!-- jQuery first, then Tether, then Bootstrap JS. -->
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
</body>
</html>