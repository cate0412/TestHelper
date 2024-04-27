package com.example.testhelper.domain.test;

import com.example.testhelper.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Question extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id")
    private Test test;


    @Column(nullable = false)
    private String content;

    @Column(nullable = false, columnDefinition = "int default 1")
    private Integer order;

    @Column(nullable = false)
    private String type;

    @BatchSize(size = 100)
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<QuestionItem> items = new ArrayList<>();

}
