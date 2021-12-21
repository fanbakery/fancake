<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
    <title>���� ������� �����</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

    <script type="text/javascript">
        $(document).ready(function() {

            $("#PaySimpleAction").on("click", function(e) {
                e.preventDefault();

                var con = "������� ������� ��û�մϴ�. \n �����Ͻðڽ��ϱ�? ";

                if (confirm(con) == true) {
                    var formData = new FormData($("#paySimpleForm")[0]);

                    $.ajax({
                        type: "POST",
                        cache: false,
                        processData: false,
                        contentType: false,
                        async: false,
                        url: "/paySimpleSend",
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

                            $_table.appendTo("#payConfirmResult");
                        },
                        error: function(jqxhr, status, error) {
                            console.log(jqxhr);

                            alert(jqxhr.statusText + ",  " + status + ",   " + error);
                            alert(jqxhr.status);
                            alert(jqxhr.responseText);
                        },
                    });
                } else {
                    return false;
                }
            });
            
            $("#PCD_PAY_TYPE").on('change', function(e) {

                e.preventDefault();

                var this_val = $(this).val();

                if (this_val == 'card') {
                    $("#taxsave_trade_view").css('display', 'none');
                } else {
                    $("#taxsave_view").css('display', '');
                }

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
            width: 100px;
            float: right;
            margin-top: 5px;
            margin-left: 5px;
        }
    </style>
</head>

<body>
    <h3>������� �����(����Ű)</h3>
    <div id="form_area">
        <form id="paySimpleForm">
            <table>
             	<tr>
                    <td>*��������</td>
                    <td> :
                        <select id="PCD_PAY_TYPE" name="PCD_PAY_TYPE">
                            <option value="card" selected>ī��</option>
                            <option value="transfer">������ü</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>*������ ���� ID(����Ű)</td>
                    <td> :
                        <input type="text" name="PCD_PAYER_ID" id="PCD_PAYER_ID" value="${payer_id}" required />
                    </td>
                </tr>
                <tr>
                    <td>*��ǰ��</td>
                    <td> :
                        <input type="text" name="PCD_PAY_GOODS" id="PCD_PAY_GOODS" value="${pay_goods}" required />
                    </td>
                </tr>
                <tr>
                    <td>*�����ݾ�</td>
                    <td> :
                        <input type="text" name="PCD_PAY_TOTAL" id="PCD_PAY_TOTAL" value="${pay_total}" required />
                    </td>
                </tr>
                <tr>
                    <td>������ ������ȣ</td>
                    <td> :
                        <input type="text" name="PCD_PAYER_NO" id="PCD_PAYER_NO" value="${payer_no}" />
                    </td>
                </tr>
                <tr>
                    <td>������ �̸���</td>
                    <td> :
                        <input type="text" name="PCD_PAYER_EMAIL" id="PCD_PAYER_EMAIL" value="${payer_email}" />
                    </td>
                </tr>
                <tr>
                    <td>��������</td>
                    <td> :
                        <select id="PCD_PAY_ISTAX" name="PCD_PAY_ISTAX">
                            <option value="Y" selected>����</option>
                            <option value="N">�����</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>�ΰ���</td>
                    <td> :
                        <input type="text" name="PCD_PAY_TAXTOTAL" id="PCD_PAY_TAXTOTAL" value="" />
                    </td>
                </tr>
	                <tr id="taxsave_flag_view" style="display:none;">
	                    <td>���ݿ����� ���࿩��</td>
	                    <td> :
	                        <select id="PCD_TAXSAVE_FLAG" name="PCD_TAXSAVE_FLAG">
	                            <option value="N">N</option>
	                            <option value="Y">Y</option>
	                        </select>
	                    </td>
	                </tr>
	                <tr id="taxsave_trade_view" style="display:none;">
	                    <td>���ݿ����� ����Ÿ��</td>
	                    <td> :
	                        <select id="PCD_TAXSAVE_TRADE" name="PCD_TAXSAVE_TRADE">
	                            <option value="personal">�ҵ����</option>
	                            <option value="company">��������</option>
	                        </select>
	                    </td>
	                </tr>
	                <tr id="taxsave_idnum_view" style="display:none;">
	                    <td>���ݿ����� ������ ��ȣ</td>
	                    <td> :
	                        <input type="text" id="PCD_TAXSAVE_IDNUM" name="PCD_TAXSAVE_IDNUM" value="" />
	                    </td>
	                </tr>
                </div>
            </table>
        </form>
        <button id="PaySimpleAction">����� ��û</button>
    </div>
    <br />
    <hr />
    <div id="payConfirmResult"></div>
</body>

</html>