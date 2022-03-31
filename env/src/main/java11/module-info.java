module com.github.nhojpatrick.config.env {
    exports com.github.nhojpatrick.config.env;
    exports com.github.nhojpatrick.config.env.internal
            to com.github.nhojpatrick.config.env.test;
    requires org.apache.commons.lang3;
    requires org.slf4j;
}
