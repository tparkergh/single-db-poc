@TypeDefs({
    @TypeDef(
        name = "trimmingStringType",
        defaultForType = String.class,
        typeClass = TrimmingStringType.class)
})
package gov.ca.cwds.cares.persistence.entity;

import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
