/*
 *  Copyright (C) 2016 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.property;

import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Element;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ResponseBody;

/*
 * @since SmartSDK V2.31
 */
public class GetNetworkResponseBody extends Element implements ResponseBody {

	private static final String KEY_LAN_TYPE = "lanType";
	private static final String KEY_IPV4 = "ipv4";
	private static final String KEY_IPV6 = "ipv6";
	private static final String KEY_HOST_NAME = "hostName";
	private static final String KEY_DOMAIN_NAME = "domainName";
	private static final String KEY_MAC_ADDRESS = "macAddress";

	GetNetworkResponseBody(Map<String, Object> values) {
		super(values);
	}

	/*
	 * lanType (String)
	 */
	public String getLanType() {
		return getStringValue(KEY_LAN_TYPE);
	}

	/*
	 * ipv4 (Object)
	 */
	public Ipv4 getIpv4() {
		Map<String, Object> value = getObjectValue(KEY_IPV4);
		if (value == null) {
			return null;
		}
		return new Ipv4(value);
	}

	/*
	 * ipv6 (Object)
	 */
	public Ipv6 getIpv6() {
		Map<String, Object> value = getObjectValue(KEY_IPV6);
		if (value == null) {
			return null;
		}
		return new Ipv6(value);
	}

	/*
	 * hostName (String)
	 */
	public String getHostName() {
		return getStringValue(KEY_HOST_NAME);
	}

	/*
	 * domainName (String)
	 */
	public String getDomainName() {
		return getStringValue(KEY_DOMAIN_NAME);
	}

	/*
	 * macAddress (String)
	 */
	public String getMacAddress() {
		return getStringValue(KEY_MAC_ADDRESS);
	}

	public static class Ipv4 extends Element {

		private static final String KEY_SETTING = "setting";
		private static final String KEY_DHCP_SETTING = "dhcpSetting";
		private static final String KEY_ADDRESS = "address";
		private static final String KEY_SUBNETMASK = "subnetmask";
		private static final String KEY_GATEWAY_ADDRESS = "gatewayAddress";

		Ipv4(Map<String, Object> values) {
			super(values);
		}

		/*
		 * setting (String)
		 */
		public String getSetting() {
			return getStringValue(KEY_SETTING);
		}

		/*
		 * dhcpSetting (String)
		 */
		public String getDhcpSetting() {
			return getStringValue(KEY_DHCP_SETTING);
		}

		/*
		 * address (String)
		 */
		public String getAddress() {
			return getStringValue(KEY_ADDRESS);
		}

		/*
		 * subnetmask (String)
		 */
		public String getSubnetmask() {
			return getStringValue(KEY_SUBNETMASK);
		}

		/*
		 * gatewayAddress (String)
		 */
		public String getGatewayAddress() {
			return getStringValue(KEY_GATEWAY_ADDRESS);
		}

	}

	public static class Ipv6 extends Element {

		private static final String KEY_SETTING = "setting";
		private static final String KEY_IDHCP_SETTING = "idhcpSetting";
		private static final String KEY_STATELESS_ADDRESS_SETTING = "statelessAddressSetting";
		private static final String KEY_DHCPV6_ADDRESS = "dhcpv6Address";
		private static final String KEY_STATELESS_ADDRESS1 = "statelessAddress1";
		private static final String KEY_STATELESS_ADDRESS2 = "statelessAddress2";
		private static final String KEY_STATELESS_ADDRESS3 = "statelessAddress3";
		private static final String KEY_STATELESS_ADDRESS4 = "statelessAddress4";
		private static final String KEY_STATELESS_ADDRESS5 = "statelessAddress5";
		private static final String KEY_LINKLOCAL_ADDRESS = "linklocalAddress";
		private static final String KEY_MANUAL_ADDRESS = "manualAddress";
		private static final String KEY_DHCPV6_ADDRESS_PREFIX_LENGTH = "dhcpv6AddressPrefixLength";
		private static final String KEY_STATELESS_ADDRESS_PREFIX_LENGTH1 = "statelessAddressPrefixLength1";
		private static final String KEY_STATELESS_ADDRESS_PREFIX_LENGTH2 = "statelessAddressPrefixLength2";
		private static final String KEY_STATELESS_ADDRESS_PREFIX_LENGTH3 = "statelessAddressPrefixLength3";
		private static final String KEY_STATELESS_ADDRESS_PREFIX_LENGTH4 = "statelessAddressPrefixLength4";
		private static final String KEY_STATELESS_ADDRESS_PREFIX_LENGTH5 = "statelessAddressPrefixLength5";
		private static final String KEY_LINKLOCAL_ADDRESS_PREFIX_LENGTH = "linklocalAddressPrefixLength";
		private static final String KEY_MANUAL_ADDRESS_PREFIX_LENGTH = "manualAddressPrefixLength";
		private static final String KEY_DEFAULT_GATEWAY_ADDRESS = "defaultGatewayAddress";
		private static final String KEY_MANUAL_GATEWAY_ADDRESS = "manualGatewayAddress";

