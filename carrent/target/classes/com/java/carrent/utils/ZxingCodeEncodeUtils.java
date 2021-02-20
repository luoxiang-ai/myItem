package com.java.carrent.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.EnumMap;

/**
 * 生成二维码工具
 * @author luoxiang
 *
 */
public class ZxingCodeEncodeUtils {
	
//	二维码颜色
	private static final int BLACK = 0xFF000000;
//  二维码背景颜色
	private static final int WHITE = 0xFFFFFFFF;
	
//	二维码格式参数
	private static EnumMap<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
	
	static {
//		二维码的纠错级别(排错率),4个级别： L (7%)、 M (15%)、 Q (25%)、 H (30%)(最高H)
//		纠错信息同样存储在二维码中，纠错级别越高，纠错信息占用的空间越多，那么能存储的有用讯息就越少；共有四级； 选择M，扫描速度快。
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
//		二维码边界空白大小 1，2，3，4（4为默认，最大）
		hints.put(EncodeHintType.MARGIN, 1);
//		设置放入的字符编码
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
	}
	
	private ZxingCodeEncodeUtils() {}
	
	public static void createZxingCodeSaveToDisk(String content, int width, int height, String savePath, String imageType) {
		
		try {
			BitMatrix encode = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
//			得到二维码的宽高
			int codeWidth = encode.getWidth();
			int codeHeight = encode.getHeight();
			
//			创建图片
			BufferedImage image = new BufferedImage(codeWidth, codeHeight, BufferedImage.TYPE_INT_RGB);
			
			for (int i = 0; i < codeWidth; i++) {
				for (int j = 0; j < codeHeight; j++) {
					image.setRGB(i, j, encode.get(i, j) ? BLACK : WHITE);
				}
			}
			
//			保存图片到硬盘
			File file = new File(savePath);
			if(!file.exists()) {
				file.createNewFile();
			}
			ImageIO.write(image, imageType, file);
			System.out.println("生成成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 生成二维码，返回BufferedImage
	 * @param content
	 * @param width
	 * @param height
	 * @return
	 */
	public static BufferedImage createZxingCodeNormal(String content, int width, int height) {
		try {
			BitMatrix encode = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
//			得到二维码的宽高
			int codeWidth = encode.getWidth();
			int codeHeight = encode.getHeight();
			
//			创建图片
			BufferedImage image = new BufferedImage(codeWidth, codeHeight, BufferedImage.TYPE_INT_RGB);
			
			for (int i = 0; i < codeWidth; i++) {
				for (int j = 0; j < codeHeight; j++) {
					image.setRGB(i, j, encode.get(i, j) ? BLACK : WHITE);
				}
			}
			
			return image;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 生一张带logo的二维码，保存到磁盘
	 * @param content
	 * @param width
	 * @param height
	 * @param savePath
	 * @param imageType
	 * @param logoStream
	 */
	public static void createZxingCodeUseLogoSaveToDisk(String content, int width, int height, String savePath, String imageType, InputStream logoStream) {
		try {
//			生成二维码图片
			BufferedImage codeNormal = createZxingCodeNormal(content, width, height);
			if(codeNormal != null) {
//				判断logoStream是否为空
				if(logoStream != null) {
//					拿到可以操作当前图片的画笔
					Graphics2D graphics = codeNormal.createGraphics();
//					拿到logo图片的对象
					BufferedImage logoImage = ImageIO.read(logoStream);
//					获取logo图片的原始宽高
					int oldLogoWidth = logoImage.getWidth();
					int oldLogoHeight = logoImage.getHeight();
					
//					得到二维码的宽高
					int codeWidth = codeNormal.getWidth();
					int codeHeight = codeNormal.getHeight();
					
//					计算logo在二维码中能存在的最大尺寸
					int logoMaxWidth = codeWidth / 5;
					int logoMaxHeight = codeHeight / 5;
					
//					计算logo可用宽高
					int logoWidth = oldLogoWidth > logoMaxWidth ? logoMaxWidth : oldLogoWidth; 
					int logoHeight = oldLogoHeight > logoMaxHeight ? logoMaxHeight : oldLogoHeight;
					
//					计算logo的开始坐标点
					int x = codeWidth / 2 - logoWidth / 2;
					int y = codeHeight / 2 - logoHeight / 2;
					
					graphics.drawImage(logoImage, x, y, logoWidth, logoHeight, null);
					
//					让画上去的内容生效
					graphics.dispose();
					
//					把二维码保存在本地磁盘上
					File file = new File(savePath);
					ImageIO.write(codeNormal, imageType, file);
					System.out.println("生成成功");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("生成失败");
		}
	}


	/**
	 * 生成一张带logo的二维码，返回BufferedImage
	 * @param content
	 * @param width
	 * @param height
	 * @param logoStream
	 * @return
	 */
	public static BufferedImage createZxingCodeUseLogoNormal(String content, int width, int height, InputStream logoStream) {
		try {
//			生成二维码图片
			BufferedImage codeNormal = createZxingCodeNormal(content, width, height);
			if(codeNormal != null) {
//				判断logoStream是否为空
				if(logoStream != null) {
//					拿到可以操作当前图片的画笔
					Graphics2D graphics = codeNormal.createGraphics();
//					拿到logo图片的对象
					BufferedImage logoImage = ImageIO.read(logoStream);
//					获取logo图片的原始宽高
					int oldLogoWidth = logoImage.getWidth();
					int oldLogoHeight = logoImage.getHeight();
					
//					得到二维码的宽高
					int codeWidth = codeNormal.getWidth();
					int codeHeight = codeNormal.getHeight();
					
//					计算logo在二维码中能存在的最大尺寸
					int logoMaxWidth = codeWidth / 5;
					int logoMaxHeight = codeHeight / 5;
					
//					计算logo可用宽高
					int logoWidth = oldLogoWidth > logoMaxWidth ? logoMaxWidth : oldLogoWidth; 
					int logoHeight = oldLogoHeight > logoMaxHeight ? logoMaxHeight : oldLogoHeight;
					
//					计算logo的开始坐标点
					int x = codeWidth / 2 - logoWidth / 2;
					int y = codeHeight / 2 - logoHeight / 2;
					
					graphics.drawImage(logoImage, x, y, logoWidth, logoHeight, null);
					
//					让画上去的内容生效
					graphics.dispose();
					
					return codeNormal;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("生成失败");
			return null;
		}
		
		return null;
	}
}
