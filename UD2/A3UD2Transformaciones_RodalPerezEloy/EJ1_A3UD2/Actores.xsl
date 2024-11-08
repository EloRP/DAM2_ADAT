<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <xsl:apply-templates/>
        </html>
    </xsl:template>
    <xsl:template match="Actores">
        <head>
            <title> LISTADO DE ACTORES </title>
        </head>
        <body>
            <h1>Lista de actores</h1>
            <table border="1">
                <tr>
                    <xsl:attribute name="style">background-color:grey;</xsl:attribute>
                    <th>Nombre</th>
                    <th>Sexo</th>
                    <th>Datanacemento</th>
                </tr>
                <xsl:apply-templates select="Actor"/>
            </table>
        </body>
    </xsl:template>
    <xsl:template match="Actor">
        <tr>
            <xsl:attribute name="style">background-color:cyan</xsl:attribute>
            <xsl:if test="position() mod 2 = 0">
                <xsl:attribute name="style">background-color:#D3D3D3</xsl:attribute>
            </xsl:if>
            <xsl:apply-templates />
        </tr>
    </xsl:template>
    <xsl:template match="Nome|Sexo|DataNacemento">
        <td>
            <xsl:apply-templates />
        </td>
    </xsl:template>
</xsl:stylesheet>