/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanRequestAttribute;

public final class SecuredPdfSetting implements ScanRequestAttribute {
	
	private static final String NAME_SECURED_PDF_SETTING = "securedPdfSetting";
	
	private static final String NAME_ENCRYPTION = "encryption";
	private static final String NAME_ENCRYPTION_PASSWORD = "encryptionPassword";
	private static final String NAME_ENCRYPTION_LEVEL = "encryptionLevel";
	private static final String NAME_DOCUMENT_SECURITY = "documentSecurity";
	private static final String NAME_DOCUMENT_SECURITY_PASSWORD = "documentSecurityPassword";
	private static final String NAME_ALLOW_PRINT = "allowPrint";
	private static final String NAME_SETTING_ALLOW_EDIT = "allowEdit";
	private static final String NAME_ALLOW_COPY_AND_EXTRACT = "allowCopyAndExtract";


	private boolean encryption;
	private String encryptionPassword;
	private EncryptionLevel encryptionLevel;
	private boolean documentSecurity;
	private String documentSecurityPassword;
	private AllowPrint allowPrint;
	private boolean allowEdit;
	private boolean allowCopyAndExtract;
	
	
	public SecuredPdfSetting() {
		encryption = false;
		encryptionPassword = null;
		encryptionLevel = null;
		documentSecurity = false;
		documentSecurityPassword = null;
		allowPrint = null;
		allowEdit = true;
		allowCopyAndExtract = true;
	}
	
	public boolean isEncryption() {
		return encryption;
	}
	
	public void setEncryption(boolean value) {
		encryption = value;
	}
	
	public String getEncryptionPassword() {
		return encryptionPassword;
	}
	
	public void setEncryptionPassword(String password) {
		encryptionPassword = password;
	}
	
	public EncryptionLevel getEncryptionLevel() {
		return encryptionLevel;
	}

	public void setEncryptionLevel(EncryptionLevel level) {
		encryptionLevel = level;
	}

	public boolean isDocumentSecurity() {
		return documentSecurity;
	}

	public void setDocumentSecurity(boolean value) {
		documentSecurity = value;
	}

	public String getDocumentSecurityPassword() {
		return documentSecurityPassword;
	}

	public void setDocumentSecurityPassword(String password) {
		documentSecurityPassword = password;
	}

	public AllowPrint getAllowPrint() {
		return allowPrint;
	}

	public void setAllowPrint(AllowPrint value) {
		allowPrint = value;
	}

	public boolean isAllowEdit() {
		return allowEdit;
	}

	public void setAllowEdit(boolean value) {
		allowEdit = value;
	}

	public boolean isAllowCopyAndExtract() {
		return allowCopyAndExtract;
	}

	public void setAllowCopyAndExtract(boolean value) {
		allowCopyAndExtract = value;
	}
	
	
	@Override
	public Class<?> getCategory() {
		return SecuredPdfSetting.class;
	}

	@Override
	public String getName() {
		return NAME_SECURED_PDF_SETTING;
	}

	@Override
	public Object getValue() {
		Map<String, Object> values = new HashMap<String, Object>();
		
		values.put(NAME_ENCRYPTION, Boolean.valueOf(encryption));
		if (encryptionPassword != null) {
			values.put(NAME_ENCRYPTION_PASSWORD, encryptionPassword);
		}
		if (encryptionLevel != null) {
			values.put(NAME_ENCRYPTION_LEVEL, encryptionLevel.getValue());
		}
		values.put(NAME_DOCUMENT_SECURITY, Boolean.valueOf(documentSecurity));
		if (documentSecurityPassword != null) {
			values.put(NAME_DOCUMENT_SECURITY_PASSWORD, documentSecurityPassword);
		}
		if (allowPrint != null) {
			values.put(NAME_ALLOW_PRINT, allowPrint.getValue());
		}
		values.put(NAME_SETTING_ALLOW_EDIT, Boolean.valueOf(allowEdit));
		values.put(NAME_ALLOW_COPY_AND_EXTRACT, Boolean.valueOf(allowCopyAndExtract));
		
		return values;
	}
	

	public static enum EncryptionLevel {
		
		RC4_40BIT("rc4_40bit"),
		RC4_128BIT("rc4_128bit"),
		AES_128BIT("aes_128bit"),
		AES_256BIT("aes_256bit");
		
		private final String value;
		
		private EncryptionLevel(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return value;
		}

        private static volatile Map<String, EncryptionLevel> directory = null;

        private static Map<String, EncryptionLevel> getDirectory() {
            if(directory == null) {
                Map<String, EncryptionLevel> d = new HashMap<String, EncryptionLevel>();

                for(EncryptionLevel encryptionLevel : values()) {
                    d.put(encryptionLevel.getValue(), encryptionLevel);
                }

                directory = d;
            }
            return directory;
        }

        private static EncryptionLevel fromString(String value) {
            return getDirectory().get(value);
        }

        public static List<EncryptionLevel> getSupportedValue(List<String> values) {
        	if (values == null) {
        		return Collections.emptyList();
        	}

            List<EncryptionLevel> list = new ArrayList<EncryptionLevel>();
            for(String value : values) {
                EncryptionLevel level = fromString(value);
                if (level != null) {
                    list.add(level);
                }
            }
            return list;
        }
		
	}
	
	public static enum AllowPrint {
		
		NOT_ALLOW("not_allow"),
		ALLOW("allow"),
		ALLOW_LOW_RESOLUTION("allow_low_resolution");
		
		private final String value;
		
		private AllowPrint(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return value;
		}

        private static volatile Map<String, AllowPrint> directory = null;

        private static Map<String, AllowPrint> getDirectory() {
            if(directory == null) {
                Map<String, AllowPrint> d = new HashMap<String, AllowPrint>();

                for(AllowPrint allowPrint : values()) {
                    d.put(allowPrint.getValue(), allowPrint);
                }

                directory = d;
            }
            return directory;
        }

        private static AllowPrint fromString(String value) {
            return getDirectory().get(value);
        }

        public static List<AllowPrint> getSupportedValue(List<String> values ) {
        	if (values == null) {
        		return Collections.emptyList();
        	}

            List<AllowPrint> list = new ArrayList<AllowPrint>();
            for(String value : values) {
                AllowPrint allowPrint = fromString(value);
                if (allowPrint != null) {
                    list.add(allowPrint);
                }
            }
            return list;
        }
	}
	
}
