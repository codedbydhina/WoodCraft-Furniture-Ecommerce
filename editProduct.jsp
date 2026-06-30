<%@ page import="com.woodcraft.model.Product"%>

<%
Product product = (Product) request.getAttribute("product");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Product</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webcontent/css/edit-product.css">
</head>

<body>

	<div class="edit-container">

		<h1 class="edit-title">Edit Product</h1>

		<p class="edit-subtitle">Update your furniture information.</p>

		<form action="<%=request.getContextPath()%>/updateProduct"
			method="post">

			<input type="hidden" name="productId"
				value="<%=product.getProductId()%>">

			<div class="form-grid">

				<div class="form-group">

					<label> Product Name </label> <input type="text" name="productName"
						value="<%=product.getProductName()%>">

				</div>

				<div class="form-group">

					<label> Price </label> <input type="number" step="0.01"
						name="price" value="<%=product.getPrice()%>">

				</div>

			</div>

			<button type="submit" class="update-btn">Update Product</button>

		</form>

	</div>

</body>
</html>