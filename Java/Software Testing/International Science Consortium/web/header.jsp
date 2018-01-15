<!--
/*
 * Arelys De La Guardia
 * Deisy Hernandez
 * Michael Smythers
 * Daniel Galano
 * Jairo Pava
 *
 * International Science Consoritum Control System
 *
 * December 1, 2009
 */
-->

<%@ page import="Storage.Repository.*, Interface.UserForms"%>

<html>
<head>
<!-- This is the main web page for ISC Users. A user must log in to
	 the system through this page in order to proceed further.

	 Once provided the correct log in information, the system
	 identifies the user as either an administrator, employee, or panelist.

	 See Use Case ISCUC-03 for more details.
	 -->
<title>ISC Control System</title>
<meta http-equiv="Content-Type" content="text/html;"/>
<meta http-equiv='refresh' content='900;url=?message=Your session has timed out.' />
<SCRIPT language=JavaScript>
function makeArray() {
     for (i = 0; i<makeArray.arguments.length; i++)
         this[i] = makeArray.arguments[i];
 }

function getFullYear(d) {
    var y = d.getYear();
    if (y < 1000) {y += 1900};
    return y;
}

//var zone = "EDT";
var days = new makeArray("Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday");
var months = new makeArray("January","February","March","April","May","June","July","August","September","October","November","December");

function format_time(t) {

    var Day = t.getDay();
    var Date = t.getDate();
    var Month = t.getMonth();
    var Year = t.getFullYear();

    timeString = "";
    timeString += days[Day];
    timeString += ", ";
    timeString += " ";
    timeString += months[Month];
    timeString += " ";
    timeString += Date;
    timeString += ", ";
    timeString += " ";
    timeString += Year;

   return timeString;
 }
</SCRIPT>
<link rel="stylesheet" href="styles.css" type="text/css">
<style type="text/css">
<!--
.style1 {
	font-size: 36px;
	color: #FFFFFF;
}
-->
</style>
</head>
<body bgcolor="#ffffff" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
  <tr>
    <td bgcolor="#395080" height="50" valign="top">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
        <tr>
          <td width="50%" valign="top"><span class="style1">International Science Consortium</span></td>
          <td width="50%" valign="top">
            <table width="100%" border="0" cellspacing="0" cellpadding="15">
              <tr>
                <td valign="top" align="right" class="menutext">
                  <script language=JavaScript>
			<!--
			d = new Date();
			document.write(format_time(d));
			// -->
		</script>
                </td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
    </td>
  </tr>
  <tr>
    <td height="98" valign="top">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
        <tr>
          <td valign="top" width="82%">
            <table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
              <tr bgcolor="#EAEBE4">
                <td height="73" valign="top" bgcolor="#EAEBE4"><img src="images/spacer.gif" width="1" height="73"></td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td height="25" bgcolor="#395080" class="menutext" nowrap width="98%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="http://localhost:8080/International_Science_Consortium/" class="menutext">Home</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" class="menutext">About
                  Us</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" class="menutext">Site
                  Map</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" class="menutext">Contact
                  </a></td>
                <td height="25" width="22" valign="bottom"><img name="triangle1" src="images/triangle1.jpg" width="22" height="25" border="0"></td>
              </tr>
            </table>
          </td>
          <td valign="top" align="right" width="340"><img name="graphic1" src="images/graphic1.jpg" width="340" height="98" border="0"></td>
        </tr>
      </table>
    </td>
  </tr>
  <tr>
    <td height="72%" valign="top">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
        <tr>
          <td valign="top" width="152" bgcolor="#395080">
            <table width="152" border="0" cellspacing="0" cellpadding="0" height="100%">
              <tr>
                <td width="152" bgcolor="#395080" height="140" valign="top"><img name="graphic2" src="images/graphic2.jpg" width="152" height="128" border="0"></td>
              </tr>
              <tr>
                  <td valign="top" bgcolor="#395080">
                     
                     <%
                     
                       ((UserForms)request.getSession(true).getAttribute("User Form")).displayNavigationMenu(out);

                     %>

                </td>
              </tr>
            </table>
          </td>
          <td valign="top" width="85%">