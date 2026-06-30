<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Product Updated</title>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webcontent/css/update-success.css">

<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600&family=Playfair+Display:wght@600;700&display=swap"
	rel="stylesheet">

</head>

<body>

	<div class="success-container">

		<div class="success-card">

			<div class="success-icon">✓</div>

			<h1>Product Updated Successfully</h1>

			<p>Your furniture information has been updated successfully.</p>

			<div class="btn-group">

				<a href="<%=request.getContextPath()%>/viewProducts"
					class="primary-btn"> View Products </a> <a href="dashboard.jsp"
					class="secondary-btn"> Dashboard </a>

			</div>

		</div>

	</div>

</body>

</html>