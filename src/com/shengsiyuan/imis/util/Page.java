package com.shengsiyuan.imis.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * <p>
 * Title: Page
 * </p>
 * <p>
 * Description: 一个在全系统的各个模块中可以使用的分页,用于Servlet中
 * </p>
 * <p>
 * Company: 自由职业
 * </p>
 * 
 * @author lsw
 * @date 2018年2月12日
 */
public class Page {
    
    public static StringBuffer buildSingleHref(String relativePath, String appendParam, long page, long range, boolean isBold, Object content) {
        long start = (page - 1) * range;
        StringBuffer sb = new StringBuffer();
        if (isBold) {
            return sb.append("<B>").append(page).append("</B>\n");
        }
        sb.append("<a href='").append(relativePath).append("?");
        if (null != appendParam && !"".equals(appendParam)) {
            sb.append(appendParam);
            sb.append("&");
        }
        
        sb.append("start=").append(start).append("&range=").append(range).append("'>").append(content).append("</a>\n");

        return sb;
    }
    
    public static StringBuffer loopHref(String relativePath, String appendParam, long currentPage, long range, long loopStart, long loopEnd) {
        
        StringBuffer result = new StringBuffer();
        for (long i = loopStart; i <= loopEnd; i++) {
            StringBuffer href = buildSingleHref(relativePath, appendParam, i, range, i == currentPage ? true : false, i);
            result.append(href);
        }
        return result;
    }

    public static void main(String[] args) {
//        String result = buildSingleHref("ListNewsCatalog", "name=zhangsan&age=11", 3, 5).toString();
//        System.out.println(result);
    }
    
    /**
     * 参数说明:
     * 
     * req 用于获取URL地址 例如: imis01/NewsCatalog/abc?start=0&range=5 abc
     * appendParam 用于追加自定义参数
     * start 用于确定当前是第几页
     * range 用于确定每页显示几条数据
     * totalNums 用于确定总记录数
     * 
     * 显示规则：
     */
    public static String pageLink(HttpServletRequest req, String appendParam,
            long start, long range, long totalNums) {
        String baseURI = req.getRequestURI();
        baseURI = baseURI.substring(baseURI.lastIndexOf("/") + 1);
        
        long currentPage = start / range + 1;
        long totalPages = totalNums / range;
        if (totalNums % range != 0) {
            totalPages++;
        }
        //当前页数 - 偏移量 - 2 如果小于等于0那么loopStart就为1
        long loopStart = 1;
        long loopEnd = currentPage;
        int offset = 5;
        StringBuffer extraFirstHref = new StringBuffer();
        StringBuffer extraEndHref = new StringBuffer();
        
        //如果不是第一页显示<
        String content = "<img src=\"../Images/prev.gif\" width=\"10\" height=\"10\" border=\"0\" />";
        StringBuffer beginPage = new StringBuffer();
        StringBuffer endPage= new StringBuffer();
        if (loopStart != currentPage) {
            beginPage = buildSingleHref(baseURI, appendParam, currentPage - 1, range, false, content);
        }
        
        if (currentPage - offset - 2 > 0) {
            //增加 1 ...
            extraFirstHref = buildSingleHref(baseURI, appendParam, loopStart, range, false, loopStart).append(" ... ");
            loopStart = currentPage - offset;
        }
        
        StringBuffer preHref = loopHref(baseURI, appendParam, currentPage, range, loopStart, loopEnd);
        
        loopStart = currentPage;
        loopEnd = totalPages;
        
        if (loopStart + offset + 1 < totalPages) {
            //增加 ... totalPages
            extraEndHref = extraEndHref.append(" ... ").append(buildSingleHref(baseURI, appendParam, totalPages, range, false, totalPages));
            loopEnd = loopStart + offset;
        }
        
       //如果不是最后一页显示>
        content = "<img src=\"../Images/next.gif\" width=\"10\" height=\"10\" border=\"0\" />";
        if (totalPages != currentPage) {
            endPage = buildSingleHref(baseURI, appendParam, currentPage + 1, range, false, content);
        }
        
        
        StringBuffer nextHref = loopHref(baseURI, appendParam, currentPage, range, loopStart + 1, loopEnd);
        
        String result = beginPage.append(extraFirstHref).append(preHref).append(nextHref).append(extraEndHref).append(endPage).toString();
        
        return result;
    }
}
