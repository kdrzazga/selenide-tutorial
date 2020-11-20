package org.kd.common;

import org.apache.http.auth.UsernamePasswordCredentials;

public class TestDataFactory {

    public UsernamePasswordCredentials createCredentials(){
        return new UsernamePasswordCredentials("adam@false.customer", "f@l$e.passw0rd");
    }
}
