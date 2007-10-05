<?xml version='1.0' encoding='utf-8' ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:output method="html"/>
	<xsl:include href="Style1.xsl"/>
	<xsl:template match="/">
		<!-- Import formatting characteristics -->
		<xsl:call-template name="Style1"/>

		<h1>Employee Listing</h1>
		<table border="1">
			<tr>
				<th>Emp ID</th>
				<th>Name</th>
				<th>Hourly Rate</th>
				<th>000.00</th>
				<th>$#00%</th>
				<th>###0.00</th>
				<th>#,00;(#,00)</th>
				<th>#,00;#,00CR</th>
				<th>#,###,###</th>
				<th>##.##</th>
				<th>#,##0.00</th>
			</tr>
			<xsl:for-each select="/employees/employee">
				<tr>
					<td>
						<xsl:value-of select="@EmployeeID"/>
					</td>
					<td>
						<xsl:value-of select="concat(@FirstName, &apos; &apos;, @LastName)"/>
					</td>
					<td>
						<xsl:value-of select="@HourlyRate"/>
					</td>
					<td>
						<xsl:value-of select="format-number(number(@HourlyRate),&apos;000.00&apos;)"/>
					</td>
					<td>
						<xsl:value-of select="format-number(number(@HourlyRate),&apos;$#00%&apos;)"/>
					</td>
					<td>
						<xsl:value-of select="format-number(number(@HourlyRate),&apos;###0.00&apos;)"/>
					</td>
					<td>
						<xsl:value-of select="format-number(number(@HourlyRate),&apos;#,00;(#,00)&apos;)"/>
					</td>
					<td>
						<xsl:value-of select="format-number(number(@HourlyRate),&apos;#,00;#,00CR&apos;)"/>
					</td>
					<td>
						<xsl:value-of select="format-number(number(@HourlyRate),&apos;#,###,###&apos;)"/>
					</td>
					<td>
						<xsl:value-of select="format-number(number(@HourlyRate),&apos;##.##&apos;)"/>
					</td>
					<td>
						<xsl:value-of select="format-number(number(@HourlyRate),&apos;#,##0.00&apos;)"/>
					</td>
				</tr>
			</xsl:for-each>
		</table>
	</xsl:template>
	<xsl:template match="employees">Total Employees</xsl:template>
</xsl:stylesheet>
