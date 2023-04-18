package com.msys.digitalwallet.notifcation.config;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.config.MeterFilter;
import io.micrometer.core.instrument.distribution.DistributionStatisticConfig;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MonitoringConfiguration {
    @Bean
    MeterRegistryCustomizer<MeterRegistry> meterRegistryCustomizer() {
        return registry -> registry.config()
                .meterFilter(new MeterFilter() {
                    @Override
                    public DistributionStatisticConfig configure(Meter.Id id, DistributionStatisticConfig config) {
                        return config.merge(DistributionStatisticConfig.builder()
                                .percentiles(0.1, 0.25, 0.5, 0.75, 0.9, 0.95, 0.99, 0.995, 0.999)
                                .percentilesHistogram(true)
                                .build());
                    }
                });
    }

    /**
     * AspectJ aspect for intercepting types or method annotated with @Timed.
     * @param registry default meter registry
     *
     * @return TimedAspect timed aspect
     */
    @Bean
    public TimedAspect timedAspect(MeterRegistry registry) {
        return new TimedAspect(registry);
    }
}