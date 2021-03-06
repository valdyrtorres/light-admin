package org.lightadmin.core.config.domain.unit;

import com.google.common.collect.Sets;
import org.lightadmin.api.config.unit.*;
import org.lightadmin.core.config.bootstrap.parsing.configuration.DomainConfigurationUnitType;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

import static com.google.common.collect.Sets.newLinkedHashSet;
import static org.lightadmin.core.util.DomainConfigurationUtils.configurationDomainType;

public class ConfigurationUnits implements Iterable<ConfigurationUnit>, Serializable {

    private final String configurationClassName;
    private final Class<?> domainType;

    private final Set<ConfigurationUnit> configurationUnits;

    public ConfigurationUnits(final Class configurationClass, ConfigurationUnit... configurationUnits) {
        this(configurationClass, Sets.<ConfigurationUnit>newLinkedHashSet(Arrays.asList(configurationUnits)));
    }

    public ConfigurationUnits(final Class configurationClass, final Set<ConfigurationUnit> configurationUnits) {
        Assert.notNull(configurationClass);
        this.domainType = configurationDomainType(configurationClass);
        Assert.notNull(this.domainType);
        this.configurationClassName = configurationClass.getSimpleName();
        this.configurationUnits = newLinkedHashSet(configurationUnits);
    }

    public ConfigurationUnits(final String configurationClassName, final Class domainType, final Set<ConfigurationUnit> configurationUnits) {
        Assert.notNull(domainType);
        this.domainType = domainType;
        this.configurationClassName = configurationClassName;
        this.configurationUnits = newLinkedHashSet(configurationUnits);
    }

    @Override
    public Iterator<ConfigurationUnit> iterator() {
        return configurationUnits.iterator();
    }

    public String getConfigurationClassName() {
        return configurationClassName;
    }

    public Class<?> getDomainType() {
        return domainType;
    }

    public ConfigurationUnit forDomainUnitType(DomainConfigurationUnitType domainConfigurationUnitType) {
        for (ConfigurationUnit configurationUnit : configurationUnits) {
            if (configurationUnit.getDomainConfigurationUnitType().equals(domainConfigurationUnitType)) {
                return configurationUnit;
            }
        }
        return null;
    }

    public FiltersConfigurationUnit getFilters() {
        return (FiltersConfigurationUnit) forDomainUnitType(DomainConfigurationUnitType.FILTERS);
    }

    public ScopesConfigurationUnit getScopes() {
        return (ScopesConfigurationUnit) forDomainUnitType(DomainConfigurationUnitType.SCOPES);
    }

    public SidebarsConfigurationUnit getSidebars() {
        return (SidebarsConfigurationUnit) forDomainUnitType(DomainConfigurationUnitType.SIDEBARS);
    }

    public FieldSetConfigurationUnit getListViewConfigurationUnit() {
        return (FieldSetConfigurationUnit) forDomainUnitType(DomainConfigurationUnitType.LIST_VIEW);
    }

    public ScreenContextConfigurationUnit getScreenContext() {
        return (ScreenContextConfigurationUnit) forDomainUnitType(DomainConfigurationUnitType.SCREEN_CONTEXT);
    }

    public EntityMetadataConfigurationUnit getEntityConfiguration() {
        return (EntityMetadataConfigurationUnit) forDomainUnitType(DomainConfigurationUnitType.CONFIGURATION);
    }

    public FieldSetConfigurationUnit getShowViewConfigurationUnit() {
        return (FieldSetConfigurationUnit) forDomainUnitType(DomainConfigurationUnitType.SHOW_VIEW);
    }

    public FieldSetConfigurationUnit getFormViewConfigurationUnit() {
        return (FieldSetConfigurationUnit) forDomainUnitType(DomainConfigurationUnitType.FORM_VIEW);
    }

    public FieldSetConfigurationUnit getQuickViewConfigurationUnit() {
        return (FieldSetConfigurationUnit) forDomainUnitType(DomainConfigurationUnitType.QUICK_VIEW);
    }
}
