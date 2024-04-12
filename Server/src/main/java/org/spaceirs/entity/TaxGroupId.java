package org.spaceirs.entity;

public enum TaxGroupId {
    BIG(1),
    MID(2),
    LOW(3);

    public final int value;

    private TaxGroupId(int value) {
        this.value = value;
    }
}
