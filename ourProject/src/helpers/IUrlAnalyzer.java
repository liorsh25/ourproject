package helpers;


public interface IUrlAnalyzer {

	/**
	 * @return the destination url as requested by this link
	 */
	public  String getDestinationUrl();

	/**
	 * @return the subscriber key as managed in monlink and as set on the link
	 */
	public String getSubcriberKey() ;

	/**
	 * @return the campaign id as managed in monlink and as set on the link
	 */
	public String getCampaingId();

	/**
	 * @return additional user param that is not set in monlink, as set on the link
	 */
	public String getUserParam();

	/**
	 * @return link date indication as set on the link
	 */
	public String getLinkDate();

	/**
	 * @return clicking end customer code (if known), as set on the link
	 */
	public String getCustomerCode();
}