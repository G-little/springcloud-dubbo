
package com.little.g.springcloud.common.dto;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mayan on 14-11-5.
 */
@ApiModel
public class Page<T> implements Serializable {

	// 初始化size
	private final static int INIT_SIZE = 20;

	private static final long serialVersionUID = 5784595885066034295L;

	@ApiModelProperty("单页条数")
	private int pageSize = INIT_SIZE;

	@ApiModelProperty("分页总条数")
	private long totalCount;

	@ApiModelProperty("当前页")
	private int currentPage;

	private List<T> data;

	public Page() {
		// 默认构造器
	}

	public static <X extends Page> X newInstance(PageInfo info, Class<X> clazz) {
		X page = null;
		try {
			page = clazz.newInstance();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		page.setResult(info.getList());
		page.setTotalCount(info.getTotal());
		page.setCurrentPage(info.getPageNum());
		page.setPageSize(info.getPageSize());
		return page;
	}

	public Page(int currentPage) {
		this.currentPage = currentPage;
	}

	public Page(int currentPage, int pageSize) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
	}

	/**
	 * 获取开始索引
	 * @return
	 */
	@ApiModelProperty("开始索引")
	public int getStartIndex() {
		return (getCurrentPage() - 1) * this.pageSize;
	}

	/**
	 * 获取结束索引
	 * @return
	 */
	@ApiModelProperty("结束索引")
	public int getEndIndex() {
		return getCurrentPage() * this.pageSize;
	}

	/**
	 * 是否第一页
	 * @return
	 */
	@ApiModelProperty("是否第一页")
	public boolean isFirstPage() {
		return getCurrentPage() <= 1;
	}

	/**
	 * 是否末页
	 * @return
	 */
	@ApiModelProperty("是否最后一页")
	public boolean isLastPage() {
		return getCurrentPage() >= getPageCount();
	}

	/**
	 * 获取下一页页码
	 * @return
	 */
	@ApiModelProperty("下一页码")
	public int getNextPage() {
		if (isLastPage()) {
			return getCurrentPage();
		}
		return getCurrentPage() + 1;
	}

	/**
	 * 获取上一页页码
	 * @return
	 */
	@ApiModelProperty("上一页码")
	public int getPreviousPage() {
		if (isFirstPage()) {
			return 1;
		}
		return getCurrentPage() - 1;
	}

	/**
	 * 获取当前页页码
	 * @return
	 */
	public int getCurrentPage() {
		if (currentPage == 0) {
			currentPage = 1;
		}
		return currentPage;
	}

	/**
	 * 取得总页数
	 * @return
	 */
	@ApiModelProperty("总页数")
	public long getPageCount() {
		if (totalCount % pageSize == 0) {
			return totalCount / pageSize;
		}
		else {
			return totalCount / pageSize + 1;
		}
	}

	/**
	 * 取总记录数.
	 * @return
	 */
	public long getTotalCount() {
		return this.totalCount;
	}

	/**
	 * 设置当前页
	 * @param currentPage
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * 获取每页数据容量.
	 * @return
	 */
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 该页是否有下一页.
	 * @return
	 */
	public boolean hasNextPage() {
		return getCurrentPage() < getPageCount();
	}

	/**
	 * 该页是否有上一页.
	 * @return
	 */
	public boolean hasPreviousPage() {
		return getCurrentPage() > 1;
	}

	/**
	 * 获取数据集
	 * @return
	 */
	@ApiModelProperty("分页数据")
	public List<T> getResult() {
		return data;
	}

	/**
	 * 设置数据集
	 * @param data
	 */
	public void setResult(List<T> data) {
		this.data = data;
	}

	/**
	 * 设置总记录条数
	 * @param totalCount
	 */
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	// ==============扩展字段===============//
	private String unit = "条";// 单位

	private String extInfo;// 扩展信息

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getUnit() {
		return unit;
	}

	public void setExtInfo(String extInfo) {
		this.extInfo = extInfo;
	}

	public String getExtInfo() {
		return extInfo;
	}

}
