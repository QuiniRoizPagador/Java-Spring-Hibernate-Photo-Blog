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
                <meta name="keywords" content="<c:forEach items=" ${families} " var="family ">${family.description},</c:forEach>"/>
                <meta name="robots" content="Index, Follow">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <meta name="author" content="Quini Roiz">
                <link rel="stylesheet" href="<c:url value=" /resources/css/estilos.min.css " />">
                <link rel="stylesheet" href="<c:url value=" /resources/css/plugins/bootstrap/bootstrap.min.css " />">
                <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
                <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
                <link rel="shortcut icon" href="<c:url value=" /resources/images/favicon.ico "/>">
                <style>
                    body,
                    h1,
                    h2,
                    h3,
                    h4,
                    h5,
                    h6 {
                        font-family: "Raleway", sans-serif
                    }

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
                <nav class="w3-sidebar w3-collapse w3-white w3-animate-left" style="z-index:3;width:300px;" id="mySidebar">
                    <br>
                    <div class="w3-container">
                        <a href="#" onclick="w3_close(this)" class="w3-hide-large w3-right w3-jumbo w3-padding w3-hover-grey" title="close menu">
                            <i class="fa fa-remove"></i>
                        </a>
                        <a href="${pageContext.request.contextPath}">
                            <img src="<c:url value=" /infoPhoto/${info.uuid}/1 "/>" style="width:45%;" class="w3-round">
                            <br>
                            <br>
                        </a>
                        <h4>
                            <c:out value="${fn:toUpperCase(info.name)}" />
                        </h4>
                    </div>
                    <div class="w3-bar-block">
                        <a id="linkabout" href="#about" onclick="w3_close(this)" class="w3-bar-item w3-button w3-padding">
                            <i class="fa fa-user fa-fw w3-margin-right"></i>SOBRE MI</a>
                        <a id="linkport" href="#portfolio" onclick="w3_close(this)" class="w3-bar-item w3-button w3-padding">
                            <i class="fa fa-th-large fa-fw w3-margin-right"></i>OBRA</a>
                        <a id="linkcurriculum" href="#curriculum" onclick="w3_close(this)" class="w3-bar-item w3-button w3-padding">
                            <i class="fa fa-address-card fa-fw w3-margin-right"></i>CURRICULUM</a>
                        <a id="linkcontact" href="#contact" onclick="w3_close(this)" class="w3-bar-item w3-button w3-padding">
                            <i class="fa fa-envelope fa-fw w3-margin-right"></i>CONTACTO</a>
                    </div>
                    <div class="w3-panel w3-large text-center">
                        <a class="w3-text-teal w3-hover-opacity" href="https://www.facebook.com/">
                            <i class="fa fa-facebook-official"></i>
                        </a>
                        <a class="w3-text-teal w3-hover-opacity" href="https://www.instagram.com/">
                            <i class="fa fa-instagram"></i>
                        </a>
                    </div>
                </nav>

                <!-- Overlay effect when opening sidebar on small screens -->
                <div class="w3-overlay w3-hide-large w3-animate-opacity" onclick="w3_close()" style="cursor:pointer" title="close side menu"
                    id="myOverlay"></div>

                <!-- !PAGE CONTENT! -->
                <div class="w3-main" style="margin-left:300px">

                    <c:if test="${not empty success}">
                        <div class="alert alert-success alert-dismissable">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">�</button>
                            <strong>Mensaje enviado!</strong> ${success}
                        </div>
                    </c:if>

                    <c:if test="${not empty error}">
                        <div class="alert alert-danger alert-dismissable">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">�</button>
                            <strong>Error al enviar el mensaje!</strong> ${error}
                        </div>
                    </c:if>
                    <header id="about">
                        <a href="#"></a>
                        <span class="w3-button w3-hide-large w3-xxlarge w3-hover-text-grey" onclick="w3_open()">
                            <i class="fa fa-bars"></i>
                        </span>
                        <section class="w3-row-padding w3-padding-16">
                            <figure class="w3-col m6" style="width:20%">
                                <img src="<c:url value=" /infoPhoto/${info.uuid}/1 "/>" alt="Sobre Mi" style="width:100%">
                            </figure>
                        </section>
                        <section class="w3-row-padding w3-padding-32">
                            <p>${info.description}</p>
                            ${success}
                            <hr>
                        </section>
                    </header>

                    <!-- Header -->
                    <section id="portfolio">

                        <div class="w3-container">
                            <h1>
                                <b>Obra</b>
                            </h1>

                        </div>
                    </section>
                    <hr>

                    <c:if test="${not empty familyData}">
                        <!-- First Photo Grid-->
                        <section class="w3-row-padding">

                            <c:forEach items="${familyData}" var="familyData">
                                <c:if test="${not empty familyData.images}">
                                    <a href="<c:url value=" /loadFamily "/>/${familyData.family.id}" class="w3-third w3-container w3-margin-bottom w3-margin-left container w3-hover-opacity "
                                        style="height: 300px; width: 300px; overflow: hidden">
                                        <img src="<c:url value=" /image/${familyData.family.firstImage.id} "/>" alt="${familyData.family.description}" style="overflow: hidden"
                                            class="text-center">

                                        <div class="overlay">
                                            <p class="text">
                                                <b>${familyData.family.description}</b>
                                            </p>
                                        </div>
                                    </a>
                                </c:if>
                            </c:forEach>
                        </section>
                    </c:if>
                    <!-- Images of Me -->


                    <section class="w3-container w3-padding-large" style="margin-bottom:32px">
                        <!-- Page Container -->
                        <article class="w3-content w3-margin-top w3-padding-16" style="max-width:1400px;" id="curriculum">

                            <!-- The Grid -->
                            <section class="w3-row-padding">

                                <!-- Left Column -->
                                <article class="w3-third">

                                    <section class="w3-white w3-text-grey w3-card-4">
                                        <picture class="w3-display-container">
                                            <img src="<c:url value=" /infoPhoto/${info.uuid}/2 "/>" style="width:100%" alt="Avatar">

                                        </picture>
                                        <article class="w3-container">
                                            <hr>
                                            <p>
                                                <i class="fa fa-user fa-fw w3-margin-right w3-large w3-text-teal"></i>${info.name}</p>
                                            <c:if test="${not empty info.experience[0].puesto}">
                                                <p>
                                                    <i class="fa fa-briefcase fa-fw w3-margin-right w3-large w3-text-teal"></i>${info.experience[0].puesto}</p>
                                            </c:if>
                                            <p>
                                                <i class="fa fa-home fa-fw w3-margin-right w3-large w3-text-teal"></i>${info.city}</p>
                                            <p>
                                                <i class="fa fa-envelope fa-fw w3-margin-right w3-large w3-text-teal"></i>${info.mail}</p>
                                            <p>
                                                <i class="fa fa-phone fa-fw w3-margin-right w3-large w3-text-teal"></i>${info.phone}</p>
                                            <hr>

                                            <p class="w3-large">
                                                <b>
                                                    <i class="fa fa-asterisk fa-fw w3-margin-right w3-text-teal"></i>Aptitudes</b>
                                            </p>
                                            <c:forEach items="${info.skills}" var="skill">
                                                <c:if test="${skill.isSkill}">
                                                    <p>${skill.description}</p>
                                                    <section class="w3-light-grey w3-round-xlarge w3-small">
                                                        <div class="w3-container w3-center w3-round-xlarge w3-teal" style="width:${skill.percent}%">${skill.percent}%</div>
                                                    </section>
                                                </c:if>
                                            </c:forEach>
                                            <br>

                                            <p class="w3-large w3-text-theme">
                                                <b>
                                                    <i class="fa fa-globe fa-fw w3-margin-right w3-text-teal"></i>Idiomas</b>
                                            </p>
                                            <c:forEach items="${info.skills}" var="skill">
                                                <c:if test="${not skill.isSkill}">
                                                    <p>${skill.description}</p>
                                                    <section class="w3-light-grey w3-round-xlarge">
                                                        <div class="w3-round-xlarge w3-teal" style="height:24px;width:${skill.percent}%"></div>
                                                    </section>
                                                </c:if>
                                            </c:forEach>


                                            <br>
                                        </article>
                                    </section>
                                    <br>

                                    <!-- End Left Column -->
                                </article>

                                <!-- Right Column -->
                                <article class="w3-twothird">

                                    <section class="w3-container w3-card-2 w3-white w3-margin-bottom">
                                        <h2 class="w3-text-grey w3-padding-16">
                                            <i class="fa fa-suitcase fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>Experiencia</h2>
                                        <c:forEach items="${info.experience}" var="experience">
                                            <article class="w3-container">
                                                <h5 class="w3-opacity">
                                                    <b>${experience.puesto} / ${experience.empresa}</b>
                                                </h5>
                                                <h6 class="w3-text-teal">
                                                    <i class="fa fa-calendar fa-fw w3-margin-right"></i>
                                                    <fmt:formatDate pattern="MM/yyyy" value="${experience.from}" /> -
                                                    <c:choose>
                                                        <c:when test="${empty experience.to}">Actualidad</c:when>
                                                        <c:otherwise>
                                                            <fmt:formatDate pattern="MM/yyyy" value="${experience.to}" />
                                                        </c:otherwise>
                                                    </c:choose>
                                                </h6>
                                                <p>${experience.description}</p>
                                                <hr>
                                            </article>
                                        </c:forEach>
                                    </section>

                                    <section class="w3-container w3-card-2 w3-white">
                                        <h2 class="w3-text-grey w3-padding-16">
                                            <i class="fa fa-certificate fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>Educaci�n</h2>
                                        <c:forEach items="${info.education}" var="education">
                                            <article class="w3-container">
                                                <h5 class="w3-opacity">
                                                    <b>${education.place}</b>
                                                </h5>
                                                <h6 class="w3-text-teal">
                                                    <i class="fa fa-calendar fa-fw w3-margin-right"></i>
                                                    <fmt:formatDate pattern="MM/yyyy" value="${education.from}" /> -
                                                    <c:choose>
                                                        <c:when test="${empty education.to}">Actualidad</c:when>
                                                        <c:otherwise>
                                                            <fmt:formatDate pattern="MM/yyyy" value="${education.to}" />
                                                        </c:otherwise>
                                                    </c:choose>
                                                </h6>
                                                <p>${education.description}</p>
                                                <hr>
                                            </article>
                                        </c:forEach>
                                    </section>

                                    <!-- End Right Column -->
                                </article>

                                <!-- End Grid -->
                            </section>

                            <!-- End Page Container -->
                        </article>


                    </section>

                    <!-- Contact Section -->
                    <section class="w3-container w3-padding-large w3-grey">
                        <h4 id="contact">
                            <b>Contacto</b>
                        </h4>
                        <article class="w3-row-padding w3-center w3-padding-24" style="margin:0 -16px">
                            <section class="w3-third w3-dark-grey">
                                <p>
                                    <i class="fa fa-envelope w3-xxlarge w3-text-light-grey"></i>
                                </p>
                                <p>${info.mail}</p>
                            </section>
                            <section class="w3-third w3-teal">
                                <p>
                                    <i class="fa fa-map-marker w3-xxlarge w3-text-light-grey"></i>
                                </p>
                                <p>${info.city}</p>
                            </section>
                            <section class="w3-third w3-dark-grey">
                                <p>
                                    <i class="fa fa-phone w3-xxlarge w3-text-light-grey"></i>
                                </p>
                                <p>
                                    <c:choose>
                                        <c:when test="${not empty info.phone}">
                                            ${info.phone}
                                        </c:when>
                                        <c:otherwise>
                                            Solicitar por privado
                                        </c:otherwise>
                                    </c:choose>
                                </p>
                            </section>
                        </article>
                        <hr class="w3-opacity">
                        <form action="<c:url value=" /sendMail " />" method="get">
                            <section class="w3-section">
                                <label>Nombre</label>
                                <input class="w3-input w3-border" type="text" name="nombre" placeholder="Tu nombre" required>
                            </section>
                            <section class="w3-section">
                                <label>E-Mail</label>
                                <input class="w3-input w3-border" type="mail" name="email" placeholder="Tu correo para poder contactarte" required>
                            </section>
                            <section class="w3-section">
                                <label>Mensaje</label>
                                <textarea class="w3-input w3-border" name="comentario" placeholder="Escr�beme tu duda" required></textarea>
                            </section>
                            <div class="g-recaptcha" data-sitekey="6Lcw-CoUAAAAAMEez_K4T-6T3VdDpwrRKc8zLcR0"></div>
                            <button type="submit" class="w3-button w3-black w3-margin-bottom">
                                <i class="fa fa-paper-plane w3-margin-right"></i>ENVIAR MENSAJE</button>
                        </form>

                    </section>

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
                                <a class="w3-text-teal w3-hover-opacity" href="https://www.facebook.com/">
                                    <i class="fa fa-facebook-official fa-3x w3-hover-opacity"></i>
                                </a>
                                <a class="w3-text-teal w3-hover-opacity" href="https://www.instagram.com/">
                                    <i class="fa fa-instagram fa-3x w3-hover-opacity"></i>
                                </a>
                            </article>

                        </section>
                    </footer>

                    <section class="w3-black w3-center w3-padding-24">
                        &copy; Realizado por
                        <a class="w3-text-teal w3-hover-opacity" href="https://es.linkedin.com/in/joaqu%C3%ADn-roiz-pagador-87050126" title="Quini Roiz"
                            target="Quini Roiz">Quini Roiz
                        </a>
                    </section>

                    <!-- End page content -->
                </div>
                <script src="<c:url value=" /resources/js/jquery/jquery.min.js "/>"></script>
                <script src="<c:url value=" /resources/js/plugins/bootstrap/bootstrap.min.js "/>"></script>
                <script src="<c:url value=" /resources/js/user.min.js "/>"></script>
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
                                url: "<c:url value=" / geoLocation"/>/" + location.country_name
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