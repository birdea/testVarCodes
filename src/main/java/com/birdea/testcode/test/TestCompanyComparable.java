package com.birdea.testcode.test;

import com.birdea.testcode.model.Company;
import com.birdea.testcode.util.L;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestCompanyComparable {

    public void test() {
        List<Company> companies = new ArrayList<>();
        companies.add(new Company("SKT","SK텔레콤","Y","3"));
        companies.add(new Company("SKT","SK브로드밴드","Y","2"));
        companies.add(new Company("SKT","11번가","Y","3"));
        companies.add(new Company("SKT","SK플래닛","Y","7"));
        companies.add(new Company("SKT","ADT 캡스","Y","5"));
        companies.add(new Company("SKT","VIP","Y","6"));

        Collections.sort(companies);

        L.msg("-start size:"+companies.size());
        for (Company company : companies) {
            L.msg(company);
        }
        L.msg("-end size:"+companies.size());
    }
}
