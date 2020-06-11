package com.little.g.springcloud.admin.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@ApiModel("统计结果Vo")
public class StatVo {

	@ApiModelProperty("字段")
	private String[] columns = new String[0];

	@ApiModelProperty("数据")
	private List<Map> rows = new ArrayList<>();

	public String[] getColumns() {
		return columns;
	}

	public void setColumns(String[] columns) {
		this.columns = columns;
	}

	public List<Map> getRows() {
		return rows;
	}

	public void setRows(List<Map> rows) {
		this.rows = rows;
	}

	public void add(Map... r) {
		rows.addAll(Arrays.asList(r));
	}

}
