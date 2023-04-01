<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
<!-- 適切なレスポンシブ動作のために必要 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

<title>九九</title>
</head>
<body>
  <div class="d-flex align-items-center justify-content-center" style="height: 100vh">
    <form action="/arithmetic/kuku" method="post">
      <input type="hidden" name="x" value="${x}">
      <input type="hidden" name="y" value="${y}">

      <div class="fs-1">
      	<% if (request.getAttribute("wrong") != null) { %>
          <span class="text-danger">x</span>
        <% } %>
        ${x} x ${y} = <input type="number" name="z" autofocus>
      </div>
    </form>
  </div>
  <!-- JavaScript; Bootstrap Bundle with Popper -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
