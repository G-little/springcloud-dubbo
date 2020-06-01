package com.little.g.springcloud.user;

import com.little.g.springcloud.common.enums.TokenVersion;
import com.little.g.springcloud.common.utils.Base62Util;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * token server的工具类 User: erin Date: 14-10-17 Time: 上午9:36
 */
@Slf4j
public class TokenUtil {

	public static void main(String[] args) throws Exception {
		Integer uid = 178934567;
		String token1 = generatorToken(uid, TokenVersion.VERSION_2.getValue());
		System.out.println("生成的token1为：" + token1 + "\n长度1为：" + token1.length());

	}

	/**
	 * 计算token token规则为: {8位版本}{32位时间戳}{64位随机字符串}{32位校验} {1字节版本}{4字节时间戳}{8字节随机字符串}{4字节校验}
	 * @param uid 用户的唯一标识
	 * @param version token的版本号
	 * @return
	 */
	public static String generatorToken(Integer uid, int version) throws IOException {

		// 获取当前系统时间，以秒为单位
		int time = TokenCommonUtil.getSecondTime();

		return generatorToken(uid, version, time);

	}

	/**
	 * 计算token token规则为: {8位版本}{32位时间戳}{64位随机字符串}{32位校验} {1字节版本}{4字节时间戳}{8字节随机字符串}{4字节校验}
	 * @param uid 用户的唯一标识
	 * @param version token的版本号
	 * @return
	 */
	public static String generatorToken(Integer uid, int version, int time) {

		// 读取配置文件，通过版本号取得对应的token的加密解密的密钥，及自校验位的生成方式
		TokenVersionConfig tokenVersionConfig = TokenVersionFactory
				.getTokenConfig(version);
		// 生成token时用的密钥
		String create_token_key = tokenVersionConfig.getCreate_token_key();
		// 生成自校验位时的密钥
		String check_token_key = tokenVersionConfig.getCheck_token_key();
		// 分别取MD5加密后串的哪四个字节做为自校验位
		String check = tokenVersionConfig.getCheck();
		String[] checkArray = check.split(",");
		int i1 = Integer.valueOf(checkArray[0]);
		int i2 = Integer.valueOf(checkArray[1]);
		int i3 = Integer.valueOf(checkArray[2]);
		int i4 = Integer.valueOf(checkArray[3]);
		//
		// //获取当前系统时间，以秒为单位
		// int time = TokenCommonUtil.getSecondTime();

		// 计算{136位随机字符串，共17字节}
		byte[] timeBytes = TokenCommonUtil.int2Bytes(time);

		byte[] udidHalfBytes = generate8MD5ByVersion(version, uid, create_token_key);

		// 添加{1字节版本}{4字节时间戳}
		byte vByte = (byte) version;
		byte[] sidBytes = TokenCommonUtil.byteMerger(new byte[] { vByte }, timeBytes);

		// 添加{1字节版本}{4字节时间戳}{中间8字节根据不同版本生成方法有变}
		sidBytes = TokenCommonUtil.byteMerger(sidBytes, udidHalfBytes);

		// 计算校验位
		byte[] sidMd5Bytes = TokenCommonUtil.byteMerger(sidBytes,
				check_token_key.getBytes());
		sidMd5Bytes = TokenCommonUtil.calculateMD5(sidMd5Bytes);
		// 根据配置文件中取相应版本对应的元素组成校验位
		byte[] sidCheckBytes = new byte[] { sidMd5Bytes[i1], sidMd5Bytes[i2],
				sidMd5Bytes[i3], sidMd5Bytes[i4] };

		// 添加校验位{1字节版本}{4字节时间戳}{中间8字节根据不同版本生成方法有变}{4字节校验}
		sidBytes = TokenCommonUtil.byteMerger(sidBytes, sidCheckBytes);
		// Base62编码
		String token = Base62Util.base62Encode(sidBytes);
		if (log.isDebugEnabled()) {
			log.debug("generatorToken " + "uid:" + uid + ",token:" + token);
		}

		return token;
	}

	/**
	 * 中间8字节根据不同版本号生成方式不同
	 * @param version 当前生成token的版本号
	 * @param uid 用户唯一标识
	 * @param create_token_key token生成的密钥
	 * @return 中间8字节
	 */
	private static byte[] generate8MD5ByVersion(int version, Integer uid,
			String create_token_key) {
		if (version == TokenVersion.VERSION_1.getValue()) {
			String udidBuilder = uid + create_token_key + System.currentTimeMillis();
			byte[] udidBytes = TokenCommonUtil
					.calculateMD5(udidBuilder.toString().getBytes());
			byte[] udidHalfBytes = new byte[8];
			System.arraycopy(udidBytes, 0, udidHalfBytes, 0, udidHalfBytes.length);
			return udidHalfBytes;
		}
		else if (version == TokenVersion.VERSION_2.getValue()) {
			// {1字节的uid字节长度}
			int binaryLength = getBytesLength(uid);
			byte lengthBinary = (byte) binaryLength;
			// {1字节的uid字节长度}{binaryLength字节的uid串}
			byte[] uByte = TokenCommonUtil.long2Bytes(uid);
			byte[] sidBytes = TokenCommonUtil.byteMerger(new byte[] { lengthBinary },
					uByte);
			// {7 - binaryLength字节MD5的串}
			String udidBuilder = create_token_key + System.currentTimeMillis();
			byte[] udidBytes = TokenCommonUtil
					.calculateMD5(udidBuilder.toString().getBytes());

			byte[] udidHalfBytes = new byte[7 - binaryLength];
			System.arraycopy(udidBytes, 0, udidHalfBytes, 0, udidHalfBytes.length);
			// {1字节的uid字节长度}{binaryLength字节的uid串}{7 - binaryLength字节MD5的串}
			sidBytes = TokenCommonUtil.byteMerger(sidBytes, udidHalfBytes);
			return sidBytes;
		}
		return null;
	}

	private static int getBytesLength(Integer uid) {
		return Integer.toBinaryString(uid).length() % 8 == 0
				? Integer.toBinaryString(uid).length() / 8
				: Integer.toBinaryString(uid).length() / 8 + 1;
	}

}
