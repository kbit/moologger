<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	
	<xsl:template match="/">
		<log>
	    	<conversation>
	    		<startTimestamp>
	    			<xsl:apply-templates select="//H3" />
	    		</startTimestamp>
				<xsl:apply-templates select="//FONT[@color]" />
	    	</conversation>
		</log>
	</xsl:template>
	
	<xsl:template match="H3">
		<xsl:value-of select="substring-before(substring-after(., 'at '), ' on')" />
	</xsl:template>
	
	<xsl:template match="FONT[@color]">
		<xsl:variable name="key" select="." />
		<xsl:variable name="auto-reply" select="substring(B, string-length(B) - 13) = ' &lt;AUTO-REPLY&gt;:'" />
		<message>
			<timestamp>
				<xsl:value-of select="FONT" />
			</timestamp>
			<principal>
				<xsl:choose>
					<xsl:when test="$auto-reply">
						<xsl:value-of select="substring-before(B, ' &lt;AUTO-REPLY&gt;:')" />
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of select="substring-before(B, ':')" />
					</xsl:otherwise>
				</xsl:choose>
			</principal>
			<text>
				<xsl:if test="$auto-reply">
					<xsl:value-of select="'&lt;AUTO-REPLY&gt; '" />
				</xsl:if>
				<xsl:for-each select="following-sibling::node()[preceding-sibling::FONT[@color][1] = $key and not(self::FONT[@color])]">
					<xsl:copy-of select="." />
				</xsl:for-each>
			</text>
		</message>
	</xsl:template>
	
</xsl:stylesheet> 