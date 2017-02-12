package jp.co.ricoh.advop.mini.cheetahminiutil.util.jpg2pdf;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

import jp.co.ricoh.advop.mini.cheetahminiutil.util.LogC;

/**
 * 
 * @author keiths
 *
 */
class Pdf extends Doc {
	private File dstFile;
	private FileOutputStream fos;
	private PrintStream ps;
	private FileChannel fc;
	private int objIndex, length = 0;
	private ArrayList<Integer> objectPos;
	
	public Pdf(String dstPath) {
		super(dstPath);
	}

	@Override
	void flush() {
		// TODO Auto-generated method stub
		// flush all the added parts.  (not implemented yet)
	}

	@Override
	void close() {
		if (super.pagesFlashed < 0) {
			_flushDocHeader();
			super.pagesFlashed = 0;
		}
		for ( ; super.pagesFlashed < super.pages.size(); super.pagesFlashed++) {
			_flushPage(super.pages.get(super.pagesFlashed));
		}
		_flushXRef();
		_flushTrailer();
	}

	private void _flushDocHeader() {
		
		try {
			// open destination file
			dstFile = new File(dstPath);
			fos = new FileOutputStream(dstFile);
			ps = new PrintStream(fos);
			fc =  fos.getChannel();

			// PDF version
			ps.printf("%s", "%PDF-1.2\n");
			
			// Object1 Catalog
			objectPos = new ArrayList<Integer>();
			objectPos.add((int)fc.position());			
			ps.printf("%d 0 obj\n", objIndex + 1);
			ps.printf("%s", "<<\n");
			ps.printf("%s", "/Type /Catalog\n");
			ps.printf("/Pages %d 0 R\n", objIndex + 2);	// Object2
			ps.printf("%s", ">>\n");
			ps.printf("%s", "endobj\n");
			objIndex++;			
			
			// Object2 Parent Pages
			objectPos.add((int)fc.position());
			ps.printf("%d 0 obj\n", objIndex + 1);
			ps.printf("%s", "<<\n");
			ps.printf("%s", "/Type /Pages\n");
			ps.printf("%s", "/Kids [\n");
			// put objects for all pages
			for (int nPage = 0, iObj = objIndex + 2; nPage < super.pages.size(); nPage++) {
				ps.printf("%d 0 R\n", iObj);
				// use 1 object(XObject) for each part
				iObj += super.pages.get(nPage).getPartNum();
				// use 3 objects(page, contents, length) for each page
				iObj += 3;
			}
			ps.printf("]\n", objIndex + 2);	// Object3
			ps.printf("/Count %d\n", super.pages.size());
			ps.printf("%s", ">>\n");
			ps.printf("%s", "endobj\n");
			objIndex++;
			
		} catch (FileNotFoundException e) {
		    LogC.w(e);
		} catch (IOException e) {
		    LogC.w(e);
		}	
	}

	private void _flushXRef() {
		try {
			objectPos.add((int) fc.position());
			ps.printf("%s", "xref\n");
			ps.printf("0 %d\n", objIndex + 1);
			ps.printf("%s", "0000000000 65535 f \n");

			for (int i= 0; i <= objIndex - 1; i++) 
				ps.printf("%010d 00000 n \n", objectPos.get(i));
		} catch (IOException e) {
			// TODO Auto-generated catch block
		    LogC.w(e);
		}		
	}
	
	private void _flushTrailer() {
		ps.printf("%s", "trailer\n");
		ps.printf("%s", "<<\n");
		ps.printf("/Size %d\n", objIndex + 1);
		ps.printf("%s", "/Root 1 0 R\n");
		ps.printf("%s", ">>\n");
		ps.printf("%s", "startxref\n");
		ps.printf("%d\n", objectPos.get(objIndex));
		ps.printf("%s", "%%EOF\n");
	}
	
