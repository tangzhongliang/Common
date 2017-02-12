package jp.co.ricoh.advop.mini.cheetahminiutil.util.jpg2pdf;

/**
 * パーツクラス
 * @author keiths
 *
 */
class Part {
	/**
	 * パーツ番号自動カウンタ（パーツが追加される毎に増えていく）
	 */
	private static long nextId = 1;
	
	/**
	 * パーツ番号（コントトラクタで自動設定される）
	 */
	private long id;
	
	/**
	 * パーツの含むイメージ
	 */
	private Image image;
	
	/**
	 * イメージに対するCurrent Transformation Matrix
	 */
	private float[] ctm = {1, 0, 0, 1, 0, 0};
	
	/**
	 * コンストラクタ
	 */
	Part() {
		id = nextId++;
	}

	/**
	 * パーツ番号の取得
	 * @return	パーツ番号
	 */
	long getId() {
		return id;
	}
	
	/**
	 * パーツへのイメージ登録
	 * @param img	パーツへ登録するイメージ
	 */
	void setImage(Image img) {
		image = img;		
	}
	
	/**
	 * パーツに登録されているイメージの取得
	 * @return	登録されているイメージ
	 */
	Image getImage() {
		return image;
	}
	
	/**
	 * CTMの登録
	 * @param f	CTMをfloat6個の配列で指定する
	 */
	void setCtm(float f[]) {
		if (f.length != 6) {
			throw new IllegalStateException("ctm size is wrong " + f);
		}

		ctm = f;
	}

	/**
	 * 登録されているCTMを取得する
	 * @return	float6個からなる配列からなるCTM
	 */
	float[] getCtm() {
		return ctm;
	}
	
}
