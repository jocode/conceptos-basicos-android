package com.crexative.tipcalc.fragments;

import com.crexative.tipcalc.models.TipRecord;

public interface TipHistoryListFragmentListener {

    void addToList(TipRecord tipRecord);
    void clearList();

}
