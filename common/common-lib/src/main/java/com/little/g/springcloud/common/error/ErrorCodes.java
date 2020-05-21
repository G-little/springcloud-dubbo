package com.little.g.springcloud.common.error;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lengligang on 2019/3/12.
 */
public abstract class ErrorCodes {

	protected Integer start;

	protected Integer end;

	private static Map<Integer, String> codeMsgs = new HashMap<>();

	public ErrorCodes(ErrorCodeDiv.CodeBorder border) {
		if (border == null || border.getStart() == null || border.getEnd() == null
				|| border.getEnd() < border.getStart()) {
			throw new Error("ErrorCodes init failed border:" + border);
		}
		start = border.getStart();
		end = border.getEnd();
	}

	protected abstract void addCodes();

	protected void addCode2Map(Integer code, String msg) {
		if (code == null || code < start || code > end) {
			throw new Error("error code invalid code:" + code);
		}
		codeMsgs.put(code, msg);
	}

	public static String getDefaultMsg(Integer code) {
		return codeMsgs.get(code);
	}

}
