<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Product Added Successfully</title>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webcontent/css/product-success.css">

<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600&family=Playfair+Display:wght@600;700&display=swap"
	rel="stylesheet">

</head>

<body>

	<div class="success-container">

		<div class="success-card">

			<div class="checkmark-wrapper">

				<div class="checkmark">✓</div>

			</div>

			<h1>Product Added Successfully</h1>

			<p>Your furniture product has been added to the WoodCraft
				catalog.</p>

			<div class="button-group">

				<a href="addProduct.jsp" class="primary-btn"> Add Another
					Product </a> <a href="dashboard.jsp" class="secondary-btn">

					Dashboard </a> <a href="viewProducts.jsp" class="secondary-btn">

					View Products </a>

			</div>

		</div>

	</div>

</body>

</html>