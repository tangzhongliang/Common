package jp.co.ricoh.advop.mini.cheetahminiutil.logic;

import java.util.Locale;

public class InitParameters {
	private Class<?> mainActivityClass;
	
	private boolean isScanAvailable = false;
	private boolean isPrintAvailable = false;
	private String appLogTag = "";
	private boolean isStorageAvailable = false;
	private boolean isLoadImageAvailable;
	private Locale appLocale = Locale.ENGLISH;

	public Class<?> getMainActivityClass() {
		return mainActivityClass;
	}
	public void setMainActivityClass(Class<?> mainActivityClass) {
		this.mainActivityClass = mainActivityClass;
	}
	public boolean isScanAvailable() {
		return isScanAvailable;
	}
	public void setScanAvailable(boolean isScanAvailable) {
		this.isScanAvailable = isScanAvailable;
	}
	public boolean isPrintAvailable() {
		return isPrintAvailable;
	}
	public void setPrintAvailable(boolean isPrintAvailable) {
		this.isPrintAvailable = isPrintAvailable;
	}
	public String getAppLogTag() {
		return appLogTag;
	}
	public void setAppLogTag(String appLogTag) {
		this.appLogTag = appLogTag;
	}

	public void setStorageAvailable(boolean isGetMountStateAvailable) {
		this.isStorageAvailable = isGetMountStateAvailable;
	}

	public boolean isStorageAvailable() {
		return isStorageAvailable;
	}

	public boolean isLoadImageAvailable() {
		return isLoadImageAvailable;
	}

	public void setLoadImageAvailable(boolean loadImageAvailable) {
		isLoadImageAvailable = loadImageAvailable;
	}

	public Locale getAppLocale() {
		return appLocale;
	}

	public void setAppLocale(Locale appLocale) {
		this.appLocale = appLocale;
	}
}
