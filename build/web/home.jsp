<%-- 
    Document   : home
    Created on : Aug 23, 2022, 1:29:14 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link href="style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <!--begin of menu-->
        <nav class="navbar navbar-expand-md navbar-dark bg-dark">
            <div class="container">
                <a class="navbar-brand" href="Homecontroller">Class Course </a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse justify-content-end" id="navbarsExampleDefault">
                    <ul class="navbar-nav m-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="Homecontroller">Home</a>
                        </li>
                        <c:if test="${sessionScope.acc.isSell == 1}">
                             <li class="nav-item">
                            <a class="nav-link" href="ManagerController">Manager Product</a>
                        </li>
                            
                        </c:if>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Information</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="createNewAccount.jsp">Sign up</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="login.html">Sign in</a>
                        </li>
                    </ul>

                    <form action="MainController" method="post" class="form-inline my-2 my-lg-0">
                        <div class="input-group input-group-sm">
                            <input name="txtSearchValue" type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" placeholder="Search...">
                            <div class="input-group-append">
                                <input type="submit" value="Search" name="btAction" />
                            </div>
                        </div>
                    

                            <a class="btn btn-success btn-sm ml-3" href="AddItemController">
                                <i class="fa fa-shopping-cart"></i> Cart
                                <!--                            <span class="badge badge-light">3</span>-->
                            </a>
                        </form>
                   
                </div>
            </div>
        </nav>
        <section class="jumbotron text-center">
            <div class="container">
                <h1 class="jumbotron-heading">The class has Piano, Guitar, and Drawing courses from basic to advanced</h1>
                <p class="lead text-muted mb-0">Suitable for every age</p>
            </div>
        </section>
        <!--end of menu-->
        <div class="container">
            <div class="row">
                <div class="col">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="Homecontroller">Home</a></li>
                            <li class="breadcrumb-item"><a href="Homecontroller">Courses</a></li>
                            <li class="breadcrumb-item active" aria-current="#">Sub-category</li>
                        </ol>
                    </nav>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-sm-3">
                    <div class="card bg-light mb-3">
                        <div class="card-header bg-primary text-white text-uppercase"><i class="fa fa-list"></i> Categories</div>
                        <ul class="list-group category_block">
                            <c:forEach items="${listCC}" var="o">
                                <li class="list-group-item text-white"><a href="CategoryController?cid=${o.cid}">${o.cname}</a></li>
                                </c:forEach>

                        </ul>
                    </div>
                    <div class="card bg-light mb-3">
                        <div class="card-header bg-success text-white text-uppercase">Last product</div>
                        <div class="card-body">
                            <img class="img-fluid" src="${p.image}" />
                            <h5 class="card-title">${p.name}</h5>
                            <p class="card-text">${p.title}</p>
                            <p class="bloc_left_price">${p.price} $</p>
                        </div>
                    </div>
                </div>

                <div class="col-sm-9" >
                    <div class="row">
                        <c:forEach items="${listP}" var="o" >
                            <div class="col-12 col-md-6 col-lg-4">
                                <div class="card">
                                    <img class="card-img-top" src="${o.image}" alt="Card image cap">
                                    <div class="card-body" >
                                        <h4 class="card-title show_txt"><a href="DetailController?pid=${o.id}" title="View Product">${o.name}</a></h4>
                                        <p class="card-text show_txt">${o.title}</p>
                                        <div class="row">
                                            <div class="col">
                                                <p class="btn btn-danger btn-block">${o.price} $</p>
                                            </div>
                                            <div class="col" >
                                                <a href="Homecontroller" class="btn btn-success btn-block" >Add to cart</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>

            </div>
        </div>
        <c:forEach begin="1" end="${endP}" var="i">               
            <a href="ListProduct?index=${i}">${i}</a>
        </c:forEach>

        <!-- Footer -->
        <footer class="text-light">
            <div class="container">
                <div class="row">
                    <div class="col-md-3 col-lg-4 col-xl-3">
                        <h5>About</h5>
                        <hr class="bg-white mb-2 mt-0 d-inline-block mx-auto w-25">
                        <p class="mb-0">
                            Le Lorem Ipsum est simplement du faux texte employé dans la composition et la mise en page avant impression.
                        </p>
                    </div>

                    <div class="col-md-2 col-lg-2 col-xl-2 mx-auto">
                        <h5>Informations</h5>
                        <hr class="bg-white mb-2 mt-0 d-inline-block mx-auto w-25">
                        <ul class="list-unstyled">
                            <li><a href="https://vietthuong.edu.vn/khoa-hoc-piano?gclid=Cj0KCQjw9ZGYBhCEARIsAEUXITVD0otUs2esO_zVBJC0jtix1A5PjQNkSL-Lvc8UYQXiVNO4q2L7QGMaAlJTEALw_wcB">Học Piano</a></li>
                            <li><a href="https://vietthuong.edu.vn/khoa-hoc-piano?gclid=Cj0KCQjw9ZGYBhCEARIsAEUXITVD0otUs2esO_zVBJC0jtix1A5PjQNkSL-Lvc8UYQXiVNO4q2L7QGMaAlJTEALw_wcB">Học Guitar</a></li>
                            <li><a href="https://vietthuong.edu.vn/khoa-hoc-piano?gclid=Cj0KCQjw9ZGYBhCEARIsAEUXITVD0otUs2esO_zVBJC0jtix1A5PjQNkSL-Lvc8UYQXiVNO4q2L7QGMaAlJTEALw_wcB">Học Vẽ</a></li>

                        </ul>
                    </div>

                    <div class="col-md-3 col-lg-2 col-xl-2 mx-auto">
                        <h5>Others links</h5>
                        <hr class="bg-white mb-2 mt-0 d-inline-block mx-auto w-25">
                        <ul class="list-unstyled">
                            <li><a href="https://vietthuong.edu.vn/khoa-hoc-piano?gclid=Cj0KCQjw9ZGYBhCEARIsAEUXITVD0otUs2esO_zVBJC0jtix1A5PjQNkSL-Lvc8UYQXiVNO4q2L7QGMaAlJTEALw_wcB">Học Piano</a></li>
                            <li><a href="https://vietthuong.edu.vn/khoa-hoc-piano?gclid=Cj0KCQjw9ZGYBhCEARIsAEUXITVD0otUs2esO_zVBJC0jtix1A5PjQNkSL-Lvc8UYQXiVNO4q2L7QGMaAlJTEALw_wcB">Học Piano</a></li>
                            <li><a href="https://vietthuong.edu.vn/khoa-hoc-piano?gclid=Cj0KCQjw9ZGYBhCEARIsAEUXITVD0otUs2esO_zVBJC0jtix1A5PjQNkSL-Lvc8UYQXiVNO4q2L7QGMaAlJTEALw_wcB">Học Piano</a></li>
                            <li><a href="https://vietthuong.edu.vn/khoa-hoc-piano?gclid=Cj0KCQjw9ZGYBhCEARIsAEUXITVD0otUs2esO_zVBJC0jtix1A5PjQNkSL-Lvc8UYQXiVNO4q2L7QGMaAlJTEALw_wcB">Học Piano</a></li>
                        </ul>
                    </div>

                    <div class="col-md-4 col-lg-3 col-xl-3">
                        <h5>Contact</h5>
                        <hr class="bg-white mb-2 mt-0 d-inline-block mx-auto w-25">
                        <ul class="list-unstyled">
                            <li><i class="fa fa-home mr-2"></i> My company</li>
                            <li><i class="fa fa-envelope mr-2"></i> email@example.com</li>
                            <li><i class="fa fa-phone mr-2"></i> + 33 12 14 15 16</li>
                            <li><i class="fa fa-print mr-2"></i> + 33 12 14 15 16</li>
                        </ul>
                    </div>
                    <div class="col-12 copyright mt-3">
                        <p class="float-left">
                            <a href="#">Back to top</a>
                        </p>
                        <p class="text-right text-muted">created with <i class="fa fa-heart"></i> by <a href="https://t-php.fr/43-theme-ecommerce-bootstrap-4.html"><i>t-php</i></a> | <span>v. 1.0</span></p>
                    </div>
                </div>
            </div>
        </footer>
    </body>
</html>
