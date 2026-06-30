<%@ page import="java.util.*"%>
<%@ page import="com.woodcraft.model.Product"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Products</title>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webcontent/css/view-products.css">

</head>

<body>
	<%
	List<Product> products = (List<Product>) request.getAttribute("products");
	%>
</body>

<div class="top-bar">

    <a href="admin/dashboard.jsp" class="back-btn">
        Products
    </a>

</div>


<div class="products-grid">
	<%
	if (products != null) {

		for (Product p : products) {
	%>

	<div class="product-card">

		<img src="<%=request.getContextPath()%>/<%=p.getImagePath()%>"
			alt="<%=p.getProductName()%>" class="product-image">

		<div class="product-info">

			<h3>
				<%=p.getProductName()%>
			</h3>

			<div class="price">
				&#8377;
				<%=p.getPrice()%>
			</div>

			<div class="stock">
				Stock :
				<%=p.getStockQuantity()%>
			</div>

			<div class="actions">

				<a
					href="<%=request.getContextPath()%>/editProduct?id=<%=p.getProductId()%>"
					class="edit-btn"> Edit </a> <a
					href="<%=request.getContextPath()%>/deleteConfirm?id=<%=p.getProductId()%>"
					class="delete-btn"> Delete </a>
			</div>

		</div>

	</div>

	<%
	}
	} else {
	%>
	<h2>No Products Found</h2>

	<%
	}
	%>

</div>

</body>
</html>