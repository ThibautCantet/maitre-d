package com.kata.maitred.infrastructure;

import com.kata.maitred.domain.Table;
import com.kata.maitred.domain.TableRepository;

public class InMemoryTableRepository implements TableRepository {
    private Table table;
    @Override
    public void save(Table table) {
        this.table = table;
    }

    @Override
    public Table find() {
        return table;
    }

    public void clear() {
        this.table = null;
    }
}
