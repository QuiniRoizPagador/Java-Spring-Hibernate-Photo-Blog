<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>
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
            body { background-image: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABoAAAAaCAYAAACpSkzOAAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAALEgAACxIB0t1+/AAAABZ0RVh0Q3JlYXRpb24gVGltZQAxMC8yOS8xMiKqq3kAAAAcdEVYdFNvZnR3YXJlAEFkb2JlIEZpcmV3b3JrcyBDUzVxteM2AAABHklEQVRIib2Vyw6EIAxFW5idr///Qx9sfG3pLEyJ3tAwi5EmBqRo7vHawiEEERHS6x7MTMxMVv6+z3tPMUYSkfTM/R0fEaG2bbMv+Gc4nZzn+dN4HAcREa3r+hi3bcuu68jLskhVIlW073tWaYlQ9+F9IpqmSfq+fwskhdO/AwmUTJXrOuaRQNeRkOd5lq7rXmS5InmERKoER/QMvUAPlZDHcZRhGN4CSeGY+aHMqgcks5RrHv/eeh455x5KrMq2yHQdibDO6ncG/KZWL7M8xDyS1/MIO0NJqdULLS81X6/X6aR0nqBSJcPeZnlZrzN477NKURn2Nus8sjzmEII0TfMiyxUuxphVWjpJkbx0btUnshRihVv70Bv8ItXq6Asoi/ZiCbU6YgAAAABJRU5ErkJggg==);}
            .error-template {padding: 40px 15px;text-align: center;}
            .error-actions {margin-top:15px;margin-bottom:15px;}
            .error-actions .btn { margin-right:10px; }
        </style>
    </head>

    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="error-template">
                        <h1>
                            Oops!</h1>
                        <h2>
                            ${errorMsg}</h2>
                        <div class="error-details">
                            Sorry, an error has occured!
                        </div>
                        <div class="error-actions">
                            <a href="<c:url value="/" />" class="btn btn-primary btn-lg"><span class="fa fa-home"></span>
                                Volver </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
