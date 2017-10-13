<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="1.0">
	<xsl:param name="loginid" />
	<xsl:template match="/">
		<CommandList>
			<StartRouting>
				<XmlLoginId>
					<xsl:value-of select="$loginid" />
				</XmlLoginId>
				<LoginId>
					<xsl:value-of select="$loginid" />
				</LoginId>
				<Mode>plane</Mode>
				<Origin>
					<Descriptor>
						<xsl:value-of select="//odo[1]/dap" />
					</Descriptor>
					<Type>airportcode</Type>
					<ResolutionTypeList>
						<ResolutionType>airportgroup</ResolutionType>
						<ResolutionType>airportcode</ResolutionType>
					</ResolutionTypeList>
					<Radius>0</Radius>
				</Origin>
				<Destination>
					<Descriptor>
						<xsl:value-of select="//odo[1]/aap" />
					</Descriptor>
					<Type>airportcode</Type>
					<Radius>0</Radius>
				</Destination>
				<OutwardDates>
					<DateOfSearch>
						<xsl:value-of select="//odo[1]/dd" />
					</DateOfSearch>
				</OutwardDates>
				<xsl:if test="count(//odo)>1">
					<ReturnDates>
						<DateOfSearch>
							<xsl:value-of select="//odo[2]/dd" />
						</DateOfSearch>
					</ReturnDates>
				</xsl:if>
				<MaxChanges>1</MaxChanges>
				<MaxHops>2</MaxHops>
				<SupplierList>
					<Supplier>ezy</Supplier>
				</SupplierList>
				<Timeout>40</Timeout>
				<TravelClass>
					<xsl:value-of select="//ctp" />
				</TravelClass>
				<TravellerList>
					<Traveller>
						<Age>30</Age>
					</Traveller>
				</TravellerList>
				<IncrementalResults>true</IncrementalResults>
			</StartRouting>
		</CommandList>
	</xsl:template>
</xsl:stylesheet>