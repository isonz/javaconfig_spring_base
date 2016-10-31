package cn.ptp;

import java.util.List;

public class Page<T>
{
    private List<T> list;
    private double total = 0;
    private double pages = 1;

    public Page(List<T> list)
    {
        setList(list);
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getPages() {
        return pages;
    }

    public void setPages(double pages) {
        this.pages = pages;
    }
}
