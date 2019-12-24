package com.tangze.treedist;

public interface Tree {
    int NOT_FOUND = -1;

    int getRoot();

    int getFirstChild(int var1);

    int getNextSibling(int var1);

    int getParent(int var1);

    int size();
}
