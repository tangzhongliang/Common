package jp.co.ricoh.advop.mini.cheetahminiutil.tzl.smb;


import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.addressbook.Entry;

public class SMBInfo {

    private String hostNameAndPath;

    private String userName;

    private String password;

    public SMBInfo(Entry entry) {
        String path = entry.getFolderData().getPath();
        if (path.startsWith("\\\\")) {
            path = path.replaceFirst("\\\\\\\\", "");
        } else if (path.startsWith("//")) {
            path = path.replaceFirst("//", "");
        } else if (path.startsWith("¥¥")) {
            path = path.replaceFirst("¥¥", "");
        }
        this.hostNameAndPath = path.replaceAll("\\\\", "/").replaceAll("¥", "/");
        this.setUserName(entry.getFolderAuthData().getLoginUserName());
        this.setPassword(entry.getFolderAuthData().getLoginPassword());
    }

    public SMBInfo(String hostNameAndPath, String userName, String password) {
        String path = hostNameAndPath;
        if (path.startsWith("\\\\")) {
            path = path.replaceFirst("\\\\\\\\", "");
        } else if (path.startsWith("//")) {
            path = path.replaceFirst("//", "");
        } else if (path.startsWith("¥¥")) {
            path = path.replaceFirst("¥¥", "");
        }
        this.hostNameAndPath = path.replaceAll("\\\\", "/").replaceAll("¥", "/");
        this.setUserName(userName);
        this.setPassword(password);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        if (userName == null || userName.trim().equals("")) {

            userName = null;
        }
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null || password.trim().equals("")) {

            password = null;
        }
        this.password = password;
    }

    public String getPath() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("smb://");
        stringBuilder.append(this.hostNameAndPath);
        String url = stringBuilder.toString();
        return url;
    }

    public void setHostNameAndPath(String path) {
        if (path.startsWith("\\\\")) {
            path = path.replaceFirst("\\\\\\\\", "");
        } else if (path.startsWith("//")) {
            path = path.replaceFirst("//", "");
        } else if (path.startsWith("¥¥")) {
            path = path.replaceFirst("¥¥", "");
        }
        this.hostNameAndPath = path.replaceAll("\\\\", "/").replaceAll("¥", "/");
    }

    public String getHostNameAndPath() {
        return hostNameAndPath;
    }
}
