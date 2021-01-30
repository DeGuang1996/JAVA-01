package gateway.router;

import com.alibaba.common.lang.StringUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author deguang
 * @date 2021/01/31
 */

@Slf4j
public class RoundRibbonRouter implements HttpEndpointRouter {

    public static final int MAX_TRY_COUNT = 10;
    private static int CUR_SEL = 0;

    @Override
    public String route(List<String> endpoints) {
        if (endpoints == null) {
            log.warn("no load balancer");
            return null;
        }
        int count = 0;
        while (count++ < MAX_TRY_COUNT) {
            // 获取可用的server列表
            int serverCount = endpoints.size();
            int next = calNext(serverCount);
            if (next < endpoints.size() && StringUtil.isNotBlank(endpoints.get(next))) {
                return endpoints.get(next);
            }
        }
        log.warn("No available alive servers after 10 tries from load balancer:{}", endpoints);
        return null;
    }

    public synchronized int calNext(int max) {
        CUR_SEL++;
        return (CUR_SEL + 1) % max;
    }
}
