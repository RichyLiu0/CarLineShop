package jum.utility;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ly.mp.common.entities.ConditionConfig;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class mybatisUtils {
    public static <T> QueryWrapper getQueryWrapper(List<ConditionConfig> cfgs, Class<T> type) {
        QueryWrapper wrapper = new QueryWrapper();
        if (!CollectionUtils.isEmpty(cfgs)) {
            for (ConditionConfig cfg : cfgs) {
                if ("eq".equals(cfg.getOperator())){
                    wrapper.eq(cfg.getColName(), cfg.getColValue());
                }
                if ("le".equals(cfg.getOperator())) {
                    wrapper.le(cfg.getColName(), cfg.getColValue());
                }
                if ("ge".equals(cfg.getOperator())) {
                    wrapper.ge(cfg.getColName(), cfg.getColValue());
                }
                if ("like".equals(cfg.getOperator())) {
                    wrapper.like(cfg.getColName(), cfg.getColValue());
                }
                if ("like".equals(cfg.getOperator())) {
                    wrapper.like(cfg.getColName(), cfg.getColValue());
                }
            }
        }
        return wrapper;
    }
}
