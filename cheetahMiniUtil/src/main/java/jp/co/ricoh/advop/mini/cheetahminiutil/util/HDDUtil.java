package jp.co.ricoh.advop.mini.cheetahminiutil.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;


import android.content.Context;

public class HDDUtil {

    private static final String TAG = HDDUtil.class.getSimpleName();
    
    private static final int STORE_BUFFER_SIZE = 131072;  //128 KB = 131072 Byte
    	
	public static boolean createNewFile(String filePath) {
		if (filePath == null) {
			LogC.e(TAG, "Empty parameter!");
			return false;
		}
		
		LogC.d(TAG, "Start creating new file:" + filePath);
		try {
			File file = new File(filePath);
			if (!file.getParentFile().exists()) {
				boolean ret = file.getParentFile().mkdirs();

				LogC.d(TAG, "Parent Directory created:" + ret);
			}
			
			if (!file.exists()) {
				file.createNewFile();
//				Runtime.getRuntime().exec("chmod 766 " + file);
			}
			
			LogC.d(TAG, "File created.");
			
			return true;
		} catch (IOException e) {
			LogC.e(TAG, "Create file error!", e);
			return false;
		}
	}
	
//	public static boolean writeFile(String filePath, byte[] data) {
//		if (filePath == null || data == null) {
//			LogC.e(TAG, "Empty parameter!");
//			return false;
//		}
//		
//		if (!HDDUtil.createNewFile(filePath)) {
//			return false;
//		}
//
//		LogC.d(TAG, "Start writing file:" + filePath);
//		
//		try {
//			File file = new File(filePath);
//			
//			FileOutputStream fileOutputStream = new FileOutputStream(file);
//			FileDescriptor fileDescriptor = fileOutputStream.getFD();
//
////////////////////////
//			// Write function 1
//			// note: Following source code seems OK to write log, at least not crush. not check less word.
//			int length = STORE_BUFFER_SIZE;  //128KB
//			int start = 0;			
//			while (start < data.length) {				
//				int len = 0;
//				if(data.length -1 - start >= length) {
//					len = length;
//				} else {
//					len = data.length - 1 - start;
//				}
//				fileDescriptor.sync();
//				fileOutputStream.write(data, start, len);
//				start += length;
//			}
////////////////////////
//			// Write function 2
////			int start = 0;
////			
////			while (start < data.length && (data.length - start) > STORE_BUFFER_SIZE) {
////				fileDescriptor.sync();
////				fileOutputStream.write(data, start, STORE_BUFFER_SIZE);
////				start += STORE_BUFFER_SIZE;
////			}
////		
////			fileDescriptor.sync();
////			fileOutputStream.write(data, start, data.length - start);
////////////////////////
//			
//			fileDescriptor.sync();
//			fileOutputStream.close();
//			LogC.d(TAG, "Finish writing file.");
//			
//			return true;
//		} catch (IOException e) {
//			LogC.e(TAG, "Write file error!" + e.toString());
//			return false;
//		}
//	}
	
	public static byte[] readFile(String filePath) throws IOException {
		if (filePath == null) {
			LogC.e(TAG, "Empty parameter!");
			return null;
		}
		LogC.d(TAG, "Start reading file:" + filePath);
		
		FileInputStream fileInputStream = null;
		try {
			File file = new File(filePath);

			fileInputStream = new FileInputStream(file);

			int length = fileInputStream.available();

			byte[] buffer = new byte[length];
			fileInputStream.read(buffer);

//			String result = EncodingUtils.getString(buffer, "UTF-8");FileInputStream fileInputStream

			LogC.d(TAG, "Finish reading file.");
			
			return buffer;
		} catch (IOException e) {
			LogC.e(TAG, "Read file error!" + e.toString());
			return null;
		} finally {
			if (fileInputStream != null) {
				fileInputStream.close();
			}
		}
	}

