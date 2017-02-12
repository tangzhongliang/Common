/*
 *  Copyright (C) 2013-2014 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanRequestAttribute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 読み取り後のジョブの動作を指定する属性です
 * 主に以下の4パターンを指定できます。
 * ・読み取りして送信
 * ・読み取りしてドキュメントボックス蓄積
 * ・読み取りして一時的にHDDに蓄積
 * ・蓄積文書の送信 (読み取りなし)
 * 蓄積を選択した場合は、蓄積後に蓄積したドキュメントへの操作を行うことで、
 * 読み取り実施したドキュメントへアクセスできます。
 * The attribute to specify job behavior after the job is scanned.
 * The following three patterns can be specified.
 *  - Scan and send
 *  - Scan and store to Document Server
 *  - Scan and store temporarily
 *  - Send stored document (without scanning)
 * When selecting to store, the scanned document can be accessed by operating the document
 * after the document is stored.
 */
public enum JobMode implements ScanRequestAttribute {

	/**
	 * 読み取りして送信
	 * Scan and send
	 */
	SCAN_AND_SEND("scan_and_send"),

	/**
	 * 読み取りしてドキュメントボックス蓄積
	 * Scan and store to Document Server
	 */
	SCAN_AND_STORE_LOCAL("scan_and_store_local"),

	/**
	 * 読み取りして一時蓄積（画像転送/ファイル転送）
	 * Scan and store temporarily (send image/file)
	 */
	SCAN_AND_STORE_TEMPORARY("scan_and_store_temporary"),

	/**
	 * 蓄積文書の送信
	 * Send stored document
	 *
	 * @since SmartSDK V1.01
	 */
	SEND_STORED_FILE("send_stored_file");


	private static final String SCAN_JOB_MODE = "jobMode";

	private final String value;

	private JobMode(String jobMode){
		this.value = jobMode;
	}

	public String getJobModeString()
	{
		return this.value;
	}

	@Override
	public Class<?> getCategory() {
		return JobMode.class;
	}

	@Override
	public String getName() {
		return SCAN_JOB_MODE;
	}

	@Override
	public Object getValue() {
		return value;
	}


    private static volatile Map<String, JobMode> directory = null;

    private static Map<String, JobMode> getDirectory() {
        if( directory == null ){
            Map<String, JobMode> d = new HashMap<String, JobMode>();
            for(JobMode jobMode : values()) {
                d.put(jobMode.getJobModeString(), jobMode);
            }
            directory = d;
        }
        return directory;
    }

	private static JobMode fromString(String value) {
		return getDirectory().get(value);
	}

	public static List<JobMode> getSupportedValue(List<String> values) {
		if (values == null) {
			return Collections.emptyList();
		}

		List<JobMode> list = new ArrayList<JobMode>();
		for(String value : values) {
			JobMode mode = JobMode.fromString(value);
			if(mode != null) {
				list.add(mode);
			}
		}
		return list;
	}


}
