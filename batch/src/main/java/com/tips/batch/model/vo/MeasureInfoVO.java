package com.tips.batch.model.vo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.tips.batch.model.FileWriteDTO;
import com.tips.batch.model.entity.MeasureInfoReal;

public class MeasureInfoVO
{
    private Map<String, MeasureInfoReal> measureInfoReal = new HashMap<String, MeasureInfoReal>();

    public boolean containsKey(Object key)
    {
        return measureInfoReal.containsKey(key);
    }

    public MeasureInfoReal put(String key, MeasureInfoReal value)
    {
        return measureInfoReal.put(key, value);
    }

    public Collection<MeasureInfoReal> values()
    {
        return measureInfoReal.values();
    }

    public MeasureInfoReal get(Object key)
    {
        return measureInfoReal.get(key);
    }
}
