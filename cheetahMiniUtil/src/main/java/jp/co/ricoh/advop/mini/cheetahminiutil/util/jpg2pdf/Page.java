package jp.co.ricoh.advop.mini.cheetahminiutil.util.jpg2pdf;

import java.util.ArrayList;

/**
 * ページクラス
 * @author keiths
 *
 */
class Page {
	final long A4LW = 841;	// default width (A4) in 72dpi
	final long A4LH = 595;	// default height (A4) in 72dpi
	
	/**
	 * ページ番号自動カウンタ（ページが追加される毎に増えていく）
	 */
	private static long nextId = 1;
	
	/**
	 * ページ番号（コントトラクタで自動設定される）
	 */
	private long id;
	
	/**
	 * 用紙サイズ（幅、高さを72dpi単位で表す）
	 */
	private long width, height;
	
	/**
	 * ページ内のパーツリスト
	 */
	private ArrayList<Part> parts = new ArrayList<Part>();
	

	/**
	 * コンストラクタ
	 */
	Page() {
		id = nextId++;
		width = A4LW;
		height = A4LH;
	}

	/**
	 * 用紙サイズを指定したコンストラクタ
	 * @param width: 幅を72dpi単位で指定
	 * @param height: 高さを72dpi単位で指定
	 */
	Page(long width, long height) {
		this();
		this.width = width;
		this.height = height;
	}

	/**
	 * ページ番号の取得
	 * @return
	 */
	long getId() {
		return id;
	}
	
	/**
	 * ページにパーツを加える。文書作成時には登録した順にパーツが描画されていく。
	 * @param p	ページに加えるパーツ。
	 */
	void addPart(Part p) {
		parts.add(p);
	}
	
	/**
	 * ページ内のパーツを取得する
	 * @param n: 取得するパーツの位置
	 * @return: n番目に登録されたPartを返す
	 */
	Part getPart(int n) {
		return parts.get(n);
	}
	
	/**
	 * 登録されているパーツの総数を取得する
	 * @return	登録されているパーツの総数
	 */
	int getPartNum() {
		return parts.size();
	}
	
	/**
	 * ページ幅を取得する
	 * @return	ページ幅（72dpi単位）
	 */
	long getWidth() {
		return width;
	}

	/**
	 * ページの高さを取得する。
	 * @return	ページ高さ（72dpi単位）
	 */
	long getHeight() {
		return height;
	}
	
}
