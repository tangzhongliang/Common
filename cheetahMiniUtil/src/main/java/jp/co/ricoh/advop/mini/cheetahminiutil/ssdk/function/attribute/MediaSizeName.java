/*
 *  Copyright (C) 2013-2015 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.attribute;


/**
 * 指定可能な紙原稿サイズの列挙です。
 * The list of document sizes that can be set.
 *
 */
public enum MediaSizeName {
	C("c"),
	C_LANDSCAPE("c_landscape"),
	CUSTOM_LANDSCAPE("custom_landscape"),
	CUSTOM_SIZE("custom_size"),
	D("d"),
	D_LANDSCAPE("d_landscape"),
	DL_ENV("dl_env"),
	DL_ENV_LANDSCAPE("dl_env_landscape"),
	E("e"),
	E_LANDSCAPE("e_landscape"),
	EXECUTIVE("executive"),
	EXECUTIVE_LANDSCAPE("executive_landscape"),
	INVOICE("invoice"),
	INVOICE_LANDSCAPE("invoice_landscape"),
	ISO_A0("iso_a0"),
	ISO_A0_LANDSCAPE("iso_a0_landscape"),
	ISO_A1("iso_a1"),
	ISO_A1_LANDSCAPE("iso_a1_landscape"),
	ISO_A2("iso_a2"),
	ISO_A2_LANDSCAPE("iso_a2_landscape"),
	ISO_A3("iso_a3"),
	ISO_A3_LANDSCAPE("iso_a3_landscape"),
	ISO_A4("iso_a4"),
	ISO_A4_LANDSCAPE("iso_a4_landscape"),
	ISO_A5("iso_a5"),
	ISO_A5_LANDSCAPE("iso_a5_landscape"),
	ISO_A6("iso_a6"),
	ISO_A6_LANDSCAPE("iso_a6_landscape"),
	ISO_A7("iso_a7"),
	ISO_A7_LANDSCAPE("iso_a7_landscape"),
	ISO_C5("iso_c5"),
	ISO_C5_LANDSCAPE("iso_c5_landscape"),
	ISO_C6("iso_c6"),
	ISO_C6_LANDSCAPE("iso_c6_landscape"),
	JAPANESE_DOUBLE_POST("japanese_double_post"),
	JAPANESE_DOUBLE_POST_LANDSCAPE("japanese_double_post_landscape"),
	JAPANESE_ENV_3("japanese_env_3"),
	JAPANESE_ENV_3_LANDSCAPE("japanese_env_3_landscape"),
	JAPANESE_ENV_4("japanese_env_4"),
	JAPANESE_ENV_4_LANDSCAPE("japanese_env_4_landscape"),
	JAPANESE_POSTCARD("japanese_postcard"),
	JAPANESE_POSTCARD_LANDSCAPE("japanese_postcard_landscape"),
	JIS_B0("jis_b0"),
	JIS_B0_LANDSCAPE("jis_b0_landscape"),
	JIS_B1("jis_b1"),
	JIS_B1_LANDSCAPE("jis_b1_landscape"),
	JIS_B2("jis_b2"),
	JIS_B2_LANDSCAPE("jis_b2_landscape"),
	JIS_B3("jis_b3"),
	JIS_B3_LANDSCAPE("jis_b3_landscape"),
	JIS_B4("jis_b4"),
	JIS_B4_LANDSCAPE("jis_b4_landscape"),
	JIS_B5("jis_b5"),
	JIS_B5_LANDSCAPE("jis_b5_landscape"),
	JIS_B6("jis_b6"),
	JIS_B6_LANDSCAPE("jis_b6_landscape"),
	JIS_B7("jis_b7"),
	JIS_B7_LANDSCAPE("jis_b7_landscape"),
	KAKU_2_ENV("kaku_2_env"),
	KAKU_2_ENV_LANDSCAPE("kaku_2_env_landscape"),
	LEDGER("ledger"),
	LIB6("lib6"),
	LIB6_LANDSCAPE("lib6_landscape"),
	LIB8("lib8"),
	LIB8_LANDSCAPE("lib8_landscape"),
	MONARCH_ENVELOPE("monarch_envelope"),
	MONARCH_ENVELOPE_LANDSCAPE("monarch_envelope_landscape"),
	MEXICO_OFICIO("mexico_oficio"),						// SmartSDK V2.00
	MEXICO_OFICIO_LANDSCAPE("mexico_oficio_landscape"), // SmartSDK V2.00
	NA_10X14_ENVELOPE("na_10x14_envelope"),
	NA_10X14_ENVELOPE_LANDSCAPE("na_10x14_envelope_landscape"),
	NA_10X15_ENVELOPE("na_10x15_envelope"),
	NA_10X15_ENVELOPE_LANDSCAPE("na_10x15_envelope_landscape"),
	NA_8X10("na_8x10"),
	NA_8X10_LANDSCAPE("na_8x10_landscape"),
	NA_9X12_ENVELOPE("na_9x12_envelope"),
	NA_9X12_ENVELOPE_LANDSCAPE("na_9x12_envelope_landscape"),
	NA_LEGAL("na_legal"),
	NA_LEGAL_LANDSCAPE("na_legal_landscape"),
	NA_LETTER("na_letter"),
	NA_LETTER_LANDSCAPE("na_letter_landscape"),
	NA_NUMBER_10_ENVELOPE("na_number_10_envelope"),
	NA_NUMBER_10_ENVELOPE_LANDSCAPE("na_number_10_envelope_landscape"),
	ROLL_182MM_WIDTH("roll_182mm_width"),
	ROLL_30INCH_WIDTH("roll_30inch_width"),
	ROLL_490MM_WIDTH("roll_490mm_width"),
	ROLL_620MM_WIDTH("roll_620mm_width"),
	ROLL_625MM_WIDTH("roll_625mm_width"),
	ROLL_680MM_WIDTH("roll_680mm_width"),
	ROLL_707MM_WIDTH("roll_707mm_width"),
	ROLL_A0_WIDTH("roll_a0_width"),
	ROLL_A1_WIDTH("roll_a1_width"),
	ROLL_A2_WIDTH("roll_a2_width"),
	ROLL_A3_WIDTH("roll_a3_width"),
	ROLL_A4_WIDTH("roll_a4_width"),
	ROLL_ARCH_12INCH_WIDTH("roll_arch_12inch_width"),
	ROLL_ARCH_18INCH_WIDTH("roll_arch_18inch_width"),
	ROLL_ARCH_24INCH_WIDTH("roll_arch_24inch_width"),
	ROLL_ARCH_36INCH_914MM_WIDTH("roll_arch_36inch_914mm_width"),
	ROLL_ARCH_9_INCH_WIDTH("roll_arch_9_inch_width"),
	ROLL_B1_JIS_WIDTH("roll_b1_jis_width"),
	ROLL_B2_JIS_WIDTH("roll_b2_jis_width"),
	ROLL_B3_JIS_WIDTH("roll_b3_jis_width"),
	ROLL_B4_JIS_WIDTH("roll_b4_jis_width"),
	ROLL_ENG_11INCH_WIDTH("roll_eng_11inch_width"),
	ROLL_ENG_17INCH_WIDTH("roll_eng_17inch_width"),
	ROLL_ENG_22INCH_WIDTH("roll_eng_22inch_width"),
	ROLL_ENG_34INCH_WIDTH("roll_eng_34inch_width"),
	ROLL_ENG_8HINCH_WIDTH("roll_eng_8hinch_width"),
	SIZE_11X14("size_11x14"),
	SIZE_11X14_LANDSCAPE("size_11x14_landscape"),
	SIZE_11X15("size_11x15"),
	SIZE_11X15_LANDSCAPE("size_11x15_landscape"),
	SIZE_11X17("size_11x17"),
	SIZE_11X17_LANDSCAPE("size_11x17_landscape"),
	SIZE_12_6X18_5("size_12_6x18_5"),
	SIZE_12_6X18_5_LANDSCAPE("size_12_6x18_5_landscape"),
	SIZE_12_6X19_2("size_12_6x19_2"),
	SIZE_12_6X19_2_LANDSCAPE("size_12_6x19_2_landscape"),
	SIZE_12X14H("size_12x14h"),
	SIZE_12X14H_LANDSCAPE("size_12x14h_landscape"),
	SIZE_12X18("size_12x18"),
	SIZE_12X18_LANDSCAPE("size_12x18_landscape"),
	SIZE_13X18("size_13x18"),
	SIZE_13X18_LANDSCAPE("size_13x18_landscape"),
	SIZE_13X19("size_13x19"),
	SIZE_13X19_LANDSCAPE("size_13x19_landscape"),
	SIZE_13X19Q("size_13x19q"),
	SIZE_13X19Q_LANDSCAPE("size_13x19q_landscape"),
	SIZE_16K("size_16k"),
	SIZE_16K_LANDSCAPE("size_16k_landscape"),
	SIZE_170X210MM("size_170x210mm"),
	SIZE_170X210MM_LANDSCAPE("size_170x210mm_landscape"),
	SIZE_18X24("size_18x24"),
	SIZE_18X24_LANDSCAPE("size_18x24_landscape"),
	SIZE_210X170MM("size_210x170mm"),
	SIZE_210X170MM_LANDSCAPE("size_210x170mm_landscape"),
	SIZE_210X182MM("size_210x182mm"),
	SIZE_210X182MM_LANDSCAPE("size_210x182mm_landscape"),
	SIZE_210X340MM("size_210x340mm"),
	SIZE_210X340MM_LANDSCAPE("size_210x340mm_landscape"),
	SIZE_21X30("size_21x30"),
	SIZE_21X30_LANDSCAPE("size_21x30_landscape"),
	SIZE_226X310MM("size_226x310mm"),
	SIZE_226X310MM_LANDSCAPE("size_226x310mm_landscape"),
	SIZE_24X36("size_24x36"),
	SIZE_24X36_LANDSCAPE("size_24x36_landscape"),
	SIZE_267X388MM("size_267x388mm"),
	SIZE_267X388MM_LANDSCAPE("size_267x388mm_landscape"),
	SIZE_30X42("size_30x42"),
	SIZE_30X42_LANDSCAPE("size_30x42_landscape"),
	SIZE_310X432MM("size_310x432mm"),
	SIZE_310X432MM_LANDSCAPE("size_310x432mm_landscape"),
	SIZE_36X48("size_36x48"),
	SIZE_36X48_LANDSCAPE("size_36x48_landscape"),
	SIZE_440MM_WIDTH("size_440mm_width"),
	SIZE_660MM_WIDTH("size_660mm_width"),
	SIZE_800MM_WIDTH("size_800mm_width"),
	SIZE_880MM_WIDTH("size_880mm_width"),
	SIZE_8HX12("size_8hx12"),
	SIZE_8HX12_LANDSCAPE("size_8hx12_landscape"),
	SIZE_8HX13("size_8hx13"),
	SIZE_8HX13_LANDSCAPE("size_8hx13_landscape"),
	SIZE_8K("size_8k"),
	SIZE_8K_LANDSCAPE("size_8k_landscape"),
	SIZE_8QX13("size_8qx13"),
	SIZE_8QX13_LANDSCAPE("size_8qx13_landscape"),
	SIZE_8QX14("size_8qx14"),
	SIZE_8QX14_LANDSCAPE("size_8qx14_landscape"),
	SIZE_8X10H("size_8x10h"),
	SIZE_8X10H_LANDSCAPE("size_8x10h_landscape"),
	SIZE_8X13("size_8x13"),
	SIZE_8X13_LANDSCAPE("size_8x13_landscape"),
	SIZE_9HX11("size_9hx11"),
	SIZE_9HX11_LANDSCAPE("size_9hx11_landscape"),
	SRA3("sra3"),
	SRA3_LANDSCAPE("sra3_landscape"),
	SRA4("sra4"),
	SRA4_LANDSCAPE("sra4_landscape"),
	YOH_4_ENV("yoh_4_env"),
	YOH_4_ENV_LANDSCAPE("yoh_4_env_landscape"),
	YOH_CHOU_3_ENV("yoh_chou_3_env"),
	YOH_CHOU_3_ENV_LANDSCAPE("yoh_chou_3_env_landscape"),
	KIKU_4("kiku_4"),												// SmartSDK V2.00
	KIKU_4_LANDSCAPE("kiku_4_landscape"),							// SmartSDK V2.00
	A3_NOBI("a3_nobi"),												// SmartSDK V2.00
	A3_NOBI_LANDSCAPE("a3_nobi_landscape"),							// SmartSDK V2.00
	POSTCARD_STANDARD("postcard_standard"),							// SmartSDK V2.00
	POSTCARD_STANDARD_LANDSCAPE("postcard_standard_landscape"),		// SmartSDK V2.00
	POSTCARD_OVERSIZED("postcard_oversized"),						// SmartSDK V2.00
	POSTCARD_OVERSIZED_LANDSCAPE("postcard_oversized_landscape");	// SmartSDK V2.00

	/**
	 * サイズのenum型
	 * Sizes in enum type
	 */
	private final String originalSize;

	/**
	 * サイズを保持します。
	 * Saves size
	 * @param originalSize
	 */
	private MediaSizeName(String originalSize){
		this.originalSize = originalSize;
	}

	/**
	 * 現在の列挙型が示す文字列を返します。
	 * Returns the string indicated by the current enum type
	 *
	 * @return 定義されている文字列
	 *         Defined strings
	 */
	public String getValue(){
		return this.originalSize;
	}

}
