<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : bookings.xsl
    Created on : 9 October 2017, 11:27 AM
    Author     : Madeleine
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="bookings">
        <html>
            <head>
                <title>bookings.xsl</title>
                <style>
                    table{
                        width:80%;
                    }
                    th, td {
			text-align:left;
			padding: 10x;
                    }
                </style>
            </head>
            <body>
                <table>
                    <thead>
                        <tr>
                            <th>Status</th>
                            <th>Tutor</th>
                            <th>Tutor Email</th>
                            <th>Subject</th>
                            <th>Student</th>
                        </tr>
                    </thead>
                    <tbody>
                        <xsl:apply-templates/>
                    </tbody>
                </table>
            </body>
        </html>
    </xsl:template>
    <xsl:template match="booking">
            <tr>
                <td>
                    <xsl:value-of select="statusType"/>
                </td>
                <td>
                    <xsl:value-of select="tutorName" />
                </td>
                <td>
                    <xsl:value-of select="tutorEmail"/>
                </td>
                <td>
                    <xsl:value-of select="subjectName"/>
                </td>
                <td>
                    <xsl:value-of select="studentName"/>
                </td>
            </tr>
    </xsl:template>

</xsl:stylesheet>
