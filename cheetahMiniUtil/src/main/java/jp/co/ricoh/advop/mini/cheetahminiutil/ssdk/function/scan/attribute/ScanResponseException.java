/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ErrorResponseBody;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.InvalidResponseException;

import java.util.HashMap;
import java.util.Map;

/**
 * InvalidExceptionをカプセル化する例外クラスです。
 * 本クラスでは、発生したエラーをMap<String,String>として保持しています。
 * エラーが発生したレスポンスではない場合、エラーは保持しません。
 * The exception class which encapsulates  InvalidException.
 * This class stores errors generated as Map<String,String>.
 * If the response is not for the error generated, the error is not stored.
 */
public class ScanResponseException extends ScanException {
    private Map<String,String> mErrors;
    private int status;

    public ScanResponseException(InvalidResponseException ex) {
        super(ex.getMessage());
        if(ex.hasBody()){
            this.mErrors = new HashMap<String, String>();
            for(ErrorResponseBody.Errors errors : ex.getBody().getErrors()) {
                this.mErrors.put(errors.getMessageId(), errors.getMessage());
            }
        }

        this.status = ex.getStatusCode();
    }

    public boolean hasErrors() {
        return (this.mErrors != null);
    }

    public Map<String,String> getErrors() {
        return this.mErrors;
    }

    public int getStatusCode() {
        return this.status;
    }
}