	public static String readFileFromAssets(Context context,String fileName){
		try {
			InputStreamReader inputReader = new InputStreamReader( context.getResources().getAssets().open(fileName) );
			BufferedReader bufReader = new BufferedReader(inputReader);
			String line="";
			String Result="";
			while((line = bufReader.readLine()) != null)
				Result += line+"\n";
			return Result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static boolean copyFile(String oldPath, String newPath) {
		if (oldPath == null || newPath == null) {
			LogC.e(TAG, "Empty parameter!");
			return false;
		}
		
		LogC.d(TAG, "Start copying file:" + oldPath + " to " + newPath);
		
		FileInputStream fileInputStream = null;
		FileOutputStream fileOutputStream = null;
		
		try {
			File oldfile = new File(oldPath);
			if (oldfile.exists()) {
				if (!HDDUtil.createNewFile(newPath)) {
					return false;
				}
//				FileInputStream fileInputStream = new FileInputStream(oldPath);
//				FileOutputStream fileOutputStream = new FileOutputStream(newPath);
//				OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
//						fileOutputStream);
//				FileDescriptor fileDescriptor = fileOutputStream.getFD();
				
//				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
//				BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
				
//				char[] buffer = new char[128];
//				int length;
				
//				while ((length = bufferedReader.read(buffer)) != -1) {
//					fileDescriptor.sync();
//					bufferedWriter.write(buffer, 0, length);
//				}
//				bufferedWriter.flush();
				
//				fileDescriptor.sync();
//				bufferedReader.close();
//				bufferedWriter.close();
//				fileInputStream.close();
				

				fileInputStream = new FileInputStream(oldPath);
				fileOutputStream = new FileOutputStream(newPath);
				FileDescriptor fileDescriptor = fileOutputStream.getFD();
				
				byte[] buffer = new byte[STORE_BUFFER_SIZE];
				int length;
				
				while ((length = fileInputStream.read(buffer)) != -1) {
					fileDescriptor.sync();
					fileOutputStream.write(buffer, 0, length);
				}
				
				fileDescriptor.sync();

				LogC.d(TAG, "Finish copying file.");
				return true;
			} else {
				LogC.e(TAG, "Copy file error! Original file is not exists.");
				return false;
			}
		} catch (IOException e) {
			LogC.e(TAG, "Copy file error!" + e.toString());
			HDDUtil.deleteFile(newPath);
			return false;
		} finally {
			try {
				if (fileInputStream != null) {
					fileInputStream.close();
				}
				if (fileOutputStream != null) {
					fileOutputStream.close();
				}
			} catch (IOException e) {
			}
		}
	}
	
	public static void deleteFiles(List<String> paths) {
		for(String path:paths) {
            if(path == null || path.isEmpty()) {
                continue;
            }
            
            deleteFile(path);            
        }
	}
	
	public static boolean deleteFile(String filePath) {
		if (filePath == null) {
			LogC.e(TAG, "Empty parameter!");
			return false;
		}
		
		LogC.d(TAG, "Start deleting file:" + filePath);

		try {
			File file = new File(filePath);
			if (file.exists()) {
				if (file.isFile()) {
					file.delete();
				} else if (file.isDirectory()) {
					File files[] = file.listFiles();
					for (File eachFile : files) {
						HDDUtil.deleteFile(eachFile.getAbsolutePath());
					}
					file.delete();
				}

				LogC.d(TAG, "File deleted.");
				return true;
			} else {
				LogC.e(TAG, "Deleted file is not exists!");
				return false;
			}
		} catch (Exception e) {
			LogC.e(TAG, "Delete file error!" + e.toString());
			return false;
		}
	}

	public static String copyAssertJarToFile(Context context, String filename,
			String des) {
		InputStream inputStream = null;
		FileOutputStream fileOutputStream = null;
		try {
			File file = new File(des);
			if (file.exists()) {
				return des;
			}

			inputStream = context.getAssets().open(filename);
			HDDUtil.createNewFile(des);
			fileOutputStream = new FileOutputStream(file);
			FileDescriptor fileDescriptor = fileOutputStream.getFD();
			
			byte[] buffer = new byte[STORE_BUFFER_SIZE];
			int length;
			
			while ((length = inputStream.read(buffer)) != -1) {
				fileDescriptor.sync();
				fileOutputStream.write(buffer, 0, length);
			}
			
			fileDescriptor.sync();

			return des;
		} catch (Exception e) {
		    LogC.w(e);
			HDDUtil.deleteFile(des);
			return null;
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
				if (fileOutputStream != null) {
					fileOutputStream.close();
				}
			} catch (IOException e) {
			}
		}
	}
	
	//get scaned file path
	public String getScanFilePath(String packageName){
		return "/mnt/hdd/"+packageName+"/tmp/";
	}
}
