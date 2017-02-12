/*
 *  Copyright (C) 2013-2014 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.supported;

import java.util.List;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.common.Conversions;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.scanner.Capability;

public final class DestinationSettingSupported {
	
	private final List<String> supportedDestinationTypes;
	private final AddressbookDestinationSettingSupported addressbookSetting;
	private final ManualDestinationSettingSupported manualSetting;
	private final MaxBroadcastNumberSupported maxBroadcastNumbers;
	
	public DestinationSettingSupported(Capability.DestinationSettingCapability destinations) {
		supportedDestinationTypes = Conversions.getList(destinations.getDestinationTypeList());
		addressbookSetting = AddressbookDestinationSettingSupported.getInstance(destinations.getAddressbookDestinationSettingCapability());
		manualSetting = ManualDestinationSettingSupported.getInstance(destinations.getManualDestinationSettingCapability());
		maxBroadcastNumbers = MaxBroadcastNumberSupported.getInstance(destinations.getMaxBroadcastNumber());
	}
	
	@Deprecated
	public DestinationSettingSupported(Capability.DestinationSettingCapability destinations,
			Capability.MaxBroadcastNumberCapability broadcasts) {
		supportedDestinationTypes = Conversions.getList(destinations.getDestinationTypeList());
		addressbookSetting = AddressbookDestinationSettingSupported.getInstance(destinations.getAddressbookDestinationSettingCapability());
		manualSetting = ManualDestinationSettingSupported.getInstance(destinations.getManualDestinationSettingCapability());
		maxBroadcastNumbers = MaxBroadcastNumberSupported.getInstance(broadcasts);
	}
	
	public List<String> getSupportedDestinationTypes() {
		return supportedDestinationTypes;
	}
	
	public AddressbookDestinationSettingSupported getAddressbookSetting() {
		return addressbookSetting;
	}
	
	public ManualDestinationSettingSupported getManualSetting() {
		return manualSetting;
	}
	
	public MaxBroadcastNumberSupported getMaxBroadcastNumbers() {
		return maxBroadcastNumbers;
	}
	
}
