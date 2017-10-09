<?xml version="1.0" encoding="UTF-8"?>


<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    
    <xsl:template match="/">
        <html>
            <head>
                <title>students.xsl</title>
            </head>
            <body>
                <xsl:apply-templates/>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="students/student">
        <h1>
            <xsl:apply-templates/>
        </h1>
    </xsl:template>
    
    <xsl:template match="email">
        <div class="email">Email: 
            <xsl:apply-templates/>
        </div>
    </xsl:template>
    
    <xsl:template match="name">
        <div class="name">Name: 
            <xsl:apply-templates/>
        </div>
    </xsl:template>
    
    <xsl:template match="dateOfBirth">
        <div class="dateOfBirth">Date of Birth:  
            <xsl:apply-templates/>
        </div>
    </xsl:template>
    
    <xsl:template match="userType">
        <div class="email">Your user status:  
            <xsl:apply-templates/>
        </div>
    </xsl:template>
    
</xsl:stylesheet>
