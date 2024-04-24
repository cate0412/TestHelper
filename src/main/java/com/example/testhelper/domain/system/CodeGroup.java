package com.example.testhelper.domain.system;

import com.example.testhelper.domain.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class CodeGroup extends BaseTimeEntity {

    @Id
    private String name;

    @Column(nullable = false)
    private String prefix;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "codeGroup")
    private List<Code> Code = new ArrayList<>();
}
