package com.hafiz.sm.post.entities;

import com.hafiz.sm.post.core.crud.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "place")
public class Place extends BaseEntity {
    private String name;
}
