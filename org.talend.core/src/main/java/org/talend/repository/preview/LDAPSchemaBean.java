// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006-2007 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License.
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
package org.talend.repository.preview;

import org.talend.core.model.utils.TalendTextUtils;

/**
 * This is a Java Bean class which used for storing LDAP schema. <br/>
 * 
 * @author ftang, 09/18/2007
 * 
 */
public class LDAPSchemaBean {

    private String host;

    private String port;

    private String authMethod;

    private String user;

    private String passwd;

    private String multiValueSeparator;

    private String countLimit;

    private String timeOutLimit;

    private String baseDN;

    private String aliasDereferenring;

    private String referrals;

    private String filter;

    private boolean isAuthen;

    private String encryMethod;

    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return this.port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPasswd() {
        return this.passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getMultiValueSeparator() {
        return this.multiValueSeparator;
    }

    public void setMultiValueSeparator(String multiValueSeparator) {
        this.multiValueSeparator = multiValueSeparator;
    }

    public String getLimit() {
        return this.countLimit;
    }

    public void setLimit(String limit) {
        this.countLimit = limit;
    }

    public String getAuthMethod() {
        return this.authMethod;
    }

    public void setAuthMethod(String authMethod) {
        this.authMethod = authMethod;
    }

    public String getCountLimit() {
        return this.countLimit;
    }

    public void setCountLimit(String countLimit) {
        this.countLimit = countLimit;
    }

    public String getTimeOutLimit() {
        return this.timeOutLimit;
    }

    public void setTimeOutLimit(String timeOutLimit) {
        this.timeOutLimit = timeOutLimit;
    }

    public String getBaseDN() {
        return this.baseDN;
    }

    public void setBaseDN(String baseDN) {
        this.baseDN = baseDN;
    }

    public String getAliasDereferenring() {
        return this.aliasDereferenring;
    }

    public void setAliasDereferenring(String aliasDereferenring) {
        this.aliasDereferenring = aliasDereferenring;
    }

    public String getReferrals() {
        return this.referrals;
    }

    public void setReferrals(String referrals) {
        this.referrals = referrals;
    }

    public String getFilter() {
        return this.filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public boolean isAuthen() {
        return this.isAuthen;
    }

    public void setAuthen(boolean isAuthen) {
        this.isAuthen = isAuthen;
    }

    public String getEncryMethod() {
        return this.encryMethod;
    }

    public void setEncryMethod(String encryMethod) {
        String ldaps = TalendTextUtils.addQuotes("LDAPS(SSL)");

        // Repository value is different as UI's.
        if (encryMethod.equals(ldaps)) {
            this.encryMethod = "LDAPS";
        } else {
            this.encryMethod = encryMethod;
        }
    }

}
