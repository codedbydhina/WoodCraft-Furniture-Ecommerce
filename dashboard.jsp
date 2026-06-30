<%@ page import="com.woodcraft.model.User"%>

<%@page import="java.util.List"%>
<%@page import="com.woodcraft.daoimpl.OrderDAOImpl"%>
<%@page import="com.woodcraft.model.Order"%>

<%
OrderDAOImpl orderDAO = new OrderDAOImpl();

List<Order> orders = orderDAO.getAllOrders();

int totalOrders = orders.size();
%>

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

<title>WoodCraft Admin Dashboard</title>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webcontent/css/admin-layout.css">

<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600&family=Playfair+Display:wght@500;600;700&display=swap"
	rel="stylesheet">

</head>

<body>

	<div class="admin-container">


		<aside class="sidebar">

			<h2 class="brand">WoodCraft</h2>

			<ul class="menu">

				<li><a href="dashboard.jsp" class="active"> Dashboard </a></li>

				<li><a
					href="<%=request.getContextPath()%>/admin/addProduct.jsp">
						Add Products </a></li>

				<li><a href="<%=request.getContextPath()%>/admin/orders">
						Orders </a></li>

				<li><a href="#"> Customers </a></li>

				<li><a href="#"> Inventory </a></li>

				<li><a href="../logout"> Logout </a></li>

			</ul>

		</aside>

		<main class="main">

			<div class="top-header">

				<input type="text" class="search-box" placeholder="Search products">

				<div class="admin-badge">Admin</div>

			</div>

			<h1 class="page-title">

				Welcome Back,
				<%=user.getName()%>

			</h1>

			<p class="page-subtitle">Manage your luxury furniture business.</p>

			<div class="dashboard-grid">

				<a href="<%=request.getContextPath()%>/admin/addProduct.jsp"
					class="dashboard-card">
					<h3>Add Product</h3>
					<p>Create new wooden furniture products.</p>

				</a> <a href="<%=request.getContextPath()%>/viewProducts"
					class="dashboard-card">

					<h3>Manage Products</h3>

					<p>View and update product catalog.</p>

				</a> <a href="<%=request.getContextPath()%>/admin/orders"
					class="dashboard-card">

					<h3>Total Orders</h3>

					<div class="card-count">

						<%=totalOrders%>

					</div>

					<p>Track customer purchases</p>

				</a> <a href="#" class="dashboard-card">

					<h3>Customers</h3>

					<p>Manage customer accounts.</p>

				</a>

			</div>

		</main>


	</div>

</body>

</html>
