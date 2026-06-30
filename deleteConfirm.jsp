<%@ page import="com.woodcraft.model.Product"%>

<%
Product product = (Product) request.getAttribute("product");
%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>Delete Product</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webcontent/css/delete-confirm.css">

</head>

<body>

	<div class="confirm-card">

		<div class="warning-icon">
			<i class="fa-solid fa-triangle-exclamation"></i>
		</div>

		<h1>Delete Product?</h1>

		<p>

			Are you sure you want to delete <span class="product-name"> <%=product.getProductName()%>

			</span> ? <br> <br> This action cannot be undone.

		</p>

		<div class="btn-group">

			<a href="<%=request.getContextPath()%>/viewProducts"
				class="cancel-btn"> Cancel </a>

			<form action="<%=request.getContextPath()%>/deleteProduct"
				method="post">

				<input type="hidden" name="productId"
					value="<%=product.getProductId()%>">

				<button type="submit" class="delete-btn">Delete Product</button>

			</form>

		</div>

	</div>

</body>
</html>