	private void _flushPage(Page p) {
		try {
			long mediaWidth = p.getWidth();
			long mediaHeight = p.getHeight();
			
			// Object3 Kids Page
			objectPos.add((int) fc.position());
			ps.printf("%d 0 obj\n", objIndex + 1);
			ps.printf("%s", "<<\n");
			ps.printf("%s", "/Type /Page\n");
			ps.printf("%s", "/Parent 2 0 R\n");
			ps.printf("%s", "/Resources\n");
			ps.printf("%s", "<<\n");
			
			ps.printf("%s", "/XObject <<");
			for (int n = 0; n < p.getPartNum(); n++) {
				Part pTemp = p.getPart(n);
				ps.printf("/Im%d %d 0 R ", pTemp.getId(), n  + objIndex + 2);	// Object4, 5, 6.. all the parts in this page
			}			
			ps.printf("%s", ">>\n");
			
			ps.printf("%s", "/ProcSet [ /PDF /ImageB /ImageC ]\n");
			ps.printf("%s", ">>\n");
			ps.printf("/MediaBox [ 0 0 %d %d ]\n", mediaWidth, mediaHeight);
			ps.printf("/Contents %d 0 R\n", objIndex + p.getPartNum() + 2);	// Object after images
			ps.printf("%s", ">>\n");
			ps.printf("%s", "endobj\n");
			objIndex++;		

			for (int n = 0; n < p.getPartNum(); n++) {
				_flushImage(p.getPart(n));
			}
			
			// Object5 Contents
			objectPos.add((int)fc.position());
			ps.printf("%d 0 obj\n", objIndex + 1);
			ps.printf("<< /Length %d 0 R >>\n", objIndex + 2);
			ps.printf("%s", "stream\n");
		
			// stream
			length = (int)fc.position();
			for (int n = 0; n < p.getPartNum(); n++) {
				ps.printf("%s", "q\n");	// push graphic state
				float ctm[] = p.getPart(n).getCtm();
				ps.printf("%f %f %f %f %f %f cm\n", ctm[0], ctm[1], ctm[2], ctm[3], ctm[4], ctm[5]);
				if (mediaWidth * mediaHeight != 0) {
					float scaleW = (float)mediaWidth / p.getPart(n).getImage().getWidth();
					float scaleH = (float)mediaHeight / p.getPart(n).getImage().getHeight();
					float scale = Math.min(scaleW, scaleH);
					int moveW = 0, moveH = 0;
					if (scaleW < scaleH)
						moveH = (int) ((mediaHeight - p.getPart(n).getImage().getHeight() * scale ) / 2);					
					else
						moveW = (int) ((mediaWidth - p.getPart(n).getImage().getWidth() * scale ) / 2);					
					
					ps.printf("%f 0 0 %f %d %d cm\n", scale, scale, moveW, moveH);
				}
				ps.printf("%d 0 0 %d 0 0 cm\n", p.getPart(n).getImage().getWidth(), p.getPart(n).getImage().getHeight());
				ps.printf("/Im%d Do\n", p.getPart(n).getId());
				ps.printf("%s", "Q\n");	// pop graphic state
			}
			length = (int) fc.position() - length;
			ps.printf("%s", "endstream\n");
			ps.printf("%s", "endobj\n");
			objIndex++;
			
			// Object6 stream Length
			objectPos.add((int) fc.position());
			ps.printf("%d 0 obj\n", objIndex + 1);
			ps.printf("%d\n", length);
			ps.printf("%s", "endobj\n");
			objIndex++;			
		} catch (IOException e) {
			// TODO Auto-generated catch block
		    LogC.w(e);
		}
	}

	private void _flushImage(Part p) {
		Image img = p.getImage();
		
		try {			
			// Object4 XObject Resource
			objectPos.add((int)fc.position());
			ps.printf("%d 0 obj\n", objIndex + 1);
			ps.printf("%s", "<<\n");
			ps.printf("%s", "/Type /XObject\n");
			ps.printf("%s", "/Subtype /Image\n");
			ps.printf("/Name /Im%d\n", p.getId());
			ps.printf("/Width %d\n", img.getWidth());
			ps.printf("/Height %d\n", img.getHeight());
			ps.printf("%s", "/BitsPerComponent 8\n");
			ps.printf("%s", "/Filter [/DCTDecode]\n");
			if (img.isColor())
				ps.printf("%s", "/ColorSpace /DeviceRGB\n");
			else
				ps.printf("%s", "/ColorSpace /DeviceGray\n");
			ps.printf("/Length %d >>\n", img.getFileSize());
			ps.printf("%s", "stream\n");

			// flush here, since we write to binary output stream for copying jpeg data
			ps.flush();
			
			// copy jpeg stream
			img.copyStream(fos);
			
			// write to print stream again
			ps.printf("%s", "endstream\n");
			ps.printf("%s", "endobj\n");
			objIndex++;		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LogC.w(e);
		}		
	}

}
