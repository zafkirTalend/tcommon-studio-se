package org.talend.core.repository.model;

import org.talend.utils.json.JSONArray;

public interface IRemoteRepositoryFactory {

    public abstract JSONArray getAllRemoteLocks();
}
