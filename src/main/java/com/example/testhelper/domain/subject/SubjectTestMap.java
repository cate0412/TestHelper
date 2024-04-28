package com.example.testhelper.domain.subject;

import com.example.testhelper.domain.BaseTimeEntity;
import com.example.testhelper.domain.test.Test;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class SubjectTestMap extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="subject_id")
    private Subject subject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="test_id")
    private Test test;

    @Column(nullable = false)
    private Integer order;

}
