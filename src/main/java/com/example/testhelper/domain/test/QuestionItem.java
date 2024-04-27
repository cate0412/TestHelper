package com.example.testhelper.domain.test;

import com.example.testhelper.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class QuestionItem extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @Column(nullable = false, columnDefinition = "int default 1")
    private Integer order;

    @Column
    private String content;

    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer score;
}
