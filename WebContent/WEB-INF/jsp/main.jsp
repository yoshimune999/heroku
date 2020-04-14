<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="entity.Account,entity.History, java.util.*,
	java.util.Map, entity.ExpenceTotal, entity.ThisMonthTotal, entity.AddItem, entity.AddBudget"  %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	Account loginAccount = (Account) session.getAttribute("loginAccount") ;
	List<History> historyList = (List<History>) request.getAttribute("historyList");
	List<ExpenceTotal> expenceTotalList = (List<ExpenceTotal>) request.getAttribute("expenceTotalList");
	List<ExpenceTotal> secondExpenceTotalList = (List<ExpenceTotal>) request.getAttribute("secondExpenceTotalList");
	List<ExpenceTotal> thirdExpenceTotalList = (List<ExpenceTotal>) request.getAttribute("thirdExpenceTotalList");
	Map<Integer,AddBudget> budgetMap = (Map<Integer,AddBudget>) session.getAttribute("budgetMap");
	ThisMonthTotal monthTotal = (ThisMonthTotal) request.getAttribute("thisMonthTotal");
	Map<Integer,AddItem> expenceMap = (Map<Integer,AddItem>) session.getAttribute("expenceMap");
	Calendar cal = Calendar.getInstance();
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>家計簿アプリケーション</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.bundle.js"></script>
<link rel="stylesheet" href="/householdAccountsSystem/css/bootstrap.min.css">
<link rel="stylesheet" href="/householdAccountsSystem/css/bootstrap.css">
<link rel="stylesheet" href="/householdAccountsSystem/css/fixed.css">
<link rel="stylesheet" href="/householdAccountsSystem/css/style.css">
<script>
	var resultD = [];
	var resultW = [];
	var dataD = [];
	var dataW = [];
	var Dtotal = 0;
	var Wtotal = 0;

	var sResultD = [];
	var sResultW = [];
	var sDataD = [];
	var sDataW = [];
	var sDtotal = 0;
	var sWtotal = 0;

	var tResultD = [];
	var tResultW = [];
	var tDataD = [];
	var tDataW = [];
	var tDtotal = 0;
	var tWtotal = 0;

	<% for(ExpenceTotal exp : expenceTotalList) { %>
		<% if(exp.getDAmountTotal() != 0) { %>
			resultD.push("<%= exp.getExpence() %>")
			dataD.push(<%= exp.getDAmountTotal() %>)
			Dtotal += <%= exp.getDAmountTotal() %>
		<% } %>
		<% if(exp.getWAmountTotal() != 0) { %>
			resultW.push("<%= exp.getExpence() %>")
			dataW.push(<%= exp.getWAmountTotal() %>)
			Wtotal += <%= exp.getWAmountTotal() %>
		<% } %>
	<% } %>

  	<% for(ExpenceTotal exp : secondExpenceTotalList) { %>
		<% if(exp.getDAmountTotal() != 0) { %>
			sResultD.push("<%= exp.getExpence() %>")
			sDataD.push(<%= exp.getDAmountTotal() %>)
			sDtotal += <%= exp.getDAmountTotal() %>
		<% } %>
		<% if(exp.getWAmountTotal() != 0) { %>
			sResultW.push("<%= exp.getExpence() %>")
			sDataW.push(<%= exp.getWAmountTotal() %>)
			sWtotal += <%= exp.getWAmountTotal() %>
		<% } %>
	<% } %>

	<% for(ExpenceTotal exp : thirdExpenceTotalList) { %>
		<% if(exp.getDAmountTotal() != 0) { %>
			tResultD.push("<%= exp.getExpence() %>")
			tDataD.push(<%= exp.getDAmountTotal() %>)
			tDtotal += <%= exp.getDAmountTotal() %>
		<% } %>
		<% if(exp.getWAmountTotal() != 0) { %>
			tResultW.push("<%= exp.getExpence() %>")
			tDataW.push(<%= exp.getWAmountTotal() %>)
			tWtotal += <%= exp.getWAmountTotal() %>
		<% } %>
	<% } %>

	function checkAddItem() {
		if (addItemForm.expenceId.value == ""){
			alert("費目を指定してください");
			return false;
		} else if (addItemForm.addItemAmount.value.match(/^\d+$/) == null) {
			alert("金額を数字で入力してください");
			return false;
		} else if (addItemForm.addItemAmount.value >= 100000000 ){
			alert("金額の桁が上限を超えています")
			return false;
		} else {
			return true;
		}
	}

	function checkBudget() {
		if (budgetForm.budgetExpenceId.value == ""){
			alert("費目を指定してください");
			return false;
		} else if (budgetForm.budgetAmount.value.match(/^\d+$/) == null) {
			alert("金額を数字で入力してください");
			return false;
		} else if (budgetForm.budgetAmount.value >= 100000000 ){
			alert("金額の桁が上限を超えています")
			return false;
		} else {
			return true;
		}
	}
	function checkAddNewExpenceForm() {
		if (addNewExpenceForm.newExpence.value == "" ){
			alert("費目を入力してください");
			return false;
		} else {
			return true;
		}
	}
