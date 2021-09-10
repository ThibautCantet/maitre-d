package com.kata.maitred.domain;

public interface TableRepository {
    void save(Table table);

    Table find();
}
