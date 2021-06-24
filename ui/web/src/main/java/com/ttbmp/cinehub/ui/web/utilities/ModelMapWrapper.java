package com.ttbmp.cinehub.ui.web.utilities;

import org.springframework.lang.NonNull;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import java.util.Collection;
import java.util.Map;

public class ModelMapWrapper implements Model {

    private final ModelMap map;

    public ModelMapWrapper(ModelMap map) {
        this.map = map;
    }

    @Override
    @NonNull
    public Model addAttribute(@NonNull String attributeName, Object attributeValue) {
        map.addAttribute(attributeName, attributeValue);
        return this;
    }

    @Override
    @NonNull
    public Model addAttribute(@NonNull Object attributeValue) {
        map.addAttribute(attributeValue);
        return this;
    }

    @Override
    @NonNull
    public Model addAllAttributes(@NonNull Collection<?> attributeValues) {
        map.addAllAttributes(attributeValues);
        return this;
    }

    @Override
    @NonNull
    public Model addAllAttributes(@NonNull Map<String, ?> attributes) {
        map.addAllAttributes(attributes);
        return this;
    }

    @Override
    @NonNull
    public Model mergeAttributes(@NonNull Map<String, ?> attributes) {
        map.mergeAttributes(attributes);
        return this;
    }

    @Override
    public boolean containsAttribute(@NonNull String attributeName) {
        return map.containsAttribute(attributeName);
    }

    @Override
    public Object getAttribute(@NonNull String attributeName) {
        return map.getAttribute(attributeName);
    }

    @Override
    @NonNull
    public Map<String, Object> asMap() {
        return map;
    }

}
