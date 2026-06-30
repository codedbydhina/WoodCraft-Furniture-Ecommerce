<%@page import="java.util.List"%>
<%@page import="com.woodcraft.model.Order"%>

<%
String successMessage = (String) session.getAttribute("successMessage");

session.removeAttribute("successMessage");

List<Order> orders = (List<Order>) request.getAttribute("orders");
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Manage Orders | WoodCraft</title>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webcontent/css/adminOrders.css">

</head>

<body>

	<div class="orders-container">



		<a href="<%=request.getContextPath()%>/admin/dashboard.jsp"
			class="back-btn"> <i class="fa-solid fa-arrow-left"></i> 
			Orders

		</a>



		<%
		if (successMessage != null) {
		%>

		<div class="success-toast">

			<i class="fa-solid fa-circle-check"></i> <span><%=successMessage%></span>

		</div>

		<%
		}
		%>

		<table>

			<colgroup>

				<col style="width: 8%">
				<col style="width: 15%">
				<col style="width: 20%">
				<col style="width: 12%">
				<col style="width: 18%">
				<col style="width: 17%">
				<col style="width: 10%">

			</colgroup>

			<thead>

				<tr>

					<th>Order ID</th>

					<th>Customer</th>

					<th>Email</th>

					<th>Total Amount</th>

					<th>Status</th>

					<th>Order Date</th>

					<th>Action</th>

				</tr>

			</thead>

			<tbody>

				<%
				if (orders != null && !orders.isEmpty()) {
				%>

				<%
				for (Order order : orders) {
				%>

				<tr>

					<td>#<%=order.getOrderId()%>

					</td>

					<td><%=order.getCustomerName()%></td>

					<td><%=order.getCustomerEmail()%></td>

					<td>&#8377;<%=order.getTotalAmount()%>

					</td>

					<td>

						<form action="<%=request.getContextPath()%>/updateOrderStatus"
							method="post">

							<input type="hidden" name="orderId"
								value="<%=order.getOrderId()%>"> <select name="status">

								<option value="PLACED"
									<%=order.getOrderStatus().equals("PLACED") ? "selected" : ""%>>
									PLACED</option>

								<option value="PROCESSING"
									<%=order.getOrderStatus().equals("PROCESSING") ? "selected" : ""%>>
									PROCESSING</option>

								<option value="SHIPPED"
									<%=order.getOrderStatus().equals("SHIPPED") ? "selected" : ""%>>
									SHIPPED</option>

								<option value="DELIVERED"
									<%=order.getOrderStatus().equals("DELIVERED") ? "selected" : ""%>>
									DELIVERED</option>

								<option value="CANCELLED"
									<%=order.getOrderStatus().equals("CANCELLED") ? "selected" : ""%>>
									CANCELLED</option>

							</select>
					</td>

					<td><%=order.getOrderDate()%></td>

					<td>

						<button type="submit" class="update-btn">Update</button>

						</form>

					</td>

				</tr>

				<%
				}
				%>

				<%
				} else {
				%>

				<tr>

					<td colspan="7"
						style="text-align: center; padding: 40px; font-weight: 600;">

						No Orders Found</td>

				</tr>

				<%
				}
				%>

			</tbody>

		</table>

	</div>

	<script>

setTimeout(() => {

    const toast =
    document.querySelector(".success-toast");

    if(toast){

        toast.style.transition =
        "all 0.5s ease";

        toast.style.opacity = "0";

        toast.style.transform =
        "translateX(-50%) translateY(20px)";

        setTimeout(() => {

            toast.remove();

        },500);
    }

},3000);

</script>

</body>

</html>