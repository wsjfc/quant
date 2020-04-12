package com.quant.quant.ashare.model;

import java.util.List;

public class ListStatusData {
    private List<String> fields;
    private List<List<String>> items;
    private boolean has_more;

    public List<String> getFields() {
        return fields;
    }

    public List<List<String>> getItems() {
        return items;
    }

    public boolean isHas_more() {
        return has_more;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public void setItems(List<List<String>> items) {
        this.items = items;
    }

    public void setHas_more(boolean has_more) {
        this.has_more = has_more;
    }
}
