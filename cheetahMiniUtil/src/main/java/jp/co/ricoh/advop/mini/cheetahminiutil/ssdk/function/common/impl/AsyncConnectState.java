package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.common.impl;

/**
 * 非同期接続の状態を表すクラスです。
 * The class to indicate asynchronous connection state.
 *
 */
public class AsyncConnectState {

    public static enum STATE {
        CONNECTED,
        DISCONNECTED,
    }

    public static enum ERROR_CODE {
        NO_ERROR,
        INVALID,
        TIMEOUT,
        BUSY,
        UNKNOWN,
    }

    private STATE mState = STATE.DISCONNECTED;
    private ERROR_CODE mErrorCode = ERROR_CODE.UNKNOWN;

    public AsyncConnectState(boolean state, int errorCode) {
        if (state==true) {
            mState = STATE.CONNECTED;
        } else {
            mState = STATE.DISCONNECTED;
        }

        switch (errorCode) {
            case 0:
                mErrorCode = ERROR_CODE.NO_ERROR;
                break;
            case 1:
                mErrorCode = ERROR_CODE.INVALID;
                break;
            case 2:
                mErrorCode = ERROR_CODE.TIMEOUT;
                break;
            case 3:
                mErrorCode = ERROR_CODE.BUSY;
                break;
            default:
                mErrorCode = ERROR_CODE.UNKNOWN;
                break;
        }
    }

    private AsyncConnectState(){};

    public static AsyncConnectState valueOf(STATE state, ERROR_CODE errorCode) {
        AsyncConnectState acyncConnectState = new AsyncConnectState();
        acyncConnectState.mState = state;
        acyncConnectState.mErrorCode = errorCode;
        return acyncConnectState;
    }

    public STATE getState() {
        return mState;
    }

    public ERROR_CODE getErrorCode() {
        return mErrorCode;
    }
}
