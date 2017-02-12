package jp.co.ricoh.advop.mini.cheetahminiutil.util.jpg2pdf;

import java.io.FileOutputStream;

/**
 * イメージクラス
 * @author keiths
 *
 */
abstract class Image {
	/**
	 * 画像ファイルのパスを設定する
	 */
	abstract void setPath(String path);
	
	/**
	 * 画像の幅を取得する
	 * @return	画像の幅、ドット数
	 */
	abstract long getWidth();
	
	/**
	 * 画像の高さを取得する
	 * @return	画像の高さ、ドット数
	 */
	abstract long getHeight();
	
	/**
	 * 画像ファイルのサイズを取得する
	 * @return	画像ファイルサイズ、バイト数
	 */
	abstract long getFileSize();
	
	/**
	 * 画像がカラーかモノクロかを判定する
	 * @return	true カラー、false モノクロ
	 */
	abstract boolean isColor();
	
	/**
	 * 画像データをコピーする
	 * @param fos	書き込み先のストリーム
	 */
	abstract void copyStream(FileOutputStream fos);
}
