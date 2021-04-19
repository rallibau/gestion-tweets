package com.rallibau.twiter.hasTag.domain;

import com.rallibau.shared.domain.AggregateRoot;

public class HasTag extends AggregateRoot {
    private Text id;
    private Used used;

    public HasTag(Text id, Used used) {
        this.id = id;
        this.used = used;
    }

    public HasTag() {
        this.id = null;
        this.used = null;
    }

    public Text id() {
        return id;
    }

    public Used used() {
        return used;
    }

    public void addUsed() {
        this.used = new Used(used.value() + 1);
    }
}
