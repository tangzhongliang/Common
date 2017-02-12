package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.system.auth;

import jp.co.ricoh.advop.mini.cheetahminiutil.util.LogC;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.RequestBody;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.WritableElement;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.json.EncodedException;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.json.JsonUtils;

import java.util.HashMap;



/**
 * 制限画面情報の取得要求を示す。<BR>
 * 読み書き可能なJSON構造を定義するための基底クラス（WritableElement）を継承している。<BR>
 * JSON構造をMap型で保持し、各要素にアクセスするためのアクセッサ（getメソッド/setメソッド/removeメソッド）を提供する。<BR>
 * HashMap＜String, Obejct＞<BR>
 * 　　Key＜String＞は、WebAPIで定義されたJSON項目のキー名をそのまま用いる。<BR>
 * 　　Value＜Object＞は、項目によりデータ型が異なるためObject型を用いる。アクセッサメソッド内で、項目に定められた適切な型へのキャストを実施する。<BR>
 * 階層構造のJSONは、MapのValueが子要素のMapとなるイメージ<BR>
 * RequestBody実装クラスのクラス名は、「APIメソッド名＋RequestBody」とする。<BR>
*/
public class CreateRestrictionPanelInfoRequestBody extends WritableElement implements RequestBody {

	/** Content-Type（リクエストボディのデータフォーマット） */
	private static final String CONTENT_TYPE_JSON = "application/json; charset=utf-8";

	/** キー（アプリ種別） */
	private static final String KEY_APPLICATION_TYPE     = "applicationType";
	/** キー（制限画面ID） */
	private static final String KEY_RESTRICTION_PANEL_ID = "restrictionPanelId";

	/**
	 * データ保存用のハッシュマップを生成する。<BR>
	 */
	public CreateRestrictionPanelInfoRequestBody() {
		super(new HashMap<String, Object>());
	}

	/**
	 * リクエストボディのデータフォーマットを返す。<BR>
	 * @return "application/json; charset=utf-8"(固定値)<BR>
	 *
	 * @see jp.co.ricoh.ssdk.sample.wrapper.common.RequestBody#getContentType()
	*/
	@Override
	public String getContentType() {
		return CONTENT_TYPE_JSON;
	}

	/**
	 * ボディ部分のデータを取得する。<BR>
	 * @return ボディとして設定されているデータ
	 * @see jp.co.ricoh.ssdk.sample.wrapper.common.RequestBody#toEntityString()
	 */
	@Override
	public String toEntityString() {
		try {
			return JsonUtils.getEncoder().encode(values);
		} catch (EncodedException e) {
		    LogC.w(e);
			return "{}";
		}
	}

	/*
	 * applicationType (String)
	 */
	/**
	 * アプリ種別（applicationType）を取得する。<BR>
	 * @return 以下のいずれかを戻り値として返却する。<BR>
	 * 　　　copier　:コピー<BR>
	 * 　　　document_box　:ドキュメントボックス<BR>
	 * 　　　fax　:ファクス<BR>
	 * 　　　scanner　:スキャナ<BR>
	 * 　　　printer　:プリンタ<BR>
	*/
	public String getApplicationType() {
		return getStringValue(KEY_APPLICATION_TYPE);
	}
	/**
	 * アプリ種別（applicationType）を設定する。<BR>
	 * @param value 以下のいずれかを引数とする。<BR>
	 * 　　　copier　:コピー<BR>
	 * 　　　document_box　:ドキュメントボックス<BR>
	 * 　　　fax　:ファクス<BR>
	 * 　　　scanner　:スキャナ<BR>
	 * 　　　printer　:プリンタ<BR>
	*/
	public void setApplicationType(String value) {
		setStringValue(KEY_APPLICATION_TYPE, value);
	}
	/**
	 * アプリ種別（applicationType）を削除する。<BR>
	 * キー:"applicationType"がリストから見つからなかった場合、変更しない。<BR>
	 * @return 削除したアプリ種別の文字列
	*/
	public String removeApplicationType() {
		return removeStringValue(KEY_APPLICATION_TYPE);
	}

	/*
	 * restrictionPanelId (String)
	 */
	/**
	 * 制限画面ID（restrictionPanelId）を取得する。<BR>
	 * @return 制限画面ID（restrictionPanelId）の値
	*/
	public String getRestrictionPanelId() {
		return getStringValue(KEY_RESTRICTION_PANEL_ID);
	}
	/**
	 * 制限画面ID（restrictionPanelId）を設定する。レガシーアプリから渡されるcertypeをセットする。<BR>
	 * @param value 設定する制限画面ID
	*/
	public void setRestrictionPanelId(String value) {
		setStringValue(KEY_RESTRICTION_PANEL_ID, value);
	}
	/**
	 * 制限画面ID（restrictionPanelId）を削除する。<BR>
	 * キー:"restrictionPanelId"がリストから見つからなかった場合、変更しない。<BR>
	 * @return 削除した制限画面IDの文字列
	*/
	public String removeRestrictionPanelId() {
		return removeStringValue(KEY_RESTRICTION_PANEL_ID);
	}
}
