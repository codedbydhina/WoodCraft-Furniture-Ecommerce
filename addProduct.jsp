<%@ page import="com.woodcraft.model.User"%>

<%
User user = (User) session.getAttribute("loggedUser");

if (user == null) {

	response.sendRedirect("../login.jsp");
	return;
}
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Add Product</title>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webcontent/css/admin-layout.css">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webcontent/css/add-product.css">

<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600&family=Playfair+Display:wght@500;600;700&display=swap"
	rel="stylesheet">

</head>

<body>

	<div class="admin-container">

		<aside class="sidebar">

			<h2 class="brand">WoodCraft</h2>

			<ul class="menu">

				<li><a href="dashboard.jsp"> Dashboard </a></li>

				<li><a href="addProduct.jsp" class="active"> Products </a></li>

				

			</ul>

		</aside>

		<main class="main">

			<h1 class="page-title">Add New Product</h1>

			<p class="page-subtitle">Create premium furniture listings.</p>

			<div class="product-container">

				<!-- LEFT PREVIEW -->

				<div class="preview-card">

					<div class="preview-image">Product Image Preview</div>

					<h3 class="preview-title">Premium Wooden Chair</h3>

					<p class="preview-price">&#8377;4,999</p>

				</div>

				<!-- RIGHT FORM -->

				<div class="form-card">

					<form action="<%=request.getContextPath()%>/addProduct"
						method="post">

						<div class="form-grid">

							<div class="form-group">

								<label> Product Name </label> <input type="text"
									name="productName">

							</div>

							<div class="form-group">

								<label> Category ID </label> <input type="number"
									name="categoryId">

							</div>

							<div class="form-group">

								<label> Price </label> <input type="number" step="0.01"
									name="price">

							</div>

							<div class="form-group">

								<label> Stock Quantity </label> <input type="number"
									name="stockQuantity">

							</div>

							<div class="form-group full-width">

								<label> Image URL </label> <input type="text" name="imagePath">

							</div>

							<div class="form-group full-width">

								<label> Description </label>

								<textarea rows="5" name="description"></textarea>

							</div>

						</div>

						<button type="submit" class="save-btn">Save Product</button>

					</form>

				</div>

			</div>

		</main>
	</div>

</body>

</html>