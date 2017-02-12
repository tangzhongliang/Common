/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanRequestAttribute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum ScanDevice implements ScanRequestAttribute {

	AUTO("auto"),
	ADF("adf"),
	CONTACT_GLASS("contact_glass");

	private static final String SCAN_DEVICE = "scanDevice";

	/**
	 * 内部保持する原稿読取装置名
	 * Scan device name to store internally
	 */
	private final String scanDevice;

	/**
	 * 指定した値で原稿読取装置を構築する
	 * Builds the scan device with specified value.
	 * @param device
	 */
	private ScanDevice(String device) {
		this.scanDevice = device;
	}

	@Override
	public Class<?> getCategory() {
		return ScanDevice.class;
	}

	@Override
	public String getName() {
		return SCAN_DEVICE;
	}

	@Override
	public Object getValue() {
		return this.scanDevice;
	}

    private static volatile Map<String, ScanDevice> directory = null;

    private static Map<String, ScanDevice> getDirectory() {
        if(directory == null){
            Map<String, ScanDevice> d = new HashMap<String, ScanDevice>();
            for(ScanDevice device : values()) {
                d.put(device.getValue().toString(), device);
            }
            directory = d;
        }
        return directory;
    }

	private static ScanDevice fromString(String value){
		return getDirectory().get(value);
	}

	public static List<ScanDevice> getSupportedValue(List<String> values) {
		if (values == null) {
			return Collections.emptyList();
		}

		List<ScanDevice> list = new ArrayList<ScanDevice>();
		for(String value : values){
			ScanDevice device = fromString(value);
			if( device != null ) {
				list.add(fromString(value));
			}
		}
		return list;
	}
}
