package org.efrey;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Configuration {

    private final String[] groupping;
    private List<ColumnDefinition> columnDefinitions;

    public Configuration(String[] grouping, List<ColumnDefinition> columnDefinitions) {
        this.groupping = Arrays.copyOf(grouping, grouping.length);
        if (columnDefinitions == null) {
            throw new IllegalArgumentException("Columns definition can't be null");
        }
        this.columnDefinitions = Collections.unmodifiableList(columnDefinitions);
    }


    /* Getters */
    public String[] getGroupping() {
        return Arrays.copyOf(groupping, groupping.length);
    }

    public List<ColumnDefinition> getColumns() {
        return Collections.unmodifiableList(this.columnDefinitions);
    }

    /* Builder */
    public static ConfigurationBuilder Builder() {
        return new ConfigurationBuilder();
    }

    static class ConfigurationBuilder {

        private List<String> groupings = new ArrayList<>();
        private List<ColumnDefinition> columnDefinitions = new ArrayList<>();

        public ConfigurationBuilder addGrouping(String grouping) {
            this.groupings.add(grouping);
            return this;
        }

        public ConfigurationBuilder addColumn(ColumnDefinition columnDefinition) {
            this.columnDefinitions.add(columnDefinition);
            return this;
        }

        public Configuration build() {
            return new Configuration(
                    groupings.toArray(new String[groupings.size()]),
                    this.columnDefinitions
            );
        }

        public ConfigurationBuilder addGroupings(String[] groupings) {
            if (groupings != null) {
                this.groupings.addAll(Arrays.asList(groupings));
            }
            return this;

        }
    }
}
