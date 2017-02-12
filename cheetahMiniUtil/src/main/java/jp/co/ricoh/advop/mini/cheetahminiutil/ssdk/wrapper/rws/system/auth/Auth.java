package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.system.auth;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.client.RestContext;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.client.RestRequest;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.client.RestResponse;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ApiClient;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.GenericJsonDecoder;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.InvalidResponseException;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Request;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Response;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Utils;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.property.GetAuthPropResponseBody;

import java.io.IOException;
import java.util.Map;


/**
 * 本体機器から制限画面情報を取得する機能を提供する。<BR>
 * 具体的には以下の制限画面情報を取得する。<BR>
 * 　　certypeに対応した制限画面情報（"/rws/system/auth/restrictionPanelInfo"）<BR><BR>
 * ＜設計について＞<BR>
 * 本クラスでは、REST Apiに対応するJavaメソッドを定義しており、
 * メソッドの名称は、「HTTPメソッド名＋エントリ名」を基本としている。<BR>
 * 　　　GET（リソースの取得）		：getXxxx()<BR>
 * 　　　POST（リソースの新規作成）	：createXxxx()<BR>
 * 　　　PUT（リソースの更新）		：updateXxxx()<BR>
 * 　　　DELETE（リソースの削除）	：deleteXxxx()<BR>
 * APIメソッドのシグニチャは、Request を引数にとり Response を返却することを基本とする。<BR>
 * コンストラクタにコンテキストを指定した場合は、指定した設定を用いて通信を行う。<BR>
 * コンストラクタにコンテキスト未指定の場合は、内部のデフォルト設定値にて通信する。<BR>
 * 利用者が使うことができるコンテキスト設定は2種類。<BR>
 * １つはMultiLink-Panel操作部⇒同一機器内コントローラへの通信用のコンテキストで、通信タイムアウト時間/スキームのみ指定できる。<BR>
 * もう１つはリモートからMFPにアクセスする際のコンテキストで、タイムアウトに加えて接続先ホスト/ポート番号/スキームを指定できる。<BR>
 * 接続先ホストはコンテキストによる指定となるため、リモートアクセス時はコンテキスト指定が必須である。<BR>
 * <BR>
 * ・APIメソッドのシグニチャは、Request を引数にとり Response を返却することを基本とする。<BR>
 * RequestのBodyはAPIごとに異なるので、APIメソッドごとに専用のRequestBodyを定義する。<BR>
 * ResponseのBodyはAPIごとに異なるので、APIメソッドごとに専用のResponseBodyを定義する。<BR>
 * 　　　RequestBodyクラス	:追加・設定する情報をあらわすボディ。<BR>
 * 　　　ResponseBodyクラス	:正常な応答からは、応答情報をあらわすボディ。<BR>
 * APIメソッドを定義する際は、<BR>
 * 　　　①実メソッド<BR>
 * 　　　②RequestBody実装クラス<BR>
 * 　　　③ResponseBody実装クラス<BR>
 * の３点セットを準備することを基本とする。
 * ただし、GETメソッドにリクエストボディの定義がないなど、②が不要となるケースもある。<BR>
 *  <BR>
 * ［基本的なAPIメソッドのシグニチャイメージ］<BR>
 * public Response<T extends ResponseBody> apiMethod(Request request) throws IOException, InvalidResponseException;<BR>
 * <BR>
 * HTTP通信に関するエラーは、IOException をスローする。（Apache HttpClientがエラーを返すケース）<BR>
 * APIで定められたエラーが返却された場合は、InvalidResponseExceptionをスローする。（WebAPIがエラーを返すケース）<BR>
 * InvalidResponseException はステータスコード・APIから返却されたエラー情報を保持しており、例外catchする側から参照できる。<BR>
 * <BR>
 */
public class Auth extends ApiClient {
	/** REST通信 リソースのURI（certypeに対応した制限画面情報） */
	private static final String REST_PATH_RESTRICTION_PANEL_INFO = "/rws/system/auth/restrictionPanelInfo";
    private static final String REST_PATH_GET_AUTH_PROP = "/rws/property/auth";

    /**
     * 内部のデフォルト設定値にて通信する。<BR>
     * MultiLink-Panel操作部で自機器内のREST APIへアクセスする際に利用するコンテキストクラスの
     * インスタンスを生成する。<BR>
     * @see jp.co.ricoh.ssdk.sample.wrapper.common.ApiClient
    */
	public Auth() {
		super();
	}

    /**
     * 指定した設定を用いて通信を行う。<BR>
     * @param context HTTP通信用のコンテキスト
     * @see jp.co.ricoh.ssdk.sample.wrapper.common.ApiClient
    */
	public Auth(RestContext context) {
		super(context);
	}

	/**
	 * certypeに対応する制限画面情報を取得する。<BR>
	 * 　　　RequestBody	: CreateRestrictionPanelInfoRequestBody<BR>
	 * 　　　ResponseBody	: CreateRestrictionPanelInfoResponseBody<BR>
	 * @param request リクエストデータ（POST: /rws/system/auth/restrictionPanelInfo）
	 * @return API実行結果を表すレスポンスデータ
	 * @throws IOException IO例外
	 * @throws InvalidResponseException SmartSDK API側でエラーが発生した場合
	*/
	public Response<CreateRestrictionPanelInfoResponseBody> createRestrictionPanelInfo(Request request) throws IOException, InvalidResponseException {
		RestResponse restResponse = execute(
				build(RestRequest.METHOD_POST, REST_PATH_RESTRICTION_PANEL_INFO, request));
		Map<String, Object> body = GenericJsonDecoder.decodeToMap(restResponse.makeContentString("UTF-8"));

		switch (restResponse.getStatusCode()) {
		case 200:
			return new Response<CreateRestrictionPanelInfoResponseBody>(restResponse, new CreateRestrictionPanelInfoResponseBody(body));
		default:
			throw Utils.createInvalidResponseException(restResponse, body);
		}
	}
    
    public Response<GetAuthPropResponseBody> getAuthProp(Request request) throws IOException, InvalidResponseException {
        RestResponse restResponse = execute(
                build(RestRequest.METHOD_GET, REST_PATH_GET_AUTH_PROP, request));
        Map<String, Object> body = GenericJsonDecoder.decodeToMap(restResponse.makeContentString("UTF-8"));

        switch (restResponse.getStatusCode()) {
        case 200:
            return new Response<GetAuthPropResponseBody>(restResponse, new GetAuthPropResponseBody(body));
        default:
            throw Utils.createInvalidResponseException(restResponse, body);
        }
    }
}
