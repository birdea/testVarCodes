package com.birdea.testcode.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Company implements Comparable<Company>{
    public String code;
    public String name;
    public String enable;
    public String sequence;

    public Company(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public Company(String code, String name, String enable, String sequence) {
        this.code = code;
        this.name = name;
        this.enable = enable;
        this.sequence = sequence;
    }

    public static Company getDefault() {
        return new Company("SKT", "SK텔레콤");
    }

    public static String[] getArray(List<Company> list) {
        Collections.sort(list);
        List<String> companyName = new ArrayList<>();
        for (Company company : list) {
            if (company.isEnable()) {
                companyName.add(company.name);
            }
        }
        return companyName.toArray(new String[companyName.size()]);
    }

    public boolean isEnable() {
        return ("Y".equals(enable));
    }

    @Override
    public String toString() {
        return new StringBuilder().append("{ ")
                .append("code:").append(code)
                .append(", name:").append(name)
                .append(", enable:").append(enable)
                .append(", sequence:").append(sequence)
                .append(" }")
                .toString();
    }

    @Override
    public int compareTo(Company comp) {
        int src = Integer.parseInt(sequence);
        int dst = Integer.parseInt(comp.sequence);
        return (src - dst);
    }
}
