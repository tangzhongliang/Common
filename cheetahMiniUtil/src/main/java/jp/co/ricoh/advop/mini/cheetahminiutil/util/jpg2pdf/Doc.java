package jp.co.ricoh.advop.mini.cheetahminiutil.util.jpg2pdf;

import java.util.ArrayList;

/**
 * ドキュメントクラス
 * @author keiths
 *
 */
abstract class Doc {
	/**
	 * PDFを出力するパス
	 */
	protected String dstPath;
	
	/**
	 * 出力したページ数：-1は未出力、0はヘッダーのみ出力
	 */
	protected int pagesFlashed = -1;
	
	/**
	 * このDocが含むPageのリスト
	 */
	protected ArrayList<Page> pages = new ArrayList<Page>();

	/**
	 * コンストラクタ
	 */
	Doc() {
	}
	
	/**
	 * コンストラクタ（出力先の指定あり）
	 * @param dstPath PDFを出力するパス
	 */
	Doc(String dstPath) {
		this();
		this.dstPath = dstPath;
	}
	
	/**
	 * PageをDocに追加する
	 * @param p　追加するPage
	 */
	void addPage(Page p) {
		pages.add(p);
	}
	
	/**
	 * 未実装：これまでに追加したページを書き出す。
	 */
	abstract void flush();
	
	/**
	 * 文書作成を終了し、結果を書き出す。
	 */
	abstract void close();
	
}
