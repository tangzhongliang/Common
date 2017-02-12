/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.client;

/**
 * REST通信のインターフェースクラスです。
 */
public interface RestContext {

    /**
     * 接続先ホスト名を取得します。
     * @return ホスト名
     */
	public String getHost();
	
	/**
	 * 通信に用いるポート番号を取得します。
	 * @return ポート番号
	 */
	public int getPort();
	
	/**
	 * スキーマを表す文字列を取得します。
	 * @return スキーマを示す文字列
	 */
    public String getScheme();
    
    /**
     * 接続タイムアウト時間を取得します。
     * @return　タイムアウト時間(ミリ秒単位)
     */
	public int getConnectionTimeout();
	
	/**
	 * データ取得のタイムアウト時間を取得します。
	 * @return タイムアウト時間(ミリ秒単位)
	 */
	public int getSoTimeout();

}