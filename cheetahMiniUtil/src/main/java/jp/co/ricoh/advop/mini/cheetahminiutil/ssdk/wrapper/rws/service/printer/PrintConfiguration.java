
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.printer;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.standard.UserCodeRestrictedFunc;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.printer.GetConfigurationResponseBody.BitSettingsArray;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.printer.GetConfigurationResponseBody.TraySettingsArray;


public class PrintConfiguration {

    /** Value文字列定義:"on" */
    private static final String VALUE_ON = "on";
    /** Value文字列定義:"off" */
    private static final String VALUE_OFF = "off";

    boolean mUerCodeRequired = false;

    boolean mMemoryDeviceToPrint = true;

    UserCodeRestrictedFunc mUserCodeFunc = UserCodeRestrictedFunc.NONE;

    TraySettingsArray mTraySettings = null;
    
    BitSettingsArray mBitSettings = null;

    public TraySettingsArray getTraySettings() {
        return mTraySettings;
    }

    public void setTraySettings(TraySettingsArray traySettings) {
        this.mTraySettings = traySettings;
    }
    
    public BitSettingsArray getBitSettings() {
        return mBitSettings;
    }
    
    public void setBitSettingsArray(BitSettingsArray array) {
        this.mBitSettings = array;
    }

    public UserCodeRestrictedFunc getUserCodeFunc() {
        return mUserCodeFunc;
    }

    public void setUserCodeFunc(UserCodeRestrictedFunc mUserCodeFunc) {
        this.mUserCodeFunc = mUserCodeFunc;
    }

    public void setUerCodeRequired(String setting) {
        if (setting.equals(VALUE_ON)) {
            mUerCodeRequired = true;
        }
        else {
            mUerCodeRequired = false;
        }
    }

    public boolean getUserCodeRequired() {
        return mUerCodeRequired;
    }

    public void setMemoryDeviceToPrint(String setting) {
        if (setting.equals(VALUE_ON)) {
            mMemoryDeviceToPrint = true;
        }
        else {
            mMemoryDeviceToPrint = false;
        }
    }

    public boolean getMemoryDeviceToPrint() {
        return mMemoryDeviceToPrint;
    }

}
