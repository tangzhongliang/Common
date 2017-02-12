/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ErrorResponseBody;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.InvalidResponseException;

import java.util.HashMap;
import java.util.Map;

/**
 * InvalidExceptionをカプセル化する例外クラスです。
 * The exception class which encapsulates InvalidException.
 */
public class PrintResponseException extends PrintException{
    private Map<String,String> mErrors;
    private int status;

    public PrintResponseException(InvalidResponseException ex) {
        super(ex.getMessage());
        if(ex.hasBody()) {
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
