/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.log.printerlog;

import java.util.List;
import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ArrayElement;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Element;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ResponseBody;

public class GetDeviceLogResponseBody extends Element implements ResponseBody {
	
	private static final String KEY_MACHINE_INFO    = "machineInfo";
	private static final String KEY_ACQUIRED_NUMBER = "acquiredNumber";
	private static final String KEY_FIRST_LINK      = "firstLink";
	private static final String KEY_NEXT_LINK       = "nextLink";
	private static final String KEY_MACHINE_LOG     = "machineLog";
	
	GetDeviceLogResponseBody(Map<String, Object> values) {
		super(values);
	}
	
	/*
	 * machineInfo (Object)
	 */
	public MachineInfo getMachineInfo() {
		Map<String, Object> value = getObjectValue(KEY_MACHINE_INFO);
		if (value == null) {
			return null;
		}
		return new MachineInfo(value);
	}
	
	/*
	 * acquiredNumber (Number)
	 */
	public Integer getAcquiredNumber() {
		return getNumberValue(KEY_ACQUIRED_NUMBER);
	}
	
	/*
	 * firstLink (String)
	 */
	public String getFirstLink() {
		return getStringValue(KEY_FIRST_LINK);
	}
	
	/*
	 * nextLink (String)
	 */
	public String getNextLink() {
		return getStringValue(KEY_NEXT_LINK);
	}
	
	/*
	 * machineLog (Array[Object])
	 */
	public MachineLogArray getMachineLog() {
		List<Map<String, Object>> value = getArrayValue(KEY_MACHINE_LOG);
		if (value == null) {
			return null;
		}
		return new MachineLogArray(value);
	}
	
	
	public static class MachineInfo extends Element {
		
		private static final String KEY_MACHINE_NUMBER = "machineNumber";
		private static final String KEY_MACHINE_NAME   = "machineName";
		
		MachineInfo(Map<String, Object> values) {
			super(values);
		}
		
		/*
		 * machineNumber (String)
		 */
		public String getMachineNumber() {
			return getStringValue(KEY_MACHINE_NUMBER);
		}
		
		/*
		 * machineName (String)
		 */
		public String getMachineName() {
			return getStringValue(KEY_MACHINE_NAME);
		}
		
	}
	
	public static class MachineLogArray extends ArrayElement<MachineLog> {
		
		MachineLogArray(List<Map<String, Object>> list) {
			super(list);
		}
		
		@Override
		protected MachineLog createElement(Map<String, Object> values) {
			return new MachineLog(values);
		}
		
	}
	
	public static class MachineLog extends Element {
		
		private static final String KEY_LOG_PROPERTY      = "logProperty";
		private static final String KEY_SUB_LOG_PROPERTY  = "subLogProperty";
		
		MachineLog(Map<String, Object> values) {
			super(values);
		}
		
		/*
		 * logProperty (Object)
		 */
		public LogProperty getLogProperty() {
			Map<String, Object> value = getObjectValue(KEY_LOG_PROPERTY);
			if (value == null) {
				return null;
			}
			return new LogProperty(value);
		}
		
		/*
		 * subLogProperty (Array[Object])
		 */
		public SubLogPropertyArray getSubLogProperty() {
			List<Map<String, Object>> value = getArrayValue(KEY_SUB_LOG_PROPERTY);
			if (value == null) {
				return null;
			}
			return new SubLogPropertyArray(value);
		}
		
	}
	
	public static class LogProperty extends Element {
		
		private static final String KEY_LOG_VERSION      = "logVersion";
		private static final String KEY_LOG_ID           = "logId";
		private static final String KEY_SUB_PROP_NUM     = "subPropNum";
		private static final String KEY_GENERAL_LOG_ITEM = "generalLogItem";
		
		LogProperty(Map<String, Object> values) {
			super(values);
		}
		
