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

 <!-- This is the main web page for ISC Users. A user must log in to
     the system through this page in order to proceed further.

     Once provided the correct log in information, the system
     identifies the user as either an administrator, employee, or panelist.

     See Use Case ISCUC-03 for more details.
        -->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Interface.*, Storage.Repository.*" %>

        <jsp:include page="header.jsp" />


        <% if(session.getAttribute("User Profile") == null) {
            if(request.getParameter("message") != null)
                out.println("<b><font color=\"red\">" + request.getParameter("message") +
                        "</font></b>");
        %>
            <jsp:include page="forms/login.htm" />
        <% } else {
        UserProfile userProfile = (UserProfile)session.getAttribute("User Profile"); %>
            <table width="100%" border="0" cellspacing="10">
                <tr>
                    <td>
                        <% out.println("Welcome, " + userProfile.FirstName + ". Please use the links to your " +
                            "left to begin working."); %>
                    </td>
                </tr>
            </table>
        <% } %>
        <jsp:include page="footer.jsp" />
