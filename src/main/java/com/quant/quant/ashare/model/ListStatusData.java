package com.quant.quant.ashare.model;

import lombok.Data;

import java.util.List;

@Data
public class ListStatusData {
    private List<String> fields;
    private List<List<String>> items;
    private boolean has_more;
}
