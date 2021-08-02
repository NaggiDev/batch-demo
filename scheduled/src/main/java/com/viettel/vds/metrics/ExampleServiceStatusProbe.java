package com.viettel.vds.metrics;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.stereotype.Component;

// The following metric probe would output
//
// # HELP service_status Example about service status
// # TYPE service_status gauge
// service_status{service="sample",} 1.0
@Component(value = "ExampleServiceStatusProbe")
public class ExampleServiceStatusProbe implements MeterBinder {

    private static final double UP = 1.0;
    private final String name;
    private final String description;
    private final Iterable<Tag> tags;

    public ExampleServiceStatusProbe() {
        name = "service_status";
        description = "Example about service status";
        tags = Tags.of(Tag.of("service", "sample"));
    }

    public double value() {
        // make metrics logic here
        return UP;
    }

    @Override
    public void bindTo(MeterRegistry meterRegistry) {
        Gauge.builder(name, this, ExampleServiceStatusProbe::value)
                .description(description)
                .tags(tags)
                .baseUnit("status")
                .register(meterRegistry);
    }
}
