package com.kata.maitred.domain;

import java.util.List;

public interface TableRepository {
    void save(Table table);

    List<Table> findAll();
}
