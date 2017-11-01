package xyz.chanjkf.aop;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by yi on 2017/9/1.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceHolder.getKey();
    }
}
