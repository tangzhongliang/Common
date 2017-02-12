/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.addressbook;

import java.util.List;
import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ArrayElement;

public class GroupArray extends ArrayElement<Group> {
	
	GroupArray(List<Map<String, Object>> list) {
		super(list);
	}
	
	@Override
	protected Group createElement(Map<String, Object> values) {
		return new Group(values);
	}
	
}
