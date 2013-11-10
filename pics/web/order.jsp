<%@ page import="java.util.*" %>
<%@ page import="dk.japps.pics.*" %>
<%@ page import="dk.japps.pics.file.*" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="../../docs-assets/ico/favicon.png">

    <title>Jon Lind</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="etc/pics.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
  </head>

  <body style="">

	<!-- include _navbar.jsp -->  

    <div class="container">

      <div class="row row-offcanvas row-offcanvas-right">

        <div class="col-xs-12 col-sm-9">
		
		<!-- include _navbar_toggle_button.jsp -->
		  
		<%@include file="_header.jsp"%>

          <div class="row">
          
<h1>Bestilling af billeder</h1>
<h2>Sådan bestiller du</h2>
<ul>
	<li>1. Skriv navnene på de billeder du vil bestille samt ønsket leveringsform, i en mail til kontakt@jonlind.dk.</li>
	<li>2. Herefter modtager du svar med detaljer om betaling og levering.</li>
</ul>



<h2>Billederne leveres som print i følgende formater</h2>
13 x 18 cm
21 x 30 cm (A4)
30 x 42 cm (A3)
42 x 60 cm (A2)

Print leveres på fotopapir af høj kvalitet (Epson Premium Glossy). Ved bestilling af print medfølger de bestilte billeder i jpg-format.

<h2>Priser</h2>
13 x 18 cm koster DKK 50,- pr stk
21 x 30 cm koster DKK 100,- pr stk
30 x 42 cm koster DKK 150,- pr stk
42 x 60 cm koster DKK 200,- pr stk

Montering af billede på 5 mm skumplade koster DKK 50,- ekstra pr stk.

<h2>Levering</h2>
Forsendelse som brev med Post Danmark koster DKK 50,- pr forsendelse (sendes på eget ansvar).
Forsendelse som pakke med Post Danmark koster DKK 100,- pr forsendelse (forsikret og med Track & Trace).
Alternativt kan print leveres torsdag eftermiddag hos Gaardbo Dans efter aftale.

          </div><!--/row-->
        </div><!--/span-->

	<%@include file="_menu.jsp"%>
        
      </div><!--/row-->

	<%@include file="_footer.jsp"%>

    </div><!--/.container-->



    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

</body>
</html>
