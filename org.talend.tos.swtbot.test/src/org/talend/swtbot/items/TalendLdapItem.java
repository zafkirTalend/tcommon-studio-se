package org.talend.swtbot.items;

import org.talend.swtbot.Utilities;

public class TalendLdapItem extends TalendMetadataItem {

    public TalendLdapItem() {
        super(Utilities.TalendItemType.LDAP);
    }

    public TalendLdapItem(String itemName) {
        super(itemName, Utilities.TalendItemType.LDAP);
    }
}
