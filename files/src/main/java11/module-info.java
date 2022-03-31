module com.github.nhojpatrick.config.files {
    exports com.github.nhojpatrick.config.files;
    exports com.github.nhojpatrick.config.files.internal
            to com.github.nhojpatrick.config.files.test;
    requires com.github.spotbugs.annotations;
    requires org.apache.commons.collections4;
    requires org.apache.commons.lang3;
    requires org.slf4j;
}
