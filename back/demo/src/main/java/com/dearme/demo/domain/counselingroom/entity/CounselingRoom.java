package com.dearme.demo.domain.counselingroom.entity;

import com.dearme.demo.domain.counseling.entity.Counseling;
import com.dearme.demo.domain.group.entity.Group;
import com.dearme.demo.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CounselingRoom {
    @Id
    private String sessionName;

    private String counselorToken;

    @OneToMany(mappedBy = "counselingRoom")
    private List<Counseling> counselings = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "counselor_id")
    private User counselor;

    @OneToOne
    @JoinColumn(name = "group_id")
    private Group group;

    public void setCounselor(User counselor){
        this.counselor = counselor;
    }

    @Builder
    public CounselingRoom(String sessionName, String counselorToken){
        this.sessionName = sessionName;
        this.counselorToken = counselorToken;
    }
}