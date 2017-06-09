package xyz.chanjkf.utils.page;

import java.util.ArrayList;
import java.util.List;

public class Page<E> {

    private int curPage;
    private int pageSize;
    private int startRow;
    private long totalRows;
    private int totalPages;
    private List<E> result;

    public Page(int curPage, int pageSize) {
        this.curPage = curPage;
        this.pageSize = pageSize;
        this.startRow = curPage > 1 ? (curPage - 1) * pageSize : 0;
        this.result = new ArrayList<E>();
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
        this.startRow = curPage > 1 ? (curPage - 1) * pageSize : 0;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        this.startRow = curPage > 1 ? (curPage - 1) * pageSize : 0;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public long getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(long totalRows) {
        this.totalRows = totalRows;
    }

    public int getTotalPages() {
        if(totalRows<=0 || pageSize<=0)
            return this.totalPages;

        int tempTotalRow= Integer.parseInt(String.valueOf(totalRows));
        return ( tempTotalRow/pageSize) + (tempTotalRow % pageSize  >0?1:0);
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<E> getResult() {
        return result;
    }

    public void setResult(List<E> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Page{" +
                "curPage=" + curPage +
                ", pageSize=" + pageSize +
                ", startRow=" + startRow +
                ", totalRows=" + totalRows +
                ", totalPages=" + totalPages +
                ", result=" + result +
                '}';
    }

}
