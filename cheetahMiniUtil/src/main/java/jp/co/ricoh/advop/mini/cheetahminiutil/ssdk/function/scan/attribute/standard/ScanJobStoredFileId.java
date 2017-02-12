/*
 *  Copyright (C) 2014 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanJobAttribute;

/**
 * HDD蓄積した文書のファイルIDを表すジョブ属性クラスです。
 * HDD蓄積を行うジョブを実行し、正常に蓄積完了した場合に取得することができます。
 * This job attribute class represents the file ID of a file stored to HDD.
 * This class runs a job to be stored to HDD, and can be obtained when the job is correctly stored.
 *
 * @since SmartSDK V1.02
 */
public class ScanJobStoredFileId implements ScanJobAttribute {

	private final String storedFileId;

	/**
	 * @since SmartSDK V1.02
	 */
	public static ScanJobStoredFileId getInstance(String storedFileId) {
	    if (storedFileId == null) {
	        return null;
	    }
	    return new ScanJobStoredFileId(storedFileId);
	}

	ScanJobStoredFileId(String storedFileId) {
		this.storedFileId = storedFileId;
	}

	/**
	 * @since SmartSDK V1.02
	 */
	public String getStoredFileId() {
		return storedFileId;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof ScanJobStoredFileId)) {
			return false;
		}

		ScanJobStoredFileId other = (ScanJobStoredFileId) obj;
		if (!isEqual(storedFileId, other.storedFileId)) {
			return false;
		}
		return true;
	}

	private boolean isEqual(Object obj1, Object obj2) {
		if (obj1 == null) {
			return (obj2 == null);
		}
		return obj1.equals(obj2);
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + (storedFileId == null ? 0 : storedFileId.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return storedFileId;
	}

	@Override
	public Class<?> getCategory() {
		return ScanJobStoredFileId.class;
	}

	@Override
	public String getName() {
		return ScanJobStoredFileId.class.getSimpleName();
	}

}
