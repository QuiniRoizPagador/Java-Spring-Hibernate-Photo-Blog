<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Damma Persalt</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="<c:url value="/resources/css/estilos.min.css" />">
        <link rel="stylesheet" href="<c:url value="/resources/css/plugins/bootstrap/bootstrap.min.css" />">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="shortcut icon" href="<c:url value="/resources/images/favicon.ico"/>">
        <style>
            body,h1,h2,h3,h4,h5,h6 {font-family: "Raleway", sans-serif}
        </style>
        <script src="<c:url value="/resources/js/paging.js"/>" ></script>
    </head>
    <body class="w3-light-grey">
        <sec:authentication var="usulogeado" property="principal" />
        <!-- Top container -->
        <div class="w3-bar w3-top w3-black w3-large" style="z-index:4">
            <button class="w3-bar-item w3-button w3-hide-large w3-hover-none w3-hover-text-light-grey" onclick="w3_open();"><i class="fa fa-bars"></i>  Menu</button>
            <a class="w3-text-leal w3-hover-opacity" href="<c:url value="/logout"/>"><span class="w3-bar-item w3-right">Logout</span></a>
        </div>

        <!-- Sidebar/menu -->
        <nav class="w3-sidebar w3-collapse w3-white w3-animate-left" style="z-index:3;width:300px;" id="mySidebar"><br>
            <div class="w3-container w3-row">
                <div class="w3-col s4">
                    <a href="${pageContext.request.contextPath}"><img src="<c:url value="/infoPhoto/${info.uuid}/1"/>" class="w3-circle w3-margin-right" style="width:46px"></a>
                </div>
                <div class="w3-col s8 w3-bar">
                    <span>Bienvenid@, <strong>${usulogeado.username}</strong></span><br>
                </div>
            </div>
            <hr>
            <div class="w3-container">
                <h4>Acceso Rápido</h4>
            </div>
            <div class="w3-bar-block">
                <a href="#" class="w3-bar-item w3-button w3-padding-16 w3-hide-large w3-dark-grey w3-hover-black" onclick="w3_close()" title="close menu"><i class="fa fa-remove fa-fw"></i>  Close Menu</a>
                <a href="#images" class="w3-bar-item w3-button w3-padding"><i class="fa fa-picture-o fa-fw"></i>  Listado de fotos</a>
                <a href="#families" class="w3-bar-item w3-button w3-padding "><i class="fa fa-users fa-fw"></i>  Listado de Familias de fotos</a>
                <a href="#info" class="w3-bar-item w3-button w3-padding "><i class="fa fa-database fa-fw"></i>  Información de la web</a>
                <a href="#skills" class="w3-bar-item w3-button w3-padding "><i class="fa fa-language fa-fw"></i>  Aptitudes e Idiomas</a>
                <a href="#experience" class="w3-bar-item w3-button w3-padding "><i class="fa fa-suitcase fa-fw"></i>  Experiencia</a>
                <a href="#education" class="w3-bar-item w3-button w3-padding "><i class="fa fa-graduation-cap fa-fw"></i>  Educación</a>
                <a href="#countries" class="w3-bar-item w3-button w3-padding "><i class="fa fa-eye fa-fw"></i>  Visitas</a>
                <a href="#password" class="w3-bar-item w3-button w3-padding "><i class="fa fa-eye fa-key"></i>  Contraseña</a>
            </div>
        </nav>


        <!-- Overlay effect when opening sidebar on small screens -->
        <div class="w3-overlay w3-hide-large w3-animate-opacity" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

        <!-- !PAGE CONTENT! -->
        <div class="w3-main" style="margin-left:300px;margin-top:43px;">

            <!-- Header -->
            <header class="w3-container" style="padding-top:22px">
                <h5><b><i class="fa fa-dashboard"></i> Zona de Administración</b></h5>
            </header>

            <hr>

            <div id="images" class="w3-row-padding w3-margin-bottom">

                <h1>Imágenes</h1>
                <hr>
                <button class="btn btn-success" data-toggle="modal" class="btn btn-success" href="#addPhoto">Añadir Imagen</button>
                <hr>
                <div class="table table-responsive">

                    <table <c:if test="${not empty images}">id="results"</c:if> class="table table-responsive table-striped">
                            <thead>
                                <tr>
                                    <th>
                                        ID
                                    </th>
                                    <th>
                                        Titulo
                                    </th>
                                    <th>
                                        Descripción
                                    </th>
                                    <th align="center">
                                        Imágen
                                    </th>
                                    <th>
                                        Familia
                                    </th>
                                    <th>
                                        Acción
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${images}" var="imagen">
                                <sf:form method="post" action="updateImage?${_csrf.parameterName}=${_csrf.token}" modelAttribute="photo" enctype="multipart/form-data">
                                    <tr>
                                        <td>
                                            <div name="id">${imagen.id}</div>
                                            <input type="hidden" value="${imagen.uuid}" name="uuid"/>
                                        </td>
                                        <td>
                                            <input required="" name="title" value="${imagen.title}"/>
                                        </td>
                                        <td>
                                            <textarea required="" name="description">${imagen.description}</textarea>
                                        </td>
                                        <td align="center">
                                            <img data-toggle="modal" data-target="#fotos" src="<c:url value="/photo/${imagen.uuid}"/>" alt="${image.title}" style="width:90px; height: 90px" class="img-responsive">
                                            <hr>
                                            <input id="foto" name="foto" type="file" 
                                                   accept="image/x-png, image/gif, image/jpeg" >
                                        </td>
                                        <td>
                                            <select class="form-control" id="family_id" name="family_id"
                                                    required="required">
                                                <c:forEach items="${families}" var="family">
                                                    <option value="${family.id}" <c:if test="${family.id eq imagen.family.id}"> selected </c:if>>${family.description}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td>
                                            <input class="btn btn-info btn-xs" type="submit" value="Update" />
                                            <a data-toggle="modal"
                                               href="#delete${imagen.uuid}" class="btn btn-danger btn-xs"><span
                                                    class="fa fa-remove"></span> </a>
                                        </td>
                                    </tr>
                                </sf:form>
                            <div id="delete${imagen.uuid}" class="modal fade in">
                                <div class="modal-dialog">
                                    <div class="modal-content">

                                        <div class="modal-header">
                                            <a class="btn btn-default" data-dismiss="modal"><span
                                                    class="fa fa-remove"></span></a>
                                            <h4 class="modal-title"><s:message code="borrar" /></h4>
                                        </div>
                                        <div class="modal-body">
                                            <h4>Eliminar Imagen</h4>
                                            <p>¿Estás seguro de querer eliminar esta imagen?</p>
                                        </div>
                                        <div class="modal-footer">
                                            <div class="btn-group">
                                                <button class="btn btn-danger" data-dismiss="modal">
                                                    <span class="fa fa-remove"></span> <s:message code="cancelar" />
                                                </button>

                                                <a href="<c:url value="/deleteImage/${imagen.uuid}"/>"
                                                   class="btn btn-primary"> <span
                                                        class="fa fa-check"></span> <s:message code="borrar" />
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /.modal-content -->
                                </div>
                                <!-- /.modal-dialog -->
                            </div> <!-- /.modal --> 
                        </c:forEach>
                        </tbody>
                    </table>
                    <div align="middle" id="pageNavPosition"> 
                    </div>
                </div>

                <div id="addPhoto" class="modal fade in">
                    <div class="modal-dialog">
                        <div class="modal-content">

                            <div class="modal-header text-right">
                                <a class="btn btn-default" data-dismiss="modal"><span
                                        class="fa fa-remove"></span></a>
                                <h4>
                                    Guardar Nueva Imagen
                                </h4>
                            </div>
                            <div class="modal-body">
                                <div class="container">
                                    <div class="col-xs-12 col-sm-8 col-md-4">
                                        <sf:form modelAttribute="photo" enctype="multipart/form-data" id="imgForm" role="form" action="savePhoto?${_csrf.parameterName}=${_csrf.token}" method="POST" >

                                            <br style="clear: both">


                                            <div class="form-group">
                                                <label for="Titulo">
                                                    <h4>
                                                        Título
                                                    </h4>
                                                </label> 
                                                <sf:input path="title" type="text" step="0.1" class="form-control"
                                                          id="title" name="title" placeholder="Título"
                                                          required="required"/> 
                                            </div>
                                            <div class="form-group">
                                                <label for="Descripción">
                                                    <h4>
                                                        Descripción
                                                    </h4>
                                                </label> 
                                                <sf:textarea path="description" class="form-control"
                                                             id="description" name="description" placeholder="Descripción"
                                                             required="required" form="imgForm"/>
                                            </div>

                                            <div class="form-group">
                                                <label for="imagen">
                                                    <h4>
                                                        Imagen
                                                    </h4>
                                                </label> 
                                                <input id="foto" name="foto" type="file" required="required"
                                                       accept="image/x-png, image/gif, image/jpeg" >
                                            </div>

                                            <div class="form-group">
                                                <label for="imagen">Familia de la Imagen</label> 
                                                <select class="form-control" id="family_id" name="family_id"
                                                        required="required" >
                                                    <c:forEach items="${families}" var="family">
                                                        <option value="${family.id}">${family.description}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>


                                            <div class="btn-group">

                                                <sf:button type="submit" id="submit" name="submit"
                                                           class="btn btn-default pull-right">Guardar Imagen</sf:button>

                                                </div>
                                        </sf:form>

                                    </div>
                                </div>
                            </div>

                        </div>
                        <!-- /.modal-content -->
                    </div>
                    <!-- /.modal-dialog -->
                </div>


                <div id="families" class="w3-row-padding w3-margin-bottom">
                    <h1>Familias de Imágenes</h1>
                    <hr>
                    <div class="table table-responsive">
                        <table id="myTable" class="table table-responsive table-striped">
                            <thead>
                                <tr>
                                    <th>
                                        ID
                                    </th>
                                    <th>
                                        Descripcion
                                    </th>
                                    <th>
                                        Imagen de Portada
                                    </th>
                                    <th>
                                        Acción
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${families}" var="family">
                                    <sf:form modelAttribute="family" enctype="multipart/form-data" role="form" action="updatefamily?${_csrf.parameterName}=${_csrf.token}" method="POST" >
                                        <tr>
                                            <td>
                                                <div>${family.id}</div>
                                                <input type="hidden"path="id" id="id" alt="id" name="id" value="${family.id}" readOnly=true/>
                                            </td>
                                            <td>
                                                <input style="text-transform:uppercase" required="" name="description" value="${family.description}"/>
                                            </td>
                                            <td align="center">
                                                <img data-toggle="modal" data-target="#fotos" src="<c:url value="/image/${family.firstImage.id}"/>" alt="${image.title}" style="width:90px; height: 90px" class="img-responsive">
                                                <hr>
                                                <input id="foto" name="foto" type="file" required="required"
                                                       accept="image/x-png, image/gif, image/jpeg" >
                                            </td>

                                            <td><input class="btn btn-info btn-xs" type="submit" value="Update" />
                                                <a data-toggle="modal"
                                                   href="#deletefamily${family.id}" class="btn btn-danger btn-xs"><span
                                                        class="fa fa-remove"></span> </a>
                                            </td>
                                        </tr>
                                    </sf:form>
                                <div id="deletefamily${family.id}" class="modal fade in">
                                    <div class="modal-dialog">
                                        <div class="modal-content">

                                            <div class="modal-header">
                                                <a class="btn btn-default" data-dismiss="modal"><span
                                                        class="fa fa-remove"></span></a>
                                                <h4 class="modal-title"><s:message code="borrar" /></h4>
                                            </div>
                                            <div class="modal-body">
                                                <h4>Eliminar Familia</h4>
                                                <p>¿Estás seguro de querer eliminar esta familia?</p>
                                            </div>
                                            <div class="modal-footer">
                                                <div class="btn-group">
                                                    <button class="btn btn-danger" data-dismiss="modal">
                                                        <span class="fa fa-remove"></span> <s:message code="cancelar" />
                                                    </button>

                                                    <a href="<c:url value="/deleteFamily/${family.id}"/>"
                                                       class="btn btn-primary"> <span
                                                            class="fa fa-check"></span> <s:message code="borrar" />
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- /.modal-content -->
                                    </div>
                                    <!-- /.modal-dialog -->
                                </div> <!-- /.modal --> 
                            </c:forEach>
                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>

                            <sf:form modelAttribute="family" enctype="multipart/form-data" role="form" action="savefamily?${_csrf.parameterName}=${_csrf.token}" method="POST" >
                                <tr>
                                    <td>

                                    </td>
                                    <td>
                                        <input style="text-transform:uppercase" required="" name="description" value=""/>
                                    </td>
                                    <td>
                                        <input id="foto" name="foto" type="file" required="required"
                                               accept="image/x-png, image/gif, image/jpeg" >
                                    </td>

                                    <td>
                                        <button type="submit" id="submit" name="submit"
                                                class="btn btn-info pull-right">Añadir</button>
                                    </td>
                                </tr>
                            </sf:form>
                            </tbody>
                        </table>
                    </div>
                </div>


                <div id="info" class="w3-row-padding w3-margin-bottom">
                    <h1>Información de la web</h1>
                    <hr>


                    <div class="table table-responsive">
                        <sf:form enctype="multipart/form-data" method="post" action="updateInfo?${_csrf.parameterName}=${_csrf.token}" modelAttribute="info">

                            <sf:hidden path="uuid" />
                            <sf:hidden path="id" />

                            <br style="clear: both"> <br>

                            <div class="col-md-6">

                                <div class="form-group col-lg-12">
                                    <img class="img-responsive text-center" src="<c:url value="/infoPhoto/${info.uuid}/1"/>" width="50%" height="50%"/>
                                    <hr>
                                    <label class="label label-pill label-default"> 
                                        Imagen de perfil 1
                                    </label> 
                                    <input id="profile1" name="profile1" type="file"
                                           accept="image/x-png, image/gif, image/jpeg" >
                                </div>
                                <hr>
                                <div class="form-group col-lg-12">
                                    <label class="label label-pill label-default"> 
                                        Nombre
                                    </label> 
                                    <sf:input path="name" type="text" class="form-control" id="name" name="name"
                                              placeholder="Nombre" required="required"
                                              maxlength="50" />
                                </div>

                                <div class="form-group col-lg-12">
                                    <label class="label label-pill label-default"> 
                                        E-Mail
                                    </label> 
                                    <sf:input path="mail" type="email" class="form-control" id="mail" name="mail"
                                              placeholder="example@mail.com" required="required" maxlength="50" minlength="6"/>
                                </div>

                                <div class="form-group col-lg-12">
                                    <label class="label label-pill label-default"> 
                                        Ciudad
                                    </label> 
                                    <sf:input path="city" type="text" class="form-control" id="city" name="city"
                                              placeholder="ciudad" required="required" maxlength="50" minlength="6"/>
                                </div>

                            </div>
                            <div class="col-md-6">
                                <div class="form-group col-lg-12">
                                    <img class="img-responsive text-center" src="<c:url value="/infoPhoto/${info.uuid}/2"/>" width="50%" height="50%"/>
                                    <hr>
                                    <label class="label label-pill label-default"> 
                                        Imagen de perfil 2
                                    </label> 
                                    <input id="profile2" name="profile2" type="file" 
                                           accept="image/x-png, image/gif, image/jpeg" >
                                </div>
                                <hr>
                                <div class="form-group col-lg-12">
                                    <label class="label label-pill label-default"> 
                                        Teléfono
                                    </label> 
                                    <sf:input path="phone" type="tel" class="form-control" id="phone" name="phone"
                                              placeholder="666666666" required="required" maxlength="50" minlength="6"/>
                                </div>
                                <div class="form-group col-lg-12">
                                    <label class="label label-pill label-default"> 
                                        Descripción
                                    </label> 
                                    <sf:textarea path="description" class="form-control" id="description" name="description"
                                                 placeholder="Descripción" required="required" maxlength="500" minlength="6"/>
                                </div>
                                <div class="form-group col-lg-12">
                                    <label class="label label-pill label-default"> 
                                        Footer
                                    </label> 
                                    <sf:textarea path="footer" class="form-control" id="footer" name="footer"
                                                 placeholder="footer" required="required" maxlength="500" minlength="6"/>
                                </div>

                                <sf:button class="btn btn-primary pull-right"><span class="fa fa-save"></span> <s:message code="save" /></sf:button>

                                </div>
                        </sf:form>
                    </div>
                </div>

                <div id="skills" class="w3-row-padding w3-margin-bottom">
                    <h1>Aptitudes e Idiomas</h1>
                    <hr>
                    <div class="table table-responsive">
                        <table id="myTable" class="table table-responsive table-striped">
                            <thead>
                                <tr>
                                    <th>
                                        Descripción
                                    </th>
                                    <th>
                                        Porcentaje
                                    </th>
                                    <th>
                                        Es Aptitud
                                    </th>
                                    <th>
                                        Acción
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${info.skills}" var="skill">
                                    <sf:form modelAttribute="skill" role="form" action="updateSkill?${_csrf.parameterName}=${_csrf.token}" method="POST" >
                                        <tr>
                                            <td>
                                                <input type="text" name="description" path="description" value="${skill.description}" required=""/>
                                                <sf:hidden path="id" name="id" value="${skill.id}"/>
                                            </td>
                                            <td>
                                                <input type="range" min="1" max="100" name="percent" value="${skill.percent}" required="">
                                            </td>
                                            <td>
                                                <input type="checkbox" class="form-check-input" name="isSkill" path="isSkill" <c:if test="${skill.isSkill}">checked</c:if> >
                                                </td>

                                                <td><input class="btn btn-info btn-xs" type="submit" value="Update" />
                                                    <a data-toggle="modal"
                                                       href="#deleteSkill${skill.id}" class="btn btn-danger btn-xs"><span
                                                        class="fa fa-remove"></span> </a></td>
                                        </tr>
                                    </sf:form>
                                <div id="deleteSkill${skill.id}" class="modal fade in">
                                    <div class="modal-dialog">
                                        <div class="modal-content">

                                            <div class="modal-header">
                                                <a class="btn btn-default" data-dismiss="modal"><span
                                                        class="fa fa-remove"></span></a>
                                                <h4 class="modal-title"><s:message code="borrar" /></h4>
                                            </div>
                                            <div class="modal-body">
                                                <h4>Eliminar Aptitud</h4>
                                                <p>¿Estás seguro de querer eliminar esta aptitud?</p>
                                            </div>
                                            <div class="modal-footer">
                                                <div class="btn-group">
                                                    <button class="btn btn-danger" data-dismiss="modal">
                                                        <span class="fa fa-remove"></span> <s:message code="cancelar" />
                                                    </button>

                                                    <a href="<c:url value="/deleteSkill/${skill.id}"/>"
                                                       class="btn btn-primary"> <span
                                                            class="fa fa-check"></span> <s:message code="borrar" />
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- /.modal-content -->
                                    </div>
                                    <!-- /.modal-dialog -->
                                </div> <!-- /.modal --> 
                            </c:forEach>
                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>

                            <sf:form modelAttribute="skill" role="form" action="saveSkill?${_csrf.parameterName}=${_csrf.token}" method="POST" >
                                <tr>
                                    <td>
                                        <input type="text" name="description" path="description" value="" required=""/>
                                    </td>
                                    <td>
                                        <input type="range" min="1" max="100" name="percent" value="0" required="">
                                    </td>
                                    <td>
                                        <input type="checkbox" class="form-check-input" name="isSkill" path="isSkill">
                                    </td>

                                    <td>
                                        <sf:button class="btn btn-primary pull-right"><span class="fa fa-save"></span> <s:message code="save" /></sf:button>
                                    </td>
                                    </tr>
                            </sf:form>
                            </tbody>
                        </table>
                    </div>
                </div>

                <div id="experience" class="w3-row-padding w3-margin-bottom">
                    <h1>Experiencia</h1>
                    <hr>
                    <div class="table table-responsive">
                        <table id="myTable" class="table table-responsive table-striped">
                            <thead>
                                <tr>
                                    <th>
                                        Empresa
                                    </th>
                                    <th>
                                        Puesto
                                    </th>

                                    <th>
                                        Desde
                                    </th>
                                    <th>
                                        Hasta
                                    </th>
                                    <th>
                                        Descripción
                                    </th>

                                    <th>
                                        Acción
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${info.experience}" var="experience">
                                    <sf:form modelAttribute="experience" role="form" action="updateExperience?${_csrf.parameterName}=${_csrf.token}" method="POST" >
                                        <tr>
                                            <td>
                                                <input type="text" name="empresa" path="empresa" value="${experience.empresa}" required=""/>
                                            </td>
                                            <td>
                                                <input type="text" name="puesto" path="puesto" value="${experience.puesto}" required=""/>
                                                <sf:hidden path="id" name="id" value="${experience.id}"/>  
                                            </td>

                                            <td>
                                                <input name="from" path="from" class="form-control" type="month" value="<fmt:formatDate pattern="yyyy-MM" value="${experience.from}" />" required="">
                                            </td>
                                            <td>
                                                <input name="to" path="to" class="form-control" type="month" value="<fmt:formatDate pattern="yyyy-MM" value="${experience.to}" />">
                                            </td>
                                            <td>
                                                <textarea path="description" class="form-control"
                                                          id="description" name="description" placeholder="Descripción"
                                                          required="required">${experience.description}</textarea>
                                            </td>

                                            <td><input class="btn btn-info btn-xs" type="submit" value="Update" />
                                                <a data-toggle="modal"
                                                   href="#deleteExperience${education.id}" class="btn btn-danger btn-xs"><span
                                                        class="fa fa-remove"></span> </a></td>
                                        </tr>
                                    </sf:form>
                                <div id="deleteExperience${skill.id}" class="modal fade in">
                                    <div class="modal-dialog">
                                        <div class="modal-content">

                                            <div class="modal-header">
                                                <a class="btn btn-default" data-dismiss="modal"><span
                                                        class="fa fa-remove"></span></a>
                                                <h4 class="modal-title"><s:message code="borrar" /></h4>
                                            </div>
                                            <div class="modal-body">
                                                <h4>Eliminar Experiencia</h4>
                                                <p>¿Estás seguro de querer eliminar esta Experiencia?</p>
                                            </div>
                                            <div class="modal-footer">
                                                <div class="btn-group">
                                                    <button class="btn btn-danger" data-dismiss="modal">
                                                        <span class="fa fa-remove"></span> <s:message code="cancelar" />
                                                    </button>

                                                    <a href="<c:url value="/deleteExperience/${experience.id}"/>"
                                                       class="btn btn-primary"> <span
                                                            class="fa fa-check"></span> <s:message code="borrar" />
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- /.modal-content -->
                                    </div>
                                    <!-- /.modal-dialog -->
                                </div> <!-- /.modal --> 
                            </c:forEach>
                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td
                                <td></td>
                            </tr>

                            <sf:form modelAttribute="experience" role="form" action="saveExperience?${_csrf.parameterName}=${_csrf.token}" method="POST" >
                                <tr>
                                    <td>
                                        <input type="text" name="empresa" path="empresa" value="" required=""/>
                                    </td>
                                    <td>
                                        <input type="text" name="puesto" path="puesto" value="" required=""/>
                                        <sf:hidden path="id" name="id" value=""/>  
                                    </td>

                                    <td>
                                        <input name="from" path="from" class="form-control" type="month" value="" required="">
                                    </td>
                                    <td>
                                        <input name="to" path="to" class="form-control" type="month" value="">
                                    </td>
                                    <td>
                                        <textarea path="description" class="form-control"
                                                  id="description" name="description" placeholder="Descripción"
                                                  required="required"></textarea>
                                    </td>

                                    <td>
                                        <sf:button class="btn btn-primary pull-right"><span class="fa fa-save"></span> <s:message code="save" /></sf:button>
                                    </td>

                                    </tr>
                            </sf:form>
                            </tbody>
                        </table>
                    </div>
                </div>

                <div id="education" class="w3-row-padding w3-margin-bottom">
                    <h1>Educación</h1>
                    <hr>
                    <div class="table table-responsive">
                        <table id="myTable" class="table table-responsive table-striped">
                            <thead>
                                <tr>
                                    <th>
                                        Institución
                                    </th>
                                    <th>
                                        Desde
                                    </th>
                                    <th>
                                        Hasta
                                    </th>
                                    <th>
                                        Descripción
                                    </th>

                                    <th>
                                        Acción
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${info.education}" var="education">
                                    <sf:form modelAttribute="education" role="form" action="updateEducation?${_csrf.parameterName}=${_csrf.token}" method="POST" >
                                        <tr>
                                            <td>
                                                <input type="text" name="place" path="place" value="${education.place}" required=""/>
                                                <sf:hidden path="id" name="id" value="${education.id}"/>  
                                            </td>

                                            <td>
                                                <input name="from" path="from" class="form-control" type="month" value="<fmt:formatDate pattern="yyyy-MM" value="${education.from}" />" required="">
                                            </td>
                                            <td>
                                                <input name="to" path="to" class="form-control" type="month" value="<fmt:formatDate pattern="yyyy-MM" value="${education.to}" />">
                                            </td>
                                            <td>
                                                <textarea path="description" class="form-control"
                                                          id="description" name="description" placeholder="Descripción"
                                                          required="required">${education.description}</textarea>
                                            </td>

                                            <td><input class="btn btn-info btn-xs" type="submit" value="Update" />
                                                <a data-toggle="modal"
                                                   href="#deleteEducation${education.id}" class="btn btn-danger btn-xs"><span
                                                        class="fa fa-remove"></span> </a></td>
                                        </tr>
                                    </sf:form>
                                <div id="deleteEducation${education.id}" class="modal fade in">
                                    <div class="modal-dialog">
                                        <div class="modal-content">

                                            <div class="modal-header">
                                                <a class="btn btn-default" data-dismiss="modal"><span
                                                        class="fa fa-remove"></span></a>
                                                <h4 class="modal-title"><s:message code="borrar" /></h4>
                                            </div>
                                            <div class="modal-body">
                                                <h4>Eliminar Educación</h4>
                                                <p>¿Estás seguro de querer eliminar esta Educación?</p>
                                            </div>
                                            <div class="modal-footer">
                                                <div class="btn-group">
                                                    <button class="btn btn-danger" data-dismiss="modal">
                                                        <span class="fa fa-remove"></span> <s:message code="cancelar" />
                                                    </button>

                                                    <a href="<c:url value="/deleteEducation/${education.id}"/>"
                                                       class="btn btn-primary"> <span
                                                            class="fa fa-check"></span> <s:message code="borrar" />
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- /.modal-content -->
                                    </div>
                                    <!-- /.modal-dialog -->
                                </div> <!-- /.modal --> 
                            </c:forEach>
                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td
                                <td></td>
                            </tr>

                            <sf:form modelAttribute="education" role="form" action="saveEducation?${_csrf.parameterName}=${_csrf.token}" method="POST" >
                                <tr>
                                    <td>
                                        <input type="text" name="place" path="place" value="${education.place}" required=""/>
                                        <sf:hidden path="id" name="id" value="${education.id}"/>  
                                    </td>

                                    <td>
                                        <input name="from" path="from" class="form-control" type="month" value="<fmt:formatDate pattern="yyyy-MM" value="${experience.from}" />" required="">
                                    </td>
                                    <td>
                                        <input name="to" path="to" class="form-control" type="month" value="<fmt:formatDate pattern="yyyy-MM" value="${experience.to}" />">
                                    </td>
                                    <td>
                                        <textarea path="description" class="form-control"
                                                  id="description" name="description" placeholder="Descripción"
                                                  required="required">${experience.description}</textarea>
                                    </td>

                                    <td>
                                        <sf:button class="btn btn-primary pull-right"><span class="fa fa-save"></span> <s:message code="save" /></sf:button>
                                    </td>

                                    </tr>
                            </sf:form>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div id="countries" class="w3-container">
                    <h5>Visitas</h5>
                    <table class="w3-table w3-striped w3-bordered w3-border w3-hoverable w3-white">
                        <c:forEach var="geo" items="${geos}" varStatus="status">
                            <c:if test="${geo.key ne 0}">
                                <tr>
                                    <td>${geo.value}</td>
                                    <td>${geo.key}</td>
                                </tr>
                            </c:if>

                        </c:forEach>
                        <tr>
                            <td><b>Total</b></td>
                            <td>${geoTotal}</td>
                        </tr>
                    </table>
                    <br>

                </div>

                <div id="password" class="w3-container">
                    <h1><i class="fa fa-key"></i> Actualizar Contraseña</h1>
                    <form role="form" action="updatePassword?${_csrf.parameterName}=${_csrf.token}" method="POST" onsubmit="return validate(this);">
                        <div class="form-group col-md-12">
                            <div class="col-md-6">
                                <input type="password" class="form-control" id="password1" required=""
                                       name="password1" placeholder="Introduce la contraseña"
                                       maxlength="50" minlength="5" onkeyup="return checkPass();"/> 
                            </div>

                            <div class="col-md-6">
                                <input type="password" class="form-control" id="password2" required=""
                                       name="password2" placeholder="Repite la contraseña"
                                       maxlength="50" minlength="5" onkeyup="return checkPass();"/> 
                            </div>
                            <div class="form-group col-lg-12" align="center">
                                <span id="confirmMessage" class="confirmMessage"></span>
                            </div>
                            <input type="submit" class="btn btn-info" value="Update"/>
                        </div>
                    </form>
                    <br>

                </div>
                <footer class="w3-container w3-padding-16 w3-light-grey">
                    <p>&copy;Realizado por <a class="w3-text-teal w3-hover-opacity" href="https://es.linkedin.com/in/joaqu%C3%ADn-roiz-pagador-87050126" target="Quini Roiz">Quini Roiz</a></p>
                </footer>
            </div>
            <script src="<c:url value="/resources/js/jquery/jquery.min.js"/>"></script>

            <script src="<c:url value="/resources/js/plugins/bootstrap/bootstrap.min.js"/>" ></script>


            <script>
                <c:if test="${not empty images}">
                                           var pager = new Pager("results", 3);
                                           pager.init(), pager.showPageNav("pager", "pageNavPosition"), pager.showPage(1);
                </c:if>
                                           function w3_open() {
                                               "block" === mySidebar.style.display ? (mySidebar.style.display = "none", overlayBg.style.display = "none") : (mySidebar.style.display = "block", overlayBg.style.display = "block")
                                           }
                                           function w3_close() {
                                               mySidebar.style.display = "none", overlayBg.style.display = "none"
                                           }
                                           function checkPass() {
                                               var e = document.getElementById("password1"), n = document.getElementById("password2"), o = document.getElementById("confirmMessage"), a = "#66cc66", l = "#ff6666";
                                               e.value == n.value ? (n.style.backgroundColor = a, e.style.backgroundColor = a, o.style.color = a, o.innerHTML = "Las contraseñas coinciden!") : (n.style.backgroundColor = l, o.style.color = l, o.innerHTML = "Las contraseñas no coinciden!")
                                           }
                                           function validate(e) {
                                               var n = e.elements;
                                               return n.password.value != n.password2.value ? (alert("Las contraseñas no coinciden. Inténtalo de nuevo."), !1) : !0
                                           }
                                           var mySidebar = document.getElementById("mySidebar"), overlayBg = document.getElementById("myOverlay");
            </script>

    </body>
</html>
