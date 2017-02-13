package jp.co.ricoh.advop.mini.cheetahminiutil.util;

import android.content.pm.PackageManager;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.R;
import jp.co.ricoh.advop.mini.cheetahminiutil.logic.CHolder;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.standard.PrintJobStateReason;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.standard.PrinterStateReason;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard.ScanJobStateReason;

public class Const {
    /**
     * アプリケーションの種別
     * システム警告ダイアログの設定に使用します。
     * Application type
     * Used for setting system warning dialog.
     */
    public final static String ALERT_DIALOG_APP_TYPE_SCANNER = "SCANNER";
    public final static String ALERT_DIALOG_APP_TYPE_PRINTER = "PRINTER";

    private final static String BASE_PATH = "/mnt/hdd/";
    public static final String packageName = "de.shandschuh.sparserss";
    public final static String PATH_TMP_FOLDER = BASE_PATH
            + packageName + "/tmp/";

    private static String getPackageName() {
        CHolder instance = CHolder.instance();
        if (instance == null) {
            return "tmp2";
        } else {
            return instance.getApplication().getPackageName();
        }
    }

    public static String PATH_PACKAGE_FOLDER = BASE_PATH
            + packageName + "/";

    public final static Map<ScanJobStateReason, Integer> SCAN_ERROR_MAP = new LinkedHashMap<ScanJobStateReason, Integer>();
    public final static Map<PrinterStateReason, Integer> PRINT_ERROR_MAP = new LinkedHashMap<PrinterStateReason, Integer>();
    public final static Map<String, Integer> JOB_ERROR_MAP = new HashMap<String, Integer>();

    static {
        PRINT_ERROR_MAP.put(PrinterStateReason.COVER_OPEN, R.string.PRINT_COVER_OPEN);
        PRINT_ERROR_MAP.put(PrinterStateReason.DEVELOPER_EMPTY, R.string.PRINT_DEVELOPER_EMPTY);
        PRINT_ERROR_MAP.put(PrinterStateReason.DEVELOPER_LOW, R.string.PRINT_DEVELOPER_LOW);
        PRINT_ERROR_MAP.put(PrinterStateReason.INPUT_TRAY_MISSING, R.string.PRINT_INPUT_TRAY_MISSING);
        PRINT_ERROR_MAP.put(PrinterStateReason.INTERPRETER_RESOURCE_UNAVAILABLE, R.string.print_fail);
        PRINT_ERROR_MAP.put(PrinterStateReason.MARKER_WASTE_ALMOST_FULL, R.string.PRINT_MARKER_WASTE_ALMOST_FULL);
        PRINT_ERROR_MAP.put(PrinterStateReason.MARKER_WASTE_FULL, R.string.PRINT_MARKER_WASTE_FULL);
        PRINT_ERROR_MAP.put(PrinterStateReason.MEDIA_EMPTY, R.string.PRINT_MEDIA_EMPTY);
        PRINT_ERROR_MAP.put(PrinterStateReason.MEDIA_JAM, R.string.PRINT_MEDIA_JAM);
//        PRINT_ERROR_MAP.put(PrinterStateReason.OPC_LIFE_OVER, R.string.PRINT_OPC_LIFE_OVER);
//        PRINT_ERROR_MAP.put(PrinterStateReason.OPC_NEAR_EOL, R.string.PRINT_OPC_NEAR_EOL);
        PRINT_ERROR_MAP.put(PrinterStateReason.OTHER, R.string.PRINT_OTHER);
        PRINT_ERROR_MAP.put(PrinterStateReason.OUTPUT_AREA_FULL, R.string.PRINT_OUTPUT_AREA_FULL);
        PRINT_ERROR_MAP.put(PrinterStateReason.OUTPUT_TRAY_MISSING, R.string.PRINT_OUTPUT_TRAY_MISSING);
        PRINT_ERROR_MAP.put(PrinterStateReason.PAUSED, R.string.PRINT_PAUSED);
        //PRINT_ERROR_MAP.put(PrinterStateReason.STOPPED_PARTLY, R.string.PRINT_STOPPED_PARTLY);
        PRINT_ERROR_MAP.put(PrinterStateReason.TONER_EMPTY, R.string.PRINT_TONER_EMPTY);
        PRINT_ERROR_MAP.put(PrinterStateReason.TONER_LOW, R.string.PRINT_TONER_LOW);
        PRINT_ERROR_MAP.put(PrinterStateReason.COMMUNICATION_LOG_FULL, R.string.PRINT_COMMUNICATION_LOG_FULL);
    }

