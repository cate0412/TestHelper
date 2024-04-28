package com.example.testhelper.domain.test;

import com.example.testhelper.domain.BaseTimeEntity;
import com.example.testhelper.domain.system.Code;
import com.example.testhelper.domain.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class TestResult extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "test_id")
    private Test test;

    @Column
    private Integer score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status")
    private Code status;

    @BatchSize(size = 100)
    @OneToMany(mappedBy = "testResult", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<QuestionAnswer> questionAnswer = new ArrayList<>();

    @Builder
    public TestResult(Test test, User user, Code status){
        this.test = test;
        this.user = user;
        this.status = status;
    }
}
