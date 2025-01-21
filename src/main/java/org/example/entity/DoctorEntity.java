package org.example.entity;

import org.example.enums.GenderEnum;
import org.example.enums.TimeEnum;

import java.util.ArrayList;

public class DoctorEntity {

    private Long id;

    private String fullName;

    private String experience;

    private int age;

    private GenderEnum gender;

    private ArrayList<TimeEnum> freeTime;

    public DoctorEntity() {}

    public DoctorEntity(Long id, String fullName, String experience, int age, GenderEnum gender) {
        this.id = id;
        this.fullName = fullName;
        this.experience = experience;
        this.age = age;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public ArrayList<TimeEnum> getFreeTime() {
        return freeTime;
    }

    public void setFreeTime(ArrayList<TimeEnum> freeTime) {
        this.freeTime = freeTime;
    }

}
