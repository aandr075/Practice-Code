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

<%-- 
    Document   : messagePage
    Created on : Nov 21, 2009, 12:01:08 PM
    Author     : Jairo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<table width="100%" border="0" cellspacing="10">
    <tr>
        <td><% out.println(request.getParameter("messageCode")); %></td>
    </tr>
</table>