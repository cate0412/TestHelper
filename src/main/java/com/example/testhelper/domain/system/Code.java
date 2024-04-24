package com.example.testhelper.domain.system;

import com.example.testhelper.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Getter
@Entity
@NoArgsConstructor
public class Code extends BaseTimeEntity {

    @Id
    private String name;

    @Column(nullable = false)
    private String label;

    @Column
    private String description;

    @Column
    private Integer order;

    @Column(nullable = false, columnDefinition = "tinyint(1) default 1")
    private Boolean usage = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group")
    @ToString.Exclude
    private CodeGroup codeGroup;
}