		Ipv6(Map<String, Object> values) {
			super(values);
		}

		/*
		 * setting (String)
		 */
		public String getSetting() {
			return getStringValue(KEY_SETTING);
		}

		/*
		 * idhcpSetting (String)
		 */
		public String getIdhcpSetting() {
			return getStringValue(KEY_IDHCP_SETTING);
		}

		/*
		 * statelessAddressSetting (String)
		 */
		public String getStatelessAddressSetting() {
			return getStringValue(KEY_STATELESS_ADDRESS_SETTING);
		}

		/*
		 * dhcpv6Address (String)
		 */
		public String getDhcpv6Address() {
			return getStringValue(KEY_DHCPV6_ADDRESS);
		}

		/*
		 * statelessAddress1 (String)
		 */
		public String getStatelessAddress1() {
			return getStringValue(KEY_STATELESS_ADDRESS1);
		}

		/*
		 * statelessAddress2 (String)
		 */
		public String getStatelessAddress2() {
			return getStringValue(KEY_STATELESS_ADDRESS2);
		}

		/*
		 * statelessAddress3 (String)
		 */
		public String getStatelessAddress3() {
			return getStringValue(KEY_STATELESS_ADDRESS3);
		}

		/*
		 * statelessAddress4 (String)
		 */
		public String getStatelessAddress4() {
			return getStringValue(KEY_STATELESS_ADDRESS4);
		}

		/*
		 * statelessAddress5 (String)
		 */
		public String getStatelessAddress5() {
			return getStringValue(KEY_STATELESS_ADDRESS5);
		}

		/*
		 * linklocalAddress (String)
		 */
		public String getLinklocalAddress() {
			return getStringValue(KEY_LINKLOCAL_ADDRESS);
		}

		/*
		 * manualAddress (String)
		 */
		public String getManualAddress() {
			return getStringValue(KEY_MANUAL_ADDRESS);
		}

		/*
		 * dhcpv6AddressPrefixLength (String)
		 */
		public String getDhcpv6AddressPrefixLength() {
			return getStringValue(KEY_DHCPV6_ADDRESS_PREFIX_LENGTH);
		}

		/*
		 * statelessAddressPrefixLength1 (String)
		 */
		public String getStatelessAddressPrefixLength1() {
			return getStringValue(KEY_STATELESS_ADDRESS_PREFIX_LENGTH1);
		}

		/*
		 * statelessAddressPrefixLength2 (String)
		 */
		public String getStatelessAddressPrefixLength2() {
			return getStringValue(KEY_STATELESS_ADDRESS_PREFIX_LENGTH2);
		}

		/*
		 * statelessAddressPrefixLength3 (String)
		 */
		public String getStatelessAddressPrefixLength3() {
			return getStringValue(KEY_STATELESS_ADDRESS_PREFIX_LENGTH3);
		}

		/*
		 * statelessAddressPrefixLength4 (String)
		 */
		public String getStatelessAddressPrefixLength4() {
			return getStringValue(KEY_STATELESS_ADDRESS_PREFIX_LENGTH4);
		}

		/*
		 * statelessAddressPrefixLength5 (String)
		 */
		public String getStatelessAddressPrefixLength5() {
			return getStringValue(KEY_STATELESS_ADDRESS_PREFIX_LENGTH5);
		}

		/*
		 * linklocalAddressPrefixLength (String)
		 */
		public String getLinklocalAddressPrefixLength() {
			return getStringValue(KEY_LINKLOCAL_ADDRESS_PREFIX_LENGTH);
		}

		/*
		 * manualAddressPrefixLength (String)
		 */
		public String getManualAddressPrefixLength() {
			return getStringValue(KEY_MANUAL_ADDRESS_PREFIX_LENGTH);
		}

		/*
		 * defaultGatewayAddress (String)
		 */
		public String getDefaultGatewayAddress() {
			return getStringValue(KEY_DEFAULT_GATEWAY_ADDRESS);
		}

		/*
		 * manualGatewayAddress (String)
		 */
		public String getManualGatewayAddress() {
			return getStringValue(KEY_MANUAL_GATEWAY_ADDRESS);
		}

	}
}
