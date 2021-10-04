package com.kata.maitred.infrastructure;

import com.kata.maitred.domain.Table;
import com.kata.maitred.domain.TableRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryTableRepository implements TableRepository {
    private final List<Table> tables = new ArrayList<>();

    @Override
    public void save(Table table) {
        this.tables.add(table);
    }

    @Override
    public List<Table> findAll() {
        return tables;
    }

}
