package com.little.g.springcloud.common.utils;

import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.common.ResultJson;
import com.little.g.springcloud.common.dto.Page;
import com.little.g.springcloud.common.error.CommonErrorCodes;

import java.util.List;

/**
 * 响应操作结果 <pre>
 *  {
 *      errno： 错误码，
 *      errmsg：错误消息，
 *      data：  响应数据
 *  }
 * </pre>
 *
 * <p>
 * 错误码：
 * <ul>
 * <li>0，成功；
 * <li>4xx，前端错误，说明前端开发者需要重新了解后端接口使用规范：
 * <ul>
 * <li>401，参数错误，即前端没有传递后端需要的参数；
 * <li>402，参数值错误，即前端传递的参数值不符合后端接收范围。
 * </ul>
 * <li>5xx，后端错误，除501外，说明后端开发者应该继续优化代码，尽量避免返回后端错误码：
 * <ul>
 * <li>501，验证失败，即后端要求用户登录；
 * <li>502，系统内部错误，即没有合适命名的后端内部错误；
 * <li>503，业务不支持，即后端虽然定义了接口，但是还没有实现功能；
 * <li>504，更新数据失效，即后端采用了乐观锁更新，而并发更新时存在数据更新失效；
 * <li>505，更新数据失败，即后端数据库更新失败（正常情况应该更新成功）。
 * </ul>
 * <li>6xx，小商城后端业务错误码， 具体见litemall-admin-api模块的AdminResponseCode。
 * <li>7xx，管理后台后端业务错误码， 具体见litemall-wx-api模块的WxResponseCode。
 * </ul>
 */
public class ResponseUtil {

    public static ResultJson ok() {
        ResultJson r = new ResultJson();
        return r;
    }

    public static <T> ResultJson<T> ok(T data) {
        ResultJson<T> r = new ResultJson<>();
        r.setData(data);
        return r;
    }

    public static ResultJson fail() {
        ResultJson r = new ResultJson();
        r.setC(ResultJson.SYSTEM_UNKNOWN_EXCEPTION);
        return r;
    }

    public static ResultJson fail(int errno, String errmsg) {
        ResultJson r = new ResultJson();
        r.setC(errno);
        r.setM(errmsg);
        return r;
    }

    public static <T> ResultJson<Page<T>> okList(List<T> list) {
        ResultJson<Page<T>> r = new ResultJson();
        Page<T> p = new Page<>();
        p.setResult(list);
        p.setTotalCount(list.size());
        p.setCurrentPage(1);
        if (list.size() != 0) {
            p.setPageSize(list.size());
        }
        r.setData(p);
        return r;
    }

    public static <T> ResultJson<Page<T>> okPage(PageInfo<T> page) {
        return okList(page.getList(), page);
    }

    public static <T> ResultJson<Page<T>> okList(List<T> list, PageInfo<?> page) {
        ResultJson<Page<T>> r = new ResultJson();
        Page<T> p = new Page<>();
        p.setResult(list);
        p.setTotalCount(page.getTotal());
        p.setCurrentPage(page.getPageNum());
        if (page.getPageSize() != 0) {
            p.setPageSize(page.getPageSize());
        }

        r.setData(p);
        return r;
    }

    public static ResultJson badArgument() {
        return fail(CommonErrorCodes.INVALID_PARAM, "参数不对");
    }

    public static ResultJson badArgumentValue() {
        return fail(CommonErrorCodes.INVALID_VALUE, "参数值不对");
    }

    public static ResultJson unlogin() {
        return fail(CommonErrorCodes.NOT_LOGIN, "请登录");
    }

    public static ResultJson serious() {
        return fail(CommonErrorCodes.SYSTEM_UNKNOWN_EXCEPTION, "系统内部错误");
    }

    public static ResultJson unsupport() {
        return fail(CommonErrorCodes.BUSINESS_UNSUPPORT_ERROR, "业务不支持");
    }

    public static ResultJson updatedDateExpired() {
        return fail(CommonErrorCodes.UPDATE_DATA_TIMEOUT_ERROR, "更新数据已经失效");
    }

    public static ResultJson updatedDataFailed() {
        return fail(CommonErrorCodes.UPDATE_DATA_FAILED_ERROR, "更新数据失败");
    }

    public static ResultJson unauthz() {
        return fail(CommonErrorCodes.NO_PRIVILEGE_ERROR, "无操作权限");
    }

}
