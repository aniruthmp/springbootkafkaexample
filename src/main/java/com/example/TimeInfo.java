package com.example;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by a.c.parthasarathy on 10/19/16.
 */
@Data
public class TimeInfo implements Serializable {
    private String time;
    private String label;

}
