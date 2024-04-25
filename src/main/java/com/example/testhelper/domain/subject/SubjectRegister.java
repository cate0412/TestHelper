package com.example.testhelper.domain.subject;

import com.example.testhelper.domain.BaseTimeEntity;
import com.example.testhelper.domain.system.Code;
import com.example.testhelper.domain.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@NoArgsConstructor
public class SubjectRegister extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status")
    private Code status;


    @Builder
    public SubjectRegister(User user, Subject subject, Code status) {
        this.user = user;
        this.subject = subject;
        this.status = status;
    }

    public void setStatus(Code statusCode) {
        this.status = statusCode;
    }

}
