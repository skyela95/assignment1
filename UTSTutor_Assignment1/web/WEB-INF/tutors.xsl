<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : tutors.xsl
    Created on : 8 October 2017, 6:07 PM
    Author     : Madeleine
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="tutors">
        <html>
            <head>
                <title>tutors.xsl</title>
                <style>
                    table{
                        width:70%;
                    }
                    th, td {
			text-align:left;
			padding: 8px;
                    }
                </style>
            </head>
            <body>
                <table>
			<thead>
				<tr>
					<th>Select</th>
					<th>Name</th>
					<th>Email</th>
                                        <th>Subject</th>
                                        <th>Status</th>
				</tr>
			</thead>
			<tbody>
				<xsl:apply-templates />
			</tbody>
		</table>
                <xsl:apply-templates />
            </body>
        </html>
    </xsl:template>
    <xsl:template match="tutor">
        <tr>
            <td>
                <input type="checkbox" name="selectCB" value=""></input>
            </td>
            <td>
		<xsl:value-of select="name" />
            </td>
            <td>
		<xsl:value-of select="email" />
            </td>
            <td>
		<xsl:value-of select="tutorSpecialty"/>
            </td>
            <td>
                <xsl:value-of select="status"/>
            </td>
        </tr>
    </xsl:template>
</xsl:stylesheet>
