/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.property;

import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ResponseBody;

public class UpdateLdapResponseBody extends Ldap implements ResponseBody {

    UpdateLdapResponseBody(Map<String, Object> values) {
        super(values);
    }

}
