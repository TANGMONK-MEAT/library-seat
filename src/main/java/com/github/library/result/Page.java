package com.github.library.result;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 分页
 *
 * @author zwl
 * @date 2020/11/29 15:10
 */
public class Page<E> {

    /**
     * 当前为第几页，从 1 开始
     */
    private Integer currPage;

    /**
     * 每页的数据行数
     */
    private Integer pageSize;

    /**
     * 总页数
     */
    private Integer totalPage;

    /**
     * 总记录数
     */
    private Long totalCount;

    /**
     * 列表数据
     */
    private List<E> list;

    public Page(){}

    /**
     *
     * @param page {@link PageInfo}
     */
    public Page(PageInfo<E> page){
        this.currPage = page.getPageNum();
        this.pageSize = page.getPageSize();
        this.totalCount = page.getTotal();
        this.totalPage = page.getPages();
        this.list = page.getList();
    }


    @Override
    public String toString() {
        return "PageResult{" +
                "currPage=" + currPage +
                ", pageSize=" + pageSize +
                ", totalPage=" + totalPage +
                ", totalCount=" + totalCount +
                ", list=" + list +
                '}';
    }

    public Integer getCurrPage() {
        return currPage;
    }

    public void setCurrPage(Integer currPage) {
        this.currPage = currPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public List<E> getList() {
        return list;
    }

    public void setList(List<E> list) {
        this.list = list;
    }
}
