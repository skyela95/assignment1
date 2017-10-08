<%-- 
    Document   : Main
    Created on : 02/10/2017, 7:01:44 PM
    Author     : Madeleine
--%>

<%@page import="uts.wsd.Tutor"%>
<%@page import="java.util.ArrayList"%>
<%@page import="uts.wsd.Tutors"%>
<%@page import="uts.wsd.User.UserType"%>
<%@page import="uts.wsd.User" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Main</title>
    </head>
    <body>
        <h1>Main Page</h1>
        <%
            User user = (User) session.getAttribute("user");
            if(user.getUserType() == UserType.STUDENT){  
                String searchStatus = request.getParameter("searchStatus");
                Boolean stat;
                if (searchStatus == "true"){
                    stat = true;
                }
                else if (searchStatus == "false"){
                    stat = false;
                }
                else{
                    stat = null;
                }
                String searchName = request.getParameter("searchName");
                String searchSubject = request.getParameter("searchSubject");
        %>
        <p> DELETE THIS: search form(STUDENT), for tutors using: subj, name, status.
        results displayed (name, email, sub, stat) - selectable.
        when student selects tutor, takes to booking page.</p>
        <p>marshall xml to java, then create list, use list for dropdowns.</p>
        <form method="get" action="main.jsp">
            <table>
                <tr>
                    <td>
                        <select name = "tutor name">
                            <option value="filler">filler</option>
                            <option value="filler">filler </option>
                        </select>
                    </td>
                    <td>
                        <select name="tutor subject">
                            <option value ="filler">filler</option>
                            <option value="filler">filler</option>
                        </select>
                    </td>
                    <td>
                        <select name="tutor status">
                            <option value="filler">filler </option>
                            <option value="filler">filler</option>
                        </select>
                    </td>
                </tr>
                <tr><td></td><td><input type="submit" value="Search"></td></tr>
            </table>            
        </form>
        <%
            ArrayList<Tutor> tutors = new ArrayList<Tutor>();
            //tutors = Tutors.getTutors(searchName, searchSubject, stat);
        %>
        <p> Do the search and display table.</p>
        <%} else if(user.getUserType() == UserType.TUTOR){ %>
        <p> DELETE THIS:
        for ALL users: Booking option to booking page, acct opt to page</p>
        <%}%>     
    </body>
</html>
