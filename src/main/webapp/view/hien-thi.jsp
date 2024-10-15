<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Book Management</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 900px;
            margin: 50px auto;
            padding: 20px;
            background-color: white;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        h2 {
            color: #333;
        }

        form {
            margin-bottom: 30px;
        }

        input[type="text"], input[type="number"] {
            width: calc(100% / 4 - 20px);
            padding: 10px;
            margin: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        button {
            padding: 10px 20px;
            background-color: #28a745;
            border: none;
            color: white;
            border-radius: 5px;
            cursor: pointer;
        }

        button:hover {
            background-color: #218838;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        table, th, td {
            border: 1px solid #ddd;
        }

        th, td {
            padding: 12px;
            text-align: center;
        }

        th {
            background-color: #f2f2f2;
        }

        .remove-btn {
            background-color: #dc3545;
            color: white;
            padding: 5px 10px;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }

        .remove-btn:hover {
            background-color: #c82333;
        }

        a {
            text-decoration: none;
            color: #333333;
        }
    </style>

    <script>
        // Confirmation for updating
        function confirmUpdate() {
            return confirm('Are you sure you want to update this book?');
        }

        // Confirmation for removing
        function confirmRemove() {
            return confirm('Are you sure you want to remove this book?');
        }
    </script>

</head>
<body>

<div class="container">
    <h2>Quản lý sách</h2>

    <!-- Search Form -->
    <form action="/sach/search" method="get">
        <input type="text" placeholder="Tìm kiếm sách (Mã hoặc Tên)" name="searchQuery">
        <button type="submit">Search</button>
    </form>

    <!-- Update Form -->
    <form action="/sach/hien-thi" method="post" onsubmit="return confirmUpdate();">
        <input type="text" placeholder="Mã" name="ma" value="${s.ma}">
        <input type="text" placeholder="Tên" name="ten" value="${s.ten}">
        <input type="number" placeholder="Số trang" name="soTrang" value="${s.soTrang}">
        <input type="text" placeholder="Đơn giá" name="donGia" value="${s.donGia}">
        <button type="submit" value="update" name="action">Update</button>
    </form>

    <!-- Table of Books -->
    <table>
        <thead>
        <tr>
            <th>STT</th>
            <th>Mã</th>
            <th>Tên</th>
            <th>Số trang</th>
            <th>Đơn giá</th>
            <th>Hành động</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach varStatus="i" var="s" items="${list}">
            <tr>
                <td>${i.index + 1}</td> <!-- Correcting index to start at 1 -->
                <td><a href="/sach/detail?ma=${s.ma}">${s.ma}</a></td>
                <td>${s.ten}</td>
                <td>${s.soTrang}</td>
                <td>${s.donGia}</td>
                <td>
                    <!-- Remove button with confirmation -->
                    <button class="remove-btn" onclick="return confirmRemove();">
                        <a href="/sach/remove?ma=${s.ma}">Remove</a>
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
