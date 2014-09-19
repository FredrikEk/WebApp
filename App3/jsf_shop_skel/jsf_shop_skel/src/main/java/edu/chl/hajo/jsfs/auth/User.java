package edu.chl.hajo.jsfs.auth;

import java.io.Serializable;

/**
 *
 * @author hajo
 */
public class User implements Serializable {

    private final String name;

    User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
