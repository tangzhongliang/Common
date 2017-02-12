/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard;

import java.util.ArrayList;
import java.util.List;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanRequestAttribute;

public final class DestinationSetting implements ScanRequestAttribute {
	
	private static final String NAME_DESTINATION_SETTING = "destinationSetting";
	
	private final List<DestinationSettingItem> items;
	
	public DestinationSetting() {
		items = new ArrayList<DestinationSettingItem>();
	}
	
	public int size() {
		return items.size();
	}
	
	public DestinationSettingItem get(int index) {
		return items.get(index);
	}
	
	public boolean add(DestinationSettingItem info) {
		return items.add(info);
	}
	
	public DestinationSettingItem remove(int index) {
		return items.remove(index);
	}
	
	public boolean remove(DestinationSettingItem info) {
		return items.remove(info);
	}
	
	public void clear() {
		items.clear();
	}
	
	
	@Override
	public Class<?> getCategory() {
		return DestinationSetting.class;
	}

	@Override
	public String getName() {
		return NAME_DESTINATION_SETTING;
	}

	@Override
	public Object getValue() {
		List<Object> list = new ArrayList<Object>();
		for (DestinationSettingItem info : items.toArray(new DestinationSettingItem[items.size()])) {
			list.add(info.getValue());
		}
		return list;
	}

}
