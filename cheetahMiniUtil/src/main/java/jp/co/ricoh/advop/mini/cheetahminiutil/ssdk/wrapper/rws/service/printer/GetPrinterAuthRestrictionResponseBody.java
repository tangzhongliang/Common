
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.printer;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Element;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ResponseBody;

import java.util.List;
import java.util.Map;

public class GetPrinterAuthRestrictionResponseBody extends Element implements ResponseBody {

    /** キー名称：認証状態 */
    private static final String KEY_LOGIN_PERMISSION = "loginPermission";
    /** キー名称：認証NGの理由 */
    private static final String KEY_LOGIN_ERROR_REASON = "loginErrorReason";
    /** キー名称：認証画面ID */
    private static final String KEY_RESTRICTION_PANEL_ID = "restrictionPanelId";

    /**
     * 認証制限状態の取得要求に対する機器からのレスポンスを構築する。<BR>
     * 
     * @param values JSON構造Map
     */
    public GetPrinterAuthRestrictionResponseBody(Map<String, Object> values) {
        super(values);
    }

    /**
     * 認証状態情報を取得する。<BR>
     * 
     * @return loginPermission (Boolean)<BR>
     *         　　true:認証OK、false:認証NG
     */
    public Boolean getLoginPermission() {
        return getBooleanValue(KEY_LOGIN_PERMISSION);
    }

    /**
     * 認証NGの理由を取得する。<BR>
     * 
     * @return loginErrorReason (String)<BR>
     */
    public String getLoginErrorReason() {
        return getStringValue(KEY_LOGIN_ERROR_REASON);
    }

    /**
     * 制限画面を判断する為のID情報を取得する。<BR>
     * 
     * @return restrictionPanelId (List<String>)<BR>
     */
    public List<String> getRestrictionPanelId() {
        return getArrayValue(KEY_RESTRICTION_PANEL_ID);
    }
}
