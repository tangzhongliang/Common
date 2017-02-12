
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.printer;

import java.util.ArrayList;
import java.util.List;

public class AuthRestriction {
    /** 認証状態 */
    private boolean mLoginPermission = false;
    /** 認証NGの理由 */
    private String mloginErrorReason = null;
    /** 認証画面ID */
    private List<String> mRestrictionPanelId = new ArrayList<String>();

    /** 認証NGの理由：認証に失敗した（認証情報未入力含む） */
    public static final String ERROR_REASON_LOGIN_FAILED = "login_failed";
    /** 認証NGの理由：認証には成功したが利用権限がない */
    public static final String ERROR_REASON_PERMISSION_DENIED = "permission_denied";

    /**
     * 認証状態を設定する。<BR>
     * 
     * @param permission 認証状態<BR>
     *            true:認証OK、false:認証NG<BR>
     */
    public void setLoginPermission(boolean permission) {
        mLoginPermission = permission;
    }

    /**
     * 認証NGの理由を設定する。<BR>
     * 
     * @param loginErrorReason 認証NGの理由<BR>
     *            ERROR_REASON_LOGIN_FAILED<BR>
     *            ERROR_REASON_PERMISSION_DENIED<BR>
     */
    public void setLoginErrorReason(String loginErrorReason) {
        mloginErrorReason = loginErrorReason;
    }

    /**
     * 認証画面IDを設定する。<BR>
     * 
     * @param panelId 認証画面ID<BR>
     */
    public void setRestrictionPanelId(List<String> panelId) {
        mRestrictionPanelId.clear();

        if (panelId != null) {
            for (String id : panelId) {
                mRestrictionPanelId.add(id);
            }
        }
    }

    /**
     * 認証状態を取得する。<BR>
     * 
     * @return mLoginPermission 認証状態<BR>
     *         true:認証OK、false:認証NG<BR>
     */
    public boolean getLoginPermission() {
        return mLoginPermission;
    }

    /**
     * 認証NGの理由を取得する。<BR>
     * 
     * @return mLoginErrorReason 認証NGの理由<BR>
     *         ERROR_REASON_LOGIN_FAILED<BR>
     *         ERROR_REASON_PERMISSION_DENIED<BR>
     */
    public String getLoginErrorReason() {
        return mloginErrorReason;
    }

    /**
     * 認証画面IDを取得する。<BR>
     * 
     * @return mRestrictionPanelId 認証画面ID<BR>
     */
    public List<String> getRestrictionPanelId() {
        return mRestrictionPanelId;
    }
}