</script>
</head>
<body data-spy="scroll" data-target="#navbarResponsive">

	<div id="home">
		<nav class="navbar navbar-expand-lg  navbar-dark bg-dark fixed-top">
			<a class="navbar-brand" href="#"><img src="/householdAccountsSystem/img/icon.jpeg">ACCOUNTBOOK</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse " id="navbarResponsive">
				<ul  class="navbar-nav ml-auto">
					<li class="nav-item">
						<a class="nav-link" href="#home">home</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="#history">履歴</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="#expenceTotal">今月の状況</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="#addItem">実績を追加</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="#budget">予算残高</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="#addBudget">予算を追加</a>
					</li>
				</ul>
			</div>
		</nav>



		<div class="landing">
			<div class="home-wrap">
				<div class="home-inner">

				</div>
			</div>
		</div>

		<div class="caption text-center">
			<h1>WELCOME TO ACCOUNT BOOK</h1>
			<h2 class="font-weight-normal mt-5"><c:out value="<%=loginAccount.getUserName() %>"/>さん</h2>
		</div>

	</div>




	<div class="history "id="history">
		<h1 class="text-white text-center bg-dark">履歴</h1>
		<table class="table">
		<thead class="thead-dark"><tr><th>DATE</th><th>ITEM</th><th>AMOUNT</th><th>MEMO</th></tr></thead>
			<% if(historyList.isEmpty()){ %>
			<tr><th>現在、履歴がありません</th><th></th><th> </th><th> </th></tr>
			<% } %>
			<% for(History his : historyList) {%>
				<tr><th><%= his.getDate() %></th><th><%= his.getExpence() %></th><th>
					<% if(his.getDAmount() != 0){%>
						+<%= his.getDAmount() %>円
					 <% } %>

					<% if(his.getWAmount() != 0){%>
						-<%= his.getWAmount() %>円
					<% } %>
					<% String memo = his.getMemo(); %>
					</th><th><c:out value="<%= memo %>"/></th></tr>
			<% } %>
		</table>
	</div>
	<div class="tabs py-5">
		<ul class="nav nav-tabs">
			<li class="nav-item">
				<a href="#expenceTotal" class="nav-link active" data-toggle="tab"><%= cal.get(Calendar.MONTH)+1 %>月</a>
			</li>
			<li class="nav-item">
				<a href="#expenceTotal2" class="nav-link" data-toggle="tab"><%= cal.get(Calendar.MONTH) %>月</a>
			</li>
			<li class="nav-item">
				<a href="#expenceTotal3" class="nav-link" data-toggle="tab"><%= cal.get(Calendar.MONTH)-1 %>月</a>
			</li>

		</ul>
	</div>

	<div class="tab-content">
		<div class="expenceTotal tab-pane active" id="expenceTotal">
			<div class="expenceTotal-container">
				<div class="row">
					<div class="chart-1 col-lg-6 col-md-6">
						<h1>OUT</h1>
						<canvas id="chartjs-1" class="chartjs" width="400" height="200" style="display: block; width: 400px; height: 200px;"></canvas>
						<script>
							var outChart =  new Chart(document.getElementById("chartjs-1"),{
							"type":'pie',"data":{
							"labels":resultW,"datasets":[{"label":"My First Dataset",
							"data":dataW,
							"backgroundColor":["#e41a1c","#377eb8","#4daf4a","#984ea3","#ff7f00","#ffff33","#a65628",,"#f781bf","#999999","#fbb4ae","#b3cde3","#ccebc5","#decbe4","#fed9a6","#ffffcc",,"#e5d8bd","#fddaec","#f2f2f2"]}]}});
						</script>
						<h3 class="text-center font-weight-normal"><script>document.write("TOTAL"+": &yen"+Wtotal);</script></h3>
					</div>

					<div class="chart-2 col-lg-6 col-md-6">
					<h1>IN</h1>
						<canvas id="chartjs-2" class="chartjs" width="400" height="200" style="display: block; width: 400px; height: 200px;"></canvas>
						<script>
							var inChart = new Chart(document.getElementById("chartjs-2"),{
							"type":"pie","data":{
							"labels":resultD,"datasets":[{"label":"My First Dataset",
							"data":dataD,
							"backgroundColor":["#e41a1c","#377eb8","#4daf4a","#984ea3","#ff7f00","#ffff33","#a65628",,"#f781bf","#999999","#fbb4ae","#b3cde3","#ccebc5","#decbe4","#fed9a6","#ffffcc",,"#e5d8bd","#fddaec","#f2f2f2"]}]}});
						</script>
						<h3 class="text-center font-weight-normal"><script>document.write("TOTAL"+": &yen"+Dtotal);</script></h3>
					</div>
				</div>

				<div class="">
					<h3 class="text-center">ALL TOTALL: &yen<%= monthTotal.getTotal() %>円</h3>
				</div>
			</div>
		</div>
		<div class="expenceTotal tab-pane " id="expenceTotal2">
			<div class="expenceTotal-container">
				<div class="row">
					<div class="chart-1 col-lg-6 col-md-6">
						<h1>OUT</h1>
						<canvas id="chartjs-3" class="chartjs" width="400" height="200" style="display: block; width: 400px; height: 200px;"></canvas>
						<script>
							new Chart(document.getElementById("chartjs-3"),{"type":"pie","data":{
							"labels":sResultW,"datasets":[{"label":"My First Dataset",
							"data":sDataW,
							"backgroundColor":["#e41a1c","#377eb8","#4daf4a","#984ea3","#ff7f00","#ffff33","#a65628",,"#f781bf","#999999","#fbb4ae","#b3cde3","#ccebc5","#decbe4","#fed9a6","#ffffcc",,"#e5d8bd","#fddaec","#f2f2f2"]}]}});
						</script>
						<h3 class="text-center font-weight-normal"><script>document.write("TOTAL"+": &yen"+sWtotal);</script></h3>
					</div>

					<div class="chart-2 col-lg-6 col-md-6">
					<h1>IN</h1>
						<canvas id="chartjs-4" class="chartjs" width="400" height="200" style="display: block; width: 400px; height: 200px;"></canvas>
						<script>
							new Chart(document.getElementById("chartjs-4"),{"type":"pie","data":{
							"labels":sResultD,"datasets":[{"label":"My First Dataset",
							"data":sDataD,
							"backgroundColor":["#e41a1c","#377eb8","#4daf4a","#984ea3","#ff7f00","#ffff33","#a65628",,"#f781bf","#999999","#fbb4ae","#b3cde3","#ccebc5","#decbe4","#fed9a6","#ffffcc",,"#e5d8bd","#fddaec","#f2f2f2"]}]}});
						</script>
						<h3 class="text-center font-weight-normal"><script>document.write("TOTAL"+": &yen"+sDtotal);</script></h3>
					</div>
				</div>

				<div class="">
					<h3 class="text-center">ALL TOTALL: &yen<script>sDtotal - sWtotal</script>円</h3>
				</div>
			</div>
		</div>
		<div class="expenceTotal tab-pane " id="expenceTotal3">
			<div class="expenceTotal-container">
				<div class="row">
					<div class="chart-1 col-lg-6 col-md-6">
						<h1>OUT</h1>
						<canvas id="chartjs-5" class="chartjs" width="400" height="200" style="display: block; width: 400px; height: 200px;"></canvas>
						<script>
							new Chart(document.getElementById("chartjs-5"),{"type":"pie","data":{
							"labels":tResultW,"datasets":[{"label":"My First Dataset",
							"data":tDataW,
							"backgroundColor":["#e41a1c","#377eb8","#4daf4a","#984ea3","#ff7f00","#ffff33","#a65628",,"#f781bf","#999999","#fbb4ae","#b3cde3","#ccebc5","#decbe4","#fed9a6","#ffffcc",,"#e5d8bd","#fddaec","#f2f2f2"]}]}});
						</script>
						<h3 class="text-center font-weight-normal"><script>document.write("TOTAL"+": &yen"+tWtotal);</script></h3>
					</div>

					<div class="chart-2 col-lg-6 col-md-6">
					<h1>IN</h1>
						<canvas id="chartjs-6" class="chartjs" width="400" height="200" style="display: block; width: 400px; height: 200px;"></canvas>
						<script>
							new Chart(document.getElementById("chartjs-6"),{"type":"pie","data":{
							"labels":tResultD,"datasets":[{"label":"My First Dataset",
							"data":tDataD,
							"backgroundColor":["#e41a1c","#377eb8","#4daf4a","#984ea3","#ff7f00","#ffff33","#a65628",,"#f781bf","#999999","#fbb4ae","#b3cde3","#ccebc5","#decbe4","#fed9a6","#ffffcc",,"#e5d8bd","#fddaec","#f2f2f2"]}]}});
						</script>
						<h3 class="text-center font-weight-normal"><script>document.write("TOTAL"+": &yen"+tDtotal);</script></h3>
					</div>
				</div>

				<div class="">
					<h3 class="text-center">ALL TOTALL: &yen<script>tDtotal - tWtotal</script>円</h3>
				</div>
			</div>
		</div>


	</div>



	<div class="addItem container" id="addItem">
		<div class="boxline">
		<h2>実績を追加</h2>
		<form class="form-inline" action="/householdAccountsSystem/MainServlet?addItemForm=done" method="post" name="addItemForm">
			<select class="custom-select my-1 mr-sm-2" id="inlineFormCustomSelectPref" name="date">
			</select>
			<select class="custom-select my-1 mr-sm-2" id="inlineFormCustomSelectPref" name=expenceId id="">
				<option value="" selected>費目を指定してください</option>
				<% for(Integer key : expenceMap.keySet()) { AddItem addItem = expenceMap.get(key); %>
					<option value="<%= key %>"><c:out value="<%= addItem.getExpenceName() %>"/></option>
				<% } %>
			</select>
			<input type="text" class="form-control" name="addItemAmount" placeholder="金額"><label>円</label>
			<input type="text" class="form-control" name="memo" placeholder="メモ">
			<input type="submit" value="追加" class="btn" onclick="return checkAddItem()">
		</form>
		</div>
		<button type="button" class="btn" data-toggle="modal" data-target="#modal1">費目を追加する</button>
	</div>





	<div class="budget container" id="budget">
		<h2 class="py-5">予算残高</h2>
		<%
		ArrayList<Integer> id = new ArrayList<Integer>();
		for (int i=0; i<expenceTotalList.size(); i++) {
			id.add(expenceTotalList.get(i).getExpenceId());
		}
		if(budgetMap.isEmpty()) { %>
			<p>現在、予算が登録されていません</p>
		<% }
		for(Integer key : budgetMap.keySet()) {
			AddBudget addBudget = budgetMap.get(key);
			int budget = addBudget.getAmount(); %>
			<ul style="margin-right: 20px;">
				<li class="pb-4">
					<%= addBudget.getExpenceName() %> : <%= addBudget.getAmount() %>円
					<% for(ExpenceTotal exp : expenceTotalList) {
						int wAmount = exp.getWAmountTotal();
						double width =  (budget - wAmount + 0.0) / (budget + 0.0) * 100;
						if (key == exp.getExpenceId()) { %>
							<div class="progress" style="height: 20px;">
								<% if (budget >= wAmount) { %>
									<div class="progress-bar progress-bar-striped progress-bar-animated bg-success " role="progressbar"
									aria-valuenow="<%= budget - wAmount %>" aria-valuemin="0"
									aria-valuemax="<%= budget %>"
									style="width: <%= width %>%;">残り<%=budget - wAmount %>円</div>
								<% } else if (budget <= wAmount) {%>
									<div class="progress-bar progress-bar-striped progress-bar-animated bg-danger " role="progressbar"
									aria-valuenow="0" aria-valuemin="0"
									aria-valuemax="<%= budget %>"
									style="width: 0%;"></div><span class="ml-1" style="color: red;"><%= addBudget.getAmount() - exp.getWAmountTotal() %>円</span>
								<%  } %>
							</div>
						<% } %>
					<% } %>
					<% if (!(id.contains(key))) { %>
						<div class="progress">
							<div class="progress-bar progress-bar-striped progress-bar-animated bg-success" role="progressbar" aria-valuenow="<%= budget %>" aria-valuemin="0" aria-valuemax="<%= budget %>" style="width: <%= budget %>%">残り<%= budget %>円</div>
						</div>
					<% } %>
				</li>
			</ul>
		<% } %>
	</div>




	<div class="addBudget container py-5" id="addBudget">
		<div class="boxline">
		<h2>費目の予算を登録</h2>
		<form class="form-inline" action="/householdAccountsSystem/MainServlet?budgetForm=done" method="post" name="budgetForm">
			<select class="custom-select my-1 mr-sm-2" id="inlineFormCustomSelectPref"name=budgetExpenceId id="">
			<option value="" selected>費目を指定してください</option>
			<% for(Integer key : expenceMap.keySet()) { AddItem addItem = expenceMap.get(key); %>
				<% if(addItem.getBalanceOPId() == 1) { %>
					<option value="<%= key %>"><c:out value="<%= addItem.getExpenceName() %>"/></option>
				<% } %>
			<% } %></select> <input type="text" class="form-control" name="budgetAmount" placeholder="金額"> <input type="submit" value="登録" class="btn btn-default" onclick="return checkBudget()">
		</form>
		</div>
		<button type="button" class="btn" data-toggle="modal" data-target="#modal1">費目を追加する</button>
	</div>



	<div>

	</div>



	<div>
 		<p><a href="/householdAccountsSystem">←ログイン画面へ</a></p>
	</div>



	<div class="modal fade" id="modal1" tabindex="-1"
		role="dialog" aria-labelledby="label1" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="label1">費目追加FORM</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>

					<form class="form-inline" action="/householdAccountsSystem/MainServlet?addNewExpenceForm=done"  method="post" name="addNewExpenceForm">
						<div class="modal-body">
							<!-- 費目の項目を追加するフォーム -->
							<div class="container">
								<div class="row col-12">
									<input type="radio" name="balanceOP"  id="1" value="1" checked><label>支出</label>
									<input type="radio" name="balanceOP" id="2" value="2"><label>収入</label>
								</div>
								<div class="row col-12">
									<input  type="text" class="form-control " name="newExpence" placeholder="追加する費目名" >
								</div>
							</div>
						</div>

						<div class="modal-footer addNewExpenceForm">
							<input type="submit" class="btn btn-default" value="費目の項目を新しく追加する" onclick="return checkAddNewExpenceForm()" >
						</div>
					</form>
			</div>
		</div>
	</div>





<script type="text/javascript" src="/householdAccountsSystem/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="/householdAccountsSystem/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/householdAccountsSystem/js/date.js"></script>
</body>
</html>