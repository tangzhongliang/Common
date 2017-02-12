package jp.co.ricoh.advop.mini.cheetahminiutil.util.jpg2pdf;

public class jpeg2pdf {
	private Image img;
	private Part part;
	private Page page;
	private Doc doc;
	private long width, height;

	/**
	 * 出力先を指定して文書作成を始める
	 * @param dstPath
	 */
	public void start( String dstPath ) {

		doc = new Pdf(dstPath);
		if (doc == null) {
			throw new IllegalStateException("can not make document : " + dstPath);
		}
		
		return;
	}
	
	/**
	 * 用紙サイズを指定する
	 * @param widthIn72dpi: 幅を72dpi単位で指定する
	 * @param heightIn72dpi: 高さを72dpi単位で指定する
	 */
	public void setPaperSize( long widthIn72dpi, long heightIn72dpi ) {
		if (doc == null) {
			throw new IllegalStateException("not properly initialized");
		}

		width = widthIn72dpi;
		height = heightIn72dpi;
	}
	
	/**
	 * 1枚のjpegファイルを用紙に張り付ける
	 * これより前にsetPaperSize()を呼ぶこと、呼んでいない場合はデフォルトサイズになる
	 * @param jpegPath
	 */
	public void addPageJpeg(String jpegPath) {
		if (doc == null) {
			throw new IllegalStateException("not properly initialized");
		}
		
		// make a new page
		if (width * height != 0) {	// if size is already set, use it
			page = new Page(width, height);
		} else {
			page = new Page();
		}
		img = new Jpeg();		// make a new image
		part = new Part();		// make a new part
		img.setPath(jpegPath);	// set image path
		part.setImage(img);		// set the image to the part
		page.addPart(part);		// add the patr to the page
		doc.addPage(page);		// add the page to the doc

		return;	
	}
	
	/**
	 * 2枚のjpegファイルを2in1で張り付ける
	 */
	public void addPageJpeg(String jpegPath1, String jpegPath2, Boolean isA3machine) {
		if (doc == null) {
			throw new IllegalStateException("not properly initialized");
		}
		
		page = new Page();		// make a new page
		// make a new page
		if (width * height != 0) {	// if size is already set, use it
			page = new Page(width, height);
		} else {
			page = new Page();
		}
		img = new Jpeg();		// make a new image
		part = new Part();		// make a new part
		float ctm[];
		img.setPath(jpegPath1);	// set image path
		part.setImage(img);		// set the image to the part	
		
		if (isA3machine == true)
		{
			ctm = new float[]{0F, -0.71F, 0.71F, 0F, 0F, page.getHeight()};	// rotate CW90, 71% reduced
		}else
		{
			ctm = new float[]{-1F, 0F, 0F, -1F, page.getWidth(), page.getHeight()*1.25F};	// rotate CW180, no reduced
		}
		
		part.setCtm(ctm);
		page.addPart(part);		// add the patr to the page
		doc.addPage(page);		// add the page to the doc
		
		img = new Jpeg();
		part = new Part();
		img.setPath(jpegPath2);		
		part.setImage(img);
		
		if (isA3machine == true)
		{
			ctm = new float[]{0F, -0.71F, 0.71F, 0F, 0F, page.getHeight()/2};	// rotate CW90, 71% reduced
		}else
		{
			ctm = new float[]{-1F, 0F, 0F, -1F, page.getWidth(), page.getHeight()*0.75F};	// rotate CW180, no reduced
		}
		
		part.setCtm(ctm);
		page.addPart(part);	
		
		return;	
	}
	
	/**
	 * 文書を書き出して、作業を終了する
	 */
	public void end() {
		if (doc == null) {
			throw new IllegalStateException("not properly initialized");
		}
		
		doc.close();			// close doc to save the contents
		
		return;
	}
	
}