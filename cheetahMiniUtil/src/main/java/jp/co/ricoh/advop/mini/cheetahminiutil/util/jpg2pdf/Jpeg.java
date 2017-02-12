package jp.co.ricoh.advop.mini.cheetahminiutil.util.jpg2pdf;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import jp.co.ricoh.advop.mini.cheetahminiutil.util.LogC;

/**
 * Jpegクラス
 * @author keiths
 *
 */
class Jpeg extends Image {
	private String path;
	private long width, height, sampling;
	private File jpegFile = null;
	private FileInputStream fis;

	@Override
	void setPath(String path) {
		assert path != null : path;

		this.path = path;
		try {
			jpegFile = new File(path);
			_getWidthHeight();
		} catch (Exception e) {

		}
	}

	@Override
	long getWidth() {
		if (path == null) {
			throw new IllegalStateException("not initialized with path");
		}
		return width;
	}

	@Override
	long getHeight() {
		if (path == null) {
			throw new IllegalStateException("not initialized with path");
		}
		return height;
	}

	@Override
	long getFileSize() {
		if (path == null) {
			throw new IllegalStateException("not initialized with path");
		}

		return jpegFile.length();
	}

	@Override
	boolean isColor() {
		if (path == null) {
			throw new IllegalStateException("not initialized with path");
		}

		if (sampling <= 1)
			return false;
		else
			return true;
	}

	@Override
	void copyStream(FileOutputStream fos) {
		if (path == null) {
			throw new IllegalStateException("not initialized with path");
		}

		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			fis = new FileInputStream(jpegFile);
			bis = new BufferedInputStream(fis);
			bos = new BufferedOutputStream(fos);
			byte[] buf = new byte[1024];
			int len = 0;
			while ((len = bis.read(buf)) != -1) {
				bos.write(buf, 0, len);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block

		    LogC.w(e);
		} finally {
			try {
				if (bos != null)
					bos.flush();
				if (bis != null)
					bis.close();
				if (fis != null)
					fis.close();
			} catch (Exception e) {

			    LogC.w(e);
			}
		}
	}

	private boolean _getWidthHeight() {
		boolean found = false;
		BufferedInputStream bis = null;
		width = 0;
		height = 0;
		sampling = 0;

		try {
			fis = new FileInputStream(jpegFile);
			bis = new BufferedInputStream(fis);

			int i1, i2;

			// skip SOI
			i1 = bis.read();
			i2 = bis.read();
			if ((i1 == -1) || (i2 == -1))
				throw new IllegalArgumentException("can not read : " + jpegFile.getAbsolutePath());

			// if it is image marker, no support
			if ((i1 != 0xff) || (i2 != 0xd8))
				throw new IllegalArgumentException("no JFIF in "  + jpegFile.getAbsolutePath());

			// read until end marker comes
			while (true) {
				// read marker
				i1 = bis.read();
				i2 = bis.read();
				if (i1 == -1 || i2 == -1)
					throw new IllegalArgumentException("no marker in "  + jpegFile.getAbsolutePath());

				// if marker is SOF0 or SOF2, check width/height
				if ((i1 == 0xff) && ((i2 == 0xc0) || (i2 == 0xc2))) {
					// skip segment length
					bis.skip(2);
					// skip sample
					bis.skip(1);

					// read width and height
					height = (bis.read() << 8) + bis.read();
					width = (bis.read() << 8) + bis.read();
					sampling = bis.read();
					found = true;

					break;
				} else if ((i1 == 0xff) && ((i2 == 0xff) || (i2 == 0xd9))) {
					// end marker
					break;
				}
				// read marker size
				i1 = bis.read();
				i2 = bis.read();
				if (i1 == -1 || i2 == -1)
					throw new IllegalArgumentException("no marker size in "  + jpegFile.getAbsolutePath());
				bis.skip((i1 << 8) + i2 - 2);
			}
		} catch (Exception e) {

		    LogC.w(e);
		} finally {
			try {
				if (bis != null)
					bis.close();
				if (fis != null)
					fis.close();
			} catch (Exception e) {

			    LogC.w(e);
			}
		}
		return found;
	}

}
