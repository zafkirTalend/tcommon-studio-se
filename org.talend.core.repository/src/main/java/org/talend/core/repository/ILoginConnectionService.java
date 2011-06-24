package org.talend.core.repository;

public interface ILoginConnectionService {

    // public boolean isAllowLocalConnection();

    public String checkConnectionValidation(String name, String description, String email, String password, String workspace,
            String url);
}
