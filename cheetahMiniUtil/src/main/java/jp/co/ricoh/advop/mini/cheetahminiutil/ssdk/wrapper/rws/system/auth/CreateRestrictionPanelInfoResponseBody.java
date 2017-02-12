package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.system.auth;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Element;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ResponseBody;

import java.util.Map;

/**
 * 制限画面情報の取得要求に対する機器からのレスポンスを示す。<BR>
 * 読み取り専用のJSON構造を定義するための基底クラス（Element）を継承している。<BR>
 * JSON構造をMap型で保持し、各要素にアクセスするためのアクセッサ（getメソッド）を提供する。<BR>
 * Map＜String, Obejct＞<BR>
 * 　　Key＜String＞は、WebAPIで定義されたJSON項目のキー名をそのまま用いる。<BR>
 * 　　Value＜Object＞は、項目によりデータ型が異なるためObject型を用いる。アクセッサメソッド内で、項目に定められた適切な型へのキャストを実施する。<BR>
 * 階層構造のJSONは、MapのValueが子要素のMapとなる。<BR>
 */
public class CreateRestrictionPanelInfoResponseBody extends Element implements ResponseBody {

	/** キー（制限画面情報） */
	private static final String KEY_RESTRICTION_PANEL_INFO = "restrictionPanelInfo";

	/**
	 * 制限画面情報の取得要求に対する機器からのレスポンスを構築する。<BR>
	 * @param values JSON構造Map
	*/
	CreateRestrictionPanelInfoResponseBody(Map<String, Object> values) {
		super(values);
	}

	/*
	 * restrictionPanelInfo (String)
     */
	/**
	 * 制限画面情報を取得する。<BR>
	 * @return 以下のいずれかを戻り値として返却する。<BR>
	 * 　　　key_counter　：キーカウンタ<BR>
	 * 　　　key_counter_func　：キーカウンタ（機能）<BR>
	 * 　　　key_card　：キーカード<BR>
	 * 　　　key_card_func　：キーカード（機能）<BR>
	 * 　　　user_code　：ユーザコード<BR>
	 * 　　　user_code_func　：ユーザコード（機能）<BR>
	 * 　　　coin_box　：コインラック<BR>
	 * 　　　coin_box_func　：コインラック（機能）<BR>
	 * 　　　coin_box_paper_size　：コインラック（用紙サイズ）<BR>
	 * 　　　coin_box_paper_size_func　：コインラック（用紙サイズ、機能）<BR>
	 * 　　　the_others　：その他<BR>
	 * 　　　personal_authentication　：個人認証<BR>
	 */
	public String getRestrictionPanelInfo() {
		return getStringValue(KEY_RESTRICTION_PANEL_INFO);
	}
}
