package org.talend.core.repository.services;

public interface IGitContentService {

    public abstract void setMenu(Object object);

    public abstract boolean isGIT();

    public abstract void createDropdownCombo(Object composite);

    public abstract void configureCombo(String descriptor);
}
