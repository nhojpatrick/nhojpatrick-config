module com.github.nhojpatrick.config.properties {
    exports com.github.nhojpatrick.config.properties;
    exports com.github.nhojpatrick.config.properties.internal
            to com.github.nhojpatrick.config.properties.test;
    requires org.apache.commons.collections4;
    requires org.apache.commons.lang3;
    requires org.slf4j;
    requires transitive org.apache.commons.configuration2;
}
