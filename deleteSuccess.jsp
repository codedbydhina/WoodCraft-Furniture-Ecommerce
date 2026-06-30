<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Product Deleted</title>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webcontent/css/delete-success.css">

<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600&family=Playfair+Display:wght@600;700&display=swap"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">

</head>

<body>

	<div class="success-container">

		<div class="success-card">

			<div class="success-icon">
				<i class="fa-solid fa-circle-check"></i>
			</div>

			<h1>Product Deleted Successfully</h1>

			<p>The selected product has been removed from the WoodCraft
				catalog.</p>

			<div class="btn-group">

				<a href="<%=request.getContextPath()%>/viewProducts"
					class="primary-btn"> View Products </a> <a href="dashboard.jsp"
					class="secondary-btn"> Dashboard </a>

			</div>

		</div>

	</div>

</body>

</html>