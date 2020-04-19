package com.clownfish7.variable;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author You
 * @create 2020-04-19 18:14
 */
@Data
@Accessors(fluent = true)
public class Holiday implements Serializable {
    private String id;
    private int num;
}