    static {
        SCAN_ERROR_MAP.put(ScanJobStateReason.MEMORY_OVER, R.string.SCAN_MEMORY_OVER);
        SCAN_ERROR_MAP.put(ScanJobStateReason.RESOURCES_ARE_NOT_READY, R.string.SCAN_RESOURCES_ARE_NOT_READY);
        SCAN_ERROR_MAP.put(ScanJobStateReason.INTERNAL_ERROR, R.string.SCAN_INTERNAL_ERROR);
        SCAN_ERROR_MAP.put(ScanJobStateReason.ORIGINAL_SET_ERROR, R.string.SCAN_ORIGINAL_SET_ERROR);
        SCAN_ERROR_MAP.put(ScanJobStateReason.TIMEOUT, R.string.SCAN_TIMEOUT);
        SCAN_ERROR_MAP.put(ScanJobStateReason.EXCEEDED_MAX_EMAIL_SIZE, R.string.SCAN_EXCEEDED_MAX_EMAIL_SIZE);
        SCAN_ERROR_MAP.put(ScanJobStateReason.EXCEEDED_MAX_PAGE_COUNT, R.string.SCAN_EXCEEDED_MAX_PAGE_COUNT);
        SCAN_ERROR_MAP.put(ScanJobStateReason.CHARGE_UNIT_LIMIT, R.string.SCAN_CHARGE_UNIT_LIMIT);
        SCAN_ERROR_MAP.put(ScanJobStateReason.PREPARING_JOB_START, R.string.SCAN_PREPARING_JOB_START);
        SCAN_ERROR_MAP.put(ScanJobStateReason.SCANNER_JAM, R.string.SCAN_SCANNER_JAM);
        SCAN_ERROR_MAP.put(ScanJobStateReason.WAIT_FOR_NEXT_ORIGINAL, R.string.SCAN_WAIT_FOR_NEXT_ORIGINAL);
        SCAN_ERROR_MAP.put(ScanJobStateReason.WAIT_FOR_NEXT_ORIGINAL_AND_CONTINUE, R.string.SCAN_WAIT_FOR_NEXT_ORIGINAL_AND_CONTINUE);
        SCAN_ERROR_MAP.put(ScanJobStateReason.CANNOT_DETECT_ORIGINAL_SIZE, R.string.SCAN_CANNOT_DETECT_ORIGINAL_SIZE);
        SCAN_ERROR_MAP.put(ScanJobStateReason.EXCEEDED_MAX_DATA_CAPACITY, R.string.SCAN_EXCEEDED_MAX_DATA_CAPACITY);
        SCAN_ERROR_MAP.put(ScanJobStateReason.NOT_SUITABLE_ORIGINAL_ORIENTATION, R.string.SCAN_NOT_SUITABLE_ORIGINAL_ORIENTATION);
        SCAN_ERROR_MAP.put(ScanJobStateReason.TOO_SMALL_SCAN_SIZE, R.string.SCAN_TOO_SMALL_SCAN_SIZE);
        SCAN_ERROR_MAP.put(ScanJobStateReason.WAIT_FOR_ORIGINAL_PREVIEW_OPERATION, R.string.SCAN_WAIT_FOR_ORIGINAL_PREVIEW_OPERATION);
        SCAN_ERROR_MAP.put(ScanJobStateReason.USER_REQUEST, R.string.SCAN_USER_REQUEST);
    }

    public final static Map<PrintJobStateReason, Integer> mJobReasonStringMap = new LinkedHashMap<PrintJobStateReason, Integer>() {
        {
            put(PrintJobStateReason.COMPRESSION_ERROR, R.string.print_fail);
            put(PrintJobStateReason.DOCUMENT_FORMAT_ERROR, R.string.print_fail);
            put(PrintJobStateReason.JOB_CANCELED_AT_DEVICE, R.string.dlg_printing_message_printing_stopped);
            put(PrintJobStateReason.RESOURCES_ARE_NOT_READY, R.string.print_fail);
            put(PrintJobStateReason.PERMISSION_DENIED, R.string.print_fail);
            put(PrintJobStateReason.PRINT_VOLUME_LIMIT, R.string.print_fail);
            put(PrintJobStateReason.TIMEOUT, R.string.print_fail);
            put(PrintJobStateReason.JOB_CANCELED_BY_USER, R.string.dlg_printing_message_printing_stopped);
            put(PrintJobStateReason.JOB_CANCELED_DURING_CREATING, R.string.dlg_printing_message_printing_stopped);
            put(PrintJobStateReason.PREPARING_JOB_START, R.string.print_fail);
        }
    };

    static {
        JOB_ERROR_MAP.put("error.login_failed", R.string.error_login_failed);
        JOB_ERROR_MAP.put("error.not_input_authentication", R.string.error_not_input_authentication);
        JOB_ERROR_MAP.put("error.not_authorized", R.string.error_not_authorized);
        JOB_ERROR_MAP.put("error.system_busy", R.string.error_system_busy);
        JOB_ERROR_MAP.put("error.permission_denied", R.string.error_permission_denied);
        JOB_ERROR_MAP.put("error.path_no_exist", R.string.error_path_no_exist);
        //JOB_ERROR_MAP.put("error.unsupported_job_setting", R.string.print_error_paper_size);

    }
}
