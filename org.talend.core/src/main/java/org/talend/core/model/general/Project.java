// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
//
// ============================================================================
package org.talend.core.model.general;

import org.talend.core.model.properties.PropertiesFactory;
import org.talend.core.model.properties.User;
import org.talend.core.model.temp.ECodeLanguage;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class Project {

    private org.talend.core.model.properties.Project project;

    /**
     * DOC smallet Project constructor comment.
     * 
     * @param label
     * @param project
     */
    public Project(org.talend.core.model.properties.Project project) {
        this.project = project;
    }

    public Project(String label) {
        this.project = PropertiesFactory.eINSTANCE.createProject();
        project.setLabel(label);
    }

    public Project() {
        this.project = PropertiesFactory.eINSTANCE.createProject();
    }

    /**
     * Getter for label.
     * 
     * @return the label
     */
    public String getLabel() {
        return project.getLabel();
    }

    /**
     * Sets the label.
     * 
     * @param label the label to set
     */
    public void setLabel(String label) {
        project.setLabel(label);
    }

    /**
     * Getter for technicalLabel.
     * 
     * @return the technicalLabel
     */
    public String getTechnicalLabel() {
        return project.getTechnicalLabel();
    }

    /**
     * Sets the technicalLabel.
     * 
     * @param technicalLabel the technicalLabel to set
     */
    public void setTechnicalLabel(String technicalLabel) {
        project.setTechnicalLabel(technicalLabel);
    }

    public String toString() {
        return getLabel();
    }

    /**
     * Getter for author.
     * 
     * @return the author
     */
    public User getAuthor() {
        return project.getAuthor();
    }

    /**
     * Sets the author.
     * 
     * @param author the author to set
     */
    public void setAuthor(User author) {
        project.setAuthor(author);
    }

    /**
     * Getter for description.
     * 
     * @return the description
     */
    public String getDescription() {
        return project.getDescription();
    }

    /**
     * Sets the description.
     * 
     * @param description the description to set
     */
    public void setDescription(String description) {
        project.setDescription(description);
    }

    /**
     * Getter for id.
     * 
     * @return the id
     */
    public int getId() {
        return project.getId();
    }

    /**
     * Sets the id.
     * 
     * @param id the id to set
     */
    public void setId(int id) {
        project.setId(id);
    }

    /**
     * Getter for language.
     * 
     * @return the language
     */
    public ECodeLanguage getLanguage() {
        return ECodeLanguage.getCodeLanguage(project.getLanguage());
    }

    /**
     * Sets the language.
     * 
     * @param language the language to set
     */
    public void setLanguage(ECodeLanguage language) {
        project.setLanguage(language.getName());
    }

    /**
     * Getter for local.
     * 
     * @return the local
     */
    public boolean isLocal() {
        return project.isLocal();
    }

    /**
     * Sets the local.
     * 
     * @param local the local to set
     */
    public void setLocal(boolean local) {
        project.setLocal(local);
    }

    /**
     * create technical name.
     * 
     * @param name
     * @return
     */
    public static String createTechnicalName(String name) {
        if (name != null) {
            name = name.toUpperCase();
            name = name.replace(" ", "_");
            name = name.replace("-", "_");
        }
        return name;
    }

    public org.talend.core.model.properties.Project getEmfProject() {
        return project;
    }

}
