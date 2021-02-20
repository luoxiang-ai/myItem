package com.java.carrent.utils;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.EnumMap;

/**
 * 解析二维码工具类
 * @author luoxiang
 *
 */
public class ZxingCodeDecodeUtils {

//	二维码格式参数
	private static EnumMap<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
	
	static {
//		设置放入的字符编码
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
	}
	
	private ZxingCodeDecodeUtils() {}
	
	/**
	 * 根据文件解析二维码
	 * @param path
	 * @return
	 */
	public static String decodeQRcodeByFile(String path) {
		File file = new File(path);
		if(path != null && file.exists() && file.isFile()) {
			try {
				String content = decodeQRcodeByStream(new FileInputStream(path));
				return content;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 根据文件解析二维码
	 * @param path
	 * @return
	 */
	public static String decodeQRcodeByFile(File path) {
		if(path != null && path.exists() && path.isFile()) {
			try {
				String content = decodeQRcodeByStream(new FileInputStream(path));
				return content;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 根据流解析二维码
	 * @param is
	 * @return
	 */
	public static String decodeQRcodeByStream(InputStream is) {
		if(is != null) {
			try {
				BufferedImage image = ImageIO.read(is);
				BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
				HybridBinarizer binarizer = new HybridBinarizer(source);
				BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
				MultiFormatReader reader = new MultiFormatReader();
				Result result = reader.decode(binaryBitmap);
				String content = result.getText();
				
				return content;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
		return null;
	}
}
