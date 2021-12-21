<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="EUC-KR" />
	<title>������� ��ȸ</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

	<script type="text/javascript">
		$(document).ready(function() {
			$("#PayCheckAct").on("click", function(event) {
				var formData = new FormData($("#PayCheckForm")[0]);

				$.ajax({
					type: "POST",
					cache: false,
					processData: false,
					contentType: false,
					async: false,
					url: "/payInfo",
					dataType: "json",
					data: formData,
					success: function(data) {
						console.log(data);

						alert(data.PCD_PAY_MSG);

						var $_table = $('<table style="border: 1px solid black"></table>');
						var table_data = "";

						$.each(data, function(key, value) {
							table_data +=
								"<tr><td>" + key + "</td><td>: " + value + "</td><tr>";
						});

						$_table.append(table_data);

						$_table.appendTo("#payInfoResult");
					},
					error: function(jqxhr, status, error) {
						console.log(jqxhr);

						alert(jqxhr.statusText + ",  " + status + ",   " + error);
						alert(jqxhr.status);
						alert(jqxhr.responseText);
					},
				});
			});
		});
	</script>
	<style>
		#form_area {
			width: 600px;
			margin-bottom: 5px;
		}
		input {
			width: 300px;
			height: 20px;
			border: 1px solid gray;
		}
		button {
			width: 80px;
			float: right;
			margin-top: 5px;
			margin-left: 5px;
		}
	</style>
</head>

<body>
	<h3>������� ��ȸ</h3>
	<div id="form_area">
		<form id="PayCheckForm">
			<table>
				<tr>
					<td>*��������</td>
					<td> :
						<select id="PCD_PAY_TYPE" name="PCD_PAY_TYPE">
							<option value="card">ī��</option>
							<option value="transfer">������ü</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>*�ֹ���ȣ</td>
					<td> :
						<input type="text" id="PCD_PAY_OID" name="PCD_PAY_OID" />
					</td>
				</tr>
				<tr>
					<td>*�������� (YYYYMMDD)</td>
					<td> :
						<input type="number" id="PCD_PAY_DATE" name="PCD_PAY_DATE" size="9" maxlength="8" />
					</td>
				</tr>
			</table>
		</form>
		<button id="PayCheckAct">��ȸ</button>
	</div>
	<br />
	<hr />
	<div id="payInfoResult"></div>
</body>

</html>