		/*
		 * logVersion (String)
		 */
		public String getLogVersion() {
			return getStringValue(KEY_LOG_VERSION);
		}
		
		/*
		 * logId (String)
		 */
		public String getLogId() {
			return getStringValue(KEY_LOG_ID);
		}
		
		/*
		 * subPropNum (Number)
		 */
		public Integer getSubPropNum() {
			return getNumberValue(KEY_SUB_PROP_NUM);
		}
		
		/*
		 * generalLogItem (Array[Object])
		 */
		public GeneralLogItemArray getGeneralLogItem() {
			List<Map<String, Object>> value = getArrayValue(KEY_GENERAL_LOG_ITEM);
			if (value == null) {
				return null;
			}
			return new GeneralLogItemArray(value);
		}
		
	}
	
	public static class GeneralLogItemArray extends ArrayElement<GeneralLogItem> {
		
		GeneralLogItemArray(List<Map<String, Object>> list) {
			super(list);
		}
		
		@Override
		protected GeneralLogItem createElement(Map<String, Object> values) {
			return new GeneralLogItem(values);
		}
		
	}
	
	public static class GeneralLogItem extends Element {
		
		private static final String KEY_LOG_NAME  = "logName";
		private static final String KEY_LOG_VALUE = "logValue";
		
		GeneralLogItem(Map<String, Object> value) {
			super(value);
		}
		
		/*
		 * logName (String)
		 */
		public String getLogName() {
			return getStringValue(KEY_LOG_NAME);
		}
		
		/*
		 * logValue (String)
		 */
		public String getLogValue() {
			return getStringValue(KEY_LOG_VALUE);
		}
	}
	
	public static class SubLogPropertyArray extends ArrayElement<SubLogProperty> {

		SubLogPropertyArray(List<Map<String, Object>> list) {
			super(list);
		}

		@Override
		protected SubLogProperty createElement(Map<String, Object> values) {
			return new SubLogProperty(values);
		}
		
	}
	
	public static class SubLogProperty extends Element {
		
		private static final String KEY_PARENT_LOG_ID = "parentLogId";
		private static final String KEY_SUB_LOG_ID    = "subLogId";
		private static final String KEY_SUB_LOG_TYPE  = "subLogType";
		private static final String KEY_LOG_ITEM      = "logItem";
		
		SubLogProperty(Map<String, Object> values) {
			super(values);
		}
		
		/*
		 * parentLogId (String)
		 */
		public String getParentLogId() {
			return getStringValue(KEY_PARENT_LOG_ID);
		}
		
		/*
		 * subLogId (String)
		 */
		public String getSubLogId() {
			return getStringValue(KEY_SUB_LOG_ID);
		}
		
		/*
		 * subLogType (String)
		 */
		public String getSubLogType() {
			return getStringValue(KEY_SUB_LOG_TYPE);
		}
		
		/*
		 * logItem (Array[Object])
		 */
		public LogItemArray getLogItem() {
			List<Map<String, Object>> value = getArrayValue(KEY_LOG_ITEM);
			if (value == null) {
				return null;
			}
			return new LogItemArray(value);
		}
		
	}
	
	public static class LogItemArray extends ArrayElement<LogItem> {
		
		LogItemArray(List<Map<String, Object>>list) {
			super(list);
		}
		
		@Override
		protected LogItem createElement(Map<String, Object> values) {
			return new LogItem(values);
		}
		
	}
	
	public static class LogItem extends Element {
		
		private static final String KEY_LOG_NAME  = "logName";
		private static final String KEY_LOG_VALUE = "logValue";
		
		LogItem(Map<String, Object> values) {
			super(values);
		}
		
		/*
		 * logName (String)
		 */
		public String getLogName() {
			return getStringValue(KEY_LOG_NAME);
		}
		
		/*
		 * logValue (String)
		 */
		public String getLogValue() {
			return getStringValue(KEY_LOG_VALUE);
		}
		
	}
	
}
