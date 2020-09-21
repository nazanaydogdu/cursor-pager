package com.kboxglobal.naydogdu.assignment.paging;

import java.util.List;

public class CursorPager<T> {
    List<T> items;
    int limit;
    String next;
    int total;

    public CursorPager() {
    }

    public CursorPager(List<T> items, int limit, String next, int total) {
        this.items = items;
        this.limit = limit;
        this.next = next;
        this.total = total;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
