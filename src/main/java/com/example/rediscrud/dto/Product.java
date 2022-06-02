package com.example.rediscrud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {

    private static final long serialVersionUID = 7156526077883281623L;

    private Integer id;
    private String productName;
    @Temporal(TemporalType.TIMESTAMP)
    private Date generateTimestamp;
}
