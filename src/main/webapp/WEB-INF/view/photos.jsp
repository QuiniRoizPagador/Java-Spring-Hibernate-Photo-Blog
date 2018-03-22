<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head lang="es">
        <title>Your Blog</title>
        <meta charset="UTF-8">
        <meta name="title" content="Your Blog">
        <meta name="description" content="This is my blog">
        <meta name="subjetc" content="Java Spring Framework Blog">
        <meta name="Language" content="ES">
        <meta name="keywords" content="<c:forEach items="${families}" var="family">${family.description},</c:forEach>"/>
            <meta name="robots" content="Index, Follow">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <meta name="author" content="Quini Roiz">
            <link rel="stylesheet" href="<c:url value="/resources/css/estilos.min.css" />">
        <link rel="stylesheet" href="<c:url value="/resources/css/plugins/bootstrap/bootstrap.min.css" />">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="shortcut icon" href="<c:url value="/resources/images/favicon.ico"/>">
        <style>
            body,h1,h2,h3,h4,h5,h6 {font-family: "Raleway", sans-serif}
            .overlay {
                position: absolute;
                top: 0;
                bottom: 0;
                left: 0;
                right: 0;
                height: 100%;
                width: 100%;
                opacity: 0;
                transition: .5s ease;
            }

            .container:hover .overlay {
                opacity: 1;
            }
            .container {
                position: relative;
                width: 50%;
            }
            .text {
                color: white;
                font-size: 20px;
                position: absolute;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                -ms-transform: translate(-50%, -50%);
            }
        </style>
        <script src='https://www.google.com/recaptcha/api.js'></script>
    </head>
    <body class="w3-light-grey w3-content" style="max-width:1600px">
        <!-- Sidebar/menu -->
        <nav class="w3-sidebar w3-collapse w3-white w3-animate-left" style="z-index:3;width:300px;" id="mySidebar"><br>
            <div class="w3-container">
                <a href="#" onclick="w3_close(this)" class="w3-hide-large w3-right w3-jumbo w3-padding w3-hover-grey" title="close menu">
                    <i class="fa fa-remove"></i>
                </a>
                <a href="${pageContext.request.contextPath}"><img src="<c:url value="/infoPhoto/${info.uuid}/1"/>" style="width:45%;" class="w3-round"><br><br></a>
                <h4><c:out value = "${fn:toUpperCase(info.name)}" /></h4>
            </div>
            <div class="w3-bar-block">
                <a id="linkabout" href="../portal/#about" onclick="w3_close(this)" class="w3-bar-item w3-button w3-padding"><i class="fa fa-user fa-fw w3-margin-right"></i>SOBRE MI</a> 
                <a id="linkport" href="../portal/#portfolio" onclick="w3_close(this)" class="w3-bar-item w3-button w3-padding"><i class="fa fa-th-large fa-fw w3-margin-right"></i>OBRA</a> 
                <a id="linkcurriculum" href="../portal/#curriculum" onclick="w3_close(this)" class="w3-bar-item w3-button w3-padding"><i class="fa fa-address-card fa-fw w3-margin-right"></i>CURRICULUM</a>
                <a id="linkcontact" href="../portal/#contact" onclick="w3_close(this)" class="w3-bar-item w3-button w3-padding"><i class="fa fa-envelope fa-fw w3-margin-right"></i>CONTACTO</a>
            </div>
            <div class="w3-panel w3-large text-center">
                <a class="w3-text-teal w3-hover-opacity" href="https://www.facebook.com/"><i class="fa fa-facebook-official"></i></a>
                <a class="w3-text-teal w3-hover-opacity" href="https://www.instagram.com/"><i class="fa fa-instagram"></i></a>
            </div>
        </nav>

        <!-- Overlay effect when opening sidebar on small screens -->
        <div class="w3-overlay w3-hide-large w3-animate-opacity" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

        <!-- !PAGE CONTENT! -->
        <div class="w3-main" style="margin-left:300px">


            <header>
                <a href="#"></a>
                <span class="w3-button w3-hide-large w3-xxlarge w3-hover-text-grey" onclick="w3_open()"><i class="fa fa-bars"></i></span>
                <section class="w3-row-padding w3-padding-16" >
                    <h1>${photo.family.description}</h1>
                </section>
            </header>

            <section class="w3-container"> 

                <article class="w3-row-padding">

                    <div class="content">

                        <!-- Wrapper for slides -->
                        <c:forEach items="${photos}" var="image" varStatus="index">
                            <section class="col-lg-4 col-md-6 col-xs-8 thumb">
                                <article data-toggle="modal" data-target="#images" class="w3-third w3-container w3-margin-bottom w3-margin-left container w3-hover-opacity " style="height: 300px; width: 300px; overflow: hidden">
                                    <img src="<c:url value="/photo/${image.uuid}"/>" alt="${image.title}" style="overflow: hidden">
                                    <div class="overlay">
                                        <p class="text"><b>${image.title}</b></p>
                                    </div>
                                </article>
                            </section>
                        </c:forEach>

                        <section class="modal fade in" tabindex="-1" role="dialog" id="images" aria-labelledby="PINTURA" aria-hidden="true" style="padding-right: 16px;"> 
                            <article class="modal-dialog modal-lg">
                                <div class="modal-content">
                                    <div id="carousel-5" class="carousel slide" data-ride="carousel">

                                        <!-- Wrapper for slides -->
                                        <div class="carousel-inner">


                                            <c:forEach items="${photos}" var="image" varStatus="loop">
                                                <figure class="item <c:if test="${loop.index eq '0'}">active</c:if>">
                                                    <img class="img-responsive text-center" src="<c:url value="/photo/${image.uuid}"/>" alt="imagen 1">
                                                    <section class="carousel-caption">
                                                        <h3>${image.title}</h3>
                                                        <p>${image.description}</p>
                                                    </section>
                                                </figure>
                                            </c:forEach>
                                        </div>

                                        <!-- Controls -->
                                        <a class="left carousel-control" href="#carousel-5" role="button" data-slide="prev">
                                            <span style="position: absolute;top: 50%;" class="fa fa-chevron-left"></span>
                                        </a>
                                        <a class="right carousel-control" href="#carousel-5" role="button" data-slide="next">
                                            <span style="position: absolute;top: 50%;" class="fa fa-chevron-right"></span>
                                        </a>
                                    </div>
                                </div>
                            </article>
                        </section>


                    </div>
                </article>
            </section>


            <hr>

            <!-- Footer -->
            <footer class="w3-container w3-padding-32 w3-dark-grey">
                <section class="w3-row-padding">
                    <article class="w3-third">
                        <h3>FOOTER</h3>
                        <p>${info.footer}</p>
                    </article>



                    <article class="w3-third">
                        <h3>TAGS POPULARES</h3>
                        <p>
                            <c:forEach items="${families}" var="family">
                                <span class="w3-tag w3-grey w3-small w3-margin-bottom">${family.description}</span>
                            </c:forEach>
                        </p>
                    </article>

                    <article class="w3-third">
                        <h3>SOCIAL NETWORKS</h3>
                        <a class="w3-text-teal w3-hover-opacity" href="https://www.facebook.com/"><i class="fa fa-facebook-official fa-3x w3-hover-opacity"></i> </a>
                        <a class="w3-text-teal w3-hover-opacity" href="https://www.instagram.com/"><i class="fa fa-instagram fa-3x w3-hover-opacity"></i> </a>
                    </article>

                </section>
            </footer>

            <section class="w3-black w3-center w3-padding-24">
                &copy; Realizado por 
                <a class="w3-text-teal w3-hover-opacity" href="https://es.linkedin.com/in/joaqu%C3%ADn-roiz-pagador-87050126" 
                   title="Quini Roiz" target="Quini Roiz">Quini Roiz
                </a>
            </section>

            <!-- End page content -->
        </div>
        <script src="<c:url value="/resources/js/jquery/jquery.min.js"/>"></script>
        <script src="<c:url value="/resources/js/plugins/bootstrap/bootstrap.min.js"/>" ></script>
        <script src="<c:url value="/resources/js/user.min.js"/>"></script>
        <a href="#" class="scroll-top">Ir Arriba</a>
        <script>
                    // Script to open and close sidebar
                    function w3_open(obj) {
                        document.getElementById("mySidebar").style.display = "block";
                        document.getElementById("myOverlay").style.display = "block";
                    }

                    function w3_close(obj) {
                        document.getElementById("mySidebar").style.display = "none";
                        document.getElementById("myOverlay").style.display = "none";
                    }
                    $.ajax({
                        url: "https://geoip-db.com/jsonp",
                        jsonpCallback: "callback",
                        dataType: "jsonp",
                        success: function (location) {
                            $.ajax({
                                url: "<c:url value="/geoLocation"/>/" + location.country_name
                            });
                        }
                    });
                    $(function () {
                        $(window).scroll(function () {
                            if ($(this).scrollTop() > 100) {
                                $('a.scroll-top').fadeIn();
                            } else {
                                $('a.scroll-top').fadeOut();
                            }
                        });
                        $('a.scroll-top').click(function () {
                            $("html, body").animate({
                                scrollTop: 0
                            }, 600);
                            return false;
                        });
                    });
        </script>

    </body>
</html>
